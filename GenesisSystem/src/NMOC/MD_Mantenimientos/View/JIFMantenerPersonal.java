/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Mantenimientos.View;

import ModuleWorker.IC.MWCON;
import ModuleWorker.SYSFRMCON;
import ModuleWorker.View.JFRPrincipal;
import NCLPM.EVENTS;
import NCLPM.LOG;
import NCLPM.RESULTS;
import NMOC.MD_Mantenimientos.Core.NOB_personal;
import NMOC.MD_Mantenimientos.IC.MICROCON_MantenerPersonal;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author USUARIO
 */
public class JIFMantenerPersonal extends javax.swing.JInternalFrame 
{

    /**
     * Creates new form JIFMantenerPersonal
     */
    
    MICROCON_MantenerPersonal PERcon = new MICROCON_MantenerPersonal();
    LOG lc = new LOG();
    EVENTS evn = new EVENTS();
    RESULTS rslt = new RESULTS();
    SYSFRMCON sysfrm = new SYSFRMCON();
    JFrame form;
    Color ColorInicial;
    boolean condicion_datos = false;
    
    //MODELOS DE JTABLE
    DefaultTableModel modelo_personal = new DefaultTableModel()
    {
        @Override
        public boolean isCellEditable(int row , int col)
        {
             return false;
        }
    };
    //FIN MODELOS JTABLE
    
    private TableRowSorter trsFiltro;
        
    public JIFMantenerPersonal() 
    {
        initComponents();
        this.setTitle(sysfrm.T_MantenerClientes());
        sysfrm.B_MantenerClientes(this.getContentPane());
        lblusuario.setText(JFRPrincipal.JMSesion.getText());
        fecha();
        
        //ESTABLECIENDO MODELO DEL JTABLE
        JTpersonal.setModel(modelo_personal);
        
        //ESTABLECIENDO PROHIBICIOND DE REORGANIZAR LAS TABLAS
        JTpersonal.getTableHeader().setReorderingAllowed(false);
        
        //CREANDO EL ROW FILTER SORTER
        trsFiltro = new TableRowSorter(JTpersonal.getModel());
        
        //ESTABLECIENDO EL ROW FILTER SORTER
        JTpersonal.setRowSorter(trsFiltro);
        
        //CARGANDO PERSONAL
        PERcon.CargarPER(modelo_personal, JTpersonal);
        
        //CARGANDO BOX CAT
        PERcon.CargarBoxCAT(CBcat);
        
        //ESTABLECIENDO LOS LINEWRAP 
        JTAcat.setLineWrap(true);
        JTAdireccion.setLineWrap(true);
        
        //COLOR INICIAL
        ColorInicial = txtnombres.getBackground();
        
        //TAMAÑO CABECERA
        tamaño_cabecera();

    }
    
    private void tamaño_cabecera()
    {
        TableColumnModel columnModel = JTpersonal.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(80);
        columnModel.getColumn(1).setPreferredWidth(240);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(50);
        columnModel.getColumn(5).setPreferredWidth(100);
        columnModel.getColumn(6).setPreferredWidth(90);
    }
    
    private final void fecha()
    {
        Date date = new Date();
        
        DateFormat año_current = new SimpleDateFormat("yyyy");
        DateFormat dia_current = new SimpleDateFormat("dd");
        DateFormat mes_current = new SimpleDateFormat("MM");

        String dia_C = ""+dia_current.format(date);
        String mes_C = ""+mes_current.format(date);
        String año_C = ""+año_current.format(date);
        
        //cambio de tipo
        
        int dia_I=Integer.parseInt(dia_C);
        int mes_I=Integer.parseInt(mes_C);
        int año_I=Integer.parseInt(año_C);
        
        //fin cambio de tipo
        
        String compl = dia_I+"/"+mes_I+"/"+(año_I);
        lbldate.setText(compl);
    }
    
    private void filtro() 
    {
          int columnaABuscar = 0;
          
          if(cbfiltro.getSelectedItem().toString().equals("Nombres"))
          {
              columnaABuscar = 1;
          }
          if(cbfiltro.getSelectedItem().toString().equals("DNI"))
          {
              columnaABuscar = 2;
          }
          if (cbfiltro.getSelectedItem().toString().equals("ID")) 
          {
              columnaABuscar = 0;
          }
          
          
          trsFiltro.setRowFilter(RowFilter.regexFilter(txtfiltro.getText(), columnaABuscar));
    }   
    
