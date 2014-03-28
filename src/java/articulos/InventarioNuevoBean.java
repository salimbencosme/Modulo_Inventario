/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package articulos;

import dao.DaoAlmacenes;
import dao.DaoArticulos;
import dao.DaoTipoInventario;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.swing.JOptionPane;
import model.Almacenes;
import model.Articulos;
import model.TipoInventario;
/**
 *
 * @author Salim
 */
@ManagedBean(name="inventarioNuevoBean")
@RequestScoped
public class InventarioNuevoBean {

    private String descripcionInventario;
    private String descripcionCuenta;
    
    private List<TipoInventario> listaTipoInventario;

    public InventarioNuevoBean() throws SQLException{
        
       
        listaTipoInventario=DaoTipoInventario.obtenerListaTipoInventario();
    
        
    }

    public String getDescripcionInventario() {
        return descripcionInventario;
    }

    public void setDescripcionInventario(String descripcionInventario) {
        this.descripcionInventario = descripcionInventario;
    }

    public String getDescripcionCuenta() {
        return descripcionCuenta;
    }

    public void setDescripcionCuenta(String descripcionCuenta) {
        this.descripcionCuenta = descripcionCuenta;
    }

    public List<TipoInventario> getListaTipoInventario() {
        return listaTipoInventario;
    }

    public void setListaTipoInventario(List<TipoInventario> listaTipoInventario) {
        this.listaTipoInventario = listaTipoInventario;
    }
    
  
    
  
  
   public void registrarAlmacen() throws SQLException {
      
     System.out.println("Invetario: "+getDescripcionInventario());
     System.out.println("Cuenta: "+getDescripcionCuenta());
        DaoTipoInventario.resgitrarTipoInventario(getDescripcionInventario(),getDescripcionCuenta(),"D");
     
        limpiarFormulario();
        FacesMessage msg = new FacesMessage("Inventario y cuenta contable Registrados");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
    
   }
   
  
   
  
   private void limpiarFormulario() throws SQLException{
       
        this.descripcionInventario= "";
        this.descripcionCuenta="";
        listaTipoInventario=DaoTipoInventario.obtenerListaTipoInventario();
   
   }
   
 
   
  public void validateExistenciaTipoInventario(FacesContext context, UIComponent toValidate, Object value) {
  
    String message = "";
   
    
    
    String nombreTipoInvetario = (String)value;
    
   
    for(TipoInventario inventarioLoop: listaTipoInventario){
    
    
         if (nombreTipoInvetario .equalsIgnoreCase( inventarioLoop.getDescripcionInventario())) {
             
              ((UIInput)toValidate).setValid(false);
              message = "El inventario existe por favor ingrese un nuevo inventario.";
              context.addMessage(toValidate.getClientId(context),
              new FacesMessage(message));
              break;
    }
    
    
    
    }
    
}
   
   public void validateExistenciaCuentaContable(FacesContext context, UIComponent toValidate, Object value) {
  
    String message = "";
   
    
    
    String nombreCuentaContable = (String)value;
    
   
    for(TipoInventario inventarioLoop: listaTipoInventario){
    
    
         if (nombreCuentaContable .equalsIgnoreCase( inventarioLoop.getCuentaContable())) {
             
              ((UIInput)toValidate).setValid(false);
              message = "La cuenta contable existe por favor ingrese una nueva cuenta contable.";
              context.addMessage(toValidate.getClientId(context),
              new FacesMessage(message));
              break;
    }
    
    
    
    }
    
}
   
   
}
