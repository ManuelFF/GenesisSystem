/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NISPM;

import ModuleWorker.DBCON;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class SGL_CTRL_Operarios
{
    private ArrayList<SGL_CL_Operarios> sgl_operario;
    public static SGL_CTRL_Operarios controller; //idea basica del patron singlaton
    
    private SGL_CTRL_Operarios()
    {
        sgl_operario = new ArrayList<SGL_CL_Operarios>();
    }
    
    public static SGL_CTRL_Operarios getInstance()
    {
        if(controller == null)
            controller = new SGL_CTRL_Operarios();
        return controller;
    }
    
    public ArrayList<SGL_CL_Operarios> getsgl_operario()
    {
        return sgl_operario;
    }
    
    public void setsgl_operario(ArrayList<SGL_CL_Operarios> sgl_operario) 
    {
        this.sgl_operario = sgl_operario;
    }
       
    
    public static void CargarOperarios(DefaultTableModel modelo, JTable Jta)         
    {
        try{
            DBCON RCN = new DBCON();
            
            Statement smt=RCN.conector().createStatement();
            ResultSet rs= smt.executeQuery("select * from V_Operarios");
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
                //lc.write("Problema al Cargar Datos en el metodo 'CargarPRO'", "MICROCON_MantenerProductos linea 36", sqlex);
            }
        try {
        } catch (Exception ex)
            {
                //lc.write("Error no controlado en el metodo 'CargarPRO'", "MICROCON_MantenerProductos linea 98", ex);
            }
    }
    
}
