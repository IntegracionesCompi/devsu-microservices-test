package com.devsu.cuenta.service;

import com.devsu.cuenta.config.RabbitConfig;
import com.devsu.cuenta.dto.CreateMovimientoRequest;
import com.devsu.cuenta.dto.MovimientoResponse;
import com.devsu.cuenta.dto.CuentaResponse;
import com.devsu.cuenta.error.BusinessException;
import com.devsu.cuenta.event.MovimientoRegistradoEvent;
import com.devsu.cuenta.model.Movimiento;
import com.devsu.cuenta.repository.MovimientoRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovimientoService {

    private final CuentaService cuentaService;
    private final MovimientoRepository movimientoRepository;
    private final RabbitTemplate rabbitTemplate;

    public MovimientoService(
            CuentaService cuentaService,
            MovimientoRepository movimientoRepository,
            RabbitTemplate rabbitTemplate
    ) {
        this.cuentaService = cuentaService;
        this.movimientoRepository = movimientoRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public MovimientoResponse registrar(CreateMovimientoRequest request) {
        CuentaResponse cuenta = cuentaService.obtenerPorId(request.getCuentaId());

        String tipo = request.getTipoMovimiento();
        if (!"DEPOSITO".equals(tipo) && !"RETIRO".equals(tipo)) {
            throw new BusinessException("Tipo de movimiento inválido", "TIPO_MOVIMIENTO_INVALIDO", HttpStatus.BAD_REQUEST);
        }

        double valor = request.getValor();
        double saldoAnterior = cuenta.getSaldoDisponible();
        double saldoNuevo;

        if ("DEPOSITO".equals(tipo)) {
            saldoNuevo = saldoAnterior + valor;
        } else {
            if (saldoAnterior < valor) {
                throw new BusinessException("Saldo no disponible", "SALDO_NO_DISPONIBLE", HttpStatus.UNPROCESSABLE_ENTITY);
            }
            saldoNuevo = saldoAnterior - valor;
        }

        // Actualiza saldo en DB (tu CuentaService ya lo hace con JPA)
        cuentaService.actualizarSaldo(cuenta.getCuentaId(), saldoNuevo);

        Movimiento mov = new Movimiento();
        mov.setId(UUID.randomUUID().toString());
        mov.setCuentaId(cuenta.getCuentaId());
        mov.setFecha(request.getFecha()); // LocalDate
        mov.setTipoMovimiento(tipo);
        mov.setValor(valor);
        mov.setSaldoAnterior(saldoAnterior);
        mov.setSaldo(saldoNuevo);

        Movimiento saved = movimientoRepository.save(mov);

        // ====== Publicar evento RabbitMQ  ======
        MovimientoRegistradoEvent event = new MovimientoRegistradoEvent();
            event.setMovimientoId(saved.getId());
            event.setCuentaId(saved.getCuentaId());
            event.setClienteId(cuenta.getClienteId());
            event.setFecha(saved.getFecha());
            event.setTipoMovimiento(saved.getTipoMovimiento());
            event.setValor(saved.getValor());
            event.setSaldoAnterior(saved.getSaldoAnterior());
            event.setSaldo(saved.getSaldo());

            rabbitTemplate.convertAndSend(
                    RabbitConfig.MOVIMIENTOS_EXCHANGE,
                    RabbitConfig.MOVIMIENTO_REGISTRADO_KEY,
                    event
        );
       // ========================================================

        return toResponse(saved);
    }

    public List<MovimientoResponse> listarPorCuenta(String cuentaId) {
        return movimientoRepository.findByCuentaIdOrderByFechaAsc(cuentaId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private MovimientoResponse toResponse(Movimiento m) {
        MovimientoResponse r = new MovimientoResponse();
        r.setMovimientoId(m.getId());
        r.setCuentaId(m.getCuentaId());
        r.setFecha(m.getFecha());
        r.setTipoMovimiento(m.getTipoMovimiento());
        r.setValor(m.getValor());
        r.setSaldoAnterior(m.getSaldoAnterior());
        r.setSaldo(m.getSaldo());
        return r;
    }
}
