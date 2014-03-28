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
import dao.DaoArticulos;

/**
 *
 * @author Salim
 */

@ManagedBean(name="indexBean")
@RequestScoped
public class IndexBean {
    
    private String descripcionArticulo;
 
    
    public IndexBean() {
        
        descripcionArticulo="";
        
    }
    
    
    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }


    public String buscar()throws SQLException{
    
            return "index.xhtml";
        
    }

    
    public ResultSet obtenerArticulos()throws SQLException{
   
         ResultSet resultSet= DaoArticulos.obtenerArticulos( getDescripcionArticulo()  );

         return resultSet;
    
    }




}
