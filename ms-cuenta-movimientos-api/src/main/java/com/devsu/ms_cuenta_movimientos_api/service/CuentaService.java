package com.devsu.ms_cuenta_movimientos_api.service;

import com.devsu.ms_cuenta_movimientos_api.dto.ClienteDTO;
import com.devsu.ms_cuenta_movimientos_api.exception.ResourceNotFoundException;
import com.devsu.ms_cuenta_movimientos_api.model.Cuenta;
import com.devsu.ms_cuenta_movimientos_api.repository.CuentaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {

    public static final Logger log = LoggerFactory.getLogger(CuentaService.class);

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteService clienteService;

    public ClienteDTO getCliente(Long id) {
        return this.clienteService.getClienteById(id);
    }

    public Cuenta createCuenta(Cuenta cuenta) {
        ClienteDTO cliente = this.getCliente(cuenta.getClienteId());
        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado");
        }
        return cuentaRepository.save(cuenta);
    }

    public Cuenta updateCuenta(Long id, Cuenta cuentaDetails) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada para este id :: " + id));

        cuenta.setTipoCuenta(cuentaDetails.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDetails.getSaldoInicial());
        cuenta.setEstado(cuentaDetails.isEstado());

        return cuentaRepository.save(cuenta);
    }

    public void deleteCuenta(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada para este id :: " + id));
        cuentaRepository.delete(cuenta);
    }

    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta getCuentaById(Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada para este id :: " + id));
    }

}
