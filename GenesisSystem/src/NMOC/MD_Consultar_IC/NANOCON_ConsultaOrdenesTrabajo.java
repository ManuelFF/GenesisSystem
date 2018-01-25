/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Consultar_IC;

import ModuleWorker.DBCON;
import NCLPM.LOG;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class NANOCON_ConsultaOrdenesTrabajo 
{
    LOG lc = new LOG();
    
    
    public void ConsultarOrden(DefaultTableModel modelo, JTable Jta,String ID_CLI)         
    {
        try{
            DBCON RCN = new DBCON();

            Statement smt=RCN.conector().createStatement();
            ResultSet rs= smt.executeQuery
                                (
                                  "select os.ID_ORDEN CÓDIGO,os.NUMERO_ORDEN,os.FECHA,os.HORA,emp.NOM_PER||' '||emp.APE_PER VENDEDOR,os.COSTO \n" +
                                  "from orden_serv os\n" +
                                  "inner join personal_emp emp on os.ID_PER = emp.ID_PER where os.ID_CLI = "+"'"+ID_CLI+"' order by NUMERO_ORDEN"
                                );
            
            ResultSetMetaData md=rs.getMetaData();
            int columnas= md.getColumnCount();
            for (int i = 1; i <= columnas; i++)
             {
               modelo.addColumn(md.getColumnLabel(i));
             }
            while(rs.next())
            {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) {fila[i]=rs.getObject(i+1);}
                modelo.addRow(fila);
            }Jta.setModel(modelo);
        }catch(SQLException sqlex)
            {
                lc.write("Problema al Cargar Datos en el metodo 'ConsultarOrden'", "MICROCON_ConsultaOrdenesTrabajo", sqlex);
            }
        try {
        } catch (Exception ex)
            {
                lc.write("Error no controlado en el metodo 'ConsultarOrden'", "MICROCON_ConsultaOrdenesTrabajo", ex);
            }
    }
    
    
    
}
