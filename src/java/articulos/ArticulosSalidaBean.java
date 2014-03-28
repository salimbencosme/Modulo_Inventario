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
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.swing.JOptionPane;
import model.Almacenes;
import model.Articulos;
import model.ExistenciaXAlmacen;
import model.TipoInventario;
/**
 *
 * @author Salim
 */
@ManagedBean(name="articulosSalidaBean")
@SessionScoped
public class ArticulosSalidaBean implements Serializable {

   
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
    
    
    public ArticulosSalidaBean() throws SQLException{
     
    
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
  
     
      ArrayList<SelectItem> lista=new ArrayList<>();
     
      if(this.getIndexArticulos()>1 ){
          
          
            this.listaAlmacenes=obtenerAlmacenesArticuloSeleccionado( listaArticulos.get( getIndexArticulos() - 2 ) );
            int cont=2;
            lista.add( new SelectItem(  1 ,"") );
            for(int x=0;x<listaAlmacenes.size();x++){
                
                lista.add( new SelectItem( cont , listaAlmacenes.get(x).getDescripcionAlmacen() ) );
                cont++;
            }
           
      
      }else{
          
        this.setCantidadTotal(0);
        lista.add( new SelectItem( 0 ,"Ningun articulo en seleccion") );
      
      
      }
 
      
  
  return lista;
  }
  
   private ArrayList<SelectItem> cargarListaArticulosSelectOneMenu() throws SQLException{
  
      List<Articulos> listaArticulos=DaoArticulos.obtenerListaArticulos();
      ArrayList<SelectItem> lista=new ArrayList<>();
      int cont=2;
      lista.add( new SelectItem(  1 ,"") );
      for(int x=0;x<listaArticulos.size();x++){
     
         lista.add( new SelectItem( cont, listaArticulos.get(x).getDescripcionArticulo() ) );
         cont++;
      }
      
      
      
  
  return lista;
  }
   
   
   public void registrarSalidaArticulos() throws SQLException {
     
    int idArticulos=listaArticulos.get( getIndexArticulos() - 2 ).getIdArticulos();
    int idAlmacenes=listaAlmacenes.get( getIndexAlmacenes()- 2).getIdAlmacenes();
   
    double catidadAlmacen=this.obtenerCantidaExistenciaXAlmacen(listaArticulos.get( getIndexArticulos() - 2 ),listaAlmacenes.get( getIndexAlmacenes()- 2));
    double cantidadArticulo=listaArticulos.get( getIndexArticulos() - 2 ).getExistencia();
    
      
       int verificador=DaoArticulos.actualizarCantidadArticulo( ((int)cantidadArticulo -  getCantidad()), idArticulos);
       
       if(verificador>0){
       
            
            double monto= getCantidad() * listaArticulos.get( getIndexArticulos() - 2 ).getCostoUnitario();
            
            int verificadorTransaccion=DaoArticulos.resgitrarTransaccion(3,idArticulos,getCantidad() ,monto);
            
            if(verificadorTransaccion>0){
            
                
                
                DaoArticulos.actualizarExistenciaXAlmacen(idAlmacenes, idArticulos,( (int)catidadAlmacen - getCantidad() ) );
         
            }
          
       }
        
        limpiarFormulario();
            
        FacesMessage msg = new FacesMessage("Salida generada");  
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
        listaArticulosSelectItem=cargarListaArticulosSelectOneMenu();
        listaAlmacenes=DaoAlmacenes.obtenerListaAlmacenes();
        listaExistenciaXAlmacen=DaoExistenciaXAlmacen.obtenerListaExistenciaXAlmacen();
   }
   
 
   
