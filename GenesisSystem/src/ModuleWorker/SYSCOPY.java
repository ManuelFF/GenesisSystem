/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker;
import NCLPM.LOG;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 *
 * @author USUARIO
 */
public class SYSCOPY 
{
    LOG lc = new LOG();
    
   //Esta clase de tipo SYS (SYSTEM) se encarga de copiar un archivo de un lado a otro
   
   //INSTANCIAMOS la CLASE
    private static SYSCOPY SYSCOPY;
    
    //CONSTURCTOR PRIVADO
    private SYSCOPY()
    {
        //NOTHING
    }
    
    //METODo que permite obtener una instancia de la clase syscopy
    public static SYSCOPY getinstance()
    {
        if(SYSCOPY == null)
        {
            SYSCOPY = new SYSCOPY();
        }
        return SYSCOPY;
    }
    
    /*
    copia un archivop de una ubicacion a otra devuelve true si y solo si se completo la operacion
    correctamente caso contrario devuelve false 
    @param origen
    @paran destino
    @return boolean
    */
    
    public boolean copy(String origin, String destino)
    {
        File ArchivoOrigen;
        File ArchivoDestino;
        FileInputStream in = null;
        FileOutputStream out = null;
        boolean b;
        try 
        {
           ArchivoOrigen = new File(origin);
           ArchivoDestino = new File(destino);
           //VALIDAR que el archivo de origen exista en caso contrario de que no, saldremos del metodo
           if(b = ArchivoOrigen.exists())
           {
               //VALIDAMOS QUE EL ARCHIVO DE ORIGEN SE PUEDA LEER
               if(b = ArchivoOrigen.canRead())
               {
                   //Creamos lector y escritor
                   in = new FileInputStream(ArchivoOrigen);
                   out = new FileOutputStream(ArchivoDestino);
                   
                   //MIENTRAS se lee de un lado por otro lado se escribe
                   int c;
                   while((c = in.read()) != -1)
                   {
                       out.write(c);
                   }
               }
           }
        } catch (IOException ex) 
          {
              lc.write("Algun error en el proceso de copiado a ocurrido", "SYSCOPY", ex);
              b = false;
          }finally
                {
                    try 
                    {
                       if(in != null)
                       {
                           in.close();
                       }
                        if(out != null)
                        {
                            out.close();
                        }
                    } catch (IOException ex2) 
                       {
                           lc.write("Error al intentar finalizar el bloque finally ", "SYSCOPY", ex2);
                           b = false;
                       }
                }
        return b;
    }
    
}
