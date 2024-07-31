package com.devsu.ms_cuenta_movimientos_api.controller;

import com.devsu.ms_cuenta_movimientos_api.controller.dto.movimiento.MovimientoRequestDTO;
import com.devsu.ms_cuenta_movimientos_api.controller.dto.movimiento.MovimientoResponseDTO;
import com.devsu.ms_cuenta_movimientos_api.mapper.MovimientoMapper;
import com.devsu.ms_cuenta_movimientos_api.service.impl.MovimientoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoServiceImpl movimientoService;

    public MovimientoController(MovimientoServiceImpl movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public ResponseEntity<?> createMovimiento(@RequestBody MovimientoRequestDTO movimiento) {
        try {
            return new ResponseEntity<>(
                    MovimientoMapper.map.movimientoToMovimientoResponseDTO(movimientoService.createMovimiento(MovimientoMapper.map.movimientoRequestDTOToMovimiento(movimiento))),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<MovimientoResponseDTO>> getAllMovimientos() {
        try {
            return new ResponseEntity<>(
                    MovimientoMapper.map.movimientoListToMovimientoResponseDTOList(movimientoService.getAllMovimientos()),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoResponseDTO> getMovimientoById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(
                    MovimientoMapper.map.movimientoToMovimientoResponseDTO(movimientoService.getMovimientoById(id)),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoResponseDTO> updateMovimiento(@PathVariable Long id, @RequestBody MovimientoRequestDTO movimiento) {
        try {
            return new ResponseEntity<>(
                    MovimientoMapper.map.movimientoToMovimientoResponseDTO(movimientoService.updateMovimiento(id, MovimientoMapper.map.movimientoRequestDTOToMovimiento(movimiento))), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovimiento(@PathVariable Long id) {
        return movimientoService.deleteMovimiento(id);
    }
}
