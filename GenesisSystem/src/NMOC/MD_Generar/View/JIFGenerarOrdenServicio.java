/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Generar.View;

import IACore.JTACON;
import ModuleWorker.IC.MWCON;
import ModuleWorker.SYSAUDIOCON;
import ModuleWorker.SYSFRMCON;
import ModuleWorker.View.JFRPrincipal;
import NCLPM.EVENTS;
import NCLPM.LOG;
import NCLPM.RESULTS;
import NMOC.JDCalendar;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class JIFGenerarOrdenServicio extends javax.swing.JInternalFrame 
{

    /**
     * Creates new form JIFGenerarOrdenServicio
     */
    
    LOG lc = new LOG();
    EVENTS evn = new EVENTS();
    RESULTS rslt = new RESULTS();
    SYSFRMCON sysfrm = new SYSFRMCON();
    MWCON mw = new MWCON();
    JFrame form;
    JTACON JTC = new JTACON();
    Color ColorInicial;
   
    public JIFGenerarOrdenServicio() 
    {
        initComponents();
        this.setTitle(sysfrm.T_GenerarOrden());
        sysfrm.B_GenerarOrden(this.getContentPane());
        
        lblusuario.setText(JFRPrincipal.JMSesion.getText());
        
        lbldate.setText(mw.fecha_actual());

        JTAAnuncio.setLineWrap(true);
        JTAIA.setLineWrap(true);
        JTAdetcliente.setLineWrap(true);
        ColorInicial = txtfecha.getBackground();

    }
    
    //Escribe dentro del anunciador la ultima orden generada en el día
    private void ultima_orden()
    {
        String cod;
        
    }
    
    //Borra y limpia texto
    private void clearFRM()
    {
        //BLOQUE SEGMENTADO 1
        txtidorden.setText("");
        txtnumeroorden.setText("");
        JTAAnuncio.setText("");
        cbestado.setSelectedIndex(0);
        JCHAntigua.setSelected(false);
        JCHTicketTrabajo.setSelected(false);
        RDBNatural.setSelected(false);
        RDBJuridica.setSelected(false);
        //BLOQUE SEGMENTADO 2 : Informacion Basica
        txtfecha.setText("");
        txthora.setText("");
        txtgiro.setText("");
        txtdocumentacion.setText("");
        txtcosto.setText("");
        txtcostofinal.setText("");
        txtdescuentomanual.setText("");
        txtdescuentocodigo.setText("");
        //BLOQUE SEGMENTADO 3 : Informacion Vendedor
        txtcodvendCrystal.setText("");
        txtidVendedor.setText("");
        txtnombrevendedor.setText("");
        //BLOQUE SEGMENTADO 4 : JTA IA
        JTAIA.setText("");
        //Bloque Segmentado 5 : Informacion Cliente
        txtcodCliente.setText("");
        txtnombrecliente.setText("");
        JTAdetcliente.setText("");
        //BLOQUE Segmentado 6 : Contenido de Orden
        CBconsultacontOrden.setSelectedIndex(0);
        
    }
    
    private void editFRM(boolean cond)
    {
        //BLOQUE SEGMENTADO 1
        cbestado.setEnabled(cond);
        RDBNatural.setEnabled(cond);
        RDBJuridica.setEnabled(cond);
        //BLOQUE SEGMENTADO 2 : Informacion Basica
        txtgiro.setEditable(cond);
        txtdocumentacion.setEditable(cond);
        txtcosto.setEditable(cond);
        txtcostofinal.setEditable(cond);
        txtdescuentomanual.setEditable(cond);
        txtdescuentocodigo.setEditable(cond);
        //BLOQUE SEGMENTADO 3 : Informacion Vendedor
        txtcodvendCrystal.setEditable(cond);
        //BLOQUE Segmentado 6 : Contenido de Orden
        CBconsultacontOrden.setEnabled(cond);
    }
    
    private void enadisa_bloque1_botones(boolean nuevo,boolean modificar,
            boolean cancelar,boolean buscar,boolean copiar,boolean pdf,
            boolean imprimir,boolean salir)
    {
        btnnuevo.setEnabled(nuevo);
        btnmodificar.setEnabled(modificar);
        btncancelar.setEnabled(cancelar);
        btnbuscar.setEnabled(buscar);
        btncopiar.setEnabled(copiar);
        btnPDF.setEnabled(pdf);
        btnimprimir.setEnabled(imprimir);
        btnsalir.setEnabled(salir);
    }
    
    private void enadisa_bloque2_botones(boolean fecha, boolean hora, boolean consultar, boolean quitar)
    {
        btnfecha.setEnabled(fecha);
        btnhora.setEnabled(hora);
        btnconsultarDescuentocodigo.setEnabled(consultar);
        btnQuitarDescuentoCodigo.setEnabled(quitar);
    }
     
    private void enadisa_bloque3_botones(boolean consult_clasico, boolean quitar_clasico, 
            boolean consult_asoc, boolean quitar_asoc)
    {
        btnconsultarVendedor.setEnabled(consult_clasico);
        btnquitarVendedor.setEnabled(quitar_clasico);
        btnconsultarvendCrystal.setEnabled(consult_asoc);
        btnquitarvendCrystal.setEnabled(quitar_asoc);
    }
      
    private void enadisa_bloque5_botones(boolean buscar)
    {
        btnBuscarcliente.setEnabled(buscar);
    }
       
    private void enadisa_bloque6_botones(boolean areas,boolean servicios, boolean operarios, boolean productos, 
            boolean implementos, boolean consultar, boolean consult_general)
    {
        btnAgregarAreas.setEnabled(areas);
        btnAgregarServicios.setEnabled(servicios);
        btnAgregarOperarios.setEnabled(operarios);
        btnAgregarProductos.setEnabled(productos);
        btnAgregarImplementos.setEnabled(implementos);
        btnconsultarcontenidoOrden.setEnabled(consultar);
        btnconsultageneral.setEnabled(consult_general);
    }
    
    private void reiniciarColors()
    {
        //BLOQUE SEGMENTADO 1
        txtidorden.setBackground(ColorInicial);
        txtnumeroorden.setBackground(ColorInicial);
        JTAAnuncio.setBackground(ColorInicial);
        //BLOQUE SEGMENTADO 2 : Informacion Basica
        txtfecha.setBackground(ColorInicial);
        txthora.setBackground(ColorInicial);
        txtgiro.setBackground(ColorInicial);
        txtdocumentacion.setBackground(ColorInicial);
        txtcosto.setBackground(ColorInicial);
        txtcostofinal.setBackground(ColorInicial);
        txtdescuentomanual.setBackground(ColorInicial);
        txtdescuentocodigo.setBackground(ColorInicial);
        //BLOQUE SEGMENTADO 3 : Informacion Vendedor
        txtcodvendCrystal.setBackground(ColorInicial);
        txtidVendedor.setBackground(ColorInicial);
        txtnombrevendedor.setBackground(ColorInicial);
        //BLOQUE SEGMENTADO 4 : JTA IA
        JTAIA.setBackground(ColorInicial);
        //Bloque Segmentado 5 : Informacion Cliente
        txtcodCliente.setBackground(ColorInicial);
        txtnombrecliente.setBackground(ColorInicial);
        JTAdetcliente.setBackground(ColorInicial);
    }
    
    private void CheckBox_Action(String action)
    {
        if(action.equals("ANT"))
        {
            clearFRM();
            RDBNatural.setEnabled(true);
            RDBJuridica.setEnabled(true);
            JCHAntigua.setSelected(true);
            enadisa_bloque1_botones(true, false, true, true, false, false, false, true);
            txtdescuentomanual.setText("0");
        }
        if(action.equals("TTRB"))
        {
            clearFRM();
            RDBNatural.setEnabled(true);
            RDBJuridica.setEnabled(true);
            JCHTicketTrabajo.setSelected(true);
            enadisa_bloque1_botones(true, false, true, true, false, false, false, true);
            txtdescuentomanual.setText("0");
        }
    }
    
    private void clearCacheDB()
    {
        try 
        {
            //CARGARDATOS
            
        } catch (Exception e) 
            {
                lc.write("Error al intentar borrar la cache de la DB", "JIFGenerarOrdenServicio metodo clearcacheDB linea 172", e);
            }
    }
    
    public String modo_orden = "";
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TipoOrden = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        lblusuario = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbldate = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtidorden = new javax.swing.JTextField();
        txtnumeroorden = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbestado = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        RDBNatural = new javax.swing.JRadioButton();
        RDBJuridica = new javax.swing.JRadioButton();
        btnnuevo = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        btnbuscar = new javax.swing.JButton();
        btncopiar = new javax.swing.JButton();
        btnPDF = new javax.swing.JButton();
        btnimprimir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTAAnuncio = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTAIA = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        lblusuario1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtfecha = new javax.swing.JTextField();
        txthora = new javax.swing.JTextField();
        btnfecha = new javax.swing.JButton();
        btnhora = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtgiro = new javax.swing.JTextField();
        txtdocumentacion = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtcosto = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtcostofinal = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtdescuentomanual = new javax.swing.JTextField();
        txtdescuentocodigo = new javax.swing.JTextField();
        btnconsultarDescuentocodigo = new javax.swing.JButton();
        btnQuitarDescuentoCodigo = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        lblusuario2 = new javax.swing.JLabel();
        lblusuario3 = new javax.swing.JLabel();
        btnconsultarVendedor = new javax.swing.JButton();
        btnquitarVendedor = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        txtcodvendCrystal = new javax.swing.JTextField();
        lblusuario4 = new javax.swing.JLabel();
        btnconsultarvendCrystal = new javax.swing.JButton();
        btnquitarvendCrystal = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        txtidVendedor = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtnombrevendedor = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtcodCliente = new javax.swing.JTextField();
        txtnombrecliente = new javax.swing.JTextField();
        lblnomcli = new javax.swing.JLabel();
        lblusuario5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTAdetcliente = new javax.swing.JTextArea();
        btnBuscarcliente = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        lblusuario6 = new javax.swing.JLabel();
        lblusuario7 = new javax.swing.JLabel();
        btnAgregarAreas = new javax.swing.JButton();
        btnAgregarServicios = new javax.swing.JButton();
        btnAgregarProductos = new javax.swing.JButton();
        btnAgregarOperarios = new javax.swing.JButton();
        btnAgregarImplementos = new javax.swing.JButton();
        CBconsultacontOrden = new javax.swing.JComboBox<>();
        btnconsultarcontenidoOrden = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        JCHAntigua = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        JCHTicketTrabajo = new javax.swing.JCheckBox();
        lblusuario8 = new javax.swing.JLabel();
        btnconsultageneral = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("usuario:");

        lblusuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblusuario.setText("Manuel Fernando Saavedra Benites");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 102));
        jLabel4.setText("Generar Orden Servicio");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Fecha Sistema:");

        lbldate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbldate.setText("18/12/2017");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("ID Orden:");

        txtidorden.setEditable(false);
        txtidorden.setBackground(new java.awt.Color(204, 255, 204));

        txtnumeroorden.setEditable(false);
        txtnumeroorden.setBackground(new java.awt.Color(204, 255, 255));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Número de Orden:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Estado:");

        cbestado.setBackground(new java.awt.Color(204, 204, 204));
        cbestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        cbestado.setSelectedItem("EN ESPERA");
        cbestado.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Tipo Orden:");

        TipoOrden.add(RDBNatural);
        RDBNatural.setSelected(true);
        RDBNatural.setText("Natural");
        RDBNatural.setEnabled(false);

        TipoOrden.add(RDBJuridica);
        RDBJuridica.setText("Juridica");
        RDBJuridica.setEnabled(false);

        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Files-New-File-icon.png"))); // NOI18N
        btnnuevo.setText("Nuevo");
        btnnuevo.setEnabled(false);
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

        btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/search-icon.png"))); // NOI18N
        btnbuscar.setText("Buscar");
        btnbuscar.setEnabled(false);
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        btncopiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Copy-iconV2.png"))); // NOI18N
        btncopiar.setText("Copiar");
        btncopiar.setEnabled(false);
        btncopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncopiarActionPerformed(evt);
            }
        });

        btnPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Adobe-PDF-Document-icon.png"))); // NOI18N
        btnPDF.setText("PDF");
        btnPDF.setEnabled(false);
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });

        btnimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/print-icon.png"))); // NOI18N
        btnimprimir.setText("Imprimir");
        btnimprimir.setEnabled(false);
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        JTAAnuncio.setEditable(false);
        JTAAnuncio.setBackground(new java.awt.Color(204, 204, 204));
        JTAAnuncio.setColumns(15);
        JTAAnuncio.setRows(2);
        jScrollPane1.setViewportView(JTAAnuncio);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        JTAIA.setEditable(false);
        JTAIA.setBackground(new java.awt.Color(204, 204, 204));
        JTAIA.setColumns(20);
        JTAIA.setRows(5);
        jScrollPane2.setViewportView(JTAIA);

        lblusuario1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblusuario1.setText("Información Básica.");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Fecha:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Hora:");

        txtfecha.setEditable(false);
        txtfecha.setBackground(new java.awt.Color(204, 204, 204));

        txthora.setEditable(false);
        txthora.setBackground(new java.awt.Color(204, 204, 204));

        btnfecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/event-search-icon.png"))); // NOI18N
        btnfecha.setText("Fecha");
        btnfecha.setContentAreaFilled(false);
        btnfecha.setEnabled(false);
        btnfecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfechaActionPerformed(evt);
            }
        });

        btnhora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Time.png"))); // NOI18N
        btnhora.setText("Hora");
        btnhora.setContentAreaFilled(false);
        btnhora.setEnabled(false);
        btnhora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhoraActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Giro:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Documentos:");

        txtgiro.setEditable(false);
        txtgiro.setBackground(new java.awt.Color(204, 204, 204));

        txtdocumentacion.setEditable(false);
        txtdocumentacion.setBackground(new java.awt.Color(204, 204, 204));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Costo:");

        txtcosto.setEditable(false);
        txtcosto.setBackground(new java.awt.Color(204, 204, 204));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 255));
        jLabel17.setText("Costo Final:");

        txtcostofinal.setEditable(false);
        txtcostofinal.setBackground(new java.awt.Color(204, 204, 255));
        txtcostofinal.setText("0");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Descuento Manual:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Descuento Codigo:");

        txtdescuentomanual.setEditable(false);
        txtdescuentomanual.setBackground(new java.awt.Color(204, 204, 204));
        txtdescuentomanual.setText("0");

        txtdescuentocodigo.setEditable(false);
        txtdescuentocodigo.setBackground(new java.awt.Color(204, 204, 204));

        btnconsultarDescuentocodigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/search-icon.png"))); // NOI18N
        btnconsultarDescuentocodigo.setText("Consultar");
        btnconsultarDescuentocodigo.setContentAreaFilled(false);
        btnconsultarDescuentocodigo.setEnabled(false);
        btnconsultarDescuentocodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconsultarDescuentocodigoActionPerformed(evt);
            }
        });

        btnQuitarDescuentoCodigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/delete-icon.png"))); // NOI18N
        btnQuitarDescuentoCodigo.setText("Quitar");
        btnQuitarDescuentoCodigo.setContentAreaFilled(false);
        btnQuitarDescuentoCodigo.setEnabled(false);
        btnQuitarDescuentoCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarDescuentoCodigoActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("%");

        lblusuario2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblusuario2.setText("Información Vendedor.");

        lblusuario3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblusuario3.setText("Clasico.");

        btnconsultarVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/search-icon.png"))); // NOI18N
        btnconsultarVendedor.setText("Consultar");
        btnconsultarVendedor.setContentAreaFilled(false);
        btnconsultarVendedor.setEnabled(false);
        btnconsultarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconsultarVendedorActionPerformed(evt);
            }
        });

        btnquitarVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/delete-icon.png"))); // NOI18N
        btnquitarVendedor.setText("Quitar");
        btnquitarVendedor.setContentAreaFilled(false);
        btnquitarVendedor.setEnabled(false);
        btnquitarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnquitarVendedorActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Codigo:");

        txtcodvendCrystal.setEditable(false);
        txtcodvendCrystal.setBackground(new java.awt.Color(204, 204, 204));

        lblusuario4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblusuario4.setText("Asociado:");

        btnconsultarvendCrystal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/search-icon.png"))); // NOI18N
        btnconsultarvendCrystal.setText("Consultar");
        btnconsultarvendCrystal.setContentAreaFilled(false);
        btnconsultarvendCrystal.setEnabled(false);
        btnconsultarvendCrystal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconsultarvendCrystalActionPerformed(evt);
            }
        });

        btnquitarvendCrystal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/delete-icon.png"))); // NOI18N
        btnquitarvendCrystal.setText("Quitar");
        btnquitarvendCrystal.setContentAreaFilled(false);
        btnquitarvendCrystal.setEnabled(false);
        btnquitarvendCrystal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnquitarvendCrystalActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("ID:");

        txtidVendedor.setEditable(false);
        txtidVendedor.setBackground(new java.awt.Color(204, 255, 204));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Nombre:");

        txtnombrevendedor.setEditable(false);
        txtnombrevendedor.setBackground(new java.awt.Color(204, 255, 255));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("COD:");

        txtcodCliente.setEditable(false);
        txtcodCliente.setBackground(new java.awt.Color(204, 255, 204));

        txtnombrecliente.setEditable(false);
        txtnombrecliente.setBackground(new java.awt.Color(204, 255, 255));

        lblnomcli.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblnomcli.setText("Nombres:");

        lblusuario5.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblusuario5.setText("Detalle.");

        JTAdetcliente.setEditable(false);
        JTAdetcliente.setBackground(new java.awt.Color(204, 204, 204));
        JTAdetcliente.setColumns(20);
        JTAdetcliente.setRows(5);
        jScrollPane3.setViewportView(JTAdetcliente);

        btnBuscarcliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/search-icon.png"))); // NOI18N
        btnBuscarcliente.setText("Buscar");
        btnBuscarcliente.setContentAreaFilled(false);
        btnBuscarcliente.setEnabled(false);
        btnBuscarcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarclienteActionPerformed(evt);
            }
        });

        lblusuario6.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblusuario6.setText("Contenido de Orden.");

        lblusuario7.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblusuario7.setText("Consultar contenido de orden.");

        btnAgregarAreas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/home.png"))); // NOI18N
        btnAgregarAreas.setText("Áreas");
        btnAgregarAreas.setContentAreaFilled(false);
        btnAgregarAreas.setEnabled(false);
        btnAgregarAreas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarAreasActionPerformed(evt);
            }
        });

        btnAgregarServicios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Services_24.png"))); // NOI18N
        btnAgregarServicios.setText("Servicios");
        btnAgregarServicios.setContentAreaFilled(false);
        btnAgregarServicios.setEnabled(false);
        btnAgregarServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarServiciosActionPerformed(evt);
            }
        });

        btnAgregarProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/ProductsV2_24.png"))); // NOI18N
        btnAgregarProductos.setText("Productos");
        btnAgregarProductos.setContentAreaFilled(false);
        btnAgregarProductos.setEnabled(false);
        btnAgregarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductosActionPerformed(evt);
            }
        });

        btnAgregarOperarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/jobs.png"))); // NOI18N
        btnAgregarOperarios.setText("Operarios");
        btnAgregarOperarios.setContentAreaFilled(false);
        btnAgregarOperarios.setEnabled(false);
        btnAgregarOperarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarOperariosActionPerformed(evt);
            }
        });

        btnAgregarImplementos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/machinesV1.png"))); // NOI18N
        btnAgregarImplementos.setText("Implementos");
        btnAgregarImplementos.setContentAreaFilled(false);
        btnAgregarImplementos.setEnabled(false);
        btnAgregarImplementos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarImplementosActionPerformed(evt);
            }
        });

        CBconsultacontOrden.setBackground(new java.awt.Color(204, 204, 204));
        CBconsultacontOrden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GENERAL", "AREAS", "SERVICIOS", "OPERARIOS", "PRODUCTOS", "IMPLEMENTOS" }));
        CBconsultacontOrden.setSelectedItem("EN ESPERA");
        CBconsultacontOrden.setEnabled(false);

        btnconsultarcontenidoOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/search-icon.png"))); // NOI18N
        btnconsultarcontenidoOrden.setText("Consultar");
        btnconsultarcontenidoOrden.setEnabled(false);
        btnconsultarcontenidoOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconsultarcontenidoOrdenActionPerformed(evt);
            }
        });

        JCHAntigua.setText("Antigua");
        JCHAntigua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCHAntiguaActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Tipo Inserción:");

        JCHTicketTrabajo.setText("Ticket Trabajo");
        JCHTicketTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCHTicketTrabajoActionPerformed(evt);
            }
        });

        lblusuario8.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblusuario8.setText("Información Cliente.");

        btnconsultageneral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/search-icon.png"))); // NOI18N
        btnconsultageneral.setText("Consult. General");
        btnconsultageneral.setEnabled(false);
        btnconsultageneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconsultageneralActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(10, 10, 10)
                        .addComponent(lbldate, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtidorden, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel9)
                                                .addComponent(jLabel8))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtnumeroorden, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cbestado, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jSeparator2)
                                        .addComponent(lblusuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel18)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtdescuentomanual, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel20))
                                        .addComponent(jSeparator4)
                                        .addComponent(lblusuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(jLabel21)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtcodvendCrystal))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(lblusuario3)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnconsultarVendedor)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnquitarVendedor)))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(41, 41, 41)
                                                    .addComponent(jLabel22)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtidVendedor))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jLabel23)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtnombrevendedor))))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblusuario4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnconsultarvendCrystal)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnquitarvendCrystal))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(btncopiar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnsalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(btnnuevo)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnmodificar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btncancelar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnbuscar)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel19)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtdescuentocodigo)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnconsultarDescuentocodigo)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnQuitarDescuentoCodigo))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLabel13)
                                                            .addComponent(jLabel12))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel16)
                                                        .addGap(2, 2, 2)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(txtcosto)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel17)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtcostofinal, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(txtfecha, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                                            .addComponent(txthora))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(btnfecha)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel14)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtgiro, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(btnhora, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel15)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtdocumentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(RDBNatural))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(JCHAntigua)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(JCHTicketTrabajo)
                                            .addComponent(RDBJuridica))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblusuario8, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(btnAgregarImplementos))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2)
                                    .addComponent(jSeparator3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtcodCliente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblnomcli)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtnombrecliente, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSeparator5)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblusuario6)
                                            .addComponent(btnBuscarcliente)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(btnAgregarOperarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(btnAgregarAreas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(btnAgregarProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(btnAgregarServicios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblusuario7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(CBconsultacontOrden, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnconsultarcontenidoOrden, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(lblusuario5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(btnconsultageneral, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblusuario)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(lbldate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7)
                                            .addComponent(txtidorden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8)
                                            .addComponent(txtnumeroorden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel9)
                                            .addComponent(cbestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(JCHTicketTrabajo)
                                    .addComponent(JCHAntigua)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(RDBNatural)
                                    .addComponent(RDBJuridica))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnnuevo)
                                    .addComponent(btnmodificar)
                                    .addComponent(btncancelar)
                                    .addComponent(btnbuscar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btncopiar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnPDF)
                                        .addComponent(btnimprimir)
                                        .addComponent(btnsalir)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblusuario1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnfecha)
                                    .addComponent(jLabel14)
                                    .addComponent(txtgiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel13)
                                        .addComponent(txthora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnhora))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel15)
                                        .addComponent(txtdocumentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(txtcosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)
                                    .addComponent(txtcostofinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(txtdescuentomanual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(txtdescuentocodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnconsultarDescuentocodigo)
                                    .addComponent(btnQuitarDescuentoCodigo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblusuario2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnconsultarVendedor)
                                    .addComponent(lblusuario3)
                                    .addComponent(btnquitarVendedor)
                                    .addComponent(jLabel22)
                                    .addComponent(txtidVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(txtcodvendCrystal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23)
                                    .addComponent(txtnombrevendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnconsultarvendCrystal)
                                    .addComponent(lblusuario4)
                                    .addComponent(btnquitarvendCrystal))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblusuario8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(txtcodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblnomcli)
                                    .addComponent(txtnombrecliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblusuario5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarcliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblusuario6)
                                    .addComponent(lblusuario7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnAgregarAreas)
                                    .addComponent(btnAgregarServicios)
                                    .addComponent(CBconsultacontOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnAgregarOperarios)
                                    .addComponent(btnAgregarProductos)
                                    .addComponent(btnconsultarcontenidoOrden))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnAgregarImplementos)
                                    .addComponent(btnconsultageneral))))
                        .addGap(7, 7, 7))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed

    String Tipo_orden = mw.getSelectedButtonText(TipoOrden);
    try 
       {
           //INICIO CABECERAS IMPORTANTES
           //Ejecutado al inicio de todo
           SYSAUDIOCON sysau = new SYSAUDIOCON();
           JFrame jf=new JFrame();
           jf.setAlwaysOnTop(true);
           clearCacheDB();
           
           if(Tipo_orden.equals("Natural"))
           {
               lblnomcli.setText("Nombres:");
               modo_orden = "Natural";
           }
           if(Tipo_orden.equals("Juridica"))
           {
               lblnomcli.setText("Razón S.:");
               modo_orden = "Juridica";
           }
           editFRM(true);
           //BLOQUE 2:
           enadisa_bloque2_botones(true, true, true, true);
           //BLOQUE 3:
           enadisa_bloque3_botones(true, true, true, true);
           //BLOQUE 5:
           enadisa_bloque5_botones(true);
           //BLOQUE 6:
           enadisa_bloque6_botones(true, true, true, true, true, true, true);
           
           evn.write(lblusuario.getText(), "Hizo click en el botón 'Nuevo' Orden de Servicio "+modo_orden, "JIFGenerarOrdenServicio", "Botón 'Nuevo' presionado");
           
           //FIN INICIO CABECERAS IMPORTANTES
           
           
           
           
           
           
           
           

       } catch (Exception e) 
           {
              lc.write("Ha ocurrido un error al intentar insertar una nueva orden","JIFGenerarOrdenServicio", e);
           }

    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed

    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed

    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed

        System.out.println(mw.getSelectedButtonText(TipoOrden));
        
        
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btncopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncopiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncopiarActionPerformed

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPDFActionPerformed

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnimprimirActionPerformed

    private void btnfechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfechaActionPerformed

    try 
    {
        evn.write(lblusuario.getText(), "Abrio el selector de fechas", "JIFGenerarOrdenServicio -> JDCalendar", "Botón 'fecha' presionado");
        JDCalendar ca = new JDCalendar(form, true,"D");
        ca.formulario="ORDEN_SERV";
        ca.setVisible(true);
        
    } catch (Exception e) 
        {
            lc.write("No se pudo abrir el selector de fechas debido a un error inesperado", "JIFGenerarOrdenServicio -> boton fecha", e);
        }

    }//GEN-LAST:event_btnfechaActionPerformed

    private void btnhoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhoraActionPerformed

    try 
    {
        evn.write(lblusuario.getText(), "Abrio el selector de horas", "JIFGenerarOrdenServicio -> JDCalendar", "Botón 'hora' presionado");
        JDCalendar ca = new JDCalendar(form, true,"T");
        ca.formulario="ORDEN_SERV";
        ca.setVisible(true);
        
    } catch (Exception e) 
        {
            lc.write("No se pudo abrir el selector de fechas debido a un error inesperado", "JIFGenerarOrdenServicio -> boton hora", e);
        }
    }//GEN-LAST:event_btnhoraActionPerformed

    private void btnconsultarDescuentocodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultarDescuentocodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnconsultarDescuentocodigoActionPerformed

    private void btnQuitarDescuentoCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarDescuentoCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuitarDescuentoCodigoActionPerformed

    private void btnconsultarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultarVendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnconsultarVendedorActionPerformed

    private void btnquitarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquitarVendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnquitarVendedorActionPerformed

    private void btnconsultarvendCrystalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultarvendCrystalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnconsultarvendCrystalActionPerformed

    private void btnquitarvendCrystalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquitarvendCrystalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnquitarvendCrystalActionPerformed

    private void btnBuscarclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarclienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarclienteActionPerformed

    private void btnAgregarAreasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarAreasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarAreasActionPerformed

    private void btnAgregarServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarServiciosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarServiciosActionPerformed

    private void btnAgregarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarProductosActionPerformed

    private void btnAgregarOperariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarOperariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarOperariosActionPerformed

    private void btnAgregarImplementosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarImplementosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarImplementosActionPerformed

    private void btnconsultarcontenidoOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultarcontenidoOrdenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnconsultarcontenidoOrdenActionPerformed

    private void btnconsultageneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultageneralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnconsultageneralActionPerformed

    private void JCHAntiguaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCHAntiguaActionPerformed
        
      //EJECUTADO ANTES DE TODO
      SYSAUDIOCON sysau = new SYSAUDIOCON();
      //EJECUTADO ANTES DE TODA CONDICIONAL
      JFrame jf=new JFrame();
      jf.setAlwaysOnTop(true);
      
      if(JCHTicketTrabajo.isSelected())
      {
        JCHAntigua.setSelected(false);
        sysau.E_ERROR();
        JOptionPane.showMessageDialog(jf, "Primero debe de desactivar la inserción por 'Ticket de Trabajo'", "Otra opción ya esta seleccionada", JOptionPane.ERROR_MESSAGE);
      }else
         if(JCHAntigua.isSelected())
         {
             CheckBox_Action("ANT");
         }
          else 
            if(JCHAntigua.isSelected()==false)
            {
                RDBNatural.setEnabled(false);
                RDBJuridica.setEnabled(false);
                clearFRM();
                //BLOQUE 1:
                enadisa_bloque1_botones(false, false, false, false, false, false, false, true);
                //BLOQUE 2:
                enadisa_bloque2_botones(false, false, false, false);
                //BLOQUE 3:
                enadisa_bloque3_botones(false, false, false, false);
                //BLOQUE 5:
                enadisa_bloque5_botones(false);
                //BLOQUE 6:
                enadisa_bloque6_botones(false, false, false, false, false, false, false);         
            }
             

    }//GEN-LAST:event_JCHAntiguaActionPerformed

    private void JCHTicketTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCHTicketTrabajoActionPerformed

      
      //EJECUTADO ANTES DE TODO
      SYSAUDIOCON sysau = new SYSAUDIOCON();
      //EJECUTADO ANTES DE TODA CONDICIONAL
      JFrame jf=new JFrame();
      jf.setAlwaysOnTop(true);

      if(JCHAntigua.isSelected())
      {
        JCHTicketTrabajo.setSelected(false);
        sysau.E_ERROR();
        JOptionPane.showMessageDialog(jf, "Primero debe de desactivar la inserción 'Antigua'", "Otra opción ya esta seleccionada", JOptionPane.ERROR_MESSAGE);
      }else
         if(JCHTicketTrabajo.isSelected())
         {
             CheckBox_Action("TTRB");
         }
         else 
            if(JCHTicketTrabajo.isSelected()==false)
            {
                RDBNatural.setEnabled(false);
                RDBJuridica.setEnabled(false);
                clearFRM();
                //BLOQUE 1:
                enadisa_bloque1_botones(false, false, false, false, false, false, false, true);
                //BLOQUE 2:
                enadisa_bloque2_botones(false, false, false, false);
                //BLOQUE 3:
                enadisa_bloque3_botones(false, false, false, false);
                //BLOQUE 5:
                enadisa_bloque5_botones(false);
                //BLOQUE 6:
                enadisa_bloque6_botones(false, false, false, false, false, false, false);
            }

    }//GEN-LAST:event_JCHTicketTrabajoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBconsultacontOrden;
    private javax.swing.JCheckBox JCHAntigua;
    private javax.swing.JCheckBox JCHTicketTrabajo;
    private javax.swing.JTextArea JTAAnuncio;
    private javax.swing.JTextArea JTAIA;
    private javax.swing.JTextArea JTAdetcliente;
    private javax.swing.JRadioButton RDBJuridica;
    private javax.swing.JRadioButton RDBNatural;
    private javax.swing.ButtonGroup TipoOrden;
    private javax.swing.JButton btnAgregarAreas;
    private javax.swing.JButton btnAgregarImplementos;
    private javax.swing.JButton btnAgregarOperarios;
    private javax.swing.JButton btnAgregarProductos;
    private javax.swing.JButton btnAgregarServicios;
    private javax.swing.JButton btnBuscarcliente;
    private javax.swing.JButton btnPDF;
    private javax.swing.JButton btnQuitarDescuentoCodigo;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnconsultageneral;
    private javax.swing.JButton btnconsultarDescuentocodigo;
    private javax.swing.JButton btnconsultarVendedor;
    private javax.swing.JButton btnconsultarcontenidoOrden;
    private javax.swing.JButton btnconsultarvendCrystal;
    private javax.swing.JButton btncopiar;
    private javax.swing.JButton btnfecha;
    private javax.swing.JButton btnhora;
    private javax.swing.JButton btnimprimir;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnquitarVendedor;
    private javax.swing.JButton btnquitarvendCrystal;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox<String> cbestado;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lblnomcli;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JLabel lblusuario1;
    private javax.swing.JLabel lblusuario2;
    private javax.swing.JLabel lblusuario3;
    private javax.swing.JLabel lblusuario4;
    private javax.swing.JLabel lblusuario5;
    private javax.swing.JLabel lblusuario6;
    private javax.swing.JLabel lblusuario7;
    private javax.swing.JLabel lblusuario8;
    private javax.swing.JTextField txtcodCliente;
    private javax.swing.JTextField txtcodvendCrystal;
    private javax.swing.JTextField txtcosto;
    private javax.swing.JTextField txtcostofinal;
    private javax.swing.JTextField txtdescuentocodigo;
    private javax.swing.JTextField txtdescuentomanual;
    private javax.swing.JTextField txtdocumentacion;
    public static javax.swing.JTextField txtfecha;
    private javax.swing.JTextField txtgiro;
    public static javax.swing.JTextField txthora;
    private javax.swing.JTextField txtidVendedor;
    private javax.swing.JTextField txtidorden;
    private javax.swing.JTextField txtnombrecliente;
    private javax.swing.JTextField txtnombrevendedor;
    private javax.swing.JTextField txtnumeroorden;
    // End of variables declaration//GEN-END:variables
}
