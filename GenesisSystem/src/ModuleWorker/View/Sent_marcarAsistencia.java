/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker.View;

import ModuleWorker.DBCON;
import NCLPM.LOG;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class Sent_marcarAsistencia 
{
    private static LOG lc = new LOG();
    private static ResultSet rs;
    private static PreparedStatement st;
    
    public static int UltimoID()         
    {
        int last=0;
        
        try{
            DBCON RCN = new DBCON();
            
            st=RCN.conector().prepareStatement("select sorted from V_ultimoCode");
            rs=st.executeQuery();
                        
            while (rs.next())
            {            
              last = rs.getInt("sorted");
            }
            System.out.println(last);
            
        }catch(SQLException sqlex)
            {
                lc.write("Problema al intentar hacer el SentMarcarAsistencia", "Sent_marcarAsistencia", sqlex);
            }
        try 
        {
        } catch (Exception ex)
            {
                lc.write("Error no controlado en el metodo Sent_MarcaR_asistencia", "Sent_marcarAsistencia", ex);
            }
        
        return (last+1);
    }
}
