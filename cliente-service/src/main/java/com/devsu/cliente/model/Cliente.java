package com.devsu.cliente.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente extends Persona {

    @Column(nullable = true)
    private String contrasena;

    @Column(nullable = false)
    private Boolean estado;

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }
}
