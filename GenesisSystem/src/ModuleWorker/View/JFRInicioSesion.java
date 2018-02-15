/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker.View;

import ModuleWorker.IC.MICROCON_InicioSesion;
import ModuleWorker.IC.MWCON;
import ModuleWorker.IC.NANOCON_Asistencia;
import ModuleWorker.IC.ShakingFrame;
import ModuleWorker.SYSAUDIOCON;
import ModuleWorker.SYSCON;
import ModuleWorker.SYSControl;
import ModuleWorker.SYSFRMCON;
import NCLPM.LOG;
import NCLPM.EVENTS;
import NCLPM.GEN_STATUS;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author USUARIO
 */
public class JFRInicioSesion extends javax.swing.JFrame 
{

    /**
     * Creates new form JFRLogin
     */
    
    LOG lc = new LOG();
    GEN_STATUS gnst = new GEN_STATUS();
    SYSFRMCON sysfrm = new SYSFRMCON();
    ShakingFrame s = new ShakingFrame(this); //SHAKING
    SYSAUDIOCON sysau = new SYSAUDIOCON(); //EFECTOS
    
    public JFRInicioSesion() 
    {
        initComponents();
        this.setTitle(sysfrm.T_login());
        sysfrm.B_login(this.getContentPane());
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);

