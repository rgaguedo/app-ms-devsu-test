package com.devsu.ms_cliente_persona_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clienteId;
    private String clave;
    private boolean estado;
}
