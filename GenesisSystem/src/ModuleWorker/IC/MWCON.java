/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker.IC;

import NCLPM.LOG;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author USUARIO
 */
public class MWCON 
{
    //puentes
    LOG lc = new LOG(); //puente apuntando a la clase LOG
    
    //Metodo que abre un archivo en la raiz con el nombre *arhivo
    public void abrir_archivo(String archivo)
    {
        try 
        {
            File ObjectoFile = new File(archivo);
            Desktop.getDesktop().open(ObjectoFile);
        } catch (IOException ioex) 
            {
                lc.write("Error al intentar abrir el archivo "+archivo, "MWCON", ioex.getMessage());
            }
    }
    
    public void abrir_actualizador()
    {
        abrir_archivo("Update.jar");
    }
    
    
    
}
