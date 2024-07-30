package com.devsu.ms_cliente_persona_api.repository;

import com.devsu.ms_cliente_persona_api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
