package com.devsu.ms_cuenta_movimientos_api.service.impl;

import com.devsu.ms_cuenta_movimientos_api.client.ClienteClient;
import com.devsu.ms_cuenta_movimientos_api.controller.dto.cliente.ClienteResponseDTO;
import com.devsu.ms_cuenta_movimientos_api.controller.dto.reporte.ReporteResponseDTO;
import com.devsu.ms_cuenta_movimientos_api.model.Movimiento;
import com.devsu.ms_cuenta_movimientos_api.repository.MovimientoRepository;
import com.devsu.ms_cuenta_movimientos_api.service.ReporteService;
import com.devsu.ms_cuenta_movimientos_api.util.CommonDateUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final MovimientoRepository movimientoRepository;

    public ReporteServiceImpl(MovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }

    @Override
    public List<ReporteResponseDTO> getReporte(Long clienteId, Date fechaInicio, Date fechaFin) {
        LocalDateTime fechaInicioLDT = CommonDateUtils.convertToLocalDateTime(fechaInicio);
        LocalDateTime fechaFinLDT = CommonDateUtils.convertToLocalDateTime(fechaFin).plusDays(1).minusSeconds(1);

        ClienteResponseDTO clienteResponseDTO = ClienteClient.getClienteById(clienteId);
        if (clienteResponseDTO == null) {
            throw new RuntimeException("Cliente no encontrado");
        }

        List<Movimiento> movimientos = movimientoRepository.findByCuentaIdAndFechaBetween(clienteResponseDTO.getId(), fechaInicioLDT, fechaFinLDT);

        return movimientos.stream()
                .map(movimiento -> {
                    ReporteResponseDTO reporte = new ReporteResponseDTO();
                    reporte.setFecha(movimiento.getFecha());
                    reporte.setCliente(clienteResponseDTO.getNombre());
                    reporte.setNumeroCuenta(movimiento.getCuenta().getNumeroCuenta());
                    reporte.setTipoCuenta(movimiento.getCuenta().getTipoCuenta());
                    reporte.setSaldoInicial(movimiento.getCuenta().getSaldoInicial());
                    reporte.setEstado(movimiento.getCuenta().isEstado());
                    reporte.setMovimiento(movimiento.getValor());
                    reporte.setSaldoDisponible(movimiento.getSaldo());
                    return reporte;
                })
                .collect(Collectors.toList());
    }

}
