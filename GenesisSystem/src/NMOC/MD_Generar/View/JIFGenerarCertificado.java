/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Generar.View;

import IACore.JTACON;
import ModuleWorker.IC.MWCON;
import ModuleWorker.SYSAUDIOCON;
import ModuleWorker.SYSCOPY;
import ModuleWorker.SYSFRMCON;
import ModuleWorker.View.JFRPrincipal;
import NCLPM.EVENTS;
import NCLPM.LOG;
import NCLPM.RESULTS;
import NMOC.GL_JDBuscarCertificados;
import NMOC.GL_JDBuscarCliente;
import NMOC.GL_JDBuscarOrdenes;
import NMOC.GL_JDCalendar;
import NMOC.MD_Generar.Core.NOB_Certificado;
import NMOC.MD_Generar.IC.MICROCON_Certificado;
import java.awt.Color;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author USUARIO
 */
public class JIFGenerarCertificado extends javax.swing.JInternalFrame 
{

    /**
     * Creates new form JIFGenerarCertificado
     */
    
    LOG lc = new LOG();
    EVENTS evn = new EVENTS();
    RESULTS rslt = new RESULTS();
    SYSFRMCON sysfrm = new SYSFRMCON();
    MWCON mw = new MWCON();
    JFrame form;
    JTACON JTC = new JTACON();
    Color ColorInicial;
    //CONTROLADOR
    MICROCON_Certificado P_cert = new MICROCON_Certificado();
    
    public static String adjunt;
    JFileChooser abrirArchivo;
    
    public JIFGenerarCertificado() 
    {
        initComponents();
        this.setTitle(sysfrm.T_Certificado());
        sysfrm.B_Certificado(this.getContentPane());
        lblusuario.setText(JFRPrincipal.JMSesion.getText());
        lbldate.setText(mw.fecha_actual());
        ColorInicial = txtrazonSocial.getBackground();
        JTADET.setLineWrap(true);
        JTADIRECCION.setLineWrap(true);
        JTAIA.setLineWrap(true);
    }
    
    private void clearCacheDB()
    {
        try 
        {
          P_cert.Clear();
          P_cert.llenarDatos();
            
        } catch (Exception e) 
            {
                lc.write("Error al intentar borrar la cache de la DB", "JIFGenerarCertificado metodo clearcacheDB linea 53", e);
            }
    }
    
    private void Nuevo()
    {
     clearCacheDB();
     String cod = String.format("%03d", 1);
     int codigo;
     NOB_Certificado ultObjeto = null;

     if(P_cert.tamaño()==0)
     {
        cod="CERT-"+cod;
     }
     else
        {
        ultObjeto=P_cert.obtener(P_cert.tamaño()-1);

        cod = ultObjeto.getId_cert().substring(5);
        codigo = Integer.parseInt(cod)+1;
        cod="CERT-"+String.format("%03d",codigo);    
        }txtidCert.setText(cod);
    }
    
    private void clear_frm()
    {
        txtidCert.setText("");
        txtnumeroCert.setText("");
        cbestado.setSelectedIndex(0);
        cbformato.setSelectedIndex(0);
        txtrazonSocial.setText("");
        txtgiro.setText("");
        txtareatotal.setText("");
        txtfechaServicio.setText("");
        txtfechaVencimiento.setText("");
        txtidCli.setText("");
        txtnumeroOrden.setText("");
        lblcertificadoAsignado.setText("");
        txttelefono.setText("");
        txtcosto.setText("");
        txtperiodo.setText("");
        JTADIRECCION.setText("");
        JTADET.setText("Detalles:");
        adjunt = "";
        //SERVICIOS 
        jcdesinsectacion.setSelected(false);
        jcDesinfeccion.setSelected(false);
        jcdesratizacion.setSelected(false);
        jclimpiezaAmbientes.setSelected(false);
        jclimpiezaTanques.setSelected(false);
        jclimpiezareservorios.setSelected(false);
    }
    
    private void enadisa_Pbuttons(boolean Nuevo, boolean Modificar, boolean Cancelar, boolean Buscar, boolean Salir,boolean verimg)
    {
        btnnuevo.setEnabled(Nuevo);
        btnmodificar.setEnabled(Modificar);
        btncancelar.setEnabled(Cancelar);
        btnbuscar.setEnabled(Buscar);
        btnverimagen.setEnabled(verimg);
        btnsalir.setEnabled(Salir);
    }
    
    private void enadisa_Gbuttons(boolean cond)
    {
        btnfecha_Serv.setEnabled(cond);
        btnFecha_Ven.setEnabled(cond);
        btnConsultarOrden.setEnabled(cond);
        btnConsultarCliente.setEnabled(cond);
        btnadjuntarCertificado.setEnabled(cond);
    }
    
    private void enadisa_boxes(boolean cond)
    {
        txtidCert.setEditable(cond);
        txtnumeroCert.setEditable(cond);
        cbestado.setEnabled(cond);
        txtrazonSocial.setEditable(cond);
        txtgiro.setEditable(cond);
        txtareatotal.setEditable(cond);
        cbformato.setEnabled(cond);
        txttelefono.setEditable(cond);
        txtcosto.setEditable(cond);
        txtperiodo.setEditable(cond);
        JTADIRECCION.setEditable(cond);
        JTADET.setEditable(cond);JTADET.setText("");
        //SERVICIOS 
        jcdesinsectacion.setEnabled(cond);
        jcDesinfeccion.setEnabled(cond);
        jcdesratizacion.setEnabled(cond);
        jclimpiezaAmbientes.setEnabled(cond);
        jclimpiezaTanques.setEnabled(cond);
        jclimpiezareservorios.setEnabled(cond);
    }
    
