/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;
import model.ConexionBaseDatos;

/**
 *
 * @author Salim
 */
public class DaoReportes {

    //Este metodo es utilizado para obtener los reportes de articulos
    public static ResultSet obtenerReportesArticulos(int indexSelected) throws SQLException {

        String query = "";

        try {

            ConexionBaseDatos.conectarBaseDatos();

            if (ConexionBaseDatos.getConnection() == null) {
                throw new SQLException("No se pudo conectar ala Base de Datos");
            }

            switch (indexSelected) {

                case 0:

                    query = "SELECT a.idArticulos,a.descripcionArticulo,i.descripcionInventario ,a.existencia,a.costoUnitario,a.estado FROM Articulos a "
                            + " INNER JOIN tipoinventario i ON a.idTipoInventario=i.idTipoInventario WHERE a.estado='D' ";

                    break;

                case 1:

                    query = "SELECT a.idArticulos,a.descripcionArticulo,i.descripcionInventario ,a.existencia,a.costoUnitario,a.estado FROM Articulos a "
                            + " INNER JOIN tipoinventario i ON a.idTipoInventario=i.idTipoInventario WHERE a.estado='ND' ";

                    break;

                case 2:

                    query = "SELECT a.idArticulos,a.descripcionArticulo,i.descripcionInventario ,a.existencia,a.costoUnitario,a.estado FROM Articulos a "
                            + " INNER JOIN tipoinventario i ON a.idTipoInventario=i.idTipoInventario WHERE a.estado='D' ORDER By i.idTipoInventario";

                    break;

                case 3:

                    query = "SELECT a.idArticulos,a.descripcionArticulo,i.descripcionInventario ,a.existencia,a.costoUnitario,a.estado FROM Articulos a "
                            + " INNER JOIN tipoinventario i ON a.idTipoInventario=i.idTipoInventario WHERE a.estado='D' ORDER By a.Existencia ASC";

                    break;

                case 4:

                    query = "SELECT a.idArticulos,a.descripcionArticulo,i.descripcionInventario ,a.existencia,a.costoUnitario,a.estado FROM Articulos a "
                            + " INNER JOIN tipoinventario i ON a.idTipoInventario=i.idTipoInventario WHERE a.estado='D' ORDER By a.Existencia DESC";

                    break;

                case 5:

                    query = "SELECT a.idArticulos,a.descripcionArticulo,i.descripcionInventario ,a.existencia,a.costoUnitario,a.estado FROM Articulos a "
                            + " INNER JOIN tipoinventario i ON a.idTipoInventario=i.idTipoInventario WHERE a.estado='D' ORDER By a.CostoUnitario ASC";

                    break;

                case 6:

                    query = "SELECT a.idArticulos,a.descripcionArticulo,i.descripcionInventario ,a.existencia,a.costoUnitario,a.estado FROM Articulos a "
                            + " INNER JOIN tipoinventario i ON a.idTipoInventario=i.idTipoInventario WHERE a.estado='D' ORDER By a.CostoUnitario DESC";

                    break;

            }

            PreparedStatement preparedStatement = ConexionBaseDatos.getConnection().prepareCall(query);

            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();

            rowSet.populate(preparedStatement.executeQuery());

            return rowSet;

        } finally {

            ConexionBaseDatos.desconectarBaseDatos();

        }

    }

