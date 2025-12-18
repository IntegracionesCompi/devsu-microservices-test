package com.devsu.cuenta.dto;

import java.time.LocalDate;

public class ReporteMovimientoResponse {

    private LocalDate fecha;
    private String clienteId;
    private String cuentaId;
    private String numeroCuenta;
    private String tipoCuenta;
    private String tipoMovimiento;
    private double valor;
    private double saldoAnterior;
    private double saldo;

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }

    public String getCuentaId() { return cuentaId; }
    public void setCuentaId(String cuentaId) { this.cuentaId = cuentaId; }

    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }

    public String getTipoCuenta() { return tipoCuenta; }
    public void setTipoCuenta(String tipoCuenta) { this.tipoCuenta = tipoCuenta; }

    public String getTipoMovimiento() { return tipoMovimiento; }
    public void setTipoMovimiento(String tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public double getSaldoAnterior() { return saldoAnterior; }
    public void setSaldoAnterior(double saldoAnterior) { this.saldoAnterior = saldoAnterior; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
}
