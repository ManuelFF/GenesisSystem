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
public class EVENTS 
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
    
     
    public void write(String usuario,String descripcion,String formulario,String accion)
    {
        try 
        {
            String carpet = "./LGS/Events/"+date();
            String carpet_name = null;

            File c = new File(carpet);

            if(!c.exists())
            {
             if(c.mkdir())
              {
                System.out.println("SUCCESS");
                carpet_name = c.getName();
              }else{System.err.println("Failed");}
            }else if(c.exists()){System.out.println("EXIST OVERRIDE");carpet_name = c.getName();}            
            
            String archivo = "./LGS/Events/"+carpet_name+"/"+"events_"+date()+"-"+usuario+".txt";
            // Creamos un objeto f, que representa al archivo Usuarios.txt en formato binario
            File f = new File(archivo);
            // Permite abrir el archivo para ESCRITURA (Permite grabar datos)
            FileWriter fw = new FileWriter(f,true);
            // El objeto pw permite invocar al m�todo print() para grabar datos en el archivo
            PrintWriter pw = new PrintWriter(fw);
           
            pw.println("USUARIO : "+usuario);
            pw.println("DESCRIPCIÓN : "+descripcion);
            pw.println("FORMULARIO : "+formulario);
            pw.println("ACCIÓN : "+accion);
            pw.println("TIME : "+mw.hour_actual());
            pw.println("----------------------------------------------------");
            pw.println(" ");
            pw.close();

        }catch (IOException e) 
          {
              System.out.println("ERROR AL ESCRIBIR EN EL EVENTS "+e);
          }
    }
        
}