    //Este metodo es utilizado para obtener los reportes de articulos filtrados por la descripcion 
    public static ResultSet obtenerReportesArticulos(int indexSelected,String descripcion) throws SQLException {

        String query = "";

        try {

            ConexionBaseDatos.conectarBaseDatos();

            if (ConexionBaseDatos.getConnection() == null) {
                throw new SQLException("No se pudo conectar ala Base de Datos");
            }

            switch (indexSelected) {

                case 0:

                    query = "SELECT a.idArticulos,a.descripcionArticulo,i.descripcionInventario ,a.existencia,a.costoUnitario,a.estado FROM Articulos a "
                            + " INNER JOIN tipoinventario i ON a.idTipoInventario=i.idTipoInventario WHERE a.estado='D' AND a.descripcionArticulo=? ";

                    break;

                case 1:

                    query = "SELECT a.idArticulos,a.descripcionArticulo,i.descripcionInventario ,a.existencia,a.costoUnitario,a.estado FROM Articulos a "
                            + " INNER JOIN tipoinventario i ON a.idTipoInventario=i.idTipoInventario WHERE a.estado='ND' AND a.descripcionArticulo=? ";

                    break;

                case 2:

                    query = "SELECT a.idArticulos,a.descripcionArticulo,i.descripcionInventario ,a.existencia,a.costoUnitario,a.estado FROM Articulos a "
                            + " INNER JOIN tipoinventario i ON a.idTipoInventario=i.idTipoInventario WHERE a.estado='D'AND a.descripcionArticulo=? ORDER By i.idTipoInventario";

                    break;

                case 3:

                    query = "SELECT a.idArticulos,a.descripcionArticulo,i.descripcionInventario ,a.existencia,a.costoUnitario,a.estado FROM Articulos a "
                            + " INNER JOIN tipoinventario i ON a.idTipoInventario=i.idTipoInventario WHERE a.estado='D' AND a.descripcionArticulo=? ORDER By a.Existencia ASC";

                    break;

                case 4:

                    query = "SELECT a.idArticulos,a.descripcionArticulo,i.descripcionInventario ,a.existencia,a.costoUnitario,a.estado FROM Articulos a "
                            + " INNER JOIN tipoinventario i ON a.idTipoInventario=i.idTipoInventario WHERE a.estado='D' AND a.descripcionArticulo=? ORDER By a.Existencia DESC";

                    break;

                case 5:

                    query = "SELECT a.idArticulos,a.descripcionArticulo,i.descripcionInventario ,a.existencia,a.costoUnitario,a.estado FROM Articulos a "
                            + " INNER JOIN tipoinventario i ON a.idTipoInventario=i.idTipoInventario WHERE a.estado='D' AND a.descripcionArticulo=? ORDER By a.CostoUnitario ASC";

                    break;

                case 6:

                    query = "SELECT a.idArticulos,a.descripcionArticulo,i.descripcionInventario ,a.existencia,a.costoUnitario,a.estado FROM Articulos a "
                            + " INNER JOIN tipoinventario i ON a.idTipoInventario=i.idTipoInventario WHERE a.estado='D' AND a.descripcionArticulo=? ORDER By a.CostoUnitario DESC";

                    break;

            }

            PreparedStatement preparedStatement = ConexionBaseDatos.getConnection().prepareCall(query);

            preparedStatement.setString(1,descripcion);
            
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();

            rowSet.populate(preparedStatement.executeQuery());

            return rowSet;

        } finally {

            ConexionBaseDatos.desconectarBaseDatos();

        }

    }
    
    
    
    
    //Este metodo es utilizado para obtener los reportes de almacenes
    public static ResultSet obtenerReportesAlmacenes(int indexSelected) throws SQLException {

        String query = "";

        try {

            ConexionBaseDatos.conectarBaseDatos();

            if (ConexionBaseDatos.getConnection() == null) {
                throw new SQLException("No se pudo conectar ala Base de Datos");
            }

            switch (indexSelected) {

                case 0:

                    query = "SELECT DescripcionAlmacen, Estado From Almacenes WHERE Estado='D'";

                    break;

                case 1:

                    query = "SELECT DescripcionAlmacen, Estado From Almacenes WHERE Estado='ND'";

                    break;

            }

            PreparedStatement preparedStatement = ConexionBaseDatos.getConnection().prepareCall(query);

            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();

            rowSet.populate(preparedStatement.executeQuery());

            return rowSet;

        } finally {

            ConexionBaseDatos.desconectarBaseDatos();

        }

    }
    
