package com.devsu.cuenta.service;

import com.devsu.cuenta.dto.CreateMovimientoRequest;
import com.devsu.cuenta.dto.CuentaResponse;
import com.devsu.cuenta.repository.MovimientoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovimientoServiceTest {

    @Mock
    private CuentaService cuentaService;

    @Mock
    private MovimientoRepository movimientoRepository;

    @InjectMocks
    private MovimientoService movimientoService;

    @Test
    void registrar_deposito_seGuardaCorrectamente() {
       
        CuentaResponse cuenta = new CuentaResponse();
        cuenta.setSaldoInicial(100.0);
        cuenta.setEstado(true);
        cuenta.setClienteId("cliente-1"); 

        when(cuentaService.obtenerPorId(any())).thenReturn(cuenta);
        when(movimientoRepository.save(any())).thenAnswer(i -> i.getArgument(0));

    
        CreateMovimientoRequest request = new CreateMovimientoRequest();
        request.setCuentaId("cualquier-id");
        request.setTipoMovimiento("DEPOSITO");
        request.setValor(50.0);

       
        movimientoService.registrar(request);

        // Assert
        verify(movimientoRepository, times(1)).save(any());
    }
}
