package com.devsu.ms_cuenta_movimientos_api.mapper;

import com.devsu.ms_cuenta_movimientos_api.controller.dto.movimiento.MovimientoRequestDTO;
import com.devsu.ms_cuenta_movimientos_api.controller.dto.movimiento.MovimientoResponseDTO;
import com.devsu.ms_cuenta_movimientos_api.model.Movimiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface MovimientoMapper {

    MovimientoMapper map = Mappers.getMapper(MovimientoMapper.class);

    @Mapping(source = "fecha", target = "fecha")
    @Mapping(source = "tipoMovimiento", target = "tipoMovimiento")
    @Mapping(source = "valor", target = "valor")
    @Mapping(source = "saldo", target = "saldo")
    @Mapping(source = "cuenta.id", target = "cuenta.id")
    Movimiento movimientoRequestDTOToMovimiento(MovimientoRequestDTO movimientoRequestDTO);
    MovimientoResponseDTO movimientoToMovimientoResponseDTO(Movimiento movimiento);
    List<MovimientoResponseDTO> movimientoListToMovimientoResponseDTOList(List<Movimiento> movimientoList);

}
