package com.devsu.ms_cliente_persona_api.model;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Cliente extends Persona {

    private String clave;
    private boolean estado;


    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
