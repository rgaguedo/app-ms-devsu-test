package com.devsu.ms_cuenta_movimientos_api.service;

import com.devsu.ms_cuenta_movimientos_api.controller.dto.reporte.ReporteResponseDTO;

import java.util.Date;
import java.util.List;

public interface ReporteService {
    List<ReporteResponseDTO> getReporte(Long clienteId, Date fechaInicio, Date fechaFin);
}