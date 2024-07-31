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
        try {
            return new ResponseEntity<>(
                    ClienteMapper.map.clienteToClienteResponseDTO(clienteService.createCliente(ClienteMapper.map.clienteRequestToCliente(cliente))),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> getAllClientes() {
        try {
            return new ResponseEntity<>(
                    ClienteMapper.map.clienteListToClienteResponseDTO(clienteService.getAllClientes()),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getClienteById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(
                    ClienteMapper.map.clienteToClienteResponseDTO(clienteService.getClienteById(id)),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteRequestDTO cliente) {
        return new ResponseEntity<>(ClienteMapper.map.clienteToClienteResponseDTO(clienteService.updateCliente(id, ClienteMapper.map.clienteRequestToCliente(cliente))), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
        return clienteService.deleteCliente(id);
    }

}