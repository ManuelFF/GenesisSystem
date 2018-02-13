/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker;

import NCLPM.LOG;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;

/**
 *
 * @author USUARIO
 */

public class SYSCON 
{
    //puentes
    LOG lc = new LOG(); //puente apuntando a la clase LOG
    
    //variables de conexion
    JFrame form;
    private Connection cn;
    private ResultSet rs;
    private PreparedStatement st;
    
    //establece version del sistema
    public String version()
    {
        String ver = "19.7.1.1";
        return ver;
    }
    
    //establece nombre del sistema
    public String nombre_sistema()
    {
        String n = "Génesis";
        return n;
    }
    
    //establece nombre de la compañia
    public String nombre_compañia()
    {
        String nc = "Saneamiento Roedjot S.A.C";
        return nc;
    }
    
    //Cargar versión del sistema en base
    public String cargar_version()
    {
        try 
        {
           st=cn.prepareStatement("SELECT VER_SIS FROM sistema");
           rs=st.executeQuery();
           
           while(rs.next())
           {
               String ver = rs.getString("VER_SIS");
               return ver;
           }
        } catch (SQLException SQLe) 
          {
              String des = "No se ha podido obtener la version del sistema. Error de comunicación con la base";
              lc.write(des,"SYSCON", SQLe);
          }
        return null;
    }
    
    //Cargar version de sistema en texto
    public String cargar_version_texto()
    {
		
	try 
        {
           String archivo = "ver.txt";
           File f = new File(archivo);
           // Permite abrir el archivo como SOLO LECTURA
	   FileReader fr = new FileReader(f);
           // El objeto br permite acceder al m�todo readLine() que 
           // lee los datos del archivo l�nea por l�nea.
           BufferedReader br = new BufferedReader(fr);
           String linea;
           while( (linea=br.readLine() )!= null )
           {
            // Pasamos los valores de la cadena linea al arreglo data[]
            String data[] = linea.split("/");
            // Separamos los datos en variables
            String ver = data[0];
            return ver;
           }
			
	}catch (IOException e)
         {
           String des = "Error en el cargado de la versión de texto";
           lc.write(des,"SYSCON", e);
         }	
        return null;
    }
    

    
    
}
