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
public class SGL_VIEW_Implementos extends javax.swing.JDialog 
{

    /**
     * Creates new form SGL_VIEW_AreaTrabajo
     */
    
    LOG lc = new LOG();
    EVENTS evn = new EVENTS();
    RESULTS rslt = new RESULTS();
    SYSFRMCON sysfrm = new SYSFRMCON();
    MWCON mw = new MWCON();
    
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
    
    DefaultTableModel modelo_importados = new DefaultTableModel()
    {
        @Override
        public boolean isCellEditable(int row , int col)
        {
             return false;
        }
    }; 
        
    public SGL_VIEW_Implementos(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        this.setLocation(600,100);
        this.setTitle(sysfrm.T_Implementos());
        sysfrm.B_Implementos(this.getContentPane());
        lblusuario.setText(JFRPrincipal.JMSesion.getText());
        lbldate.setText(mw.fecha_actual());
        JTImportados.setModel(modelo);
        JTImplementosImportar.setModel(modelo_importados);
        //COLOR INICIAL
        
        JTImportados.getTableHeader().setReorderingAllowed(false);
        JTImplementosImportar.getTableHeader().setReorderingAllowed(false);
        
        SGL_CTRL_Implementos.CargarImplementos(modelo_importados, JTImplementosImportar);
        MuestraDatos();
    }
    
