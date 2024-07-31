package com.devsu.ms_cuenta_movimientos_api.controller;

import com.devsu.ms_cuenta_movimientos_api.controller.dto.cuenta.CuentaRequestDTO;
import com.devsu.ms_cuenta_movimientos_api.controller.dto.cuenta.CuentaResponseDTO;
import com.devsu.ms_cuenta_movimientos_api.mapper.CuentaMapper;
import com.devsu.ms_cuenta_movimientos_api.service.impl.CuentaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cuentas")
public class CuentaController {

    private final CuentaServiceImpl cuentaService;

    public CuentaController(CuentaServiceImpl cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping
    public ResponseEntity<?> createCuenta(@RequestBody CuentaRequestDTO cuenta) {
        try {
            return new ResponseEntity<>(
                    CuentaMapper.map.cuentaToCuentaResponseDTO(cuentaService.createCuenta(CuentaMapper.map.cuentaRequestDTOToCliente(cuenta))),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<CuentaResponseDTO>> getAllCuentas() {
        try {
            return new ResponseEntity<>(CuentaMapper.map.cuentaListToCuentaResponseDTOList(cuentaService.getAllCuentas()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaResponseDTO> getCuentaById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(
                    CuentaMapper.map.cuentaToCuentaResponseDTO(cuentaService.getCuentaById(id)),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaResponseDTO> updateCuenta(@PathVariable Long id, @RequestBody CuentaRequestDTO cuenta) {
        try {
            return new ResponseEntity<>(CuentaMapper.map.cuentaToCuentaResponseDTO(cuentaService.updateCuenta(id, CuentaMapper.map.cuentaRequestDTOToCliente(cuenta))), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCuenta(@PathVariable Long id) {
        return cuentaService.deleteCuenta(id);
    }

}
