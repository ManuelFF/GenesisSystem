/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IC;

import Core.DBCON;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author USUARIO
 */
public class UpdaterController
{
 
    private Connection cn;
    private ResultSet rs;
    private PreparedStatement st;
    
    public static void main(String[] args)
    {
    }
    
    public UpdaterController()
    {
        System.out.println(Ver_SIS());
    }
    
    //Obtener VER_SIS
    public String Ver_SIS()         
    {
        try{
            DBCON RCN = new DBCON();

            st=RCN.conector().prepareStatement("select VER_SIS from sistema");
            rs=st.executeQuery();
            
            String sistema = null;
            
            while (rs.next())
            {            
              sistema = rs.getString("VER_SIS");
            }
            System.out.println(sistema);
            return sistema;
            
        }catch(SQLException sqlex)
            {
                //lc.write("Problema al Cargar Datos en el metodo 'img'", "MICROCON_ConsultaCertificados", sqlex);
            }
        try 
        {
        } catch (Exception ex)
            {
                //lc.write("Error no controlado en el metodo 'img'", "MICROCON_ConsultaCertificados", ex);
            }
        return null;
    }
    
    
    
    
    
    
}