    private void NuevoCodigoPER()
    {
        String codigo  = String.format("%03d", 1);
	int cod ; 
        NOB_personal ultObjeto = null;
				
	if( PERcon.tamaño()==0 ) // Array vacío
		codigo = "CLI-"+codigo;
	else {
		// La posición del último objeto se obtiene con tamaño()-1
		// Obtenemos el último objeto del ArrayList
		ultObjeto = PERcon.obtener( PERcon.tamaño()-1 );
		// Obtenemos los caracteres desde la posicion dos hasta el final ("003"); 
		codigo = ultObjeto.getId_personal().substring(4);
		//Agregamos una unidad al codigo extraido en el paso anterior
		cod = Integer.parseInt(codigo)+1;
		codigo = "PER-"+String.format("%03d",cod);
              }
	txtcod.setText(codigo);
    }
    
    //Borra y limpia el texto 
    private void clearFRM()
    {
      txtcod.setText("");
      txtnombres.setText("");
      txtapellidos.setText("");
      txtdni.setText("");
      txttelefono.setText("");
      txtcelular.setText("");
      txtcorreo.setText("");
      JTAdireccion.setText("");
      JTAcat.setText("");
      CBestado.setSelectedIndex(0);
    }
    
    //Habilita los campos de texto
    private void editFRM(boolean cond)
    {
      txtcod.setEditable(cond);
      txtnombres.setEditable(cond);
      txtapellidos.setEditable(cond);
      txtdni.setEditable(cond);
      txttelefono.setEditable(cond);
      txtcelular.setEditable(cond);
      txtcorreo.setEditable(cond);
      JTAdireccion.setEditable(cond);
      CBestado.setEnabled(cond);
    }
    
    //Borra cache DB
    private void clearCacheDB()
    {
        try 
        {
            MWCON mw = new MWCON();
            mw.clear_table(modelo_personal, JTpersonal);
            PERcon.llenarIDS_PER();
            PERcon.CargarPER(modelo_personal, JTpersonal);
            tamaño_cabecera();
        } catch (Exception e) 
            {
                lc.write("Error al intentar borrar la cache de la DB", "JIFmantenerPersonal", e);
            }
    }
    
    //ENABLE BOTONES
    private void ena_disaButtons(boolean nuevo, boolean modificar, boolean cancelar, boolean salir)
    {
        btnnuevo.setEnabled(nuevo);
        btnmodificar.setEnabled(modificar);
        btncancelar.setEnabled(cancelar);
        btnsalir.setEnabled(salir);
    }
    private void ena_disaCAT(boolean cat)
    {
        btnregistrar_cat.setEnabled(cat);
        btnquitar_cat.setEnabled(cat);
        btnconsultar_cat.setEnabled(cat);
        JTAcat.setEnabled(cat);
        CBcat.setEnabled(cat);
    }
    
