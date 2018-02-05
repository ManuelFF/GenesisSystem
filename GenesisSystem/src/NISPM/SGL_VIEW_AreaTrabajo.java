/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NISPM;

import ModuleWorker.IC.MWCON;
import ModuleWorker.IC.ShakingComponent;
import ModuleWorker.SYSAUDIOCON;
import ModuleWorker.SYSFRMCON;
import ModuleWorker.View.JFRPrincipal;
import NCLPM.EVENTS;
import NCLPM.LOG;
import NCLPM.RESULTS;
import NMOC.MD_Generar.View.JIFGenerarOrdenTrabajo;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author USUARIO
 */
public class SGL_VIEW_AreaTrabajo extends javax.swing.JDialog 
{

    /**
     * Creates new form SGL_VIEW_AreaTrabajo
     */
    
    LOG lc = new LOG();
    EVENTS evn = new EVENTS();
    RESULTS rslt = new RESULTS();
    SYSFRMCON sysfrm = new SYSFRMCON();
    MWCON mw = new MWCON();
    
    double sumaAreaTotalMT2 = 0;
    double IntegerTotalMT2 = 0;
    double sumaAreaTotalMT3 = 0;
    double IntegerTotalMT3 = 0;
    public String formulario;
    Color ColorInicial;

    DefaultTableModel modelo = new DefaultTableModel()
    {
        @Override
        public boolean isCellEditable(int row , int col)
        {
             return false;
        }
    }; 
        
    public SGL_VIEW_AreaTrabajo(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        this.setLocation(400,300);
        this.setTitle(sysfrm.T_GenerarArea());
        sysfrm.B_GenerarArea(this.getContentPane());
        lblusuario.setText(JFRPrincipal.JMSesion.getText());
        lbldate.setText(mw.fecha_actual());
        JTGenerarArea.setModel(modelo);
        
        //COLOR INICIAL
        ColorInicial = txtnumeroambientes.getBackground();
        
        JTGenerarArea.getTableHeader().setReorderingAllowed(false);
        MuestraDatos();
    }
    
    //INVOKE del singlaton
    ArrayList<SGL_CL_AreaTrabajo> areas = SGL_CTRL_AreaTrabajo.getInstance().getSgl_area();

    private void MuestraDatos()
    {
        cabecera();
        tamaño_cabecera();
        listaDatos();

    }

    private void cabecera()
    {
        modelo.setColumnCount(0);
        modelo.addColumn("Código");
        modelo.addColumn("Descripción");
        modelo.addColumn("Núm. Amb.");
        modelo.addColumn("Dimensión Área");
        modelo.addColumn("Formato");
    }

    private void tamaño_cabecera()
    {
        TableColumnModel columnModel = JTGenerarArea.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(90);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(30);
    }
  
    
    private void listaDatos()
    {
        sumaAreaTotalMT2=0;
        sumaAreaTotalMT3=0;
        
        modelo.setRowCount(0);
        for( SGL_CL_AreaTrabajo x : areas)
        {
            Object fila[] = { x.getIdarea(),x.getNombreArea(),x.getNumeroAmbientes(),x.getAreaTrabajar(),x.getFormato()};
            modelo.addRow(fila); 
            if(x.getFormato().equals("MT2"))
            {
                IntegerTotalMT2=Double.parseDouble(x.getAreaTrabajar());
                sumaAreaTotalMT2= sumaAreaTotalMT2+IntegerTotalMT2;
            }
            
            if(x.getFormato().equals("MT3"))
            {
                IntegerTotalMT3=Double.parseDouble(x.getAreaTrabajar());
                sumaAreaTotalMT3= sumaAreaTotalMT3+IntegerTotalMT3;
            }
        }
        modelo.fireTableDataChanged();lblareatotalMt2.setText(""+sumaAreaTotalMT2);lblareatotalMt3.setText(""+sumaAreaTotalMT3);
    }
    
   private void eliminaobjeto(SGL_CL_AreaTrabajo b)
   {
       areas.remove(b);
   }
   
   private void limpiarObjeto()
   {
       areas.clear();
   }
   
