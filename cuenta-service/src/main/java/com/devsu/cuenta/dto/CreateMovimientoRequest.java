package com.devsu.cuenta.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class CreateMovimientoRequest {

    @NotBlank
    private String cuentaId;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @NotBlank
    private String tipoMovimiento;

    @NotNull
    @Positive
    private Double valor;

    public String getCuentaId() { return cuentaId; }
    public void setCuentaId(String cuentaId) { this.cuentaId = cuentaId; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getTipoMovimiento() { return tipoMovimiento; }
    public void setTipoMovimiento(String tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
}


