/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker;

import NCLPM.EVENTS;
import NCLPM.LOG;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

/**
 *
 * @author USUARIO
 */
public class SYSAUDIOCON
{
    LOG lc = new LOG();
    EVENTS evn = new EVENTS();
    
    Clip sonido;
    
    public SYSAUDIOCON()
    {
        try
        {
            sonido = AudioSystem.getClip();
        } catch (LineUnavailableException ex) 
            {
                lc.write("Error al intentar inicializar el constructor de sonidos", "SYSAUDIOCON linea 28", ex.getMessage());
            }
    }
    
    public void E_ERROR()
    {
        try 
        {
            sonido.open(AudioSystem.getAudioInputStream(new File("ERROR.wav")));
            sonido.start();
          
        } catch (Exception e) 
            {
                lc.write("Error al intentar reproducir el efecto 'ERROR.wav'", "SYSAUDIOCON linea 41", e.getMessage());
            }       
    }
    
    public void E_CERRAR_SESION()
    {
        try 
        {
            sonido.open(AudioSystem.getAudioInputStream(new File("CERRAR_SESION.wav")));
            sonido.start();
          
        } catch (Exception e) 
            {
                lc.write("Error al intentar reproducir el efecto 'ERROR.wav'", "SYSAUDIOCON linea 41", e.getMessage());
            }
    }
    

}
