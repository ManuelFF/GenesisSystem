/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker.IC;

import ModuleWorker.Core.NOB_usuario;
import ModuleWorker.DBCON;
import NCLPM.LOG;
import java.sql.CallableStatement;
import java.sql.DriverManager;
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
public class MICROCON_MantenerUsuario 
{
    private ResultSet rs;
    private PreparedStatement st;
    LOG lc = new LOG(); //puente apuntando a la clase LOG
    
    public MICROCON_MantenerUsuario()
    {
          //inicializacion
         L_USRID = new ArrayList<>();
         llenarIDS_USR();
    }
    
    
    //INSERTAR USUARIO
    public void InsertarUsuario(String idUser,String idPer, String user,String pass, String tip, String est)
    {
        try {
                 DBCON RCN = new DBCON();

                // Llamada al procedimiento almacenado
                CallableStatement cst = RCN.conector().prepareCall("{call usp_insertar_usuario(?,?,?,?,?,?)}");
                // Parametro del procedimiento almacenado
                cst.setString(1, idUser);
                cst.setString(2, idPer);
                cst.setString(3, user);
                cst.setString(4, pass);
                cst.setString(5, tip);
                cst.setString(6, est);
                // Ejecuta el procedimiento almacenado
                cst.execute();
                cst.close();

            } catch (SQLException ex) 
                {
                     lc.write("Problema al intentar insertar un usuario en el metodo 'InsertarUsuario'", "MICROCON_MantenerUsuario linea 42", ex.getMessage());             
                } 
    }

    
    //MODIFICAR USUARIO 
    
    //CARGAR USUARIO
    public void Cargarusuario(DefaultTableModel modelo, JTable Jta)         
    {
        try{
            DBCON RCN = new DBCON();
            
            Statement smt=RCN.conector().createStatement();
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
            }Jta.setModel(modelo);
        }catch(SQLException sqlex)
            {
                lc.write("Problema al Cargar Datos en el metodo 'CargarUsuario'", "MICROCON_MantenerUsuario linea 72", sqlex.getMessage());
            }
        try {
        } catch (Exception ex)
            {
                lc.write("Error no controlado en el metodo 'cargarUsuario'", "MICROCON_MantenerUsuario linea 98", ex.getMessage());
            }
    }
    
    public void CargarSeleccionPersonal(DefaultTableModel modelo, JTable Jta)         
    {
        try{
            DBCON RCN = new DBCON();
            
            Statement smt=RCN.conector().createStatement();
            ResultSet rs= smt.executeQuery("SELECT * from V_SeleccionPersonal");
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
                lc.write("Problema al Cargar Datos en el metodo 'CargarSeleccionPersonal'", "MICROCON_MantenerUsuario", sqlex.getMessage());
            }
        try {
        } catch (Exception ex)
            {
                lc.write("Error no controlado en el metodo 'CargarSeleccionPersonal'", "MICROCON_MantenerUsuario", ex.getMessage());
            }
    }
    
    public void CargarBoxTipo(JComboBox<String> Cbcats)
    {
        try
        {   
            DBCON RCN = new DBCON();
            
            st=RCN.conector().prepareStatement("SELECT DESC_TIPO FROM tipo order by id_tipo");
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
    
    public String Buscar_ID_Tipo(String DESC)
    {
        try
        {   
            DBCON RCN = new DBCON();
            
            st=RCN.conector().prepareStatement("SELECT ID_TIPO FROM tipo where DESC_TIPO = '"+DESC+"'");
            rs=st.executeQuery();

            while (rs.next())
            {            
                String DESC_TIPO = rs.getString("ID_TIPO");
                return DESC_TIPO;
            }

        }
        catch (SQLException sqlex) 
            {
                lc.write("Problema al buscar Datos en el metodo 'Buscar_ID_Tipo'", "MICROCON_MantenerUsuario", sqlex.getMessage());
            }
        catch (Exception ex)
        {
                lc.write("Error no controlado en el metodo 'Buscar_ID_Tipo'", "MICROCON_MantenerUsuario", ex.getMessage());
        }
        return null;
    }

    //Array List Controlador
    private ArrayList<NOB_usuario> L_USRID;
    
    //GET ARRAYLISt
    public ArrayList<NOB_usuario> getL_USRID() 
    {
        return L_USRID;
    }

    //SET ARRAYLIST
    public void setL_USRID(ArrayList<NOB_usuario> L_USRID) 
    {
        this.L_USRID = L_USRID;
    }
    
     public void A_objeto(NOB_usuario p)
    {
        L_USRID.add(p);
    }

    //retorna tamaño array
    public int tamaño()
    {
        return L_USRID.size();
    }

    //recibe pos y retorna obj
    public NOB_usuario obtener(int pos)
    {
        return L_USRID.get(pos);
    }
    
    protected final void llenarIDS_USR()
    {
        try
        {   
            DBCON RCN = new DBCON();
            
            st=RCN.conector().prepareStatement("SELECT ID_USER FROM usuario order by ID_USER");
            rs=st.executeQuery();

            while (rs.next())
            {            
                String ID_USER = rs.getString("ID_USER");
                NOB_usuario nu = new NOB_usuario(ID_USER);
                A_objeto(nu);
            }
            RCN.conector().close();

        }
        catch (SQLException sqle) 
            {
               lc.write( "La base de datos no retorno conexion!","MICROCON_MantenerUsuario", sqle.getMessage());
            }
        catch (Exception e)
            {
               lc.write( "ha ocurrido algun error no controlado","MICROCON_MantenerUsuario", e.getMessage());
            }
    }
   
    
}
