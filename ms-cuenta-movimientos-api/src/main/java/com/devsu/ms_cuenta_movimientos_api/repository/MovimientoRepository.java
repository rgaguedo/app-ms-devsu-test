package com.devsu.ms_cuenta_movimientos_api.repository;

import com.devsu.ms_cuenta_movimientos_api.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuentaIdAndFechaBetween(Long cuenta_id, LocalDateTime fecha, LocalDateTime fecha2);
    Movimiento findTopByCuentaIdOrderByFechaDesc(Long cuentaId);
}
