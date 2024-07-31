package com.devsu.ms_cuenta_movimientos_api.service.impl;

import com.devsu.ms_cuenta_movimientos_api.client.ClienteClient;
import com.devsu.ms_cuenta_movimientos_api.controller.dto.cliente.ClienteResponseDTO;
import com.devsu.ms_cuenta_movimientos_api.exception.ResourceNotFoundException;
import com.devsu.ms_cuenta_movimientos_api.model.Cuenta;
import com.devsu.ms_cuenta_movimientos_api.repository.CuentaRepository;
import com.devsu.ms_cuenta_movimientos_api.service.CuentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public Cuenta createCuenta(Cuenta cuenta) {
        ClienteResponseDTO cliente = ClienteClient.getClienteById(cuenta.getClienteId());
        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado");
        }
        return cuentaRepository.save(cuenta);
    }

    @Override
    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    @Override
    public Cuenta getCuentaById(Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada para este id :: " + id));
    }

    @Override
    public Cuenta updateCuenta(Long id, Cuenta cuentaDetails) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada para este id :: " + id));

        cuenta.setTipoCuenta(cuentaDetails.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDetails.getSaldoInicial());
        cuenta.setEstado(cuentaDetails.isEstado());

        return cuentaRepository.save(cuenta);
    }

    @Override
    public ResponseEntity<?> deleteCuenta(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada para este id :: " + id));
        cuentaRepository.delete(cuenta);
        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

}