    //INVOKE del singlaton
    ArrayList<SGL_CL_Implementos> implementos = SGL_CTRL_Implementos.getInstance().getsgl_implementos();

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
        modelo.addColumn("Nom. Implemento");
    }

    private void tamaño_cabecera()
    {
        TableColumnModel columnModel = JTImportados.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(90);
        columnModel.getColumn(1).setPreferredWidth(300);
    }
  
    
    private void listaDatos()
    {
        
        modelo.setRowCount(0);
        for( SGL_CL_Implementos x : implementos)
        {
            Object fila[] = { x.getIdmaq(),x.getNommaq()};
            modelo.addRow(fila); 
           
        }
        modelo.fireTableDataChanged();
    }
    
   private void eliminaobjeto(SGL_CL_Implementos b)
   {
       implementos.remove(b);
   }
   
   private void limpiarObjeto()
   {
       implementos.clear();
   }
     
   private void clear_FRM()
   {
       txtidimplemento.setText("");
       txtnombreImplemento.setText("");
   }
   
   private void ena_disaButtons(boolean generar, boolean quitar, boolean limpiar, boolean salir,boolean ninguno)
   {
       btnimportar.setEnabled(generar);
       btnquitar.setEnabled(quitar);
       btnlimpiar.setEnabled(limpiar);
       btnsalir.setEnabled(salir);
       btnninguno.setEnabled(ninguno);
   }
   
   private SGL_CL_Implementos buscar(String codigo)
    {
        for (SGL_CL_Implementos x : implementos) 
         {
             if(codigo.equals(x.getIdmaq())) // && x.getEstado().equals("A") -> esta parte del codigo sirve para no buscar los inactivos
                    return x;
         }
            return null;	//El elemento buscado no existe
    }
   
    public boolean buscar_validar(String codigo)
    {
        for (SGL_CL_Implementos x : implementos) 
         {
             if(codigo.equals(x.getIdmaq())) // && x.getEstado().equals("A") -> esta parte del codigo sirve para no buscar los inactivos
                    return true;
         }
            return false;	//El elemento buscado no existe
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
        JTImplementosImportar = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtidimplemento = new javax.swing.JTextField();
        txtnombreImplemento = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnimportar = new javax.swing.JButton();
        btnquitar = new javax.swing.JButton();
        btnlimpiar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTImportados = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        btnninguno = new javax.swing.JButton();

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
        jLabel4.setText("Importar Productos");

        JTImplementosImportar.setBackground(new java.awt.Color(204, 204, 204));
        JTImplementosImportar.setModel(new javax.swing.table.DefaultTableModel(
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
        JTImplementosImportar.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JTImplementosImportar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTImplementosImportarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTImplementosImportar);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Código:");

        txtidimplemento.setEditable(false);
        txtidimplemento.setBackground(new java.awt.Color(204, 255, 204));

        txtnombreImplemento.setEditable(false);
        txtnombreImplemento.setBackground(new java.awt.Color(204, 255, 255));
        txtnombreImplemento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreImplementoKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Nom. Implemento:");

        btnimportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Files-New-File-icon.png"))); // NOI18N
        btnimportar.setText("Importar");
        btnimportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimportarActionPerformed(evt);
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

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 0, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Seleccione un Producto a importar");

        JTImportados.setBackground(new java.awt.Color(204, 204, 204));
        JTImportados.setModel(new javax.swing.table.DefaultTableModel(
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
        JTImportados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(JTImportados);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 0, 51));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Productos Actualmente Importados");

        btnninguno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/delete-icon.png"))); // NOI18N
        btnninguno.setText("Sin Implementos");
        btnninguno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnningunoActionPerformed(evt);
            }
        });

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(10, 10, 10)
                        .addComponent(lbldate, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtidimplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtnombreImplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnimportar)
                                .addGap(10, 10, 10)
                                .addComponent(btnninguno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnquitar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnlimpiar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(txtidimplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtnombreImplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnimportar)
                    .addComponent(btnquitar)
                    .addComponent(btnlimpiar)
                    .addComponent(btnsalir)
                    .addComponent(btnninguno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnimportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimportarActionPerformed
 
  try
    {
      //EJECUTADO ANTES DE TODO
      SYSAUDIOCON sysau = new SYSAUDIOCON();

      //EJECUTADO ANTES DE TODA CONDICIONAL
      JFrame jf=new JFrame();
      jf.setAlwaysOnTop(true);
      
      evn.write(lblusuario.getText(), "Hizo click en el botón 'Importar' ", "SGL_VIEW_Implementos", "Botón 'Importar Implemento' Presionado");

      if(txtidimplemento.getText().trim().equals(""))
      {
          sysau.E_ERROR();
          JOptionPane.showMessageDialog(jf, "Seleccione un Implemento a importar de la lista", "Importar", JOptionPane.ERROR_MESSAGE);
      }
      else
      {
        if(btnimportar.getText().equals("Importar"))
        {
            ena_disaButtons(true, false, false, true,false);
            btnimportar.setText("Confirmar");

        }
        else
          {
              //LOGICA DE VERIFICACION

              //OBTENCION DE DATOS
             String id = txtidimplemento.getText();
             if(buscar_validar(id)==false)
             {

              String nomserv = txtnombreImplemento.getText().toUpperCase();

              SGL_CL_Implementos serv = new SGL_CL_Implementos(id, nomserv);
              implementos.add(serv);

              evn.write(lblusuario.getText(), "Importo un Implemento", "SGL_VIEW_Implementos", "Botón 'Importar' Presionado");
              rslt.write(lblusuario.getText(), "SGL_VIEW_Implementos", "IMPORTACIÓN", "Se ha importado el Implemento con ID  "+id+
                                      "\n NOMBRE: "+nomserv);

              btnimportar.setText("Importar");
              sysau.E_INFORMATION();
              listaDatos();
              clear_FRM();
              ena_disaButtons(true, true, true, true,false);
             }
             else
              {  
                sysau.E_CRITICAL_ERROR();
                JOptionPane.showMessageDialog(jf, "Implemento Ya existente en lista", "Importación Fallida", JOptionPane.ERROR_MESSAGE);
              }


          }
      }
    } catch (Exception e)
        {
          lc.write("Ha ocurrido algun error al intentar importar un nuevo Implemento", "SGL_VIEW_Implementos", e);
        }


    }//GEN-LAST:event_btnimportarActionPerformed

    private void btnquitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquitarActionPerformed

     SYSAUDIOCON sysau = new SYSAUDIOCON();
     sysau.E_PUSH();        
     
     int filasel = JTImportados.getSelectedRow();
     
     String IDMAQ = ""+modelo.getValueAt(filasel, 0);    
     eliminaobjeto(buscar(IDMAQ));
     listaDatos();
     
    }//GEN-LAST:event_btnquitarActionPerformed

    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiarActionPerformed

       SYSAUDIOCON sysau = new SYSAUDIOCON();
       sysau.E_PUSH();
        
        limpiarObjeto();
        listaDatos();
        
    }//GEN-LAST:event_btnlimpiarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed

        try
        {
            evn.write(lblusuario.getText(), "Ha salido de la vista 'SGL_VIEW_AreaTrabajo'", "JIFMantenerClientes", "Presiono Botón 'Salir'");
            this.dispose();
        } catch (Exception e)
        {
            lc.write("No se ha podido cerrar la vista 'SGL_VIEW_AreaTrabajo' debido a un error inesperado", "SGL_VIEW_AreaTrabajo", e);
        }

    }//GEN-LAST:event_btnsalirActionPerformed

    private void txtnombreImplementoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreImplementoKeyTyped
        
        char car = evt.getKeyChar();
        if( txtnombreImplemento.getText().length()>=8 ) evt.consume();
        if(( car<'0' || car>'9' )) evt.consume();
        
        
    }//GEN-LAST:event_txtnombreImplementoKeyTyped

    private void JTImplementosImportarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTImplementosImportarMouseClicked

  try 
    {
            int row = JTImplementosImportar.rowAtPoint(evt.getPoint());

            txtidimplemento.setText(""+JTImplementosImportar.getValueAt(row, 0));
            txtnombreImplemento.setText(""+JTImplementosImportar.getValueAt(row, 1));
            
            String select = ""+JTImplementosImportar.getValueAt(row, 1);

            evn.write(lblusuario.getText(),"Ha seleccionado al Implemento "+select,"SGL_VIEW_Implementos", "Tabla de Implementos a importar presionado");
        
    } catch (Exception e) 
     {
        lc.write("Error al seleccionar implementos", "SGL_VIEW_Implementos", e);
     }
        
    }//GEN-LAST:event_JTImplementosImportarMouseClicked

    private void btnningunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnningunoActionPerformed

        SYSAUDIOCON sysau = new SYSAUDIOCON();
        sysau.E_PUSH();
        limpiarObjeto();
        listaDatos();

        txtidimplemento.setText("MAQ-009");
        txtnombreImplemento.setText("NINGUNA");

        String nomserv = txtnombreImplemento.getText().toUpperCase();
        String id = txtidimplemento.getText();
        SGL_CL_Implementos impl = new SGL_CL_Implementos(id, nomserv);
        implementos.add(impl);
        listaDatos();
        ena_disaButtons(false, false, false, true,false);

    }//GEN-LAST:event_btnningunoActionPerformed

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
            java.util.logging.Logger.getLogger(SGL_VIEW_Implementos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SGL_VIEW_Implementos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SGL_VIEW_Implementos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SGL_VIEW_Implementos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SGL_VIEW_Implementos dialog = new SGL_VIEW_Implementos(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable JTImplementosImportar;
    private javax.swing.JTable JTImportados;
    private javax.swing.JButton btnimportar;
    private javax.swing.JButton btnlimpiar;
    private javax.swing.JButton btnninguno;
    private javax.swing.JButton btnquitar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JTextField txtidimplemento;
    private javax.swing.JTextField txtnombreImplemento;
    // End of variables declaration//GEN-END:variables
}
