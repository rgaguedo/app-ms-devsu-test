package com.devsu.ms_cuenta_movimientos_api.service;

import com.devsu.ms_cuenta_movimientos_api.model.Movimiento;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovimientoService {
    Movimiento createMovimiento(Movimiento movimiento) throws Exception;
    List<Movimiento> getAllMovimientos();
    Movimiento getMovimientoById(Long id);
    Movimiento updateMovimiento(Long id, Movimiento movimientoDetails);
    ResponseEntity<?> deleteMovimiento(Long id);
}
