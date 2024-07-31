package com.devsu.ms_cuenta_movimientos_api.service;

import com.devsu.ms_cuenta_movimientos_api.controller.dto.cliente.ClienteResponseDTO;
import com.devsu.ms_cuenta_movimientos_api.model.Cuenta;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CuentaService {
    Cuenta createCuenta(Cuenta cuenta);
    List<Cuenta> getAllCuentas();
    Cuenta getCuentaById(Long id);
    Cuenta updateCuenta(Long id, Cuenta cuentaDetails);
    ResponseEntity<?> deleteCuenta(Long id);
}
