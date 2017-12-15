/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker.IC;

import ModuleWorker.DBCON;
import NCLPM.LOG;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author USUARIO
 */
public class MICROCON_InicioSesion 
{
    private ResultSet rs;
    private PreparedStatement st;
    Connection cn;
    LOG lc = new LOG(); //puente apuntando a la clase LOG

    public String validarUsuario(String usr, String pass)
    {
        try 
        {
            DBCON db = new DBCON();
            cn = db.conector();
                        
            CallableStatement cst = cn.prepareCall("{?=call F_inicio_sesion(?,?)}");
            cst.setString(2, usr);
            cst.setString(3, pass);
            cst.registerOutParameter(1, Types.VARCHAR);
            cst.execute();
            String resultado = cst.getString(1);
            return resultado;
            
        } catch (SQLException sqle) 
        {
            lc.write( "La base de datos no retorno conexion!","MICROCON_InicioSesion", sqle.getMessage());
            return "ERROR";
        } finally 
           {
             try 
             {
               cn.close();
             }catch (Exception sqlex) 
               {
            lc.write( "Error no controlado en el bloque del Finally del metodo ValidarUsuario","MICROCON_InicioSesion", sqlex.getMessage());
               }
            }
    }
    
    public String obtenerNombreUSR(String usr)
    {
        try 
        {
            DBCON db = new DBCON();
            cn = db.conector();
            CallableStatement cst = cn.prepareCall("{?=call F_Nombre_Personal(?)}");
            cst.setString(2, usr);
            cst.registerOutParameter(1, Types.VARCHAR);
            cst.execute();
            String resultado = cst.getString(1);
            return resultado;
            
        } catch (SQLException sqle) 
        {
            lc.write( "La base de datos no retorno conexion!","MICROCON_InicioSesion", sqle.getMessage());
        } finally 
           {
             try 
             {
               cn.close();
             }catch (Exception sqlex) 
               {
            lc.write( "Error no controlado en el bloque del Finally del metodo ObtenerNombreUSR","MICROCON_InicioSesion", sqlex.getMessage());
               }
            }
        
        return "0";
    }
    
}
