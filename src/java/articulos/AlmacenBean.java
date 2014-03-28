/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package articulos;


import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import dao.DaoAlmacenes;

/**
 *
 * @author Salim
 */
@ManagedBean(name="almacenBean")
@RequestScoped
public class AlmacenBean {

        private String descripcionAlmacen;
        

    public AlmacenBean() {
     
       descripcionAlmacen="";
    }
    
    public String getDescripcionAlmacen() {
        return descripcionAlmacen;
    }

    public void setDescripcionAlmacen(String descripcionAlmacen) {
        this.descripcionAlmacen = descripcionAlmacen;
    }

    
      public String buscar()throws SQLException{
    
            return "almacen.xhtml";
     
    }


   public ResultSet obtenerAlmacenes()throws SQLException{
   
         ResultSet resultSet= DaoAlmacenes.obtenerAlmacenes( getDescripcionAlmacen()  );

         return resultSet;
    
    }
    
    
    
}
