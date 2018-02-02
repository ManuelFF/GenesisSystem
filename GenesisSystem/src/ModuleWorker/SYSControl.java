/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker;

import NCLPM.LOG;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author USUARIO
 */
public class SYSControl 
{
    //fichero TMP
    private String appPath = System.getProperties().getProperty("user.home");
    private File fichero = new File( appPath + "\\AppData\\Local\\Temp\\GenesisTemporalFile.tmp");
    
    SYSAUDIOCON sysau = new SYSAUDIOCON();

    JFrame jf=new JFrame();
    LOG lc = new LOG();
     
    public int comprobar()
    {

        if(fichero.exists())
       {
          //SI EXISTE EL TMP
          int codigo = read();
          switch (codigo) 
          {
              case 1:
                  //SISTEMA EN EJECUCION
                  return codigo;
              case 0:
                  return codigo;
              case 2:
                  return codigo;
              case 3:
                  return codigo;
              default:
                  break;
          }
       }else
           if(fichero.exists()==false)
           {
               //SI NO EXISTE EL TMP
               makeTMP();
               write("1");
               return 3;
           }
       return 0;
    }
    
    public void makeTMP()
    {
        try 
        {
           BufferedWriter writer = new BufferedWriter(new FileWriter( fichero ));
           writer.close();

        } catch (IOException e) 
          {
              System.err.println(e.getMessage());
          }
    }
    
    public void deleteTMP()
    {
        try 
        {
            fichero.delete();            
        } catch (Exception e) 
          {
              
          }
    }
    
    public int read()
    {
        //otra formato
       String linea = "0";        
       int codigo = 0;
       BufferedReader bufferedReader;
       try 
       {
          bufferedReader = new BufferedReader( new FileReader( fichero ) );            
          while(bufferedReader.ready())
          {
             linea = bufferedReader.readLine();
          }
       bufferedReader.close();
            
       }catch (IOException e) 
        {
          System.err.println( e.getMessage() );
        }
        codigo = Integer.parseInt(linea);
        return codigo;
    }  

    public void write(String codigo)
    {
        try 
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fichero));
            writer.write(codigo);
            writer.close();
            
        } catch (Exception e) 
          {
              
          }
    }
    
    public void clear()
    {
        deleteTMP();
        makeTMP();
    }
    
    public void Close_System()
    {
        try 
        {
            if(fichero.exists())
            {
                fichero.delete();
            }
            
            System.exit(0);
            
        } catch (Exception e) 
        {
          System.err.println(e.getMessage());
        }
        
    }
    
    
    
}
