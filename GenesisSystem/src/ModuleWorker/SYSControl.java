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
import javax.swing.JFrame;

/**
 *
 * @author USUARIO
 */
public class SYSControl 
{
    //fichero TMP
    private String appPath = System.getProperties().getProperty("user.home");
    private File fichero = new File( appPath + "\\GenesisTemporalFile.tmp");
    
    SYSAUDIOCON sysau = new SYSAUDIOCON();

    JFrame jf=new JFrame();
    LOG lc = new LOG();
     
    public boolean comprobar()
    {
       jf.setAlwaysOnTop(true);
       
       if(fichero.exists())
       {
          // int codigo = leer();
       }
       
       return false;

    }
    
    public int leer()
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
            
       }catch (IOException e) 
        {
          System.err.println( e.getMessage() );
        }
        codigo = Integer.parseInt(linea);
        return codigo;
    }  

    
    
    
}
