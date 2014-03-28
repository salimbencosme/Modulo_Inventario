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
@ManagedBean(name="articulosEliminarBean")
@RequestScoped
public class ArticulosEliminarBean implements Serializable {

   
   
    private int indexArticulos;
    private ArrayList<SelectItem> listaArticulosSelectItem;

    private int cantidadTotal;


    private List<Articulos> listaArticulos;

    public ArticulosEliminarBean() throws SQLException{

        listaArticulosSelectItem=new ArrayList<SelectItem>();
        
        listaArticulosSelectItem=cargarListaArticulosSelectOneMenu();

        listaArticulos=DaoArticulos.obtenerListaArticulos();

    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }
    
    

    public int getIndexArticulos() {
        return indexArticulos;
    }

    public void setIndexArticulos(int indexArticulos) {
        this.indexArticulos = indexArticulos;
    }
    
  
    public ArrayList<SelectItem> getListaArticulosSelectItem() {
        return listaArticulosSelectItem;
    }

    public void setListaArticulosSelectItem(ArrayList<SelectItem> listaArticulosSelectItem) {
        this.listaArticulosSelectItem = listaArticulosSelectItem;
    }

 
   private ArrayList<SelectItem> cargarListaArticulosSelectOneMenu() throws SQLException{
  
      List<Articulos> listaArticulos=DaoArticulos.obtenerListaArticulos();
      ArrayList<SelectItem> lista=new ArrayList<>();
      int cont=2;
      lista.add( new SelectItem( 1 ,"") );
      for(int x=0;x<listaArticulos.size();x++){
      
      lista.add( new SelectItem( cont , listaArticulos.get(x).getDescripcionArticulo() ) );
        cont++;
      }
    
      
      
  
  return lista;
  }
   
   
   public void registrarArticulosEliminados(ActionEvent actionEvent) throws SQLException, IOException {
       
       
        
       if( this.getCantidadTotal()== 0  && this.indexArticulos != 1){
           
           

           int value= DaoArticulos.actualizarEstadoArticulos( listaArticulos.get( getIndexArticulos() - 2 ).getIdArticulos(),"ND") ;

        
            if(value>0){
          System.out.println("Elimino!");
             limpiarFormulario();
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"INFORMACION","Articulo eliminado");  
             FacesContext.getCurrentInstance().addMessage(null, msg);
            
            } 
       
             setIndexArticulos(1);
             FacesContext.getCurrentInstance().getExternalContext().redirect("articulosEliminar.xhtml");
       }else{
       
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"ERROR","Para eliminar un articulo debe de estar seleccionado y  la cantidad del mismo tiene que  ser cero.\n En caso de no ser cero ,registre la salida del articulo y luego proceda a eliminarlo."); 
      
           FacesContext.getCurrentInstance().addMessage(null, msg); 
           
       
       }
       
  
   }
  
   
   

   
   
   private void limpiarFormulario() throws SQLException{
       
        this.cantidadTotal=0; 
        this.indexArticulos=1;  
        this.listaArticulos=DaoArticulos.obtenerListaArticulos();
       this. listaArticulosSelectItem=cargarListaArticulosSelectOneMenu();
   
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
 
 
   
  public void valueChangeMethod(ValueChangeEvent e) throws SQLException{
		int index=(Integer)e.getNewValue();
      
         System.out.println("Index: "+index);
         
         listaArticulos=DaoArticulos.obtenerListaArticulos();
         
          if(index>1)
            this.setCantidadTotal(  (int) listaArticulos.get( ( index>0 ? (index-2) : 0  )  ).getExistencia() );
          else{
            this.setCantidadTotal(0);
           
          }
          
          
    }
    
   

   
}
