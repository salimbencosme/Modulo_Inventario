/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package articulos;

import dao.DaoArticulos;
import dao.DaoReportes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.sql.rowset.CachedRowSet;
import model.Articulos;

/**
 *
 * @author Salim
 */
@ManagedBean(name = "reportesBean")
@RequestScoped
public class ReportesBean {

    private int indexSelected;
    private String descripcion;

    public ReportesBean() {

        indexSelected = 0;
        descripcion="";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIndexSelected() {
        return indexSelected;
    }

    public void setIndexSelected(int indexSelected) {
        this.indexSelected = indexSelected;
    }

    public String buscar() throws SQLException {

        return "reportes.xhtml";

    }

    public ResultSet obtenerReportesArticulos() throws SQLException {

        
        if (getDescripcion().equals("") == false) {

            ResultSet resultSet = DaoReportes.obtenerReportesArticulos(getIndexSelected(), getDescripcion());
            return resultSet;

        } else {

            ResultSet resultSet = DaoReportes.obtenerReportesArticulos(getIndexSelected());
            return resultSet;
        }

    }

    
    
    public void validateExistenciaArticulo(FacesContext context, UIComponent toValidate, Object value) throws SQLException {

        String message = "";
        int cont = 0;
        List<Articulos> listaArticulos = DaoArticulos.obtenerListaArticulos();

        String nombreArticulo = (String) value;

        if (nombreArticulo.equals("") == false) {

            for (Articulos articuloLoop : listaArticulos) {

                if (nombreArticulo.equalsIgnoreCase(articuloLoop.getDescripcionArticulo())) {

                    cont++;
                }

            }

            if (cont == 0) {

                ((UIInput) toValidate).setValid(false);
                message = "No existen ningun articulo con esa descripcion.";
                context.addMessage(toValidate.getClientId(context),
                        new FacesMessage(message));
            }

        }
    }

}
