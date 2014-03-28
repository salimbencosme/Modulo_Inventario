/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package articulos;

import static com.sun.faces.facelets.util.Path.context;
import dao.DaoAlmacenes;
import dao.DaoArticulos;
import dao.DaoExistenciaXAlmacen;
import dao.DaoTipoInventario;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import model.Almacenes;
import model.Articulos;
import model.ExistenciaXAlmacen;
import model.TipoInventario;
/**
 *
 * @author Salim
 */
@ManagedBean(name="inventarioEliminarBean")
@RequestScoped
public class InventarioEliminarBean implements Serializable {

   
   
    private int indexInventario;
    private ArrayList<SelectItem> listaInventarioSelectItem;
    private List<TipoInventario> listaInventario;
    public InventarioEliminarBean() throws SQLException{

       listaInventarioSelectItem=new ArrayList<SelectItem>();
       listaInventarioSelectItem=cargarListaAlmacenSelectOneMenu();
       listaInventario=DaoTipoInventario.obtenerListaTipoInventario();
    }

    public int getIndexInventario() {
        return indexInventario;
    }

    public void setIndexInventario(int indexInventario) {
        this.indexInventario = indexInventario;
    }

    public ArrayList<SelectItem> getListaInventarioSelectItem() {
        return listaInventarioSelectItem;
    }

    public void setListaInventarioSelectItem(ArrayList<SelectItem> listaInventarioSelectItem) {
        this.listaInventarioSelectItem = listaInventarioSelectItem;
    }

    public List<TipoInventario> getListaInventario() {
        return listaInventario;
    }

    public void setListaInventario(List<TipoInventario> listaInventario) {
        this.listaInventario = listaInventario;
    }


  
   private ArrayList<SelectItem> cargarListaAlmacenSelectOneMenu() throws SQLException{
  
      listaInventario=DaoTipoInventario.obtenerListaTipoInventario();
      ArrayList<SelectItem> lista=new ArrayList<>();
      int cont=2;
      lista.add( new SelectItem( 1 ,"") );
      for(int x=0;x<listaInventario.size();x++){
      
      lista.add( new SelectItem( cont , listaInventario.get(x).getDescripcionInventario()) );
        cont++;
      }
    
      
      
  
  return lista;
  }
   
   
   public void registrarInventariosEliminados(ActionEvent actionEvent) throws SQLException, IOException {
       
        
       if(this.indexInventario != 1){
           
           int value=DaoTipoInventario.actualizarEstadoTipoInventario(this.listaInventario.get( this.indexInventario - 2).getIdTipoInventario(),"ND");
       
            if(value>0){
          
             limpiarFormulario();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"INFORMACION","Inventario eliminado");  
             FacesContext.getCurrentInstance().addMessage(null, msg);
            
            } 
       
            setIndexInventario(1);
            
             FacesContext.getCurrentInstance().getExternalContext().redirect("inventarioEliminar.xhtml");
       }else{
       
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"ERROR","Nose pudo eliminar el inventario.Debe de seleccionar un inventario"); 
      
           FacesContext.getCurrentInstance().addMessage(null, msg); 
           
       
       }
      
  
   }
  
   
   

   
   
   private void limpiarFormulario() throws SQLException{
       
        
        this.indexInventario=1; 
        this.listaInventarioSelectItem=cargarListaAlmacenSelectOneMenu();
        this.listaInventario=DaoTipoInventario.obtenerListaTipoInventario();
   
   
   }
   
 
   
   public void validateSelectedOneMenu(FacesContext context, UIComponent toValidate, Object value) {
  
    String message = "";
   
    
    
    int indexSelected = (Integer)value;
      
    if (indexSelected == 0) {
     
      ((UIInput)toValidate).setValid(false);
      message = "Debe de seleccionar un inventario";
      context.addMessage(toValidate.getClientId(context),
        new FacesMessage(message));
    }
    
}
 
 
   
 
   

   
}
