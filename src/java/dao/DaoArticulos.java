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
import model.Articulos;
import model.ConexionBaseDatos;

public class DaoArticulos {
  
    
     //Este metodo es utilizado para obtener todos los articulos, para llenar una tabla con los campos de articulos
    public static ResultSet obtenerArticulos(String descripcionArticulo)throws SQLException{
   
        String query="";
      
          try{  
              
             ConexionBaseDatos.conectarBaseDatos();
        
                if(ConexionBaseDatos.getConnection()==null)
                    throw new SQLException("No se pudo conectar ala Base de Datos");
              
             
            if(descripcionArticulo.length()>0){
            
            query=" SELECT a.idArticulos,a.descripcionArticulo,i.descripcionInventario ,a.existencia,a.costoUnitario"
                    + ",a.estado FROM Articulos a  INNER JOIN tipoinventario i ON a.idTipoInventario=i.idTipoInventario "
                    + " WHERE a.descripcionArticulo= '"+descripcionArticulo+"'"+" AND a.Estado='D'  ";
        
            }else{
                
             query="SELECT a.idArticulos,a.descripcionArticulo,i.descripcionInventario ,a.existencia,a.costoUnitario,"
                     + "a.estado FROM Articulos a INNER JOIN tipoinventario i ON a.idTipoInventario=i.idTipoInventario WHERE a.Estado='D'";
            
            }
               
            
           PreparedStatement preparedStatement=ConexionBaseDatos.getConnection().prepareCall(query);
        
            CachedRowSet rowSet=new com.sun.rowset.CachedRowSetImpl();
        
            rowSet.populate( preparedStatement.executeQuery());
            
            
            return rowSet;
        
        }finally{
        
        ConexionBaseDatos.desconectarBaseDatos();
              
              
        }
        
    
        
    }
    
    
    
    //Este metodo es utilizado para obtener una lista de todos los articulos
    public static List<Articulos> obtenerListaArticulos()throws SQLException{
   
        List<Articulos> listaArticulos=new ArrayList<>();
        
        String query="SELECT idArticulos,idTipoInventario,DescripcionArticulo,Existencia,CostoUnitario,Estado FROM Articulos WHERE Estado='D'";
      
          try{  
              
             ConexionBaseDatos.conectarBaseDatos();
        
                if(ConexionBaseDatos.getConnection()==null)
                    throw new SQLException("No se pudo conectar ala Base de Datos");
              
           
           PreparedStatement preparedStatement=ConexionBaseDatos.getConnection().prepareCall(query);
        
            CachedRowSet rowSet=new com.sun.rowset.CachedRowSetImpl();
        
            rowSet.populate( preparedStatement.executeQuery());
            
           while(rowSet.next()){
           
           
           listaArticulos.add( new Articulos(
           
           rowSet.getInt("idArticulos"),
           rowSet.getInt("idTipoInventario"),
           rowSet.getString("DescripcionArticulo"),
           rowSet.getDouble("Existencia"),
           rowSet.getInt("CostoUnitario"),
           rowSet.getString("Estado")
           
           ));
           
           
           }
            
            
            
            return listaArticulos;
        
        }finally{
        
        ConexionBaseDatos.desconectarBaseDatos();
              
              
        }
        
    
        
    }
    
     //Este metodo es utilizado para obtener una lista de todos los articulos
    public static List<Articulos> obtenerListaArticulos(int idTipoinventario)throws SQLException{
   
        List<Articulos> listaArticulos=new ArrayList<>();
        
        String query="SELECT idArticulos,idTipoInventario,DescripcionArticulo,Existencia,CostoUnitario,Estado FROM Articulos WHERE Estado='D' AND idTipoInventario=? ";
      
          try{  
              
             ConexionBaseDatos.conectarBaseDatos();
        
                if(ConexionBaseDatos.getConnection()==null)
                    throw new SQLException("No se pudo conectar ala Base de Datos");
              
           
                 
           PreparedStatement preparedStatement=ConexionBaseDatos.getConnection().prepareCall(query);
            preparedStatement.setInt(1, idTipoinventario);
            CachedRowSet rowSet=new com.sun.rowset.CachedRowSetImpl();
        
            rowSet.populate( preparedStatement.executeQuery());
            
           while(rowSet.next()){
           
           
           listaArticulos.add( new Articulos(
           
           rowSet.getInt("idArticulos"),
           rowSet.getInt("idTipoInventario"),
           rowSet.getString("DescripcionArticulo"),
           rowSet.getDouble("Existencia"),
           rowSet.getInt("CostoUnitario"),
           rowSet.getString("Estado")
           
           ));
           
           
           }
            
            
            
            return listaArticulos;
        
        }finally{
        
        ConexionBaseDatos.desconectarBaseDatos();
              
              
        }
        
    
        
    }
    
    
    
    
    
