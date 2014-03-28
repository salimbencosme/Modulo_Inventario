/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package articulos;

import dao.DaoReportes;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


/**
 *
 * @author Salim
 */
@ManagedBean(name="reportesAsientosContablesBean")
@RequestScoped
public class ReportesAsientosContablesBean {

   
    private  int indexSelected;
  
    public ReportesAsientosContablesBean() {
        
        indexSelected=0;
     
    }

    public int getIndexSelected() {
        return indexSelected;
    }

    public void setIndexSelected(int indexSelected) {
        this.indexSelected = indexSelected;
    }
    
   
    public String buscar()throws SQLException{
    
                return "reportesAsientosContables.xhtml"; 
       
        
    }

 

     public ResultSet obtenerReportesAsientosContables()throws SQLException{
   
         ResultSet resultSet= DaoReportes.obtenerReportesAsientosContables( getIndexSelected() );

         return resultSet;
    
    }

    
    
}
