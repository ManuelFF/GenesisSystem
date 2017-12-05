/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */

public class SYSCON 
{
    //variables de conexion
    JFrame form;
    protected Connection cn;
    protected ResultSet rs;
    protected PreparedStatement st;
    
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
              JOptionPane.showMessageDialog(form, "No se ha podido obtener la version del sistema.\nError de comunicación con la base\nERROR: "+SQLe);
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
			
	}catch (Exception e)
         {
           JOptionPane.showMessageDialog(form,"Error en el cargado\nEsto es para el programador ERROR??"+e);
         }	
        return null;
    }
    
    //establece version del sistema
    public String version()
    {
        String ver = "20";
        return ver;
    }
    
    //establece nombre del sistema
    public String nombre_sistema()
    {
        String n = "Crystal Génesis";
        return n;
    }
    
    //establece nombre de la compañia
    public String nombre_compañia()
    {
        String nc = "Saneamiento Roedjot S.A.C";
        return nc;
    }
    
    
    
    
}
