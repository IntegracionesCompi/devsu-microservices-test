package com.devsu.cuenta.dto;

import java.time.LocalDate;

public class MovimientoResponse {

    private String movimientoId;
    private String cuentaId;
    private LocalDate fecha;
    private String tipoMovimiento;
    private double valor;
    private double saldoAnterior;
    private double saldo;

    public String getMovimientoId() { return movimientoId; }
    public void setMovimientoId(String movimientoId) { this.movimientoId = movimientoId; }

    public String getCuentaId() { return cuentaId; }
    public void setCuentaId(String cuentaId) { this.cuentaId = cuentaId; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getTipoMovimiento() { return tipoMovimiento; }
    public void setTipoMovimiento(String tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public double getSaldoAnterior() { return saldoAnterior; }
    public void setSaldoAnterior(double saldoAnterior) { this.saldoAnterior = saldoAnterior; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
}