    private void reiniciarColores()
    {
        txtnumeroCert.setBackground(ColorInicial);
        txtrazonSocial.setBackground(ColorInicial);
        txtgiro.setBackground(ColorInicial);
        txtareatotal.setBackground(ColorInicial);
        txtfechaServicio.setBackground(ColorInicial);
        txtfechaVencimiento.setBackground(ColorInicial);
        txtidCli.setBackground(ColorInicial);
        txtnumeroOrden.setBackground(ColorInicial);
        lblcertificadoAsignado.setBackground(ColorInicial);
        txttelefono.setBackground(ColorInicial);
        txtcosto.setBackground(ColorInicial);
        txtperiodo.setBackground(ColorInicial);
        JTADIRECCION.setBackground(ColorInicial);
        JTADET.setBackground(ColorInicial);
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        lblusuario = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbldate = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTAIA = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        txtidCert = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtnumeroCert = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbestado = new javax.swing.JComboBox<>();
        btnnuevo = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        btnbuscar = new javax.swing.JButton();
        btnverimagen = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtrazonSocial = new javax.swing.JTextField();
        txtgiro = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtareatotal = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cbformato = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        txtfechaServicio = new javax.swing.JTextField();
        txtfechaVencimiento = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        btnfecha_Serv = new javax.swing.JButton();
        btnFecha_Ven = new javax.swing.JButton();
        txttelefono = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtcosto = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtperiodo = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTADIRECCION = new javax.swing.JTextArea();
        jLabel21 = new javax.swing.JLabel();
        txtidCli = new javax.swing.JTextField();
        btnConsultarCliente = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jcdesinsectacion = new javax.swing.JCheckBox();
        jcDesinfeccion = new javax.swing.JCheckBox();
        jcdesratizacion = new javax.swing.JCheckBox();
        jclimpiezareservorios = new javax.swing.JCheckBox();
        jclimpiezaTanques = new javax.swing.JCheckBox();
        jclimpiezaAmbientes = new javax.swing.JCheckBox();
        jLabel23 = new javax.swing.JLabel();
        btnadjuntarCertificado = new javax.swing.JButton();
        btnConsultarOrden = new javax.swing.JButton();
        txtnumeroOrden = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTADET = new javax.swing.JTextArea();
        lblcertificadoAsignado = new javax.swing.JLabel();
        btnsalir = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("usuario:");

        lblusuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblusuario.setText("Manuel Fernando Saavedra Benites");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 102));
        jLabel4.setText("Generar Certificado");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Fecha Sistema:");

        lbldate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbldate.setText("18/12/2017");

        JTAIA.setEditable(false);
        JTAIA.setBackground(new java.awt.Color(204, 204, 204));
        JTAIA.setColumns(15);
        JTAIA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        JTAIA.setForeground(new java.awt.Color(0, 102, 153));
        JTAIA.setRows(2);
        jScrollPane1.setViewportView(JTAIA);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("ID CERT:");

        txtidCert.setEditable(false);
        txtidCert.setBackground(new java.awt.Color(204, 255, 204));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Número de Cert:");

        txtnumeroCert.setEditable(false);
        txtnumeroCert.setBackground(new java.awt.Color(204, 204, 204));
        txtnumeroCert.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnumeroCertKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Estado:");

        cbestado.setBackground(new java.awt.Color(204, 204, 204));
        cbestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VIGENTE", "VENCIDO", "POR VENCER" }));
        cbestado.setSelectedItem("EN ESPERA");
        cbestado.setEnabled(false);

        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Files-New-File-icon.png"))); // NOI18N
        btnnuevo.setText("Nuevo");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/EDIT.png"))); // NOI18N
        btnmodificar.setText("Modificar");
        btnmodificar.setEnabled(false);
        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarActionPerformed(evt);
            }
        });

        btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Windows-Close-Program-icon.png"))); // NOI18N
        btncancelar.setText("Cancelar");
        btncancelar.setEnabled(false);
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/search-icon.png"))); // NOI18N
        btnbuscar.setText("Buscar");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        btnverimagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Certificado_24.png"))); // NOI18N
        btnverimagen.setText("Ver Adjuntado");
        btnverimagen.setEnabled(false);
        btnverimagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnverimagenActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Ubicación En:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Razón Social:");

        txtrazonSocial.setEditable(false);
        txtrazonSocial.setBackground(new java.awt.Color(204, 204, 204));

        txtgiro.setEditable(false);
        txtgiro.setBackground(new java.awt.Color(204, 204, 204));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Giro:");

        txtareatotal.setEditable(false);
        txtareatotal.setBackground(new java.awt.Color(204, 204, 204));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Área Total Tratada:");

        cbformato.setBackground(new java.awt.Color(204, 204, 204));
        cbformato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MT2", "MT3" }));
        cbformato.setSelectedItem("EN ESPERA");
        cbformato.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Fecha Servicio:");

        txtfechaServicio.setEditable(false);
        txtfechaServicio.setBackground(new java.awt.Color(204, 204, 204));

        txtfechaVencimiento.setEditable(false);
        txtfechaVencimiento.setBackground(new java.awt.Color(204, 204, 204));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Fecha Venc.:");

        btnfecha_Serv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/event-search-icon.png"))); // NOI18N
        btnfecha_Serv.setContentAreaFilled(false);
        btnfecha_Serv.setEnabled(false);
        btnfecha_Serv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfecha_ServActionPerformed(evt);
            }
        });

        btnFecha_Ven.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/event-search-icon.png"))); // NOI18N
        btnFecha_Ven.setContentAreaFilled(false);
        btnFecha_Ven.setEnabled(false);
        btnFecha_Ven.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecha_VenActionPerformed(evt);
            }
        });

        txttelefono.setEditable(false);
        txttelefono.setBackground(new java.awt.Color(204, 204, 204));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Telefono:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Costo:");

        txtcosto.setEditable(false);
        txtcosto.setBackground(new java.awt.Color(204, 204, 204));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Periodo:");

        txtperiodo.setEditable(false);
        txtperiodo.setBackground(new java.awt.Color(204, 204, 204));
        txtperiodo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtperiodoKeyTyped(evt);
            }
        });

        JTADIRECCION.setEditable(false);
        JTADIRECCION.setBackground(new java.awt.Color(204, 204, 204));
        JTADIRECCION.setColumns(15);
        JTADIRECCION.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        JTADIRECCION.setForeground(new java.awt.Color(0, 102, 153));
        JTADIRECCION.setRows(2);
        jScrollPane2.setViewportView(JTADIRECCION);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Asignar Cliente:");

        txtidCli.setEditable(false);
        txtidCli.setBackground(new java.awt.Color(204, 204, 204));

        btnConsultarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/search-icon.png"))); // NOI18N
        btnConsultarCliente.setText("Consultar");
        btnConsultarCliente.setContentAreaFilled(false);
        btnConsultarCliente.setEnabled(false);
        btnConsultarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarClienteActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("SERVICIOS REALIZADOS");

        jcdesinsectacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcdesinsectacion.setText("Desinsectación");
        jcdesinsectacion.setEnabled(false);

        jcDesinfeccion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcDesinfeccion.setText("Desinfección");
        jcDesinfeccion.setEnabled(false);

        jcdesratizacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcdesratizacion.setText("Desratización");
        jcdesratizacion.setEnabled(false);

        jclimpiezareservorios.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jclimpiezareservorios.setText("Limpieza y Desinfección de Reservorios de Agua");
        jclimpiezareservorios.setEnabled(false);

        jclimpiezaTanques.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jclimpiezaTanques.setText("Limpieza de Tanques Septicos");
        jclimpiezaTanques.setEnabled(false);

        jclimpiezaAmbientes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jclimpiezaAmbientes.setText("Limpieza de Ambientes");
        jclimpiezaAmbientes.setEnabled(false);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 255));
        jLabel23.setText("Adjuntar Certificado Escaneado:");

        btnadjuntarCertificado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Certificado_24.png"))); // NOI18N
        btnadjuntarCertificado.setText("Adjuntar");
        btnadjuntarCertificado.setContentAreaFilled(false);
        btnadjuntarCertificado.setEnabled(false);
        btnadjuntarCertificado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnadjuntarCertificadoActionPerformed(evt);
            }
        });

        btnConsultarOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/search-icon.png"))); // NOI18N
        btnConsultarOrden.setText("Consultar");
        btnConsultarOrden.setContentAreaFilled(false);
        btnConsultarOrden.setEnabled(false);
        btnConsultarOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarOrdenActionPerformed(evt);
            }
        });

        txtnumeroOrden.setEditable(false);
        txtnumeroOrden.setBackground(new java.awt.Color(204, 204, 204));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Asignar Orden:");

        JTADET.setEditable(false);
        JTADET.setBackground(new java.awt.Color(204, 204, 204));
        JTADET.setColumns(15);
        JTADET.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        JTADET.setForeground(new java.awt.Color(0, 102, 153));
        JTADET.setRows(2);
        JTADET.setText("Detalles:\n");
        jScrollPane3.setViewportView(JTADET);

        lblcertificadoAsignado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblcertificadoAsignado.setForeground(new java.awt.Color(51, 102, 0));
        lblcertificadoAsignado.setText(" ");

        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/logout-icon24.png"))); // NOI18N
        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtidCli, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConsultarCliente))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblcertificadoAsignado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnadjuntarCertificado, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnumeroOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConsultarOrden)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcdesratizacion)
                            .addComponent(jcDesinfeccion)
                            .addComponent(jcdesinsectacion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jclimpiezareservorios)
                            .addComponent(jclimpiezaTanques)
                            .addComponent(jclimpiezaAmbientes))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(7, 7, 7))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addComponent(lblusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(10, 10, 10)
                        .addComponent(lbldate, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtidCert, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbestado, 0, 103, Short.MAX_VALUE)
                                    .addComponent(txtnumeroCert))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnnuevo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnmodificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btncancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnbuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnverimagen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel16)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtfechaServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel17)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtfechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnFecha_Ven)
                                            .addComponent(btnfecha_Serv)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel15)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(122, 122, 122)
                                                    .addComponent(txtareatotal, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbformato, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel14)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtgiro, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel13)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtrazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jScrollPane2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jLabel20)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtperiodo))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel19)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtcosto, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel18)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane3)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(lbldate))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(lblusuario)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel7))
                            .addComponent(txtidCert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel8))
                            .addComponent(txtnumeroCert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnuevo)
                    .addComponent(btnmodificar)
                    .addComponent(btncancelar)
                    .addComponent(btnbuscar)
                    .addComponent(btnverimagen)
                    .addComponent(btnsalir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(txtcosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20)
                                    .addComponent(txtperiodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane3))
                        .addGap(9, 9, 9)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel13))
                            .addComponent(txtrazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel14))
                            .addComponent(txtgiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel15))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtareatotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbformato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfechaServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnfecha_Serv)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFecha_Ven)
                            .addComponent(jLabel17))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jcdesinsectacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcDesinfeccion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcdesratizacion))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jclimpiezaAmbientes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jclimpiezaTanques)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jclimpiezareservorios))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtidCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21)
                                .addComponent(btnConsultarCliente))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtnumeroOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel24)
                                .addComponent(btnConsultarOrden))
                            .addGap(1, 1, 1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnadjuntarCertificado)
                                .addComponent(jLabel23))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblcertificadoAsignado))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed

    try
    {
    //EJECUTADO ANTES DE TODO
    SYSAUDIOCON sysau = new SYSAUDIOCON();

    //EJECUTADO ANTES DE TODA CONDICIONAL
    JFrame jf = new JFrame();
    jf.setAlwaysOnTop(true);
    clearCacheDB();
        
    //SERVICIOS ID
    String serv1 = "SERV-001"; //DESINSECTACION
    String serv2 = "SERV-003"; //DESINFECCION
    String serv3 = "SERV-002"; //DESRATIZACION
    String serv4 = "SERV-005"; //LIMPIEZA DE AMBIENTES
    String serv5 = "SERV-006"; //LIMPIEZA DE TANQUES
    String serv6 = "SERV-004"; //LIMPIEZA DE RESERVORIOS

    if(btnnuevo.getText().equals("Nuevo"))
    {
        clearCacheDB();
        clear_frm();
        enadisa_Pbuttons(true, false, true, false, true,false);
        enadisa_boxes(true);
        enadisa_Gbuttons(true);
        Nuevo();
        btnnuevo.setText("Insertar");
    }else
        {
          //LOGICA DE VERIFICACION
          /*
            la logica de verificacion usada sera LS.
            la cual sigfnifica logica separativa; brevemente lo que hace es 
            verificar cada bloquye a a la vez en ves de verificar bloque por bloque
         */

          if(txtnumeroCert.getText().trim().equals(""))
          {
              sysau.E_ERROR();
              txtnumeroCert.setBackground(Color.RED);
              JOptionPane.showMessageDialog(jf, "Es obligatorio ingrear un 'Número de Certificado'", "Número de Certificado No Ingresado", JOptionPane.ERROR_MESSAGE);
              sysau.S_STOP();
          }else
              {
                  if(txtrazonSocial.getText().trim().equals(""))
                  {
                      sysau.E_ERROR();
                      txtrazonSocial.setBackground(Color.RED);
                      JOptionPane.showMessageDialog(jf, "Es obligatorio ingrear una 'Razón Social'", "Razón Social No Ingresado", JOptionPane.ERROR_MESSAGE);
                      sysau.S_STOP();
                  }else
                      {
                          if(txtgiro.getText().trim().equals(""))
                          {
                              sysau.E_ERROR();
                              txtgiro.setBackground(Color.RED);
                              JOptionPane.showMessageDialog(jf, "Es obligatorio ingrear un 'Giro'", "Giro No Ingresado", JOptionPane.ERROR_MESSAGE);
                              sysau.S_STOP();
                          }else
                              {
                                 if(txtcosto.getText().trim().equals(""))
                                 {
                                    sysau.E_ERROR();
                                    txtcosto.setBackground(Color.RED);
                                    JOptionPane.showMessageDialog(jf, "Es obligatorio ingrear un 'Costo'", "Costo No Ingresado", JOptionPane.ERROR_MESSAGE);
                                    sysau.S_STOP();
                                 }else
                                     {
                                         if(txtareatotal.getText().trim().equals(""))
                                         {
                                            sysau.E_ERROR();
                                            txtareatotal.setBackground(Color.RED);
                                            JOptionPane.showMessageDialog(jf, "Es obligatorio ingrear una 'Área Total'", "Área Total No Ingresado", JOptionPane.ERROR_MESSAGE);
                                            sysau.S_STOP();
                                         }else
                                            {
                                                if(txtfechaServicio.getText().trim().equals(""))
                                                {
                                                    sysau.E_ERROR();
                                                    txtfechaServicio.setBackground(Color.RED);
                                                    JOptionPane.showMessageDialog(jf, "Es obligatorio ingrear una 'Fecha de Servicio'", "Fecha de Servicio No Ingresado", JOptionPane.ERROR_MESSAGE);
                                                    sysau.S_STOP();      
                                                }else
                                                    {
                                                        if(txtfechaVencimiento.getText().trim().equals(""))
                                                        {
                                                            sysau.E_ERROR();
                                                            txtfechaVencimiento.setBackground(Color.RED);
                                                            JOptionPane.showMessageDialog(jf, "Es obligatorio ingrear una 'Fecha de Vencimiento'", "Fecha de Vencimiento No Ingresado", JOptionPane.ERROR_MESSAGE);
                                                            sysau.S_STOP();      
                                                        }
                                                        else
                                                           {
                                                               if(txtidCli.getText().trim().equals(""))
                                                               {
                                                                   sysau.E_ERROR();
                                                                   txtidCli.setBackground(Color.RED);
                                                                   JOptionPane.showMessageDialog(jf, "Es obligatorio Asignar un 'Cliente'", "Cliente No Asignado", JOptionPane.ERROR_MESSAGE);
                                                                   sysau.S_STOP();      
                                                                   
                                                                   evn.write(lblusuario.getText(),"Hizo click en el botón 'Nuevo' Certificado"  , "JIFGenerarCertificado", "Botón 'Nuevo' presionado");
                                                                   
                                                               }else
                                                                   {
                                                                       if(lblcertificadoAsignado.getText().trim().equals(""))
                                                                       {
                                                                           sysau.E_ERROR();
                                                                           lblcertificadoAsignado.setBackground(Color.RED);
                                                                           JOptionPane.showMessageDialog(jf, "Se necesita asignar un 'escaneo del ceritificado'", "Escaneo del Ceritificado no seleccionado", JOptionPane.ERROR_MESSAGE);
                                                                           JTC.clear(JTAIA);
                                                                           JTC.cabecera(JTAIA);
                                                                           JTC.msj(JTAIA, "Recuerde que ahora es obligatorio adjuntar un escaneo del certificado original\npara tener un mayor control y almacen de certificados.");
                                                                           sysau.S_STOP();      
                                                                       }else
                                                                           {
                                                                               if(txtperiodo.getText().trim().equals(""))
                                                                               {
                                                                                   sysau.E_ERROR();
                                                                                   txtperiodo.setBackground(Color.RED);
                                                                                   JOptionPane.showMessageDialog(jf, "Es obligatorio ingresar un 'Periodo'", "Periodo no Ingresado", JOptionPane.ERROR_MESSAGE);
                                                                                   sysau.S_STOP();      
                                                                               }else
                                                                                   {
                                                                                       if(JTADIRECCION.getText().trim().equals(""))
                                                                                       {
                                                                                           sysau.E_ERROR();
                                                                                           JTADIRECCION.setBackground(Color.RED);
                                                                                           JOptionPane.showMessageDialog(jf, "Es obligatorio ingresar una 'Dirección'", "Dirección no Ingresada", JOptionPane.ERROR_MESSAGE);
                                                                                           sysau.S_STOP();      
                                                                                       }else
                                                                                           {
                                                                                               
                                                                                                if(txttelefono.getText().trim().equals(""))
                                                                                                {
                                                                                                    txttelefono.setBackground(Color.YELLOW);
                                                                                                    txttelefono.setText("-");
                                                                                                }

                                                                                                if(JTADET.getText().trim().equals(""))
                                                                                                {
                                                                                                    JTADET.setBackground(Color.YELLOW);
                                                                                                    JTADET.setText("-");
                                                                                                }

                                                                                                if(txtnumeroOrden.getText().trim().equals(""))
                                                                                                {
                                                                                                    txtnumeroOrden.setBackground(Color.YELLOW);
                                                                                                    txtnumeroOrden.setText("-");
                                                                                                }

                                                                                                //FIN LOGICA DE VERIFICACION
                                                                                                //INICIO PROGRAMACION DE INGRESO

                                                                                                //OBTENCION DE DATOS
                                                                                                String id_cert = txtidCert.getText().toUpperCase();
                                                                                                String numero_cert = txtnumeroCert.getText().toUpperCase();
                                                                                                String estado = cbestado.getSelectedItem().toString().toUpperCase();
                                                                                                String razon_social = txtrazonSocial.getText().toUpperCase();
                                                                                                String giro = txtgiro.getText().toUpperCase();
                                                                                                String area = txtareatotal.getText().toUpperCase();
                                                                                                String formato = cbformato.getSelectedItem().toString().toUpperCase();
                                                                                                String fecha_serv = txtfechaServicio.getText().toUpperCase();
                                                                                                String fecha_ven = txtfechaVencimiento.getText().toUpperCase();
                                                                                                String telefono = txttelefono.getText().toUpperCase();
                                                                                                String costo = txtcosto.getText().toUpperCase();
                                                                                                String periodo = txtperiodo.getText().toUpperCase();
                                                                                                String det = JTADET.getText().toUpperCase();
                                                                                                String dir = JTADIRECCION.getText().toUpperCase();
                                                                                                String cli_asig = txtidCli.getText().toUpperCase();
                                                                                                String num_orden = txtnumeroOrden.getText().toUpperCase();
                                                                                                String Adjuntado = adjunt;

                                                                                                //ALGORITMO DE INSERCION DE DATOS MULTIPLEs
                                                                                                P_cert.InsertarCertificado(id_cert, numero_cert, razon_social,dir,giro,area,formato,fecha_serv,fecha_ven,telefono,costo,periodo,det,cli_asig,num_orden,Adjuntado,estado);

                                                                                                if(jcdesinsectacion.isSelected()){P_cert.InsertarDETCertificado(id_cert, serv1);} //DESINSECTACION
                                                                                                if(jcDesinfeccion.isSelected()){P_cert.InsertarDETCertificado(id_cert, serv2);} //DESINFECCION
                                                                                                if(jcdesratizacion.isSelected()){P_cert.InsertarDETCertificado(id_cert, serv3);} //DESRATIZACION
                                                                                                if(jclimpiezaAmbientes.isSelected()){P_cert.InsertarDETCertificado(id_cert, serv4);} //LIMPIEZA DE AMBIENTES
                                                                                                if(jclimpiezaTanques.isSelected()){P_cert.InsertarDETCertificado(id_cert, serv5);} //LIMPIENZA TANQUES
                                                                                                if(jclimpiezareservorios.isSelected()){P_cert.InsertarDETCertificado(id_cert, serv6);} //LIMPIEZA RESERVORIOS

                                                                                                evn.write(lblusuario.getText(), "Inserto un nuevo Certificado", "JIFGenerarCertificado", "Botón 'Insertar' Presionado");
                                                                                                rslt.write(lblusuario.getText(), "JIFGenerarCertificado", "INSERCIÓN", "Se ha insertado el Certificado con ID  "+id_cert+
                                                                                                                             "\n RAZÓN: "+razon_social+" \n CLIENTE ASIGNADO: "+cli_asig);

                                                                                                sysau.E_INFORMATION();
                                                                                                JOptionPane.showMessageDialog(jf, "Certificado Insertado con exito!", "Certificado Insertado", JOptionPane.INFORMATION_MESSAGE);

                                                                                                clearCacheDB();
                                                                                                clear_frm();
                                                                                                enadisa_Pbuttons(true, false, false, true, true,false);
                                                                                                enadisa_boxes(false);
                                                                                                enadisa_Gbuttons(false);
                                                                                                btnnuevo.setText("Nuevo");
                                                                                                reiniciarColores();
                                                                                           }
                                                                                   }
                                                                           }
                                                                   }
                                                           }
                                                    }
                                            }
                                     }
                              }
                      }
              }
        }
    } catch (Exception e) 
       {
         lc.write("Ha ocurrido un error al intentar crear un certificado", "JIFGenerarCertificado", e);
       }
        
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
    
    try
    {
    //EJECUTADO ANTES DE TODO
    SYSAUDIOCON sysau = new SYSAUDIOCON();

    //EJECUTADO ANTES DE TODA CONDICIONAL
    JFrame jf = new JFrame();
    jf.setAlwaysOnTop(true);
    clearCacheDB();
        
    //SERVICIOS ID
    String serv1 = "SERV-001"; //DESINSECTACION
    String serv2 = "SERV-003"; //DESINFECCION
    String serv3 = "SERV-002"; //DESRATIZACION
    String serv4 = "SERV-005"; //LIMPIEZA DE AMBIENTES
    String serv5 = "SERV-006"; //LIMPIEZA DE TANQUES
    String serv6 = "SERV-004"; //LIMPIEZA DE RESERVORIOS

    if(btnmodificar.getText().equals("Modificar"))
    {
        clearCacheDB();
        enadisa_Pbuttons(false, true, true, false, true,false);
        enadisa_boxes(true);
        enadisa_Gbuttons(true);
        btnmodificar.setText("Actualizar");
    }else
        {
          //LOGICA DE VERIFICACION
          /*
            la logica de verificacion usada sera LS.
            la cual sigfnifica logica separativa; brevemente lo que hace es 
            verificar cada bloquye a a la vez en ves de verificar bloque por bloque
         */

          if(txtnumeroCert.getText().trim().equals(""))
          {
              sysau.E_ERROR();
              txtnumeroCert.setBackground(Color.RED);
              JOptionPane.showMessageDialog(jf, "Es obligatorio ingrear un 'Número de Certificado'", "Número de Certificado No Ingresado", JOptionPane.ERROR_MESSAGE);
              sysau.S_STOP();
          }else
              {
                  if(txtrazonSocial.getText().trim().equals(""))
                  {
                      sysau.E_ERROR();
                      txtrazonSocial.setBackground(Color.RED);
                      JOptionPane.showMessageDialog(jf, "Es obligatorio ingrear una 'Razón Social'", "Razón Social No Ingresado", JOptionPane.ERROR_MESSAGE);
                      sysau.S_STOP();
                  }else
                      {
                          if(txtgiro.getText().trim().equals(""))
                          {
                              sysau.E_ERROR();
                              txtgiro.setBackground(Color.RED);
                              JOptionPane.showMessageDialog(jf, "Es obligatorio ingrear un 'Giro'", "Giro No Ingresado", JOptionPane.ERROR_MESSAGE);
                              sysau.S_STOP();
                          }else
                              {
                                 if(txtcosto.getText().trim().equals(""))
                                 {
                                    sysau.E_ERROR();
                                    txtcosto.setBackground(Color.RED);
                                    JOptionPane.showMessageDialog(jf, "Es obligatorio ingrear un 'Costo'", "Costo No Ingresado", JOptionPane.ERROR_MESSAGE);
                                    sysau.S_STOP();
                                 }else
                                     {
                                         if(txtareatotal.getText().trim().equals(""))
                                         {
                                            sysau.E_ERROR();
                                            txtareatotal.setBackground(Color.RED);
                                            JOptionPane.showMessageDialog(jf, "Es obligatorio ingrear una 'Área Total'", "Área Total No Ingresado", JOptionPane.ERROR_MESSAGE);
                                            sysau.S_STOP();
                                         }else
                                            {
                                                if(txtfechaServicio.getText().trim().equals(""))
                                                {
                                                    sysau.E_ERROR();
                                                    txtfechaServicio.setBackground(Color.RED);
                                                    JOptionPane.showMessageDialog(jf, "Es obligatorio ingrear una 'Fecha de Servicio'", "Fecha de Servicio No Ingresado", JOptionPane.ERROR_MESSAGE);
                                                    sysau.S_STOP();      
                                                }else
                                                    {
                                                        if(txtfechaVencimiento.getText().trim().equals(""))
                                                        {
                                                            sysau.E_ERROR();
                                                            txtfechaVencimiento.setBackground(Color.RED);
                                                            JOptionPane.showMessageDialog(jf, "Es obligatorio ingrear una 'Fecha de Vencimiento'", "Fecha de Vencimiento No Ingresado", JOptionPane.ERROR_MESSAGE);
                                                            sysau.S_STOP();      
                                                        }
                                                        else
                                                           {
                                                               if(txtidCli.getText().trim().equals(""))
                                                               {
                                                                   sysau.E_ERROR();
                                                                   txtidCli.setBackground(Color.RED);
                                                                   JOptionPane.showMessageDialog(jf, "Es obligatorio Asignar un 'Cliente'", "Cliente No Asignado", JOptionPane.ERROR_MESSAGE);
                                                                   sysau.S_STOP();      
                                                                   
                                                                   evn.write(lblusuario.getText(),"Hizo click en el botón 'Nuevo' Certificado"  , "JIFGenerarCertificado", "Botón 'Nuevo' presionado");
                                                                   
                                                               }else
                                                                   {
                                                                       if(lblcertificadoAsignado.getText().trim().equals(""))
                                                                       {
                                                                           sysau.E_ERROR();
                                                                           lblcertificadoAsignado.setBackground(Color.RED);
                                                                           JOptionPane.showMessageDialog(jf, "Se necesita asignar un 'escaneo del ceritificado'", "Escaneo del Ceritificado no seleccionado", JOptionPane.ERROR_MESSAGE);
                                                                           JTC.clear(JTAIA);
                                                                           JTC.cabecera(JTAIA);
                                                                           JTC.msj(JTAIA, "Recuerde que ahora es obligatorio adjuntar un escaneo del certificado original\npara tener un mayor control y almacen de certificados.");
                                                                           sysau.S_STOP();      
                                                                       }else
                                                                           {
                                                                               if(txtperiodo.getText().trim().equals(""))
                                                                               {
                                                                                   sysau.E_ERROR();
                                                                                   txtperiodo.setBackground(Color.RED);
                                                                                   JOptionPane.showMessageDialog(jf, "Es obligatorio ingresar un 'Periodo'", "Periodo no Ingresado", JOptionPane.ERROR_MESSAGE);
                                                                                   sysau.S_STOP();      
                                                                               }else
                                                                                   {
                                                                                       if(JTADIRECCION.getText().trim().equals(""))
                                                                                       {
                                                                                           sysau.E_ERROR();
                                                                                           JTADIRECCION.setBackground(Color.RED);
                                                                                           JOptionPane.showMessageDialog(jf, "Es obligatorio ingresar una 'Dirección'", "Dirección no Ingresada", JOptionPane.ERROR_MESSAGE);
                                                                                           sysau.S_STOP();      
                                                                                       }else
                                                                                           {
                                                                                               
                                                                                                if(txttelefono.getText().trim().equals(""))
                                                                                                {
                                                                                                    txttelefono.setBackground(Color.YELLOW);
                                                                                                    txttelefono.setText("-");
                                                                                                }

                                                                                                if(JTADET.getText().trim().equals(""))
                                                                                                {
                                                                                                    JTADET.setBackground(Color.YELLOW);
                                                                                                    JTADET.setText("-");
                                                                                                }

                                                                                                if(txtnumeroOrden.getText().trim().equals(""))
                                                                                                {
                                                                                                    txtnumeroOrden.setBackground(Color.YELLOW);
                                                                                                    txtnumeroOrden.setText("-");
                                                                                                }

                                                                                                //FIN LOGICA DE VERIFICACION
                                                                                                //INICIO PROGRAMACION DE INGRESO

                                                                                                //OBTENCION DE DATOS
                                                                                                String id_cert = txtidCert.getText().toUpperCase();
                                                                                                String numero_cert = txtnumeroCert.getText().toUpperCase();
                                                                                                String estado = cbestado.getSelectedItem().toString().toUpperCase();
                                                                                                String razon_social = txtrazonSocial.getText().toUpperCase();
                                                                                                String giro = txtgiro.getText().toUpperCase();
                                                                                                String area = txtareatotal.getText().toUpperCase();
                                                                                                String formato = cbformato.getSelectedItem().toString().toUpperCase();
                                                                                                String fecha_serv = txtfechaServicio.getText().toUpperCase();
                                                                                                String fecha_ven = txtfechaVencimiento.getText().toUpperCase();
                                                                                                String telefono = txttelefono.getText().toUpperCase();
                                                                                                String costo = txtcosto.getText().toUpperCase();
                                                                                                String periodo = txtperiodo.getText().toUpperCase();
                                                                                                String det = JTADET.getText().toUpperCase();
                                                                                                String dir = JTADIRECCION.getText().toUpperCase();
                                                                                                String cli_asig = txtidCli.getText().toUpperCase();
                                                                                                String num_orden = txtnumeroOrden.getText().toUpperCase();
                                                                                                String Adjuntado = adjunt;
                                                                                                System.out.println(Adjuntado);


                                                                                                //ALGORITMO DE INSERCION DE DATOS MULTIPLEs
                                                                                                P_cert.ModificarCertificado(id_cert, numero_cert, razon_social,dir,giro,area,formato,fecha_serv,fecha_ven,telefono,costo,periodo,det,cli_asig,num_orden,Adjuntado,estado);

                                                                                                if(jcdesinsectacion.isSelected()){P_cert.InsertarDETCertificado(id_cert, serv1);} //DESINSECTACION
                                                                                                if(jcDesinfeccion.isSelected()){P_cert.InsertarDETCertificado(id_cert, serv2);} //DESINFECCION
                                                                                                if(jcdesratizacion.isSelected()){P_cert.InsertarDETCertificado(id_cert, serv3);} //DESRATIZACION
                                                                                                if(jclimpiezaAmbientes.isSelected()){P_cert.InsertarDETCertificado(id_cert, serv4);} //LIMPIEZA DE AMBIENTES
                                                                                                if(jclimpiezaTanques.isSelected()){P_cert.InsertarDETCertificado(id_cert, serv5);} //LIMPIENZA TANQUES
                                                                                                if(jclimpiezareservorios.isSelected()){P_cert.InsertarDETCertificado(id_cert, serv6);} //LIMPIEZA RESERVORIOS

                                                                                                evn.write(lblusuario.getText(), "Modifico un Certificado", "JIFGenerarCertificado", "Botón 'Modificar' Presionado");
                                                                                                rslt.write(lblusuario.getText(), "JIFGenerarCertificado", "MODIFICACIÓN", "Se ha modificado el Certificado con ID  "+id_cert+
                                                                                                                             "\n RAZÓN: "+razon_social+" \n CLIENTE ASIGNADO: "+cli_asig);

                                                                                                sysau.E_INFORMATION();
                                                                                                JOptionPane.showMessageDialog(jf, "Certificado Modificado con exito!", "Certificado Modificado", JOptionPane.INFORMATION_MESSAGE);

                                                                                                clearCacheDB();
                                                                                                clear_frm();
                                                                                                btnverimagen.setEnabled(false);
                                                                                                enadisa_Pbuttons(true, false, false, true, true,false);
                                                                                                enadisa_boxes(false);
                                                                                                enadisa_Gbuttons(false);
                                                                                                reiniciarColores();
                                                                                           }
                                                                                   }
                                                                           }
                                                                   }
                                                           }
                                                    }
                                            }
                                     }
                              }
                      }
              }
        }
    } catch (Exception e) 
       {
         lc.write("Ha ocurrido un error al intentar actualizar un certificado", "JIFGenerarCertificado", e);
       }
        
       
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed

    try 
    {
        evn.write(lblusuario.getText(), "Ha cancelado la inserción o modificación de un Certificado ", "JIFGenerarCertificado", "Botón 'Cancelar' Presionado");
        clear_frm();
        enadisa_Pbuttons(true, false, false, true, true,false);
        enadisa_boxes(false);
        enadisa_Gbuttons(false);
        reiniciarColores();
        btnnuevo.setText("Nuevo");
        btnmodificar.setText("Modificar");
        clearCacheDB();

    } catch (Exception e) 
       {
           lc.write("No se pudo cancelar el certificado", "JIFGenerarCertificado", e);
       }
        

    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed

          
    //Modulo de busqueda de ordenes de trabajo V0.9 
    try
    {
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true); 
        clearCacheDB();
        evn.write(lblusuario.getText(), "Ha abierto el formulario de busqueda de Certificados de servicio", "JIFGenerarCertificados", "Hizo click en el botón 'Buscar'");

       // display the showOptionDialog
        Object[] options = { "JURIDICOS", "NATURALES","CANCELAR"};
        int choice = JOptionPane.showOptionDialog(jf, 
            "¿De que tipo de Cliente desea buscar?", 
            "Tipo de Certificado", 
            JOptionPane.YES_NO_CANCEL_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            options, 
            options[0]);

        // interpret the user's choice
        if (choice == 1)
        {
            //NATURAL
            GL_JDBuscarCertificados busca = new GL_JDBuscarCertificados(form, true, "Natural");
            busca.formulario="GEN_CERT";
            busca.setVisible(true);
            enadisa_Pbuttons(true, true, false, true, true,true);
        }

        if (choice == 0)
        {
            //JURIDICA
            GL_JDBuscarCertificados busca = new GL_JDBuscarCertificados(form, true, "Juridica");
            busca.formulario="GEN_CERT";
            busca.setVisible(true);
            enadisa_Pbuttons(true, true, false, true, true,true);
        }
        if(choice == 2)
        {
            //NOTHING
        } 


