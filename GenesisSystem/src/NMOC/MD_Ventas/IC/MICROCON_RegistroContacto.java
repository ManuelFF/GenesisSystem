/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Ventas.IC;

import ModuleWorker.DBCON;
import NCLPM.LOG;
import NMOC.MD_Ventas.Core.NOB_registroContacto;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class MICROCON_RegistroContacto
{
    
    private ResultSet rs;
    private PreparedStatement st;
    LOG lc = new LOG();
    
    //INICIALIZADOR
    public MICROCON_RegistroContacto()
    {
        L_REGCON = new ArrayList();
    }
    
    //INSERTAR REGISTRO
    public void InsertarRegistro(String ID_REG, String tip_con, String estado, String nombre,String dni,String descripcion,String area,String formato,String telf, String correo,String fech,String hor, String costo)
    {
        try{
            DBCON RCN = new DBCON();

            // Llamada al procedimiento almacenado
            CallableStatement cst = RCN.conector().prepareCall("{call usp_insertar_regcont(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            // Parametro del procedimiento almacenado
            cst.setString(1, ID_REG);
            cst.setString(2, tip_con);
            cst.setString(3, estado);
            cst.setString(4, nombre);
            cst.setString(5, dni);
            cst.setString(6, descripcion);
            cst.setString(7, area);
            cst.setString(8, formato);
            cst.setString(9, telf);
            cst.setString(10, correo);
            cst.setString(11, fech);
            cst.setString(12, hor);
            cst.setString(13, costo);
            // Ejecuta el procedimiento almacenado
            cst.execute();
            cst.close();
         }catch (SQLException ex) 
            {
                 lc.write("Problema al intentar insertar un registro en el metodo 'InsertarRegistro'", this.getClass().getName(), ex);             
            } 
    }
    
    //MODIFICAR REGISTRO
    public void ModificarRegistro(String ID_REG, String tip_con, String estado, String nombre,String dni,String descripcion,String area,String formato,String telf, String correo,String fech,String hor, String costo)
    {
        try{
            DBCON RCN = new DBCON();

            // Llamada al procedimiento almacenado
            CallableStatement cst = RCN.conector().prepareCall("{call usp_modificar_regcont(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            // Parametro del procedimiento almacenado
            cst.setString(1, ID_REG);
            cst.setString(2, tip_con);
            cst.setString(3, estado);
            cst.setString(4, nombre);
            cst.setString(5, dni);
            cst.setString(6, descripcion);
            cst.setString(7, area);
            cst.setString(8, formato);
            cst.setString(9, telf);
            cst.setString(10, correo);
            cst.setString(11, fech);
            cst.setString(12, hor);
            cst.setString(13, costo);
            // Ejecuta el procedimiento almacenado
            cst.execute();
            cst.close();
         }catch (SQLException ex) 
            {
                 lc.write("Problema al intentar modificar un registro en el metodo 'ModificarRegistro'", this.getClass().getName(), ex);             
            } 
    }
    
     //INSERTAR DETALLE REGISTRO
    public void InsertarDETRegistro(String ID_REG, String serv)
    {
        try{
            DBCON RCN = new DBCON();

            // Llamada al procedimiento almacenado
            CallableStatement cst = RCN.conector().prepareCall("{call usp_insertar_detregcont(?,?)}");
            // Parametro del procedimiento almacenado
            cst.setString(1, ID_REG);
            cst.setString(2, serv);
            // Ejecuta el procedimiento almacenado
            cst.execute();
            cst.close();
         }catch (SQLException ex) 
            {
                 lc.write("Problema al intentar insertar un detalle de registro en el metodo 'InsertarDETRegistro'", this.getClass().getName(), ex);             
            } 
    }
    
    //MODIFICAR DETALLE REGISTRO
    public void ModificarDETRegistro(String ID_REG, String serv)
    {
        try{
            DBCON RCN = new DBCON();

            // Llamada al procedimiento almacenado
            CallableStatement cst = RCN.conector().prepareCall("{call usp_modificar_detregcont(?,?)}");
            // Parametro del procedimiento almacenado
            cst.setString(1, ID_REG);
            cst.setString(2, serv);
            // Ejecuta el procedimiento almacenado
            cst.execute();
            cst.close();
         }catch (SQLException ex) 
            {
                 lc.write("Problema al intentar modificar un detalle de registro en el metodo 'InsertarDETRegistro'", this.getClass().getName(), ex);
            } 
    }
    
    
    //##############################################################################
    //ESPACIO ARRAYLIST Y OTROS METODOS QUE INTERACTUAN CON LA BASE DE DATOS
    
    //ArrayList Controlador
    private ArrayList<NOB_registroContacto> L_REGCON;
    
    //GET
    public ArrayList<NOB_registroContacto> getL_REGCON() 
    {
        return L_REGCON;
    }
    
    //SET
    public void setL_REGCON(ArrayList<NOB_registroContacto> L_REGCON) 
    {
        this.L_REGCON = L_REGCON;
    }
    
    //ADD OBJECT
    public void A_Objeto(NOB_registroContacto p)
    {
        L_REGCON.add(p);
    }
    
    //RETURN SIZE
    public int tamaño()
    {
        return L_REGCON.size();
    }
    
    //Recibe pos y retorna objt
    public NOB_registroContacto obtener(int pos)
    {
        return L_REGCON.get(pos);
    }
    
    //CLEAR
    public void clear()
    {
        L_REGCON.clear();
    }
    
    //LLENA ARRAYLIST
    public final void llenarDatos()
    {
        try
        {   
            DBCON RCN = new DBCON();
            L_REGCON.clear();
            st=RCN.conector().prepareStatement("SELECT ID_REG_CONT FROM REGISTRO_CONTACTO order by ID_REG_CONT");
            rs=st.executeQuery();

            while (rs.next())
            {            
                String ID_REG = rs.getString("ID_REG_CONT");
                NOB_registroContacto nu = new NOB_registroContacto(ID_REG);
                A_Objeto(nu);
            }
            RCN.conector().close();

        }
        catch (SQLException sqle) 
            {
               lc.write( "La base de datos no retorno conexion!",this.getClass().getName(), sqle);
            }
        catch (Exception e)
            {
               lc.write( "ha ocurrido algun error no controlado",this.getClass().getName(), e);
            }
    }
    
    
}
