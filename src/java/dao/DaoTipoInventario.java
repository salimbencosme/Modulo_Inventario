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
import model.TipoInventario;

/**
 *
 * @author Salim
 */
public class DaoTipoInventario {
    
    
       
    //Este metodo es utilizado para obtener una lista de todos los inventarios
    public static List<TipoInventario> obtenerListaTipoInventario()throws SQLException{
   
        List<TipoInventario> listaTipoInventario=new ArrayList<>();
        
        String query="SELECT idTipoInventario,DescripcionInventario,CuentaContable,Estado FROM Tipoinventario WHERE Estado='D'";
      
          try{  
              
             ConexionBaseDatos.conectarBaseDatos();
        
                if(ConexionBaseDatos.getConnection()==null)
                    throw new SQLException("No se pudo conectar ala Base de Datos");
              
           
           PreparedStatement preparedStatement=ConexionBaseDatos.getConnection().prepareCall(query);
        
            CachedRowSet rowSet=new com.sun.rowset.CachedRowSetImpl();
        
            rowSet.populate( preparedStatement.executeQuery());
            
           while(rowSet.next()){
           
           
           listaTipoInventario.add( new TipoInventario(
           
           rowSet.getInt("idTipoInventario"),
           rowSet.getString("DescripcionInventario"),
           rowSet.getString("CuentaContable"),
           rowSet.getString("Estado")
           
           ));
           
           
           }
            
            
            
            return listaTipoInventario;
        
        }finally{
        
        ConexionBaseDatos.desconectarBaseDatos();
              
              
        }
        
    
        
    }
    
    
      //Este metodo es utilizado para registrar un tipo de inventario
    public static int resgitrarTipoInventario(String descripcionTipoInventario,String descripcionCuentaContable,String estado)throws SQLException{
   
       int value=0;
        
        String query="INSERT INTO Tipoinventario (DescripcionInventario,CuentaContable,Estado) " +
                            " VALUES (?,?,?)";
      
          try{  
              
             ConexionBaseDatos.conectarBaseDatos();
        
                if(ConexionBaseDatos.getConnection()==null)
                    throw new SQLException("No se pudo conectar ala Base de Datos");
              
           
           PreparedStatement preparedStatement=ConexionBaseDatos.getConnection().prepareCall(query);
           
       
           preparedStatement.setString(1,descripcionTipoInventario);
           preparedStatement.setString(2,descripcionCuentaContable);
           preparedStatement.setString(3,estado);
           
           
           value= preparedStatement.executeUpdate();
    
           return value;
        }finally{
        
        ConexionBaseDatos.desconectarBaseDatos();
              
              
        }
        
    
        
    }
    
      //Este metodo es utilizado para actualizar el estado de un tipo inventario
    public static int actualizarEstadoTipoInventario (int idTipoIventario,String descripcionEstado)throws SQLException{
   
       int value=0;
        
        String query="UPDATE TipoInventario SET Estado=? WHERE idTipoInventario=?";
      
          try{  
              
             ConexionBaseDatos.conectarBaseDatos();
        
                if(ConexionBaseDatos.getConnection()==null)
                    throw new SQLException("No se pudo conectar ala Base de Datos");
              
           
           PreparedStatement preparedStatement=ConexionBaseDatos.getConnection().prepareCall(query);
           
         preparedStatement.setString(1,descripcionEstado);
           preparedStatement.setInt(2, idTipoIventario);
          
         
           
             value=preparedStatement.executeUpdate();
            
    
           return value;
        }finally{
        
        ConexionBaseDatos.desconectarBaseDatos();
              
              
        }
        
    
        
    }
    
    
}
