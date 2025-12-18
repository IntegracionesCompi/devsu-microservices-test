package com.devsu.cuenta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "movimientos")
public class Movimiento {

    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false)
    private String cuentaId;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private String tipoMovimiento;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private Double saldoAnterior;

    @Column(nullable = false)
    private Double saldo;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCuentaId() { return cuentaId; }
    public void setCuentaId(String cuentaId) { this.cuentaId = cuentaId; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getTipoMovimiento() { return tipoMovimiento; }
    public void setTipoMovimiento(String tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public Double getSaldoAnterior() { return saldoAnterior; }
    public void setSaldoAnterior(Double saldoAnterior) { this.saldoAnterior = saldoAnterior; }

    public Double getSaldo() { return saldo; }
    public void setSaldo(Double saldo) { this.saldo = saldo; }
}