    //REINICIAR COLORES
    private void reiniciarColors()
    {
        txtnombres.setBackground(ColorInicial);
        txtapellidos.setBackground(ColorInicial);
        txtdni.setBackground(ColorInicial);
        txttelefono.setBackground(ColorInicial);
        txtcelular.setBackground(ColorInicial);
        txtcorreo.setBackground(ColorInicial);
        JTAdireccion.setBackground(ColorInicial);
        CBestado.setBackground(ColorInicial);
    }
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbldate = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblusuario = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTpersonal = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbfiltro = new javax.swing.JComboBox<>();
        txtfiltro = new javax.swing.JTextField();
        btnconsultar_cat = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtcod = new javax.swing.JTextField();
        txtnombres = new javax.swing.JTextField();
        txtdni = new javax.swing.JTextField();
        txtcelular = new javax.swing.JTextField();
        CBestado = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        JTAdireccion = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        txtapellidos = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtcorreo = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        CBcat = new javax.swing.JComboBox<>();
        btnnuevo = new javax.swing.JButton();
        btnregistrar_cat = new javax.swing.JButton();
        btnquitar_cat = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTAcat = new javax.swing.JTextArea();
        jSeparator3 = new javax.swing.JSeparator();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbldate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbldate.setText("18/12/2017");
        getContentPane().add(lbldate, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 96, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("usuario:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblusuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblusuario.setText("jLabel3");
        getContentPane().add(lblusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 10, 300, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("MANTENIMIENTO PERSONAL");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 31, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Fecha Sistema:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("ID PERSONAL:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 59, -1, -1));

        JTpersonal.setBackground(new java.awt.Color(204, 204, 204));
        JTpersonal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        JTpersonal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTpersonalMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTpersonal);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 372, 820, 150));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Resultado busqueda:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 351, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel15.setText("Buscar:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        cbfiltro.setBackground(new java.awt.Color(204, 204, 204));
        cbfiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombres", "DNI", "ID" }));
        getContentPane().add(cbfiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 320, 128, -1));

        txtfiltro.setBackground(new java.awt.Color(204, 204, 255));
        txtfiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfiltroKeyTyped(evt);
            }
        });
        getContentPane().add(txtfiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 320, 620, -1));

        btnconsultar_cat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/search-icon.png"))); // NOI18N
        btnconsultar_cat.setText("Consultar");
        btnconsultar_cat.setEnabled(false);
        btnconsultar_cat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconsultar_catActionPerformed(evt);
            }
        });
        getContentPane().add(btnconsultar_cat, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 280, -1, -1));

        btnmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/EDIT.png"))); // NOI18N
        btnmodificar.setText("Modificar");
        btnmodificar.setEnabled(false);
        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnmodificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 276, -1, -1));

        btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Windows-Close-Program-icon.png"))); // NOI18N
        btncancelar.setText("Cancelar");
        btncancelar.setEnabled(false);
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btncancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(224, 276, -1, -1));

        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/logout-icon24.png"))); // NOI18N
        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnsalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(337, 276, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Nombres:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 87, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("DNI:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 118, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Celular:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 149, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Dirección:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 178, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Estado:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 247, -1, -1));

        txtcod.setEditable(false);
        txtcod.setBackground(new java.awt.Color(204, 255, 204));
        getContentPane().add(txtcod, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 54, 207, -1));

        txtnombres.setEditable(false);
        txtnombres.setBackground(new java.awt.Color(204, 204, 204));
        getContentPane().add(txtnombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 85, 207, -1));

        txtdni.setEditable(false);
        txtdni.setBackground(new java.awt.Color(204, 204, 204));
        getContentPane().add(txtdni, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 116, 207, -1));

        txtcelular.setEditable(false);
        txtcelular.setBackground(new java.awt.Color(204, 204, 204));
        getContentPane().add(txtcelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 147, 207, -1));

        CBestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        CBestado.setEnabled(false);
        getContentPane().add(CBestado, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 245, 111, -1));

        JTAdireccion.setEditable(false);
        JTAdireccion.setBackground(new java.awt.Color(204, 204, 204));
        JTAdireccion.setColumns(20);
        JTAdireccion.setRows(5);
        jScrollPane5.setViewportView(JTAdireccion);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 178, 484, 61));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("CATEGORIA");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, -1, -1));

        txtapellidos.setEditable(false);
        txtapellidos.setBackground(new java.awt.Color(204, 204, 204));
        getContentPane().add(txtapellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(383, 85, 214, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Telefono:");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 118, -1, -1));

        txttelefono.setEditable(false);
        txttelefono.setBackground(new java.awt.Color(204, 204, 204));
        getContentPane().add(txttelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(383, 116, 214, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Correo:");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(334, 149, -1, -1));

        txtcorreo.setEditable(false);
        txtcorreo.setBackground(new java.awt.Color(204, 204, 204));
        getContentPane().add(txtcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(383, 147, 214, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, 64, 220, 10));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, 10, 230));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Apellidos:");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 87, -1, -1));

        CBcat.setEnabled(false);
        getContentPane().add(CBcat, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 110, 210, -1));

        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Files-New-File-icon.png"))); // NOI18N
        btnnuevo.setText("Nuevo");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });
        getContentPane().add(btnnuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 276, -1, -1));

        btnregistrar_cat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Accept-icon24.png"))); // NOI18N
        btnregistrar_cat.setText("Registrar");
        btnregistrar_cat.setEnabled(false);
        btnregistrar_cat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistrar_catActionPerformed(evt);
            }
        });
        getContentPane().add(btnregistrar_cat, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, -1, -1));

        btnquitar_cat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/delete-icon.png"))); // NOI18N
        btnquitar_cat.setText("Quitar");
        btnquitar_cat.setEnabled(false);
        btnquitar_cat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnquitar_catActionPerformed(evt);
            }
        });
        getContentPane().add(btnquitar_cat, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 140, -1, -1));

        JTAcat.setEditable(false);
        JTAcat.setBackground(new java.awt.Color(204, 204, 204));
        JTAcat.setColumns(20);
        JTAcat.setFont(new java.awt.Font("Monospaced", 1, 11)); // NOI18N
        JTAcat.setRows(2);
        JTAcat.setTabSize(2);
        JTAcat.setEnabled(false);
        jScrollPane2.setViewportView(JTAcat);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 180, 210, 90));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 820, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtfiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltroKeyTyped

        txtfiltro.addKeyListener(new KeyAdapter()
            {
                @Override
                public void keyReleased(final KeyEvent e)
                {
                    String cadena = (txtfiltro.getText().toUpperCase());
                    txtfiltro.setText(cadena);

                    repaint();
                    filtro();
                }
            });
        
    }//GEN-LAST:event_txtfiltroKeyTyped

    private void btnconsultar_catActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultar_catActionPerformed
        
    }//GEN-LAST:event_btnconsultar_catActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed

    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed

        try
        {
            evn.write(lblusuario.getText(), "Ha cancelado la inserción o modificación de un cliente natural", "JIFMantenerClientes -> Natural", "Botón 'Cancelar' Presionado");
            clearFRM();
            btnconsultar_cat.setText("Nuevo");
            btnmodificar.setText("Modificar");
            clearCacheDB();
            reiniciarColors();
            editFRM(false);
            txtfiltro.setEnabled(true);cbfiltro.setEnabled(true);
            condicion_datos = false;
            ena_disaButtons(true, false, false, true);
            ena_disaCAT(false);

        } catch (Exception e)
        {
            lc.write("Ha ocurrido algun error al intentar cancelar una inserción", "JIFMantenerClientes -> naturales", e);
        }
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed

        try
        {
            evn.write(lblusuario.getText(), "Ha salido del formulario 'Mantener Personal'", "JIFMantenerPersonal", "Presiono Botón 'Salir'");
            JFRPrincipal.JSMMantenerPersonal.setActionCommand("CERRADO");
            this.dispose();
        } catch (Exception e)
        {
            lc.write("No se ha podido cerrar el formulario 'Mantener Personal' debido a un error inesperado", "JIFMantenerPersonal", e);
        }

    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed

        NuevoCodigoPER();
        ena_disaCAT(true);

    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btnregistrar_catActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistrar_catActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnregistrar_catActionPerformed

    private void btnquitar_catActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquitar_catActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnquitar_catActionPerformed

    String ArrayTemp[];
    String buff;  
    
    private void JTpersonalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTpersonalMouseClicked
        
    try 
    {
        if(condicion_datos == false)
        {
            btnmodificar.setEnabled(true);
            btncancelar.setEnabled(true);
            ena_disaCAT(true);

            int row = JTpersonal.rowAtPoint(evt.getPoint());

            txtcod.setText(""+JTpersonal.getValueAt(row, 0));

            txtdni.setText(""+JTpersonal.getValueAt(row, 2));
            txttelefono.setText(""+JTpersonal.getValueAt(row, 3));
            txtcelular.setText(""+JTpersonal.getValueAt(row, 4));
            JTAdireccion.setText(""+JTpersonal.getValueAt(row, 5));
            txtcorreo.setText(""+JTpersonal.getValueAt(row, 6));
            CBestado.setSelectedItem(""+JTpersonal.getValueAt(row, 7));

            buff =""+JTpersonal.getValueAt(row, 1);

            ArrayTemp = buff.split("-");

            txtnombres.setText(ArrayTemp[0]);
            txtapellidos.setText(ArrayTemp[1]);

            String select = ArrayTemp[0]+" "+ArrayTemp[1];

            evn.write(lblusuario.getText(),"Ha seleccionado al Personal "+select,"JIFMantenerPersonal", "Tabla de Personal presionado");
        }
    } catch (Exception e) 
     {
        lc.write("Error al seleccionar personal", "JIFMantenerPersonal", e);
     }

    }//GEN-LAST:event_JTpersonalMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBcat;
    private javax.swing.JComboBox<String> CBestado;
    private javax.swing.JTextArea JTAcat;
    private javax.swing.JTextArea JTAdireccion;
    private javax.swing.JTable JTpersonal;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnconsultar_cat;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnquitar_cat;
    private javax.swing.JButton btnregistrar_cat;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox<String> cbfiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JTextField txtapellidos;
    private javax.swing.JTextField txtcelular;
    private javax.swing.JTextField txtcod;
    private javax.swing.JTextField txtcorreo;
    private javax.swing.JTextField txtdni;
    private javax.swing.JTextField txtfiltro;
    private javax.swing.JTextField txtnombres;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
