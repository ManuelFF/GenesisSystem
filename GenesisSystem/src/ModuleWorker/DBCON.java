/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker;

import ModuleWorker.coreModels.DbConfModel;
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
public class DBCON {

    //puentes
    LOG lc = new LOG(); //puente apuntando a la clase LOG

    //variables de conexion
    protected Connection cn;
    protected ResultSet rs;
    protected PreparedStatement st;
    //Variables de configuracion de base

    //Configuracion de Servidor
    String serverName = DbConfModel.getDbConfModel().getDb_serverName(); //LINEA QUE USARA LA BASE
    String serverPort = DbConfModel.getDbConfModel().getDb_serverPort(); //PUERTO QUE USARA LA BASE
    String userName = DbConfModel.getDbConfModel().getDb_userName(); // USUARIO QUE USARA LA LINEA QUE USARA LA BASE
    String password = DbConfModel.getDbConfModel().getDb_password(); //CONTRASEÑA QUE USARA EL USUARIO DE LINEA QUE USARA LA BASE
    String sid = DbConfModel.getDbConfModel().getDb_sid(); //SID 

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

    protected Connection DB_ORC_CON() {
        cn = null;
        JFrame jf = new JFrame();
        jf.setAlwaysOnTop(true);

        try {
            String driverName = "oracle.jdbc.driver.OracleDriver"; //DRIVER QUE SE USARA
            Class.forName(driverName); // Implementacion de DriverName 
            String SN = ServerName(); //SN = SERVER NAME 
            String SP = ServerPort(); // SP = SERVERPORT
            String SID = Sid(); // SID = sid
            String url = "jdbc:oracle:thin:@" + SN + ":" + SP + ":" + SID; //Url de conexion de base
            String UN = UserName(); //UN = USERNAME
            String PSW = Password(); //PSW = USERPASSWORD
            cn = DriverManager.getConnection(url, UN, PSW); //IMPLEMENTACION DE LA CONEXIOn 
            return cn;
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(jf, "El driver no retorno conexion! ");
            lc.write("El driver no retorno conexion!", "DBCON metodo DB_ORC_CON", e);
        } catch (SQLException sqle) {

            JOptionPane.showMessageDialog(jf, "No se puede conectar a la base de datos", "Base de datos no conectada", JOptionPane.ERROR_MESSAGE);

            lc.write("La base de datos no retorno conexion!", "DBCON metodo DB_ORC_CON", sqle);
        }
        return cn;
    }

    final public Connection conector() {
        return DB_ORC_CON();
    }

}
