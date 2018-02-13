/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IC;

import Core.DBCON;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
            return sistema;
            
        }catch(SQLException sqlex)
            {
                System.err.println(sqlex);
                //lc.write("Problema al Cargar Datos en el metodo 'img'", "MICROCON_ConsultaCertificados", sqlex);
            }
        try 
        {
        } catch (Exception ex)
            {
                 System.err.println(ex);
                //lc.write("Error no controlado en el metodo 'img'", "MICROCON_ConsultaCertificados", ex);
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
            //Pasamos los valores de la cadena linea al arreglo data[]
            String data[] = linea.split("/");
            //Separamos los datos en variables
            String ver = data[0];
            return ver;
           }
			
	}catch (IOException e)
         {
           String des = "Error en el cargado de la versión de texto";
           //lc.write(des,"SYSCON", e);
         }	
        return null;
    }
    
    public void abrir_archivo(String archivo)
    {
       try 
       {
         File ObjectoFile = new File(archivo);
         Desktop.getDesktop().open(ObjectoFile);
       } catch (IOException ioex) 
            {
                //lc.write("Error al intentar abrir el archivo "+archivo, "MWCON", ioex);
            }
    }
    
    
}
