package com.devsu.ms_cliente_persona_api.controller;

import com.devsu.ms_cliente_persona_api.controller.dto.ClienteRequestDTO;
import com.devsu.ms_cliente_persona_api.controller.dto.ClienteResponseDTO;
import com.devsu.ms_cliente_persona_api.mapper.ClienteMapper;
import com.devsu.ms_cliente_persona_api.service.impl.ClienteServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteServiceImpl clienteService;

    public ClienteController(ClienteServiceImpl clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> createCliente(@RequestBody ClienteRequestDTO cliente) {
        return new ResponseEntity<>(ClienteMapper.map.clienteToClienteResponseDTO(clienteService.save(ClienteMapper.map.clienteRequestToCliente(cliente))), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteRequestDTO cliente) {
        return new ResponseEntity<>(ClienteMapper.map.clienteToClienteResponseDTO(clienteService.update(id, ClienteMapper.map.clienteRequestToCliente(cliente))), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
        return clienteService.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> getAllClientes() {
        return new ResponseEntity<>(ClienteMapper.map.clienteListToClienteResponseDTO(clienteService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getClienteById(@PathVariable Long id) {
        return new ResponseEntity<>(ClienteMapper.map.clienteToClienteResponseDTO(clienteService.findById(id)), HttpStatus.OK);
    }

}