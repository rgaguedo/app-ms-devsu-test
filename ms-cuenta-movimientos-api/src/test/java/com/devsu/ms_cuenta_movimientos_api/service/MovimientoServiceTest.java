package com.devsu.ms_cuenta_movimientos_api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.devsu.ms_cuenta_movimientos_api.model.Cuenta;
import com.devsu.ms_cuenta_movimientos_api.model.Movimiento;
import com.devsu.ms_cuenta_movimientos_api.repository.CuentaRepository;
import com.devsu.ms_cuenta_movimientos_api.repository.MovimientoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class MovimientoServiceTest {

    @Mock
    private MovimientoRepository movimientoRepository;

    @Mock
    private CuentaRepository cuentaRepository;

    @InjectMocks
    private MovimientoService movimientoService;

    @Test
    public void testCreateMovimiento() throws Exception {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setNumeroCuenta(1654321L);
        cuenta.setSaldoInicial(1000);

        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setValor(500);

        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuenta));
        when(movimientoRepository.save(movimiento)).thenReturn(movimiento);

        Movimiento createdMovimiento = movimientoService.createMovimiento(movimiento);

        assertThat(createdMovimiento.getSaldo()).isEqualTo(1500);
    }

    @Test
    public void testCreateMovimientoSaldoInsuficiente() {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setNumeroCuenta(1654321L);
        cuenta.setSaldoInicial(100);

        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setValor(-200);

        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuenta));

        Exception exception = assertThrows(Exception.class, () -> movimientoService.createMovimiento(movimiento));

        assertThat(exception.getMessage()).isEqualTo("Saldo no disponible");
    }

}