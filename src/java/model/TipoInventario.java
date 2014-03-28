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
public class TipoInventario {
    
    private int idTipoInventario;
    private String descripcionInventario;
    private String cuentaContable;
    private String estado;

    public TipoInventario(int idTipoInventario, String descripcionInventario, String cuentaContable, String estado) {
        this.idTipoInventario = idTipoInventario;
        this.descripcionInventario = descripcionInventario;
        this.cuentaContable = cuentaContable;
        this.estado = estado;
    }

    public int getIdTipoInventario() {
        return idTipoInventario;
    }

    public void setIdTipoInventario(int idTipoInventario) {
        this.idTipoInventario = idTipoInventario;
    }

    public String getDescripcionInventario() {
        return descripcionInventario;
    }

    public void setDescripcionInventario(String descripcionInventario) {
        this.descripcionInventario = descripcionInventario;
    }

    public String getCuentaContable() {
        return cuentaContable;
    }

    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
