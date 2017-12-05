/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NCLPM;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class EVENTS 
{
    /*
    Log : en esta clase se menejara el log de errores del sistema , el cual recopilara
    todos los errores con hora y fecha;
    */
    
    JFrame form;
    
    public void write(String path,String descripcion,String error,String time)
    {
        try 
        {
            String archivo = path;
            // Creamos un objeto f, que representa al archivo Usuarios.txt en formato binario
            File f = new File(archivo);
            // Permite abrir el archivo para ESCRITURA (Permite grabar datos)
            FileWriter fw = new FileWriter(f,true);
            // El objeto pw permite invocar al m�todo print() para grabar datos en el archivo
            PrintWriter pw = new PrintWriter(fw);
           
            pw.println("DESCRIPCIÓN : "+descripcion);
            pw.println("ERROR : "+error);
            pw.println("TIME : "+time);
            pw.println("----------------------------------------------------");
            pw.println(" ");
            pw.close();
            

        }catch (IOException e) 
          {
              System.out.println("ERROR AL ESCRIBIR EN EL LOG "+e);
          }
    }
    
    public String read(String ruta)
    {
        FileReader fr = null;
        BufferedReader br = null;
        //Cadena de texto donde se guardara el contenido del archivo
        String contenido = "";
        try{
                //ruta puede ser de tipo String o tipo File
                fr = new FileReader( ruta );
                br = new BufferedReader( fr );

                String linea;
                //Obtenemos el contenido del archivo linea por linea
                while( ( linea = br.readLine() ) != null ){ 
                        contenido += linea + "\n";
                }

        }catch( Exception e ){  }
        //finally se utiliza para que si todo ocurre correctamente o si ocurre 
        //algun error se cierre el archivo que anteriormente abrimos
        finally{
                try{
                        br.close();
                }catch( Exception ex ){}
        }
        return contenido;
    }
    
    public void clear()
    {
        
    }
    
}
