/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Mantenimientos.View;

import ModuleWorker.IC.MWCON;
import ModuleWorker.IC.ShakingComponent;
import ModuleWorker.SYSAUDIOCON;
import ModuleWorker.SYSFRMCON;
import ModuleWorker.View.JFRPrincipal;
import NCLPM.EVENTS;
import NCLPM.LOG;
import NCLPM.RESULTS;
import NMOC.MD_Mantenimientos.Core.NOB_Implementos;
import NMOC.MD_Mantenimientos.IC.MICROCON_MantenerImplementos;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author USUARIO
 */
public class JIFMantenerImplementos extends javax.swing.JInternalFrame 
{

    /**
     * Creates new form JIFMantenerImplementoss
     */
    
    MICROCON_MantenerImplementos MAQcon = new MICROCON_MantenerImplementos();
    LOG lc = new LOG();
    EVENTS evn = new EVENTS();
    RESULTS rslt = new RESULTS();
    SYSFRMCON sysfrm = new SYSFRMCON();
    MWCON mw = new MWCON();
    Color ColorInicial;
    boolean condicion_datos = false;
    
    //MODELOS DE JTABLE
    DefaultTableModel modelo_implementos = new DefaultTableModel()
    {
        @Override
        public boolean isCellEditable(int row , int col)
        {
             return false;
        }
    };
    //FIN MODELOS JTABLE    
    
    private TableRowSorter trsFiltro;
    
    public JIFMantenerImplementos() 
    {
        initComponents();
        this.setTitle(sysfrm.T_MantenerImplemento());
        sysfrm.B_MantenerImplemento(this.getContentPane());
        lblusuario.setText(JFRPrincipal.JMSesion.getText());
        lbldate.setText(mw.fecha_actual());
        
        //ESTABLECIENDO MODELO DEL JTABLE
        JTImplemento.setModel(modelo_implementos);
        
        //ESTABLECIENDO PROHIBICION DE REORGANIZAR LAS TABLAS
        JTImplemento.getTableHeader().setReorderingAllowed(false);
        
        //CREANDO EL ROW FILTER SORTER
        trsFiltro = new TableRowSorter(JTImplemento.getModel());
        
        //ESTABLECIENDO EL ROW FILTER SORTER
        JTImplemento.setRowSorter(trsFiltro);
        
        //CARGANDO PRODUCTO
        MAQcon.CargarIMPL(modelo_implementos, JTImplemento);
        
        //COLOR INICIAL
        ColorInicial = txtnombres.getBackground();
        
        //TAMAÑO CABECERA
        tamaño_cabecera();
        
    }
    
    private void tamaño_cabecera()
    {
        TableColumnModel columnModel = JTImplemento.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(80);
        columnModel.getColumn(1).setPreferredWidth(240);
        columnModel.getColumn(2).setPreferredWidth(80);
    }
    
    private void filtro() 
    {
          int columnaABuscar = 0;
          
          if(cbfiltro.getSelectedItem().toString().equals("Nombre"))
          {
              columnaABuscar = 1;
          }
          if (cbfiltro.getSelectedItem().toString().equals("ID")) 
          {
              columnaABuscar = 0;
          }
          trsFiltro.setRowFilter(RowFilter.regexFilter(txtfiltro.getText(), columnaABuscar));
    }   
    
    private void NuevoCodigoMAQ()
    {
        String codigo  = String.format("%03d", 1);
	int cod ; 
        NOB_Implementos ultObjeto = null;
				
	if( MAQcon.tamaño()==0 ) // Array vacío
		codigo = "MAQ-"+codigo;
	else {
		// La posición del último objeto se obtiene con tamaño()-1
		// Obtenemos el último objeto del ArrayList
		ultObjeto = MAQcon.obtener(MAQcon.tamaño()-1);
		// Obtenemos los caracteres desde la posicion dos hasta el final ("003"); 
		codigo = ultObjeto.getId_ma().substring(4);
		//Agregamos una unidad al codigo extraido en el paso anterior
		cod = Integer.parseInt(codigo)+1;
		codigo = "MAQ-"+String.format("%03d",cod);
              }
	txtcod.setText(codigo);
    }   
    
