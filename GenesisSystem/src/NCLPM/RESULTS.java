/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NCLPM;

import ModuleWorker.IC.MWCON;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;

/**
 *
 * @author USUARIO
 */
public class RESULTS 
{
    /*
    EVENTS : en esta clase se manejara los eventos del usuario (seguimiento); cada acción
    que genere un evento sera rastreada desde esta clase.
    */
       
    MWCON mw = new MWCON();
    JFrame form;
    
    private String date()
    {
        Date date = new Date();
        
        DateFormat año_current = new SimpleDateFormat("yy");
        DateFormat dia_current = new SimpleDateFormat("dd");
        DateFormat mes_current = new SimpleDateFormat("MM");

        String dia_C = ""+dia_current.format(date);
        String mes_C = ""+mes_current.format(date);
        String año_C = ""+año_current.format(date);
        
        String fecha = dia_C+"-"+mes_C+"-"+año_C;
        return fecha;
    }
        
    public void write(String usuario,String formulario ,String tipo,String descripcion)
    {
        try 
        {
            String archivo = "./LGS/results_"+date()+".txt";
            // Creamos un objeto f, que representa al archivo Usuarios.txt en formato binario
            File f = new File(archivo);
            // Permite abrir el archivo para ESCRITURA (Permite grabar datos)
            FileWriter fw = new FileWriter(f,true);
            // El objeto pw permite invocar al m�todo print() para grabar datos en el archivo
            PrintWriter pw = new PrintWriter(fw);
           
            pw.println("USUARIO : "+usuario);
            pw.println("FORMULARIO : "+formulario);
            pw.println("HORA : "+mw.hour_actual());
            pw.println("TIPO : "+tipo);
            pw.println("DESCRIPCIÓN : "+descripcion);
            pw.println("----------------------------------------------------");
            pw.println(" ");
            pw.close();

        }catch (IOException e) 
          {
              System.out.println("ERROR AL ESCRIBIR EN EL RESULTS "+e);
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
