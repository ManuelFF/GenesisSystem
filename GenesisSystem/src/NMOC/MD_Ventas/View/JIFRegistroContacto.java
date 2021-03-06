/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Ventas.View;

import IACore.JTACON;
import ModuleWorker.IC.MWCON;
import ModuleWorker.SYSAUDIOCON;
import ModuleWorker.SYSFRMCON;
import ModuleWorker.View.JFRPrincipal;
import NCLPM.EVENTS;
import NCLPM.LOG;
import NCLPM.RESULTS;
import NMOC.GL_JDCalendar;
import NMOC.MD_Ventas.Core.NOB_registroContacto;
import NMOC.MD_Ventas.IC.MICROCON_RegistroContacto;
import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class JIFRegistroContacto extends javax.swing.JInternalFrame
{

    LOG lc = new LOG();
    EVENTS evn = new EVENTS();
    RESULTS rslt = new RESULTS();
    SYSFRMCON sysfrm = new SYSFRMCON();
    MWCON mw = new MWCON();
    JFrame form;
    JTACON JTC = new JTACON();
    Color ColorInicial;
    //PUENTE
    MICROCON_RegistroContacto P_REGCON = new MICROCON_RegistroContacto();
    
    public JIFRegistroContacto() 
    {
        initComponents();
        this.setTitle(sysfrm.T_RegistroContactos());
        sysfrm.B_RegistroContactos(this.getContentPane());
        lblusuario.setText(JFRPrincipal.JMSesion.getText());
        lbldate.setText(mw.fecha_actual());
        JTADescripcion.setLineWrap(true);
        ColorInicial = txtnombre_razon.getBackground();
        Nuevo();
    }
    
    private void clearCacheDB()
    {
        try
        {
            P_REGCON.clear();
            P_REGCON.llenarDatos();
            
        } catch (Exception e) 
          {
                lc.write("Error al intentar borrar la cache de la DB", this.getTitle(), e);
          }
    }
    
    private void Nuevo()
    {
        clearCacheDB();
        String cod = String.format("%03d", 1);
        int codigo;
        NOB_registroContacto ultObjeto = null;
        
        if(P_REGCON.tamaño() == 0)
        {
            cod="REGC-"+cod;
        }else
            {
                ultObjeto = P_REGCON.obtener(P_REGCON.tamaño()-1);
                
                cod = ultObjeto.getID_REG_CONT().substring(5);
                codigo = Integer.parseInt(cod)+1;
                cod="REGC-"+String.format("%03d", codigo);
            }txtidreg.setText(cod);
        
    }
    
    private void clear_frm()
    {
        cbtipContacto.setSelectedIndex(0);
        cbestado.setSelectedIndex(0);
        txtnombre_razon.setText("");
        txtdni_ruc.setText("");
        JTADescripcion.setText("");
        txtarea.setText("");
        cbformato.setSelectedIndex(0);
        txttelf_celu.setText("");
        txtfecha.setText("");
        txthora.setText("");
        txtcorreo.setText("");
        txtcosto.setText("");
        //SERVICIOS
        jcdesinsectacion.setSelected(false);
        jcDesinfeccion.setSelected(false);
        jcdesratizacion.setSelected(false);
        jclimpiezaAmbientes.setSelected(false);
        jclimpiezaTanques.setSelected(false);
        jclimpiezareservorios.setSelected(false);
        jcventaExtintores.setSelected(false);
        jcrecargaExtintores.setSelected(false);
        jclimpiezaTrampasGrasa.setSelected(false);
    }
    
    
     private void edit_frm(boolean cond)
    {
        cbtipContacto.setEnabled(cond);
        cbestado.setEnabled(cond);
        
        txtnombre_razon.setEditable(cond);
        txtdni_ruc.setEditable(cond);
        JTADescripcion.setEditable(cond);
        txtarea.setEditable(cond);
        
        cbformato.setEnabled(cond);
        
        txttelf_celu.setEditable(cond);
        txtcorreo.setEditable(cond);
        txtcosto.setEditable(cond);
        //SERVICIOS
        jcdesinsectacion.setEnabled(cond);
        jcDesinfeccion.setEnabled(cond);
        jcdesratizacion.setEnabled(cond);
        jclimpiezaAmbientes.setEnabled(cond);
        jclimpiezaTanques.setEnabled(cond);
        jclimpiezareservorios.setEnabled(cond);
        //jcventaExtintores.setEnabled(cond);
        //jcrecargaExtintores.setEnabled(cond);
        jclimpiezaTrampasGrasa.setEnabled(cond);
    }
    
    
    
    private void enadisa_Pburronts(boolean registrar,boolean modificar,boolean limpiar, boolean buscar,boolean exportar,boolean salir)
    {
        btnregistrar.setEnabled(registrar);
        btnmodificar.setEnabled(modificar);
        btnlimpiar.setEnabled(limpiar);
        btnbuscar.setEnabled(buscar);
        btnexportar.setEnabled(exportar);
        btnsalir.setEnabled(salir);
    }
    
    private void enadisa_Pfechas(boolean fecha, boolean hora)
    {
        btnfecha.setEnabled(fecha);
        btnhora.setEnabled(hora);
    }
    
    private void reiniciarColores()
    {
        txtnombre_razon.setBackground(ColorInicial);
        txtdni_ruc.setBackground(ColorInicial);
        JTADescripcion.setBackground(ColorInicial);
        txtarea.setBackground(ColorInicial);
        txttelf_celu.setBackground(ColorInicial);
        txtfecha.setBackground(ColorInicial);
        txthora.setBackground(ColorInicial);
        txtcorreo.setBackground(ColorInicial);
        txtcosto.setBackground(ColorInicial);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblusuario = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbldate = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbtipContacto = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtidreg = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtfecha = new javax.swing.JTextField();
        btnfecha = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txthora = new javax.swing.JTextField();
        btnhora = new javax.swing.JButton();
        btnbuscar = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        btnlimpiar = new javax.swing.JButton();
        btnregistrar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cbestado = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txtnombre_razon = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtdni_ruc = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTADescripcion = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtarea = new javax.swing.JTextField();
        cbformato = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        txtcosto = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txttelf_celu = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtcorreo = new javax.swing.JTextField();
        btnexportar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jcdesinsectacion = new javax.swing.JCheckBox();
        jcDesinfeccion = new javax.swing.JCheckBox();
        jcdesratizacion = new javax.swing.JCheckBox();
        jclimpiezareservorios = new javax.swing.JCheckBox();
        jclimpiezaTanques = new javax.swing.JCheckBox();
        jclimpiezaAmbientes = new javax.swing.JCheckBox();
        jcventaExtintores = new javax.swing.JCheckBox();
        jcrecargaExtintores = new javax.swing.JCheckBox();
        jclimpiezaTrampasGrasa = new javax.swing.JCheckBox();

        lblusuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblusuario.setText("Manuel Fernando Saavedra Benites");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("usuario:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 102));
        jLabel4.setText("Registro de Contactos");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Fecha Sistema:");

        lbldate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbldate.setText("18/12/2017");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Tipo de Contacto: ");

        cbtipContacto.setBackground(new java.awt.Color(204, 204, 204));
        cbtipContacto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CELULAR", "TELEFONO", "CORREO", "CAMPO", "WEB" }));
        cbtipContacto.setSelectedItem("EN ESPERA");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("ID Registro:");

        txtidreg.setEditable(false);
        txtidreg.setBackground(new java.awt.Color(204, 255, 204));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Fecha Propuesta:");

        txtfecha.setEditable(false);
        txtfecha.setBackground(new java.awt.Color(204, 204, 204));

        btnfecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/event-search-icon.png"))); // NOI18N
        btnfecha.setText("Fecha");
        btnfecha.setContentAreaFilled(false);
        btnfecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfechaActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Hora Propuesta:");

        txthora.setEditable(false);
        txthora.setBackground(new java.awt.Color(204, 204, 204));

        btnhora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Time.png"))); // NOI18N
        btnhora.setText("Hora");
        btnhora.setContentAreaFilled(false);
        btnhora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhoraActionPerformed(evt);
            }
        });

        btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/search-icon.png"))); // NOI18N
        btnbuscar.setText("Buscar");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
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

        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/logout-icon24.png"))); // NOI18N
        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        btnlimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Windows-Close-Program-icon.png"))); // NOI18N
        btnlimpiar.setText("Limpiar");
        btnlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimpiarActionPerformed(evt);
            }
        });

        btnregistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Files-New-File-icon.png"))); // NOI18N
        btnregistrar.setText("Registrar");
        btnregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistrarActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Estado:");

        cbestado.setBackground(new java.awt.Color(204, 204, 204));
        cbestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        cbestado.setSelectedItem("EN ESPERA");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Nombres/Razón:");

        txtnombre_razon.setBackground(new java.awt.Color(204, 204, 204));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("DNI/RUC:");

        txtdni_ruc.setBackground(new java.awt.Color(204, 204, 204));

        JTADescripcion.setBackground(new java.awt.Color(204, 204, 204));
        JTADescripcion.setColumns(15);
        JTADescripcion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        JTADescripcion.setForeground(new java.awt.Color(0, 102, 153));
        JTADescripcion.setRows(2);
        jScrollPane1.setViewportView(JTADescripcion);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Descripción Caso:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Área Trabajo:");

        txtarea.setBackground(new java.awt.Color(204, 204, 204));

        cbformato.setBackground(new java.awt.Color(204, 204, 204));
        cbformato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MT2", "MT3" }));
        cbformato.setSelectedItem("EN ESPERA");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Costo:");

        txtcosto.setBackground(new java.awt.Color(204, 204, 204));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Telf/Cel:");

        txttelf_celu.setBackground(new java.awt.Color(204, 204, 204));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Correo:");

        txtcorreo.setBackground(new java.awt.Color(204, 204, 204));

        btnexportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/exchange.png"))); // NOI18N
        btnexportar.setText("Exportar");
        btnexportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportarActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("SERVICIOS COTIZADOS");

        jcdesinsectacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcdesinsectacion.setText("Desinsectación");

        jcDesinfeccion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcDesinfeccion.setText("Desinfección");

        jcdesratizacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcdesratizacion.setText("Desratización");

        jclimpiezareservorios.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jclimpiezareservorios.setText("Limpieza y Desinfección de Reservorios de Agua");

        jclimpiezaTanques.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jclimpiezaTanques.setText("Limpieza de Tanques Septicos");

        jclimpiezaAmbientes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jclimpiezaAmbientes.setText("Limpieza de Ambientes");

        jcventaExtintores.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcventaExtintores.setText("Venta de Extintores");
        jcventaExtintores.setEnabled(false);

        jcrecargaExtintores.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcrecargaExtintores.setText("Recarga y Mantenimiento de Extintores");
        jcrecargaExtintores.setEnabled(false);

        jclimpiezaTrampasGrasa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jclimpiezaTrampasGrasa.setText("Limpieza de Trampas de Grasa");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbtipContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtidreg, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(10, 10, 10)
                                    .addComponent(cbestado, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(6, 6, 6)
                                            .addComponent(lblusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel14)
                                            .addGap(3, 3, 3)
                                            .addComponent(txtnombre_razon, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(jLabel4)
                                            .addGap(79, 79, 79)
                                            .addComponent(jLabel5)
                                            .addGap(10, 10, 10)
                                            .addComponent(lbldate, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(124, 124, 124)
                                            .addComponent(jLabel15)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtdni_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel16)
                                        .addComponent(jLabel17))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtarea, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(10, 10, 10)
                                            .addComponent(cbformato, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel19)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txttelf_celu, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(90, 90, 90))
                                        .addComponent(jScrollPane1)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel12)
                                            .addGap(5, 5, 5))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel18)
                                                .addComponent(jLabel13))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txthora, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtcosto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnhora, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnfecha)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel20))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(btnregistrar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnmodificar)
                                    .addGap(10, 10, 10)
                                    .addComponent(btnlimpiar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnbuscar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnexportar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jcdesratizacion)
                                        .addComponent(jcDesinfeccion)
                                        .addComponent(jcdesinsectacion))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jclimpiezareservorios)
                                        .addComponent(jclimpiezaTanques)
                                        .addComponent(jclimpiezaAmbientes))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jclimpiezaTrampasGrasa)
                                        .addComponent(jcrecargaExtintores)
                                        .addComponent(jcventaExtintores)))))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblusuario)
                            .addComponent(jLabel5)
                            .addComponent(lbldate))))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel9))
                    .addComponent(cbtipContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel10))
                    .addComponent(cbestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel7))
                    .addComponent(txtidreg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnombre_razon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtdni_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(cbformato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txttelf_celu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnfecha)
                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel20)
                    .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txthora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnhora))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtcosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addGap(14, 14, 14)
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
                        .addComponent(jclimpiezareservorios))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jcventaExtintores)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcrecargaExtintores)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jclimpiezaTrampasGrasa)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnsalir)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnmodificar)
                        .addComponent(btnregistrar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnlimpiar)
                        .addComponent(btnbuscar)
                        .addComponent(btnexportar)))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnfechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfechaActionPerformed

    try 
    {
        evn.write(lblusuario.getText(), "Abrio el selector de fechas", "JIFRegistrarContacto -> JDCalendar", "Botón 'Fecha' presionado");
        GL_JDCalendar ca = new GL_JDCalendar(form, true,"D");
        ca.formulario="REG_CONT";
        ca.setVisible(true);

    } catch (Exception e) 
        {
            lc.write("No se pudo abrir el selector de fechas debido a un error inesperado", "JIFRegistrarContacto -> boton Fecha", e);
        }
     
    }//GEN-LAST:event_btnfechaActionPerformed

    private void btnhoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhoraActionPerformed

    try 
    {
        evn.write(lblusuario.getText(), "Abrio el selector de horas", "JIFRegistrarContacto -> JDCalendar", "Botón 'Fecha' presionado");
        GL_JDCalendar ca = new GL_JDCalendar(form, true,"T");
        ca.formulario="REG_CONT";
        ca.setVisible(true);

    } catch (Exception e) 
        {
            lc.write("No se pudo abrir el selector de fechas debido a un error inesperado", "JIFRegistrarContacto -> boton Fecha", e);
        }
      
    }//GEN-LAST:event_btnhoraActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed

       

    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed

       

    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
                                
    try 
    {           
      evn.write(lblusuario.getText(), "Ha salido del formulario 'JIFRegistroContacto'", this.getTitle(), "Presiono Botón 'Salir'");
      JFRPrincipal.JSMRegistroContactos.setActionCommand("CERRADO");
      this.dispose();
    } catch (Exception e) 
        {
            lc.write("No se ha podido cerrar el formulario 'JIFGenerarCertificado' debido a un error inesperado", this.getTitle(), e);
        }    
        
    
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiarActionPerformed

        SYSAUDIOCON sysau = new SYSAUDIOCON();
        sysau.E_PUSH();
        clearCacheDB();
        txtidreg.setText("");
        clear_frm();
        Nuevo();
        
    }//GEN-LAST:event_btnlimpiarActionPerformed

    private void btnregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistrarActionPerformed

    try 
    {
        JFrame jf = new JFrame();
        jf.setAlwaysOnTop(true);
        SYSAUDIOCON sysau = new SYSAUDIOCON();
              
        if(btnregistrar.getText().equals("Registrar"))
        {
            enadisa_Pburronts(true, false, true, false, false, true);
            enadisa_Pfechas(true,true);
            edit_frm(true);
            
            //OBTENCION DE DATOS
            String tipo_contacto = cbtipContacto.getSelectedItem().toString();
            String estado = cbestado.getSelectedItem().toString();
            String id_reg = txtidreg.getText().toUpperCase();
            String nom = txtnombre_razon.getText().toUpperCase();
            String dni_ruc = txtdni_ruc.getText().toUpperCase();
            String Descripcion = JTADescripcion.getText().toUpperCase();
            String area = txtarea.getText();
            String formato = cbformato.getSelectedItem().toString();
            String telf_cel = txttelf_celu.getText();
            String correo = txtcorreo.getText();
            String fecha = txtfecha.getText();
            String hora = txthora.getText();
            String costo = txtcosto.getText();
            
            if(nom.equals(""))
            {
                sysau.E_ERROR();
                JOptionPane.showMessageDialog(jf, "Ingrese al menos un nombre del contacto", "Ingrese Nombre", JOptionPane.ERROR_MESSAGE);
            }
            else
                {
                    //CUERPO
                    P_REGCON.InsertarRegistro(id_reg,tipo_contacto,estado,nom,dni_ruc,Descripcion,area,formato,telf_cel,correo,fecha,hora,costo,mw.hour_actual(),mw.fecha_actual(),lblusuario.getText());
                    //DETALLE

                    if(jcdesinsectacion.isSelected()){P_REGCON.InsertarDETRegistro(id_reg, "SERV-001");} //DESINSECTACION
                    if(jcDesinfeccion.isSelected()){P_REGCON.InsertarDETRegistro(id_reg, "SERV-003");} //DESINFECCION
                    if(jcdesratizacion.isSelected()){P_REGCON.InsertarDETRegistro(id_reg, "SERV-002");} //DESRATIZACION
                    if(jclimpiezaAmbientes.isSelected()){P_REGCON.InsertarDETRegistro(id_reg, "SERV-005");} //LIMPIEZA DE AMBIENTES
                    if(jclimpiezaTanques.isSelected()){P_REGCON.InsertarDETRegistro(id_reg, "SERV-006");} //LIMPIENZA TANQUES
                    if(jclimpiezareservorios.isSelected()){P_REGCON.InsertarDETRegistro(id_reg, "SERV-004");} //LIMPIEZA RESERVORIOS
                    if(jclimpiezaTrampasGrasa.isSelected()){P_REGCON.InsertarDETRegistro(id_reg, "SERV-007");} //LIMPIEZA RESERVORIOS

                    evn.write(lblusuario.getText(), "Registro un nuevo registro de contacto", this.getTitle(), "Botón 'Registrar' Presionado");

                    rslt.write(lblusuario.getText(), this.getTitle(), "REGISTRO", "Se ha registrado el Registro de Contacto con ID  "+id_reg+"\n NOMBRE/RAZÓN: "+nom+"\n");

                    sysau.E_INFORMATION();
                    JOptionPane.showMessageDialog(jf, "Registro Insertado con exito!", "Registro Insertado", JOptionPane.INFORMATION_MESSAGE);

                    clearCacheDB();
                    clear_frm();
                    enadisa_Pburronts(true, false, true, true, false, true);
                    enadisa_Pfechas(true,true);
                    Nuevo();
                }
        }else
            {
                //NOTHING FOR THE MOMENT
            }
    } catch (HeadlessException | SecurityException e) 
      {
          lc.write("ha ocurrido un error al intentar registrar un nuevo registro de contacto", this.getTitle(), e);
      }

    }//GEN-LAST:event_btnregistrarActionPerformed

    private void btnexportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportarActionPerformed
        
        
        
    }//GEN-LAST:event_btnexportarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea JTADescripcion;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnexportar;
    private javax.swing.JButton btnfecha;
    private javax.swing.JButton btnhora;
    private javax.swing.JButton btnlimpiar;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JButton btnregistrar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox<String> cbestado;
    private javax.swing.JComboBox<String> cbformato;
    private javax.swing.JComboBox<String> cbtipContacto;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JCheckBox jcDesinfeccion;
    public static javax.swing.JCheckBox jcdesinsectacion;
    public static javax.swing.JCheckBox jcdesratizacion;
    public static javax.swing.JCheckBox jclimpiezaAmbientes;
    public static javax.swing.JCheckBox jclimpiezaTanques;
    public static javax.swing.JCheckBox jclimpiezaTrampasGrasa;
    public static javax.swing.JCheckBox jclimpiezareservorios;
    public static javax.swing.JCheckBox jcrecargaExtintores;
    public static javax.swing.JCheckBox jcventaExtintores;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lblusuario;
    public static javax.swing.JTextField txtarea;
    public static javax.swing.JTextField txtcorreo;
    public static javax.swing.JTextField txtcosto;
    public static javax.swing.JTextField txtdni_ruc;
    public static javax.swing.JTextField txtfecha;
    public static javax.swing.JTextField txthora;
    public static javax.swing.JTextField txtidreg;
    public static javax.swing.JTextField txtnombre_razon;
    public static javax.swing.JTextField txttelf_celu;
    // End of variables declaration//GEN-END:variables
}
