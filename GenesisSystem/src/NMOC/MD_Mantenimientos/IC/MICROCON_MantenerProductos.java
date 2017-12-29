/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Mantenimientos.IC;

import ModuleWorker.DBCON;
import NCLPM.LOG;
import NMOC.MD_Mantenimientos.Core.NOB_producto;
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
public class MICROCON_MantenerProductos
{
    private ResultSet rs;
    private PreparedStatement st;
    LOG lc = new LOG();
    
    //INICIALIZADOR (CONSTRUCTOR)
    public MICROCON_MantenerProductos()
    {
        L_PRODUCTO = new ArrayList();
        llenarIDS_PRO();
    }
    
    //CARGAR PRODUCTO
    public void CargarPRO(DefaultTableModel modelo, JTable Jta)         
    {
        try{
            DBCON RCN = new DBCON();
            
            Statement smt=RCN.conector().createStatement();
            ResultSet rs= smt.executeQuery("SELECT * from V_PRODUCTOS");
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
                lc.write("Problema al Cargar Datos en el metodo 'CargarPRO'", "MICROCON_MantenerProductos linea 36", sqlex);
            }
        try {
        } catch (Exception ex)
            {
                lc.write("Error no controlado en el metodo 'CargarPRO'", "MICROCON_MantenerProductos linea 98", ex);
            }
    }
  
    
    
    //##############################################################################
    //ESPACIO ARRAYLIST Y OTROS METODOS QUE INTERACTUAN CON LA BASE DE DATOS

    //ArrayList controlador
    private ArrayList<NOB_producto> L_PRODUCTO;
    
    //GET
    public ArrayList<NOB_producto> getL_PRODUCTO() 
    {
        return L_PRODUCTO;
    }
    
    //SET
    public void setL_PRODUCTO(ArrayList<NOB_producto> L_PRODUCTO) 
    {
        this.L_PRODUCTO = L_PRODUCTO;
    }
    
    //ADD OBJECT
    public void A_Objeto(NOB_producto p)
    {
        L_PRODUCTO.add(p);
    }
    
    //RETURN SIZE
    public int tamaño()
    {
        return L_PRODUCTO.size();
    }
            
    //Recibe pos y retorna obj
    public NOB_producto obtener(int pos)    
    {
        return L_PRODUCTO.get(pos);
    }
    
    //LLENA ARRAYLIST
    public final void llenarIDS_PRO()
    {
        try
        {   
            DBCON RCN = new DBCON();
            L_PRODUCTO.clear();
            st=RCN.conector().prepareStatement("SELECT ID_PRO FROM PRODUCTO order by ID_PRO");
            rs=st.executeQuery();

            while (rs.next())
            {            
                String ID_PRO = rs.getString("ID_PRO");
                NOB_producto nu = new NOB_producto(ID_PRO);
                A_Objeto(nu);
            }
            RCN.conector().close();

        }
        catch (SQLException sqle) 
            {
               lc.write( "La base de datos no retorno conexion!","MICROCON_MantenerProductos", sqle);
            }
        catch (Exception e)
            {
               lc.write( "ha ocurrido algun error no controlado","MICROCON_MantenerProductos", e);
            }
        
        }  
        
    
            
            
}
