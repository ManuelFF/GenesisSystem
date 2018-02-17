/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Ventas.View;

import ModuleWorker.SYSFRMCON;

/**
 *
 * @author USUARIO
 */
public class JIFRegistroContacto extends javax.swing.JInternalFrame
{

    
    SYSFRMCON sysfrm = new SYSFRMCON();
    
    
    public JIFRegistroContacto() 
    {
        initComponents();
        this.setTitle(sysfrm.T_RegistroContactos());
        sysfrm.B_RegistroContactos(this.getContentPane());
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
        cbestado = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtidorden = new javax.swing.JTextField();
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
        cbestado1 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txtfecha1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtfecha2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTAAnuncio = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtfecha3 = new javax.swing.JTextField();
        cbestado2 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        txtcosto = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtcosto1 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtcosto2 = new javax.swing.JTextField();
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

        cbestado.setBackground(new java.awt.Color(204, 204, 204));
        cbestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CELULAR", "TELEFONO", "CORREO", "CAMPO", "WEB" }));
        cbestado.setSelectedItem("EN ESPERA");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("ID Registro:");

        txtidorden.setEditable(false);
        txtidorden.setBackground(new java.awt.Color(204, 255, 204));

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

        cbestado1.setBackground(new java.awt.Color(204, 204, 204));
        cbestado1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        cbestado1.setSelectedItem("EN ESPERA");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Nombres/Razón:");

        txtfecha1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("DNI/RUC:");

        txtfecha2.setBackground(new java.awt.Color(204, 204, 204));

        JTAAnuncio.setBackground(new java.awt.Color(204, 204, 204));
        JTAAnuncio.setColumns(15);
        JTAAnuncio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        JTAAnuncio.setForeground(new java.awt.Color(0, 102, 153));
        JTAAnuncio.setRows(2);
        jScrollPane1.setViewportView(JTAAnuncio);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Descripción Caso:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Área Trabajo:");

        txtfecha3.setBackground(new java.awt.Color(204, 204, 204));

        cbestado2.setBackground(new java.awt.Color(204, 204, 204));
        cbestado2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MT2", "MT3" }));
        cbestado2.setSelectedItem("EN ESPERA");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Costo:");

        txtcosto.setBackground(new java.awt.Color(204, 204, 204));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Telf/Cel:");

        txtcosto1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Correo:");

        txtcosto2.setBackground(new java.awt.Color(204, 204, 204));

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
                            .addComponent(cbestado, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtidorden, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(10, 10, 10)
                                    .addComponent(cbestado1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                                            .addComponent(txtfecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                            .addComponent(txtfecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel16)
                                        .addComponent(jLabel17))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel20)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtcosto2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(txtfecha3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(10, 10, 10)
                                                    .addComponent(cbestado2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jLabel19)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtcosto1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(266, 266, 266))
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
                                        .addComponent(txtfecha, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                        .addComponent(txthora)
                                        .addComponent(txtcosto))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnfecha)
                                        .addComponent(btnhora, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                    .addComponent(jcventaExtintores)))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(cbestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel10))
                    .addComponent(cbestado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel7))
                    .addComponent(txtidorden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtfecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfecha3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(cbestado2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txtcosto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnfecha)
                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel20)
                    .addComponent(txtcosto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

     
    }//GEN-LAST:event_btnfechaActionPerformed

    private void btnhoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhoraActionPerformed

      
    }//GEN-LAST:event_btnhoraActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed

       

    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed

       

    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed

    
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiarActionPerformed

    }//GEN-LAST:event_btnlimpiarActionPerformed

    private void btnregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistrarActionPerformed


    }//GEN-LAST:event_btnregistrarActionPerformed

    private void btnexportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnexportarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea JTAAnuncio;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnexportar;
    private javax.swing.JButton btnfecha;
    private javax.swing.JButton btnhora;
    private javax.swing.JButton btnlimpiar;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JButton btnregistrar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox<String> cbestado;
    private javax.swing.JComboBox<String> cbestado1;
    private javax.swing.JComboBox<String> cbestado2;
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
    public static javax.swing.JTextField txtcosto;
    public static javax.swing.JTextField txtcosto1;
    public static javax.swing.JTextField txtcosto2;
    public static javax.swing.JTextField txtfecha;
    public static javax.swing.JTextField txtfecha1;
    public static javax.swing.JTextField txtfecha2;
    public static javax.swing.JTextField txtfecha3;
    public static javax.swing.JTextField txthora;
    public static javax.swing.JTextField txtidorden;
    // End of variables declaration//GEN-END:variables
}