//        
    } catch (Exception e) 
        {
            lc.write("Problema al intentar abrir el formulario de buscar ordenes", "JIFGenerarOrdenServicio", e);
        }
        
     
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnverimagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnverimagenActionPerformed

        System.out.println(adjunt);
        mw.abrir_archivo(adjunt);
        
    }//GEN-LAST:event_btnverimagenActionPerformed

    private void btnfecha_ServActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfecha_ServActionPerformed

    try 
    {
        evn.write(lblusuario.getText(), "Abrio el selector de fechas", "JIFGenerarCertificado -> JDCalendar", "Botón 'fecha servicio' presionado");
        GL_JDCalendar ca = new GL_JDCalendar(form, true,"D");
        ca.formulario="GEN_CERT_SERV";
        ca.setVisible(true);

    } catch (Exception e) 
        {
            lc.write("No se pudo abrir el selector de fechas debido a un error inesperado", "JIFGenerarCertificado -> boton fecha servicio", e);
        }
     
    }//GEN-LAST:event_btnfecha_ServActionPerformed

    private void btnFecha_VenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecha_VenActionPerformed

    try 
    {
        evn.write(lblusuario.getText(), "Abrio el selector de fechas", "JIFGenerarCertificado -> JDCalendar", "Botón 'fecha vencimiento' presionado");
        GL_JDCalendar ca = new GL_JDCalendar(form, true,"D");
        ca.formulario="GEN_CERT_VEN";
        ca.setVisible(true);

    } catch (Exception e) 
        {
            lc.write("No se pudo abrir el selector de fechas debido a un error inesperado", "JIFGenerarCertificado -> boton fecha vencimiento", e);
        }

    }//GEN-LAST:event_btnFecha_VenActionPerformed

    private void btnConsultarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarClienteActionPerformed

    try 
    {
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true); 
        
        Object[] options = { "JURIDICOS", "NATURALES","CANCELAR"};
        int choice = JOptionPane.showOptionDialog(jf, 
            "¿Que tipo de Cliente buscar?", 
            "Tipo de Cliente", 
            JOptionPane.YES_NO_CANCEL_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            options, 
            options[0]);
        
        if(choice == 0)
        {
           evn.write(lblusuario.getText(), "Abrio el Buscador de Cliente Juridico", "JIFGenerarCertificado -> GL_JDBuscarCliente", "Botón 'Asignar Cliente' presionado");
           txtidCli.setText("");
           GL_JDBuscarCliente BC = new GL_JDBuscarCliente(form, true,"Juridica");
           BC.formulario="GEN_CERT";
           BC.setVisible(true);

        }
        if(choice == 1)
        {
           evn.write(lblusuario.getText(), "Abrio el Buscador de Cliente Natural", "JIFGenerarCertificado -> GL_JDBuscarCliente", "Botón 'Asignar Cliente' presionado");
           txtidCli.setText("");
           GL_JDBuscarCliente BC = new GL_JDBuscarCliente(form, true,"Natural");
           BC.formulario="GEN_CERT";
           BC.setVisible(true); 
        }
        
        if(choice == 2)
        {
            //NOTHING
        }
        
        
    } catch (Exception e) 
        {
            lc.write("No se pudo abrir el buscador de clientes debido a un error inesperado", "JIFGenerarCertificado -> botón Asignar Cliente", e);
        }
      
    }//GEN-LAST:event_btnConsultarClienteActionPerformed

    private void btnadjuntarCertificadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnadjuntarCertificadoActionPerformed

    try
    {
        SYSAUDIOCON sysau = new SYSAUDIOCON();
        lblcertificadoAsignado.setText("Adjuntando espere...");lblcertificadoAsignado.setForeground(Color.YELLOW);
        JTC.cabecera(JTAIA);
        sysau.E_PUSH();
        
        JTC.msj(JTAIA,"Se Adjuntara el certificado\nPor favor espere a que el proceso termine.");
        //DESTINO
        String id_cert = txtidCert.getText();
        String destino = "./CERT/"+id_cert+".jpg";
        String path = null;
        
        if(abrirArchivo == null ) {abrirArchivo = new JFileChooser();}
        
        //ABRIR SOLO IMAGENES JPG
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagenes JPG","jpg");

        abrirArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        abrirArchivo.setFileHidingEnabled(false);
        abrirArchivo.setFileFilter(filter);
        abrirArchivo.setAcceptAllFileFilterUsed(false);
        
        int seleccion = abrirArchivo.showOpenDialog(this);
        
        if(seleccion == JFileChooser.APPROVE_OPTION)
        {
            File f = abrirArchivo.getSelectedFile();
            path = f.getAbsolutePath();
        }
        sysau.S_STOP();        
        System.out.println("Proceso de copiar archivo : "+SYSCOPY.getinstance().copy(path, destino));
        
        lblcertificadoAsignado.setText("Adjuntado con exito!");lblcertificadoAsignado.setForeground(Color.GREEN);
        sysau.E_INFORMATION();
        JTC.clear(JTAIA);
        JTC.cabecera(JTAIA);
        JTC.msj(JTAIA,"El certificado ha sido adjuntado con exito!\n ya puede proceder a insertar.");
        adjunt = destino;
        System.out.println(destino);
    } catch (Exception e) 
       {
          lc.write("Ha ocurrido algun error al intentar adjuntar un certificado", "JIFGenerarCertificado", e);
       }
      
    }//GEN-LAST:event_btnadjuntarCertificadoActionPerformed

    private void btnConsultarOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarOrdenActionPerformed

  
    //Modulo de busqueda de ordenes de trabajo V0.9 
    try
    {
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true); 
        evn.write(lblusuario.getText(), "Ha abierto el formulario de busqueda de ordenes de servicio", "JIFGenerarCertificado", "Hizo click en el botón 'Asignar Orden'");

       // display the showOptionDialog
        Object[] options = { "JURIDICOS", "NATURALES","CANCELAR"};
        int choice = JOptionPane.showOptionDialog(jf, 
            "¿Que tipo de orden desea buscar?", 
            "Tipo de Orden de Trabajo", 
            JOptionPane.YES_NO_CANCEL_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            options, 
            options[0]);

        // interpret the user's choice
        if (choice == 0)
        {
            //NATURAL
            GL_JDBuscarOrdenes busca = new GL_JDBuscarOrdenes(form, true, "Juridica");
            busca.formulario="GEN_CERT";
            busca.setVisible(true);
        }

        if (choice == 1)
        {
            //JURIDICA
            GL_JDBuscarOrdenes busca = new GL_JDBuscarOrdenes(form, true, "Natural");
            busca.formulario="GEN_CERT";
            busca.setVisible(true);
        }
        if(choice == 2)
        {
            //NOTHING
        } 

    } catch (Exception e) 
        {
            lc.write("Problema al intentar abrir el formulario de buscar ordenes", "JIFGenerarCertificado", e);
        }        

    }//GEN-LAST:event_btnConsultarOrdenActionPerformed

    private void txtnumeroCertKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumeroCertKeyTyped

    char car = evt.getKeyChar();
    if( txtnumeroCert.getText().length()>=9999 ) evt.consume();
    if(( car<'0' || car>'9' )) evt.consume();
        
    }//GEN-LAST:event_txtnumeroCertKeyTyped

    private void txtperiodoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtperiodoKeyTyped

    char car = evt.getKeyChar();
    if( txtnumeroCert.getText().length()>=9999 ) evt.consume();
    if(( car<'0' || car>'9' )) evt.consume();

    }//GEN-LAST:event_txtperiodoKeyTyped

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
                                
    try 
    {           
      evn.write(lblusuario.getText(), "Ha salido del formulario 'JIFGenerarCertificado'", "JIFGenerarCertificado", "Presiono Botón 'Salir'");
      JFRPrincipal.JSMGenerarCertificado.setActionCommand("CERRADO");
      this.dispose();
    } catch (Exception e) 
        {
            lc.write("No se ha podido cerrar el formulario 'JIFGenerarCertificado' debido a un error inesperado", "JIFGenerarCertificado", e);
        }    
        
    }//GEN-LAST:event_btnsalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextArea JTADET;
    public static javax.swing.JTextArea JTADIRECCION;
    private javax.swing.JTextArea JTAIA;
    private javax.swing.JButton btnConsultarCliente;
    private javax.swing.JButton btnConsultarOrden;
    private javax.swing.JButton btnFecha_Ven;
    private javax.swing.JButton btnadjuntarCertificado;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnfecha_Serv;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JButton btnverimagen;
    public static javax.swing.JComboBox<String> cbestado;
    public static javax.swing.JComboBox<String> cbformato;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JCheckBox jcDesinfeccion;
    public static javax.swing.JCheckBox jcdesinsectacion;
    public static javax.swing.JCheckBox jcdesratizacion;
    public static javax.swing.JCheckBox jclimpiezaAmbientes;
    public static javax.swing.JCheckBox jclimpiezaTanques;
    public static javax.swing.JCheckBox jclimpiezareservorios;
    public static javax.swing.JLabel lblcertificadoAsignado;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lblusuario;
    public static javax.swing.JTextField txtareatotal;
    public static javax.swing.JTextField txtcosto;
    public static javax.swing.JTextField txtfechaServicio;
    public static javax.swing.JTextField txtfechaVencimiento;
    public static javax.swing.JTextField txtgiro;
    public static javax.swing.JTextField txtidCert;
    public static javax.swing.JTextField txtidCli;
    public static javax.swing.JTextField txtnumeroCert;
    public static javax.swing.JTextField txtnumeroOrden;
    public static javax.swing.JTextField txtperiodo;
    public static javax.swing.JTextField txtrazonSocial;
    public static javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
