package com.devsu.ms_cuenta_movimientos_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.devsu.ms_cuenta_movimientos_api.client.ClienteClient;
import com.devsu.ms_cuenta_movimientos_api.controller.dto.cliente.ClienteResponseDTO;
import com.devsu.ms_cuenta_movimientos_api.controller.dto.reporte.ReporteResponseDTO;
import com.devsu.ms_cuenta_movimientos_api.model.Cuenta;
import com.devsu.ms_cuenta_movimientos_api.model.Movimiento;
import com.devsu.ms_cuenta_movimientos_api.repository.MovimientoRepository;
import com.devsu.ms_cuenta_movimientos_api.service.impl.ReporteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class ReporteServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private MovimientoRepository movimientoRepository;

    @InjectMocks
    private ReporteServiceImpl reporteService;

    private ClienteResponseDTO cliente;
    private Cuenta cuenta;
    private Movimiento movimiento1;
    private Movimiento movimiento2;
    private Date fechaInicio;
    private Date fechaFin;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        restTemplate = mock(RestTemplate.class);
        ClienteClient.restTemplate = restTemplate;

        cliente = new ClienteResponseDTO();
        cliente.setId(1L);
        cliente.setNombre("Jose Lema");
        cliente.setGenero("Masculino");
        cliente.setEdad(30);
        cliente.setIdentificacion("1234567890");
        cliente.setDireccion("Otavalo sn y principal");
        cliente.setTelefono("098254785");
        cliente.setClave("1234");
        cliente.setEstado(true);

        cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setNumeroCuenta(6934509438L);
        cuenta.setTipoCuenta("Ahorros");
        cuenta.setSaldoInicial(2000.0);
        cuenta.setEstado(true);

        movimiento1 = new Movimiento();
        movimiento1.setId(1L);
        movimiento1.setFecha(LocalDateTime.of(2024, 1, 2, 10, 0));
        movimiento1.setTipoMovimiento("Deposito");
        movimiento1.setValor(500.0);
        movimiento1.setSaldo(2500.0);
        movimiento1.setCuenta(cuenta);

        movimiento2 = new Movimiento();
        movimiento2.setId(2L);
        movimiento2.setFecha(LocalDateTime.of(2024, 1, 3, 10, 0));
        movimiento2.setTipoMovimiento("Retiro");
        movimiento2.setValor(-200.0);
        movimiento2.setSaldo(2300.0);
        movimiento2.setCuenta(cuenta);

        fechaInicio = Date.from(LocalDateTime.of(2024, 1, 1, 0, 0).atZone(ZoneId.systemDefault()).toInstant());
        fechaFin = Date.from(LocalDateTime.of(2024, 1, 31, 23, 59).atZone(ZoneId.systemDefault()).toInstant());
    }

    @Test
    public void testGetEstadoCuenta() {
        when(restTemplate.getForObject(anyString(), eq(ClienteResponseDTO.class))).thenReturn(cliente);
        when(movimientoRepository.findByCuentaIdAndFechaBetween(eq(1L), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(List.of(movimiento1, movimiento2));

        List<ReporteResponseDTO> reportes = reporteService.getReporte(1L, fechaInicio, fechaFin);

        assertEquals(2, reportes.size());

        ReporteResponseDTO reporte1 = reportes.get(0);
        assertEquals(movimiento1.getFecha(), reporte1.getFecha());
        assertEquals(cliente.getNombre(), reporte1.getCliente());
        assertEquals(cuenta.getNumeroCuenta(), reporte1.getNumeroCuenta());
        assertEquals(cuenta.getTipoCuenta(), reporte1.getTipoCuenta());
        assertEquals(cuenta.getSaldoInicial(), reporte1.getSaldoInicial());
        assertEquals(cuenta.isEstado(), reporte1.getEstado());
        assertEquals(movimiento1.getValor(), reporte1.getMovimiento());
        assertEquals(movimiento1.getSaldo(), reporte1.getSaldoDisponible());

        ReporteResponseDTO reporte2 = reportes.get(1);
        assertEquals(movimiento2.getFecha(), reporte2.getFecha());
        assertEquals(cliente.getNombre(), reporte2.getCliente());
        assertEquals(cuenta.getNumeroCuenta(), reporte2.getNumeroCuenta());
        assertEquals(cuenta.getTipoCuenta(), reporte2.getTipoCuenta());
        assertEquals(cuenta.getSaldoInicial(), reporte2.getSaldoInicial());
        assertEquals(cuenta.isEstado(), reporte2.getEstado());
        assertEquals(movimiento2.getValor(), reporte2.getMovimiento());
        assertEquals(movimiento2.getSaldo(), reporte2.getSaldoDisponible());
    }
}