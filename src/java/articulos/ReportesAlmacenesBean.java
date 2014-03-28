/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package articulos;

import dao.DaoAlmacenes;
import dao.DaoArticulos;
import dao.DaoReportes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import model.Almacenes;
import model.Articulos;

/**
 *
 * @author Salim
 */
@ManagedBean(name = "reportesAlmacenesBean")
@RequestScoped
public class ReportesAlmacenesBean {

    private int indexSelected;
    private String descripcion;

    public ReportesAlmacenesBean() {

        indexSelected = 0;
        descripcion = "";
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

        return "reportesAlmacenes.xhtml";

    }

    public ResultSet obtenerReportesAlmacenes() throws SQLException {

        if (getDescripcion().equals("") == false) {

            ResultSet resultSet = DaoReportes.obtenerReportesAlmacenes(getIndexSelected(), getDescripcion());

            return resultSet;

        } else {

            ResultSet resultSet = DaoReportes.obtenerReportesAlmacenes(getIndexSelected());

            return resultSet;

        }

    }

    public void validateExistenciaAlmacen(FacesContext context, UIComponent toValidate, Object value) throws SQLException {

        String message = "";
        int cont = 0;
        List<Almacenes> listaAlmacenes = DaoAlmacenes.obtenerListaAlmacenes();

        String nombreArticulo = (String) value;

        if (nombreArticulo.equals("") == false) {

            for (Almacenes almacenesLoop : listaAlmacenes) {

                if (nombreArticulo.equalsIgnoreCase(almacenesLoop.getDescripcionAlmacen())) {

                    cont++;
                }

            }

            if (cont == 0) {

                ((UIInput) toValidate).setValid(false);
                message = "No existen ningun almacen con esa descripcion.";
                context.addMessage(toValidate.getClientId(context),
                        new FacesMessage(message));
               
            }

        }
    }
}
