package com.devsu.ms_cuenta_movimientos_api.controller.dto.movimiento;

public class MovimientoRequestDTO {

    private String tipoMovimiento;
    private double valor;
    private double saldo;
    private MovimientoCuentaDTO cuenta;

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public MovimientoCuentaDTO getCuenta() {
        return cuenta;
    }

    public void setCuenta(MovimientoCuentaDTO cuenta) {
        this.cuenta = cuenta;
    }
}
