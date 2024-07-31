package com.devsu.ms_cuenta_movimientos_api.client;

import com.devsu.ms_cuenta_movimientos_api.controller.dto.cliente.ClienteResponseDTO;
import org.springframework.web.client.RestTemplate;

public class ClienteClient {

    public static RestTemplate restTemplate;

    public static ClienteResponseDTO getClienteById(Long clienteId) {
        return restTemplate.getForObject("http://cliente-persona:8080/clientes/" + clienteId, ClienteResponseDTO.class);
    }

}
