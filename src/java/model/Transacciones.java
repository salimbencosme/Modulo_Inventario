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

public class Transacciones {
    
    private int idTransacciones;
    private int idTipoTransacciones;
    private int idArticulos;
    private Date fecha;
    private int cantidad;
    private double monto;

    public Transacciones(int idTransacciones, int idTipoTransacciones, int idArticulos, Date fecha, int cantidad, double monto) {
        this.idTransacciones = idTransacciones;
        this.idTipoTransacciones = idTipoTransacciones;
        this.idArticulos = idArticulos;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.monto = monto;
    }

    public int getIdTransacciones() {
        return idTransacciones;
    }

    public void setIdTransacciones(int idTransacciones) {
        this.idTransacciones = idTransacciones;
    }

    public int getIdTipoTransacciones() {
        return idTipoTransacciones;
    }

    public void setIdTipoTransacciones(int idTipoTransacciones) {
        this.idTipoTransacciones = idTipoTransacciones;
    }

    public int getIdArticulos() {
        return idArticulos;
    }

    public void setIdArticulos(int idArticulos) {
        this.idArticulos = idArticulos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    
    
}
