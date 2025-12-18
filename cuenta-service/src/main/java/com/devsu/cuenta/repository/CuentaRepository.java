package com.devsu.cuenta.repository;

import com.devsu.cuenta.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta, String> {
    List<Cuenta> findByClienteId(String clienteId);
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
}
