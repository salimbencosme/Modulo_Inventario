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
@ManagedBean(name="almacenNuevoBean")
@RequestScoped
public class AlmacenNuevoBean {

    private String descripcion;

    private List<Almacenes> listaAlmacenes;

    public AlmacenNuevoBean() throws SQLException{
        
       
        listaAlmacenes=DaoAlmacenes.obtenerListaAlmacenes();
    
        
    }
    
  
      public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

   
    
  
  
   public void registrarAlmacen() throws SQLException {
      
       DaoAlmacenes.resgitrarAlmacen(getDescripcion(),"D");
               
     
        limpiarFormulario();
        FacesMessage msg = new FacesMessage("Almacen Registrado");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
    
   }
   
  
   
  
   private void limpiarFormulario() throws SQLException{
       
        this.descripcion= "";   
        listaAlmacenes=DaoAlmacenes.obtenerListaAlmacenes();
   
   }
   
 
   
  public void validateExistenciaAlmacen(FacesContext context, UIComponent toValidate, Object value) {
  
    String message = "";
   
    
    
    String nombreAlmacen = (String)value;
    
   
    for(Almacenes almacenLoop: listaAlmacenes){
    
    
         if (nombreAlmacen .equalsIgnoreCase( almacenLoop.getDescripcionAlmacen())) {
             
              ((UIInput)toValidate).setValid(false);
              message = "El Almacen existe por favor ingrese un nuevo almacen.";
              context.addMessage(toValidate.getClientId(context),
              new FacesMessage(message));
              break;
    }
    
    
    
    }
    
}
   
   
   
}
