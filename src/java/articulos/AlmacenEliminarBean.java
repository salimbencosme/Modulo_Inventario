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
@ManagedBean(name="almacenEliminarBean")
@RequestScoped
public class AlmacenEliminarBean implements Serializable {

   
   
    private int indexAlmacen;
    private ArrayList<SelectItem> listaAlmacenesSelectItem;
    private List<Almacenes> listaAlmacenes;
    public AlmacenEliminarBean() throws SQLException{

       listaAlmacenesSelectItem=new ArrayList<SelectItem>();
       listaAlmacenesSelectItem=cargarListaAlmacenSelectOneMenu();
        listaAlmacenes=DaoAlmacenes.obtenerListaAlmacenes();
    }

    public ArrayList<SelectItem> getListaAlmacenesSelectItem() {
        return listaAlmacenesSelectItem;
    }

    public void setListaAlmacenesSelectItem(ArrayList<SelectItem> listaAlmacenesSelectItem) {
        this.listaAlmacenesSelectItem = listaAlmacenesSelectItem;
    }

    public List<Almacenes> getListaAlmacenes() {
        return listaAlmacenes;
    }

    public void setListaAlmacenes(List<Almacenes> listaAlmacenes) {
        this.listaAlmacenes = listaAlmacenes;
    }

   

    public int getIndexAlmacen() {
        return indexAlmacen;
    }

    public void setIndexAlmacen(int indexAlmacen) {
        this.indexAlmacen = indexAlmacen;
    }

  
   private ArrayList<SelectItem> cargarListaAlmacenSelectOneMenu() throws SQLException{
  
      listaAlmacenes=DaoAlmacenes.obtenerListaAlmacenes();
      ArrayList<SelectItem> lista=new ArrayList<>();
      int cont=2;
      lista.add( new SelectItem( 1 ,"") );
      for(int x=0;x<listaAlmacenes.size();x++){
      
      lista.add( new SelectItem( cont , listaAlmacenes.get(x).getDescripcionAlmacen()) );
        cont++;
      }
    
      
      
  
  return lista;
  }
   
   
   public void registrarAlmacenesEliminados(ActionEvent actionEvent) throws SQLException, IOException {
       
        
       if(this.indexAlmacen != 1){
           
           int value=DaoAlmacenes.actualizarEstadoAlmacen(listaAlmacenes.get( getIndexAlmacen() -2   ).getIdAlmacenes(),"ND");
        
            if(value>0){
          
             limpiarFormulario();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"INFORMACION","Almacen eliminado");  
             FacesContext.getCurrentInstance().addMessage(null, msg);
            
            } 
       
            setIndexAlmacen(1);
          
             FacesContext.getCurrentInstance().getExternalContext().redirect("almacenEliminar.xhtml");
       }else{
       
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"ERROR","Nose pudo eliminar el almacen.Debe de seleccionar un almacen"); 
      
           FacesContext.getCurrentInstance().addMessage(null, msg); 
           
       
       }
       
  
   }
  
   
   

   
   
   private void limpiarFormulario() throws SQLException{
       
        
        this.indexAlmacen=1;  
        this.listaAlmacenes=DaoAlmacenes.obtenerListaAlmacenes();
       this. listaAlmacenesSelectItem=cargarListaAlmacenSelectOneMenu();
   
   }
   
 
   
   public void validateSelectedOneMenu(FacesContext context, UIComponent toValidate, Object value) {
  
    String message = "";
    String nombreComponente=( toValidate.getId().equals("AlmacenSelectOneMenu") ? "almacen" : "Articulo" )   ;
    
    
    int indexSelected = (Integer)value;
      
    if (indexSelected == 0) {
     
      ((UIInput)toValidate).setValid(false);
      message = "Debe de seleccionar un "+nombreComponente;
      context.addMessage(toValidate.getClientId(context),
        new FacesMessage(message));
    }
    
}
 
 
   
 
   

   
}
