package com.devsu.cliente.controller;

import com.devsu.cliente.dto.ClienteResponse;
import com.devsu.cliente.dto.CreateClienteRequest;
import com.devsu.cliente.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.devsu.cliente.dto.PatchClienteRequest;
import com.devsu.cliente.dto.UpdateClienteRequest;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponse crear(@Valid @RequestBody CreateClienteRequest request) {
        return clienteService.crear(request);
    }

    @GetMapping
    public List<ClienteResponse> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{id}")
    public ClienteResponse obtenerPorId(@PathVariable String id) {
        return clienteService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public ClienteResponse actualizarPut(@PathVariable String id, @Valid @RequestBody UpdateClienteRequest request) {
        return clienteService.actualizarPut(id, request);
    }

    @PatchMapping("/{id}")
    public ClienteResponse actualizarPatch(@PathVariable String id, @RequestBody PatchClienteRequest request) {
        return clienteService.actualizarPatch(id, request);
    }

    @DeleteMapping("/{id}")
    public ClienteResponse desactivar(@PathVariable String id) {
        return clienteService.desactivar(id);
    }
}
