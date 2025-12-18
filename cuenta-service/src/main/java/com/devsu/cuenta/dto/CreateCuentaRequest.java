package com.devsu.cuenta.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateCuentaRequest {

    @NotBlank
    private String numeroCuenta;

    @NotBlank
    private String tipoCuenta;

    @Min(0)
    private double saldoInicial;

    @NotNull
    private Boolean estado;

    @NotBlank
    private String clienteId;

    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }

    public String getTipoCuenta() { return tipoCuenta; }
    public void setTipoCuenta(String tipoCuenta) { this.tipoCuenta = tipoCuenta; }

    public double getSaldoInicial() { return saldoInicial; }
    public void setSaldoInicial(double saldoInicial) { this.saldoInicial = saldoInicial; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }

    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
}
