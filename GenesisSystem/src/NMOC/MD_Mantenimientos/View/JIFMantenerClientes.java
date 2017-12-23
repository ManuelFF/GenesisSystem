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
import NMOC.MD_Mantenimientos.Core.NOB_cliente;
import NMOC.MD_Mantenimientos.IC.MICROCON_MantenerClientes;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Manuel
 */
public class JIFMantenerClientes extends javax.swing.JInternalFrame 
{

    /**
     * Creates new form JIFGestionarCliente
     */
    
    MICROCON_MantenerClientes CliNCon = new MICROCON_MantenerClientes();
    LOG lc = new LOG();
    EVENTS evn = new EVENTS();
    SYSFRMCON sysfrm = new SYSFRMCON();
    JFrame form;
    Color ColorInicial;

    DefaultTableModel modelo_Cli_NATU = new DefaultTableModel()
    {
        @Override
        public boolean isCellEditable(int row , int col)
        {
             return false;
        }
    };
    
    private TableRowSorter trsFiltro;
    protected String IDCLI;
    
    
    public JIFMantenerClientes() 
    {
        initComponents();
        this.setTitle(sysfrm.T_MantenerClientes());
        sysfrm.B_MantenerClientes(this.getContentPane());
        sysfrm.B_MantenerClientes(JPNatural);
        sysfrm.B_MantenerClientes(JPJuridico);

        lblusuario.setText(JFRPrincipal.JMSesion.getText());
        //lblusuario1.setText(JFRPrincipal.JMSesion.getText());
        fecha_actual();
               
        JTNatural.setModel(modelo_Cli_NATU);
        JTNatural.addMouseListener(new MouseAdapter(){});
        JTNatural.getTableHeader().setReorderingAllowed(false);
        trsFiltro = new TableRowSorter(JTNatural.getModel());
        CliNCon.CargarCliNatu(modelo_Cli_NATU, JTNatural);
        JTNatural.setRowSorter(trsFiltro);
        JTAdireccion.setLineWrap(true);
        ColorInicial = txtnombres.getBackground();

    }

    protected void filtro() 
    {
          int columnaABuscar = 0;
          
          if(cbfiltro.getSelectedItem().toString().equals("Nombres"))
          {
              columnaABuscar = 1;
          }
          if (cbfiltro.getSelectedItem().toString().equals("ID")) 
          {
              columnaABuscar = 0;
          }
          
          
          trsFiltro.setRowFilter(RowFilter.regexFilter(txtfiltro.getText(), columnaABuscar));
     }    
    
