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
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.swing.JOptionPane;
import model.Almacenes;
import model.Articulos;
import model.ExistenciaXAlmacen;
import model.TipoInventario;
/**
 *
 * @author Salim
 */
@ManagedBean(name="articulosEntradaBean")
@SessionScoped
public class ArticulosEntradaBean {

   
    private int indexAlmacenes;
    private int indexArticulos;
    private ArrayList<SelectItem> listaAlmacenesSelectItem;
    private ArrayList<SelectItem> listaArticulosSelectItem;

    private int cantidadTotal;
    private int cantidad;
    private int costoUnitario;

    private List<Articulos> listaArticulos;
    private List<Almacenes> listaAlmacenes;
    private List<TipoInventario> listaTipoInventario;
    private List<ExistenciaXAlmacen> listaExistenciaXAlmacen;
    
    
    public ArticulosEntradaBean() throws SQLException{
        
        listaAlmacenesSelectItem=new ArrayList<SelectItem>();
        listaArticulosSelectItem=new ArrayList<SelectItem>();
        
        listaArticulosSelectItem=cargarListaArticulosSelectOneMenu();
      
        listaAlmacenesSelectItem=cargarListaAlmacenesSelectOneMenu();
        listaArticulos=DaoArticulos.obtenerListaArticulos();
        listaAlmacenes=DaoAlmacenes.obtenerListaAlmacenes();
        listaTipoInventario=DaoTipoInventario.obtenerListaTipoInventario();
        listaExistenciaXAlmacen=DaoExistenciaXAlmacen.obtenerListaExistenciaXAlmacen();
        
        
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }
    
     public int getIndexAlmacenes() {
        return indexAlmacenes;
    }

    public void setIndexAlmacenes(int indexAlmacenes) {
        this.indexAlmacenes = indexAlmacenes;
    }

    public int getIndexArticulos() {
        return indexArticulos;
    }

    public void setIndexArticulos(int indexArticulos) {
        this.indexArticulos = indexArticulos;
    }
    
    

    public ArrayList<SelectItem> getListaAlmacenesSelectItem() {
        return listaAlmacenesSelectItem;
    }

    public void setListaAlmacenesSelectItem(ArrayList<SelectItem> listaAlmacenesSelectItem) {
        this.listaAlmacenesSelectItem = listaAlmacenesSelectItem;
    }

    public ArrayList<SelectItem> getListaArticulosSelectItem() {
        return listaArticulosSelectItem;
    }

    public void setListaArticulosSelectItem(ArrayList<SelectItem> listaArticulosSelectItem) {
        this.listaArticulosSelectItem = listaArticulosSelectItem;
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
  
   private ArrayList<SelectItem> cargarListaArticulosSelectOneMenu() throws SQLException{
  
      List<Articulos> listaArticulos=DaoArticulos.obtenerListaArticulos();
      ArrayList<SelectItem> lista=new ArrayList<>();
      int cont=1;
      lista.add( new SelectItem( 0 ,"") );
      for(int x=0;x<listaArticulos.size();x++){
      
      lista.add( new SelectItem( cont++ , listaArticulos.get(x).getDescripcionArticulo() ) );
     
      }
      
      
      
  
  return lista;
  }
   
   
   public void registrarEntradaArticulos() throws SQLException {
       
    int idArticulos=listaArticulos.get( getIndexArticulos() - 1 ).getIdArticulos();
    double cantidadArticulo=listaArticulos.get( getIndexArticulos() - 1 ).getExistencia();
    double catidadNueva= cantidadArticulo + getCantidad();
    int idAlmacenes=listaAlmacenes.get( getIndexAlmacenes()- 1).getIdAlmacenes();
    
      
       int verificador=DaoArticulos.resgitrarEntradaArticulo(idArticulos, catidadNueva , getCostoUnitario());
       
       if(verificador>0){
       
            
            double monto= getCantidad() * getCostoUnitario();
            
            int verificadorTransaccion=DaoArticulos.resgitrarTransaccion(2,idArticulos,getCantidad(),monto);
            
            if(verificadorTransaccion>0){
            
                
                if(verificarExistenciaArticuloEnAlmacen(idAlmacenes, idArticulos )){
                
                    int cantidadVieja=obtenercantidadArticuloEnAlmacen(idAlmacenes, idArticulos);
                    
                 DaoArticulos.actualizarExistenciaXAlmacen(idAlmacenes, idArticulos,( cantidadVieja + getCantidad() ) );
                
                
                }else{
                
                     DaoArticulos.resgitrarExistenciaXAlmacen(idAlmacenes, idArticulos,getCantidad());
                    
                }
               
            }
          
       }
        
        limpiarFormulario();
        FacesMessage msg = new FacesMessage("Entrada registrada");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
    
   }
   
   private boolean verificarExistenciaArticuloEnAlmacen(int idAlmacenes, int idArticulos){
      
       for( ExistenciaXAlmacen a: listaExistenciaXAlmacen){
       
           if( a.getIdAlmacenes()== idAlmacenes && a.getIdArticulos() == idArticulos)
               return true;
       }
       
        return false; 
   }
   
   private int  obtenercantidadArticuloEnAlmacen(int idAlmacenes, int idArticulos){
      
       
       
       for( ExistenciaXAlmacen a: listaExistenciaXAlmacen){
       
           if( a.getIdAlmacenes()== idAlmacenes && a.getIdArticulos() == idArticulos)
               return a.getCantidad();
       }
       
        return 0; 
   }
   
   
   
   private int obtenerIdArticulo() throws SQLException{
   
       int index=0;
       
    listaArticulos=DaoArticulos.obtenerListaArticulos();
    
       for(Articulos articulos: listaArticulos){
       
           if(articulos.getDescripcionArticulo().equals( "" )){
           
           index=articulos.getIdArticulos();
               
           
           }
       
       }
       
   
       return index;
   
   }
   
  
   
   
   private void limpiarFormulario() throws SQLException{
       
        this.cantidadTotal=0; 
        this.cantidad=0;
        this.costoUnitario=0;
        this.indexAlmacenes=0;
        this.indexArticulos=0;  
        listaArticulos=DaoArticulos.obtenerListaArticulos();
   
   
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
   
    public void valueChangeMethod(ValueChangeEvent e){
		int index=(Integer)e.getNewValue();
         
          if(index>0)
            this.setCantidadTotal(  (int) listaArticulos.get( ( index>0 ? (index-1) : 0  )  ).getExistencia() );
          else
            this.setCantidadTotal(0);
    }
    
    
    private int obtenerIdAlmacen() throws SQLException{
   
       int index=0;
       
    listaArticulos=DaoArticulos.obtenerListaArticulos();
    
       for(Articulos articulos: listaArticulos){
       
           if(articulos.getDescripcionArticulo().equals( "" )){
           
           index=articulos.getIdArticulos();
               
           
           }
       
       }
       
   
       return index;
   
   }
   
   
}
