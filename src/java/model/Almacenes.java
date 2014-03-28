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
public class Almacenes {
    
    private int idAlmacenes;
    private String descripcionAlmacen;
    private String estado;

    public Almacenes(int idAlmacenes, String descripcionAlmacen, String estado) {
        this.idAlmacenes = idAlmacenes;
        this.descripcionAlmacen = descripcionAlmacen;
        this.estado = estado;
    }

    public int getIdAlmacenes() {
        return idAlmacenes;
    }

    public void setIdAlmacenes(int idAlmacenes) {
        this.idAlmacenes = idAlmacenes;
    }

    public String getDescripcionAlmacen() {
        return descripcionAlmacen;
    }

    public void setDescripcionAlmacen(String descripcionAlmacen) {
        this.descripcionAlmacen = descripcionAlmacen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
