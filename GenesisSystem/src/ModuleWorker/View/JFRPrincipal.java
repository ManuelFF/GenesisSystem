/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker.View;

import ModuleWorker.Control;
import ModuleWorker.IC.MWCON;
import ModuleWorker.SYSAUDIOCON;
import ModuleWorker.SYSCON;
import ModuleWorker.SYSWALLPCON;
import NCLPM.EVENTS;
import NCLPM.LOG;
import NMOC.MD_Consultar_View.JIFConsultarOrdenesTrabajo;
import NMOC.MD_Generar.View.JIFGenerarCotizacion;
import NMOC.MD_Generar.View.JIFGenerarOrdenTrabajo;
import NMOC.MD_Mantenimientos.View.JIFMantenerClientes;
import NMOC.MD_Mantenimientos.View.JIFMantenerImplementos;
import NMOC.MD_Mantenimientos.View.JIFMantenerPersonal;
import NMOC.MD_Mantenimientos.View.JIFMantenerProductos;
import java.awt.Dimension;
import java.io.File;
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
        this.setTitle(sys.nombre_sistema()+" - "+sys.nombre_compañia()+" - Versión V"+sys.version()+" VERSIÓN INTERNA DE DESARROLLO");
        
        detectar();
    }
    
    private static void detectar()
    {
        try 
        {
            JFrame jf=new JFrame();
            jf.setAlwaysOnTop(true);
            JDMarcarAsistencia jd = new JDMarcarAsistencia(jf, true);
            jd.setVisible(true);
            
            
        } catch (Exception e) 
            {
                
            }
    }
    
    private void image()
    {   
        SYSWALLPCON.cargarImagen(JDEscritorio, imagen);
    }

    //ACTUALIZACION
    @Deprecated
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
        jButton3 = new javax.swing.JButton();
        JMPrincipal = new javax.swing.JMenuBar();
        JMSesion = new javax.swing.JMenu();
        JSMMantenimientoUsuarios = new javax.swing.JMenuItem();
        JSMPermisosUsuarios = new javax.swing.JMenuItem();
        JSMCerrarSesion = new javax.swing.JMenuItem();
        JMMantenimientos = new javax.swing.JMenu();
        JSMMantenerClientes = new javax.swing.JMenuItem();
        JSMMantenerPersonal = new javax.swing.JMenuItem();
        JSMMantenerProducto = new javax.swing.JMenuItem();
        JSMMantenerImplementos = new javax.swing.JMenuItem();
        JMGenerar = new javax.swing.JMenu();
        JSMGenerarCotizacion = new javax.swing.JMenuItem();
        JSMGenerarOrden = new javax.swing.JMenuItem();
        JMConsultar = new javax.swing.JMenu();
        JSMConsultarOrden = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        javax.swing.GroupLayout JDEscritorioLayout = new javax.swing.GroupLayout(JDEscritorio);
        JDEscritorio.setLayout(JDEscritorioLayout);
        JDEscritorioLayout.setHorizontalGroup(
            JDEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1243, Short.MAX_VALUE)
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

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 51, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Notification_32.png"))); // NOI18N
        jButton3.setText("0");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JTBHerramientas.add(jButton3);

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

        JSMPermisosUsuarios.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        JSMPermisosUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Lock-Unlock-icon.png"))); // NOI18N
        JSMPermisosUsuarios.setText("Permisos de Usuarios");
        JSMPermisosUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JSMPermisosUsuariosActionPerformed(evt);
            }
        });
        JMSesion.add(JSMPermisosUsuarios);

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

        JMMantenimientos.setText("Mantenimientos");
        JMMantenimientos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        JSMMantenerClientes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        JSMMantenerClientes.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        JSMMantenerClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Client1_add24.png"))); // NOI18N
        JSMMantenerClientes.setText("Mantener Clientes");
        JSMMantenerClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JSMMantenerClientesActionPerformed(evt);
            }
        });
        JMMantenimientos.add(JSMMantenerClientes);

        JSMMantenerPersonal.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        JSMMantenerPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Male_User_add24.png"))); // NOI18N
        JSMMantenerPersonal.setText("Mantener Personal");
        JSMMantenerPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JSMMantenerPersonalActionPerformed(evt);
            }
        });
        JMMantenimientos.add(JSMMantenerPersonal);

        JSMMantenerProducto.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        JSMMantenerProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/ProductsV2_24.png"))); // NOI18N
        JSMMantenerProducto.setText("Mantener Producto");
        JSMMantenerProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JSMMantenerProductoActionPerformed(evt);
            }
        });
        JMMantenimientos.add(JSMMantenerProducto);

        JSMMantenerImplementos.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        JSMMantenerImplementos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/machinesV1.png"))); // NOI18N
        JSMMantenerImplementos.setText("Mantener Implementos");
        JSMMantenerImplementos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JSMMantenerImplementosActionPerformed(evt);
            }
        });
        JMMantenimientos.add(JSMMantenerImplementos);

        JMPrincipal.add(JMMantenimientos);

        JMGenerar.setText("Generar");
        JMGenerar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        JSMGenerarCotizacion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        JSMGenerarCotizacion.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        JSMGenerarCotizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Files-New-File-icon.png"))); // NOI18N
        JSMGenerarCotizacion.setText("Generar Cotización");
        JSMGenerarCotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JSMGenerarCotizacionActionPerformed(evt);
            }
        });
        JMGenerar.add(JSMGenerarCotizacion);

        JSMGenerarOrden.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        JSMGenerarOrden.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        JSMGenerarOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/OrdenV2_1 24.png"))); // NOI18N
        JSMGenerarOrden.setText("Generar Orden Trabajo");
        JSMGenerarOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JSMGenerarOrdenActionPerformed(evt);
            }
        });
        JMGenerar.add(JSMGenerarOrden);

        JMPrincipal.add(JMGenerar);

        JMConsultar.setText("Consultar");
        JMConsultar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        JSMConsultarOrden.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        JSMConsultarOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/ConsultarOrdenV2 24_1.png"))); // NOI18N
        JSMConsultarOrden.setText("Consultar Orden Trabajo");
        JSMConsultarOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JSMConsultarOrdenActionPerformed(evt);
            }
        });
        JMConsultar.add(JSMConsultarOrden);

        JMPrincipal.add(JMConsultar);

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
            .addComponent(JTBHerramientas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            JFrame jf=new JFrame();
            jf.setAlwaysOnTop(true);

            if(JSMMantenimientoUsuarios.getActionCommand().equals("Abierto"))
                {
                    SYSAUDIOCON sysau = new SYSAUDIOCON();
                    sysau.E_ERROR();
                    JOptionPane.showMessageDialog(jf,"Ya esta abierto", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }
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
            
            lc.write("Error intentando abrir 'Mantenimiento Usuarios'", "JFRPrincipal", e);
        }

    }//GEN-LAST:event_JSMMantenimientoUsuariosActionPerformed

    private void JSMCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JSMCerrarSesionActionPerformed
        try 
        {
          evn.write(JMSesion.getText(), "Ha cerrado sesión y salio del sistema", "JFRPrincipal", "Botón 'Cerrar Sesión' Presionado");
          new Control().cerrarApp();
          System.exit(0);
            
        } catch (Exception sqle) 
            {
               lc.write( "Ha ocurrido algun error al intentar cerrar el sistema!","JFPrincipal metodo JSMCerrar Sesión Linea 255", sqle);
            }

    }//GEN-LAST:event_JSMCerrarSesionActionPerformed

    private void JSMMantenerClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JSMMantenerClientesActionPerformed
        
        try
        {
            JIFMantenerClientes clientes = new JIFMantenerClientes();
            JFrame jf=new JFrame();
            jf.setAlwaysOnTop(true);

            if(JSMMantenerClientes.getActionCommand().equals("Abierto"))
                {
                    SYSAUDIOCON sysau = new SYSAUDIOCON();
                    sysau.E_ERROR();
                    JOptionPane.showMessageDialog(jf,"Ya esta abierto", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }
            else
            {                
                JSMMantenerClientes.setActionCommand("Abierto");
                JDEscritorio.add(clientes);
                Dimension desktopSize = JDEscritorio.getSize();
                Dimension FrameSize = clientes.getSize();
                clientes.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                evn.write(JMSesion.getText(), "Abrio el formulario 'Mantener Clientes'", "JFPrincipal", "Menu 'Mantener Clientes' Presionado");
                clientes.show();
            }
        }
        catch (Exception e)
        {
            
            lc.write("Error intentando abrir 'Mantenimiento Clientes'", "JFRPrincipal", e);
        }
        
    }//GEN-LAST:event_JSMMantenerClientesActionPerformed

    private void JSMPermisosUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JSMPermisosUsuariosActionPerformed

    try
    {
       JIFPermisos permisos = new JIFPermisos();
       JFrame jf=new JFrame();
       jf.setAlwaysOnTop(true);

       if(JSMPermisosUsuarios.getActionCommand().equals("Abierto"))
           {
               SYSAUDIOCON sysau = new SYSAUDIOCON();
               sysau.E_ERROR();
               JOptionPane.showMessageDialog(jf,"Ya esta abierto", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
           }
       else
       {                
           JSMPermisosUsuarios.setActionCommand("Abierto");
           JDEscritorio.add(permisos);
           Dimension desktopSize = JDEscritorio.getSize();
           Dimension FrameSize = permisos.getSize();
           permisos.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
           evn.write(JMSesion.getText(), "Abrio el formulario 'Permisos'", "JFPrincipal", "Menu 'Permisos' Presionado");
           permisos.show();
       }
    }
   catch (Exception e)
   {

       lc.write("Error intentando abrir 'Permisos'", "JFRPrincipal", e);
   }

    }//GEN-LAST:event_JSMPermisosUsuariosActionPerformed

    private void JSMMantenerPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JSMMantenerPersonalActionPerformed
        
    try
    {
        JIFMantenerPersonal personal = new JIFMantenerPersonal();
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);

        if(JSMMantenerPersonal.getActionCommand().equals("Abierto"))
            {
                SYSAUDIOCON sysau = new SYSAUDIOCON();
                sysau.E_ERROR();
                JOptionPane.showMessageDialog(jf,"Ya esta abierto", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
        else
        {                
            JSMMantenerPersonal.setActionCommand("Abierto");
            JDEscritorio.add(personal);
            Dimension desktopSize = JDEscritorio.getSize();
            Dimension FrameSize = personal.getSize();
            personal.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
            evn.write(JMSesion.getText(), "Abrio el formulario 'Mantener Personal'", "JFPrincipal", "Menu 'Mantener Personal' Presionado");
            personal.show();
        }
    }
    catch (Exception e)
    {

        lc.write("Error intentando abrir 'Mantenimiento Personal'", "JFRPrincipal", e);
    }
        
    }//GEN-LAST:event_JSMMantenerPersonalActionPerformed

    private void JSMMantenerProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JSMMantenerProductoActionPerformed

    try
    {
        JIFMantenerProductos producto = new JIFMantenerProductos();
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);

        if(JSMMantenerProducto.getActionCommand().equals("Abierto"))
            {
                SYSAUDIOCON sysau = new SYSAUDIOCON();
                sysau.E_ERROR();
                JOptionPane.showMessageDialog(jf,"Ya esta abierto", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
        else
        {                
            JSMMantenerProducto.setActionCommand("Abierto");
            JDEscritorio.add(producto);
            Dimension desktopSize = JDEscritorio.getSize();
            Dimension FrameSize = producto.getSize();
            producto.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
            evn.write(JMSesion.getText(), "Abrio el formulario 'Mantener Producto'", "JFPrincipal", "Menu 'Mantener Producto' Presionado");
            producto.show();
        }
    }
    catch (Exception e)
    {

        lc.write("Error intentando abrir 'Mantenimiento Productos'", "JFRPrincipal", e);
    }

        
    }//GEN-LAST:event_JSMMantenerProductoActionPerformed

    private void JSMMantenerImplementosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JSMMantenerImplementosActionPerformed

    try
    {
        JIFMantenerImplementos producto = new JIFMantenerImplementos();
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);

        if(JSMMantenerImplementos.getActionCommand().equals("Abierto"))
            {
                SYSAUDIOCON sysau = new SYSAUDIOCON();
                sysau.E_ERROR();
                JOptionPane.showMessageDialog(jf,"Ya esta abierto", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
        else
        {                
            JSMMantenerImplementos.setActionCommand("Abierto");
            JDEscritorio.add(producto);
            Dimension desktopSize = JDEscritorio.getSize();
            Dimension FrameSize = producto.getSize();
            producto.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
            evn.write(JMSesion.getText(), "Abrio el formulario 'Mantener Implementos'", "JFPrincipal", "Menu 'Mantener Implementos' Presionado");
            producto.show();
        }
    }
    catch (Exception e)
    {

        lc.write("Error intentando abrir 'Mantenimiento Implementos'", "JFRPrincipal", e);
    }

    }//GEN-LAST:event_JSMMantenerImplementosActionPerformed

    private void JSMGenerarCotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JSMGenerarCotizacionActionPerformed
        
    try
    {
        JIFGenerarCotizacion generar_coti = new JIFGenerarCotizacion();
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);

        if(JSMGenerarCotizacion.getActionCommand().equals("Abierto"))
            {
                SYSAUDIOCON sysau = new SYSAUDIOCON();
                sysau.E_ERROR();
                JOptionPane.showMessageDialog(jf,"Ya esta abierto", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
        else
        {                
            JSMGenerarCotizacion.setActionCommand("Abierto");
            JDEscritorio.add(generar_coti);
            Dimension desktopSize = JDEscritorio.getSize();
            Dimension FrameSize = generar_coti.getSize();
            generar_coti.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
            evn.write(JMSesion.getText(), "Abrio el formulario 'Generar Cotización'", "JFPrincipal", "Menu 'Generar Cotización' Presionado");
            generar_coti.show();
        }
    }
    catch (Exception e)
    {

        lc.write("Error intentando abrir 'Generar Cotización'", "JFRPrincipal", e);
    }     

    }//GEN-LAST:event_JSMGenerarCotizacionActionPerformed

    private void JSMGenerarOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JSMGenerarOrdenActionPerformed

    try
    {
        JIFGenerarOrdenTrabajo orden = new JIFGenerarOrdenTrabajo();
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);

        if(JSMGenerarOrden.getActionCommand().equals("Abierto"))
            {
                SYSAUDIOCON sysau = new SYSAUDIOCON();
                sysau.E_ERROR();
                JOptionPane.showMessageDialog(jf,"Ya esta abierto", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
        else
        {                
            JSMGenerarOrden.setActionCommand("Abierto");
            JDEscritorio.add(orden);
            Dimension desktopSize = JDEscritorio.getSize();
            Dimension FrameSize = orden.getSize();
            orden.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
            evn.write(JMSesion.getText(), "Abrio el formulario 'Generar Orden Servicio'", "JFPrincipal", "Menu 'Generar Orden Servicio' Presionado");
            orden.show();
        }
    }
    catch (Exception e)
    {

        lc.write("Error intentando abrir 'Generar Orden Servicio'", "JFRPrincipal", e);
    }     

    }//GEN-LAST:event_JSMGenerarOrdenActionPerformed

    private void JSMConsultarOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JSMConsultarOrdenActionPerformed

    try
      {
          JIFConsultarOrdenesTrabajo orden = new JIFConsultarOrdenesTrabajo();
          JFrame jf=new JFrame();
          jf.setAlwaysOnTop(true);

          if(JMConsultar.getActionCommand().equals("Abierto"))
              {
                  SYSAUDIOCON sysau = new SYSAUDIOCON();
                  sysau.E_ERROR();
                  JOptionPane.showMessageDialog(jf,"Ya esta abierto", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
              }
          else
          {                
              JMConsultar.setActionCommand("Abierto");
              JDEscritorio.add(orden);
              Dimension desktopSize = JDEscritorio.getSize();
              Dimension FrameSize = orden.getSize();
              orden.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
              evn.write(JMSesion.getText(), "Abrio el formulario 'Consultar Orden Trabajo'", "JFPrincipal", "Menu 'Consultar Orden Trabajo' Presionado");
              orden.show();
          }
      }
      catch (Exception e)
      {

          lc.write("Error intentando abrir 'Consultar Orden Trabajo'", "JFRPrincipal", e);
      }     

    }//GEN-LAST:event_JSMConsultarOrdenActionPerformed

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
    public static javax.swing.JDesktopPane JDEscritorio;
    public static javax.swing.JMenu JMConsultar;
    private javax.swing.JMenu JMGenerar;
    private javax.swing.JMenu JMMantenimientos;
    private javax.swing.JMenuBar JMPrincipal;
    public static javax.swing.JMenu JMSesion;
    private javax.swing.JMenuItem JSMCerrarSesion;
    public static javax.swing.JMenuItem JSMConsultarOrden;
    public static javax.swing.JMenuItem JSMGenerarCotizacion;
    public static javax.swing.JMenuItem JSMGenerarOrden;
    public static javax.swing.JMenuItem JSMMantenerClientes;
    public static javax.swing.JMenuItem JSMMantenerImplementos;
    public static javax.swing.JMenuItem JSMMantenerPersonal;
    public static javax.swing.JMenuItem JSMMantenerProducto;
    public static javax.swing.JMenuItem JSMMantenimientoUsuarios;
    public static javax.swing.JMenuItem JSMPermisosUsuarios;
    private javax.swing.JToolBar JTBHerramientas;
    private javax.swing.JButton btnAdministrarCertificado;
    private javax.swing.JButton btnAgregarCliente;
    private javax.swing.JButton btnAgregarPersonal;
    private javax.swing.JButton btnGenerarOrden;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    // End of variables declaration//GEN-END:variables
}