   public void validateSelectedOneMenu(FacesContext context, UIComponent toValidate, Object value) {
  
    String message = "";
    String nombreComponente=( toValidate.getId().equals("almacenSelectOneMenu") ? "almacen" : "Articulo" )   ;
    
    
    int indexSelected = (Integer)value;
    
    if (indexSelected == 1) {
        setCantidadTotal(0);   
      ((UIInput)toValidate).setValid(false);
      message = "Debe de seleccionar un "+nombreComponente;
      context.addMessage(toValidate.getClientId(context),
        new FacesMessage(message));
    }
    
}
   
 
 
    public void validateInputText(FacesContext context, UIComponent toValidate, Object value){
  
    String message = "";
 
    int indexSelected = (Integer)value;
    
    if (indexSelected <= 0 || indexSelected > getCantidadTotal()) {
     ((UIInput)toValidate).setValid(false);
      message = "La cantidad debe ser mayor que cero y menor que la cantidad total del articulo.";
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
/*   
    public void valueChangeMethod(ValueChangeEvent e){
		int index=(Integer)e.getNewValue();
                //listaExistenciaXAlmacen
        if(index>0){   
            
           int idAlmacenesSelected=this.listaAlmacenes.get( this.getIndexAlmacenes()- 1).getIdAlmacenes();
           int idArticulosSelected=this.listaArticulos.get(  index - 1 ).getIdArticulos();
           
           
            for(ExistenciaXAlmacen a: listaExistenciaXAlmacen){
            
               if(a.getIdAlmacenes()== idAlmacenesSelected && a.getIdArticulos()== idArticulosSelected ){
               
                this.setCantidadTotal( a.getCantidad());
               
               
               }
                
            
            }
            
            
        }else
            this.setCantidadTotal(0);
    }
    */
  
    public void valueChangeMethodArticulos() throws SQLException, IOException{
        
        this.cantidadTotal=0;
        this.setIndexAlmacenes(1);   
        this.listaAlmacenesSelectItem =cargarListaAlmacenesSelectOneMenu() ;
        listaArticulos=DaoArticulos.obtenerListaArticulos();
        listaArticulosSelectItem=cargarListaArticulosSelectOneMenu();
          FacesContext.getCurrentInstance().getExternalContext().redirect("articulosSalida.xhtml");
    }
    
     public void valueChangeMethodAlmacenes(ValueChangeEvent e) throws SQLException{
         
       
        this.setIndexAlmacenes((int)e.getNewValue());   
        this.cantidadTotal= obtenerCantidaExistenciaXAlmacen(listaArticulos.get( getIndexArticulos() - 2 ),listaAlmacenes.get(  getIndexAlmacenes()-2 ));
       
    }
  
    
    private List<Almacenes> obtenerAlmacenesArticuloSeleccionado(Articulos articulo) throws SQLException{
   
       List<Almacenes> lista=new ArrayList<>();
       int id=articulo.getIdArticulos();
       listaAlmacenes=DaoAlmacenes.obtenerListaAlmacenes();
       listaExistenciaXAlmacen=DaoExistenciaXAlmacen.obtenerListaExistenciaXAlmacen();    
    
       for(ExistenciaXAlmacen a:  listaExistenciaXAlmacen){
       
          if( id == a.getIdArticulos()){
          
                for(Almacenes r: listaAlmacenes){
                
                    if(r.getIdAlmacenes() == a.getIdAlmacenes()){
                    
                  
                        lista.add( new Almacenes(
                                    r.getIdAlmacenes(),
                                    r.getDescripcionAlmacen(),
                                    r.getEstado()
                        
                        ));
                   
                    }
                
                }
                
          
          }
       
       }
       
   
       return lista;
   
   }
   
    private int obtenerCantidaExistenciaXAlmacen(Articulos articulos, Almacenes almacenes){
    
            for(ExistenciaXAlmacen e: listaExistenciaXAlmacen){
                
                   if(e.getIdArticulos()== articulos.getIdArticulos() && e.getIdAlmacenes()== almacenes.getIdAlmacenes())
                       return e.getCantidad();
            
            }
        
            return 0;      
    }
   
}
