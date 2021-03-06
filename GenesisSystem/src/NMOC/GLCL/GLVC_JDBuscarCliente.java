/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.GLCL;

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
public class GLVC_JDBuscarCliente 
{
    private ResultSet rs;
    private PreparedStatement st;
    LOG lc = new LOG();
    
    public void CargarCliente_J(DefaultTableModel modelo, JTable Jta)         
    {
        try{
            DBCON RCN = new DBCON();

            Statement smt=RCN.conector().createStatement();
            ResultSet rs= smt.executeQuery("SELECT * from V_CLI_JURI");
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
                lc.write("Problema al Cargar Datos en el metodo 'CargarCliente_J'", "GLVC_JDBuscarCliente", sqlex);
            }
        try {
        } catch (Exception ex)
            {
                lc.write("Error no controlado en el metodo 'CargarCliente_J'", "GLVC_JDBuscarCliente", ex);
            }
    }
    
    public void CargarCliente_N(DefaultTableModel modelo, JTable Jta)         
    {
        try{
            DBCON RCN = new DBCON();

            Statement smt=RCN.conector().createStatement();
            ResultSet rs= smt.executeQuery("SELECT * from V_CLI_NATU");
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
                lc.write("Problema al Cargar Datos en el metodo 'CargarCliente_N'", "GLVC_JDBuscarCliente", sqlex);
            }
        try {
        } catch (Exception ex)
            {
                lc.write("Error no controlado en el metodo 'CargarCliente_N'", "GLVC_JDBuscarCliente", ex);
            }
    }
    

    
}