    //Borra y limpia el texto
    private void ClearFRM()
    {
        txtcod.setText("");
        txtnombres.setText("");
        CBestado.setSelectedIndex(0);
    }
    
    //Habilita los campos de texto
    private void editFRM(boolean cond)
    {
        txtnombres.setEditable(cond);
        CBestado.setEnabled(cond);
    }
    
       //Borra cache DB
    private void clearCacheDB()
    {
        try 
        {
            MWCON mw = new MWCON();
            mw.clear_table(modelo_implementos, JTImplemento);
            MAQcon.llenarIDS_PRO();
            MAQcon.CargarIMPL(modelo_implementos, JTImplemento);
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
    
    //REINICIAR COLOREs
    private void reiniciarColors()
    {
        txtnombres.setBackground(ColorInicial);
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

        lblusuario = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbldate = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtcod = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtnombres = new javax.swing.JTextField();
        CBestado = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        btnnuevo = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTImplemento = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        cbfiltro = new javax.swing.JComboBox<>();
        txtfiltro = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        lblusuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblusuario.setText("MANUEL FERNANDO SAAVEDRA BENTIES");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Usuario:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Fecha Sistema:");

        lbldate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbldate.setText("18/12/2017");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("MANTENIMIENTO IMPLEMENTOS");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("CODIGO:");

        txtcod.setEditable(false);
        txtcod.setBackground(new java.awt.Color(204, 255, 204));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Nombre:");

        txtnombres.setEditable(false);
        txtnombres.setBackground(new java.awt.Color(204, 204, 204));

        CBestado.setBackground(new java.awt.Color(204, 204, 204));
        CBestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        CBestado.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Estado:");

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

        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/logout-icon24.png"))); // NOI18N
        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        JTImplemento.setBackground(new java.awt.Color(204, 204, 204));
        JTImplemento.setModel(new javax.swing.table.DefaultTableModel(
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
        JTImplemento.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JTImplemento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTImplementoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTImplemento);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel15.setText("Buscar:");

        cbfiltro.setBackground(new java.awt.Color(204, 204, 204));
        cbfiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "ID" }));

        txtfiltro.setBackground(new java.awt.Color(204, 204, 255));
        txtfiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfiltroKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Resultado busqueda:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(103, 103, 103))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBestado, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnombres, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnnuevo)
                                .addGap(10, 10, 10)
                                .addComponent(btnmodificar)
                                .addGap(10, 10, 10)
                                .addComponent(btncancelar)
                                .addGap(10, 10, 10)
                                .addComponent(btnsalir))
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(6, 6, 6)
                                    .addComponent(lblusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel5)
                                    .addGap(10, 10, 10)
                                    .addComponent(lbldate, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addGap(4, 4, 4)
                                    .addComponent(cbfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(lbldate)
                    .addComponent(jLabel2)
                    .addComponent(lblusuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtnombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(CBestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnnuevo)
                    .addComponent(btnmodificar)
                    .addComponent(btncancelar)
                    .addComponent(btnsalir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(cbfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addContainerGap())
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
            
            evn.write(lblusuario.getText(),"Hizo click en el botón 'Nuevo' Impemento"  , "JIFMantenerImplementos", "Botón 'Implemento' presionado");
            
            if(btnnuevo.getText().equals("Nuevo"))
            {
                ClearFRM();
                NuevoCodigoMAQ();
                editFRM(true);
                ena_disaButtons(true, false, true, false);
                btnnuevo.setText("Insertar");
                txtfiltro.setEnabled(false);cbfiltro.setEnabled(false);
                condicion_datos = true;
            }
             else
                {
                    //LOGICA DE VERIFICACION
                   /*
                        la logica de verificacion usada sera LS.
                        la cual significa loica separativa; brevemente lo que hace es
                        verificar cada bloque a la vez en ves de verificar bloque por bloque
                   */
                    if(txtnombres.getText().trim().equals(""))
                    {
                       sysau.E_ERROR();
                       txtnombres.setBackground(Color.RED);
                       ShakingComponent sh_nombre = new ShakingComponent(txtnombres);
                       sh_nombre.startShake();
                       JOptionPane.showMessageDialog(jf, "Es obligatorio el uso de 'Nombre' para el Implemento", "Falta Nombre del Implemento", JOptionPane.ERROR_MESSAGE);
                       sysau.S_STOP();
                    }else
                        {
                            //FIN LOGICA DE VERIFICACIOn
                            //INICIO PROGRAMACION DE INGRESO
                            
                            //OBTENER DATOS
                            String cod = txtcod.getText();
                            String nombre = txtnombres.getText().toUpperCase();
                            String estado = CBestado.getSelectedItem().toString();
                            //FIN OBTENER DATOS
                            
                            //INICIO CONTROLADOR QUE INSERTA
                            MAQcon.InsertarImplementos(cod, nombre, estado);
                            //FIN CONTROLADOR QUE INSERTA
                            
                            evn.write(lblusuario.getText(), "Inserto un nuevo Implemento", "JIFMantenerImplementos", "Botón 'Insertar' Presionado");
                            rslt.write(lblusuario.getText(), "JIFMantenerImplementos", "INSERCIÓN", "Se ha insertado el Implemento con ID  "+cod+
                                                        "\n NOMBRE: "+nombre+" \n ESTADO: "+estado);
                        
                            sysau.E_INFORMATION();
                            JOptionPane.showMessageDialog(jf, "Implemento Insertado con exito!", "Implemento Insertado", JOptionPane.INFORMATION_MESSAGE);
                            ClearFRM();
                            editFRM(false);
                            ena_disaButtons(true, false, false, true);
                            btnnuevo.setText("Nuevo");
                            txtfiltro.setEnabled(true);cbfiltro.setEnabled(true);
                            condicion_datos = false;
                            reiniciarColors();
                            clearCacheDB();
                        }
                }
        } catch (Exception e) 
            {
                lc.write("Ha ocurrido un error al intentar insertar un nuevo Implemento", "JIFMantenerImplementos", e);
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

        evn.write(lblusuario.getText(),"Hizo click en el botón 'Modificar' Implemento"  , "JIFMantenerImplementos", "Botón 'Modificar' presionado");

        if(btnmodificar.getText().equals("Modificar"))
        {
            editFRM(true);
            ena_disaButtons(false, true, true, false);
            btnmodificar.setText("Actualizar");
            txtfiltro.setEnabled(false);cbfiltro.setEnabled(false);
            condicion_datos = true;
        }
         else
            {
                //LOGICA DE VERIFICACION
               /*
                    la logica de verificacion usada sera LS.
                    la cual significa loica separativa; brevemente lo que hace es
                    verificar cada bloque a la vez en ves de verificar bloque por bloque
               */
                if(txtnombres.getText().trim().equals(""))
                {
                   sysau.E_ERROR();
                   txtnombres.setBackground(Color.RED);
                   ShakingComponent sh_nombre = new ShakingComponent(txtnombres);
                   sh_nombre.startShake();
                   JOptionPane.showMessageDialog(jf, "Es obligatorio el uso de 'Nombre' para el Implemento", "Falta Nombre del Implemento", JOptionPane.ERROR_MESSAGE);
                   sysau.S_STOP();
                }else
                    {
                        //FIN LOGICA DE VERIFICACIOn
                        //INICIO PROGRAMACION DE INGRESO

                        //OBTENER DATOS
                        String cod = txtcod.getText();
                        String nombre = txtnombres.getText().toUpperCase();
                        String estado = CBestado.getSelectedItem().toString();
                        //FIN OBTENER DATOS

                        //INICIO CONTROLADOR QUE INSERTA
                        MAQcon.ModificarImplementos(cod, nombre, estado);
                        //FIN CONTROLADOR QUE INSERTA

                        evn.write(lblusuario.getText(), "Modifico un Implemento", "JIFMantenerImplementos", "Botón 'Actualizar' Presionado");
                        rslt.write(lblusuario.getText(), "JIFMantenerImplementos", "MODIFICACIÓN", "Se ha modificado el Implemento con ID  "+cod+
                                                    "\n NOMBRE: "+nombre+" \n ESTADO: "+estado);

                        sysau.E_INFORMATION();
                        JOptionPane.showMessageDialog(jf, "Implemento Modificado con exito!", "Implemento Modificado", JOptionPane.INFORMATION_MESSAGE);
                        ClearFRM();
                        editFRM(false);
                        ena_disaButtons(true, false, false, true);
                        btnmodificar.setText("Modificar");
                        txtfiltro.setEnabled(true);cbfiltro.setEnabled(true);
                        condicion_datos = false;
                        reiniciarColors();
                        clearCacheDB();
                    }
            }
    } catch (Exception e) 
        {
            lc.write("Ha ocurrido un error al intentar insertar un nuevo Implemento", "JIFMantenerImplementos", e);
        }
       
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed

    try
    {
        evn.write(lblusuario.getText(), "Ha cancelado la inserción o modificación de un Implemento", "JIFMantenerImplementos", "Botón 'Cancelar' Presionado");
        ClearFRM();
        btnnuevo.setText("Nuevo");
        btnmodificar.setText("Modificar");
        clearCacheDB();
        reiniciarColors();
        editFRM(false);
        txtfiltro.setEnabled(true);cbfiltro.setEnabled(true);
        condicion_datos = false;
        ena_disaButtons(true, false, false, true);

    } catch (Exception e)
    {
        lc.write("Ha ocurrido algun error al intentar cancelar una inserción o modificacion", "JIFMantenerImplementos", e);
    }

    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed

    try
    {
        evn.write(lblusuario.getText(), "Ha salido del formulario 'Mantener Implemento'", "JIFMantenerImplementos", "Presiono Botón 'Salir'");
        JFRPrincipal.JSMMantenerImplementos.setActionCommand("CERRADO");
        this.dispose();
    } catch (Exception e)
    {
        lc.write("No se ha podido cerrar el formulario 'Mantener Implemento' debido a un error inesperado", "JIFMantenerImplementos", e);
    }
    
    }//GEN-LAST:event_btnsalirActionPerformed

    private void JTImplementoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTImplementoMouseClicked
                
    try 
    {
        if(condicion_datos == false)
        {
            btnmodificar.setEnabled(true);
            btncancelar.setEnabled(true);

            int row = JTImplemento.rowAtPoint(evt.getPoint());

            txtcod.setText(""+JTImplemento.getValueAt(row, 0));
            txtnombres.setText(""+JTImplemento.getValueAt(row, 1));
            CBestado.setSelectedItem(""+JTImplemento.getValueAt(row, 2));

            String select = ""+JTImplemento.getValueAt(row, 1);

            evn.write(lblusuario.getText(),"Ha seleccionado al Implemento "+select,"JIFMantenerImplementos", "Tabla de Implemento presionado");
        }
    } catch (Exception e) 
     {
        lc.write("Error al seleccionar Implemento", "JIFMantenerImplementos", e);
     }
        
    }//GEN-LAST:event_JTImplementoMouseClicked

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBestado;
    private javax.swing.JTable JTImplemento;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox<String> cbfiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JTextField txtcod;
    private javax.swing.JTextField txtfiltro;
    private javax.swing.JTextField txtnombres;
    // End of variables declaration//GEN-END:variables
}
