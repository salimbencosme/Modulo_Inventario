/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.CachedRowSet;
import model.ConexionBaseDatos;
import model.ExistenciaXAlmacen;

/**
 *
 * @author Salim
 */
public class DaoExistenciaXAlmacen {
    
    
    //Este metodo es utilizado para obtener una lista de toda la existencia por alamacenes
    public static List<ExistenciaXAlmacen> obtenerListaExistenciaXAlmacen()throws SQLException{
   
        List<ExistenciaXAlmacen> listaExistenciaXAlmacen=new ArrayList<>();
        
        String query="SELECT idAlmacenes,idArticulos,Cantidad FROM ExistenciaXAlmacen;";
      
          try{  
              
             ConexionBaseDatos.conectarBaseDatos();
        
                if(ConexionBaseDatos.getConnection()==null)
                    throw new SQLException("No se pudo conectar ala Base de Datos");
              
           
           PreparedStatement preparedStatement=ConexionBaseDatos.getConnection().prepareCall(query);
        
            CachedRowSet rowSet=new com.sun.rowset.CachedRowSetImpl();
        
            rowSet.populate( preparedStatement.executeQuery());
            
           while(rowSet.next()){
           
           
           listaExistenciaXAlmacen.add( new ExistenciaXAlmacen(
           
           rowSet.getInt("idAlmacenes"),
           rowSet.getInt("idArticulos"),
           rowSet.getInt("Cantidad")
           
           ));
           
           
           }
            
            
            
            return listaExistenciaXAlmacen;
        
        }finally{
        
        ConexionBaseDatos.desconectarBaseDatos();
              
              
        }
        
    
        
    }
    
    
    
    
    
}
