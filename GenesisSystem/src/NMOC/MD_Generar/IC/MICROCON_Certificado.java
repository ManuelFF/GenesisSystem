/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Generar.IC;

import ModuleWorker.DBCON;
import NCLPM.LOG;
import NMOC.MD_Generar.Core.NOB_Certificado;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class MICROCON_Certificado 
{
    private ResultSet rs;
    private PreparedStatement st;
    LOG lc = new LOG();
    
    //INICIALIZADOR
    public MICROCON_Certificado()
    {
        
        L_CERTIFICADO = new ArrayList();
        
        
    }
    
    //INSERTAR CERTIFICADO
    public void InsertarCertificado(String cod, String numero_cert, String razon_social, String ubicacion,
            String giro, String area, String formato,String fech_serv,String fech_ven,String telf,
            String costo,String periodo,String dt,String cli,String nume_orde, String cert_esca, 
            String estado )
    {
      try{
            DBCON RCN = new DBCON();

            // Llamada al procedimiento almacenado
            CallableStatement cst = RCN.conector().prepareCall("{call usp_insertar_certificadoV2(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            // Parametro del procedimiento almacenado
            cst.setString(1, cod);
            cst.setString(2, numero_cert);
            cst.setString(3, razon_social);
            cst.setString(4, ubicacion);
            cst.setString(5, giro);
            cst.setString(6, area);
            cst.setString(7, formato);
            cst.setString(8, fech_serv);
            cst.setString(9, fech_ven);
            cst.setString(10, telf);
            cst.setString(11, costo);
            cst.setString(12, periodo);
            cst.setString(13, dt);
            cst.setString(14, cli);
            cst.setString(15, nume_orde);
            cst.setString(16, cert_esca);
            cst.setString(17, estado);
            
            // Ejecuta el procedimiento almacenado
            cst.execute();
            cst.close();

         }catch (SQLException ex) 
            {
                 lc.write("Problema al intentar insertar un Certificado en el metodo 'InsertarCertificado'", "MICROCON_Certificado linea 42", ex);             
            } 
    }
    
    //MODIFICAR CERTIFICADO
    public void ModificarCertificado(String cod, String numero_cert, String razon_social, String ubicacion,
            String giro, String area, String formato,String fech_serv,String fech_ven,String telf,
            String costo,String periodo,String dt,String cli,String nume_orde, String cert_esca, 
            String estado )
    {
      try{
            DBCON RCN = new DBCON();

            // Llamada al procedimiento almacenado
            CallableStatement cst = RCN.conector().prepareCall("{call usp_modificar_certificadoV2(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            // Parametro del procedimiento almacenado
            cst.setString(1, cod);
            cst.setString(2, numero_cert);
            cst.setString(3, razon_social);
            cst.setString(4, ubicacion);
            cst.setString(5, giro);
            cst.setString(6, area);
            cst.setString(7, formato);
            cst.setString(8, fech_serv);
            cst.setString(9, fech_ven);
            cst.setString(10, telf);
            cst.setString(11, costo);
            cst.setString(12, periodo);
            cst.setString(13, dt);
            cst.setString(14, cli);
            cst.setString(15, nume_orde);
            cst.setString(16, cert_esca);
            cst.setString(17, estado);
            
            // Ejecuta el procedimiento almacenado
            cst.execute();
            cst.close();

         }catch (SQLException ex) 
            {
                 lc.write("Problema al intentar modificar un Certificado en el metodo 'ModificarCertificado'", "MICROCON_Certificado linea 42", ex);             
            } 
    }    
    
     //INSERTAR DETALLE CERTIFICADO
    public void InsertarDETCertificado(String cod, String serv)
    {
      try{
            DBCON RCN = new DBCON();

            // Llamada al procedimiento almacenado
            CallableStatement cst = RCN.conector().prepareCall("{call usp_insertar_DETcertificadoV2(?,?)}");
            // Parametro del procedimiento almacenado
            cst.setString(1, cod);
            cst.setString(2, serv);
            // Ejecuta el procedimiento almacenado
            cst.execute();
            cst.close();

         }catch (SQLException ex) 
            {
                 lc.write("Problema al intentar insertar un detalle de Certificado en el metodo 'InsertarDETCertificado'", "MICROCON_Certificado linea 42", ex);             
            } 
    }
    
     //MODIFICAR DETALLE CERTIFICADO
    public void ModificarDETCertificado(String cod, String serv)
    {
      try{
            DBCON RCN = new DBCON();

            // Llamada al procedimiento almacenado
            CallableStatement cst = RCN.conector().prepareCall("{call usp_modificar_DETcertificadoV2(?,?)}");
            // Parametro del procedimiento almacenado
            cst.setString(1, cod);
            cst.setString(2, serv);
            // Ejecuta el procedimiento almacenado
            cst.execute();
            cst.close();

         }catch (SQLException ex) 
            {
                 lc.write("Problema al intentar insertar un detalle de Certificado en el metodo 'InsertarDETCertificado'", "MICROCON_Certificado linea 42", ex);             
            } 
    }

    //##############################################################################
    //ESPACIO ARRAYLIST Y OTROS METODOS QUE INTERACTUAN CON LA BASE DE DATOS
    
    //ArrayList Controlador
    private ArrayList<NOB_Certificado> L_CERTIFICADO;

    //GET
    public ArrayList<NOB_Certificado> getL_CERTIFICADO() 
    {
        return L_CERTIFICADO;
    }
    
    //SET
    public void setL_CERTIFICADO(ArrayList<NOB_Certificado> L_CERTIFICADO) 
    {
        this.L_CERTIFICADO = L_CERTIFICADO;
    }
    
    //ADD OBEJECT
    public void A_Objeto(NOB_Certificado p)
    {
        L_CERTIFICADO.add(p);
    }
    
    //RETURN SIze
    public int tamaño()
    {
      return L_CERTIFICADO.size();
    }
    
    //RECIBE POS Y RETORNA OBJ
    public NOB_Certificado obtener(int pos)
    {
        return L_CERTIFICADO.get(pos);
    }
    
    //CLEAR
    public void Clear()
    {
        L_CERTIFICADO.clear();
    }
    
    
    
    //LLENNA ARRAY LIST
    public final void llenarDatos()
    {
        try
        {   
            DBCON RCN = new DBCON();
            L_CERTIFICADO.clear();
            st=RCN.conector().prepareStatement("SELECT ID_CERT FROM CERTIFICADOV2 order by ID_CERT");
            rs=st.executeQuery();

            while (rs.next())
            {            
                String ID_CERT = rs.getString("ID_CERT");
                NOB_Certificado nu = new NOB_Certificado(ID_CERT);
                A_Objeto(nu);
            }
            RCN.conector().close();

        }
        catch (SQLException sqle) 
            {
               lc.write( "La base de datos no retorno conexion!","MICROCON_Certificado", sqle);
            }
        catch (Exception e)
            {
               lc.write( "ha ocurrido algun error no controlado","MICROCON_Certificado", e);
            }
        
        }  
    
    
    
    
    
    
    
    
    
    
    
    
    
}
