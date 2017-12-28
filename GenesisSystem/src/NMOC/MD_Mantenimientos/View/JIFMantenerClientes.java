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
import NMOC.MD_Mantenimientos.Core.NOB_cliente;
import NMOC.MD_Mantenimientos.IC.MICROCON_MantenerClientes;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
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
    RESULTS rslt = new RESULTS();
    SYSFRMCON sysfrm = new SYSFRMCON();
    JFrame form;
    Color ColorInicial;
    boolean condicion_datos = false;

    //MODELOS DE JTABLE
    DefaultTableModel modelo_NATU = new DefaultTableModel()
    {
        @Override
        public boolean isCellEditable(int row , int col)
        {
             return false;
        }
    };
    
    DefaultTableModel modelo_JURI = new DefaultTableModel()
    {
        @Override
        public boolean isCellEditable(int row , int col)
        {
             return false;
        }
    };
    //FIN MODELOS JTABLE
    
    
    private TableRowSorter trsFiltro;
    private TableRowSorter trsFiltro_J;
    protected String IDCLI;
    
    public JIFMantenerClientes() 
    {
        initComponents();
        this.setTitle(sysfrm.T_MantenerClientes());
        sysfrm.B_MantenerClientes(this.getContentPane());
        sysfrm.B_MantenerClientes(JPNatural);
        sysfrm.B_MantenerClientes(JPJuridico);

        lblusuario.setText(JFRPrincipal.JMSesion.getText());
        lblusuario1.setText(JFRPrincipal.JMSesion.getText());
        fecha_actual();

        //ESTABLECIENDO EL MODELO DEL JTABLE
        JTNatural.setModel(modelo_NATU);
        JTJuridico.setModel(modelo_JURI);
        
        //ESTABLECIENDO PROHIBICION DE REOGANIZAR LAS TABLAS
        JTNatural.getTableHeader().setReorderingAllowed(false);
        JTJuridico.getTableHeader().setReorderingAllowed(false);
        
        //CREANDO EL ROW FILTER SORTER
        trsFiltro = new TableRowSorter(JTNatural.getModel());
        trsFiltro_J = new TableRowSorter(JTJuridico.getModel());
        
        //ESTABLECIENDO EL ROW FILTER SORTER
        JTNatural.setRowSorter(trsFiltro);
        JTJuridico.setRowSorter(trsFiltro_J);
        
        //CARGANDO CLIENTES
        CliNCon.CargarCliNatu(modelo_NATU, JTNatural);
        CliNCon.CargarCliJuri(modelo_JURI, JTJuridico);
        
        //ESTABLECIENDO EL LINEWRAP EN TRUE
        JTAdireccion.setLineWrap(true);
        JTAdireccion_J.setLineWrap(true);
        
        //COLOR INICIAL
        ColorInicial = txtnombres.getBackground();
        
        //TAMAÑO DE LAS CABCERAS
        tamaño_cabecera_natu();
        tamaño_cabecera_juri();
    }
    
    private void tamaño_cabecera_natu()
    {
        TableColumnModel columnModel = JTNatural.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(80);
        columnModel.getColumn(1).setPreferredWidth(240);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(50);
        columnModel.getColumn(5).setPreferredWidth(100);
        columnModel.getColumn(6).setPreferredWidth(90);
    }
    
    private void tamaño_cabecera_juri()
    {
        TableColumnModel columnModel = JTJuridico.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(80);
        columnModel.getColumn(1).setPreferredWidth(240);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(50);
        columnModel.getColumn(5).setPreferredWidth(100);
        columnModel.getColumn(6).setPreferredWidth(90);
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
    
    protected void filtro_J() 
    {
          int columnaABuscar = 0;
          
          if(cbfiltro_J.getSelectedItem().toString().equals("Razón Social"))
          {
              columnaABuscar = 1;
          }
          if(cbfiltro_J.getSelectedItem().toString().equals("RUC"))
          {
              columnaABuscar = 2;
          }
          if (cbfiltro_J.getSelectedItem().toString().equals("ID")) 
          {
              columnaABuscar = 0;
          }
          
          trsFiltro_J.setRowFilter(RowFilter.regexFilter(txtfiltro_J.getText(), columnaABuscar));
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
        lbldate1.setText(compl);
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
    private void clearFRM_natu()
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
    
    private void clearFRM_juri()
    {
        IDCLI = "";
        txtRazonSocial.setText("");
        txtRUC.setText("");
        txtNombreEncargado.setText("");
        txtDNI_encarg.setText("");
        txttelefono_J.setText("");
        txtcelular_J.setText("");
        txtcorreo_J.setText("");
        JTAdireccion_J.setText("");
    }
    
    //Habilita los campos de texto
    private void editFRM_natu(boolean cond)
    {
        txtnombres.setEditable(cond);
        txtApellidoP.setEditable(cond);
        txtApellidoM.setEditable(cond);
        txtDNI.setEditable(cond);
        txttelefono.setEditable(cond);
        txtcelular.setEditable(cond);
        txtcorreo.setEditable(cond);
        JTAdireccion.setEditable(cond);
    }
    
    private void editFRM_juri(boolean cond)
    {
        txtRazonSocial.setEditable(cond);
        txtRUC.setEditable(cond);
        txtNombreEncargado.setEditable(cond);
        txtDNI_encarg.setEditable(cond);
        txttelefono_J.setEditable(cond);
        txtcelular_J.setEditable(cond);
        txtcorreo_J.setEditable(cond);
        JTAdireccion_J.setEditable(cond);
    }
        
    private void clearCacheDB_natu()
    {
        try 
        {
            MWCON mw = new MWCON();
            mw.clear_table(modelo_NATU, JTNatural);
            CliNCon.llenarIDS_CLI();
            CliNCon.CargarCliNatu(modelo_NATU, JTNatural);
            tamaño_cabecera_natu();
        } catch (Exception e) 
            {
                lc.write("Error al intentar borrar la cache de la DB", "JIFMantenerClientes metodo clearcacheDB linea 172", e);
            }
    }
    
    private void clearCacheDB_juri()
    {
        try 
        {
            MWCON mw = new MWCON();
            mw.clear_table(modelo_JURI, JTJuridico);
            CliNCon.llenarIDS_CLI();
            CliNCon.CargarCliJuri(modelo_JURI, JTJuridico);
            tamaño_cabecera_juri();
        } catch (Exception e) 
            {
                lc.write("Error al intentar borrar la cache de la DB", "JIFMantenerClientes metodo clearcacheDB linea 172", e);
            }
    }
    
    private void ena_disaButtons_natu(boolean nuevo, boolean modificar, boolean cancelar, boolean salir)
    {
        btnnuevo_N.setEnabled(nuevo);
        btnmodificar_N.setEnabled(modificar);
        btncancelar_N.setEnabled(cancelar);
        btnsalir_N.setEnabled(salir);
    }
    
     private void ena_disaButtons_juri(boolean nuevo, boolean modificar, boolean cancelar, boolean salir)
    {
        btnnuevo_J.setEnabled(nuevo);
        btnmodificar_J.setEnabled(modificar);
        btncancelar_J.setEnabled(cancelar);
        btnsalir_J.setEnabled(salir);
    }
    
    private void reiniciarColors_natu()
    {
        txtnombres.setBackground(ColorInicial);
        txtApellidoP.setBackground(ColorInicial);
        txtApellidoM.setBackground(ColorInicial);
        txtDNI.setBackground(ColorInicial);
        txttelefono.setBackground(ColorInicial);
        txtcelular.setBackground(ColorInicial);
        JTAdireccion.setBackground(ColorInicial);
        txtcorreo.setBackground(ColorInicial);  
    }
    
    private void reiniciarColors_juri()
    {
        txtRazonSocial.setBackground(ColorInicial);
        txtRUC.setBackground(ColorInicial);
        txtNombreEncargado.setBackground(ColorInicial);
        txtDNI_encarg.setBackground(ColorInicial);
        txttelefono_J.setBackground(ColorInicial);
        txtcelular_J.setBackground(ColorInicial);
        JTAdireccion_J.setBackground(ColorInicial);
        txtcorreo_J.setBackground(ColorInicial);  
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
        jScrollPane2 = new javax.swing.JScrollPane();
        JTJuridico = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblusuario1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbldate1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtRazonSocial = new javax.swing.JTextField();
        txtRUC = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtNombreEncargado = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtDNI_encarg = new javax.swing.JTextField();
        txtcelular_J = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtcorreo_J = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txttelefono_J = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        cbfiltro_J = new javax.swing.JComboBox<>();
        txtfiltro_J = new javax.swing.JTextField();
        btnnuevo_J = new javax.swing.JButton();
        btnmodificar_J = new javax.swing.JButton();
        btncancelar_J = new javax.swing.JButton();
        btnsalir_J = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        JTAdireccion_J = new javax.swing.JTextArea();

        JTNatural.setBackground(new java.awt.Color(204, 204, 204));
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
        jLabel7.setText("Nombres:");

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

        txtfiltro.setBackground(new java.awt.Color(204, 204, 255));
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
        btnmodificar_N.setEnabled(false);
        btnmodificar_N.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificar_NActionPerformed(evt);
            }
        });

        btncancelar_N.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Windows-Close-Program-icon.png"))); // NOI18N
        btncancelar_N.setText("Cancelar");
        btncancelar_N.setEnabled(false);
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JTABPrincipal.addTab("Naturales", JPNatural);

        JTJuridico.setBackground(new java.awt.Color(204, 204, 204));
        JTJuridico.setModel(new javax.swing.table.DefaultTableModel(
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
        JTJuridico.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JTJuridico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTJuridicoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JTJuridico);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Resultado busqueda:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("usuario:");

        lblusuario1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblusuario1.setText("jLabel3");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("CLIENTES JURIDICOS");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Fecha Sistema:");

        lbldate1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbldate1.setText("18/12/2017");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Razón Social:");

        txtRazonSocial.setEditable(false);
        txtRazonSocial.setBackground(new java.awt.Color(204, 204, 204));

        txtRUC.setEditable(false);
        txtRUC.setBackground(new java.awt.Color(204, 204, 204));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("RUC:");

        txtNombreEncargado.setEditable(false);
        txtNombreEncargado.setBackground(new java.awt.Color(204, 204, 204));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Nombre Encargado:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("DNI:");

        txtDNI_encarg.setEditable(false);
        txtDNI_encarg.setBackground(new java.awt.Color(204, 204, 204));

        txtcelular_J.setEditable(false);
        txtcelular_J.setBackground(new java.awt.Color(204, 204, 204));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Celular:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Correo:");

        txtcorreo_J.setEditable(false);
        txtcorreo_J.setBackground(new java.awt.Color(204, 204, 204));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Telefono:");

        txttelefono_J.setEditable(false);
        txttelefono_J.setBackground(new java.awt.Color(204, 204, 204));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Dirección:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel26.setText("Buscar:");

        cbfiltro_J.setBackground(new java.awt.Color(204, 204, 204));
        cbfiltro_J.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Razón Social", "RUC", "ID" }));

        txtfiltro_J.setBackground(new java.awt.Color(204, 204, 255));
        txtfiltro_J.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfiltro_JKeyTyped(evt);
            }
        });

        btnnuevo_J.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Files-New-File-icon.png"))); // NOI18N
        btnnuevo_J.setText("Nuevo");
        btnnuevo_J.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevo_JActionPerformed(evt);
            }
        });

        btnmodificar_J.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/EDIT.png"))); // NOI18N
        btnmodificar_J.setText("Modificar");
        btnmodificar_J.setEnabled(false);
        btnmodificar_J.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificar_JActionPerformed(evt);
            }
        });

        btncancelar_J.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Windows-Close-Program-icon.png"))); // NOI18N
        btncancelar_J.setText("Cancelar");
        btncancelar_J.setEnabled(false);
        btncancelar_J.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar_JActionPerformed(evt);
            }
        });

        btnsalir_J.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/logout-icon24.png"))); // NOI18N
        btnsalir_J.setText("Salir");
        btnsalir_J.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalir_JActionPerformed(evt);
            }
        });

        JTAdireccion_J.setEditable(false);
        JTAdireccion_J.setBackground(new java.awt.Color(204, 204, 204));
        JTAdireccion_J.setColumns(20);
        JTAdireccion_J.setRows(5);
        jScrollPane6.setViewportView(JTAdireccion_J);

        javax.swing.GroupLayout JPJuridicoLayout = new javax.swing.GroupLayout(JPJuridico);
        JPJuridico.setLayout(JPJuridicoLayout);
        JPJuridicoLayout.setHorizontalGroup(
            JPJuridicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPJuridicoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPJuridicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPJuridicoLayout.createSequentialGroup()
                        .addGroup(JPJuridicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(JPJuridicoLayout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JPJuridicoLayout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JPJuridicoLayout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDNI_encarg, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JPJuridicoLayout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcelular_J, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JPJuridicoLayout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcorreo_J, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(JPJuridicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPJuridicoLayout.createSequentialGroup()
                                .addGroup(JPJuridicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JPJuridicoLayout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNombreEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(JPJuridicoLayout.createSequentialGroup()
                                        .addGap(62, 62, 62)
                                        .addComponent(jLabel24)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txttelefono_J, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(JPJuridicoLayout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jLabel25)
                                .addGap(1, 1, 1)
                                .addComponent(jScrollPane6))))
                    .addGroup(JPJuridicoLayout.createSequentialGroup()
                        .addComponent(btnnuevo_J)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnmodificar_J)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btncancelar_J)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnsalir_J))
                    .addComponent(jScrollPane2)
                    .addGroup(JPJuridicoLayout.createSequentialGroup()
                        .addGroup(JPJuridicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(JPJuridicoLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblusuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                .addComponent(jLabel17)))
                        .addGap(10, 10, 10)
                        .addComponent(lbldate1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPJuridicoLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbfiltro_J, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfiltro_J)))
                .addContainerGap())
        );
        JPJuridicoLayout.setVerticalGroup(
            JPJuridicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPJuridicoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPJuridicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblusuario1)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(lbldate1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPJuridicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPJuridicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(txtNombreEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPJuridicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtDNI_encarg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(txttelefono_J, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPJuridicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPJuridicoLayout.createSequentialGroup()
                        .addGroup(JPJuridicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtcelular_J, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPJuridicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtcorreo_J, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPJuridicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnuevo_J)
                    .addComponent(btnmodificar_J)
                    .addComponent(btncancelar_J)
                    .addComponent(btnsalir_J))
                .addGap(7, 7, 7)
                .addGroup(JPJuridicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPJuridicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbfiltro_J, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtfiltro_J, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
       //EJECUTADO ANTES DE TODO
      SYSAUDIOCON sysau = new SYSAUDIOCON();

      //EJECUTADO ANTES DE TODA CONDICIONAL
      JFrame jf=new JFrame();
      jf.setAlwaysOnTop(true);
      clearCacheDB_natu();

      JTABPrincipal.setEnabled(false);

      evn.write(JFRPrincipal.JMSesion.getText(), "Hizo click en el botón 'Nuevo' cliente natural ", "JIFMantenerCliente linea 344", "Botón 'Nuevo_Natural' presionado");

      if(btnnuevo_N.getText().equals("Nuevo"))
      {
          clearFRM_natu();
          NuevoCodigoCLI();
          editFRM_natu(true);
          ena_disaButtons_natu(true, false, true, false);
          btnnuevo_N.setText("Insertar");
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
           
           //OBLIGATORIO
           //NOMBRES
           if(txtnombres.getText().trim().equals(""))
           {
              sysau.E_ERROR();
              txtnombres.setBackground(Color.RED);
              ShakingComponent sh_nombre = new ShakingComponent(txtnombres);
              sh_nombre.startShake();
              JOptionPane.showMessageDialog(jf, "Es obligatorio el uso de 'Nombre' para el cliente", "Falta Nombre del Cliente", JOptionPane.ERROR_MESSAGE);
              sysau.S_STOP();
           }else
           //APELLIDO PATERNO
           if(txtApellidoP.getText().trim().equals(""))
           {
              sysau.E_ERROR();
              txtApellidoP.setBackground(Color.RED);
              ShakingComponent sh_ApePat = new ShakingComponent(txtApellidoP);
              sh_ApePat.startShake();
              JOptionPane.showMessageDialog(jf, "Es obligatorio el uso de 'Apellido Paterno' para el cliente", "Falta Apellido Paterno del cliente", JOptionPane.ERROR_MESSAGE);
              sysau.S_STOP();
           }else
           //DIRECCION
           if(JTAdireccion.getText().trim().equals(""))
           {
               sysau.E_ERROR();
               JTAdireccion.setBackground(Color.RED);
               ShakingComponent sh_Direccion = new ShakingComponent(JTAdireccion);
               sh_Direccion.startShake();
               JOptionPane.showMessageDialog(jf, "Es obligatorio el uso de 'Dirección' para el cliente", "Falta Dirección del cliente", JOptionPane.ERROR_MESSAGE);
               sysau.S_STOP();
           }else
           //ONE BY ONE OBLIGATORIOS
           //TELEFONO - CELULAR
           if(txttelefono.getText().trim().equals("") && txtcelular.getText().trim().equals(""))
           {
              sysau.E_ERROR();
              txttelefono.setBackground(Color.RED);
              txtcelular.setBackground(Color.RED);
              ShakingComponent sh_Telefono = new ShakingComponent(txttelefono);
              ShakingComponent sh_Celular = new ShakingComponent(txtcelular);
              sh_Telefono.startShake();  
              sh_Celular.startShake();
              JOptionPane.showMessageDialog(jf, "Es obligatorio el uso de algun 'Telefono' o 'Celular' para el cliente", "Falta información de comunicación", JOptionPane.ERROR_MESSAGE);
              sysau.S_STOP();
           }else
           {   

            //WARNINGS
            //APELLIDO MATERNO
            if(txtApellidoM.getText().trim().equals(""))
            {
                txtApellidoM.setBackground(Color.YELLOW);
                txtApellidoM.setText("-");
                ShakingComponent sh_ApeMat = new ShakingComponent(txtApellidoM);
                sh_ApeMat.startShake();
            }
            //DNI
            if(txtDNI.getText().trim().equals(""))
            {
                txtDNI.setBackground(Color.YELLOW);
                txtDNI.setText("-");
                ShakingComponent sh_DNI = new ShakingComponent(txtDNI);
                sh_DNI.startShake();
            }
            //CORREO
            if(txtcorreo.getText().trim().equals(""))
            {
                txtcorreo.setBackground(Color.YELLOW);
                txtcorreo.setText("-");
                ShakingComponent sh_Correo = new ShakingComponent(txtcorreo); 
                sh_Correo.startShake();
            }
            //COMPROBAR SI TELEFONO ESTA VACIO
            if(txttelefono.getText().trim().equals(""))
            {
                txttelefono.setText("-");
            }
            //COMPROBAR SI CELULAR ESTA VACIO
            if(txtcelular.getText().trim().equals(""))
            {
                txtcelular.setText("-");
            }
            
            //FIN DE LOGICA DE VERIFICACION
            
            //INICIO PROGRAMACION DE INGRESO
            
            //OBTENER DATOS
            String ID_CLI = IDCLI;
            String nombre = txtnombres.getText().toUpperCase();
            String ape_pat = txtApellidoP.getText().toUpperCase();
            String ape_mat = txtApellidoM.getText().toUpperCase();
            String dni = txtDNI.getText().toUpperCase();
            String telefono = txttelefono.getText().toUpperCase();
            String celular = txtcelular.getText().toUpperCase();
            String direccion = JTAdireccion.getText().toUpperCase();
            String correo = txtcorreo.getText().toUpperCase();                         
            //FIN  DE OBTENCION DE DATOS 
            
            //INICIO CONTROLADOR QUE INSERTA
            CliNCon.InsertarCliente_Natural(ID_CLI, nombre, ape_pat, ape_mat, dni, telefono, celular, direccion, correo);
            //FINAL CONTROLADOR QUE INSERTA
            
            evn.write(JFRPrincipal.JMSesion.getText(), "Inserto un cliente natural", "JIFMantenerClientes -> Naturales", "Botón 'Insertar' Presionado");
            rslt.write(JFRPrincipal.JMSesion.getText(), "JIFMantenerClientes -> Natural", "INSERCIÓN", "Se ha insertado el cliente con ID  "+ID_CLI+
                                                        "\n NOMBRE: "+nombre+" \n APELLIDOS: "+ape_pat+" "+ape_mat+" \n DNI: "+dni);
            sysau.E_INFORMATION();
            JOptionPane.showMessageDialog(jf, "Cliente Insertado con exito!", "Cliente Insertado", JOptionPane.INFORMATION_MESSAGE);
            clearFRM_natu();
            editFRM_natu(false);
            ena_disaButtons_natu(true, false, false, true);
            btnnuevo_N.setText("Nuevo");
            txtfiltro.setEnabled(true);cbfiltro.setEnabled(true);
            condicion_datos = false;
            reiniciarColors_natu();
            clearCacheDB_natu();
            JTABPrincipal.setEnabled(true);
           }
        }
    } catch (Exception e) 
        {
           lc.write("Error al intentar ingresar un nuevo Cliente Natural", "JIFMantenerCliente linea 344", e);
        }

    }//GEN-LAST:event_btnnuevo_NActionPerformed

    private void btnmodificar_NActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificar_NActionPerformed
   
    try 
    {  
       //EJECUTADO ANTES DE TODO
      SYSAUDIOCON sysau = new SYSAUDIOCON();

      //EJECUTADO ANTES DE TODA CONDICIONAL
      JFrame jf=new JFrame();
      jf.setAlwaysOnTop(true);
      clearCacheDB_natu();

      JTABPrincipal.setEnabled(false);

      evn.write(JFRPrincipal.JMSesion.getText(), "hizo click en el botón 'Modificar' cliente natural ", "JIFMantenerCliente linea 730", "Botón 'Modificar_Natural' presionado");

      if(btnmodificar_N.getText().equals("Modificar"))
      {
          editFRM_natu(true);
          ena_disaButtons_natu(false, true, true, false);
          btnmodificar_N.setText("Actualizar");
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
           
           //OBLIGATORIO
           //NOMBRES
           if(txtnombres.getText().trim().equals(""))
           {
              sysau.E_ERROR();
              txtnombres.setBackground(Color.RED);
              ShakingComponent sh_nombre = new ShakingComponent(txtnombres);
              sh_nombre.startShake();
              JOptionPane.showMessageDialog(jf, "Es obligatorio el uso de 'Nombre' para el cliente", "Falta Nombre del Cliente", JOptionPane.ERROR_MESSAGE);
              sysau.S_STOP();
           }else
           //APELLIDO PATERNO
           if(txtApellidoP.getText().trim().equals(""))
           {
              sysau.E_ERROR();
              txtApellidoP.setBackground(Color.RED);
              ShakingComponent sh_ApePat = new ShakingComponent(txtApellidoP);
              sh_ApePat.startShake();
              JOptionPane.showMessageDialog(jf, "Es obligatorio el uso de 'Apellido Paterno' para el cliente", "Falta Apellido Paterno del cliente", JOptionPane.ERROR_MESSAGE);
              sysau.S_STOP();
           }else
           //DIRECCION
           if(JTAdireccion.getText().trim().equals(""))
           {
               sysau.E_ERROR();
               JTAdireccion.setBackground(Color.RED);
               ShakingComponent sh_Direccion = new ShakingComponent(JTAdireccion);
               sh_Direccion.startShake();
               JOptionPane.showMessageDialog(jf, "Es obligatorio el uso de 'Dirección' para el cliente", "Falta Dirección del cliente", JOptionPane.ERROR_MESSAGE);
               sysau.S_STOP();
           }else
           //ONE BY ONE OBLIGATORIOS
           //TELEFONO - CELULAR
           if(txttelefono.getText().trim().equals("") && txtcelular.getText().trim().equals(""))
           {
              sysau.E_ERROR();
              txttelefono.setBackground(Color.RED);
              txtcelular.setBackground(Color.RED);
              ShakingComponent sh_Telefono = new ShakingComponent(txttelefono);
              ShakingComponent sh_Celular = new ShakingComponent(txtcelular);
              sh_Telefono.startShake();  
              sh_Celular.startShake();
              JOptionPane.showMessageDialog(jf, "Es obligatorio el uso de algun 'Telefono' o 'Celular' para el cliente", "Falta información de comunicación", JOptionPane.ERROR_MESSAGE);
              sysau.S_STOP();
           }else
           {   

            //WARNINGS
            //APELLIDO MATERNO
            if(txtApellidoM.getText().trim().equals(""))
            {
                txtApellidoM.setBackground(Color.YELLOW);
                txtApellidoM.setText("-");
                ShakingComponent sh_ApeMat = new ShakingComponent(txtApellidoM);
                sh_ApeMat.startShake();
            }
            //DNI
            if(txtDNI.getText().trim().equals(""))
            {
                txtDNI.setBackground(Color.YELLOW);
                txtDNI.setText("-");
                ShakingComponent sh_DNI = new ShakingComponent(txtDNI);
                sh_DNI.startShake();
            }
            //CORREO
            if(txtcorreo.getText().trim().equals(""))
            {
                txtcorreo.setBackground(Color.YELLOW);
                txtcorreo.setText("-");
                ShakingComponent sh_Correo = new ShakingComponent(txtcorreo); 
                sh_Correo.startShake();
            }
            //COMPROBAR SI TELEFONO ESTA VACIO
            if(txttelefono.getText().trim().equals(""))
            {
                txttelefono.setText("-");
            }
            //COMPROBAR SI CELULAR ESTA VACIO
            if(txtcelular.getText().trim().equals(""))
            {
                txtcelular.setText("-");
            }
            
            //FIN DE LOGICA DE VERIFICACION
            
            //INICIO PROGRAMACION DE INGRESO
            
            //OBTENER DATOS
            String ID_CLI = IDCLI;
            String nombre = txtnombres.getText().toUpperCase();
            String ape_pat = txtApellidoP.getText().toUpperCase();
            String ape_mat = txtApellidoM.getText().toUpperCase();
            String dni = txtDNI.getText().toUpperCase();
            String telefono = txttelefono.getText().toUpperCase();
            String celular = txtcelular.getText().toUpperCase();
            String direccion = JTAdireccion.getText().toUpperCase();
            String correo = txtcorreo.getText().toUpperCase();                         
            //FIN  DE OBTENCION DE DATOS 
            
            //INICIO CONTROLADOR QUE INSERTA
            CliNCon.ModificarCliente_Natural(ID_CLI, nombre, ape_pat, ape_mat, dni, telefono, celular, direccion, correo);
            //FINAL CONTROLADOR QUE INSERTA
            
            evn.write(JFRPrincipal.JMSesion.getText(), "Modifico un cliente natural", "JIFMantenerClientes -> Naturales", "Botón 'Actualizar' Presionado");
            rslt.write(JFRPrincipal.JMSesion.getText(), "JIFMantenerClientes -> Natural", "MODIFICACIÓN", "Se ha modificado el cliente con ID  "+ID_CLI+
                                                        "\n NOMBRE: "+nombre+" \n APELLIDOS: "+ape_pat+" "+ape_mat+" \n DNI: "+dni);
            sysau.E_INFORMATION();
            JOptionPane.showMessageDialog(jf, "Cliente Modificado con exito!", "Cliente Modificado", JOptionPane.INFORMATION_MESSAGE);
            clearFRM_natu();
            editFRM_natu(false);
            ena_disaButtons_natu(true, false, false, true);
            btnmodificar_N.setText("Modificar");
            txtfiltro.setEnabled(true);cbfiltro.setEnabled(true);
            condicion_datos = false;
            reiniciarColors_natu();
            clearCacheDB_natu();
            JTABPrincipal.setEnabled(true);
           }
        }
    } catch (Exception e) 
        {
           lc.write("Error al intentar modificar un nuevo Cliente Natural", "JIFMantenerCliente linea 344", e);
        }

    }//GEN-LAST:event_btnmodificar_NActionPerformed

    private void btncancelar_NActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar_NActionPerformed

    try 
    {
        evn.write(JFRPrincipal.JMSesion.getText(), "Ha cancelado la inserción o modificación de un cliente natural", "JIFMantenerClientes -> Natural", "Botón 'Cancelar' Presionado");
        clearFRM_natu();
        IDCLI="";
        btnnuevo_N.setText("Nuevo");
        btnmodificar_N.setText("Modificar");
        clearCacheDB_natu();
        JTABPrincipal.setEnabled(true);
        reiniciarColors_natu();
        editFRM_natu(false);
        txtfiltro.setEnabled(true);cbfiltro.setEnabled(true);
        condicion_datos = false;
        ena_disaButtons_natu(true, false, false, true);

    } catch (Exception e) 
       {
           lc.write("Ha ocurrido algun error al intentar cancelar una inserción", "JIFMantenerClientes -> naturales", e);
       }

    }//GEN-LAST:event_btncancelar_NActionPerformed

    private void btnsalir_NActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalir_NActionPerformed
        
    try 
    {           
      evn.write(JFRPrincipal.JMSesion.getText(), "Ha salido del formulario 'Mantener Clientes'", "JIFMantenerClientes", "Presiono Botón 'Salir'");
      JFRPrincipal.JSMMantenerClientes.setActionCommand("CERRADO");
      this.dispose();
    } catch (Exception e) 
    {
        lc.write("No se ha podido cerrar el formulario 'Mantener Clientes' debido a un error inesperado", "JIFMantenerClientes", e);
    }
        
    }//GEN-LAST:event_btnsalir_NActionPerformed

    String ArrayTemp2[];
    
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
 
    String ArrayTemp[];
    String buff;        
    
    private void JTNaturalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTNaturalMouseClicked

    try 
    {
        if(condicion_datos == false)
        {
            btnmodificar_N.setEnabled(true);
            JTABPrincipal.setEnabled(false);
            btncancelar_N.setEnabled(true);
            
            
            int row = JTNatural.rowAtPoint(evt.getPoint());

            IDCLI = ""+JTNatural.getValueAt(row, 0);

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

            evn.write(JFRPrincipal.JMSesion.getText(),"Ha seleccionado al cliente natural "+select,"JIFMantenerClientes -> Natural", "Tabla de Clientes Naturales presionado");
        }
 } catch (Exception e) 
     {
        lc.write("Error al seleccionar cliente natural", "JIFMantenerClientes -> Natural", e);
     }


    }//GEN-LAST:event_JTNaturalMouseClicked

    private void JTJuridicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTJuridicoMouseClicked

        
    try 
    {
        if(condicion_datos == false)
        {
            btnmodificar_J.setEnabled(true);
            btncancelar_J.setEnabled(true);
            JTABPrincipal.setEnabled(false);
            
            int row = JTJuridico.rowAtPoint(evt.getPoint());

            IDCLI = ""+JTJuridico.getValueAt(row, 0);

            txtRazonSocial.setText(""+JTJuridico.getValueAt(row, 1));
            txtRUC.setText(""+JTJuridico.getValueAt(row, 2));
            txtNombreEncargado.setText(""+JTJuridico.getValueAt(row, 3));
            txtDNI_encarg.setText(""+JTJuridico.getValueAt(row, 4));
            txttelefono_J.setText(""+JTJuridico.getValueAt(row, 5));
            txtcelular_J.setText(""+JTJuridico.getValueAt(row, 6));
            JTAdireccion_J.setText(""+JTJuridico.getValueAt(row, 7));
            txtcorreo_J.setText(""+JTJuridico.getValueAt(row, 8));


            String select = ""+JTJuridico.getValueAt(row, 1);

            evn.write(JFRPrincipal.JMSesion.getText(),"Ha seleccionado al cliente juridico "+select,"JIFMantenerClientes -> Juridico", "Tabla de Clientes Juridicos presionado");
        }
 } catch (Exception e) 
     {
        lc.write("Error al seleccionar cliente juridico", "JIFMantenerClientes -> Juridico", e);
     }

    }//GEN-LAST:event_JTJuridicoMouseClicked

    private void txtfiltro_JKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltro_JKeyTyped

    txtfiltro_J.addKeyListener(new KeyAdapter() 
     {
         @Override
         public void keyReleased(final KeyEvent e) 
         {
             String cadena = (txtfiltro_J.getText().toUpperCase());
             txtfiltro_J.setText(cadena);

             repaint();
             filtro_J();
         }
     });


    }//GEN-LAST:event_txtfiltro_JKeyTyped

    private void btnnuevo_JActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevo_JActionPerformed

    try 
    {  
       //EJECUTADO ANTES DE TODO
      SYSAUDIOCON sysau = new SYSAUDIOCON();

      //EJECUTADO ANTES DE TODA CONDICIONAL
      JFrame jf=new JFrame();
      jf.setAlwaysOnTop(true);
      clearCacheDB_juri();

      JTABPrincipal.setEnabled(false);

      evn.write(JFRPrincipal.JMSesion.getText(), "hizo click en el botón 'Nuevo' cliente juridico ", "JIFMantenerCliente linea 344", "Botón 'Nuevo_Juridico' presionado");

      if(btnnuevo_J.getText().equals("Nuevo"))
      {
          clearFRM_juri();
          NuevoCodigoCLI();
          editFRM_juri(true);
          ena_disaButtons_juri(true, false, true, false);
          btnnuevo_J.setText("Insertar");
          txtfiltro_J.setEnabled(false);cbfiltro_J.setEnabled(false);
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
           
           //OBLIGATORIO
           //RAZON SOCIAL
           if(txtRazonSocial.getText().trim().equals(""))
           {
              sysau.E_ERROR();
              txtRazonSocial.setBackground(Color.RED);
              ShakingComponent sh_raz = new ShakingComponent(txtRazonSocial);
              sh_raz.startShake();
              JOptionPane.showMessageDialog(jf, "Es obligatorio el uso de 'Razónn Social' para el cliente", "Falta Razón Social del Cliente", JOptionPane.ERROR_MESSAGE);
              sysau.S_STOP();
           }else
           //DIRECCION
           if(JTAdireccion_J.getText().trim().equals(""))
           {
               sysau.E_ERROR();
               JTAdireccion_J.setBackground(Color.RED);
               ShakingComponent sh_Direccion = new ShakingComponent(JTAdireccion_J);
               sh_Direccion.startShake();
               JOptionPane.showMessageDialog(jf, "Es obligatorio el uso de 'Dirección' para el cliente", "Falta Dirección del cliente", JOptionPane.ERROR_MESSAGE);
               sysau.S_STOP();
           }else
           //ONE BY ONE OBLIGATORIOS
           //TELEFONO - CELULAR
           if(txttelefono_J.getText().trim().equals("") && txtcelular_J.getText().trim().equals(""))
           {
              sysau.E_ERROR();
              txttelefono_J.setBackground(Color.RED);
              txtcelular_J.setBackground(Color.RED);
              ShakingComponent sh_Telefono = new ShakingComponent(txttelefono_J);
              ShakingComponent sh_Celular = new ShakingComponent(txtcelular_J);
              sh_Telefono.startShake();  
              sh_Celular.startShake();
              JOptionPane.showMessageDialog(jf, "Es obligatorio el uso de algun 'Telefono' o 'Celular' para el cliente", "Falta información de comunicación", JOptionPane.ERROR_MESSAGE);
              sysau.S_STOP();
           }else
           {   
            //WARNINGS
            //RUC
            if(txtRUC.getText().trim().equals(""))
            {
                txtRUC.setBackground(Color.YELLOW);
                txtRUC.setText("-");
                ShakingComponent sh_RUC = new ShakingComponent(txtRUC);
                sh_RUC.startShake();
            }
            //NOM ENCARG
            if(txtNombreEncargado.getText().trim().equals(""))
            {
                txtNombreEncargado.setBackground(Color.YELLOW);
                txtNombreEncargado.setText("-");
                ShakingComponent sh_nom = new ShakingComponent(txtNombreEncargado);
                sh_nom.startShake();
            }
            //DNI ENCARG
            if(txtDNI_encarg.getText().trim().equals(""))
            {
                txtDNI_encarg.setBackground(Color.YELLOW);
                txtDNI_encarg.setText("-");
                ShakingComponent sh_DNI = new ShakingComponent(txtDNI_encarg);
                sh_DNI.startShake();
            }
            //CORREO
            if(txtcorreo_J.getText().trim().equals(""))
            {
                txtcorreo_J.setBackground(Color.YELLOW);
                txtcorreo_J.setText("-");
                ShakingComponent sh_Correo = new ShakingComponent(txtcorreo_J); 
                sh_Correo.startShake();
            }
            //COMPROBAR SI TELEFONO ESTA VACIO
            if(txttelefono_J.getText().trim().equals(""))
            {
                txttelefono_J.setText("-");
            }
            //COMPROBAR SI CELULAR ESTA VACIO
            if(txtcelular_J.getText().trim().equals(""))
            {
                txtcelular_J.setText("-");
            }
            
            //FIN DE LOGICA DE VERIFICACION
            
            //INICIO PROGRAMACION DE INGRESO
            
            //OBTENER DATOS
            String ID_CLI = IDCLI;
            String razon_social = txtRazonSocial.getText().toUpperCase();
            String RUC = txtRUC.getText().toUpperCase();
            String nom_encarg = txtNombreEncargado.getText().toUpperCase();;
            String DNI_encarg = txtDNI_encarg.getText().toUpperCase();;
            String telefono = txttelefono_J.getText().toUpperCase();
            String celular = txtcelular_J.getText().toUpperCase();
            String direccion = JTAdireccion_J.getText().toUpperCase();
            String correo = txtcorreo_J.getText().toUpperCase();                         
            //FIN  DE OBTENCION DE DATOS 
            
            //INICIO CONTROLADOR QUE INSERTA
            CliNCon.InsertarCliente_Juridico(ID_CLI, razon_social,nom_encarg,RUC,direccion,telefono,celular,correo,DNI_encarg);
            //FINAL CONTROLADOR QUE INSERTA
            
            evn.write(JFRPrincipal.JMSesion.getText(), "Inserto un cliente juridico", "JIFMantenerClientes -> Juridicos", "Botón 'Insertar' Presionado");
            rslt.write(JFRPrincipal.JMSesion.getText(), "JIFMantenerClientes -> Juridicos", "INSERCIÓN", "Se ha insertado el cliente con ID  "+ID_CLI+
                                                        "\n Razón Social: "+razon_social+" \n RUC: "+RUC);
            sysau.E_INFORMATION();
            JOptionPane.showMessageDialog(jf, "Cliente Insertado con exito!", "Cliente Insertado", JOptionPane.INFORMATION_MESSAGE);
            clearFRM_juri();
            editFRM_juri(false);
            ena_disaButtons_juri(true, false, false, true);
            btnnuevo_J.setText("Nuevo");
            txtfiltro_J.setEnabled(true);cbfiltro_J.setEnabled(true);
            condicion_datos = false;
            reiniciarColors_juri();
            clearCacheDB_juri();
            JTABPrincipal.setEnabled(true);
           }
        }
    } catch (Exception e) 
        {
           lc.write("Error al intentar ingresar un nuevo Cliente Juridico", "JIFMantenerCliente linea 344", e);
        }
        
    }//GEN-LAST:event_btnnuevo_JActionPerformed

    private void btnmodificar_JActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificar_JActionPerformed
   
    try 
    {  
       //EJECUTADO ANTES DE TODO
      SYSAUDIOCON sysau = new SYSAUDIOCON();

      //EJECUTADO ANTES DE TODA CONDICIONAL
      JFrame jf=new JFrame();
      jf.setAlwaysOnTop(true);
      clearCacheDB_natu();

      JTABPrincipal.setEnabled(false);

      evn.write(JFRPrincipal.JMSesion.getText(), "hizo click en el botón 'Modificar' cliente juridico ", "JIFMantenerCliente linea 730", "Botón 'Modificar_Juridico' presionado");

      if(btnmodificar_J.getText().equals("Modificar"))
      {
          editFRM_juri(true);
          ena_disaButtons_juri(false, true, true, false);
          btnmodificar_J.setText("Actualizar");
          txtfiltro_J.setEnabled(false);cbfiltro_J.setEnabled(false);
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
           
           //OBLIGATORIO
           //RAZON SOCIAL
           if(txtRazonSocial.getText().trim().equals(""))
           {
              sysau.E_ERROR();
              txtRazonSocial.setBackground(Color.RED);
              ShakingComponent sh_raz = new ShakingComponent(txtRazonSocial);
              sh_raz.startShake();
              JOptionPane.showMessageDialog(jf, "Es obligatorio el uso de 'Razónn Social' para el cliente", "Falta Razón Social del Cliente", JOptionPane.ERROR_MESSAGE);
              sysau.S_STOP();
           }else
           //DIRECCION
           if(JTAdireccion_J.getText().trim().equals(""))
           {
               sysau.E_ERROR();
               JTAdireccion_J.setBackground(Color.RED);
               ShakingComponent sh_Direccion = new ShakingComponent(JTAdireccion_J);
               sh_Direccion.startShake();
               JOptionPane.showMessageDialog(jf, "Es obligatorio el uso de 'Dirección' para el cliente", "Falta Dirección del cliente", JOptionPane.ERROR_MESSAGE);
               sysau.S_STOP();
           }else
           //ONE BY ONE OBLIGATORIOS
           //TELEFONO - CELULAR
           if(txttelefono_J.getText().trim().equals("") && txtcelular_J.getText().trim().equals(""))
           {
              sysau.E_ERROR();
              txttelefono_J.setBackground(Color.RED);
              txtcelular_J.setBackground(Color.RED);
              ShakingComponent sh_Telefono = new ShakingComponent(txttelefono_J);
              ShakingComponent sh_Celular = new ShakingComponent(txtcelular_J);
              sh_Telefono.startShake();  
              sh_Celular.startShake();
              JOptionPane.showMessageDialog(jf, "Es obligatorio el uso de algun 'Telefono' o 'Celular' para el cliente", "Falta información de comunicación", JOptionPane.ERROR_MESSAGE);
              sysau.S_STOP();
           }else
           {   
            //WARNINGS
            //RUC
            if(txtRUC.getText().trim().equals(""))
            {
                txtRUC.setBackground(Color.YELLOW);
                txtRUC.setText("-");
                ShakingComponent sh_RUC = new ShakingComponent(txtRUC);
                sh_RUC.startShake();
            }
            //NOM ENCARG
            if(txtNombreEncargado.getText().trim().equals(""))
            {
                txtNombreEncargado.setBackground(Color.YELLOW);
                txtNombreEncargado.setText("-");
                ShakingComponent sh_nom = new ShakingComponent(txtNombreEncargado);
                sh_nom.startShake();
            }
            //DNI ENCARG
            if(txtDNI_encarg.getText().trim().equals(""))
            {
                txtDNI_encarg.setBackground(Color.YELLOW);
                txtDNI_encarg.setText("-");
                ShakingComponent sh_DNI = new ShakingComponent(txtDNI_encarg);
                sh_DNI.startShake();
            }
            //CORREO
            if(txtcorreo_J.getText().trim().equals(""))
            {
                txtcorreo_J.setBackground(Color.YELLOW);
                txtcorreo_J.setText("-");
                ShakingComponent sh_Correo = new ShakingComponent(txtcorreo_J); 
                sh_Correo.startShake();
            }
            //COMPROBAR SI TELEFONO ESTA VACIO
            if(txttelefono_J.getText().trim().equals(""))
            {
                txttelefono_J.setText("-");
            }
            //COMPROBAR SI CELULAR ESTA VACIO
            if(txtcelular_J.getText().trim().equals(""))
            {
                txtcelular_J.setText("-");
            }
            
            //FIN DE LOGICA DE VERIFICACION
            
            //INICIO PROGRAMACION DE INGRESO
            
            //OBTENER DATOS
            String ID_CLI = IDCLI;
            String razon_social = txtRazonSocial.getText().toUpperCase();
            String RUC = txtRUC.getText().toUpperCase();
            String nom_encarg = txtNombreEncargado.getText().toUpperCase();;
            String DNI_encarg = txtDNI_encarg.getText().toUpperCase();;
            String telefono = txttelefono_J.getText().toUpperCase();
            String celular = txtcelular_J.getText().toUpperCase();
            String direccion = JTAdireccion_J.getText().toUpperCase();
            String correo = txtcorreo_J.getText().toUpperCase();                         
            //FIN  DE OBTENCION DE DATOS 
            
            
            //INICIO CONTROLADOR QUE INSERTA
            CliNCon.ModificarCliente_Juridico(ID_CLI, razon_social,nom_encarg,RUC,direccion,telefono,celular,correo,DNI_encarg);
            //FINAL CONTROLADOR QUE INSERTA
            
            evn.write(JFRPrincipal.JMSesion.getText(), "Modifico un cliente juridico", "JIFMantenerClientes -> Juridicos", "Botón 'Actualizar' Presionado");
            rslt.write(JFRPrincipal.JMSesion.getText(), "JIFMantenerClientes -> Juridico", "MODIFICACIÓN", "Se ha modificado el cliente con ID  "+ID_CLI+
                                                        "\n Razón Social: "+razon_social+" \n RUC: "+RUC);
            sysau.E_INFORMATION();
            JOptionPane.showMessageDialog(jf, "Cliente Modificado con exito!", "Cliente Modificado", JOptionPane.INFORMATION_MESSAGE);
            clearFRM_juri();
            editFRM_juri(false);
            ena_disaButtons_juri(true, false, false, true);
            btnnuevo_J.setText("Nuevo");
            txtfiltro_J.setEnabled(true);cbfiltro_J.setEnabled(true);
            condicion_datos = false;
            reiniciarColors_juri();
            clearCacheDB_juri();
            JTABPrincipal.setEnabled(true);
           }
        }
    } catch (Exception e) 
        {
           lc.write("Error al intentar modificar un nuevo Cliente Natural", "JIFMantenerCliente linea 344", e);
        }
        
    }//GEN-LAST:event_btnmodificar_JActionPerformed

    private void btncancelar_JActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar_JActionPerformed

    try 
    {
        evn.write(JFRPrincipal.JMSesion.getText(), "Ha cancelado la inserción o modificación de un cliente juridico", "JIFMantenerClientes -> Juridico", "Botón 'Cancelar' Presionado");
        clearFRM_juri();
        IDCLI="";
        btnnuevo_J.setText("Nuevo");
        btnmodificar_J.setText("Modificar");
        clearCacheDB_juri();
        JTABPrincipal.setEnabled(true);
        reiniciarColors_juri();
        editFRM_juri(false);
        txtfiltro_J.setEnabled(true);cbfiltro_J.setEnabled(true);
        condicion_datos = false;
        ena_disaButtons_juri(true, false, false, true);

    } catch (Exception e) 
       {
           lc.write("Ha ocurrido algun error al intentar cancelar una inserción", "JIFMantenerClientes -> naturales", e);
       }
        
    }//GEN-LAST:event_btncancelar_JActionPerformed

    private void btnsalir_JActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalir_JActionPerformed

    try 
    {           
      evn.write(JFRPrincipal.JMSesion.getText(), "Ha salido del formulario 'Mantener Clientes'", "JIFMantenerClientes", "Presiono Botón 'Salir'");
      JFRPrincipal.JSMMantenerClientes.setActionCommand("CERRADO");
      this.dispose();
    } catch (Exception e) 
    {
        lc.write("No se ha podido cerrar el formulario 'Mantener Clientes' debido a un error inesperado", "JIFMantenerClientes", e);
    }
        
    }//GEN-LAST:event_btnsalir_JActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPJuridico;
    private javax.swing.JPanel JPNatural;
    private javax.swing.JTabbedPane JTABPrincipal;
    private javax.swing.JTextArea JTAdireccion;
    private javax.swing.JTextArea JTAdireccion_J;
    private javax.swing.JTable JTJuridico;
    private javax.swing.JTable JTNatural;
    private javax.swing.JButton btncancelar_J;
    private javax.swing.JButton btncancelar_N;
    private javax.swing.JButton btnmodificar_J;
    private javax.swing.JButton btnmodificar_N;
    private javax.swing.JButton btnnuevo_J;
    private javax.swing.JButton btnnuevo_N;
    private javax.swing.JButton btnsalir_J;
    private javax.swing.JButton btnsalir_N;
    private javax.swing.JComboBox<String> cbfiltro;
    private javax.swing.JComboBox<String> cbfiltro_J;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lbldate1;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JLabel lblusuario1;
    private javax.swing.JTextField txtApellidoM;
    private javax.swing.JTextField txtApellidoP;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtDNI_encarg;
    private javax.swing.JTextField txtNombreEncargado;
    private javax.swing.JTextField txtRUC;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtcelular;
    private javax.swing.JTextField txtcelular_J;
    private javax.swing.JTextField txtcorreo;
    private javax.swing.JTextField txtcorreo_J;
    private javax.swing.JTextField txtfiltro;
    private javax.swing.JTextField txtfiltro_J;
    private javax.swing.JTextField txtnombres;
    private javax.swing.JTextField txttelefono;
    private javax.swing.JTextField txttelefono_J;
    // End of variables declaration//GEN-END:variables
}
