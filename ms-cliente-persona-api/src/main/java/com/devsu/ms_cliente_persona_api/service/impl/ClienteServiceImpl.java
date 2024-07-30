package com.devsu.ms_cliente_persona_api.service.impl;

import com.devsu.ms_cliente_persona_api.exception.ResourceNotFoundException;
import com.devsu.ms_cliente_persona_api.model.Cliente;
import com.devsu.ms_cliente_persona_api.repository.ClienteRepository;
import com.devsu.ms_cliente_persona_api.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) throw new ResourceNotFoundException("No existen registros.");
        return clientes;
    }

    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado para este id :: " + id));
    }

    @Override
    public Cliente update(Long id, Cliente clienteDetails) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            cliente.setNombre(clienteDetails.getNombre());
            cliente.setGenero(clienteDetails.getGenero());
            cliente.setEdad(clienteDetails.getEdad());
            cliente.setIdentificacion(clienteDetails.getIdentificacion());
            cliente.setDireccion(clienteDetails.getDireccion());
            cliente.setTelefono(clienteDetails.getTelefono());
            cliente.setClave(clienteDetails.getClave());
            cliente.setEstado(clienteDetails.isEstado());
            return clienteRepository.save(cliente);
        } else {
            throw new ResourceNotFoundException("Cliente no encontrado para este id :: " + id);
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado para este id :: " + id));

        clienteRepository.delete(cliente);
        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

}
