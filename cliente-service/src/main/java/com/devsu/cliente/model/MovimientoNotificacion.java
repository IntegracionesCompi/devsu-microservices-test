package com.devsu.cliente.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cliente_movimientos")
public class MovimientoNotificacion {

    @Id
    @Column(length = 36)
    private String movimientoId;

    @Column(nullable = false, length = 36)
    private String clienteId;

    @Column(nullable = false, length = 36)
    private String cuentaId;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, length = 30)
    private String tipoMovimiento;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private Double saldoAnterior;

    @Column(nullable = false)
    private Double saldo;

    @Column(nullable = false)
    private LocalDateTime receivedAt;

    public String getMovimientoId() { return movimientoId; }
    public void setMovimientoId(String movimientoId) { this.movimientoId = movimientoId; }

    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }

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

    public LocalDateTime getReceivedAt() { return receivedAt; }
    public void setReceivedAt(LocalDateTime receivedAt) { this.receivedAt = receivedAt; }
}
