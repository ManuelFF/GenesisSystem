/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NCLPM;

import ModuleWorker.IC.MWCON;
import java.io.File;
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
public class GEN_STATUS
{
     /*
    GEN_STATUS : en esta clase seran seguidos las conexiones de todos los usuarios que se conectec a Génesis
    Cada conexion que genere un usuario sera almacenada en esta clase.
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
        
    //************************* ESTE METODO SE USA PARA ESCRIBIR DENTRO DE GENCON ***********************************//
    public void write_CON(String nombre,String hora_CON,String fecha_CON,String Tipo_CON)
    {
        try 
        {
            String carpet = "./LGS/GENCON/"+date();
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

            
            String archivo = "./LGS/GENCON/"+carpet_name+"/"+nombre+"-"+date()+".GENCON";
            // Creamos un objeto f, que representa al archivo Usuarios.txt en formato binario
            File f = new File(archivo);
            // Permite abrir el archivo para ESCRITURA (Permite grabar datos)
            FileWriter fw = new FileWriter(f,true);
            // El objeto pw permite invocar al m�todo print() para grabar datos en el archivo
            PrintWriter pw = new PrintWriter(fw);
           
            pw.println("*********************GENCON************************");
            pw.println("Nombre : "+nombre);
            pw.println("Hora CON : "+hora_CON);
            pw.println("Fecha CON : "+fecha_CON);
            pw.println("Tipo CON : "+Tipo_CON);
            pw.println("CAPTURED TIME : "+mw.hour_actual());
            pw.println("----------------------------------------------------");
            pw.println(" ");
            pw.close();

        }catch (IOException e) 
          {
              System.out.println("ERROR AL ESCRIBIR EN GENCON "+e);
          }
    }
    
    //************************* ESTE METODO SE USA PARA ESCRIBIR DENTRO DE GENCON ***********************************//
    public void write_DIS(String nombre,String hora_DIS,String fecha_DIS,String Tipo_DIS)
    {
        try 
        {
            
            String carpet = "./LGS/GENDIS/"+date();
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

            
            
            String archivo = "./LGS/GENDIS/"+carpet_name+"/"+nombre+"-"+date()+".GENDIS";
            // Creamos un objeto f, que representa al archivo Usuarios.txt en formato binario
            File f = new File(archivo);
            // Permite abrir el archivo para ESCRITURA (Permite grabar datos)
            FileWriter fw = new FileWriter(f,true);
            // El objeto pw permite invocar al m�todo print() para grabar datos en el archivo
            PrintWriter pw = new PrintWriter(fw);
           
            pw.println("*********************GENDIS************************");
            pw.println("Nombre : "+nombre);
            pw.println("Hora DIS : "+hora_DIS);
            pw.println("Fecha DIS : "+fecha_DIS);
            pw.println("Tipo DIS : "+Tipo_DIS);
            pw.println("CAPTURED TIME : "+mw.hour_actual());
            pw.println("----------------------------------------------------");
            pw.println(" ");
            pw.close();

        }catch (IOException e) 
          {
              System.out.println("ERROR AL ESCRIBIR EN GENDIS "+e);
          }
    }
    
}
