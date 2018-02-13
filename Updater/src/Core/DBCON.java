/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class DBCON
{
    //Puentes
    //considerar el uso de la clase LOG
    
    private Connection cn;
    private ResultSet rs;
    private PreparedStatement st;
    
    //lienas de conexion
    String LT = "192.168.0.100";
    String LO = "192.168.0.35";
    String LNP = "roedjot.servehttp.com"; //DEPRECATED
    
    //USUARIOS
    String LTU = "roedjotdev";
    String LOU = "roedjot";
    
    String ServerName = LT;
    String ServerPort = "1521";
    
    String username = LTU;
    String password = "roedjot";
    String sid = "XE";
   
    private Connection DB_ORC_CON()
    {
        cn = null;
        JFrame jf = new JFrame();
        jf.setAlwaysOnTop(true);
        
        try 
        {
          String driverName = "oracle.jdbc.driver.OracleDriver"; //DRIVER QUE SE USARA
            Class.forName(driverName); // Implementacion de DriverName 
            String SN = ServerName; //SN = SERVER NAME 
            String SP = ServerPort; // SP = SERVERPORT
            String SID = sid; // SID = sid
            String url ="jdbc:oracle:thin:@"+SN+":"+SP+":"+SID; //Url de conexion de base
            String UN = username; //UN = USERNAME
            String PSW = password; //PSW = USERPASSWORD
            cn = DriverManager.getConnection(url,UN,PSW); //IMPLEMENTACION DE LA CONEXIOn 
            return cn;
        } catch (ClassNotFoundException e) 
            {
                JOptionPane.showMessageDialog(jf, "El driver no retorno conexion! ");
                
                //lc.write( "El driver no retorno conexion!","DBCON metodo DB_ORC_CON", e);
                
            }
        catch(SQLException sqle) 
        {       
           
            JOptionPane.showMessageDialog(jf,"No se puede conectar a la base de datos", "Base de datos no conectada", JOptionPane.ERROR_MESSAGE);
            //lc.write( "La base de datos no retorno conexion!","DBCON metodo DB_ORC_CON", sqle);
            
        }
        return cn;
    }
    

    final public Connection conector()
    {
        return DB_ORC_CON();
    }
    
    
}
