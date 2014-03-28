/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionBaseDatos {
    
    private static final String  URL="jdbc:mysql://localhost:3307/inventario";
    private static final String USUARIO="root";
    private static final String CONTRASENA="123123";
    private static Connection connection;

   

    
    
    //Este metedo  permite conectarse ala base de datos
    public static void conectarBaseDatos()throws SQLException{   
    
        connection=DriverManager.getConnection(URL,USUARIO ,CONTRASENA);
    
    
    }
    
     //Este metedo  permite desconectarse ala base de datos
    public static void desconectarBaseDatos()throws SQLException{   
    
        connection.close();
    
    
    }
    
    public static Connection getConnection() {
        return connection;
    }
    
    
    
    
}
