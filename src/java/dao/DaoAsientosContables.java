/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.ConexionBaseDatos;

public class DaoAsientosContables {

    //Este metodo es utilizado para generar asientos contables
    public static int resgitrarAsientoContable(int idTipoInventario, String descripcionAsientoContable, String cuenta, String tipoMovimiento, double monto) throws SQLException {

        int value = 0;

        String query = "INSERT INTO AsientosContables (idTipoInventario,DescripcionAsientoContable,Cuenta,Tipo_Movimiento,Fecha_Asiento,Monto,Estado) "
                + "  VALUES ( ?,?,?,?,CURDATE(),?,'D');";

        try {

            ConexionBaseDatos.conectarBaseDatos();

            if (ConexionBaseDatos.getConnection() == null) {
                throw new SQLException("No se pudo conectar ala Base de Datos");
            }

            PreparedStatement preparedStatement = ConexionBaseDatos.getConnection().prepareCall(query);

            preparedStatement.setInt(1, idTipoInventario);
            preparedStatement.setString(2, descripcionAsientoContable);
            preparedStatement.setString(3, cuenta);
            preparedStatement.setString(4, tipoMovimiento);
            preparedStatement.setDouble(5, monto);

            value = preparedStatement.executeUpdate();

            return value;
        } finally {

            ConexionBaseDatos.desconectarBaseDatos();

        }

    }

}
