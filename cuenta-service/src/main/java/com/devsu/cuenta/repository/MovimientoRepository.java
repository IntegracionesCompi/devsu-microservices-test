package com.devsu.cuenta.repository;

import com.devsu.cuenta.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, String> {
    List<Movimiento> findByCuentaIdOrderByFechaAsc(String cuentaId);
}
