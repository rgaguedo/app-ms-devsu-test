package com.devsu.ms_cuenta_movimientos_api.mapper;

import com.devsu.ms_cuenta_movimientos_api.controller.dto.cuenta.CuentaRequestDTO;
import com.devsu.ms_cuenta_movimientos_api.controller.dto.cuenta.CuentaResponseDTO;
import com.devsu.ms_cuenta_movimientos_api.model.Cuenta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CuentaMapper {

    CuentaMapper map = Mappers.getMapper(CuentaMapper.class);

    @Mapping(source = "numeroCuenta", target = "numeroCuenta")
    @Mapping(source = "tipoCuenta", target = "tipoCuenta")
    @Mapping(source = "saldoInicial", target = "saldoInicial")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "clienteId", target = "clienteId")
    Cuenta cuentaRequestDTOToCuenta(CuentaRequestDTO cuentaRequestDTO);
    CuentaResponseDTO cuentaToCuentaResponseDTO(Cuenta cuenta);
    List<CuentaResponseDTO> cuentaListToCuentaResponseDTOList(List<Cuenta> cuentaList);

}
