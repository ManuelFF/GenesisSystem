/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker.View;

import ModuleWorker.DBCON;
import ModuleWorker.SYSCON;
import ModuleWorker.SYSWALLPCON;
import NCLPM.EVENTS;
import NCLPM.LOG;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class JFRPrincipal extends javax.swing.JFrame 
{

    /**
     * Creates new form JFRPrincipal
     */
    
    JFrame form;
    SYSCON sys = new SYSCON();
    LOG lc = new LOG();
    EVENTS evn = new EVENTS();
    public File imagen = new File (System.getProperty ("user.dir")+"\\Fondo.png");

    public JFRPrincipal()
    {
        initComponents();
        image();
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setTitle(sys.nombre_sistema()+" - "+sys.nombre_compañia()+" - Versión V"+sys.version());
    }
    
    private void image()
    {   
        SYSWALLPCON.cargarImagen(JDEscritorio, imagen);
    }

    //ACTUALIZACION
    public void actualizacion()
    {
        //ARGUMENTOS DE ACTUALIZACION
        System.exit(0);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JDEscritorio = new javax.swing.JDesktopPane();
        JTBHerramientas = new javax.swing.JToolBar();
        btnAgregarCliente = new javax.swing.JButton();
        btnAgregarPersonal = new javax.swing.JButton();
        btnGenerarOrden = new javax.swing.JButton();
        btnAdministrarCertificado = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        JMPrincipal = new javax.swing.JMenuBar();
        JMSesion = new javax.swing.JMenu();
        JSMMantenimientoUsuarios = new javax.swing.JMenuItem();
        JSMCerrarSesion = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout JDEscritorioLayout = new javax.swing.GroupLayout(JDEscritorio);
        JDEscritorio.setLayout(JDEscritorioLayout);
        JDEscritorioLayout.setHorizontalGroup(
            JDEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        JDEscritorioLayout.setVerticalGroup(
            JDEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 612, Short.MAX_VALUE)
        );

        JTBHerramientas.setBackground(new java.awt.Color(204, 204, 204));
        JTBHerramientas.setFloatable(false);
        JTBHerramientas.setBorderPainted(false);
        JTBHerramientas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JTBHerramientas.setOpaque(false);

        btnAgregarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Client1_add32.png"))); // NOI18N
        btnAgregarCliente.setToolTipText("Agregar Cliente");
        btnAgregarCliente.setFocusable(false);
        btnAgregarCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAgregarCliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JTBHerramientas.add(btnAgregarCliente);

        btnAgregarPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Male_User_add32.png"))); // NOI18N
        btnAgregarPersonal.setToolTipText("Agregar Personal");
        btnAgregarPersonal.setFocusable(false);
        btnAgregarPersonal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAgregarPersonal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JTBHerramientas.add(btnAgregarPersonal);

        btnGenerarOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/OrdenV2 32.png"))); // NOI18N
        btnGenerarOrden.setToolTipText("Generar Orden de Servicio");
        btnGenerarOrden.setFocusable(false);
        btnGenerarOrden.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGenerarOrden.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JTBHerramientas.add(btnGenerarOrden);

        btnAdministrarCertificado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Certificado32.png"))); // NOI18N
        btnAdministrarCertificado.setToolTipText("Administrar Certificados");
        btnAdministrarCertificado.setFocusable(false);
        btnAdministrarCertificado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdministrarCertificado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JTBHerramientas.add(btnAdministrarCertificado);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/codigo-pin32.png"))); // NOI18N
        jButton4.setToolTipText("Generar Codigos");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JTBHerramientas.add(jButton4);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/ConsultarOrdenV2 32.png"))); // NOI18N
        jButton5.setToolTipText("Consulta de Ordenes de Servicio");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JTBHerramientas.add(jButton5);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/ConsultaCert 32.png"))); // NOI18N
        jButton2.setToolTipText("Consulta de Certificados");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JTBHerramientas.add(jButton2);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/logout-icon32.png"))); // NOI18N
        jButton1.setToolTipText("Cerrar Sesión");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JTBHerramientas.add(jButton1);

        JMSesion.setText("Sesion");
        JMSesion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        JSMMantenimientoUsuarios.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        JSMMantenimientoUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/personal.png"))); // NOI18N
        JSMMantenimientoUsuarios.setText("Mantenimiento de Usuarios");
        JSMMantenimientoUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JSMMantenimientoUsuariosActionPerformed(evt);
            }
        });
        JMSesion.add(JSMMantenimientoUsuarios);

        JSMCerrarSesion.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        JSMCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/logout-icon24.png"))); // NOI18N
        JSMCerrarSesion.setText("Cerrar Sesión");
        JSMCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JSMCerrarSesionActionPerformed(evt);
            }
        });
        JMSesion.add(JSMCerrarSesion);

        JMPrincipal.add(JMSesion);

        jMenu2.setText("Mantenimientos");
        jMenu2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JMPrincipal.add(jMenu2);

        jMenu1.setText("Generar");
        jMenu1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JMPrincipal.add(jMenu1);

        jMenu3.setText("Consultar");
        jMenu3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JMPrincipal.add(jMenu3);

        jMenu4.setText("Administración");
        jMenu4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JMPrincipal.add(jMenu4);

        jMenu5.setText("Ventas");
        jMenu5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JMPrincipal.add(jMenu5);

        setJMenuBar(JMPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JDEscritorio)
            .addComponent(JTBHerramientas, javax.swing.GroupLayout.PREFERRED_SIZE, 1243, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(JTBHerramientas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JDEscritorio))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JSMMantenimientoUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JSMMantenimientoUsuariosActionPerformed

        try
        {
            JIFMatenerUsuario usuarios = new JIFMatenerUsuario();
            if(JSMMantenimientoUsuarios.getActionCommand().equals("Abierto")){JOptionPane.showMessageDialog(this,"Ya esta abierto");}
            else
            {
                JSMMantenimientoUsuarios.setActionCommand("Abierto");
                JDEscritorio.add(usuarios);
                Dimension desktopSize = JDEscritorio.getSize();
                Dimension FrameSize = usuarios.getSize();
                usuarios.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                evn.write(JMSesion.getText(), "Abrio el formulario 'Mantener Usuario'", "JFPrincipal", "Menu 'Mantener Usuario' Presionado");
                usuarios.show();
            }
        }
        catch (Exception e)
        {
            lc.write("Error intentando abrir 'Mantenimiento Usuarios'", "JFRPrincipal", e.getMessage());
        }

    }//GEN-LAST:event_JSMMantenimientoUsuariosActionPerformed

    private void JSMCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JSMCerrarSesionActionPerformed
        try 
        {
  
          System.exit(0);
            
        } catch (Exception sqle) 
            {
               lc.write( "Ha ocurrido algun error al intentar cerrar el sistema!","JFPrincipal metodo JSMCerrar Sesión Linea 255", sqle.getMessage());
            }

    }//GEN-LAST:event_JSMCerrarSesionActionPerformed

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
            java.util.logging.Logger.getLogger(JFRPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFRPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFRPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFRPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new JFRPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane JDEscritorio;
    private javax.swing.JMenuBar JMPrincipal;
    public static javax.swing.JMenu JMSesion;
    private javax.swing.JMenuItem JSMCerrarSesion;
    public static javax.swing.JMenuItem JSMMantenimientoUsuarios;
    private javax.swing.JToolBar JTBHerramientas;
    private javax.swing.JButton btnAdministrarCertificado;
    private javax.swing.JButton btnAgregarCliente;
    private javax.swing.JButton btnAgregarPersonal;
    private javax.swing.JButton btnGenerarOrden;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    // End of variables declaration//GEN-END:variables
}
