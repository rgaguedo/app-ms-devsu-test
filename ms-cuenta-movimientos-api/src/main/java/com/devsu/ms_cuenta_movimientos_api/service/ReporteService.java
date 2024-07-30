package com.devsu.ms_cuenta_movimientos_api.service;

import com.devsu.ms_cuenta_movimientos_api.dto.ClienteDTO;
import com.devsu.ms_cuenta_movimientos_api.dto.ReporteDTO;
import com.devsu.ms_cuenta_movimientos_api.model.Movimiento;
import com.devsu.ms_cuenta_movimientos_api.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MovimientoRepository movimientoRepository;

    public List<ReporteDTO> getReporte(Long clienteId, Date fechaInicio, Date fechaFin) {
        LocalDateTime fechaInicioLDT = convertToLocalDateTime(fechaInicio);
        LocalDateTime fechaFinLDT = convertToLocalDateTime(fechaFin).plusDays(1).minusSeconds(1);

        ClienteDTO clienteDTO = clienteService.getClienteById(clienteId);
        if (clienteDTO == null) {
            throw new RuntimeException("Cliente no encontrado");
        }

        List<Movimiento> movimientos = movimientoRepository.findByCuentaIdAndFechaBetween(clienteDTO.getId(), fechaInicioLDT, fechaFinLDT);

        return movimientos.stream()
                .map(movimiento -> {
                    ReporteDTO reporte = new ReporteDTO();
                    reporte.setFecha(movimiento.getFecha());
                    reporte.setCliente(clienteDTO.getNombre());
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

    private LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}