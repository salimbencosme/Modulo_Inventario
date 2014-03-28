/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

/**
 *
 * @author Salim
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.CachedRowSet;
import model.Almacenes;

import model.ConexionBaseDatos;

public class DaoAlmacenes {

     //Este metodo es utilizado para obtener todos los almacenes, para llenar una tabla con los campos de almacen
    public static ResultSet obtenerAlmacenes(String descripcionAlmacen)throws SQLException{
   
        String query="";
      
          try{  
              
             ConexionBaseDatos.conectarBaseDatos();
        
                if(ConexionBaseDatos.getConnection()==null)
                    throw new SQLException("No se pudo conectar ala Base de Datos");
              
             
            if(descripcionAlmacen.length()>0){
            
            query="SELECT DescripcionAlmacen, Estado From Almacenes WHERE DescripcionAlmacen= '"+descripcionAlmacen+"'"+" AND Estado='D' ";
        
            }else{
                
             query="SELECT DescripcionAlmacen, Estado From Almacenes WHERE Estado='D'";
            
            }
               
            
           PreparedStatement preparedStatement=ConexionBaseDatos.getConnection().prepareCall(query);
        
            CachedRowSet rowSet=new com.sun.rowset.CachedRowSetImpl();
        
            rowSet.populate( preparedStatement.executeQuery());
            
            
            return rowSet;
        
        }finally{
        
        ConexionBaseDatos.desconectarBaseDatos();
              
              
        }
        
    
        
    }
    
    
    //Este metodo es utilizado para obtener una lista de todos los almacenes
    public static List<Almacenes> obtenerListaAlmacenes()throws SQLException{
   
        List<Almacenes> listaAlmacenes=new ArrayList<>();
        
        String query="SELECT idAlmacenes,DescripcionAlmacen, Estado From Almacenes WHERE Estado='D'";
      
          try{  
              
             ConexionBaseDatos.conectarBaseDatos();
        
                if(ConexionBaseDatos.getConnection()==null)
                    throw new SQLException("No se pudo conectar ala Base de Datos");
              
           
           PreparedStatement preparedStatement=ConexionBaseDatos.getConnection().prepareCall(query);
        
            CachedRowSet rowSet=new com.sun.rowset.CachedRowSetImpl();
        
            rowSet.populate( preparedStatement.executeQuery());
            
           while(rowSet.next()){
           
           
           listaAlmacenes.add( new Almacenes(
           
           rowSet.getInt("idAlmacenes"),
           rowSet.getString("DescripcionAlmacen"),
           rowSet.getString("Estado")
           
           ));
           
           
           }
            
            
            
            return listaAlmacenes;
        
        }finally{
        
        ConexionBaseDatos.desconectarBaseDatos();
              
              
        }
        
    
        
    }
    
    
    
     //Este metodo es utilizado para registrar un almacen
    public static int resgitrarAlmacen(String descripcionAlmacen,String estado)throws SQLException{
   
       int value=0;
        
        String query="INSERT Almacenes (DescripcionAlmacen,Estado)VALUES (?,?);";
      
          try{  
              
             ConexionBaseDatos.conectarBaseDatos();
        
                if(ConexionBaseDatos.getConnection()==null)
                    throw new SQLException("No se pudo conectar ala Base de Datos");
              
           
           PreparedStatement preparedStatement=ConexionBaseDatos.getConnection().prepareCall(query);
           
          
           preparedStatement.setString(1,descripcionAlmacen);
           preparedStatement.setString(2,estado);
           
           
           value= preparedStatement.executeUpdate();
    
           return value;
        }finally{
        
        ConexionBaseDatos.desconectarBaseDatos();
              
              
        }
        
    
        
    }
    
    
    //Este metodo es utilizado para actualizar el estado de un almacen
    public static int actualizarEstadoAlmacen (int idAlmacenes,String descripcionEstado)throws SQLException{
   
       int value=0;
        
        String query="UPDATE Almacenes SET Estado=? WHERE idAlmacenes=?";
      
          try{  
              
             ConexionBaseDatos.conectarBaseDatos();
        
                if(ConexionBaseDatos.getConnection()==null)
                    throw new SQLException("No se pudo conectar ala Base de Datos");
              
           
           PreparedStatement preparedStatement=ConexionBaseDatos.getConnection().prepareCall(query);
           
         preparedStatement.setString(1,descripcionEstado);
           preparedStatement.setInt(2, idAlmacenes);
          
         
           
             value=preparedStatement.executeUpdate();
            
    
           return value;
        }finally{
        
        ConexionBaseDatos.desconectarBaseDatos();
              
              
        }
        
    
        
    }
    
    
    
    
}
