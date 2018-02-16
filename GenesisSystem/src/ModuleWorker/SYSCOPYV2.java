/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker;
import NCLPM.LOG;
import java.io.*;
import java.nio.channels.*;

/**
 *
 * @author USUARIO
 */

public class SYSCOPYV2
{
    LOG lc = new LOG();
    
    public static void copyFile(String inS, String outS) throws IOException
    {
        File in = new File(inS);
        File out = new File(outS);
        
        FileChannel inChannel = new FileInputStream(in).getChannel();
        
        FileChannel outChannel = new FileOutputStream(out).getChannel();
        try 
        {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException e) 
          {
              System.err.println("Problema al intentar usar el algoritmo de copiado : "+e);
          }finally
                {
                    if(inChannel != null) inChannel.close();
                    if(outChannel != null) outChannel.close();
                }
    }
    
    public static void main(String[] args) throws IOException
    {
       SYSCOPYV2.copyFile(new String (args[0]),new String (args[1]));
    }
    
}
