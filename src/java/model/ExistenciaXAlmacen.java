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
public class ExistenciaXAlmacen {
 
    private int idAlmacenes;
    private int idArticulos;
    private int cantidad;

    public ExistenciaXAlmacen(int idAlmacenes, int idArticulos, int cantidad) {
        this.idAlmacenes = idAlmacenes;
        this.idArticulos = idArticulos;
        this.cantidad = cantidad;
    }

    public int getIdAlmacenes() {
        return idAlmacenes;
    }

    public void setIdAlmacenes(int idAlmacenes) {
        this.idAlmacenes = idAlmacenes;
    }

    public int getIdArticulos() {
        return idArticulos;
    }

    public void setIdArticulos(int idArticulos) {
        this.idArticulos = idArticulos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
