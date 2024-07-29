package com.devsu.ms_cuenta_movimientos_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fecha;
    private String tipoMovimiento;
    private double valor;
    private double saldo;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;


}
