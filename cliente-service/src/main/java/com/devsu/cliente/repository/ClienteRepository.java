package com.devsu.cliente.repository;

import com.devsu.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    Optional<Cliente> findByIdentificacion(String identificacion);
    boolean existsByIdentificacion(String identificacion);
}
