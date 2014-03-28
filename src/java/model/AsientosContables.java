/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Salim
 */

import java.util.Date;

public class AsientosContables {
    
    private int idAsientosContables;
    private int idTipoInventario;
    private String DescripcionAsientoContable;
    private String cuenta;
    private String tipo_Movimiento;
    private Date fecha_Asiento;
    private double monto;
    private String estado;

    public AsientosContables(int idAsientosContables, int idTipoInventario, String DescripcionAsientoContable, String cuenta, String tipo_Movimiento, Date fecha_Asiento, double monto, String estado) {
        this.idAsientosContables = idAsientosContables;
        this.idTipoInventario = idTipoInventario;
        this.DescripcionAsientoContable = DescripcionAsientoContable;
        this.cuenta = cuenta;
        this.tipo_Movimiento = tipo_Movimiento;
        this.fecha_Asiento = fecha_Asiento;
        this.monto = monto;
        this.estado = estado;
    }

    public int getIdAsientosContables() {
        return idAsientosContables;
    }

    public void setIdAsientosContables(int idAsientosContables) {
        this.idAsientosContables = idAsientosContables;
    }

    public int getIdTipoInventario() {
        return idTipoInventario;
    }

    public void setIdTipoInventario(int idTipoInventario) {
        this.idTipoInventario = idTipoInventario;
    }

    public String getDescripcionAsientoContable() {
        return DescripcionAsientoContable;
    }

    public void setDescripcionAsientoContable(String DescripcionAsientoContable) {
        this.DescripcionAsientoContable = DescripcionAsientoContable;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getTipo_Movimiento() {
        return tipo_Movimiento;
    }

    public void setTipo_Movimiento(String tipo_Movimiento) {
        this.tipo_Movimiento = tipo_Movimiento;
    }

    public Date getFecha_Asiento() {
        return fecha_Asiento;
    }

    public void setFecha_Asiento(Date fecha_Asiento) {
        this.fecha_Asiento = fecha_Asiento;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