      //Este metodo es utilizado para obtener los reportes de almacenes filtrado por descripcion
    public static ResultSet obtenerReportesAlmacenes(int indexSelected,String descripcion) throws SQLException {

        String query = "";

        try {

            ConexionBaseDatos.conectarBaseDatos();

            if (ConexionBaseDatos.getConnection() == null) {
                throw new SQLException("No se pudo conectar ala Base de Datos");
            }

            switch (indexSelected) {

                case 0:

                    query = "SELECT DescripcionAlmacen, Estado From Almacenes WHERE Estado='D' AND DescripcionAlmacen=? ";

                    break;

                case 1:

                    query = "SELECT DescripcionAlmacen, Estado From Almacenes WHERE Estado='ND' AND DescripcionAlmacen=?";

                    break;

            }

            PreparedStatement preparedStatement = ConexionBaseDatos.getConnection().prepareCall(query);

            preparedStatement.setString(1, descripcion);
            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();

            rowSet.populate(preparedStatement.executeQuery());

            return rowSet;

        } finally {

            ConexionBaseDatos.desconectarBaseDatos();

        }

    }
    

    //Este metodo es utilizado para obtener los reportes de las transacciones
    public static ResultSet obtenerReportesTransacciones(int indexSelected) throws SQLException {

        String query = "";

        try {

            ConexionBaseDatos.conectarBaseDatos();

            if (ConexionBaseDatos.getConnection() == null) {
                throw new SQLException("No se pudo conectar ala Base de Datos");
            }

            switch (indexSelected) {

                case 0:

                    query = "SELECT t.descripcionTransaccion,a.DescripcionArticulo,i.fecha,"
                            + "i.cantidad,i.monto FROM Transacciones i INNER JOIN TipoTransacciones t ON"
                            + " t.idTipoTransacciones=i.idTipoTransacciones INNER JOIN Articulos a ON a.idArticulos=i.idArticulos;";

                    break;

            }

            PreparedStatement preparedStatement = ConexionBaseDatos.getConnection().prepareCall(query);

            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();

            rowSet.populate(preparedStatement.executeQuery());

            return rowSet;

        } finally {

            ConexionBaseDatos.desconectarBaseDatos();

        }

    }

    //Este metodo es utilizado para obtener los reportes de los asientos contables
    public static ResultSet obtenerReportesAsientosContables(int indexSelected) throws SQLException {

        String query = "";

        try {

            ConexionBaseDatos.conectarBaseDatos();

            if (ConexionBaseDatos.getConnection() == null) {
                throw new SQLException("No se pudo conectar ala Base de Datos");
            }

            switch (indexSelected) {

                case 0:

                    query = "SELECT i.DescripcionInventario,a.DescripcionAsientoContable,a.Cuenta,a.Tipo_Movimiento,"
                            + "a.Fecha_Asiento,a.Monto,a.Estado FROM AsientosContables a INNER JOIN Tipoinventario i ON i.idTipoInventario= a.idTipoInventario;";

                    break;

            }

            PreparedStatement preparedStatement = ConexionBaseDatos.getConnection().prepareCall(query);

            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();

            rowSet.populate(preparedStatement.executeQuery());

            return rowSet;

        } finally {

            ConexionBaseDatos.desconectarBaseDatos();

        }

    }

    //Este metodo es utilizado para obtener los reportes de los asientos contables
    public static ResultSet obtenerReportesInventariosContables(int indexSelected) throws SQLException {

        String query = "";

        try {

            ConexionBaseDatos.conectarBaseDatos();

            if (ConexionBaseDatos.getConnection() == null) {
                throw new SQLException("No se pudo conectar ala Base de Datos");
            }

            switch (indexSelected) {

                case 0:

                    query = "SELECT idTipoInventario,DescripcionInventario,CuentaContable,Estado FROM Tipoinventario;";

                    break;

            }

            PreparedStatement preparedStatement = ConexionBaseDatos.getConnection().prepareCall(query);

            CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();

            rowSet.populate(preparedStatement.executeQuery());

            return rowSet;

        } finally {

            ConexionBaseDatos.desconectarBaseDatos();

        }

    }

}
