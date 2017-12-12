/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker.IC;

import ModuleWorker.DBCON;
import NCLPM.LOG;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class MICROCON_MantenerUsuario 
{
    JFrame form;
    private ResultSet rs;
    private PreparedStatement st;
    Connection cn;
    LOG lc = new LOG(); //puente apuntando a la clase LOG
    
    //INSERTAR USUARIO
    //MODIFICAR USUARIO 
    
    //CARGAR USUARIO
    public void Cargarusuario(DefaultTableModel modelo, JTable JTasistencia)         
    {
        try{
            DBCON db = new DBCON();
            cn=db.CN();
            
            Statement smt=cn.createStatement();
            ResultSet rs= smt.executeQuery("SELECT * from V_usuario");
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
            }JTasistencia.setModel(modelo);
        }catch(SQLException sqlex)
            {
                lc.write("Problema al Cargar Datos en el metodo 'CargarUsuario'", "MICROCON_MantenerUsuario", sqlex.getMessage());
            }
        try {
        } catch (Exception ex)
            {
                lc.write("Error no controlado en el metodo cargar usuario", "MICROCON_MantenerUsuario", ex.getMessage());
            }
    }
    
    public void CargarBoxTipo(JComboBox<String> Cbcats)
    {
        try
        {   
            DBCON db = new DBCON();
            cn=db.CN();
            
            st=cn.prepareStatement("SELECT DESC_TIPO FROM tipo");
            rs=st.executeQuery();

            while (rs.next())
            {            

            String DESC_TIPO = rs.getString("DESC_TIPO");
            Cbcats.addItem(DESC_TIPO);
            }

        }
        catch (SQLException sqlex) 
            {
                lc.write("Problema al Cargar Datos en el metodo 'CargarBoxTipo'", "MICROCON_MantenerUsuario", sqlex.getMessage());
            }
        catch (Exception ex)
        {
                lc.write("Error no controlado en el metodo 'cargarBoxTipo'", "MICROCON_MantenerUsuario", ex.getMessage());
        }
    }

    
}
