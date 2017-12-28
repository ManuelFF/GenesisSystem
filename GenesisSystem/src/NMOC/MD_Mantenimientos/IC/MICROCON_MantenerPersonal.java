/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Mantenimientos.IC;

import ModuleWorker.DBCON;
import NCLPM.LOG;
import NMOC.MD_Mantenimientos.Core.NOB_personal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class MICROCON_MantenerPersonal 
{
    private ResultSet rs; 
    private PreparedStatement st;
    LOG lc = new LOG();
    
    //INICIALIZADOR (CONSTRUCTOR)
    public MICROCON_MantenerPersonal()
    {
        L_PERSONAL = new ArrayList();
        llenarIDS_PER();
    }
    
    //CARGAR CLIENTE NATURAL
    public void CargarPER(DefaultTableModel modelo, JTable Jta)         
    {
        try{
            DBCON RCN = new DBCON();
            
            Statement smt=RCN.conector().createStatement();
            ResultSet rs= smt.executeQuery("SELECT * from V_Personal");
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
                lc.write("Problema al Cargar Datos en el metodo 'CargarPER'", "MICROCON_MantenerPersonal linea 36", sqlex);
            }
        try {
        } catch (Exception ex)
            {
                lc.write("Error no controlado en el metodo 'CargarPER'", "MICROCON_MantenerPersonal linea 98", ex);
            }
    }
    
    //CARGAR CATEGORIA DEL PESRONAL
    public void CargarBoxCAT(JComboBox<String> Cbcats)
    {
        try
        {   
            DBCON RCN = new DBCON();
            
            st=RCN.conector().prepareStatement("SELECT DESC_CAT FROM cat order by id_cat");
            rs=st.executeQuery();

            while (rs.next())
            {            

            String DESC_TIPO = rs.getString("DESC_CAT");
            Cbcats.addItem(DESC_TIPO);
            }

        }
        catch (SQLException sqlex) 
            {
                lc.write("Problema al Cargar Datos en el metodo 'CargarBoxTipo'", "MICROCON_MantenerUsuario", sqlex);
            }
        catch (Exception ex)
        {
                lc.write("Error no controlado en el metodo 'cargarBoxTipo'", "MICROCON_MantenerUsuario", ex);
        }
    }
    
    
    //##########################################################################
    //ESPACIO ARRAYLIST Y OTROS METODOS QUE INTERACTUAN CON LA BASE DE DATOS
    
    //ArrayList controlador
    private ArrayList<NOB_personal> L_PERSONAL;
    
    //GET
    public ArrayList<NOB_personal> getL_PERSONAL() 
    {
        return L_PERSONAL;
    }
    
    //SET
    public void setL_PERSONAL(ArrayList<NOB_personal> L_PERSONAL) 
    {
        this.L_PERSONAL = L_PERSONAL;
    }
    
    //ADD OBJECT
    public void A_Objeto(NOB_personal p)
    {
        L_PERSONAL.add(p);
    }
    
    //RETURN SIZE
    public int tamaño()
    {
        return L_PERSONAL.size();
    }
    
    //Recibe pos y retorna obj
    public NOB_personal obtener(int pos)
    {
        return L_PERSONAL.get(pos);
    }
    
    //LLENA ARRAYLIST
    public final void llenarIDS_PER()
    {
       try
        {   
            DBCON RCN = new DBCON();
            L_PERSONAL.clear();
            st=RCN.conector().prepareStatement("SELECT ID_PER FROM PERSONAL order by ID_PER");
            rs=st.executeQuery();

            while (rs.next())
            {            
                String ID_PER = rs.getString("ID_PER");
                NOB_personal nu = new NOB_personal(ID_PER);
                A_Objeto(nu);
            }
            RCN.conector().close();

        }
        catch (SQLException sqle) 
            {
               lc.write( "La base de datos no retorno conexion!","MICROCON_MantenerPersonal", sqle);
            }
        catch (Exception e)
            {
               lc.write( "ha ocurrido algun error no controlado","MICROCON_MantenerPersonal", e);
            }
        
    }
    
}
