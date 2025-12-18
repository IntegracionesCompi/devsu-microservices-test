package com.devsu.cuenta.controller;

import com.devsu.cuenta.dto.ReporteMovimientoResponse;
import com.devsu.cuenta.service.ReporteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public List<ReporteMovimientoResponse> generar(
            @RequestParam String clienteId,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin
    ) {
        return reporteService.generar(clienteId, fechaInicio, fechaFin);
    }
}
