package com.devsu.ms_cuenta_movimientos_api.service;

import com.devsu.ms_cuenta_movimientos_api.exception.ResourceNotFoundException;
import com.devsu.ms_cuenta_movimientos_api.model.Cuenta;
import com.devsu.ms_cuenta_movimientos_api.model.Movimiento;
import com.devsu.ms_cuenta_movimientos_api.repository.CuentaRepository;
import com.devsu.ms_cuenta_movimientos_api.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public Movimiento createMovimiento(Movimiento movimiento) throws Exception {
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada para este id :: " + movimiento.getCuenta().getId()));

        Movimiento ultimoMovimiento = movimientoRepository.findTopByCuentaIdOrderByFechaDesc(cuenta.getId());

        double nuevoSaldo = 0;

        if (ultimoMovimiento == null){
            nuevoSaldo = cuenta.getSaldoInicial() + movimiento.getValor();
        } else if(ultimoMovimiento.getSaldo() + movimiento.getValor() >= 0) {
            nuevoSaldo = ultimoMovimiento.getSaldo() + movimiento.getValor();
        }

        if (nuevoSaldo < 0) {
            throw new Exception("Saldo no disponible");
        }

        cuentaRepository.save(cuenta);

        movimiento.setSaldo(nuevoSaldo);
        movimiento.setCuenta(cuenta);
        return movimientoRepository.save(movimiento);
    }

    public Movimiento updateMovimiento(Long id, Movimiento movimientoDetails) {
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado para este id :: " + id));

        movimiento.setFecha(movimientoDetails.getFecha());
        movimiento.setTipoMovimiento(movimientoDetails.getTipoMovimiento());
        movimiento.setValor(movimientoDetails.getValor());
        movimiento.setSaldo(movimientoDetails.getSaldo());
        movimiento.setCuenta(movimientoDetails.getCuenta());

        return movimientoRepository.save(movimiento);
    }

    public ResponseEntity<?> deleteMovimiento(Long id) {
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado para este id :: " + id));

        movimientoRepository.delete(movimiento);
        return ResponseEntity.ok().build();
    }

    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    public Movimiento getMovimientoById(Long id) {
        return movimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado para este id :: " + id));
    }

}
