/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker.View;

import ModuleWorker.Core.NOB_usuario;
import ModuleWorker.IC.MICROCON_MantenerUsuario;
import ModuleWorker.IC.ShakingComponent;
import ModuleWorker.SYSAUDIOCON;
import ModuleWorker.SYSFRMCON;
import NCLPM.EVENTS;
import NCLPM.LOG;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class JIFMatenerUsuario extends javax.swing.JInternalFrame 
{

    /**
     * Creates new form JIFRegistroUsuarios
     */
    MICROCON_MantenerUsuario mcmuser = new MICROCON_MantenerUsuario();
    LOG lc = new LOG();
    EVENTS evn = new EVENTS();
    SYSFRMCON sysfrm = new SYSFRMCON();
    JFrame form;
    
    DefaultTableModel modelo_usuarios = new DefaultTableModel()
    {
        @Override
        public boolean isCellEditable(int row , int col)
        {
             return false;
        }
    };
       
    public JIFMatenerUsuario() 
    {
        initComponents();
        this.setTitle(sysfrm.T_mantenerUsuario());
        sysfrm.B_mantenerUsuario(this.getContentPane());
        JTusrs.setModel(modelo_usuarios);
        mcmuser.Cargarusuario(modelo_usuarios, JTusrs);
        mcmuser.CargarBoxTipo(CBTipo);
    }
    
    private void NuevoCodigoUSR()
    {
        String codigo  = String.format("%03d", 1);
	int cod ; 
	NOB_usuario ultObjeto = null;
				
	if( mcmuser.tamaño()==0 ) // Array vacío
		codigo = "USR-"+codigo;
	else {
		// La posición del último objeto se obtiene con tamaño()-1
		// Obtenemos el último objeto del ArrayList
		ultObjeto = mcmuser.obtener( mcmuser.tamaño()-1 );
		// Obtenemos los caracteres desde la posicion dos hasta el final ("003"); 
		codigo = ultObjeto.getId_user().substring(4);
		//Agregamos una unidad al codigo extraido en el paso anterior
		cod = Integer.parseInt(codigo)+1;
		codigo = "USR-"+String.format("%03d",cod);
              }
	txtcod.setText(codigo);
    }
    
    protected void clearFRM()
    {
        txtcod.setText("");
        txtidPer.setText("");
        txtnombres.setText("");
        txtusuarios.setText("");
        pswPassword.setText("");
        CBEstado.setSelectedIndex(0);
        CBTipo.setSelectedIndex(0);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pswPassword = new javax.swing.JPasswordField();
        txtusuarios = new javax.swing.JTextField();
        txtnombres = new javax.swing.JTextField();
        txtidPer = new javax.swing.JTextField();
        txtcod = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        CBTipo = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        CBEstado = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTusrs = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        btnBuscarPersonal = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("MATENER USUARIO");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("CODIGO:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("ID_PER:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("NOMBRES:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("USUARIO:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("CONTRASEÑA:");

        pswPassword.setEditable(false);
        pswPassword.setBackground(new java.awt.Color(204, 204, 204));

        txtusuarios.setEditable(false);
        txtusuarios.setBackground(new java.awt.Color(204, 204, 204));

        txtnombres.setEditable(false);
        txtnombres.setBackground(new java.awt.Color(204, 255, 255));

        txtidPer.setEditable(false);
        txtidPer.setBackground(new java.awt.Color(204, 255, 204));

        txtcod.setEditable(false);
        txtcod.setBackground(new java.awt.Color(204, 255, 204));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("TIPO:");

        CBTipo.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("ESTADO:");

        CBEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        CBEstado.setEnabled(false);

        JTusrs.setBackground(new java.awt.Color(204, 204, 204));
        JTusrs.setModel(new javax.swing.table.DefaultTableModel(
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
        JTusrs.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JTusrs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTusrsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTusrs);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("USUARIOS:");

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/EDIT.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnBuscarPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Male_User_search24.png"))); // NOI18N
        btnBuscarPersonal.setText("Buscar Personal");
        btnBuscarPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPersonalActionPerformed(evt);
            }
        });

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Files-New-File-icon.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setEnabled(false);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarPersonal)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pswPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtusuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnombres, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtidPer, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(132, 132, 132))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnsalir)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtidPer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtusuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pswPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CBTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(CBEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnModificar)
                        .addComponent(btnBuscarPersonal)
                        .addComponent(btnNuevo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsalir)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

        NuevoCodigoUSR();


    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

        ShakingComponent ss = new ShakingComponent(txtcod);
        ss.startShake();
        
        SYSAUDIOCON sysau = new SYSAUDIOCON();
        sysau.E_CERRAR_SESION();
        
        
        
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnBuscarPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPersonalActionPerformed

        evn.write(JFRPrincipal.JMSesion.getText(), "Abrio el formulario de selección de personal", "JIFMantenerUsuario", "Botón 'Buscar Personal' Presionado");
        try 
        {
            clearFRM();
            JDES_seleccionarPersonal bp = new JDES_seleccionarPersonal(form, true);
            bp.setAlwaysOnTop(true);
            bp.setVisible(true);
            
        } catch (Exception e) 
           {
               lc.write("Ha ocurrido un error al intentar abrir el formulario de selección de personal", "JIFMantenerUsuario linea 313", e.getMessage());
           }

    }//GEN-LAST:event_btnBuscarPersonalActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed

          try 
          {
            evn.write(JFRPrincipal.JMSesion.getText(), "Ha salido del formulario 'Mantener Usuario'", "JIFMantenerUsuario", "Presiono Botón 'Salir'");
              JFRPrincipal.JSMMantenimientoUsuarios.setActionCommand("CERRADO");
            this.dispose();
          } catch (Exception e) 
          {
              lc.write("No se ha podido cerrar el formulario 'Mantener Usuario' debido a un error inesperado", "JIFMantenerUsuario", e.getMessage());
          }
          


    }//GEN-LAST:event_btnsalirActionPerformed

    private void JTusrsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTusrsMouseClicked

        try 
        {
            int row = JTusrs.rowAtPoint(evt.getPoint());
            //JIFOrdenesDeServicio.
            
            txtcod.setText(""+JTusrs.getValueAt(row, 0));
            txtnombres.setText(""+JTusrs.getValueAt(row, 1));
            txtusuarios.setText(""+JTusrs.getValueAt(row, 2));
            CBTipo.setSelectedItem(""+JTusrs.getValueAt(row, 3));
            CBEstado.setSelectedItem(""+JTusrs.getValueAt(row, 4));
            String nom = ""+JTusrs.getValueAt(row, 1);
            
            evn.write(JFRPrincipal.JMSesion.getText(), "Se ha seleccionado un usuario", "JIFMantenerUsuario", "El usuario "+nom+" ha sido seleccionado");
            
        } catch (Exception e) 
            {
                lc.write("Ha ocurrido algun error al intentar seleccionar un usuario", "JIFMantenerUsuario + Metodo MouseClicked Linea 304", e.getMessage());
            }

    }//GEN-LAST:event_JTusrsMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBEstado;
    private javax.swing.JComboBox<String> CBTipo;
    private javax.swing.JTable JTusrs;
    private javax.swing.JButton btnBuscarPersonal;
    public static javax.swing.JButton btnModificar;
    public static javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPasswordField pswPassword;
    private javax.swing.JTextField txtcod;
    public static javax.swing.JTextField txtidPer;
    public static javax.swing.JTextField txtnombres;
    private javax.swing.JTextField txtusuarios;
    // End of variables declaration//GEN-END:variables
}
