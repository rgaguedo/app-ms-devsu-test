package com.devsu.ms_cuenta_movimientos_api.service;

import com.devsu.ms_cuenta_movimientos_api.dto.ClienteDTO;
import com.devsu.ms_cuenta_movimientos_api.model.Cuenta;
import com.devsu.ms_cuenta_movimientos_api.repository.CuentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CuentaServiceTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private CuentaService cuentaService;

    private Cuenta cuenta1;
    private Cuenta cuenta2;
    private ClienteDTO cliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        cliente = new ClienteDTO();
        cliente.setId(1L);
        cliente.setNombre("Jose Lema");
        cliente.setGenero("Masculino");
        cliente.setEdad(30);
        cliente.setIdentificacion("1234567890");
        cliente.setDireccion("Otavalo sn y principal");
        cliente.setTelefono("098254785");
        cliente.setClave("1234");
        cliente.setEstado(true);

        cuenta1 = new Cuenta();
        cuenta1.setId(1L);
        cuenta1.setNumeroCuenta(6934509438L);
        cuenta1.setTipoCuenta("Ahorros");
        cuenta1.setSaldoInicial(2000.0);
        cuenta1.setEstado(true);
        cuenta1.setClienteId(1L);

        cuenta2 = new Cuenta();
        cuenta2.setId(2L);
        cuenta2.setNumeroCuenta(9084565445L);
        cuenta2.setTipoCuenta("Corriente");
        cuenta2.setSaldoInicial(1000.0);
        cuenta2.setEstado(true);
        cuenta2.setClienteId(1L);

    }

    @Test
    public void testFindAll() {
        when(cuentaRepository.findAll()).thenReturn(Arrays.asList(cuenta1, cuenta2));
        List<Cuenta> cuentas = cuentaService.getAllCuentas();
        assertEquals(2, cuentas.size());
        verify(cuentaRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuenta1));
        Cuenta cuenta = cuentaService.getCuentaById(1L);
        assertEquals(cuenta1.getNumeroCuenta(), cuenta.getNumeroCuenta());
        verify(cuentaRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateCuenta() {
        when(clienteService.getClienteById(1L)).thenReturn(cliente);
        when(cuentaRepository.save(any(Cuenta.class))).thenReturn(cuenta1);

        Cuenta savedCuenta = cuentaService.createCuenta(cuenta1);

        assertNotNull(savedCuenta);
        assertEquals(6934509438L, savedCuenta.getNumeroCuenta());
        verify(clienteService, times(1)).getClienteById(1L);
        verify(cuentaRepository, times(1)).save(cuenta1);
    }

}