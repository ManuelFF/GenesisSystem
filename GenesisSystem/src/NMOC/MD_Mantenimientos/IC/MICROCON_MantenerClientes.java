/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Mantenimientos.IC;

import ModuleWorker.DBCON;
import NCLPM.LOG;
import NMOC.MD_Mantenimientos.Core.NOB_cliente;
import java.sql.PreparedStatement;
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
public class MICROCON_MantenerClientes 
{
    private ResultSet rs;
    private PreparedStatement st;
    LOG lc = new LOG();
    
    //Constructor
    public MICROCON_MantenerClientes()
    {
        //inicializadores
        L_CLIENTE = new ArrayList<>();
        llenarIDS_CLI();     
    }
    
    //CARGAR CLIENTE NATURAL
    public void CargarCliNatu(DefaultTableModel modelo, JTable Jta)         
    {
        try{
            DBCON RCN = new DBCON();
            
            Statement smt=RCN.conector().createStatement();
            ResultSet rs= smt.executeQuery("SELECT * from V_cli_natu");
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
                lc.write("Problema al Cargar Datos en el metodo 'CargarCliNatu'", "MICROCON_MantenerClientes linea 36", sqlex);
            }
        try {
        } catch (Exception ex)
            {
                lc.write("Error no controlado en el metodo 'CargarCliNatu'", "MICROCON_MantenerClientes linea 98", ex);
            }
    }
    
  //###################################### ESPACIO ARRAYLIST Y OTROS METODOS QUE INTERACTUAN DIRECTAMENTE CON LA BASE ###############################################
    
    //ArrayList controlador
    private ArrayList<NOB_cliente> L_CLIENTE;
    
    //GET ARRAYLIST
    public ArrayList<NOB_cliente> getL_CLIENTE() 
    {
        return L_CLIENTE;
    }
    
    //SET ARRAYLIST
    public void setL_CLIENTE(ArrayList<NOB_cliente> L_CLIENTE) 
    {
        this.L_CLIENTE = L_CLIENTE;
    }
    
    //ADD OBJECT
    public void A_Objeto(NOB_cliente p)
    {
        L_CLIENTE.add(p);
    }
    
    //RETURN SIZE
    public int tamaño()
    {
        return L_CLIENTE.size();
    }
    
    //recibe pos y retorna obj 
    public NOB_cliente obtener(int pos)
    {
        return L_CLIENTE.get(pos);
    }
    
    public final void llenarIDS_CLI()
    {
        try
        {   
            DBCON RCN = new DBCON();
            
            st=RCN.conector().prepareStatement("SELECT ID_CLI FROM CLIENTE order by ID_CLI");
            rs=st.executeQuery();

            while (rs.next())
            {            
                String ID_CLI = rs.getString("ID_CLI");
                NOB_cliente nu = new NOB_cliente(ID_CLI);
                A_Objeto(nu);
            }
            RCN.conector().close();

        }
        catch (SQLException sqle) 
            {
               lc.write( "La base de datos no retorno conexion!","MICROCON_MantenerClientes", sqle);
            }
        catch (Exception e)
            {
               lc.write( "ha ocurrido algun error no controlado","MICROCON_MantenerClientes", e);
            }
    }
    
    
    
    
    
    
    
}
