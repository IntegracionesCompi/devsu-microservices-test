package com.devsu.cliente.service;

import com.devsu.cliente.dto.ClienteResponse;
import com.devsu.cliente.dto.CreateClienteRequest;
import com.devsu.cliente.dto.PatchClienteRequest;
import com.devsu.cliente.dto.UpdateClienteRequest;
import com.devsu.cliente.error.NotFoundException;
import com.devsu.cliente.model.Cliente;
import com.devsu.cliente.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponse crear(CreateClienteRequest request) {
        Cliente c = new Cliente();
        c.setId(UUID.randomUUID().toString());
        c.setNombre(request.getNombre());
        c.setGenero(request.getGenero());
        c.setEdad(request.getEdad());
        c.setIdentificacion(request.getIdentificacion());
        c.setDireccion(request.getDireccion());
        c.setTelefono(request.getTelefono());
        c.setEstado(request.getEstado());
        c.setContrasena(request.getContrasena());

        return toResponse(clienteRepository.save(c));
    }

    public List<ClienteResponse> listar() {
        return clienteRepository.findAll().stream().map(this::toResponse).toList();
    }

    public ClienteResponse obtenerPorId(String id) {
        Cliente c = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
        return toResponse(c);
    }

    public ClienteResponse actualizar(String id, CreateClienteRequest request) {
        Cliente c = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));

        c.setNombre(request.getNombre());
        c.setGenero(request.getGenero());
        c.setEdad(request.getEdad());
        c.setIdentificacion(request.getIdentificacion());
        c.setDireccion(request.getDireccion());
        c.setTelefono(request.getTelefono());
        c.setEstado(request.getEstado());

        return toResponse(clienteRepository.save(c));
    }

    public ClienteResponse desactivar(String id) {
        Cliente c = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));

        c.setEstado(false);
        return toResponse(clienteRepository.save(c));
    }

    public ClienteResponse actualizarPut(String id, UpdateClienteRequest request) {
        Cliente c = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));

        c.setNombre(request.getNombre());
        c.setGenero(request.getGenero());
        c.setEdad(request.getEdad());
        c.setIdentificacion(request.getIdentificacion());
        c.setDireccion(request.getDireccion());
        c.setTelefono(request.getTelefono());
        c.setEstado(request.getEstado());

        return toResponse(clienteRepository.save(c));
    }

    public ClienteResponse actualizarPatch(String id, PatchClienteRequest request) {
        Cliente c = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));

        if (request.getNombre() != null) c.setNombre(request.getNombre());
        if (request.getGenero() != null) c.setGenero(request.getGenero());
        if (request.getEdad() != null) c.setEdad(request.getEdad());
        if (request.getIdentificacion() != null) c.setIdentificacion(request.getIdentificacion());
        if (request.getDireccion() != null) c.setDireccion(request.getDireccion());
        if (request.getTelefono() != null) c.setTelefono(request.getTelefono());
        if (request.getEstado() != null) c.setEstado(request.getEstado());

        return toResponse(clienteRepository.save(c));
    }

    private ClienteResponse toResponse(Cliente c) {
        ClienteResponse r = new ClienteResponse();
        r.setClienteId(c.getId());
        r.setNombre(c.getNombre());
        r.setGenero(c.getGenero());
        r.setEdad(c.getEdad());
        r.setIdentificacion(c.getIdentificacion());
        r.setDireccion(c.getDireccion());
        r.setTelefono(c.getTelefono());
        r.setEstado(c.getEstado());
        return r;
    }
}
