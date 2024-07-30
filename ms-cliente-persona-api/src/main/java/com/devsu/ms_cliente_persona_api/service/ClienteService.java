package com.devsu.ms_cliente_persona_api.service;

import com.devsu.ms_cliente_persona_api.model.Cliente;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClienteService {
    Cliente save(Cliente cliente);
    List<Cliente> findAll();
    Cliente findById(Long id);
    Cliente update(Long id, Cliente clienteDetails);
    ResponseEntity<?> delete(Long id);
}
