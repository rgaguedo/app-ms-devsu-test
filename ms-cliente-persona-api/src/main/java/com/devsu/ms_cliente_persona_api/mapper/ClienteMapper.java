package com.devsu.ms_cliente_persona_api.mapper;

import com.devsu.ms_cliente_persona_api.controller.dto.ClienteRequestDTO;
import com.devsu.ms_cliente_persona_api.controller.dto.ClienteResponseDTO;
import com.devsu.ms_cliente_persona_api.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClienteMapper {

    ClienteMapper map = Mappers.getMapper(ClienteMapper.class);

    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "genero", target = "genero")
    @Mapping(source = "edad", target = "edad")
    @Mapping(source = "identificacion", target = "identificacion")
    @Mapping(source = "direccion", target = "direccion")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "clave", target = "clave")
    @Mapping(source = "estado", target = "estado")
    Cliente clienteRequestToCliente(ClienteRequestDTO clienteRequestDTO);
    ClienteResponseDTO clienteToClienteResponseDTO(Cliente cliente);
    List<ClienteResponseDTO> clienteListToClienteResponseDTO(List<Cliente> clienteList);

}
