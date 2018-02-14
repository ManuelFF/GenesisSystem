/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker.Core;

import ModuleWorker.IC.MWCON;
import ModuleWorker.SYSAUDIOCON;
import ModuleWorker.SYSCON;
import ModuleWorker.SYSControl;
import ModuleWorker.SYSSCHEDULERCON;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author USUARIO
 */
public class JOB_Update implements Job
{
    
    SYSCON syscon = new SYSCON();
    SYSControl con = new SYSControl();
    MWCON mw = new MWCON();
    SYSAUDIOCON sysau = new SYSAUDIOCON();
    
    JFrame jf = new JFrame();
   
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException
    {
        //AQUI IRA LA LOGICA DE PROGRAMACION PARA DETERMINAR SI HAY UNA     
        //ACTUALIZACION DISPONIBLE 
       
       jf.setAlwaysOnTop(true);
       String verSIS = syscon.Ver_SIS();
       String cargarVER = syscon.cargar_version_texto();
       
       System.out.println("VERSION DE SISTEMA EN BASE : "+verSIS);
       System.out.println("VERSION DE SISTEMA LOCAL : "+cargarVER);
       
       if(verSIS.equals(cargarVER) == false)
       {
           System.out.println("Versiones distintas");
           try 
           {
              SYSSCHEDULERCON SCheduler = new SYSSCHEDULERCON(); //SE ENCARGA DE LAS TAREAS PRE PROGRAMADAs
              SCheduler.stop();
              sysau.E_INFORMATION();
              JOptionPane.showMessageDialog(jf, "Se ha encontrado una nueva Versión de Génesis!\n guarde todo el proceso de trabajo\nEL SISTEMA SE APAGARA DENTRO DE 10 SEGUNDOS");
              comp.start();
           } catch (Exception e) 
              {
                  
              }
           //mw.abrir_actualizador();
           //con.Close_System();
       }else
          {
             if(verSIS.equals(cargarVER) == true)
             {
                 System.out.println("Versiones Iguales");
             }
          }        
    }

    Timer comp = new Timer (10000, new ActionListener () 
     { 
        public void actionPerformed(ActionEvent e) 
        {
            System.out.println("ACTUALIZACIÓN");
            mw.abrir_actualizador();
            con.Close_System();
        } 
     }); 
    
    
    
}
