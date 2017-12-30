/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Mantenimientos.IC;

import ModuleWorker.DBCON;
import NCLPM.LOG;
import NMOC.MD_Mantenimientos.Core.NOB_Implementos;
import java.sql.CallableStatement;
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
public class MICROCON_MantenerImplementos
{
    private ResultSet rs;
    private PreparedStatement st;
    LOG lc = new LOG();
    
    //INICIALIZADOR (CONSTRUCTOR)
    public MICROCON_MantenerImplementos()
    {
        L_IMPLEMENTOS = new ArrayList();
        llenarIDS_PRO();
    }
    
    //CARGAR PRODUCTO
    public void CargarIMPL(DefaultTableModel modelo, JTable Jta)         
    {
        try{
            DBCON RCN = new DBCON();
            
            Statement smt=RCN.conector().createStatement();
            ResultSet rs= smt.executeQuery("SELECT * from V_implementos");
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
                lc.write("Problema al Cargar Datos en el metodo 'CargarPRO'", "MICROCON_MantenerImplementoss linea 36", sqlex);
            }
        try {
        } catch (Exception ex)
            {
                lc.write("Error no controlado en el metodo 'CargarPRO'", "MICROCON_MantenerImplementoss linea 98", ex);
            }
    }
    
    //INSERTAR PERSONAL
    public void InsertarImplementos(String cod, String nom,String estado)
    {
      try{
            DBCON RCN = new DBCON();

            // Llamada al procedimiento almacenado
            CallableStatement cst = RCN.conector().prepareCall("{call usp_insertar_maquina(?,?,?)}");
            // Parametro del procedimiento almacenado
            cst.setString(1, cod);
            cst.setString(2, nom);
            cst.setString(3, estado);
            // Ejecuta el procedimiento almacenado
            cst.execute();
            cst.close();

         }catch (SQLException ex) 
            {
                 lc.write("Problema al intentar insertar un Implementos en el metodo 'InsertarImplementos'", "MICROCON_MantenerImplementos linea 42", ex);             
            } 
    }
    
    //INSERTAR PERSONAL
    public void ModificarImplementos(String cod, String nom,String estado)
    {
      try{
            DBCON RCN = new DBCON();

            // Llamada al procedimiento almacenado
            CallableStatement cst = RCN.conector().prepareCall("{call usp_modificar_maquina(?,?,?)}");
            // Parametro del procedimiento almacenado
            cst.setString(1, cod);
            cst.setString(2, nom);
            cst.setString(3, estado);
            // Ejecuta el procedimiento almacenado
            cst.execute();
            cst.close();

         }catch (SQLException ex) 
            {
                 lc.write("Problema al intentar insertar un Implementos en el metodo 'InsertarImplementos'", "MICROCON_MantenerImplementos linea 42", ex);             
            } 
    }
  
    
    
    //##############################################################################
    //ESPACIO ARRAYLIST Y OTROS METODOS QUE INTERACTUAN CON LA BASE DE DATOS

    //ArrayList controlador
    private ArrayList<NOB_Implementos> L_IMPLEMENTOS;
    
    //GET
    public ArrayList<NOB_Implementos> getL_IMPLEMENTOS() 
    {
        return L_IMPLEMENTOS;
    }
    
    //SET
    public void setL_IMPLEMENTOS(ArrayList<NOB_Implementos> L_IMPLEMENTOS) 
    {
        this.L_IMPLEMENTOS = L_IMPLEMENTOS;
    }
    
    //ADD OBJECT
    public void A_Objeto(NOB_Implementos p)
    {
        L_IMPLEMENTOS.add(p);
    }
    
    //RETURN SIZE
    public int tamaño()
    {
        return L_IMPLEMENTOS.size();
    }
            
    //Recibe pos y retorna obj
    public NOB_Implementos obtener(int pos)    
    {
        return L_IMPLEMENTOS.get(pos);
    }
    
    //LLENA ARRAYLIST
    public final void llenarIDS_PRO()
    {
        try
        {   
            DBCON RCN = new DBCON();
            L_IMPLEMENTOS.clear();
            st=RCN.conector().prepareStatement("SELECT ID_MAQU FROM MAQUINA order by ID_MAQU");
            rs=st.executeQuery();

            while (rs.next())
            {            
                String ID_MAQU = rs.getString("ID_MAQU");
                NOB_Implementos nu = new NOB_Implementos(ID_MAQU);
                A_Objeto(nu);
            }
            RCN.conector().close();

        }
        catch (SQLException sqle) 
            {
               lc.write( "La base de datos no retorno conexion!","MICROCON_MantenerImplementoss", sqle);
            }
        catch (Exception e)
            {
               lc.write( "ha ocurrido algun error no controlado","MICROCON_MantenerImplementoss", e);
            }
        
        }  
            
            
}
