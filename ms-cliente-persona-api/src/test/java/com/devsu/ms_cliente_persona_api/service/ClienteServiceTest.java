package com.devsu.ms_cliente_persona_api.service;

import com.devsu.ms_cliente_persona_api.model.Cliente;
import com.devsu.ms_cliente_persona_api.repository.ClienteRepository;
import com.devsu.ms_cliente_persona_api.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente1;
    private Cliente cliente2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNombre("Jose Lema");
        cliente1.setGenero("Masculino");
        cliente1.setEdad(30);
        cliente1.setIdentificacion("1234567890");
        cliente1.setDireccion("Otavalo sn y principal");
        cliente1.setTelefono("098254785");
        cliente1.setClave("1234");
        cliente1.setEstado(true);

        cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNombre("Marianela Montalvo");
        cliente2.setGenero("Femenino");
        cliente2.setEdad(28);
        cliente2.setIdentificacion("0987654321");
        cliente2.setDireccion("Amazonas y NNUU");
        cliente2.setTelefono("097548965");
        cliente2.setClave("5678");
        cliente2.setEstado(true);
    }

    @Test
    public void testCreateCliente() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente1);
        Cliente savedCliente = clienteService.createCliente(cliente1);
        assertNotNull(savedCliente);
        assertEquals(cliente1.getNombre(), savedCliente.getNombre());
        assertEquals(cliente1.getGenero(), savedCliente.getGenero());
        assertEquals(cliente1.getEdad(), savedCliente.getEdad());
        assertEquals(cliente1.getIdentificacion(), savedCliente.getIdentificacion());
        assertEquals(cliente1.getDireccion(), savedCliente.getDireccion());
        assertEquals(cliente1.getTelefono(), savedCliente.getTelefono());
        assertEquals(cliente1.getClave(), savedCliente.getClave());
        assertEquals(cliente1.isEstado(), savedCliente.isEstado());
        verify(clienteRepository, times(1)).save(cliente1);
    }

    @Test
    public void testGetAllClientes() {
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));
        List<Cliente> clientes = clienteService.getAllClientes();
        assertEquals(2, clientes.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    public void testGetClienteById() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente1));
        Cliente cliente = clienteService.getClienteById(1L);
        assertEquals(cliente1.getNombre(), cliente.getNombre());
        assertEquals(cliente1.getGenero(), cliente.getGenero());
        assertEquals(cliente1.getEdad(), cliente.getEdad());
        assertEquals(cliente1.getIdentificacion(), cliente.getIdentificacion());
        assertEquals(cliente1.getDireccion(), cliente.getDireccion());
        assertEquals(cliente1.getTelefono(), cliente.getTelefono());
        assertEquals(cliente1.getClave(), cliente.getClave());
        assertEquals(cliente1.isEstado(), cliente.isEstado());
        verify(clienteRepository, times(1)).findById(1L);
    }

}