   private SGL_CL_AreaTrabajo buscar(String codigo)
    {
        for (SGL_CL_AreaTrabajo x : areas) 
         {
             if(codigo.equals(x.getIdarea())) // && x.getEstado().equals("A") -> esta parte del codigo sirve para no buscar los inactivos
                    return x;
         }
            return null;	//El elemento buscado no existe
    }
   
 
   
   private void nuevoCodigo()
   {
     int cod;
       SGL_CL_AreaTrabajo ultObjeto = null;
        if(areas.size()==0)
        {
           cod=1;
        }
          else
           {
           ultObjeto=areas.get(areas.size()-1); //UltimoObjeto
           cod=Integer.parseInt(ultObjeto.getIdarea())+1;
           }txtidarea.setText(""+cod);
   }
   
   private void edit_FRM(boolean cond)
   {
       txtnumeroambientes.setEditable(cond);
       txtdimensionAreas.setEditable(cond);
       JTAnombreArea.setEditable(cond);
       CBformato.setEnabled(cond);
   }
   
   private void clear_FRM()
   {
       txtidarea.setText("");
       txtnumeroambientes.setText("");
       txtdimensionAreas.setText("");
       JTAnombreArea.setText("");
       CBformato.setSelectedIndex(0);
   }
   
   private void reiniciarColors()
   {
       txtnumeroambientes.setBackground(ColorInicial);
       txtdimensionAreas.setBackground(ColorInicial);
       JTAnombreArea.setBackground(ColorInicial);
   }
   
   private void ena_disaButtons(boolean generar, boolean quitar, boolean limpiar, boolean salir)
   {
       btngenerarArea.setEnabled(generar);
       btnquitar.setEnabled(quitar);
       btnlimpiar.setEnabled(limpiar);
       btnsalir.setEnabled(salir);
   }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        lbldate = new javax.swing.JLabel();
        lblusuario = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTGenerarArea = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        lblareatotalMt2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtidarea = new javax.swing.JTextField();
        txtnumeroambientes = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtdimensionAreas = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTAnombreArea = new javax.swing.JTextArea();
        btngenerarArea = new javax.swing.JButton();
        btnquitar = new javax.swing.JButton();
        btnlimpiar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        CBformato = new javax.swing.JComboBox<>();
        FRTM = new javax.swing.JLabel();
        FRTM1 = new javax.swing.JLabel();
        lblareatotalMt3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Fecha Sistema:");

        lbldate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbldate.setText("18/12/2017");

        lblusuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblusuario.setText("Manuel Fernando Saavedra Benites");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("usuario:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Generar Área de Trabajo");