    //Este metodo es utilizado para registrar un articulo
    public static int resgitrarArticulo(int idTipoInventario,String descripcionArticulo,double existencia,int costoUnitario,String estado)throws SQLException{
   
       int value=0;
        
        String query="INSERT Articulos (idTipoInventario,DescripcionArticulo,Existencia,CostoUnitario,Estado) " +
                        " VALUES (?,?,?,?,?)";
      
          try{  
              
             ConexionBaseDatos.conectarBaseDatos();
        
                if(ConexionBaseDatos.getConnection()==null)
                    throw new SQLException("No se pudo conectar ala Base de Datos");
              
           
           PreparedStatement preparedStatement=ConexionBaseDatos.getConnection().prepareCall(query);
           
           preparedStatement.setInt(1,idTipoInventario);
           preparedStatement.setString(2,descripcionArticulo);
           preparedStatement.setDouble(3, existencia);
           preparedStatement.setInt(4,costoUnitario);
           preparedStatement.setString(5,estado);
           
           
           value= preparedStatement.executeUpdate();
    
           return value;
        }finally{
        
        ConexionBaseDatos.desconectarBaseDatos();
              
              
        }
        
    
        
    }
    
    
     //Este metodo es utilizado para registrar una transaccion
    public static int resgitrarTransaccion(int idTipoTransacciones,int idArticulos,int cantidad,double monto)throws SQLException{
   
       int value=0;
        
        String query="INSERT INTO Transacciones (idTipoTransacciones,idArticulos,Fecha,Cantidad,Monto) " +
                        " VALUES(?,?,CURDATE(),?,?)";
      
          try{  
              
             ConexionBaseDatos.conectarBaseDatos();
        
                if(ConexionBaseDatos.getConnection()==null)
                    throw new SQLException("No se pudo conectar ala Base de Datos");
              
           
           PreparedStatement preparedStatement=ConexionBaseDatos.getConnection().prepareCall(query);
           
            preparedStatement.setInt(1,idTipoTransacciones);
           preparedStatement.setInt(2,idArticulos);
           preparedStatement.setInt(3,cantidad);
           preparedStatement.setDouble(4, monto);
           
           
         value= preparedStatement.executeUpdate();
    
           return value;
        }finally{
        
        ConexionBaseDatos.desconectarBaseDatos();
              
              
        }
        
    
        
    }
    
    
     //Este metodo es utilizado para registrar existencia por alamacen
    public static int resgitrarExistenciaXAlmacen(int idAlmacenes,int idArticulos,int cantidad)throws SQLException{
   
       int value=0;
        
        String query="INSERT INTO ExistenciaXAlmacen (idAlmacenes,idArticulos,Cantidad) " +
                        " VALUES (?,?,?)";
      
          try{  
              
             ConexionBaseDatos.conectarBaseDatos();
        
                if(ConexionBaseDatos.getConnection()==null)
                    throw new SQLException("No se pudo conectar ala Base de Datos");
              
           
           PreparedStatement preparedStatement=ConexionBaseDatos.getConnection().prepareCall(query);
           
           preparedStatement.setInt(1, idAlmacenes);
           preparedStatement.setInt(2, idArticulos);
           preparedStatement.setInt(3,cantidad);
         
           
             value=preparedStatement.executeUpdate();
    
           return value;
        }finally{
        
        ConexionBaseDatos.desconectarBaseDatos();
              
              
        }
        
    
        
    }
    
    
    //Este metodo es utilizado para actualizar un articulo
    public static int resgitrarEntradaArticulo(int idArticulos,double existencia,int costoUnitario)throws SQLException{
   
       int value=0;
        
        String query="UPDATE Articulos SET Existencia=?, CostoUnitario=? WHERE idArticulos=?";
      
          try{  
              
             ConexionBaseDatos.conectarBaseDatos();
        
                if(ConexionBaseDatos.getConnection()==null)
                    throw new SQLException("No se pudo conectar ala Base de Datos");
              
           
           PreparedStatement preparedStatement=ConexionBaseDatos.getConnection().prepareCall(query);
           
           
           preparedStatement.setDouble(1, existencia);
           preparedStatement.setInt(2,costoUnitario);
           preparedStatement.setInt(3,idArticulos);
           
           
           value= preparedStatement.executeUpdate();
    
           return value;
        }finally{
        
        ConexionBaseDatos.desconectarBaseDatos();
              
              
        }
        
    
        
    }
    
    
    //Este metodo es utilizado para actualizar existencia por alamacen
    public static int actualizarExistenciaXAlmacen(int idAlmacenes,int idArticulos,int cantidad)throws SQLException{
   
       int value=0;
        
        String query="UPDATE ExistenciaXAlmacen SET Cantidad=? WHERE idAlmacenes=? AND idArticulos=?";
      
          try{  
              
             ConexionBaseDatos.conectarBaseDatos();
        
                if(ConexionBaseDatos.getConnection()==null)
                    throw new SQLException("No se pudo conectar ala Base de Datos");
              
           
           PreparedStatement preparedStatement=ConexionBaseDatos.getConnection().prepareCall(query);
           
           preparedStatement.setInt(1,cantidad);
           preparedStatement.setInt(2, idAlmacenes);
           preparedStatement.setInt(3, idArticulos);
           
         
           
             value=preparedStatement.executeUpdate();
    
           return value;
        }finally{
        
        ConexionBaseDatos.desconectarBaseDatos();
              
              
        }
        
    
        
    }
    
