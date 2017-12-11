/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker;

import NCLPM.LOG;
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
    //puentes
    LOG lc = new LOG(); //puente apuntando a la clase LOG

    
    //variables de conexion
    JFrame form;
    protected Connection cn;
    protected ResultSet rs;
    protected PreparedStatement st;
    //Variables de configuracion de base
    
    //Lineas de Conexion
    String LineaTesting = "192.168.0.100"; //Linea de testing
    String LineaOriginal = "SERVIDOR"; //DEPRECATED - Linea de uso estandar
    String LineaNoIp = "roedjot.servehttp.com"; //Linea Internet
    //USERS
    String UP = "roedjot";//Usuario Principal
    String UD = "roedjotdev"; //Usuario de Desarrollo
    //Configuracion de Servidor
    String serverName = LineaTesting; //LINEA QUE USARA LA BASE
    String serverPort ="1521"; //PUERTO QUE USARA LA BASE
    String userName =UD; // USUARIO QUE USARA LA LINEA QUE USARA LA BASE
    String password ="roedjot"; //CONTRASEÑA QUE USARA EL USUARIO DE LINEA QUE USARA LA BASE
    String sid ="XE"; //SID 
       
    //Retornos de Variables de configuracion de base
    final public String ServerName() //Retorno del serverName
    {
        return serverName;
    }
    
    final public String ServerPort() //Retorno del puerto (serverPort)
    {
        return serverPort;
    }
    
    final public String UserName() //Retorno del username
    {
        return userName;
    }
    
    final public String Password() //Retorno del Password
    {
        return password;
    }
    
    final public String Sid() //Retorno del SID
    {
        return sid;
    }
    
    //constructor
    public DBCON()
    {
        DB_ORC_CON();
    }
    
    //PROGRAMACION DEL CONECTOR
     private void DB_ORC_CON()
    {
        cn = null;
        try 
        {
            String driverName = "oracle.jdbc.driver.OracleDriver"; //DRIVER QUE SE USARA
            Class.forName(driverName); // Implementacion de DriverName 
            String SN = ServerName(); //SN = SERVER NAME 
            String SP = ServerPort(); // SP = SERVERPORT
            String SID = Sid(); // SID = sid
            String url ="jdbc:oracle:thin:@"+SN+":"+SP+":"+SID; //Url de conexion de base
            String UN = UserName(); //UN = USERNAME
            String PSW = Password(); //PSW = USERPASSWORD
            cn = DriverManager.getConnection(url,UN,PSW); //IMPLEMENTACION DE LA CONEXIOn 
        } catch (ClassNotFoundException e) 
            {
                JOptionPane.showMessageDialog(form, "El driver no retorno conexion! "+e.getMessage());
                lc.write( "El driver no retorno conexion!","DBCON", e.getMessage());
            }
        catch(SQLException sqle) 
        {
            JOptionPane.showMessageDialog(form,"La base de datos no retorno conexion "+sqle);
            lc.write( "La base de datos no retorno conexion!","DBCON", sqle.getMessage());
        }
    }
    
    final public Connection CN()
    {
        cn = null;
        try 
        {
            String driverName = "oracle.jdbc.driver.OracleDriver"; //DRIVER QUE SE USARA
            Class.forName(driverName); // Implementacion de DriverName 
            String SN = ServerName(); //SN = SERVER NAME 
            String SP = ServerPort(); // SP = SERVERPORT
            String SID = Sid(); // SID = sid
            String url ="jdbc:oracle:thin:@"+SN+":"+SP+":"+SID; //Url de conexion de base
            String UN = UserName(); //UN = USERNAME
            String PSW = Password(); //PSW = USERPASSWORD
            cn = DriverManager.getConnection(url,UN,PSW); //IMPLEMENTACION DE LA CONEXIOn 
            return cn;
        } catch (ClassNotFoundException e) 
            {
                JOptionPane.showMessageDialog(form, "El driver no retorno conexion! "+e.getMessage());
                lc.write( "El driver no retorno conexion!","DBCON", e.getMessage());
            }
        catch(SQLException sqle) 
        {
            JOptionPane.showMessageDialog(form,"La base de datos no retorno conexion "+sqle);
            lc.write( "La base de datos no retorno conexion!","DBCON", sqle.getMessage());
        }
        return cn;
    }
    
       
    
    
    
}
