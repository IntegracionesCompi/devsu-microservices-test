package com.devsu.cuenta.service;

import com.devsu.cuenta.client.ClienteServiceClient;
import com.devsu.cuenta.dto.CreateCuentaRequest;
import com.devsu.cuenta.dto.CuentaResponse;
import com.devsu.cuenta.error.BusinessException;
import com.devsu.cuenta.error.NotFoundException;
import com.devsu.cuenta.model.Cuenta;
import com.devsu.cuenta.repository.CuentaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteServiceClient clienteServiceClient;

    public CuentaService(CuentaRepository cuentaRepository, ClienteServiceClient clienteServiceClient) {
        this.cuentaRepository = cuentaRepository;
        this.clienteServiceClient = clienteServiceClient;
    }

    public CuentaResponse crear(CreateCuentaRequest request) {
        if (!clienteServiceClient.existeCliente(request.getClienteId())) {
            throw new BusinessException("Cliente no encontrado", "CLIENTE_NO_ENCONTRADO", HttpStatus.NOT_FOUND);
        }

        String id = UUID.randomUUID().toString();

        Cuenta cuenta = new Cuenta();
        cuenta.setId(id);
        cuenta.setNumeroCuenta(request.getNumeroCuenta());
        cuenta.setTipoCuenta(request.getTipoCuenta());
        cuenta.setSaldoInicial(request.getSaldoInicial());
        cuenta.setSaldoDisponible(request.getSaldoInicial());
        cuenta.setEstado(request.getEstado());
        cuenta.setClienteId(request.getClienteId());

        Cuenta saved = cuentaRepository.save(cuenta);
        return toResponse(saved);
    }

    public CuentaResponse obtenerPorId(String cuentaId) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId)
                .orElseThrow(() -> new NotFoundException("Cuenta no encontrada"));
        return toResponse(cuenta);
    }

    public List<CuentaResponse> listarPorCliente(String clienteId) {
        return cuentaRepository.findByClienteId(clienteId).stream()
                .map(this::toResponse)
                .toList();
    }

    public void actualizarSaldo(String cuentaId, double nuevoSaldo) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId)
                .orElseThrow(() -> new NotFoundException("Cuenta no encontrada"));

        cuenta.setSaldoDisponible(nuevoSaldo);
        cuentaRepository.save(cuenta);
    }

    private CuentaResponse toResponse(Cuenta cuenta) {
        CuentaResponse r = new CuentaResponse();
        r.setCuentaId(cuenta.getId());
        r.setNumeroCuenta(cuenta.getNumeroCuenta());
        r.setTipoCuenta(cuenta.getTipoCuenta());
        r.setSaldoInicial(cuenta.getSaldoInicial());
        r.setSaldoDisponible(cuenta.getSaldoDisponible());
        r.setEstado(cuenta.getEstado());
        r.setClienteId(cuenta.getClienteId());
        return r;
    }
}
