package com.devsu.ms_cuenta_movimientos_api.service;

import com.devsu.ms_cuenta_movimientos_api.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClienteService {

//    @Autowired
    private final RestTemplate restTemplate;

    public ClienteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ClienteDTO getClienteById(Long clienteId) {
        return restTemplate.getForObject("http://cliente-persona:8080/clientes/" + clienteId, ClienteDTO.class);
    }

}
