package com.devsu.cuenta.controller;

import com.devsu.cuenta.dto.CreateMovimientoRequest;
import com.devsu.cuenta.dto.MovimientoResponse;
import com.devsu.cuenta.service.MovimientoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovimientoResponse registrar(@Valid @RequestBody CreateMovimientoRequest request) {
        return movimientoService.registrar(request);
    }

    @GetMapping
    public List<MovimientoResponse> listarPorCuenta(@RequestParam String cuentaId) {
        return movimientoService.listarPorCuenta(cuentaId);
    }
}
