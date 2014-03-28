/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package articulos;

import static com.sun.faces.facelets.util.Path.context;
import dao.DaoAlmacenes;
import dao.DaoArticulos;
import dao.DaoAsientosContables;
import dao.DaoExistenciaXAlmacen;
import dao.DaoTipoInventario;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import model.Almacenes;
import model.Articulos;
import model.ExistenciaXAlmacen;
import model.TipoInventario;

/**
 *
 * @author Salim
 */
@ManagedBean(name = "asientosContablesBean")
@RequestScoped
public class AsientosContablesBean implements Serializable {

    private int indexTipoInventario;
    private ArrayList<SelectItem> listaTipoInventarioSelectItem;
    private String cuenta;
    private String tipoMovimiento;
    private String descripcion;

    private List<TipoInventario> listaTipoInventario;

    public AsientosContablesBean() throws SQLException {

        listaTipoInventarioSelectItem = new ArrayList<SelectItem>();

        listaTipoInventarioSelectItem = cargarListaTipoInventarioSelectOneMenu();

        listaTipoInventario = DaoTipoInventario.obtenerListaTipoInventario();

    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIndexTipoInventario() {
        return indexTipoInventario;
    }

    public void setIndexTipoInventario(int indexTipoInventario) {
        this.indexTipoInventario = indexTipoInventario;
    }

    public ArrayList<SelectItem> getListaTipoInventarioSelectItem() {
        return listaTipoInventarioSelectItem;
    }

    public void setListaTipoInventarioSelectItem(ArrayList<SelectItem> listaTipoInventarioSelectItem) {
        this.listaTipoInventarioSelectItem = listaTipoInventarioSelectItem;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public List<TipoInventario> getListaTipoInventario() {
        return listaTipoInventario;
    }

    public void setListaTipoInventario(List<TipoInventario> listaTipoInventario) {
        this.listaTipoInventario = listaTipoInventario;
    }

    private ArrayList<SelectItem> cargarListaTipoInventarioSelectOneMenu() throws SQLException {

        List<TipoInventario> listaTipoInventario = DaoTipoInventario.obtenerListaTipoInventario();
        ArrayList<SelectItem> lista = new ArrayList<>();
        int cont = 2;
        lista.add(new SelectItem(1, ""));
        for (int x = 0; x < listaTipoInventario.size(); x++) {

            lista.add(new SelectItem(cont, listaTipoInventario.get(x).getDescripcionInventario()));
            cont++;
        }

        return lista;
    }

    public void generarAsientoContable() throws SQLException {

        if (this.indexTipoInventario != 1) {

           int  idTipoInventario=listaTipoInventario.get((this.indexTipoInventario > 0 ? (this.indexTipoInventario- 2) : 0)).getIdTipoInventario() ;
            double monto=this.obtenerMonto(idTipoInventario);
            
            DaoAsientosContables.resgitrarAsientoContable(idTipoInventario, this.getDescripcion(), this.getCuenta(),this.getTipoMovimiento(), monto);
            
            
            limpiarFormulario();
            FacesMessage msg = new FacesMessage("Asiento Contable Generado");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else {

            FacesMessage msg = new FacesMessage("Debe de seleccionar un tipo de inventario");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
       
    }

    
    private double obtenerMonto(int idTipoInventario) throws SQLException{
    
        double monto = 0;
        List<Articulos>listaArticulos=DaoArticulos.obtenerListaArticulos(idTipoInventario);
        
        for(Articulos articulos: listaArticulos){
        
            monto += ( articulos.getExistencia() * articulos.getCostoUnitario() );
        
        }
        
        return monto;
    }
    
    private void limpiarFormulario() throws SQLException {

        this.indexTipoInventario = 1;
        this.cuenta = "";
        this.tipoMovimiento = "";
        this.descripcion = "";
        this.listaTipoInventarioSelectItem = cargarListaTipoInventarioSelectOneMenu();
        this.listaTipoInventario = DaoTipoInventario.obtenerListaTipoInventario();

    }

    public void validateSelectedOneMenu(FacesContext context, UIComponent toValidate, Object value) {

        String message = "";

        int indexSelected = (Integer) value;

        if (indexSelected == 0) {

            ((UIInput) toValidate).setValid(false);
            message = "Debe de seleccionar un tipo de inventario";
            context.addMessage(toValidate.getClientId(context),
                    new FacesMessage(message));
        }

    }

    public void valueChangeMethod(ValueChangeEvent e) throws SQLException {
        int index = (Integer) e.getNewValue();

        this.listaTipoInventario = DaoTipoInventario.obtenerListaTipoInventario();

        if (index > 1) {

            this.setCuenta(listaTipoInventario.get((index > 0 ? (index - 2) : 0)).getCuentaContable());

        } else {
            this.setCuenta("");

        }

    }

}