        JTGenerarArea.setBackground(new java.awt.Color(204, 204, 204));
        JTGenerarArea.setModel(new javax.swing.table.DefaultTableModel(
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
        JTGenerarArea.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(JTGenerarArea);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("ÁREA TOTAL A TRABAJAR : ");

        lblareatotalMt2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblareatotalMt2.setForeground(new java.awt.Color(0, 0, 204));
        lblareatotalMt2.setText("AREATOTAL");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Código:");

        txtidarea.setEditable(false);
        txtidarea.setBackground(new java.awt.Color(204, 255, 204));

        txtnumeroambientes.setEditable(false);
        txtnumeroambientes.setBackground(new java.awt.Color(204, 204, 204));
        txtnumeroambientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnumeroambientesKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Núm. de Ambientes:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Dimensión del Área:");

        txtdimensionAreas.setEditable(false);
        txtdimensionAreas.setBackground(new java.awt.Color(204, 204, 204));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Descripción:");

        JTAnombreArea.setEditable(false);
        JTAnombreArea.setBackground(new java.awt.Color(204, 204, 204));
        JTAnombreArea.setColumns(20);
        JTAnombreArea.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        JTAnombreArea.setRows(5);
        jScrollPane3.setViewportView(JTAnombreArea);

        btngenerarArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Files-New-File-icon.png"))); // NOI18N
        btngenerarArea.setText("Generar Área");
        btngenerarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngenerarAreaActionPerformed(evt);
            }
        });

        btnquitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Windows-Close-Program-icon.png"))); // NOI18N
        btnquitar.setText("Quitar");
        btnquitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnquitarActionPerformed(evt);
            }
        });

        btnlimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/delete-file-icon.png"))); // NOI18N
        btnlimpiar.setText("Limpiar");
        btnlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimpiarActionPerformed(evt);
            }
        });

        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/logout-icon24.png"))); // NOI18N
        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Formato:");

        CBformato.setBackground(new java.awt.Color(204, 204, 204));
        CBformato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MT2", "MT3" }));
        CBformato.setEnabled(false);

        FRTM.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        FRTM.setForeground(new java.awt.Color(0, 0, 204));
        FRTM.setText("MT2");

        FRTM1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        FRTM1.setForeground(new java.awt.Color(0, 0, 204));
        FRTM1.setText("MT3");

        lblareatotalMt3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblareatotalMt3.setForeground(new java.awt.Color(0, 0, 204));
        lblareatotalMt3.setText("AREATOTAL");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(10, 10, 10)
                        .addComponent(lbldate, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtidarea, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtnumeroambientes, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtdimensionAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel11)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(CBformato, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btngenerarArea)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnquitar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnlimpiar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblareatotalMt2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FRTM)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblareatotalMt3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FRTM1)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblusuario)
                    .addComponent(jLabel5)
                    .addComponent(lbldate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtidarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtnumeroambientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtdimensionAreas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(CBformato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 40, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btngenerarArea)
                    .addComponent(btnquitar)
                    .addComponent(btnlimpiar)
                    .addComponent(btnsalir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblareatotalMt3)
                        .addComponent(FRTM1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(lblareatotalMt2)
                        .addComponent(FRTM)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btngenerarAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngenerarAreaActionPerformed
 
 try
    {
      //EJECUTADO ANTES DE TODO
      SYSAUDIOCON sysau = new SYSAUDIOCON();

      //EJECUTADO ANTES DE TODA CONDICIONAL
      JFrame jf=new JFrame();
      jf.setAlwaysOnTop(true);
      
      evn.write(lblusuario.getText(), "Hizo click en el botón 'Generar Área' ", "SGL_VIEW_AreaTrabajo", "Botón 'Generar Área' Presionado");

      if(btngenerarArea.getText().equals("Generar Área"))
      {
          clear_FRM();
          nuevoCodigo();
          edit_FRM(true);
          ena_disaButtons(true, false, false, true);
          btngenerarArea.setText("Generar");
      }
      else
        {
            //LOGICA DE VERIFICACION
            if(txtnumeroambientes.getText().trim().equals(""))
                    {
                       sysau.E_ERROR();
                       txtnumeroambientes.setBackground(Color.RED);
                       ShakingComponent sh_nombre = new ShakingComponent(txtnumeroambientes);
                       sh_nombre.startShake();
                       JOptionPane.showMessageDialog(jf, "Debe especificar la cantidad de ambientes", "Falta Cantidad de Ambientes", JOptionPane.ERROR_MESSAGE);
                       sysau.S_STOP();
                    }else
                        if(txtdimensionAreas.getText().trim().equals(""))
                        {
                            sysau.E_ERROR();
                            txtdimensionAreas.setBackground(Color.RED);
                            ShakingComponent sh_nombre = new ShakingComponent(txtdimensionAreas);
                            sh_nombre.startShake();
                            JOptionPane.showMessageDialog(jf, "Debe especificar la dimensión de la área", "Falta Dimensión de Área", JOptionPane.ERROR_MESSAGE);
                            sysau.S_STOP();
                        }
                    else
                        if(JTAnombreArea.getText().trim().equals(""))
                        {
                            sysau.E_ERROR();
                            JTAnombreArea.setBackground(Color.RED);
                            ShakingComponent sh_nombre = new ShakingComponent(JTAnombreArea);
                            sh_nombre.startShake();
                            JOptionPane.showMessageDialog(jf, "Debe especificar una pequeña descripción del área", "Falta Descripción", JOptionPane.ERROR_MESSAGE);
                            sysau.S_STOP();
                        }else
                            {
                                //OBTENCION DE DATOS
                                String id = txtidarea.getText();
                                String nomArea= JTAnombreArea.getText().toUpperCase();
                                int numAmbientes = Integer.parseInt(txtnumeroambientes.getText());
                                double areaTrabajar = Double.parseDouble(txtdimensionAreas.getText());
                                String formato = CBformato.getSelectedItem().toString();

                                SGL_CL_AreaTrabajo maq = new SGL_CL_AreaTrabajo(id, nomArea, ""+numAmbientes, ""+areaTrabajar,formato);
                                areas.add(maq);
                            
                                evn.write(lblusuario.getText(), "Genero una nueva área", "SGL_VIEW_AreaTrabajo", "Botón 'Generar' Presionado");
                                rslt.write(lblusuario.getText(), "SGL_VIEW_AreaTrabajo", "GENERACIÓN", "Se ha generado el Área con ID  "+id+
                                                        "\n NOMBRE: "+nomArea);
                            
                                btngenerarArea.setText("Generar Área");
                                reiniciarColors();
                                sysau.E_INFORMATION();
                                listaDatos();
                                clear_FRM();
                                edit_FRM(false);
                                ena_disaButtons(true, true, true, true);

                            }
        }
      
    } catch (Exception e)
        {
          lc.write("Ha ocurrido algun error al intentar generar una nueva area de trabajo", "SGL_VIEW_AreaTrabajo", e);
        }


    }//GEN-LAST:event_btngenerarAreaActionPerformed

    private void btnquitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquitarActionPerformed

  try 
    {
        SYSAUDIOCON sysau = new SYSAUDIOCON();
        sysau.E_PUSH();        

        int filasel = JTGenerarArea.getSelectedRow();

        String IDMAQ = ""+modelo.getValueAt(filasel, 0);    
        eliminaobjeto(buscar(IDMAQ));
        listaDatos();

    } catch (Exception e) 
        {
            lc.write("Ha ocurrido un error al intentar quitar una área generada", "SGL_VIEW_AreaTrabajo", e);
        }
     
    }//GEN-LAST:event_btnquitarActionPerformed

    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiarActionPerformed

        try
        {
           SYSAUDIOCON sysau = new SYSAUDIOCON();
           sysau.E_PUSH();
           limpiarObjeto();
           listaDatos();
        } catch (Exception e) 
            {
                lc.write("Ha ocurrido un error al intentar limpiar por completo la tabla de áreas generadas", "SGL_VIEW_AreaTrabajo", e);
            }
        
    }//GEN-LAST:event_btnlimpiarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed

        try
        {
            evn.write(lblusuario.getText(), "Ha salido de la vista 'SGL_VIEW_AreaTrabajo'", "JIFMantenerClientes", "Presiono Botón 'Salir'");
            JIFGenerarOrdenTrabajo.areaM2 = ""+sumaAreaTotalMT2;
            JIFGenerarOrdenTrabajo.areaM3 = ""+sumaAreaTotalMT3;
            this.dispose();
        } catch (Exception e)
        {
            lc.write("No se ha podido cerrar la vista 'SGL_VIEW_AreaTrabajo' debido a un error inesperado", "SGL_VIEW_AreaTrabajo", e);
        }

    }//GEN-LAST:event_btnsalirActionPerformed

    private void txtnumeroambientesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumeroambientesKeyTyped
        
        char car = evt.getKeyChar();
        if( txtnumeroambientes.getText().length()>=100 ) evt.consume();
        if(( car<'0' || car>'9' )) evt.consume();
        
        
    }//GEN-LAST:event_txtnumeroambientesKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(SGL_VIEW_AreaTrabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SGL_VIEW_AreaTrabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SGL_VIEW_AreaTrabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SGL_VIEW_AreaTrabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SGL_VIEW_AreaTrabajo dialog = new SGL_VIEW_AreaTrabajo(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBformato;
    private javax.swing.JLabel FRTM;
    private javax.swing.JLabel FRTM1;
    public static javax.swing.JTextArea JTAnombreArea;
    private javax.swing.JTable JTGenerarArea;
    private javax.swing.JButton btngenerarArea;
    private javax.swing.JButton btnlimpiar;
    private javax.swing.JButton btnquitar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblareatotalMt2;
    private javax.swing.JLabel lblareatotalMt3;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JTextField txtdimensionAreas;
    private javax.swing.JTextField txtidarea;
    private javax.swing.JTextField txtnumeroambientes;
    // End of variables declaration//GEN-END:variables
}
