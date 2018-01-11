/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC;

import ModuleWorker.SYSFRMCON;
import ModuleWorker.View.JFRPrincipal;
import NCLPM.EVENTS;
import NCLPM.LOG;
import NMOC.GLCL.GLVC_JDBuscarCliente;
import NMOC.MD_Generar.View.JIFGenerarOrdenServicio;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author USUARIO
 */
public class GL_JDBuscarCliente extends javax.swing.JDialog 
{

    /**
     * Creates new form GL_JDBuscarCliente
     */
    GLVC_JDBuscarCliente P_CL = new GLVC_JDBuscarCliente();
    LOG lc = new LOG();
    EVENTS evn = new EVENTS();
    SYSFRMCON sysfrm = new SYSFRMCON();
    public String formulario = "";
    private String buftip="";
    
    DefaultTableModel modelo = new DefaultTableModel()
    {
        @Override
        public boolean isCellEditable(int row , int col)
        {
             return false;
        }
    };
    
    private TableRowSorter trsFiltro;
    
    public GL_JDBuscarCliente(java.awt.Frame parent, boolean modal,String tipo_cliente) 
    {
        super(parent, modal);
        initComponents();
        this.setLocation(400,230);
        this.setTitle(sysfrm.T_BuscarCliente());
        sysfrm.B_BuscarCliente(this.getContentPane());
        JTbuscarCliente.setModel(modelo);        
        JTbuscarCliente.addMouseListener(new MouseAdapter(){});
        JTbuscarCliente.getTableHeader().setReorderingAllowed(false);
        trsFiltro = new TableRowSorter(JTbuscarCliente.getModel());
        JTbuscarCliente.setRowSorter(trsFiltro);          

        buftip = tipo_cliente;
        cliente(tipo_cliente);
    }
    
        public void filtro() 
    {
          int columnaABuscar = 0;
          
          if(cbfiltro.getSelectedItem().toString().equals("Nombres/Razón"))
          {
              columnaABuscar = 1;
          }

          if (cbfiltro.getSelectedItem().toString().equals("ID")) 
          {
              columnaABuscar = 0;
          }
          
          
          trsFiltro.setRowFilter(RowFilter.regexFilter(txtfiltro.getText(), columnaABuscar));
     }
    
    
    private void cliente(String tipo_cliente)
    {
        if(tipo_cliente.equals("Natural"))
        {
           P_CL.CargarCliente_N(modelo, JTbuscarCliente);
        }
        
        if(tipo_cliente.equals("Juridica"))
        {
           P_CL.CargarCliente_J(modelo, JTbuscarCliente);
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbfiltro = new javax.swing.JComboBox<>();
        txtfiltro = new javax.swing.JTextField();
        btnsalir_N = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTbuscarCliente = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Resultados de la busqueda:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Buscar:");

        cbfiltro.setBackground(new java.awt.Color(204, 204, 204));
        cbfiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombres/Razón", "ID" }));

        txtfiltro.setBackground(new java.awt.Color(204, 204, 255));
        txtfiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfiltroKeyTyped(evt);
            }
        });

        btnsalir_N.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/logout-icon24.png"))); // NOI18N
        btnsalir_N.setText("Salir");
        btnsalir_N.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalir_NActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("BUSCAR CLIENTE");

        JTbuscarCliente.setBackground(new java.awt.Color(204, 204, 204));
        JTbuscarCliente.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        JTbuscarCliente.setModel(new javax.swing.table.DefaultTableModel(
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
        JTbuscarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTbuscarClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTbuscarCliente);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfiltro))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnsalir_N)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnsalir_N)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtfiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltroKeyTyped

        txtfiltro.addKeyListener(new KeyAdapter()
            {
                @Override
                public void keyReleased(final KeyEvent e)
                {
                    String cadena = (txtfiltro.getText().toUpperCase());
                    txtfiltro.setText(cadena.replace(" ", "/"));

                    repaint();
                    filtro();
                }
            });
    }//GEN-LAST:event_txtfiltroKeyTyped

    private void btnsalir_NActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalir_NActionPerformed

        try
        {
            evn.write(JFRPrincipal.JMSesion.getText(), "Ha salido del formulario 'Buscar Cliente'", "GL_JDBuscarCliente", "Presiono Botón 'Salir'");
            this.dispose();
        } catch (Exception e)
        {
            lc.write("No se ha podido cerrar el formulario 'Buscar Cliente' debido a un error inesperado", "GL_JDBuscarCliente", e);
        }
    }//GEN-LAST:event_btnsalir_NActionPerformed

    String select;
    
    private void JTbuscarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTbuscarClienteMouseClicked

        try {

            int row = JTbuscarCliente.rowAtPoint(evt.getPoint());

            if(formulario.equals("ORDEN_SERV"))
            {
                JIFGenerarOrdenServicio.JTAdetcliente.setText("");
                JIFGenerarOrdenServicio.txtcodCliente.setText(""+JTbuscarCliente.getValueAt(row, 0));
                
                if(buftip.equals("Juridica"))
                {
                    JIFGenerarOrdenServicio.JTAdetcliente.append(
                                                              "Detalles Adicionales: \n"+
                                                              "RUC : "+JTbuscarCliente.getValueAt(row, 2)+
                                                              "\nNOMBRE ENC. : "+JTbuscarCliente.getValueAt(row, 3)+
                                                              "\nDIRECCIÓN : "+JTbuscarCliente.getValueAt(row, 7)
                                                            );
                     JIFGenerarOrdenServicio.txtnombrecliente.setText(""+JTbuscarCliente.getValueAt(row, 1));
                     select = ""+JTbuscarCliente.getValueAt(row, 1);
                    
                    
                    
                }else 
                     if(buftip.equals("Natural"))
                     {
                        JIFGenerarOrdenServicio.JTAdetcliente.append(
                                                              "Detalles Adicionales: \n"+
                                                              "DNI : "+JTbuscarCliente.getValueAt(row, 2)+
                                                              "\nCELULAR : "+JTbuscarCliente.getValueAt(row, 4)+
                                                              "\nDIRECCIÓN : "+JTbuscarCliente.getValueAt(row, 5)
                                                            );
                        
                        String buff =""+JTbuscarCliente.getValueAt(row, 1);
                        String ArrayTemp[] = buff.split("/");
                        if(ArrayTemp[2].equals("-")){ArrayTemp[2]="";}
                        JIFGenerarOrdenServicio.txtnombrecliente.setText(ArrayTemp[0]+" "+ArrayTemp[1]+" "+ArrayTemp[2]);
                        select = ArrayTemp[0]+" "+ArrayTemp[1]+" "+ArrayTemp[2];
                     }
                evn.write(JFRPrincipal.JMSesion.getText(),"Ha seleccionado al Cliente "+select,"GL_JDBuscarCliente", "Tabla de Cliente presionado");
            }

            this.dispose();

        } catch (Exception e)
        {
            lc.write("Error al seleccionar Cliente", "GL_JDBuscarCliente", e);
        }
        
    }//GEN-LAST:event_JTbuscarClienteMouseClicked

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
            java.util.logging.Logger.getLogger(GL_JDBuscarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GL_JDBuscarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GL_JDBuscarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GL_JDBuscarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run() {
                GL_JDBuscarCliente dialog = new GL_JDBuscarCliente(new javax.swing.JFrame(), true,"");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() 
                {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) 
                    {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTbuscarCliente;
    private javax.swing.JButton btnsalir_N;
    private javax.swing.JComboBox<String> cbfiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtfiltro;
    // End of variables declaration//GEN-END:variables
}
