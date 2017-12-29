/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Mantenimientos.IC;

import ModuleWorker.DBCON;
import NCLPM.LOG;
import NMOC.MD_Mantenimientos.Core.NOB_personal;
import java.awt.Color;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
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
    
    //INSERTAR PERSONAL
    public void InsertarPersonal(String codPER, String nom,String ape,String dni, String dir,String numtelf,String numcel,String corre,String estado)
    {
      try{
             DBCON RCN = new DBCON();

            // Llamada al procedimiento almacenado
            CallableStatement cst = RCN.conector().prepareCall("{call usp_insertar_personal(?,?,?,?,?,?,?,?,?)}");
            // Parametro del procedimiento almacenado
            cst.setString(1, codPER);
            cst.setString(2, nom);
            cst.setString(3, ape);
            cst.setString(4, dni);
            cst.setString(5, dir);
            cst.setString(6, numtelf);
            cst.setString(7, numcel);
            cst.setString(8, corre);
            cst.setString(9, estado);
            // Ejecuta el procedimiento almacenado
            cst.execute();
            cst.close();

         }catch (SQLException ex) 
            {
                 lc.write("Problema al intentar insertar un Personal en el metodo 'InsertarPersonal'", "MICROCON_MantenerPersonal linea 42", ex);             
            } 
    }
    
    //MODIFICAR PERSONAL
    public void ModificarPersonal(String codPER, String nom,String ape,String dni, String dir,String numtelf,String numcel,String corre,String estado)
    {
      try{
             DBCON RCN = new DBCON();

            // Llamada al procedimiento almacenado
            CallableStatement cst = RCN.conector().prepareCall("{call usp_modificar_personal(?,?,?,?,?,?,?,?,?)}");
            // Parametro del procedimiento almacenado
            cst.setString(1, codPER);
            cst.setString(2, nom);
            cst.setString(3, ape);
            cst.setString(4, dni);
            cst.setString(5, dir);
            cst.setString(6, numtelf);
            cst.setString(7, numcel);
            cst.setString(8, corre);
            cst.setString(9, estado);
            // Ejecuta el procedimiento almacenado
            cst.execute();
            cst.close();

         }catch (SQLException ex) 
            {
                 lc.write("Problema al intentar insertar un Personal en el metodo 'InsertarPersonal'", "MICROCON_MantenerPersonal linea 42", ex);             
            } 
    }
    
    //OBTIENE EL COD DE UNA CAT ENVIANDO UN DESC_CAT
    public String ObtenerIDCAT( String DESC_CAT)
    { 
        try {     
              DBCON RCN = new DBCON();
              CallableStatement cst =null;
              cst = RCN.conector().prepareCall("{?=call F_retorno_IDCAT(?)}");
              cst.setString(2, DESC_CAT);
              cst.registerOutParameter(1, Types.VARCHAR);
              cst.execute();
              String resultado = cst.getString(1);
              return resultado;
            }
              catch (SQLException ex) 
               {
                    lc.write("Error en el metodo ObtenerIDCAT", "MICROCON_MantenerPersonal", ex);
               } finally 
                {
                  try 
                  {
                      
                  } 
                    catch (Exception ex) 
                    {
                      lc.write("Error en el metodo ObtenerIDCAT", "MICROCON_MantenerPersonal", ex);
                    }
                }    
        return "NULL";
    }
    
    //INSERTA UN DETALLE DE CATEGORIA AL PERSONAL
    public void insertarDETPER(String codPER, String IDCAT)
    {
                    
       try {
            DBCON RCN = new DBCON();
            CallableStatement cst = RCN.conector().prepareCall("{call usp_insertar_det_per(?,?)}");
            cst.setString(1, codPER);
            cst.setString(2, IDCAT);
            cst.execute();
            cst.close();
        } catch (SQLException ex)
            {
               lc.write("Error en el metodo insertarDETPER", "MICROCON_MantenerPersonal", ex);
            } finally 
                {
                }    
    }   
    
    //QUITA UN DETALLE DE CATEGORIA AL PERSONAL
    public void QuitarDETPER(String codPER, String IDCAT)
    {
                    
       try {
            DBCON RCN = new DBCON();
            CallableStatement cst = RCN.conector().prepareCall("{call usp_eliminar_det_per(?,?)}");
            cst.setString(1, codPER);
            cst.setString(2, IDCAT);
            cst.execute();
            cst.close();
        } catch (SQLException ex)
            {
               lc.write("Error en el metodo QuitarDETPER", "MICROCON_MantenerPersonal", ex);
            } finally 
               {
               }    
    }   
    
    //CONSULTA UN DETALLE DE CATEGORIA
    public void ConsultarDetCat(String cod, JTextArea jta)
    {
        try
        {   
            DBCON RCN = new DBCON();
            st=RCN.conector().prepareStatement("SELECT * from V_cat_IDPER where ID_PER = "+"'"+cod+"'");
            rs=st.executeQuery();
            jta.setText("");jta.setForeground(Color.BLUE);
            while (rs.next())
            {            

            String IDPER = rs.getString("ID_PER");
            String ID_CAT = rs.getString("ID_CAT");
            String desc = rs.getString("DESC_CAT");            
            jta.append("ID_PER : "+IDPER+"\n"+"ID_CAT : "+ID_CAT+"\n"+"DESCRIPCIÓN : "+desc+"\n\n");
            }
        }
        catch (SQLException e) 
            {
               lc.write("Error en el metodo ConsultarDetCat", "MICROCON_MantenerPersonal", e);
            }
        catch (Exception ex)
            {
               lc.write("Error en el metodo ConsultarDetCat", "MICROCON_MantenerPersonal", ex);
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