    protected final void fecha_actual()
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
        //lbldate1.setText(compl);
    }

    private void NuevoCodigoCLI()
    {
        String codigo  = String.format("%08d", 1);
	int cod ; 
        NOB_cliente ultObjeto = null;
				
	if( CliNCon.tamaño()==0 ) // Array vacío
		codigo = "CLI-"+codigo;
	else {
		// La posición del último objeto se obtiene con tamaño()-1
		// Obtenemos el último objeto del ArrayList
		ultObjeto = CliNCon.obtener( CliNCon.tamaño()-1 );
		// Obtenemos los caracteres desde la posicion dos hasta el final ("003"); 
		codigo = ultObjeto.getId_cli().substring(4);
		//Agregamos una unidad al codigo extraido en el paso anterior
		cod = Integer.parseInt(codigo)+1;
		codigo = "CLI-"+String.format("%08d",cod);
              }
	IDCLI = codigo;
    }
    
    
    //Borra y limpia el texto
    private void clearFRM()
    {
        IDCLI = "";
        txtnombres.setText("");
        txtApellidoP.setText("");
        txtApellidoM.setText("");
        txtDNI.setText("");
        txttelefono.setText("");
        txtcelular.setText("");
        txtcorreo.setText("");
        JTAdireccion.setText("");
    }
    
    //Habilita los campos de texto
    private void ena_disaFRM(boolean cond)
    {
        txtnombres.setEnabled(cond);
        txtApellidoP.setEnabled(cond);
        txtApellidoM.setEnabled(cond);
        txtDNI.setEnabled(cond);
        txttelefono.setEnabled(cond);
        txtcelular.setEnabled(cond);
        txtcorreo.setEnabled(cond);
        JTAdireccion.setEnabled(cond);
    }
       
    private void clearCacheDB()
    {
        MWCON mw = new MWCON();
        mw.clear_table(modelo_Cli_NATU, JTNatural);
        CliNCon.CargarCliNatu(modelo_Cli_NATU, JTNatural);
        CliNCon.llenarIDS_CLI();
    }
    
    private void ena_disaButtons(boolean nuevo, boolean modificar, boolean cancelar, boolean salir)
    {
        btnnuevo_N.setEnabled(nuevo);
        btnmodificar_N.setEnabled(modificar);
        btncancelar_N.setEnabled(cancelar);
        btnsalir_N.setEnabled(salir);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JTABPrincipal = new javax.swing.JTabbedPane();
        JPNatural = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTNatural = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblusuario = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbldate = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtnombres = new javax.swing.JTextField();
        txtApellidoP = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtApellidoM = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        txtcelular = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtcorreo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbfiltro = new javax.swing.JComboBox<>();
        txtfiltro = new javax.swing.JTextField();
        btnnuevo_N = new javax.swing.JButton();
        btnmodificar_N = new javax.swing.JButton();
        btncancelar_N = new javax.swing.JButton();
        btnsalir_N = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        JTAdireccion = new javax.swing.JTextArea();
        JPJuridico = new javax.swing.JPanel();

        JTNatural.setModel(new javax.swing.table.DefaultTableModel(
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
        JTNatural.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JTNatural.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTNaturalMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTNatural);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Resultado busqueda:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("usuario:");

        lblusuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblusuario.setText("jLabel3");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("CLIENTES NATURALES");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Fecha Sistema:");

        lbldate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbldate.setText("18/12/2017");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Nombres");

        txtnombres.setEditable(false);
        txtnombres.setBackground(new java.awt.Color(204, 204, 204));

        txtApellidoP.setEditable(false);
        txtApellidoP.setBackground(new java.awt.Color(204, 204, 204));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Apellido Paterno:");

        txtApellidoM.setEditable(false);
        txtApellidoM.setBackground(new java.awt.Color(204, 204, 204));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Apellido Materno:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("DNI:");

        txtDNI.setEditable(false);
        txtDNI.setBackground(new java.awt.Color(204, 204, 204));

        txtcelular.setEditable(false);
        txtcelular.setBackground(new java.awt.Color(204, 204, 204));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Celular:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Correo:");

        txtcorreo.setEditable(false);
        txtcorreo.setBackground(new java.awt.Color(204, 204, 204));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Telefono:");

        txttelefono.setEditable(false);
        txttelefono.setBackground(new java.awt.Color(204, 204, 204));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Dirección:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel15.setText("Buscar:");

        cbfiltro.setBackground(new java.awt.Color(204, 204, 204));
        cbfiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombres", "ID" }));

        txtfiltro.setBackground(new java.awt.Color(204, 204, 204));
        txtfiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfiltroKeyTyped(evt);
            }
        });

        btnnuevo_N.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Files-New-File-icon.png"))); // NOI18N
        btnnuevo_N.setText("Nuevo");
        btnnuevo_N.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevo_NActionPerformed(evt);
            }
        });

        btnmodificar_N.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/EDIT.png"))); // NOI18N
        btnmodificar_N.setText("Modificar");
        btnmodificar_N.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificar_NActionPerformed(evt);
            }
        });

        btncancelar_N.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Windows-Close-Program-icon.png"))); // NOI18N
        btncancelar_N.setText("Cancelar");
        btncancelar_N.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar_NActionPerformed(evt);
            }
        });

        btnsalir_N.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/logout-icon24.png"))); // NOI18N
        btnsalir_N.setText("Salir");
        btnsalir_N.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalir_NActionPerformed(evt);
            }
        });

        JTAdireccion.setEditable(false);
        JTAdireccion.setBackground(new java.awt.Color(204, 204, 204));
        JTAdireccion.setColumns(20);
        JTAdireccion.setRows(5);
        jScrollPane5.setViewportView(JTAdireccion);

        javax.swing.GroupLayout JPNaturalLayout = new javax.swing.GroupLayout(JPNatural);
        JPNatural.setLayout(JPNaturalLayout);
        JPNaturalLayout.setHorizontalGroup(
            JPNaturalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNaturalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPNaturalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNaturalLayout.createSequentialGroup()
                        .addGroup(JPNaturalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(JPNaturalLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtApellidoP, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JPNaturalLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtnombres, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JPNaturalLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JPNaturalLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcelular, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JPNaturalLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(JPNaturalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPNaturalLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtApellidoM, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(JPNaturalLayout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(JPNaturalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(JPNaturalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JPNaturalLayout.createSequentialGroup()
                                        .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane5)))))
                    .addGroup(JPNaturalLayout.createSequentialGroup()
                        .addComponent(btnnuevo_N)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnmodificar_N)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btncancelar_N)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnsalir_N)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(JPNaturalLayout.createSequentialGroup()
                        .addGroup(JPNaturalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(JPNaturalLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                                .addComponent(jLabel5)))
                        .addGap(10, 10, 10)
                        .addComponent(lbldate, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPNaturalLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfiltro)))
                .addContainerGap())
        );
        JPNaturalLayout.setVerticalGroup(
            JPNaturalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNaturalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPNaturalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblusuario)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(lbldate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPNaturalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtnombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPNaturalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtApellidoP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtApellidoM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPNaturalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPNaturalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNaturalLayout.createSequentialGroup()
                        .addGroup(JPNaturalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtcelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPNaturalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPNaturalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnuevo_N)
                    .addComponent(btnmodificar_N)
                    .addComponent(btncancelar_N)
                    .addComponent(btnsalir_N))
                .addGap(7, 7, 7)
                .addGroup(JPNaturalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNaturalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JTABPrincipal.addTab("Naturales", JPNatural);

        javax.swing.GroupLayout JPJuridicoLayout = new javax.swing.GroupLayout(JPJuridico);
        JPJuridico.setLayout(JPJuridicoLayout);
        JPJuridicoLayout.setHorizontalGroup(
            JPJuridicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 782, Short.MAX_VALUE)
        );
        JPJuridicoLayout.setVerticalGroup(
            JPJuridicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );

        JTABPrincipal.addTab("Juridicos", JPJuridico);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JTABPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JTABPrincipal)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnnuevo_NActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevo_NActionPerformed
        


        try 
        {   
          //EJECUTADO ANTES DE TODA CONDICIONAL
          JFrame jf=new JFrame();
          jf.setAlwaysOnTop(true);
          clearCacheDB();
          JPJuridico.setEnabled(false);
       
          int index = JTABPrincipal.getSelectedIndex();
        

          if(index == 0 ){System.out.println("\nNATURAL\n");}
          else if(index == 1){System.out.println("\nJURIDICO\n");}

          NuevoCodigoCLI();

          System.out.println(""+IDCLI);
          
          evn.write(JFRPrincipal.JMSesion.getText(), "hizo click en el botón nuevo cliente natural ", "JIFMantenerCliente linea 344", "Botón 'Nuevo_Natural' presionado");
          
          if(btnnuevo_N.getText().equals("Nuevo"))
          {
              clearFRM();
              NuevoCodigoCLI();
              ena_disaFRM(true);
              ena_disaButtons(true, false, true, false);
              btnnuevo_N.setText("Insertar");
              
              
          }
          
        } catch (Exception e) 
            {
               lc.write("Error al intentar ingresar un nuevo Cliente Natural", "JIFMantenerCliente linea 344", e);
            }
        

    }//GEN-LAST:event_btnnuevo_NActionPerformed

    private void btnmodificar_NActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificar_NActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmodificar_NActionPerformed

    private void btncancelar_NActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar_NActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncancelar_NActionPerformed

    private void btnsalir_NActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalir_NActionPerformed
        
    try 
    {           
      evn.write(JFRPrincipal.JMSesion.getText(), "Ha salido del formulario 'Mantener Clientes'", "JIFMantenerClientes", "Presiono Botón 'Salir'");
      JFRPrincipal.JSMMantenerClientes.setActionCommand("CERRADO");
      this.dispose();
    } catch (Exception e) 
    {
        lc.write("No se ha podido cerrar el formulario 'Mantener Clientes' debido a un error inesperado", "JIFMantenerClientes", e.getMessage());
    }
        
    }//GEN-LAST:event_btnsalir_NActionPerformed

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
 
    String ArrayTemp[];
    String buff;        
    
    private void JTNaturalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTNaturalMouseClicked

           try 
           {
            
            int row = JTNatural.rowAtPoint(evt.getPoint());
            
            IDCLI = ""+JTNatural.getValueAt(row, 0);
            System.out.println(IDCLI+"\n");
            
             txtDNI.setText(""+JTNatural.getValueAt(row, 2));
            txttelefono.setText(""+JTNatural.getValueAt(row, 3));
            txtcelular.setText(""+JTNatural.getValueAt(row, 4));
            JTAdireccion.setText("");
            JTAdireccion.setText(""+JTNatural.getValueAt(row, 5));
            txtcorreo.setText(""+JTNatural.getValueAt(row, 6));
            
            
            buff =""+JTNatural.getValueAt(row, 1);
            
            ArrayTemp = buff.split("/");
                       
            txtnombres.setText(ArrayTemp[0]);
            txtApellidoP.setText(ArrayTemp[1]);
            txtApellidoM.setText(ArrayTemp[2]);
            
            String select = ArrayTemp[0]+" "+ArrayTemp[1]+" "+ArrayTemp[2];
            
            evn.write(JFRPrincipal.JMSesion.getText(),"Ha seleccionado al cliente natural "+select,"JIFMantenerClientes", "Tabla de Clientes Naturales presionado");

        } catch (Exception e) 
            {
               lc.write("Error al seleccionar cliente natural", "JIFMantenerClientes", e.getMessage());
            }


    }//GEN-LAST:event_JTNaturalMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPJuridico;
    private javax.swing.JPanel JPNatural;
    private javax.swing.JTabbedPane JTABPrincipal;
    private javax.swing.JTextArea JTAdireccion;
    private javax.swing.JTable JTNatural;
    private javax.swing.JButton btncancelar_N;
    private javax.swing.JButton btnmodificar_N;
    private javax.swing.JButton btnnuevo_N;
    private javax.swing.JButton btnsalir_N;
    private javax.swing.JComboBox<String> cbfiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JTextField txtApellidoM;
    private javax.swing.JTextField txtApellidoP;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtcelular;
    private javax.swing.JTextField txtcorreo;
    private javax.swing.JTextField txtfiltro;
    private javax.swing.JTextField txtnombres;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
