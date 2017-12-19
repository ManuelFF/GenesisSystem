/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker.View;

import ModuleWorker.IC.NANOCON_Permisos;
import ModuleWorker.SYSAUDIOCON;
import ModuleWorker.SYSFRMCON;
import NCLPM.EVENTS;
import NCLPM.LOG;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author USUARIO
 */
public class JIFPermisos extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFPermisos
     */
    NANOCON_Permisos prm = new NANOCON_Permisos();
    LOG lc = new LOG();
    EVENTS evn = new EVENTS();
    SYSFRMCON sysfrm = new SYSFRMCON();
    
    DefaultTableModel modelo = new DefaultTableModel()
    {
        @Override
        public boolean isCellEditable(int row , int col)
        {
             return false;
        }
    };
    
    private TableRowSorter trsFiltro;

    
    public JIFPermisos() 
    {
        initComponents();
        this.setTitle(sysfrm.T_Permisos());
        sysfrm.B_permisos(this.getContentPane());
        prm.CargarUsuariosPermisos(modelo, JTpermisos);
        
        JTpermisos.setModel(modelo);
        JTpermisos.addMouseListener(new MouseAdapter(){});
        JTpermisos.getTableHeader().setReorderingAllowed(false);
        trsFiltro = new TableRowSorter(JTpermisos.getModel());
        JTpermisos.setRowSorter(trsFiltro);
        
    }

    public void filtro() 
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
    
    protected void habilitaCBX(boolean t)
    {
        //READ
        CBXREAD.setEnabled(t);
        //READONLY
        CBXREADONLY.setEnabled(t);
        //WRITE
        CBXWRITE.setEnabled(t);
        //GEN
        CBXGEN.setEnabled(t);
        //OPEN
        CBXOPEN.setEnabled(t);
        //EXEC
        CBXEXEC.setEnabled(t);
        //VIEW
        CBXVIEW.setEnabled(t);
        //PRINT
        CBXPRINT.setEnabled(t);
        //CREATE
        CBXCREATE.setEnabled(t);
        //INSERT
        CBXINSERT.setEnabled(t);
        //MODIFY
        CBXMODIFY.setEnabled(t);
        //UPDATE
        CBXUPDATE.setEnabled(t);
        //DELETE    
        CBXDELETE.setEnabled(t);
        //FIND
        CBXFIND.setEnabled(t);
        //SYSTEM
        CBXSystem.setEnabled(t);
    }
    
    
    protected void permisos(String usr)
    {
        //READ
        if(prm.Comprueba_Permiso_Usuario(usr,"READ").equals("SI"))
            {
                CBXREAD.setSelected(true);
            }else{if(prm.Comprueba_Permiso_Usuario(usr,"READ").equals("NO")){CBXREAD.setSelected(false);}}
        //READONLY
        if(prm.Comprueba_Permiso_Usuario(usr,"READONLY").equals("SI"))
            {
                CBXREADONLY.setSelected(true);
            }else{if(prm.Comprueba_Permiso_Usuario(usr,"READONLY").equals("NO")){CBXREADONLY.setSelected(false);}}
        //WRITE
        if(prm.Comprueba_Permiso_Usuario(usr,"WRITE").equals("SI"))
            {
                CBXWRITE.setSelected(true);
            }else{if(prm.Comprueba_Permiso_Usuario(usr,"WRITE").equals("NO")){CBXWRITE.setSelected(false);}}
        //GEN
        if(prm.Comprueba_Permiso_Usuario(usr,"GEN").equals("SI"))
            {
                CBXGEN.setSelected(true);
            }else{if(prm.Comprueba_Permiso_Usuario(usr,"GEN").equals("NO")){CBXGEN.setSelected(false);}}
        //OPEN
        if(prm.Comprueba_Permiso_Usuario(usr,"OPEN").equals("SI"))
            {
                CBXOPEN.setSelected(true);
            }else{if(prm.Comprueba_Permiso_Usuario(usr,"OPEN").equals("NO")){CBXOPEN.setSelected(false);}}
        //EXEC
        if(prm.Comprueba_Permiso_Usuario(usr,"EXEC").equals("SI"))
            {
                CBXEXEC.setSelected(true);
            }else{if(prm.Comprueba_Permiso_Usuario(usr,"EXEC").equals("NO")){CBXEXEC.setSelected(false);}}
        //VIEW
        if(prm.Comprueba_Permiso_Usuario(usr,"VIEW").equals("SI"))
            {
                CBXVIEW.setSelected(true);
            }else{if(prm.Comprueba_Permiso_Usuario(usr,"VIEW").equals("NO")){CBXVIEW.setSelected(false);}}
        //PRINT
        if(prm.Comprueba_Permiso_Usuario(usr,"PRINT").equals("SI"))
            {
                CBXPRINT.setSelected(true);
            }else{if(prm.Comprueba_Permiso_Usuario(usr,"PRINT").equals("NO")){CBXPRINT.setSelected(false);}}
        
        //CREATE
        if(prm.Comprueba_Permiso_Usuario(usr,"CREATE").equals("SI"))
            {
                CBXCREATE.setSelected(true);
            }else{if(prm.Comprueba_Permiso_Usuario(usr,"CREATE").equals("NO")){CBXCREATE.setSelected(false);}}
        //INSERT
        if(prm.Comprueba_Permiso_Usuario(usr,"INSERT").equals("SI"))
            {
                CBXINSERT.setSelected(true);
            }else{if(prm.Comprueba_Permiso_Usuario(usr,"INSERT").equals("NO")){CBXINSERT.setSelected(false);}}
        //MODIFY
        if(prm.Comprueba_Permiso_Usuario(usr,"MODIFY").equals("SI"))
            {
                CBXMODIFY.setSelected(true);
            }else{if(prm.Comprueba_Permiso_Usuario(usr,"MODIFY").equals("NO")){CBXMODIFY.setSelected(false);}}
        //UPDATE
        if(prm.Comprueba_Permiso_Usuario(usr,"UPDATE").equals("SI"))
            {
                CBXUPDATE.setSelected(true);
            }else{if(prm.Comprueba_Permiso_Usuario(usr,"UPDATE").equals("NO")){CBXUPDATE.setSelected(false);}}
        //DELETE
        if(prm.Comprueba_Permiso_Usuario(usr,"DELETE").equals("SI"))
            {
                CBXDELETE.setSelected(true);
            }else{if(prm.Comprueba_Permiso_Usuario(usr,"DELETE").equals("NO")){CBXDELETE.setSelected(false);}}
        //FIND
        if(prm.Comprueba_Permiso_Usuario(usr,"FIND").equals("SI"))
            {
                CBXFIND.setSelected(true);
            }else{if(prm.Comprueba_Permiso_Usuario(usr,"FIND").equals("NO")){CBXFIND.setSelected(false);}}
        //SYSTEM
        if(prm.Comprueba_Permiso_Usuario(usr,"SYSTEM").equals("SI"))
            {
                CBXSystem.setSelected(true);
            }else{if(prm.Comprueba_Permiso_Usuario(usr,"SYSTEM").equals("NO")){CBXSystem.setSelected(false);}}
    }
    
    protected void update_permisos(String usr)
    {
        //READ
        if(CBXREAD.isSelected()==true)
            {
                prm.UpdateaPermisos(usr, "PRM-001", "SI");
            }else{if(CBXREAD.isSelected()==false){prm.UpdateaPermisos(usr, "PRM-001", "NO");}}
        //READONLY
        if(CBXREADONLY.isSelected()==true)
            {
                prm.UpdateaPermisos(usr, "PRM-002", "SI");
            }else{if(CBXREADONLY.isSelected()==false){prm.UpdateaPermisos(usr, "PRM-002", "NO");}}
        //WRITE
        if(CBXWRITE.isSelected()==true)
            {
                prm.UpdateaPermisos(usr, "PRM-003", "SI");
            }else{if(CBXWRITE.isSelected()==false){prm.UpdateaPermisos(usr, "PRM-003", "NO");}}
        //GEN
        if(CBXGEN.isSelected()==true)
            {
                prm.UpdateaPermisos(usr, "PRM-004", "SI");
            }else{if(CBXGEN.isSelected()==false){prm.UpdateaPermisos(usr, "PRM-004", "NO");}}
        //OPEN
        if(CBXOPEN.isSelected()==true)
            {
                prm.UpdateaPermisos(usr, "PRM-005", "SI");
            }else{if(CBXOPEN.isSelected()==false){prm.UpdateaPermisos(usr, "PRM-005", "NO");}}
        //EXEC
        if(CBXEXEC.isSelected()==true)
            {
                prm.UpdateaPermisos(usr, "PRM-006", "SI");
            }else{if(CBXEXEC.isSelected()==false){prm.UpdateaPermisos(usr, "PRM-006", "NO");}}
        //VIEW
        if(CBXVIEW.isSelected()==true)
            {
                prm.UpdateaPermisos(usr, "PRM-007", "SI");
            }else{if(CBXVIEW.isSelected()==false){prm.UpdateaPermisos(usr, "PRM-007", "NO");}}
        //PRINT
        if(CBXPRINT.isSelected()==true)
            {
                prm.UpdateaPermisos(usr, "PRM-008", "SI");
            }else{if(CBXPRINT.isSelected()==false){prm.UpdateaPermisos(usr, "PRM-008", "NO");}}
        
        //CREATE
        if(CBXCREATE.isSelected()==true)
            {
                prm.UpdateaPermisos(usr, "PRM-009", "SI");
            }else{if(CBXCREATE.isSelected()==false){prm.UpdateaPermisos(usr, "PRM-009", "NO");}}
        //INSERT
        if(CBXINSERT.isSelected()==true)
            {
                prm.UpdateaPermisos(usr, "PRM-010", "SI");
            }else{if(CBXINSERT.isSelected()==false){prm.UpdateaPermisos(usr, "PRM-010", "NO");}}
        //MODIFY
        if(CBXMODIFY.isSelected()==true)
            {
                prm.UpdateaPermisos(usr, "PRM-011", "SI");
            }else{if(CBXMODIFY.isSelected()==false){prm.UpdateaPermisos(usr, "PRM-011", "NO");}}
        //UPDATE
        if(CBXUPDATE.isSelected()==true)
            {
                prm.UpdateaPermisos(usr, "PRM-012", "SI");
            }else{if(CBXUPDATE.isSelected()==false){prm.UpdateaPermisos(usr, "PRM-012", "NO");}}
        //DELETE
        if(CBXDELETE.isSelected()==true)
            {
                prm.UpdateaPermisos(usr, "PRM-013", "SI");
            }else{if(CBXDELETE.isSelected()==false){prm.UpdateaPermisos(usr, "PRM-013", "NO");}}
        //FIND
        if(CBXFIND.isSelected()==true)
            {
                prm.UpdateaPermisos(usr, "PRM-014", "SI");
            }else{if(CBXFIND.isSelected()==false){prm.UpdateaPermisos(usr, "PRM-014", "NO");}}
        //SYSTEM
        if(CBXSystem.isSelected()==true)
            {
                prm.UpdateaPermisos(usr, "PRM-015", "SI");
            }else{if(CBXSystem.isSelected()==false){prm.UpdateaPermisos(usr, "PRM-015", "NO");}}
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
        txtnombres = new javax.swing.JTextField();
        txtidusr = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbfiltro = new javax.swing.JComboBox<>();
        txtfiltro = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTpermisos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        CBXREADONLY = new javax.swing.JCheckBox();
        CBXREAD = new javax.swing.JCheckBox();
        CBXWRITE = new javax.swing.JCheckBox();
        CBXGEN = new javax.swing.JCheckBox();
        CBXOPEN = new javax.swing.JCheckBox();
        CBXEXEC = new javax.swing.JCheckBox();
        CBXVIEW = new javax.swing.JCheckBox();
        CBXPRINT = new javax.swing.JCheckBox();
        CBXCREATE = new javax.swing.JCheckBox();
        CBXINSERT = new javax.swing.JCheckBox();
        CBXMODIFY = new javax.swing.JCheckBox();
        CBXUPDATE = new javax.swing.JCheckBox();
        CBXDELETE = new javax.swing.JCheckBox();
        CBXFIND = new javax.swing.JCheckBox();
        btnSalir = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        CBXSystem = new javax.swing.JCheckBox();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("PERMISOS");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("ID_USR:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Nombres:");

        txtnombres.setEditable(false);
        txtnombres.setBackground(new java.awt.Color(204, 255, 255));

        txtidusr.setEditable(false);
        txtidusr.setBackground(new java.awt.Color(204, 255, 204));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Buscar");

        cbfiltro.setBackground(new java.awt.Color(204, 204, 204));
        cbfiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombres", "ID" }));

        txtfiltro.setBackground(new java.awt.Color(204, 204, 204));
        txtfiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfiltroKeyTyped(evt);
            }
        });

        JTpermisos.setBackground(new java.awt.Color(204, 204, 204));
        JTpermisos.setModel(new javax.swing.table.DefaultTableModel(
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
        JTpermisos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JTpermisos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTpermisosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTpermisos);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Resultados de la busqueda:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("PERMISOS:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Permisos del Sistema.");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Permisos de Formulario.");

        CBXREADONLY.setText("READONLY");
        CBXREADONLY.setEnabled(false);

        CBXREAD.setText("READ");
        CBXREAD.setEnabled(false);

        CBXWRITE.setText("WRITE");
        CBXWRITE.setEnabled(false);

        CBXGEN.setText("GEN");
        CBXGEN.setEnabled(false);

        CBXOPEN.setText("OPEN");
        CBXOPEN.setEnabled(false);

        CBXEXEC.setText("EXEC");
        CBXEXEC.setEnabled(false);

        CBXVIEW.setText("VIEW");
        CBXVIEW.setEnabled(false);

        CBXPRINT.setText("PRINT");
        CBXPRINT.setEnabled(false);

        CBXCREATE.setText("CREATE");
        CBXCREATE.setEnabled(false);

        CBXINSERT.setText("INSERT");
        CBXINSERT.setEnabled(false);

        CBXMODIFY.setText("MODIFY");
        CBXMODIFY.setEnabled(false);

        CBXUPDATE.setText("UPDATE");
        CBXUPDATE.setEnabled(false);

        CBXDELETE.setText("DELETE");
        CBXDELETE.setEnabled(false);

        CBXFIND.setText("FIND");
        CBXFIND.setEnabled(false);

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/logout-icon24.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Accept-icon24.png"))); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.setEnabled(false);
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        CBXSystem.setText("SYSTEM");
        CBXSystem.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(304, 304, 304)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(CBXREADONLY)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CBXREAD)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CBXWRITE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CBXGEN)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CBXOPEN)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CBXEXEC)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CBXVIEW)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CBXPRINT)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CBXSystem)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(CBXCREATE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CBXINSERT)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CBXMODIFY)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CBXUPDATE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CBXDELETE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CBXFIND))
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAceptar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSalir))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel5)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtnombres, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtidusr, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 154, Short.MAX_VALUE)
                        .addComponent(txtfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(txtidusr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtnombres, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CBXREADONLY)
                            .addComponent(CBXREAD)
                            .addComponent(CBXWRITE)
                            .addComponent(CBXGEN)
                            .addComponent(CBXOPEN)
                            .addComponent(CBXEXEC)
                            .addComponent(CBXVIEW)
                            .addComponent(CBXPRINT)
                            .addComponent(CBXSystem))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CBXCREATE)
                            .addComponent(CBXINSERT)
                            .addComponent(CBXMODIFY)
                            .addComponent(CBXUPDATE)
                            .addComponent(CBXDELETE)
                            .addComponent(CBXFIND))
                        .addContainerGap(14, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSalir)
                            .addComponent(btnAceptar))
                        .addGap(13, 13, 13))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtfiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltroKeyTyped

        txtfiltro.addKeyListener(new KeyAdapter()
            {
                public void keyReleased(final KeyEvent e)
                {
                    String cadena = (txtfiltro.getText().toUpperCase());
                    txtfiltro.setText(cadena);
                    repaint();
                    filtro();
                }
            });
    }//GEN-LAST:event_txtfiltroKeyTyped

    private void JTpermisosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTpermisosMouseClicked
        
        try {
            
            int row = JTpermisos.rowAtPoint(evt.getPoint());
            
            txtidusr.setText(""+JTpermisos.getValueAt(row, 0));
            txtnombres.setText(""+JTpermisos.getValueAt(row, 1));
            
            String usr = ""+JTpermisos.getValueAt(row, 0);
            String select = ""+JTpermisos.getValueAt(row, 1);

            btnAceptar.setEnabled(true);
            habilitaCBX(true);
            //COMPRUEBA PERMISOS
            permisos(usr);
                                    
            evn.write(JFRPrincipal.JMSesion.getText(),"Ha seleccionado al Usuario "+select,"JIFPermisos", "Tabla de Permisos presionado");
            
            
        } catch (Exception e) 
            {
               lc.write("Error al seleccionar usuario", "JIFPermisos", e.getMessage());
            }


    }//GEN-LAST:event_JTpermisosMouseClicked

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed

        try 
        {
            JFrame jf=new JFrame();
            jf.setAlwaysOnTop(true);
            
            evn.write(JFRPrincipal.JMSesion.getText(), "Presiono el botón 'Aceptar' y updateo permisos", "JIFPermisos", "Botón 'Aceptar' Presionado");
            SYSAUDIOCON sysau = new SYSAUDIOCON();
            sysau.E_INFORMATION();
            JOptionPane.showMessageDialog(jf, "Permisos Updateados", "Actualización de Permisos", JOptionPane.INFORMATION_MESSAGE);
            
            update_permisos(txtidusr.getText());
            btnAceptar.setEnabled(false);
            habilitaCBX(false);
                    
        } catch (Exception e) 
            {
                lc.write("Error al intentar updatear un permiso", "JIFPermisos linea 486", e.getMessage());
            }

    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed

    try 
      {           
        evn.write(JFRPrincipal.JMSesion.getText(), "Ha salido del formulario 'Permisos'", "JIFPermisos", "Presiono Botón 'Salir'");
        JFRPrincipal.JSMPermisosUsuarios.setActionCommand("CERRADO");
        this.dispose();
      } catch (Exception e) 
      {
          lc.write("No se ha podido cerrar el formulario 'Permisos' debido a un error inesperado", "JIFPermisos", e.getMessage());
      }

    }//GEN-LAST:event_btnSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CBXCREATE;
    private javax.swing.JCheckBox CBXDELETE;
    private javax.swing.JCheckBox CBXEXEC;
    private javax.swing.JCheckBox CBXFIND;
    private javax.swing.JCheckBox CBXGEN;
    private javax.swing.JCheckBox CBXINSERT;
    private javax.swing.JCheckBox CBXMODIFY;
    private javax.swing.JCheckBox CBXOPEN;
    private javax.swing.JCheckBox CBXPRINT;
    private javax.swing.JCheckBox CBXREAD;
    private javax.swing.JCheckBox CBXREADONLY;
    private javax.swing.JCheckBox CBXSystem;
    private javax.swing.JCheckBox CBXUPDATE;
    private javax.swing.JCheckBox CBXVIEW;
    private javax.swing.JCheckBox CBXWRITE;
    private javax.swing.JTable JTpermisos;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cbfiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtfiltro;
    private javax.swing.JTextField txtidusr;
    private javax.swing.JTextField txtnombres;
    // End of variables declaration//GEN-END:variables
}
