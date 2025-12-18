package com.devsu.cuenta.controller;

import com.devsu.cuenta.dto.CreateCuentaRequest;
import com.devsu.cuenta.dto.CuentaResponse;
import com.devsu.cuenta.service.CuentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CuentaResponse crear(@Valid @RequestBody CreateCuentaRequest request) {
        return cuentaService.crear(request);
    }

    @GetMapping
    public List<CuentaResponse> listarPorCliente(@RequestParam String clienteId) {
        return cuentaService.listarPorCliente(clienteId);
    }

    @GetMapping("/{id}")
    public CuentaResponse obtenerPorId(@PathVariable String id) {
        return cuentaService.obtenerPorId(id);
    }
}
