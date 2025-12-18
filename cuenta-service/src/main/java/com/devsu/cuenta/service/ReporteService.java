package com.devsu.cuenta.service;

import com.devsu.cuenta.dto.CuentaResponse;
import com.devsu.cuenta.dto.MovimientoResponse;
import com.devsu.cuenta.dto.ReporteMovimientoResponse;
import com.devsu.cuenta.error.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ReporteService {

    private final CuentaService cuentaService;
    private final MovimientoService movimientoService;

    public ReporteService(CuentaService cuentaService, MovimientoService movimientoService) {
        this.cuentaService = cuentaService;
        this.movimientoService = movimientoService;
    }

    public List<ReporteMovimientoResponse> generar(String clienteId, String fechaInicio, String fechaFin) {
        LocalDate inicio = parseFecha(fechaInicio);
        LocalDate fin = parseFecha(fechaFin);

        if (fin.isBefore(inicio)) {
            throw new BusinessException("Rango de fechas inválido", "RANGO_FECHAS_INVALIDO", HttpStatus.BAD_REQUEST);
        }

        List<CuentaResponse> cuentas = cuentaService.listarPorCliente(clienteId);
        List<ReporteMovimientoResponse> out = new ArrayList<>();

        for (CuentaResponse cuenta : cuentas) {
            List<MovimientoResponse> movs = movimientoService.listarPorCuenta(cuenta.getCuentaId());

            for (MovimientoResponse mov : movs) {
                LocalDate f = mov.getFecha(); // YA ES LocalDate

                boolean dentro = (f.isEqual(inicio) || f.isAfter(inicio)) && (f.isEqual(fin) || f.isBefore(fin));
                if (!dentro) continue;

                ReporteMovimientoResponse r = new ReporteMovimientoResponse();
                r.setFecha(mov.getFecha()); // LocalDate
                r.setClienteId(clienteId);
                r.setCuentaId(cuenta.getCuentaId());
                r.setNumeroCuenta(cuenta.getNumeroCuenta());
                r.setTipoCuenta(cuenta.getTipoCuenta());
                r.setTipoMovimiento(mov.getTipoMovimiento());
                r.setValor(mov.getValor());
                r.setSaldoAnterior(mov.getSaldoAnterior());
                r.setSaldo(mov.getSaldo());

                out.add(r);
            }
        }

        out.sort(Comparator.comparing(ReporteMovimientoResponse::getFecha));
        return out;
    }

    private LocalDate parseFecha(String fecha) {
        try {
            return LocalDate.parse(fecha);
        } catch (DateTimeParseException e) {
            throw new BusinessException("Formato de fecha inválido. Use YYYY-MM-DD", "FECHA_INVALIDA", HttpStatus.BAD_REQUEST);
        }
    }
}
