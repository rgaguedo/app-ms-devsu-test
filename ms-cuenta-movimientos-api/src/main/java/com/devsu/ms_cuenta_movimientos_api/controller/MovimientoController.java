package com.devsu.ms_cuenta_movimientos_api.controller;

import com.devsu.ms_cuenta_movimientos_api.model.Movimiento;
import com.devsu.ms_cuenta_movimientos_api.service.MovimientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public ResponseEntity<?> createMovimiento(@RequestBody Movimiento movimiento) {
        try {
            Movimiento createdMovimiento = movimientoService.createMovimiento(movimiento);
            return new ResponseEntity<>(createdMovimiento, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Movimiento> getAllMovimientos() {
        return movimientoService.getAllMovimientos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> getMovimientoById(@PathVariable Long id) {
        Movimiento movimiento = movimientoService.getMovimientoById(id);
        if (movimiento != null) {
            return new ResponseEntity<>(movimiento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovimiento(@PathVariable Long id, @RequestBody Movimiento movimientoDetails) {
        Movimiento updatedMovimiento = movimientoService.updateMovimiento(id, movimientoDetails);
        if (updatedMovimiento != null) {
            return new ResponseEntity<>(updatedMovimiento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Recurso no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovimiento(@PathVariable Long id) {
        return movimientoService.deleteMovimiento(id);
    }
}
