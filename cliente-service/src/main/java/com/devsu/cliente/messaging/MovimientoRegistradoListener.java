package com.devsu.cliente.messaging;

import com.devsu.cliente.event.MovimientoRegistradoEvent;
import com.devsu.cliente.model.MovimientoNotificacion;
import com.devsu.cliente.repository.MovimientoNotificacionRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MovimientoRegistradoListener {

    private final MovimientoNotificacionRepository repo;

    public MovimientoRegistradoListener(MovimientoNotificacionRepository repo) {
        this.repo = repo;
    }

    @RabbitListener(queues = "movimiento.registrado.queue")
    public void onMessage(MovimientoRegistradoEvent event) {

        if (event.getMovimientoId() != null && repo.existsById(event.getMovimientoId())) {
            return;
        }

        MovimientoNotificacion n = new MovimientoNotificacion();
        n.setMovimientoId(event.getMovimientoId());
        n.setClienteId(event.getClienteId());
        n.setCuentaId(event.getCuentaId());
        n.setFecha(event.getFecha());
        n.setTipoMovimiento(event.getTipoMovimiento());
        n.setValor(event.getValor());
        n.setSaldoAnterior(event.getSaldoAnterior());
        n.setSaldo(event.getSaldo());
        n.setReceivedAt(LocalDateTime.now());

        repo.save(n);
    }
}
