package com.devsu.ms_cliente_persona_api.service;

import com.devsu.ms_cliente_persona_api.model.Cliente;
import com.devsu.ms_cliente_persona_api.exception.ResourceNotFoundException;
import com.devsu.ms_cliente_persona_api.repository.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente clienteDetails) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado para este id :: " + id));

        cliente.setNombre(clienteDetails.getNombre());
        cliente.setClave(clienteDetails.getClave());
        cliente.setEstado(clienteDetails.isEstado());
        // Otros setters para las propiedades de Persona

        return clienteRepository.save(cliente);
    }

    public ResponseEntity<?> deleteCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado para este id :: " + id));

        clienteRepository.delete(cliente);
        return ResponseEntity.ok().build();
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado para este id :: " + id));
    }
}
