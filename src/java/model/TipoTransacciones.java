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
public class TipoTransacciones {
    
    private int idTipoTransacciones;
    private String descripcionTransaccion;

    public TipoTransacciones(int idTipoTransacciones, String descripcionTransaccion) {
        this.idTipoTransacciones = idTipoTransacciones;
        this.descripcionTransaccion = descripcionTransaccion;
    }

    public int getIdTipoTransacciones() {
        return idTipoTransacciones;
    }

    public void setIdTipoTransacciones(int idTipoTransacciones) {
        this.idTipoTransacciones = idTipoTransacciones;
    }

    public String getDescripcionTransaccion() {
        return descripcionTransaccion;
    }

    public void setDescripcionTransaccion(String descripcionTransaccion) {
        this.descripcionTransaccion = descripcionTransaccion;
    }
    
    
}
