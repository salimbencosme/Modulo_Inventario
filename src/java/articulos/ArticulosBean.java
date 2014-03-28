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
@ManagedBean(name="articulosBean")
@RequestScoped
public class ArticulosBean {

   
    private int indexAlmacenes;
    private int indexTipoInventarios;
    private ArrayList<SelectItem> listaAlmacenesSelectItem;
    private ArrayList<SelectItem> listaTipoInventarioSelectItem;

    private String descripcion;
    private int cantidad;
    private int costoUnitario;

    private List<Articulos> listaArticulos;
    private List<Almacenes> listaAlmacenes;
    private List<TipoInventario> listaTipoInventario;

    public ArticulosBean() throws SQLException{
        
        listaAlmacenesSelectItem=new ArrayList<SelectItem>();
        listaTipoInventarioSelectItem=new ArrayList<SelectItem>();
        
        listaTipoInventarioSelectItem=cargarListaTipoInventarioSelectOneMenu();
        listaAlmacenesSelectItem=cargarListaAlmacenesSelectOneMenu();
        listaArticulos=DaoArticulos.obtenerListaArticulos();
        listaAlmacenes=DaoAlmacenes.obtenerListaAlmacenes();
        listaTipoInventario=DaoTipoInventario.obtenerListaTipoInventario();
        
    }
    
     public int getIndexAlmacenes() {
        return indexAlmacenes;
    }

    public void setIndexAlmacenes(int indexAlmacenes) {
        this.indexAlmacenes = indexAlmacenes;
    }
    
    public int getIndexTipoInventarios() {
        return indexTipoInventarios;
    }

    public void setIndexTipoInventarios(int indexTipoInventarios) {
        this.indexTipoInventarios = indexTipoInventarios;
    }

    public ArrayList<SelectItem> getListaAlmacenesSelectItem() {
        return listaAlmacenesSelectItem;
    }

    public void setListaAlmacenesSelectItem(ArrayList<SelectItem> listaAlmacenesSelectItem) {
        this.listaAlmacenesSelectItem = listaAlmacenesSelectItem;
    }

    public void setListaTipoInventarioSelectItem(ArrayList<SelectItem> listaTipoInventarioSelectItem) {
        this.listaTipoInventarioSelectItem = listaTipoInventarioSelectItem;
    }
  
    public ArrayList<SelectItem> getListaTipoInventarioSelectItem() {
        return listaTipoInventarioSelectItem;
    }
    
      public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) throws Exception {
      
        
        this.cantidad = cantidad;
    }

    public boolean validadorCantidad(){
    
        boolean validador=false;
        
        if(cantidad>0)
            validador=true;    
    return validador;
    }
    public int getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(int costoUnitario) {
        this.costoUnitario = costoUnitario;
    }
  
  private ArrayList<SelectItem> cargarListaAlmacenesSelectOneMenu() throws SQLException{
  
      List<Almacenes> listaAlmacenes=DaoAlmacenes.obtenerListaAlmacenes();
      ArrayList<SelectItem> lista=new ArrayList<>();
      int cont=1;
      lista.add( new SelectItem( 0 ,"") );
      for(int x=0;x<listaAlmacenes.size();x++){
      
      lista.add( new SelectItem( cont++ , listaAlmacenes.get(x).getDescripcionAlmacen() ) );
     
      }
      
      
      
  
  return lista;
  }
  
   private ArrayList<SelectItem> cargarListaTipoInventarioSelectOneMenu() throws SQLException{
  
      List<TipoInventario> listaTipoInventario=DaoTipoInventario.obtenerListaTipoInventario();
      ArrayList<SelectItem> lista=new ArrayList<>();
      int cont=1;
      lista.add( new SelectItem( 0 ,"") );
      for(int x=0;x<listaTipoInventario.size();x++){
      
      lista.add( new SelectItem( cont++ , listaTipoInventario.get(x).getDescripcionInventario() ) );
     
      }
      
      
      
  
  return lista;
  }
   
   
   public void registrarArticulos() throws SQLException {
       
       
               
       int idTipoInventario=listaTipoInventario.get( getIndexTipoInventarios() - 1 ).getIdTipoInventario();
       int idAlmacenes=listaAlmacenes.get( getIndexAlmacenes()- 1).getIdAlmacenes();
       
       int verificador=DaoArticulos.resgitrarArticulo(idTipoInventario,getDescripcion(),getCantidad(), getCostoUnitario(), "D");
       
       if(verificador>0){
       
            int idArticulo=obtenerIdArticulo();
            double monto= getCantidad() * getCostoUnitario();
            
            int verificadorTransaccion=DaoArticulos.resgitrarTransaccion(1,idArticulo,getCantidad(),monto);
            
            if(verificadorTransaccion>0){
            
            DaoArticulos.resgitrarExistenciaXAlmacen(idAlmacenes, idArticulo,getCantidad());
            
            
            }
            
       }
      
     
        
        limpiarFormulario();
        FacesMessage msg = new FacesMessage("Articulo Registrado");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
    
   }
   
   private int obtenerIdArticulo() throws SQLException{
   
       int index=0;
       
    listaArticulos=DaoArticulos.obtenerListaArticulos();
    
       for(Articulos articulos: listaArticulos){
       
           if(articulos.getDescripcionArticulo().equals( getDescripcion() )){
           
           index=articulos.getIdArticulos();
               
           
           }
       
       }
       
   
       return index;
   
   }
   
  
   private void limpiarFormulario() throws SQLException{
       
        this.descripcion= "";  
        this.cantidad=0;
        this.costoUnitario=0;
        this.indexAlmacenes=0;
        this.indexTipoInventarios=0;  
        listaArticulos=DaoArticulos.obtenerListaArticulos();
   
   
   }
   
 
   
   public void validateSelectedOneMenu(FacesContext context, UIComponent toValidate, Object value) {
  
    String message = "";
    String nombreComponente=( toValidate.getId().equals("AlmacenSelectOneMenu") ? "almacen" : "tipo de inventario" )   ;
    
    
    int indexSelected = (Integer)value;
    
    if (indexSelected == 0) {
      ((UIInput)toValidate).setValid(false);
      message = "Debe de seleccionar un "+nombreComponente;
      context.addMessage(toValidate.getClientId(context),
        new FacesMessage(message));
    }
    
}
 
   
  public void validateExistenciaArticulo(FacesContext context, UIComponent toValidate, Object value) {
  
    String message = "";
   
    
    
    String nombreArticulo = (String)value;
    
   
    for(Articulos articuloLoop: listaArticulos){
    
    
         if (nombreArticulo.equalsIgnoreCase( articuloLoop.getDescripcionArticulo())) {
             
              ((UIInput)toValidate).setValid(false);
              message = "El Articulo existe por favor ingrese un nuevo articulo.";
              context.addMessage(toValidate.getClientId(context),
              new FacesMessage(message));
              break;
    }
    
    
    
    }
    
}
   
   
   
}
