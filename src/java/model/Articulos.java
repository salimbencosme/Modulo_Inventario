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
public class Articulos {
    
    private int idArticulos;
    private int idTipoInventario;
    private String descripcionArticulo;
    private double existencia;
    private int costoUnitario;
    private String estado;

    public Articulos(int idArticulos, int idTipoInventario, String descripcionArticulo, double existencia, int costoUnitario, String estado) {
        this.idArticulos = idArticulos;
        this.idTipoInventario = idTipoInventario;
        this.descripcionArticulo = descripcionArticulo;
        this.existencia = existencia;
        this.costoUnitario = costoUnitario;
        this.estado = estado;
    }

    public int getIdArticulos() {
        return idArticulos;
    }

    public void setIdArticulos(int idArticulos) {
        this.idArticulos = idArticulos;
    }

    public int getIdTipoInventario() {
        return idTipoInventario;
    }

    public void setIdTipoInventario(int idTipoInventario) {
        this.idTipoInventario = idTipoInventario;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public double getExistencia() {
        return existencia;
    }

    public void setExistencia(double existencia) {
        this.existencia = existencia;
    }

    public int getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(int costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