     //Este metodo es utilizado para actualizar la cantidad de un articulo
    public static int actualizarCantidadArticulo(double existencia,int idArticulos)throws SQLException{
   
       int value=0;
        
        String query="UPDATE Articulos SET Existencia=? WHERE idArticulos=?";
      
          try{  
              
             ConexionBaseDatos.conectarBaseDatos();
        
                if(ConexionBaseDatos.getConnection()==null)
                    throw new SQLException("No se pudo conectar ala Base de Datos");
              
           
           PreparedStatement preparedStatement=ConexionBaseDatos.getConnection().prepareCall(query);
           
           preparedStatement.setDouble(1,existencia);
           preparedStatement.setInt(2, idArticulos);
           
         
           
             value=preparedStatement.executeUpdate();
    
           return value;
        }finally{
        
        ConexionBaseDatos.desconectarBaseDatos();
              
              
        }
        
    
        
    }
    
    
    
     //Este metodo es utilizado para actualizar la cantidad de existencia por alamacen
    public static int actualizarCantidadExistenciaXAlmacen (int cantidad,int idArticulos,int idAlmacenes)throws SQLException{
   
       int value=0;
        
        String query="UPDATE ExistenciaXAlmacen SET Cantidad=? WHERE idArticulos=? AND idAlmacenes=?";
      
          try{  
              
             ConexionBaseDatos.conectarBaseDatos();
        
                if(ConexionBaseDatos.getConnection()==null)
                    throw new SQLException("No se pudo conectar ala Base de Datos");
              
           
           PreparedStatement preparedStatement=ConexionBaseDatos.getConnection().prepareCall(query);
           
           preparedStatement.setInt(1,cantidad);
           preparedStatement.setInt(2, idArticulos);
           preparedStatement.setInt(3, idAlmacenes);
         
           
             value=preparedStatement.executeUpdate();
    
           return value;
        }finally{
        
        ConexionBaseDatos.desconectarBaseDatos();
              
              
        }
        
    
        
    }
    
    
    //Este metodo es utilizado para actualizar el estado de un articulo
    public static int actualizarEstadoArticulos (int idArticulos,String descripcionEstado)throws SQLException{
   
       int value=0;
        
        String query="UPDATE Articulos SET Estado=? WHERE idArticulos=?";
      
          try{  
              
             ConexionBaseDatos.conectarBaseDatos();
        
                if(ConexionBaseDatos.getConnection()==null)
                    throw new SQLException("No se pudo conectar ala Base de Datos");
              
           
           PreparedStatement preparedStatement=ConexionBaseDatos.getConnection().prepareCall(query);
           
         preparedStatement.setString(1,descripcionEstado);
           preparedStatement.setInt(2, idArticulos);
          
         
           
             value=preparedStatement.executeUpdate();
            
    
           return value;
        }finally{
        
        ConexionBaseDatos.desconectarBaseDatos();
              
              
        }
        
    
        
    }
    
}
