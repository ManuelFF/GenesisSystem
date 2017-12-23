/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker;

import NCLPM.EVENTS;
import NCLPM.LOG;
import java.io.File;
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
            sonido.close();
            
        } catch (LineUnavailableException ex) 
            {
                lc.write("Error al intentar inicializar el constructor de sonidos", "SYSAUDIOCON linea 28", ex);
            }
    }
    
    public void E_ERROR()
    {
        try 
        {
            sonido.open(AudioSystem.getAudioInputStream(new File("./WA_EF/ERROR.wav")));
            sonido.start();
          
        } catch (Exception e) 
            {
                lc.write("Error al intentar reproducir el efecto 'ERROR.wav'", "SYSAUDIOCON linea 44", e);
            }       
    }
    
    public void E_CRITICAL_ERROR()
    {
        try 
        {
            sonido.open(AudioSystem.getAudioInputStream(new File("./WA_EF/CRITICAL_ERROR.wav")));
            sonido.start();
            
        } catch (Exception e) 
            {
               lc.write("Error al intentar reproducir el efecto 'CRITICAL_ERROR.wav'", "SYSAUDIOCON linea 57", e);
            }
    }
    
    
    public void E_CERRAR_SESION()
    {
        try 
        {
            sonido.open(AudioSystem.getAudioInputStream(new File("./WA_EF/CERRAR_SESION.wav")));
            sonido.start();
          
        } catch (Exception e) 
            {
                lc.write("Error al intentar reproducir el efecto 'CERRAR_SESION.wav'", "SYSAUDIOCON linea 71", e);
            }
    }
    
    public void E_INICIAR_SESION()
    {
        try 
        {
            sonido.open(AudioSystem.getAudioInputStream(new File("./WA_EF/INICIAR_SESION.wav")));
            sonido.start();
          
        } catch (Exception e) 
            {
                lc.write("Error al intentar reproducir el efecto 'INICIAR_SESION.wav'", "SYSAUDIOCON linea 84", e);
            }
    }
    
    public void E_CLICK()
    {
        try 
        {
            sonido.open(AudioSystem.getAudioInputStream(new File("./WA_EF/CLICK.wav")));
            sonido.start();
          
        } catch (Exception e) 
            {
                lc.write("Error al intentar reproducir el efecto 'CLICK.wav'", "SYSAUDIOCON linea 97", e);
            }
    }    
    
    public void E_PUSH()
    {
        try 
        {
            sonido.open(AudioSystem.getAudioInputStream(new File("./WA_EF/PUSH.wav")));
            sonido.start();
          
        } catch (Exception e) 
            {
                lc.write("Error al intentar reproducir el efecto 'PUSH.wav'", "SYSAUDIOCON linea 110", e);
            }
    }    
    
    public void E_INFORMATION()
    {
        try 
        {
            sonido.open(AudioSystem.getAudioInputStream(new File("./WA_EF/INFORMATION.wav")));
            sonido.start();
          
        } catch (Exception e) 
            {
                lc.write("Error al intentar reproducir el efecto 'INFORMATION.wav'", "SYSAUDIOCON linea 123", e);
            }
    } 
    
    public void E_NOTIFY()
    {
        try 
        {
            sonido.open(AudioSystem.getAudioInputStream(new File("./WA_EF/NOTIFY.wav")));
            sonido.start();
          
        } catch (Exception e) 
            {
                lc.write("Error al intentar reproducir el efecto 'NOTIFY.wav'", "SYSAUDIOCON linea 136", e);
            }
    } 
    
    public void E_EXCLAMATION()
    {
        try 
        {
            sonido.open(AudioSystem.getAudioInputStream(new File("./WA_EF/EXCLAMATION.wav")));
            sonido.start();
          
        } catch (Exception e) 
            {
                lc.write("Error al intentar reproducir el efecto 'EXCLAMATION.wav'", "SYSAUDIOCON linea 149", e);
            }
    }
}
