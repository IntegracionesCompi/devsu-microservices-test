package com.devsu.cuenta.event;

import java.time.LocalDate;

public class MovimientoRegistradoEvent {

    private String movimientoId;
    private String cuentaId;
    private String clienteId;
    private LocalDate fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldoAnterior;
    private Double saldo;

    public MovimientoRegistradoEvent() {}

    public String getMovimientoId() { return movimientoId; }
    public void setMovimientoId(String movimientoId) { this.movimientoId = movimientoId; }

    public String getCuentaId() { return cuentaId; }
    public void setCuentaId(String cuentaId) { this.cuentaId = cuentaId; }

    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }

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