        comprobar();
    }

    private void comprobar()
    {
       SYSCON syscon = new SYSCON();
       SYSControl con = new SYSControl();
       MWCON mw = new MWCON();
       
       String verSIS = syscon.Ver_SIS();
       String cargarVER = syscon.cargar_version_texto();
       
       if(verSIS.equals(cargarVER) == false)
       {
           JFrame jf = new JFrame();
           jf.setAlwaysOnTop(true);

           sysau.E_INFORMATION();

           JOptionPane.showMessageDialog(jf, "Existe una nueva versión con muchas más Caracteristicas!", "Nueva Versión de Génesis Disponible!", JOptionPane.INFORMATION_MESSAGE);
           
           System.out.println("Versiones distintas");
           
           mw.abrir_actualizador();
           con.Close_System();
           
       }else
          {
             if(verSIS.equals(cargarVER) == true)
             {
                 control_impl();
                 System.out.println("Versiones Iguales");
             }
          }
    }
    
    private void control_impl()
    {
        SYSControl con = new SYSControl();
        if(con.comprobar() == 3 )
        {
            
        }else
        {
            sysau.E_CRITICAL_ERROR();                
             try
             {
                JFrame jf=new JFrame();
                jf.setAlwaysOnTop(true); 

               // display the showOptionDialog
                Object[] options = { "RECUPERAR", "SALIR"};
                int choice = JOptionPane.showOptionDialog(jf, 
                    "                 El sistema ya esta en ejecución!\n"
                            + "        Si el sistema no esta en ejecucion intente\nla opción 'RECUPERAR' y vuelva a iniciar el sistema\n", 
                    "ERROR EN LA CREACIÓN DE LA INSTANCIA", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.ERROR_MESSAGE, 
                    null, 
                    options, 
                    options[0]);

                // interpret the user's choice
                if (choice == 0)
                {
                    con.write("2");
                }

                if (choice == 1)
                {
                      System.exit(0);
                }
                
            }catch(Exception e){lc.write("Problema al intentar crear una instancia del sistema", "control.java", e);}
        }
    }
    
     private void detectar(String NOMC)
    {
        try 
        {
            //INICIO ANTES QUE NADA
            NANOCON_Asistencia P_asist = new NANOCON_Asistencia();
            MWCON mw = new MWCON();
            
            JFrame jf=new JFrame();
            jf.setAlwaysOnTop(true);
            
            String ID_PER = P_asist.obtenerIDNomCOn(NOMC);
            
            if(P_asist.obtenerEstadoAsistencia(mw.fecha_actual_clasica(),ID_PER).equals("ENTRADA"))
            {
                
            }else
                {
                   if(P_asist.obtenerEstadoAsistencia(mw.fecha_actual_clasica(),ID_PER).equals("SALIDA"))
                   {
                       
                   }
                   else
                       {
                         JDMarcarAsistencia jd = new JDMarcarAsistencia(jf, true,ID_PER);
                         jd.setVisible(true);
                         this.dispose();
                       }
                }
                
                gnst.write_CON(NOMC, mw.hour_actual(), mw.fecha_actual(), "NORMAL");
                
                JFRPrincipal principal = new JFRPrincipal();
                JFRPrincipal.JMSesion.setText(NOMC);
                JFRPrincipal.detectar(ID_PER);
                sysfrm.B_JMSesion(JFRPrincipal.JMSesion);
                principal.setVisible(true);
                this.dispose();
            
        } catch (Exception e) 
            {
                lc.write("Algun error ha ocurrido al intentar detectar el estado de la asistencia ", "Clase Inicio Sesion", e);
            }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnIngresar = new javax.swing.JButton();
        lblimage = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        pswPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        btnIngresar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Accept-icon24.png"))); // NOI18N
        btnIngresar.setText("INGRESAR");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        lblimage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Login_Image.jpg"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Génesis Inicio de Sesión");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("USUARIO:");

        txtusuario.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("CONTRASEÑA:");

        btnSalir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/logout-icon24.png"))); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        pswPassword.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(txtusuario, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(pswPassword))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnIngresar))
                        .addGap(79, 79, 79)))
                .addComponent(lblimage)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(pswPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblimage)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
       
    int intentos = 3;

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        
  try 
    {
       
        MICROCON_InicioSesion Minse = new MICROCON_InicioSesion();

        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);
        
        String usr = txtusuario.getText();
        String psw = DigestUtils.md5Hex(pswPassword.getText());
                
       if(intentos<=0)
       {
           s.startShake();
           SYSAUDIOCON sysau = new SYSAUDIOCON();
           sysau.E_CRITICAL_ERROR();
           JOptionPane.showMessageDialog(jf, "Se quedo sin intentos\nEL SISTEMA SE CERRARA!", "Sobrepaso limite de intentos", JOptionPane.ERROR_MESSAGE);
           //evn.write("Aún no definido", "Intento entrar al sistema pero ya no tenia intentos", "JFRInicioSesion", "FUE EXPULSADO");
           new SYSControl().Close_System();
       }
       else
       {//ELSE INTENTOS    
        if(usr.trim().isEmpty())
        {
            s.startShake();
            intentos--;
            SYSAUDIOCON sysau = new SYSAUDIOCON();
            sysau.E_ERROR();
            JOptionPane.showMessageDialog(jf, "Ingrese un usuario valido"+"\nINTENTOS RESTANTES : "+intentos, "Error de usuario", JOptionPane.ERROR_MESSAGE);
        }else if(pswPassword.getText().trim().isEmpty())
        {
            s.startShake();
            intentos--;
            SYSAUDIOCON sysau = new SYSAUDIOCON();
            sysau.E_ERROR();
            JOptionPane.showMessageDialog(jf, "Ingrese una contraseña valida"+"\nINTENTOS RESTANTES : "+intentos, "Contraseña invalida", JOptionPane.ERROR_MESSAGE);
        }else
        {//INICIO ELSE GENERAL
            
            if(Minse.validarUsuario(usr, psw).equals("0"))
            {
              s.startShake();
              intentos--;  
              SYSAUDIOCON sysau = new SYSAUDIOCON();
              sysau.E_ERROR();
              JOptionPane.showMessageDialog(jf, "El usuario no existe"+"\nINTENTOS RESTANTES : "+intentos, "Usuario Inexistente", JOptionPane.ERROR_MESSAGE);
            }else
                {
                    if(Minse.validarUsuario(usr, psw).equals("1"))
                    {
                        sysau.E_INICIAR_SESION();
                        Thread.sleep(220);

                        String nombre = Minse.obtenerNombreUSR(usr);
                        
                        detectar(nombre);

                        //tipo
                        //estadoEntrada
                        //EstadoSalida
                        //ComprobarAsistencia
                    }
                }
                        
        }//FIN ELSE GENERAL
       }//ELSE INTENTOS
       
  } catch (Exception e) 
     {
         lc.write("No se ha podido ingresar al sistema debido a un error", "JFRInicioSesion metodo ingresar linea 157", e);
     }
     
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
     
        try 
        {
            new SYSControl().Close_System();
        } catch (Exception e) 
            {
               lc.write("No se pudo salir del sistema", "JFRInicioSesion", e);
            }
        
    }//GEN-LAST:event_btnSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFRInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFRInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFRInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFRInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new JFRInicioSesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblimage;
    private javax.swing.JPasswordField pswPassword;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
