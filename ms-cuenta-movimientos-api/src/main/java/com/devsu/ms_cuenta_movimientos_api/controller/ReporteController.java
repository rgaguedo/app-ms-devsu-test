package com.devsu.ms_cuenta_movimientos_api.controller;

import com.devsu.ms_cuenta_movimientos_api.controller.dto.reporte.ReporteResponseDTO;
import com.devsu.ms_cuenta_movimientos_api.service.impl.ReporteServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    private final ReporteServiceImpl reporteService;

    public ReporteController(ReporteServiceImpl reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public List<ReporteResponseDTO> getEstadoCuenta(
            @RequestParam("clienteId") Long clienteId,
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin
    ) {
        return reporteService.getReporte(clienteId, fechaInicio, fechaFin);
    }

}
