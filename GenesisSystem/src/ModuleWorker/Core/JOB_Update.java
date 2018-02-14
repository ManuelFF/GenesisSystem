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
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    private static javax.swing.JLabel lblnoti;
    SYSCON syscon = new SYSCON();
    SYSControl con = new SYSControl();
    MWCON mw = new MWCON();
    SYSAUDIOCON sysau = new SYSAUDIOCON();
    
    JFrame jf = new JFrame();

    String estado;
    int cnt=101;
    
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
              JOptionPane.showMessageDialog(jf, "Se ha encontrado una nueva Versión de Génesis!\n guarde todo el proceso de trabajo\nEL SISTEMA SE APAGARA DENTRO DE 100 SEGUNDOS");
              estado= "1";
              notificacion();
              comp.start();
              cont.start();
              
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

    
    Timer comp = new Timer (101000, new ActionListener () 
     { 
        public void actionPerformed(ActionEvent e) 
        {
            System.out.println("ACTUALIZACIÓN");
            sysau.E_CERRAR_SESION();
            estado = "0";
            comp.stop();
            mw.abrir_actualizador();
            con.Close_System();
        } 
     }); 
        
    Timer cont = new Timer (1000, new ActionListener () 
     { 
        public void actionPerformed(ActionEvent e) 
        {
            cnt = cnt-1;
            System.out.println("RES : "+cnt);
            lblnoti.setText("<html><body><CENTER>Se cerrará en..<br>"+cnt+"</CENTER></body></html>");
            if(estado.equals("0"))
            {
                cont.stop();
            }
        } 
     }); 
    
    public void notificacion()
    {
        final JFrame parent = new JFrame();
        
        parent.setUndecorated(true);
       
        lblnoti = new JLabel("<html><body><CENTER>Se cerrará en..<br>100</CENTER></body></html>");
        
        lblnoti.setFont(new java.awt.Font("Tahoma", 1, 35)); // NOI18N
        lblnoti.setForeground(new java.awt.Color(0, 0, 255));
        lblnoti.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        parent.setAlwaysOnTop(true);
        parent.setDefaultCloseOperation(0);
        parent.setSize(800, 600);
        
        parent.setResizable(false);
        parent.add(lblnoti);
        parent.pack();
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        
        int x = (int) rect.getMaxX() - parent.getWidth();
        int y = (int) rect.getMaxY() - parent.getHeight();
        
        int yx = (int) rect.getMaxY() - y ;
        
        System.out.println("X : "+x);
        System.out.println("Y : "+y);
        System.out.println("yx : "+yx);
        
        parent.setLocation(x, yx);
        parent.setVisible(true);
        
        
    }
    
    
}
