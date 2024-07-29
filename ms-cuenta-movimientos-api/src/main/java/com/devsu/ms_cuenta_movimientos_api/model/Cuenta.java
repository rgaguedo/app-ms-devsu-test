package com.devsu.ms_cuenta_movimientos_api.model;

import jakarta.persistence.*;
import lombok.Data;
import com.devsu.ms_cliente_persona_api.model.Cliente;

@Data
@Entity
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroCuenta;
    private String tipoCuenta;
    private double saldoInicial;
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
