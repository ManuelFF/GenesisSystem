/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Consultar.View;

import ModuleWorker.IC.MWCON;
import ModuleWorker.SYSAUDIOCON;
import ModuleWorker.SYSFRMCON;
import ModuleWorker.View.JFRPrincipal;
import NCLPM.EVENTS;
import NCLPM.LOG;
import NCLPM.RESULTS;
import NMOC.GL_JDBuscarCliente;
import NMOC.MD_Consultar.IC.NANOCON_ConsultarCertificados;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author USUARIO
 */
public class JIFConsultarCertificados extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFConsultarCertificados
     */
    
    NANOCON_ConsultarCertificados P_CC = new NANOCON_ConsultarCertificados();
    LOG lc = new LOG();
    EVENTS evn = new EVENTS();
    RESULTS rslt = new RESULTS();
    SYSFRMCON sysfrm = new SYSFRMCON();
    MWCON mw = new MWCON();
    JFrame form; 
    Color colorinicial;
    
    String tipo_cliente;
    String printURL = "";
    
    //MODELOS DE JTABLE
    DefaultTableModel modelo = new DefaultTableModel()
    {
        @Override
        public boolean isCellEditable(int row , int col)
        {
             return false;
        }
    };
    
    private TableRowSorter trsFiltro;
   
    ImageIcon defaultIcon = new ImageIcon("./consultaCertLogo.png");

    
    public JIFConsultarCertificados()
    {
        initComponents();
        this.setTitle(sysfrm.T_ConsultarCertificado());
        sysfrm.B_ConsultarCertificado(this.getContentPane());
        lblusuario.setText(JFRPrincipal.JMSesion.getText());
        lbldate.setText(mw.fecha_actual());
        
        lblpic.setIcon(defaultIcon);
        
        //ESTABLECIENDO MODELO DE JTABLE
        JTConsultar.setModel(modelo);
        
        JTConsultar.getTableHeader().setReorderingAllowed(false);
        
        //CREANDO EL ROW FILTER SORTER
        trsFiltro = new TableRowSorter(JTConsultar.getModel());
        
        //Estableciendo el ROW FILTER SORTER
        JTConsultar.setRowSorter(trsFiltro);

        txtdireccion.setLineWrap(true);
    }
    
    //Tamaño Cabecera Jtable
    private void tamaño_cabecera()
    {
        TableColumnModel columnModel = JTConsultar.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(80);
        columnModel.getColumn(1).setPreferredWidth(80);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(4).setPreferredWidth(200);
        columnModel.getColumn(5).setPreferredWidth(100);
    }
    
    private void cargar_Cert(String cod)
    {
        mw.clear_table(modelo, JTConsultar);
        P_CC.ConsultarCertificado(modelo, JTConsultar, cod);
        tamaño_cabecera();
    }
        
    private void print_service(String dir)
    {
        java.awt.Desktop desktop = java.awt.Desktop.getDesktop(); 
        java.io.File fichero = new java.io.File(dir); 
        if (desktop.isSupported(Desktop.Action.PRINT))
        { 
            try 
            {
                desktop.print(fichero);
            } catch (Exception e)
            {
                System.out.print("El sistema no permite imprimir usando la clase Desktop"); 
                e.printStackTrace();
            }
        }else
        { 
            System.out.print("El sistema no permite imprimir usando la clase Desktop"); 
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

        lblusuario = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbldate = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTConsultar = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnbuscar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtid_cli = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txtdni_ruc = new javax.swing.JTextField();
        txttelefono = new javax.swing.JTextField();
        txtcelular = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtdireccion = new javax.swing.JTextArea();
        btnimprimir = new javax.swing.JButton();
        lblusuario1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblpic = new javax.swing.JLabel();

        lblusuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblusuario.setText("Manuel Fernando Saavedra Benites");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 102));
        jLabel4.setText("Consultar Certificados ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Fecha Sistema:");

        lbldate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbldate.setText("18/12/2017");

        JTConsultar.setBackground(new java.awt.Color(204, 204, 204));
        JTConsultar.setModel(new javax.swing.table.DefaultTableModel(
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
        JTConsultar.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JTConsultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTConsultarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTConsultar);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Detalles del cliente ");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/search-icon.png"))); // NOI18N
        btnbuscar.setText("Buscar Cliente");
        btnbuscar.setContentAreaFilled(false);
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/logout-icon24.png"))); // NOI18N
        btnsalir.setText("Salir");
        btnsalir.setContentAreaFilled(false);
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Código:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Nombre:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("RUC/DNI:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Teléfono:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Celular: ");

        txtid_cli.setEditable(false);
        txtid_cli.setBackground(new java.awt.Color(204, 255, 204));

        txtnombre.setEditable(false);
        txtnombre.setBackground(new java.awt.Color(204, 255, 255));

        txtdni_ruc.setEditable(false);
        txtdni_ruc.setBackground(new java.awt.Color(204, 255, 255));

        txttelefono.setEditable(false);
        txttelefono.setBackground(new java.awt.Color(204, 255, 255));

        txtcelular.setEditable(false);
        txtcelular.setBackground(new java.awt.Color(204, 255, 255));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Dirección:");

        txtdireccion.setEditable(false);
        txtdireccion.setBackground(new java.awt.Color(204, 255, 255));
        txtdireccion.setColumns(20);
        txtdireccion.setRows(5);
        jScrollPane5.setViewportView(txtdireccion);

        btnimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/print-icon.png"))); // NOI18N
        btnimprimir.setText("Imprimir");
        btnimprimir.setContentAreaFilled(false);
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtid_cli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtdni_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txtcelular, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnbuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtid_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtdni_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(txtcelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(8, 62, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnbuscar)
                                    .addComponent(btnsalir)
                                    .addComponent(btnimprimir)))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        lblusuario1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblusuario1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblusuario1.setText("Certificados Generados:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("usuario:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(10, 10, 10)
                        .addComponent(lbldate, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblusuario1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblpic, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblusuario)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(lbldate))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblusuario1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblpic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTConsultarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTConsultarMouseClicked

        try
        {
            
           int row = JTConsultar.rowAtPoint(evt.getPoint());
                     
           ImageIcon imagen = new ImageIcon(P_CC.img(""+JTConsultar.getValueAt(row, 0)));
           
           Image image = imagen.getImage(); // transform it 
            Image newimg = image.getScaledInstance(480, 500,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
           imagen = new ImageIcon(newimg); 
           printURL = ""+P_CC.img(""+JTConsultar.getValueAt(row, 0));
           lblpic.setIcon(imagen);

        } catch (Exception e)
        {
            lc.write("Ha ocurrido un error al intentar cargar imagen en el metodo CLickMouse de la tabla Consultar", "JIFConsultarOrdenesTrabajo", e);
        }

    }//GEN-LAST:event_JTConsultarMouseClicked

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed

        //Modulo de busqueda de ordenes de trabajo V0.9
        try
        {
            lblpic.setIcon(defaultIcon);
            
            JFrame jf=new JFrame();
            jf.setAlwaysOnTop(true);
            evn.write(lblusuario.getText(), "Ha abierto el formulario de busqueda de Clientes", "JIFConsultarCertificados", "Hizo click en el botón 'Buscar'");

            // display the showOptionDialog
            Object[] options = { "JURIDICOS", "NATURALES","CANCELAR"};
            int choice = JOptionPane.showOptionDialog(jf,
                "¿Que tipo de cliente desea buscar?",
                "Tipo de Cliente",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

            // interpret the user's choice
            if (choice == 1)
            {
                //NATURAL
                evn.write(lblusuario.getText(), "Abrio el Buscador de Cliente", "JIFConsultarCertificados -> GL_JDBuscarCliente", "Botón 'Consultar Cliente' presionado");
                txtdireccion.setText("");
                GL_JDBuscarCliente BC = new GL_JDBuscarCliente(form, true,"Natural");
                BC.formulario="GEN_CERT_CON";
                BC.setVisible(true);
                cargar_Cert(txtid_cli.getText());
                tipo_cliente = "Natural";
            }

            if (choice == 0)
            {
                //JURIDICA
                evn.write(lblusuario.getText(), "Abrio el Buscador de Cliente", "JIFConsultarOrdenServicio -> GL_JDBuscarCliente", "Botón 'Consultar Cliente' presionado");
                txtdireccion.setText("");
                GL_JDBuscarCliente BC = new GL_JDBuscarCliente(form, true,"Juridica");
                BC.formulario="GEN_CERT_CON";
                BC.setVisible(true);
                cargar_Cert(txtid_cli.getText());
                tipo_cliente = "Juridica";
            }
            if(choice == 2)
            {
                //NOTHING
            }

            //
        } catch (Exception e)
        {
            lc.write("Problema al intentar abrir el formulario de buscar Clientes", "JIFConsultarCertificados", e);
        }
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed

        try
        {
            evn.write(lblusuario.getText(), "Ha salido del formulario 'JIFConsultarCertificado'", "JIFConsultarCertificado", "Presiono Botón 'Salir'");
            JFRPrincipal.JSMConsultarCertificado.setActionCommand("CERRADO");
            this.dispose();
        } catch (Exception e)
        {
            lc.write("No se ha podido cerrar el formulario 'JIFConsultarCertificado' debido a un error inesperado", "JIFConsultarCertificado", e);
        }
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed

        try 
        {
            JFrame jf=new JFrame();
            jf.setAlwaysOnTop(true);
            SYSAUDIOCON sysau = new SYSAUDIOCON();
            
            if(printURL.equals(""))
            {
                sysau.E_ERROR();
                JOptionPane.showMessageDialog(jf, "Seleccione primero un certificado", "Certificado no Seleccionado", JOptionPane.ERROR_MESSAGE);
                sysau.S_STOP();
            }else
                {
                    evn.write(lblusuario.getText(), "Ha impreso un certificado", "JIFConsultarCertificados", "Botón 'imprimir' presionado");
                    print_service(printURL);
                }
            
        }catch (Exception e) 
          {
              lc.write("Algun error ha ocurrido al intentar imprimir", "JIFConsultarCertificados -> Imprimir", e);
          }
           
    }//GEN-LAST:event_btnimprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTConsultar;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnimprimir;
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lblpic;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JLabel lblusuario1;
    public static javax.swing.JTextField txtcelular;
    public static javax.swing.JTextArea txtdireccion;
    public static javax.swing.JTextField txtdni_ruc;
    public static javax.swing.JTextField txtid_cli;
    public static javax.swing.JTextField txtnombre;
    public static javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
