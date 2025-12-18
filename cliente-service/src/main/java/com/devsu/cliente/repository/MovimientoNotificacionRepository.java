package com.devsu.cliente.repository;

import com.devsu.cliente.model.MovimientoNotificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoNotificacionRepository extends JpaRepository<MovimientoNotificacion, String> {
}
