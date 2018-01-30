/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker.IC;

import ModuleWorker.Core.NOB_Asistencia;
import ModuleWorker.DBCON;
import NCLPM.LOG;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class NANOCON_Asistencia 
{
    //CLASE PRINCIPAL LA CUAL CONTENDRA LAS FUNCIONES BASICAS PRINCIPALES PARA LA MARCACION DE LA ASISTENCIA
    
    private ResultSet rs; 
    private PreparedStatement st;
    LOG lc = new LOG();
    
    //INICIALIZADOR
    public NANOCON_Asistencia()
    {
        L_ASISTENCIA = new ArrayList();
        llenarIDS_ASIST();
    }
    
    
    //Registrar entrada (reemplazo al ex metodo de marcacion de asistencia antiguo)
    public void RegistrarEntrada(String ID_ASIST, String ID_PER, String fech)
    {
      try{
             DBCON RCN = new DBCON();

            // Llamada al procedimiento almacenado
            CallableStatement cst = RCN.conector().prepareCall("{call USP_registrar_entrada(?,?,?)}");
            // Parametro del procedimiento almacenado
            cst.setString(1, ID_ASIST);
            cst.setString(2, ID_PER);
            cst.setString(3, fech);
            // Ejecuta el procedimiento almacenado
            cst.execute();
            cst.close();

         }catch (SQLException ex) 
            {
                 lc.write("Problema al intentar registrar la entrada de algun personal 'RegistrarEntrada'", "NANOCON_Asistencia linea 17", ex);             
            } 
    }
    
    //Updatear entrada (reemplazo al ex modo de insertar una entrada (llenar con datos nuevos)
    public void UpdateEntrada(String ID_PER, String fecha, String retraso)
    {
        try{
             DBCON RCN = new DBCON();

            // Llamada al procedimiento almacenado
            CallableStatement cst = RCN.conector().prepareCall("{call USP_update_entrada(?,?,?)}");
            // Parametro del procedimiento almacenado
            cst.setString(1, ID_PER);
            cst.setString(2, fecha);
            cst.setString(3, retraso);
            // Ejecuta el procedimiento almacenado
            cst.execute();
            cst.close();

         }catch (SQLException ex) 
            {
                 lc.write("Problema al intentar updatear la entrada de algun personal 'UpdateEntrada'", "NANOCON_Asistencia linea 46", ex);             
            } 
    }

    //Registrar Salida (reemplazo al ex metodo de marcacion de salida)
    public void RegistrarSalida(String ID_PER, String fech)
    {
      try{
             DBCON RCN = new DBCON();

            // Llamada al procedimiento almacenado
            CallableStatement cst = RCN.conector().prepareCall("{call USP_registrar_salida(?,?)}");
            // Parametro del procedimiento almacenado
            cst.setString(1, ID_PER);
            cst.setString(2, fech);
            // Ejecuta el procedimiento almacenado
            cst.execute();
            cst.close();

         }catch (SQLException ex) 
            {
                 lc.write("Problema al intentar registrar la salida de algun personal 'RegistrarSalida'", "NANOCON_Asistencia linea 69", ex);             
            } 
    }    
    
    //Updatear entrada (reemplazo al ex modo de insertar una entrada (llenar con datos nuevos)
    public void UpdateSalida(String ID_PER, String fecha, String H_trab, String S_temp)
    {
        try{
             DBCON RCN = new DBCON();

            // Llamada al procedimiento almacenado
            CallableStatement cst = RCN.conector().prepareCall("{call USP_update_salida(?,?,?,?)}");
            // Parametro del procedimiento almacenado
            cst.setString(1, ID_PER);
            cst.setString(2, fecha);
            cst.setString(3, H_trab);
            cst.setString(4, S_temp);
            // Ejecuta el procedimiento almacenado
            cst.execute();
            cst.close();

         }catch (SQLException ex) 
            {
                 lc.write("Problema al intentar updatear la entrada de algun personal 'UpdateEntrada'", "NANOCON_Asistencia linea 46", ex);             
            } 
    }
    
    //OBTENER ESTADO DE LA ASISTENCIA
    public String obtenerEstadoAsistencia(String fecha, String ID_PER)
    {
        try 
        {
            DBCON RCN = new DBCON();
            CallableStatement cst = RCN.conector().prepareCall("{?=call F_Estado_asistencia(?,?)}");
            cst.setString(2, fecha);
            cst.setString(3, ID_PER);
            cst.registerOutParameter(1, Types.VARCHAR);
            cst.execute();
            String resultado = cst.getString(1);
            return resultado;
            
        } catch (SQLException sqle) 
        {
            lc.write( "La base de datos no retorno conexion! -> obtenerEstadoAsistencia","NANOCON_Asistencia", sqle);
        } 
        
        return "0";
    }
    
    //OBTENER ID MANDANDO COMO PARAMETRO NOMBRE CONCATENADO
    public String obtenerIDNomCOn(String nomc)
    {
        try 
        {
            DBCON RCN = new DBCON();
            CallableStatement cst = RCN.conector().prepareCall("{?=call F_IDCON_NOMBRE(?)}");
            cst.setString(2, nomc);
            cst.registerOutParameter(1, Types.VARCHAR);
            cst.execute();
            String resultado = cst.getString(1);
            return resultado;
            
        } catch (Exception e) 
            {
                lc.write("la base de datos no retorno conexion! -> ObtenerIDNomCOn", "NANOCON_Asistencia", e);
            }
        return "0";
    }
    
    //OBTENER HE MANDANDO COMO PARAMETRO FECHA E ID_PER
    public String obtenerHE(String fecha, String IDPER)
    {
        try 
        {
            DBCON RCN = new DBCON();
            CallableStatement cst = RCN.conector().prepareCall("{?=call F_HE_asistencia(?,?)}");
            cst.setString(2, fecha);
            cst.setString(3, IDPER);
            cst.registerOutParameter(1, Types.VARCHAR);
            cst.execute();
            String resultado = cst.getString(1);
            return resultado;
            
        } catch (Exception e) 
            {
                lc.write("la base de datos no retorno conexion! -> obtenerHE", "NANOCON_Asistencia", e);
            }
        return "0";
    }
    
    //##########################################################################
    //ESPACIO ARRAYLIST Y OTROS METODOS QUE INTERACTUAN CON LA BASE DE DATOS
    
    //ArrayList controlador
    private ArrayList<NOB_Asistencia> L_ASISTENCIA;

    //GET
    public ArrayList<NOB_Asistencia> getL_ASISTENCIA() 
    {
        return L_ASISTENCIA;
    }

    //SET
    public void setL_ASISTENCIA(ArrayList<NOB_Asistencia> L_ASISTENCIA) 
    {
        this.L_ASISTENCIA = L_ASISTENCIA;
    }
    
    public void A_Objeto(NOB_Asistencia p)
    {
        L_ASISTENCIA.add(p);
    }
    
    public int tamaño()
    {
        return L_ASISTENCIA.size();
    }
    
    public NOB_Asistencia obtener(int pos)
    {
        return L_ASISTENCIA.get(pos);
    }
    
    //LLENA ARRAYLIST
    public final void llenarIDS_ASIST()
    {
       try
        {   
            DBCON RCN = new DBCON();
            L_ASISTENCIA.clear();
            st=RCN.conector().prepareStatement("SELECT ID_ASIST FROM ASISTENCIA order by ID_ASIST");
            rs=st.executeQuery();

            while (rs.next())
            {            
                String ID_ASIST = rs.getString("ID_ASIST");
                NOB_Asistencia na = new NOB_Asistencia(ID_ASIST);
                A_Objeto(na);
            }
            RCN.conector().close();

        }
        catch (SQLException sqle) 
            {
               lc.write( "La base de datos no retorno conexion!","NANOCON_Asistencia", sqle);
            }
        catch (Exception e)
            {
               lc.write( "ha ocurrido algun error no controlado","NANOCON_Asistencia", e);
            }
        
    }
    
    
}
