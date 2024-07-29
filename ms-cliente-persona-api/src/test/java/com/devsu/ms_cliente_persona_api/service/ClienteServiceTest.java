package com.devsu.ms_cliente_persona_api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.devsu.ms_cliente_persona_api.model.Cliente;
import com.devsu.ms_cliente_persona_api.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    public void testCreateCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Jose Lema");
        cliente.setDireccion("Otavalo sn y principal");
        cliente.setTelefono("098254785");
        cliente.setClave("1234");
        cliente.setEstado(true);

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente createdCliente = clienteService.createCliente(cliente);

        assertThat(createdCliente.getNombre()).isEqualTo("Jose Lema");
        assertThat(createdCliente.getDireccion()).isEqualTo("Otavalo sn y principal");
        assertThat(createdCliente.getTelefono()).isEqualTo("098254785");
        assertThat(createdCliente.getClave()).isEqualTo("1234");
        assertThat(createdCliente.isEstado()).isTrue();
    }

    @Test
    public void testUpdateCliente() {
        Long id = 1L;
        Cliente cliente = new Cliente();
        cliente.setClienteId(id);
        cliente.setNombre("Jose Lema");
        cliente.setDireccion("Otavalo sn y principal");
        cliente.setTelefono("098254785");
        cliente.setClave("1234");
        cliente.setEstado(true);

        Cliente updatedDetails = new Cliente();
        updatedDetails.setNombre("Jose Lema Updated");
        updatedDetails.setClave("4321");
        updatedDetails.setEstado(false);

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente updatedCliente = clienteService.updateCliente(id, updatedDetails);

        assertThat(updatedCliente.getNombre()).isEqualTo("Jose Lema Updated");
        assertThat(updatedCliente.getClave()).isEqualTo("4321");
        assertThat(updatedCliente.isEstado()).isFalse();
    }

}