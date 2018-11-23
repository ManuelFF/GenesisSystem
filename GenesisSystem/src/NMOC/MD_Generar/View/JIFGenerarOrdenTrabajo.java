/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Generar.View;

import IACore.JTACON;
import ModuleWorker.IC.MWCON;
import ModuleWorker.IC.ShakingComponent;
import ModuleWorker.SYSAUDIOCON;
import ModuleWorker.SYSFRMCON;
import ModuleWorker.View.JFRPrincipal;
import static ModuleWorker.View.JFRPrincipal.JMSesion;
import static ModuleWorker.View.JFRPrincipal.JSMMantenerClientes;
import static ModuleWorker.View.JFRPrincipal.JDEscritorio;
import NBRPM.RP_ORDEN_SERV;
import NCLPM.EVENTS;
import NCLPM.LOG;
import NCLPM.RESULTS;
import NISPM.SGL_CL_AreaTrabajo;
import NISPM.SGL_CL_Implementos;
import NISPM.SGL_CL_Operarios;
import NISPM.SGL_CL_Productos;
import NISPM.SGL_CL_Servicios;
import NISPM.SGL_CTRL_AreaTrabajo;
import NISPM.SGL_CTRL_Implementos;
import NISPM.SGL_CTRL_Operarios;
import NISPM.SGL_CTRL_Productos;
import NISPM.SGL_CTRL_Servicios;
import NISPM.SGL_VIEW_AreaTrabajo;
import NISPM.SGL_VIEW_Implementos;
import NISPM.SGL_VIEW_Operarios;
import NISPM.SGL_VIEW_Productos;
import NISPM.SGL_VIEW_Servicios;
import NMOC.GL_JDBuscarCliente;
import NMOC.GL_JDBuscarOrdenes;
import NMOC.GL_JDBuscarVendedor;
import NMOC.GL_JDCalendar;
import NMOC.MD_Generar.Core.O_OrdenTrabajo;
import NMOC.MD_Generar.IC.CO_GenerarOrdenTrabajo;
import NMOC.MD_Generar.IC.UltimoID;
import NMOC.MD_Mantenimientos.View.JIFMantenerClientes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class JIFGenerarOrdenTrabajo extends javax.swing.JInternalFrame 
{

    /**
     * Creates new form JIFGenerarOrdenTrabajo
     */
    
    LOG lc = new LOG();
    EVENTS evn = new EVENTS();
    RESULTS rslt = new RESULTS();
    SYSFRMCON sysfrm = new SYSFRMCON();
    MWCON mw = new MWCON();
    JFrame form;
    JTACON JTC = new JTACON();
    CO_GenerarOrdenTrabajo P_orden = new CO_GenerarOrdenTrabajo();
    Color ColorInicial;
   
    double sumaAreaTotalMT2 = 0;
    double IntegerTotalMT2 = 0;
    double sumaAreaTotalMT3 = 0;
    double IntegerTotalMT3 = 0;
    
    //SINGLATONES
    //INVOKE
    ArrayList<SGL_CL_AreaTrabajo> areas = SGL_CTRL_AreaTrabajo.getInstance().getSgl_area();
    ArrayList<SGL_CL_Servicios> servicios = SGL_CTRL_Servicios.getInstance().getsgl_servicio();
    ArrayList<SGL_CL_Operarios> operarios = SGL_CTRL_Operarios.getInstance().getsgl_operario();
    ArrayList<SGL_CL_Productos> productos = SGL_CTRL_Productos.getInstance().getsgl_producto();
    ArrayList<SGL_CL_Implementos> implementos = SGL_CTRL_Implementos.getInstance().getsgl_implementos();

    
    public JIFGenerarOrdenTrabajo() 
    {
        initComponents();
        this.setTitle(sysfrm.T_GenerarOrden());
        sysfrm.B_GenerarOrden(this.getContentPane());
        
        ultima_orden();
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
        JTC.clear(JTAAnuncio);
        JTC.msj(JTAAnuncio, "La última orden generada fue la:\n\nORDEN DE SERVICIO N° "+P_orden.R_ultimaOrden());
    }
    
    //RETORNA EL ID DE ORDEN ACTUAL
    private String R_ID_ORDEN()
    {
      clearCacheDB();
     
      String cod = null;
     
      cod="ORDEN-"+UltimoID.UltimoID();
      return cod;
    }
    
    //RETORNA NUMERO DE ORDEN
    private String R_NUMERO_ORDEN()
    {
      clearCacheDB();
      return ""+UltimoID.UltimoNU();
    }
    
    private void Nuevo()
    {
        txtidorden.setText(R_ID_ORDEN());
        txtnumeroorden.setText(R_NUMERO_ORDEN());        
    }
    
    private void LegacyClearFRM()
    {
        //BLOQUE SEGMENTADO 1
        txtidorden.setText("");
        txtnumeroorden.setText("");
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
        //Bloque Segmentado 5 : Informacion Cliente
        txtcodCliente.setText("");
        txtnombrecliente.setText("");
        JTAdetcliente.setText("");
        //BLOQUE Segmentado 6 : Contenido de Orden
        txtdireccion.setText("");
        telefono = "";
        celular = "";
        CBconsultacontOrden.setSelectedIndex(0);
    }
    
    //Borra y limpia texto
    private void clearFRM()
    {
        //BLOQUE SEGMENTADO 1
        txtidorden.setText("");
        txtnumeroorden.setText("");
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
        txtdireccion.setText("");
        telefono = "";
        celular = "";
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
        txtdescuentomanual.setEditable(cond);
        txtdescuentocodigo.setEditable(cond);
        //BLOQUE SEGMENTADO 3 : Informacion Vendedor
        txtcodvendCrystal.setEditable(cond);
        //BLOQUE Segmentado 6 : Contenido de Orden
        CBconsultacontOrden.setEnabled(cond);
        txtdireccion.setEditable(cond);
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
    }
     
    private void enadisa_bloque3_botones(boolean consult_clasico, boolean quitar_clasico, 
            boolean consult_asoc)
    {
        btnconsultarVendedor.setEnabled(consult_clasico);
        btnquitarVendedor.setEnabled(quitar_clasico);
        btnconsultarAsociado.setEnabled(consult_asoc);
    }
      
    private void enadisa_bloque5_botones(boolean buscar)
    {
        btnBuscarcliente.setEnabled(buscar);
        btnAgregarCliente.setEnabled(buscar);
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
    }
    
    private void enadisa_bloque_descuento(boolean cond)
    {
        txtdescuentomanual.setEnabled(cond);
        txtdescuentocodigo.setEnabled(cond);
        btnconsultarDescuentocodigo.setEnabled(cond);
        btnQuitarDescuentoCodigo.setEnabled(cond);
    }
    
    private void reiniciarColors()
    {
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
        //BLOQUE SEGMENTADO 4 : JTA IA
        JTAIA.setBackground(ColorInicial);
        //Bloque Segmentado 5 : Informacion Cliente
        JTAdetcliente.setBackground(ColorInicial);
        txtdireccion.setBackground(ColorInicial);
    }
    
    private void CheckBox_Action(String action)
    {
        if(action.equals("ANT"))
        {
            clearFRM();
            RDBNatural.setEnabled(true);
            RDBJuridica.setEnabled(true);
            JCHAntigua.setSelected(true);
            enadisa_bloque1_botones(true, false, false, false, false, false, false, true);
            txtdescuentomanual.setText("0");
            txtcodvendCrystal.setText("-");
            txtdescuentocodigo.setText("-");
        }
        if(action.equals("TTRB"))
        {
            clearFRM();
            RDBNatural.setEnabled(true);
            RDBJuridica.setEnabled(true);
            JCHTicketTrabajo.setSelected(true);
            enadisa_bloque1_botones(true, false, false, true, false, false, false, true);
            txtdescuentomanual.setText("0");
            txtcodvendCrystal.setText("-");
            txtdescuentocodigo.setText("-");
        }
    }
    
    private void clearCacheDB()
    {
        try 
        {
            P_orden.Clear();
            P_orden.llenarDatos();
            //CARGARDATOS
            
        } catch (Exception e) 
            {
                lc.write("Error al intentar borrar la cache de la DB", "JIFGenerarOrdenServicio metodo clearcacheDB linea 172", e);
            }
    }
    
    private void cancelar()
    {

        try 
        {
            clearFRM();
            editFRM(false);
            //BLOQUE 1:
            enadisa_bloque1_botones(false, false, false, true, false, false, false, true);
            //BLOQUE 2:
            enadisa_bloque2_botones(false, false, false, false);
            //BLOQUE 3:
            enadisa_bloque3_botones(false, false, false);
            //BLOQUE 5:
            enadisa_bloque5_botones(false);
            //BLOQUE 6:
            enadisa_bloque6_botones(false, false, false, false, false, false, false);
            btnnuevo.setText("Nuevo");
            btnmodificar.setText("Modificar");
            clearCacheDB();
            vaciado_memoria();
            modi_lock="";
                    
        } catch (Exception e) 
           {
               lc.write("No se pudo cancelar la orden de servicio", "JIFGenerarOrdenServicio", e);
           }
    }
    
    private void vaciado_memoria()
    {
        areas.clear();
        servicios.clear();
        operarios.clear();
        productos.clear();
        implementos.clear();
        modo_orden="";
        telefono="";
        celular="";
        areaM2="";
        areaM3="";
    }
    
    public String modo_orden = "";
    public static String telefono="",celular="";
    public static String areaM2="", areaM3="";
    
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
        btnconsultarAsociado = new javax.swing.JButton();
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
        btnAgregarCliente = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        txtdireccion = new javax.swing.JTextField();
        lblnomcli1 = new javax.swing.JLabel();

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
        JTAAnuncio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        JTAAnuncio.setForeground(new java.awt.Color(0, 102, 153));
        JTAAnuncio.setRows(2);
        jScrollPane1.setViewportView(JTAAnuncio);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        JTAIA.setEditable(false);
        JTAIA.setBackground(new java.awt.Color(204, 204, 204));
        JTAIA.setColumns(20);
        JTAIA.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
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
        txtcosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcostoKeyTyped(evt);
            }
        });

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

        txtdescuentomanual.setBackground(new java.awt.Color(204, 204, 204));
        txtdescuentomanual.setText("0");
        txtdescuentomanual.setEnabled(false);
        txtdescuentomanual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdescuentomanualKeyTyped(evt);
            }
        });

        txtdescuentocodigo.setBackground(new java.awt.Color(204, 204, 204));
        txtdescuentocodigo.setText("-");
        txtdescuentocodigo.setEnabled(false);

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
        btnconsultarVendedor.setText("Buscar Vendedor");
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
        txtcodvendCrystal.setText("-");

        lblusuario4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblusuario4.setText("Asociado.");

        btnconsultarAsociado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/search-icon.png"))); // NOI18N
        btnconsultarAsociado.setText("Consultar Asociado");
        btnconsultarAsociado.setContentAreaFilled(false);
        btnconsultarAsociado.setEnabled(false);
        btnconsultarAsociado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconsultarAsociadoActionPerformed(evt);
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
        txtcodCliente.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        txtnombrecliente.setEditable(false);
        txtnombrecliente.setBackground(new java.awt.Color(204, 255, 255));
        txtnombrecliente.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        lblnomcli.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblnomcli.setText("Nombres:");

        lblusuario5.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblusuario5.setText("Detalle.");

        JTAdetcliente.setEditable(false);
        JTAdetcliente.setBackground(new java.awt.Color(204, 204, 204));
        JTAdetcliente.setColumns(20);
        JTAdetcliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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
        CBconsultacontOrden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AREAS", "SERVICIOS", "OPERARIOS", "PRODUCTOS", "IMPLEMENTOS" }));
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
        JCHTicketTrabajo.setEnabled(false);
        JCHTicketTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCHTicketTrabajoActionPerformed(evt);
            }
        });

        lblusuario8.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblusuario8.setText("Información Cliente.");

        btnAgregarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Client1_add24.png"))); // NOI18N
        btnAgregarCliente.setText("Agregar");
        btnAgregarCliente.setContentAreaFilled(false);
        btnAgregarCliente.setEnabled(false);
        btnAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarClienteActionPerformed(evt);
            }
        });

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        txtdireccion.setEditable(false);
        txtdireccion.setBackground(new java.awt.Color(204, 204, 204));
        txtdireccion.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        lblnomcli1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblnomcli1.setText("Dirección:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addComponent(lblusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel4)
                        .addGap(117, 117, 117)
                        .addComponent(jLabel5)
                        .addGap(10, 10, 10)
                        .addComponent(lbldate, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(4, 4, 4)
                                        .addComponent(txtidorden, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(4, 4, 4)
                                        .addComponent(txtnumeroorden, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(65, 65, 65)
                                        .addComponent(jLabel9)
                                        .addGap(4, 4, 4)
                                        .addComponent(cbestado, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel11)
                                .addGap(0, 0, 0)
                                .addComponent(JCHAntigua)
                                .addGap(2, 2, 2)
                                .addComponent(JCHTicketTrabajo))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel10)
                                .addGap(0, 0, 0)
                                .addComponent(RDBNatural)
                                .addGap(2, 2, 2)
                                .addComponent(RDBJuridica))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnnuevo)
                                .addGap(10, 10, 10)
                                .addComponent(btnmodificar)
                                .addGap(10, 10, 10)
                                .addComponent(btncancelar)
                                .addGap(10, 10, 10)
                                .addComponent(btnbuscar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btncopiar)
                                .addGap(10, 10, 10)
                                .addComponent(btnPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblusuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(4, 4, 4)
                                .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(btnfecha)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel14)
                                .addGap(4, 4, 4)
                                .addComponent(txtgiro, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel13)
                                .addGap(4, 4, 4)
                                .addComponent(txthora, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(btnhora, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel15)
                                .addGap(4, 4, 4)
                                .addComponent(txtdocumentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(2, 2, 2)
                                .addComponent(txtcosto, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel17)
                                .addGap(4, 4, 4)
                                .addComponent(txtcostofinal, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(4, 4, 4)
                                .addComponent(txtdescuentomanual, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel20))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(4, 4, 4)
                                .addComponent(txtdescuentocodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(btnconsultarDescuentocodigo)
                                .addGap(6, 6, 6)
                                .addComponent(btnQuitarDescuentoCodigo))
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblusuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblusuario3)
                                .addGap(18, 18, 18)
                                .addComponent(btnconsultarVendedor)
                                .addGap(90, 90, 90)
                                .addComponent(jLabel22)
                                .addGap(4, 4, 4)
                                .addComponent(txtidVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(4, 4, 4)
                                .addComponent(txtcodvendCrystal, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel23)
                                .addGap(4, 4, 4)
                                .addComponent(txtnombrevendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblusuario4)
                                .addGap(4, 4, 4)
                                .addComponent(btnconsultarAsociado)
                                .addGap(172, 172, 172)
                                .addComponent(btnquitarVendedor))
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblusuario8, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(jLabel24)
                                    .addGap(4, 4, 4)
                                    .addComponent(txtcodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4)
                                    .addComponent(lblnomcli)
                                    .addGap(4, 4, 4)
                                    .addComponent(txtnombrecliente, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(lblusuario5)
                                    .addGap(4, 4, 4)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(btnBuscarcliente)
                                    .addGap(10, 10, 10)
                                    .addComponent(btnAgregarCliente))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(lblusuario6)
                                    .addGap(130, 130, 130)
                                    .addComponent(lblusuario7))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(btnAgregarAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(btnAgregarServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(CBconsultacontOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(btnAgregarOperarios)
                                    .addGap(10, 10, 10)
                                    .addComponent(btnAgregarProductos)
                                    .addGap(6, 6, 6)
                                    .addComponent(btnconsultarcontenidoOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(51, 51, 51)
                                    .addComponent(btnAgregarImplementos)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblnomcli1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(lblusuario)
                            .addComponent(jLabel5)
                            .addComponent(lbldate))))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel7))
                                    .addComponent(txtidorden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel8))
                                    .addComponent(txtnumeroorden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel9))
                                    .addComponent(cbestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel11))
                            .addComponent(JCHAntigua)
                            .addComponent(JCHTicketTrabajo))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel10))
                            .addComponent(RDBNatural)
                            .addComponent(RDBJuridica))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnnuevo)
                            .addComponent(btnmodificar)
                            .addComponent(btncancelar)
                            .addComponent(btnbuscar))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btncopiar)
                            .addComponent(btnPDF)
                            .addComponent(btnimprimir)
                            .addComponent(btnsalir))
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(lblusuario1)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel12))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnfecha)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel14))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtgiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel13))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txthora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnhora)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel15))
                            .addComponent(txtdocumentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcostofinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17))))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtdescuentomanual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel20))))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel19))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtdescuentocodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnconsultarDescuentocodigo)
                            .addComponent(btnQuitarDescuentoCodigo))
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(lblusuario2)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(lblusuario3))
                            .addComponent(btnconsultarVendedor)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel22))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtidVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcodvendCrystal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnombrevendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel23))))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(lblusuario4))
                            .addComponent(btnconsultarAsociado)
                            .addComponent(btnquitarVendedor))
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(lblusuario8)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnombrecliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(lblnomcli))))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblusuario5)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(lblnomcli1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscarcliente)
                            .addComponent(btnAgregarCliente))
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblusuario6)
                            .addComponent(lblusuario7))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregarAreas)
                            .addComponent(btnAgregarServicios)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(CBconsultacontOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregarOperarios)
                            .addComponent(btnAgregarProductos)
                            .addComponent(btnconsultarcontenidoOrden))
                        .addGap(6, 6, 6)
                        .addComponent(btnAgregarImplementos))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(370, 370, 370)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
           
        if(btnnuevo.getText().equals("Nuevo"))
        {

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
            //BLOQUE 1:
            enadisa_bloque1_botones(true, false, true, false, false, false, false, true);
            //BLOQUE 2:
            enadisa_bloque2_botones(true, true, true, true);
            //BLOQUE 3:
            enadisa_bloque3_botones(true, true, true);
            //BLOQUE 5:
            enadisa_bloque5_botones(true);
            //BLOQUE 6:
            enadisa_bloque6_botones(true, true, true, true, true, true, true);

            evn.write(lblusuario.getText(), "Hizo click en el botón 'Nuevo' Orden de Servicio "+modo_orden, "JIFGenerarOrdenServicio", "Botón 'Nuevo' presionado");
            //FIN INICIO CABECERAS IMPORTANTES
            Nuevo();
            btnnuevo.setText("Insertar");

            RDBJuridica.setEnabled(false);
            RDBNatural.setEnabled(false);

        }
        else
           {
               
             if(modi_lock.equals("lock")) 
             {
                 sysau.E_CRITICAL_ERROR();
                 JOptionPane.showMessageDialog(jf, "Presione el botón 'Área' para proceder con la inserción", "Presione botón 'Área'", JOptionPane.INFORMATION_MESSAGE);
                 sysau.S_STOP();
             }
             else
             {
                 
             
            //LOGICA DE VERIFICACION
            /*
                  esta logica de verificacion sera LS.
                  estara centrada a conectar y verificar todas las partes
                  dentro de este formulario.
                  la verificacion se hara a cada bloque a la vez
            */

            if(txtgiro.getText().trim().equals(""))
            {
                sysau.E_ERROR();
                txtgiro.setBackground(Color.RED);
                JOptionPane.showMessageDialog(jf, "Es obligatorio el uso de 'Giro' para la orden", "Falta giro de la orden", JOptionPane.ERROR_MESSAGE);
                sysau.S_STOP();
            }
            else
              if(txthora.getText().trim().equals(""))
              {
                sysau.E_ERROR();
                txthora.setBackground(Color.RED);
                JOptionPane.showMessageDialog(jf, "Establezca una hora", "No se ingreso hora", JOptionPane.ERROR_MESSAGE);
                sysau.S_STOP();
              }
            else
              if(txtfecha.getText().trim().equals(""))
              {
                sysau.E_ERROR();
                txtfecha.setBackground(Color.RED);
                JOptionPane.showMessageDialog(jf, "Establezca una fecha", "No se ingreso fecha", JOptionPane.ERROR_MESSAGE);
                sysau.S_STOP();
              }
            else
              if(txtidVendedor.getText().trim().equals(""))
              {
                sysau.E_ERROR();
                txtidVendedor.setBackground(Color.RED);
                JOptionPane.showMessageDialog(jf, "Debe seleccionar un vendedor", "Falta Vendedor", JOptionPane.ERROR_MESSAGE);
                sysau.S_STOP();
              }
            else
              if(txtcodCliente.getText().trim().equals(""))
              {
                sysau.E_ERROR();
                txtcodCliente.setBackground(Color.RED);
                JOptionPane.showMessageDialog(jf, "Debe seleccionar un Cliente", "Falta Cliente", JOptionPane.ERROR_MESSAGE);
                sysau.S_STOP();
              }
            else
              if(areas.isEmpty())
              {
                sysau.E_ERROR();
                JTC.clear(JTAIA);
                JTC.cabecera(JTAIA);
                JTC.msj(JTAIA, "No se ha encontrado áreas generadas");
                ShakingComponent ar = new ShakingComponent(JTAIA);
                ar.startShake();
              }
            else
              if(servicios.isEmpty())
              {
                sysau.E_ERROR();
                JTC.clear(JTAIA);
                JTC.cabecera(JTAIA);
                JTC.msj(JTAIA, "No se ha encontrado servicios importados");
                ShakingComponent ar = new ShakingComponent(JTAIA);
                ar.startShake();
              }
            else
              if(operarios.isEmpty())
              {
                sysau.E_ERROR();
                JTC.clear(JTAIA);
                JTC.cabecera(JTAIA);
                JTC.msj(JTAIA, "No se ha encontrado operarios importados");
                ShakingComponent ar = new ShakingComponent(JTAIA);
                ar.startShake();
              }
            else
              if(productos.isEmpty())
              {
                sysau.E_ERROR();
                JTC.clear(JTAIA);
                JTC.cabecera(JTAIA);
                JTC.msj(JTAIA, "No se ha encontrado productos importados");
                ShakingComponent ar = new ShakingComponent(JTAIA);
                ar.startShake();
              }
            else
              if(implementos.isEmpty())
              {
                sysau.E_ERROR();
                JTC.clear(JTAIA);
                JTC.cabecera(JTAIA);
                JTC.msj(JTAIA, "No se ha encontrado implementos importados");
                ShakingComponent ar = new ShakingComponent(JTAIA);
                ar.startShake();
              }
            else
              {
              if(txtcosto.getText().trim().equals(""))
              {
                sysau.E_ERROR();
                txtcosto.setBackground(Color.YELLOW);
                txtcosto.setText("0");
                txtcostofinal.setText("0");
                sysau.S_STOP();
              }
              if(txtdocumentacion.getText().trim().equals(""))
              {
                txtdocumentacion.setText("-");
              }
              
               //OBTENCION DE DATOS
               String ID_ORDEN = txtidorden.getText();
               String numero_orden = txtnumeroorden.getText();
               String id_cli = txtcodCliente.getText();
               String fecha = txtfecha.getText();
               String hora = txthora.getText();
               String direccion = txtdireccion.getText().toUpperCase();
               String giro = txtgiro.getText().toUpperCase();
               String desc = txtdocumentacion.getText().toUpperCase();
               String id_vend = txtidVendedor.getText().toUpperCase();
               String costo = txtcostofinal.getText();
               String estado = cbestado.getSelectedItem().toString();

               //INSERTAR ORDEN BASE
               P_orden.Insertar_orden(ID_ORDEN, numero_orden, id_cli, fecha, hora, direccion, giro, telefono, celular, desc,id_vend, costo, estado);

               int menor=9999; 
               int mayor=0; 

               int[] arreglo=new int[5]; 
               String IDPRO="",IDMAQ="",IDPERO="",IDSERV="",NOTASERV="",IDARE="",NOMARE="",ARETRAB="",NUMAMBI="",FORM="";

               //CREACION Y ASIGNACION DE ALMACEN DE ARRAYS        
               {arreglo[0] = implementos.size();}
               {arreglo[1] = productos.size();}
               {arreglo[2] = operarios.size();}
               {arreglo[3] = servicios.size();}
               {arreglo[4] = areas.size();}

               for(int i=0;i<5;i++)
               { 
                 if(menor>arreglo[i])
                  { 
                    menor=arreglo[i]; 
                  } 
                 if(mayor<arreglo[i])
                  { 
                    mayor=arreglo[i]; 
                  } 
               }

               //System.out.println("##########################################\n"+"I = "+i+"\n"+"MAYOR : "+mayor);
               //System.out.println("ARREGLO IMPLMENTOS : "+arreglo[0]);

               for(int j=1; j<=mayor; j++)
               {
                   //IMPLEMENTOS
                   if(arreglo[0] ==1 )
                   {
                        IDMAQ=implementos.get(0).getIdmaq();
                   }else
                     {
                        if(arreglo[0] >j)
                           {
                               IDMAQ=implementos.get(j-1).getIdmaq();
                           }else
                               {
                                 IDMAQ=implementos.get(implementos.size()-1).getIdmaq();
                               }
                    }

                   //PRODUCTOS
                   if(arreglo[1] == 1)    
                   {
                       IDPRO = productos.get(0).getIdPro();
                   }else
                       {
                           if(arreglo[1] > j)
                           {
                               IDPRO = productos.get(j-1).getIdPro();
                           }else
                               {
                                   IDPRO = productos.get(productos.size()-1).getIdPro();
                               }
                       }

                   //PERSONAL OPERARIO
                   if(arreglo[2] == 1)
                   {
                       IDPERO = operarios.get(0).getIdope();
                   }else
                       {
                           if(arreglo[2] > j)
                           {
                               IDPERO = operarios.get(j-1).getIdope();
                           }else
                               {
                                   IDPERO = operarios.get(operarios.size()-1).getIdope();
                               }
                       }

                   //SERVICIOS
                   if(arreglo[3] == 1)
                   {
                       IDSERV = servicios.get(0).getIdserv();
                       NOTASERV = servicios.get(0).getNotaserv();
                   }else
                       {
                           if(arreglo[3] > j)
                           {
                               IDSERV = servicios.get(j-1).getIdserv();
                               NOTASERV = servicios.get(j-1).getNotaserv();
                           }else
                               {
                                   IDSERV = servicios.get(servicios.size()-1).getIdserv();
                                   NOTASERV = servicios.get(servicios.size()-1).getNotaserv();
                               }
                       }

                   //AREAS
                   if(arreglo[4] == 1)
                   {
                       IDARE = areas.get(0).getIdarea();
                       NOMARE = areas.get(0).getNombreArea();
                       ARETRAB = areas.get(0).getAreaTrabajar();
                       NUMAMBI = areas.get(0).getNumeroAmbientes();
                       FORM = areas.get(0).getFormato();
                   }else
                       {
                           if(arreglo[4] > j)
                           {
                               IDARE = areas.get(j-1).getIdarea();
                               NOMARE = areas.get(j-1).getNombreArea();
                               ARETRAB = areas.get(j-1).getAreaTrabajar();
                               NUMAMBI = areas.get(j-1).getNumeroAmbientes();
                               FORM = areas.get(j-1).getFormato();
                           }else
                               {
                                   IDARE = areas.get(areas.size()-1).getIdarea();
                                   NOMARE = areas.get(areas.size()-1).getNombreArea();
                                   ARETRAB = areas.get(areas.size()-1).getAreaTrabajar();
                                   NUMAMBI = areas.get(areas.size()-1).getNumeroAmbientes();
                                   FORM = areas.get(areas.size()-1).getFormato();
                               }

                       }

                   P_orden.InsertarDETOrden(ID_ORDEN, (j), IDPRO, IDMAQ, IDPERO, IDSERV, NOTASERV,IDARE, NOMARE, ARETRAB, NUMAMBI, areaM2,areaM3,FORM);
                   //AQUI SE AGREGA LA DESCIP
                   //System.out.println("--------------------------\n"+ "DET = "+(j)+"\n"+IDPRO+"\nID MAQ "+ IDMAQ+"\nID PER "+ IDPERO+"\nID SERV "+ IDSERV +"\nNOT "+ NOTASERV+"\nID ARE "+IDARE+"\nNOM AR: "+ NOMARE+"\nID ARE TRAB "+ ARETRAB +"\n NUM AMB "+ NUMAMBI +"\nM2 "+ areaM2+"\nM3 "+areaM3+"\nFORMATO "+FORM);
               }

               rslt.write(lblusuario.getText(), "JIFGenerarOrdenServicio", "INSERSIÓN", "Se ha insertado la orden N° "+numero_orden+"\n del Cliente : "+txtnombrecliente.getText());
               
               JTC.clear(JTAIA);
               JTC.cabecera(JTAIA);
               JTC.msj(JTAIA, "Se ha ingresado correctamente:\n");
               JTC.msj(JTAIA,  "\n"+arreglo[0]+" Maquinas.");
               JTC.msj(JTAIA,  "\n"+arreglo[1]+" Productos.");
               JTC.msj(JTAIA,  "\n"+arreglo[2]+" Personas operarias.");
               JTC.msj(JTAIA,  "\n"+arreglo[4]+" Áreas.");
               JTC.msj(JTAIA,  "\n"+arreglo[3]+" Trabajos.");
               JTC.msj(JTAIA,  "\nAREA TOTAL A TRABAJAR : "+areaM2+" M2"+"------------"+areaM3+" M3");
               JTC.msj(JTAIA,  "\n\nCLIENTE : "+txtnombrecliente.getText().toUpperCase());
               JTC.msj(JTAIA,  "\nGIRO DEL LUGAR : "+giro);
               JTC.msj(JTAIA,  "\nCOSTO POR EL SERVICIO : "+costo+" SOLES");
               JTC.msj(JTAIA,  "\n\nVENDEDOR : "+txtnombrevendedor.getText().toUpperCase());

               LegacyClearFRM();                                                                                
               editFRM(false);
               //BLOQUE 1:
               enadisa_bloque1_botones(false, false, false, true, false, true, true, true);
               //BLOQUE 2:
               enadisa_bloque2_botones(false, false, false, false);
               //BLOQUE 3:
               enadisa_bloque3_botones(false, false, false);
               //BLOQUE 5:
               enadisa_bloque5_botones(false);
               //BLOQUE 6:
               enadisa_bloque6_botones(false, false, false, false, false, false, false);
               btnnuevo.setText("Nuevo");
               modi_lock = "";
               RP_ORDEN_SERV b = new RP_ORDEN_SERV();//natural 342 -- juridico 194
               b.speculation(Tipo_orden, ID_ORDEN);
               clearCacheDB();
               vaciado_memoria();
               reiniciarColors();
               ultima_orden();
               
              }
           }
         }
       }catch (Exception e) 
           {
              lc.write("Ha ocurrido un error al intentar insertar una nueva orden","JIFGenerarOrdenServicio", e);
           }
    
    }//GEN-LAST:event_btnnuevoActionPerformed

    String modi_lock;
    
    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        
   String Tipo_orden = mw.getSelectedButtonText(TipoOrden);
   
   try 
      {
       //INICIO CABECERAS IMPORTANTES
       //Ejecutado al inicio de todo
       SYSAUDIOCON sysau = new SYSAUDIOCON();
       JFrame jf=new JFrame();
       jf.setAlwaysOnTop(true);
       clearCacheDB();

       if(btnmodificar.getText().equals("Modificar"))
       {

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
           //BLOQUE 1:
           enadisa_bloque1_botones(false, true, true, false, false, false, false, true);
           //BLOQUE 2:
           enadisa_bloque2_botones(true, true, true, true);
           //BLOQUE 3:
           enadisa_bloque3_botones(true, true, true);
           //BLOQUE 5:
           enadisa_bloque5_botones(true);
           //BLOQUE 6:
           enadisa_bloque6_botones(true, true, true, true, true, true, true);

           evn.write(lblusuario.getText(), "Hizo click en el botón 'Modificar' Orden de Servicio "+modo_orden, "JIFGenerarOrdenServicio", "Botón 'Modificar' presionado");
           //FIN INICIO CABECERAS IMPORTANTES
           btnmodificar.setText("Actualizar");

           RDBJuridica.setEnabled(false);
           RDBNatural.setEnabled(false);
           modi_lock = "lock";
           
       }
       else
          {
             if(modi_lock.equals("lock")) 
             {
                 sysau.E_CRITICAL_ERROR();
                 JOptionPane.showMessageDialog(jf, "Presione el botón 'Área' para proceder con la modificacion", "Presione botón 'Área'", JOptionPane.INFORMATION_MESSAGE);
                 sysau.S_STOP();
             }
             else
             {
            //LOGICA DE VERIFICACION
            /*
                  esta logica de verificacion sera LS.
                  estara centrada a conectar y verificar todas las partes
                  dentro de este formulario.
                  la verificacion se hara a cada bloque a la vez
            */

            if(txtgiro.getText().trim().equals(""))
            {
                sysau.E_ERROR();
                txtgiro.setBackground(Color.RED);
                JOptionPane.showMessageDialog(jf, "Es obligatorio el uso de 'Giro' para la orden", "Falta giro de la orden", JOptionPane.ERROR_MESSAGE);
                sysau.S_STOP();
            }
            else
              if(txthora.getText().trim().equals(""))
              {
                sysau.E_ERROR();
                txthora.setBackground(Color.RED);
                JOptionPane.showMessageDialog(jf, "Establezca una hora", "No se ingreso hora", JOptionPane.ERROR_MESSAGE);
                sysau.S_STOP();
              }
            else
              if(txtfecha.getText().trim().equals(""))
              {
                sysau.E_ERROR();
                txtfecha.setBackground(Color.RED);
                JOptionPane.showMessageDialog(jf, "Establezca una fecha", "No se ingreso fecha", JOptionPane.ERROR_MESSAGE);
                sysau.S_STOP();
              }
            else
              if(txtidVendedor.getText().trim().equals(""))
              {
                sysau.E_ERROR();
                txtidVendedor.setBackground(Color.RED);
                JOptionPane.showMessageDialog(jf, "Debe seleccionar un vendedor", "Falta Vendedor", JOptionPane.ERROR_MESSAGE);
                sysau.S_STOP();
              }
            else
              if(txtcodCliente.getText().trim().equals(""))
              {
                sysau.E_ERROR();
                txtcodCliente.setBackground(Color.RED);
                JOptionPane.showMessageDialog(jf, "Debe seleccionar un Cliente", "Falta Cliente", JOptionPane.ERROR_MESSAGE);
                sysau.S_STOP();
              }
            else
              if(areas.isEmpty())
              {
                sysau.E_ERROR();
                JTC.clear(JTAIA);
                JTC.cabecera(JTAIA);
                JTC.msj(JTAIA, "No se ha encontrado áreas generadas");
                ShakingComponent ar = new ShakingComponent(JTAIA);
                ar.startShake();
              }
            else
              if(servicios.isEmpty())
              {
                sysau.E_ERROR();
                JTC.clear(JTAIA);
                JTC.cabecera(JTAIA);
                JTC.msj(JTAIA, "No se ha encontrado servicios importados");
                ShakingComponent ar = new ShakingComponent(JTAIA);
                ar.startShake();
              }
            else
              if(operarios.isEmpty())
              {
                sysau.E_ERROR();
                JTC.clear(JTAIA);
                JTC.cabecera(JTAIA);
                JTC.msj(JTAIA, "No se ha encontrado operarios importados");
                ShakingComponent ar = new ShakingComponent(JTAIA);
                ar.startShake();
              }
            else
              if(productos.isEmpty())
              {
                sysau.E_ERROR();
                JTC.clear(JTAIA);
                JTC.cabecera(JTAIA);
                JTC.msj(JTAIA, "No se ha encontrado productos importados");
                ShakingComponent ar = new ShakingComponent(JTAIA);
                ar.startShake();
              }
            else
              if(implementos.isEmpty())
              {
                sysau.E_ERROR();
                JTC.clear(JTAIA);
                JTC.cabecera(JTAIA);
                JTC.msj(JTAIA, "No se ha encontrado implementos importados");
                ShakingComponent ar = new ShakingComponent(JTAIA);
                ar.startShake();
              }
            else
              {
              if(txtcosto.getText().trim().equals(""))
              {
                sysau.E_ERROR();
                txtcosto.setBackground(Color.YELLOW);
                txtcosto.setText("0");
                txtcostofinal.setText("0");
                sysau.S_STOP();
              }
              if(txtdocumentacion.getText().trim().equals(""))
              {
                txtdocumentacion.setText("-");
              }

               //OBTENCION DE DATOS
               String ID_ORDEN = txtidorden.getText();
               String numero_orden = txtnumeroorden.getText();
               String id_cli = txtcodCliente.getText();
               String fecha = txtfecha.getText();
               String hora = txthora.getText();
               String direccion = txtdireccion.getText().toUpperCase();
               String giro = txtgiro.getText().toUpperCase();
               String desc = txtdocumentacion.getText().toUpperCase();
               String id_vend = txtidVendedor.getText().toUpperCase();
               String costo = txtcostofinal.getText();
               String estado = cbestado.getSelectedItem().toString();

               //INSERTAR ORDEN BASE
               P_orden.Actualizar_orden(ID_ORDEN, numero_orden, id_cli, fecha, hora, direccion, giro, telefono, celular, desc,id_vend, costo, estado);
               P_orden.ActualizarDETOrden(ID_ORDEN);

               int menor=9999; 
               int mayor=0; 

               int[] arreglo=new int[5]; 
               String IDPRO="",IDMAQ="",IDPERO="",IDSERV="",NOTASERV="",IDARE="",NOMARE="",ARETRAB="",NUMAMBI="",FORM="";

               //CREACION Y ASIGNACION DE ALMACEN DE ARRAYS        
               {arreglo[0] = implementos.size();}
               {arreglo[1] = productos.size();}
               {arreglo[2] = operarios.size();}
               {arreglo[3] = servicios.size();}
               {arreglo[4] = areas.size();}

               for(int i=0;i<5;i++)
               { 
                 if(menor>arreglo[i])
                  { 
                    menor=arreglo[i]; 
                  } 
                 if(mayor<arreglo[i])
                  { 
                    mayor=arreglo[i]; 
                  } 
               }

               //System.out.println("##########################################\n"+"I = "+i+"\n"+"MAYOR : "+mayor);
               //System.out.println("ARREGLO IMPLMENTOS : "+arreglo[0]);

               for(int j=1; j<=mayor; j++)
               {
                   //IMPLEMENTOS
                   if(arreglo[0] ==1 )
                   {
                        IDMAQ=implementos.get(0).getIdmaq();
                   }else
                     {
                        if(arreglo[0] >j)
                           {
                               IDMAQ=implementos.get(j-1).getIdmaq();
                           }else
                               {
                                 IDMAQ=implementos.get(implementos.size()-1).getIdmaq();
                               }
                    }

                   //PRODUCTOS
                   if(arreglo[1] == 1)    
                   {
                       IDPRO = productos.get(0).getIdPro();
                   }else
                       {
                           if(arreglo[1] > j)
                           {
                               IDPRO = productos.get(j-1).getIdPro();
                           }else
                               {
                                   IDPRO = productos.get(productos.size()-1).getIdPro();
                               }
                       }

                   //PERSONAL OPERARIO
                   if(arreglo[2] == 1)
                   {
                       IDPERO = operarios.get(0).getIdope();
                   }else
                       {
                           if(arreglo[2] > j)
                           {
                               IDPERO = operarios.get(j-1).getIdope();
                           }else
                               {
                                   IDPERO = operarios.get(operarios.size()-1).getIdope();
                               }
                       }

                   //SERVICIOS
                   if(arreglo[3] == 1)
                   {
                       IDSERV = servicios.get(0).getIdserv();
                       NOTASERV = servicios.get(0).getNotaserv();
                   }else
                       {
                           if(arreglo[3] > j)
                           {
                               IDSERV = servicios.get(j-1).getIdserv();
                               NOTASERV = servicios.get(j-1).getNotaserv();
                           }else
                               {
                                   IDSERV = servicios.get(servicios.size()-1).getIdserv();
                                   NOTASERV = servicios.get(servicios.size()-1).getNotaserv();
                               }
                       }

                   //AREAS
                   if(arreglo[4] == 1)
                   {
                       IDARE = areas.get(0).getIdarea();
                       NOMARE = areas.get(0).getNombreArea();
                       ARETRAB = areas.get(0).getAreaTrabajar();
                       NUMAMBI = areas.get(0).getNumeroAmbientes();
                       FORM = areas.get(0).getFormato();
                   }else
                       {
                           if(arreglo[4] > j)
                           {
                               IDARE = areas.get(j-1).getIdarea();
                               NOMARE = areas.get(j-1).getNombreArea();
                               ARETRAB = areas.get(j-1).getAreaTrabajar();
                               NUMAMBI = areas.get(j-1).getNumeroAmbientes();
                               FORM = areas.get(j-1).getFormato();
                           }else
                               {
                                   IDARE = areas.get(areas.size()-1).getIdarea();
                                   NOMARE = areas.get(areas.size()-1).getNombreArea();
                                   ARETRAB = areas.get(areas.size()-1).getAreaTrabajar();
                                   NUMAMBI = areas.get(areas.size()-1).getNumeroAmbientes();
                                   FORM = areas.get(areas.size()-1).getFormato();
                               }

                       }

                    P_orden.InsertarDETOrden(ID_ORDEN, (j), IDPRO, IDMAQ, IDPERO, IDSERV, NOTASERV,IDARE, NOMARE, ARETRAB, NUMAMBI, areaM2,areaM3,FORM);
                   //AQUI SE AGREGA LA DESCIP
                   //System.out.println("--------------------------\n"+ "DET = "+(j)+"\n"+IDPRO+"\nID MAQ "+ IDMAQ+"\nID PER "+ IDPERO+"\nID SERV "+ IDSERV +"\nNOT "+ NOTASERV+"\nID ARE "+IDARE+"\nNOM AR: "+ NOMARE+"\nID ARE TRAB "+ ARETRAB +"\n NUM AMB "+ NUMAMBI +"\nM2 "+ areaM2+"\nM3 "+areaM3+"\nFORMATO "+FORM);
               }

               rslt.write(lblusuario.getText(), "JIFGenerarOrdenServicio", "MODIFICACIÓN", "Se ha actualizado la orden N° "+numero_orden+"\n del Cliente : "+txtnombrecliente.getText());


               JTC.clear(JTAIA);
               JTC.cabecera(JTAIA);
               JTC.msj(JTAIA, "Se ha actualizado correctamente:\n");
               JTC.msj(JTAIA,  "\n"+arreglo[0]+" Maquinas.");
               JTC.msj(JTAIA,  "\n"+arreglo[1]+" Productos.");
               JTC.msj(JTAIA,  "\n"+arreglo[2]+" Personas operarias.");
               JTC.msj(JTAIA,  "\n"+arreglo[4]+" Áreas.");
               JTC.msj(JTAIA,  "\n"+arreglo[3]+" Trabajos.");
               JTC.msj(JTAIA,  "\nAREA TOTAL A TRABAJAR : "+areaM2+" M2"+"------------"+areaM3+" M3");
               JTC.msj(JTAIA,  "\n\nCLIENTE : "+txtnombrecliente.getText().toUpperCase());
               JTC.msj(JTAIA,  "\nGIRO DEL LUGAR : "+giro);
               JTC.msj(JTAIA,  "\nCOSTO POR EL SERVICIO : "+costo+" SOLES");
               JTC.msj(JTAIA,  "\n\nVENDEDOR : "+txtnombrevendedor.getText().toUpperCase());

               LegacyClearFRM();                                                                                
               editFRM(false);
               //BLOQUE 1:
               enadisa_bloque1_botones(false, false, false, true, false, true, true, true);
               //BLOQUE 2:
               enadisa_bloque2_botones(false, false, false, false);
               //BLOQUE 3:
               enadisa_bloque3_botones(false, false, false);
               //BLOQUE 5:
               enadisa_bloque5_botones(false);
               //BLOQUE 6:
               enadisa_bloque6_botones(false, false, false, false, false, false, false);
               btnmodificar.setText("Modificar");
               RP_ORDEN_SERV b = new RP_ORDEN_SERV();//natural 342 -- juridico 194
               b.speculation(Tipo_orden, ID_ORDEN);
               clearCacheDB();
               vaciado_memoria();
               reiniciarColors();
               ultima_orden();
               modi_lock = "";
               
              }
             }
          }
      }catch (Exception e) 
          {
             lc.write("Ha ocurrido un error al intentar modificar una nueva orden","JIFGenerarOrdenServicio", e);
          }

        
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
           
    evn.write(lblusuario.getText(), "Ha cancelado la inserción o modificación de una orden "+modo_orden, "JIFGenerarOrdenServicio", "Botón 'Cancelar' Presionado");
    cancelar();
        
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
                
    try 
    {           
      evn.write(lblusuario.getText(), "Ha salido del formulario 'JIFGenerarOrdenServicio'", "JIFMantenerClientes", "Presiono Botón 'Salir'");
      JFRPrincipal.JSMGenerarOrden.setActionCommand("CERRADO");
      this.dispose();
    } catch (Exception e) 
        {
            lc.write("No se ha podido cerrar el formulario 'JIFGenerarOrdenServicio' debido a un error inesperado", "JIFGenerarOrdenServicio", e);
        }    
        
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
  
    //Modulo de busqueda de ordenes de trabajo V0.9 
    try
    {
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true); 
        vaciado_memoria();
        clearCacheDB();
        evn.write(lblusuario.getText(), "Ha abierto el formulario de busqueda de ordenes de servicio", "JIFGenerarOrdenServicio", "Hizo click en el botón 'Buscar'");

       // display the showOptionDialog
        Object[] options = { "JURIDICOS", "NATURALES","CANCELAR"};
        int choice = JOptionPane.showOptionDialog(jf, 
            "¿Que tipo de orden desea buscar?", 
            "Tipo de Orden de Trabajo", 
            JOptionPane.YES_NO_CANCEL_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            options, 
            options[0]);

        // interpret the user's choice
        if (choice == JOptionPane.NO_OPTION)
        {
            //NATURAL
            GL_JDBuscarOrdenes busca = new GL_JDBuscarOrdenes(form, true, "Natural");
            busca.formulario="ORDEN_SERV";
            busca.setVisible(true);
            CBconsultacontOrden.setEnabled(true);
            btnconsultarcontenidoOrden.setEnabled(true);
            enadisa_bloque1_botones(false, true, true, true, true, true, true, true);
            RP_ORDEN_SERV b = new RP_ORDEN_SERV();//natural 342 -- juridico 194
            b.speculation("Natural", txtidorden.getText());
            TipoOrden.setSelected(RDBNatural.getModel(), true);
        }

        if (choice == JOptionPane.YES_OPTION)
        {
            //JURIDICA
            GL_JDBuscarOrdenes busca = new GL_JDBuscarOrdenes(form, true, "Juridica");
            busca.formulario="ORDEN_SERV";
            busca.setVisible(true);
            CBconsultacontOrden.setEnabled(true);
            btnconsultarcontenidoOrden.setEnabled(true);
            enadisa_bloque1_botones(false, true, true, true, true, true, true, true);
            RP_ORDEN_SERV b = new RP_ORDEN_SERV();//natural 342 -- juridico 194
            b.speculation("Juridica", txtidorden.getText());
            TipoOrden.setSelected(RDBJuridica.getModel(), true);
        }
        if(choice == JOptionPane.CANCEL_OPTION)
        {
            //NOTHING
        } 


//        
    } catch (Exception e) 
        {
            lc.write("Problema al intentar abrir el formulario de buscar ordenes", "JIFGenerarOrdenServicio", e);
        }
        
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btncopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncopiarActionPerformed
  
        String Tipo_orden = mw.getSelectedButtonText(TipoOrden);

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

        
        clearCacheDB();
        JTC.clear(JTAIA);
        JTC.cabecera(JTAIA);
        JTC.msj(JTAIA, "Copia de una Orden de Trabajo ."+modo_orden);
  
        editFRM(true);
        //BLOQUE 1:
        enadisa_bloque1_botones(true, false, true, false, false, false, false, true);
        //BLOQUE 2:
        enadisa_bloque2_botones(true, true, true, true);
        //BLOQUE 3:
        enadisa_bloque3_botones(true, true, true);
        //BLOQUE 5:
        enadisa_bloque5_botones(true);
        //BLOQUE 6:
        enadisa_bloque6_botones(true, true, true, true, true, true, true);

        evn.write(lblusuario.getText(), "Hizo click en el botón 'Copiar' Orden de Trabajo "+modo_orden, "JIFGenerarOrdenServicio -> Copiar", "Botón 'Copiar' presionado");
        //FIN INICIO CABECERAS IMPORTANTES
        Nuevo();
        modi_lock = "lock";
        btnnuevo.setText("Insertar");

    }//GEN-LAST:event_btncopiarActionPerformed

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed

    try 
       {
            RP_ORDEN_SERV b = new RP_ORDEN_SERV();//natural 342 -- juridico 194
            b.build("SHOW");
        } catch (Exception e) 
            {
               lc.write("Error al generar el visor", "JIFGenerarOrdenes", e);
            }
     
    }//GEN-LAST:event_btnPDFActionPerformed

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed

          
    //Modulo de impresión de ordenes de trabajo V0.9 
    try
    {
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true); 
        RP_ORDEN_SERV b = new RP_ORDEN_SERV();//natural 342 -- juridico 194
        evn.write(lblusuario.getText(), "Ha impreso una orden", "JIFGenerarOrdenServicio -> Modulo Impresión", "Hizo click en el botón 'Imprimir'");

       // display the showOptionDialog
        Object[] options = { "RAPIDA", "SELECTIVA","CANCELAR"};
        int choice = JOptionPane.showOptionDialog(jf, 
            "¿Como desea imprimir?", 
            "Tipo de Impresión", 
            JOptionPane.YES_NO_CANCEL_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            options, 
            options[0]);

        // interpret the user's choice
        if (choice == JOptionPane.NO_OPTION)
        {
            //NATURAL 2
            b.build("PRINT");
        }

        if (choice == JOptionPane.YES_OPTION)
        {
            //JURIDICA 1
            b.build("FAST_PRINT");

        }
        if(choice == JOptionPane.CANCEL_OPTION)
        {
            //NOTHING
        } 

    //        
    } catch (Exception e) 
        {
            lc.write("Problema al intentar abrir al intentar imprimir la orden ", "JIFGenerarOrdenServicio -> Modulo Impresión", e);
        }
        
    }//GEN-LAST:event_btnimprimirActionPerformed

    private void btnfechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfechaActionPerformed

    try 
    {
        evn.write(lblusuario.getText(), "Abrio el selector de fechas", "JIFGenerarOrdenServicio -> JDCalendar", "Botón 'fecha' presionado");
        GL_JDCalendar ca = new GL_JDCalendar(form, true,"D");
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
        GL_JDCalendar ca = new GL_JDCalendar(form, true,"T");
        ca.formulario="ORDEN_SERV";
        ca.setVisible(true);
        
    } catch (Exception e) 
        {
            lc.write("No se pudo abrir el selector de fechas debido a un error inesperado", "JIFGenerarOrdenServicio -> boton hora", e);
        }
    }//GEN-LAST:event_btnhoraActionPerformed

    private void btnconsultarDescuentocodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultarDescuentocodigoActionPerformed

    try 
    {
       //EJECUTADO ANTES DE TODO
       SYSAUDIOCON sysau = new SYSAUDIOCON();
       //EJECUTADO ANTES DE TODA CONDICIONAL
       JFrame jf=new JFrame();
       jf.setAlwaysOnTop(true);
       
       String cod = txtdescuentocodigo.getText().trim();
       txtdescuentomanual.setText("0");
               
       if(cod.equals(""))
       {
           txtdescuentocodigo.setBackground(Color.red);
           JTC.clear(JTAIA);
           JTC.cabecera(JTAIA);
           sysau.E_ERROR();
           JTC.msj(JTAIA, "Ingrese primero un codigo de descuento");
       }
           else
           {
           int porcentaje = P_orden.BuscarPorcentaje(cod);

           if(porcentaje==0)
           {
               txtdescuentocodigo.setBackground(Color.red);
               JTC.clear(JTAIA);
               JTC.cabecera(JTAIA);
               sysau.E_ERROR();
               JTC.msj(JTAIA, "El codigo que ingreso no se encuentra activo o no existe");
           }
           else
           {
               JTC.clear(JTAIA);
               JTC.cabecera(JTAIA);
               
               JTC.msj(JTAIA,"El codigo '"+cod+"' corresponde a un descuento \ndel "+porcentaje+"%"
                       +" Sobre el costo");
               
               ShakingComponent jtia = new ShakingComponent(JTAIA);
               jtia.startShake();
               
               sysau.E_INFORMATION();
               
               ShakingComponent c = new ShakingComponent(txtdescuentocodigo);
               c.startShake();
               
               txtdescuentocodigo.setBackground(Color.GREEN);
               Double costo = Double.parseDouble(txtcosto.getText());
               int desc = porcentaje;
               Double calcTotal = ((costo*desc)/100);
               Double total = costo-calcTotal;
               txtcostofinal.setText(""+String.format("%.2f", total));
           }
       }
    } catch (Exception e) 
        {
            lc.write("un error ha ocurrido al aplicar el descuento por codigo", "JIFGenerarOrdenServicio -> consultardescuentocodigo", e);
        }

    }//GEN-LAST:event_btnconsultarDescuentocodigoActionPerformed

    private void btnQuitarDescuentoCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarDescuentoCodigoActionPerformed

        txtdescuentocodigo.setText("-");txtdescuentomanual.setText("0");
        txtcostofinal.setText(txtcosto.getText());
        txtdescuentocodigo.setBackground(ColorInicial);
        JTC.clear(JTAIA);

    }//GEN-LAST:event_btnQuitarDescuentoCodigoActionPerformed

    private void btnconsultarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultarVendedorActionPerformed

    try 
    {
        evn.write(lblusuario.getText(), "Abrio el Buscador de Vendedor", "JIFGenerarOrdenServicio -> GL_JDBuscarVendedor", "Botón 'Consultar Vendedor' presionado");
        GL_JDBuscarVendedor BV = new GL_JDBuscarVendedor(form, true);
        BV.formulario="ORDEN_SERV";        
        BV.setVisible(true);
        
    } catch (Exception e) 
        {
            lc.write("No se pudo abrir el Buscador de Vendedor debido a un error inesperado", "JIFGenerarOrdenServicio -> boton Consultar Vendedor", e);
        }
        
    }//GEN-LAST:event_btnconsultarVendedorActionPerformed

    private void btnquitarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquitarVendedorActionPerformed

        int menor=9999; 
        int mayor=0; 

        int[] arreglo=new int[5]; 
        String IDPRO="",IDMAQ="",IDPERO="",IDSERV="",NOTASERV="",IDARE="",NOMARE="",ARETRAB="",NUMAMBI="",FORM="";

        //CREACION Y ASIGNACION DE ALMACEN DE ARRAYS        
        {arreglo[0] = implementos.size();}
        {arreglo[1] = productos.size();}
        {arreglo[2] = operarios.size();}
        {arreglo[3] = servicios.size();}
        {arreglo[4] = areas.size();}

        for(int i=0;i<5;i++)
        { 
          if(menor>arreglo[i])
           { 
             menor=arreglo[i]; 
           } 
          if(mayor<arreglo[i])
           { 
             mayor=arreglo[i]; 
           } 
        }
                  

        //System.out.println("##########################################\n"+"I = "+i+"\n"+"MAYOR : "+mayor);
        //System.out.println("ARREGLO IMPLMENTOS : "+arreglo[0]);

        for(int j=1; j<=mayor; j++)
        {
            //IMPLEMENTOS
            if(arreglo[0] ==1 )
            {
                 IDMAQ=implementos.get(0).getIdmaq();
            }else
              {
                 if(arreglo[0] >j)
                    {
                        IDMAQ=implementos.get(j-1).getIdmaq();
                    }else
                        {
                          IDMAQ=implementos.get(implementos.size()-1).getIdmaq();
                        }
             }
        

            //PRODUCTOS
            if(arreglo[1] == 1)    
            {
                IDPRO = productos.get(0).getIdPro();
            }else
                {
                    if(arreglo[1] > j)
                    {
                        IDPRO = productos.get(j-1).getIdPro();
                    }else
                        {
                            IDPRO = productos.get(productos.size()-1).getIdPro();
                        }
                }
            
            //PERSONAL OPERARIO
            if(arreglo[2] == 1)
            {
                IDPERO = operarios.get(0).getIdope();
            }else
                {
                    if(arreglo[2] > j)
                    {
                        IDPERO = operarios.get(j-1).getIdope();
                    }else
                        {
                            IDPERO = operarios.get(operarios.size()-1).getIdope();
                        }
                }
            
            //SERVICIOS
            if(arreglo[3] == 1)
            {
                IDSERV = servicios.get(0).getIdserv();
            }else
                {
                    if(arreglo[3] > j)
                    {
                        IDSERV = servicios.get(j-1).getIdserv();
                    }else
                        {
                            IDSERV = servicios.get(servicios.size()-1).getIdserv();
                        }
                }
            
            
            if(arreglo[4] == 1)
            {
                IDARE = areas.get(0).getIdarea();
                NOMARE = areas.get(0).getNombreArea();
                ARETRAB = areas.get(0).getAreaTrabajar();
                NUMAMBI = areas.get(0).getNumeroAmbientes();
                FORM = areas.get(0).getFormato();
            }else
                {
                    if(arreglo[4] > j)
                    {
                        IDARE = areas.get(j-1).getIdarea();
                        NOMARE = areas.get(j-1).getNombreArea();
                        ARETRAB = areas.get(j-1).getAreaTrabajar();
                        NUMAMBI = areas.get(j-1).getNumeroAmbientes();
                        FORM = areas.get(j-1).getFormato();
                    }else
                        {
                            IDARE = areas.get(areas.size()-1).getIdarea();
                            NOMARE = areas.get(areas.size()-1).getNombreArea();
                            ARETRAB = areas.get(areas.size()-1).getAreaTrabajar();
                            NUMAMBI = areas.get(areas.size()-1).getNumeroAmbientes();
                            FORM = areas.get(areas.size()-1).getFormato();
                        }
                    
                }

            //AQUI SE AGREGA LA DESCIP
            System.out.println("--------------------------\n"+ "DET = "+(j)+"\n"+IDPRO+"\nID MAQ "+ IDMAQ+"\nID PER "+ IDPERO+"\nID SERV "+ IDSERV +"\nNOT "+ NOTASERV+"\nID ARE "+IDARE+"\nNOM AR: "+ NOMARE+"\nID ARE TRAB "+ ARETRAB +"\n NUM AMB "+ NUMAMBI +"\nM2 "+ areaM2+"\nM3 "+areaM3+"\nFORMATO "+FORM);

        }
        

              
    }//GEN-LAST:event_btnquitarVendedorActionPerformed

    private void btnconsultarAsociadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultarAsociadoActionPerformed

        
    }//GEN-LAST:event_btnconsultarAsociadoActionPerformed

    private void btnBuscarclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarclienteActionPerformed

    try 
    {
        evn.write(lblusuario.getText(), "Abrio el Buscador de Cliente", "JIFGenerarOrdenServicio -> GL_JDBuscarCliente", "Botón 'Consultar Cliente' presionado");
        txtcodCliente.setText("");
        txtnombrecliente.setText("");
        JTAdetcliente.setText("");
        txtdireccion.setText("");
        GL_JDBuscarCliente BC = new GL_JDBuscarCliente(form, true,modo_orden);
        BC.formulario="ORDEN_SERV";
        BC.setVisible(true);
        System.out.println("NUMERO T: "+telefono+"\nNUMERO C: "+celular);
        
    } catch (Exception e) 
        {
            lc.write("No se pudo abrir el buscador de clientes debido a un error inesperado", "JIFGenerarOrdenServicio -> boton Consultar Cliente", e);
        }
    
    }//GEN-LAST:event_btnBuscarclienteActionPerformed

    private void btnAgregarAreasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarAreasActionPerformed
                
    try 
     {
         
         SGL_VIEW_AreaTrabajo agregar_areas = new SGL_VIEW_AreaTrabajo(form, true);
         agregar_areas.setVisible(true);
         modi_lock = "unlock";
         
     } catch (Exception e) 
        {
            lc.write("Ha Ocurrido un error al intentar abrir 'Agregar Areas'", "JIFGenerarOrdenServicio -> Agregar Areas", e);
        }
        
    }//GEN-LAST:event_btnAgregarAreasActionPerformed

    private void btnAgregarServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarServiciosActionPerformed
                        
    try 
     {
         SGL_VIEW_Servicios agregar_servicios = new SGL_VIEW_Servicios(form, true);
         agregar_servicios.setVisible(true);

     } catch (Exception e) 
        {
            lc.write("Ha Ocurrido un error al intentar abrir 'Agregar Servicios'", "JIFGenerarOrdenServicio -> Agregar Servicios", e);
        }
        
    }//GEN-LAST:event_btnAgregarServiciosActionPerformed

    private void btnAgregarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductosActionPerformed
                                
    try 
     {
         SGL_VIEW_Productos agregar_productos = new SGL_VIEW_Productos(form, true);
         agregar_productos.setVisible(true);

     } catch (Exception e) 
        {
            lc.write("Ha Ocurrido un error al intentar abrir 'Agregar Productos'", "JIFGenerarOrdenServicio -> Agregar Productos", e);
        }

    }//GEN-LAST:event_btnAgregarProductosActionPerformed

    private void btnAgregarOperariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarOperariosActionPerformed
                                
    try 
     {
         SGL_VIEW_Operarios agregar_operarios = new SGL_VIEW_Operarios(form, true);
         agregar_operarios.setVisible(true);

     } catch (Exception e) 
        {
            lc.write("Ha Ocurrido un error al intentar abrir 'Agregar Operarios'", "JIFGenerarOrdenServicio -> Agregar Operarios", e);
        }
        
    }//GEN-LAST:event_btnAgregarOperariosActionPerformed

    private void btnAgregarImplementosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarImplementosActionPerformed
                                        
    try 
     {
         SGL_VIEW_Implementos agregar_implementos = new SGL_VIEW_Implementos(form, true);
         agregar_implementos.setVisible(true);

     } catch (Exception e) 
        {
            lc.write("Ha Ocurrido un error al intentar abrir 'Agregar Implementos'", "JIFGenerarOrdenServicio -> Agregar Implementos", e);
        }
        
    }//GEN-LAST:event_btnAgregarImplementosActionPerformed

    private void btnconsultarcontenidoOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultarcontenidoOrdenActionPerformed
    
    SYSAUDIOCON sysau = new SYSAUDIOCON();
    sysau.E_NOTIFY();
                
    if(CBconsultacontOrden.getSelectedItem().toString().equals("AREAS"))
    {
        JTC.clear(JTAIA);
        JTC.cabecera(JTAIA);
        JTC.msj(JTAIA,"Consulta de Áreas a Trabajar.\n");    
        
        sumaAreaTotalMT2=0;
        sumaAreaTotalMT3=0;
        
        if(areas.isEmpty()){JTC.msj(JTAIA,"Aún no se ha generado ninguna área a trabajar");}
        else
            {
                for( SGL_CL_AreaTrabajo x : areas)
                {
                    JTC.msj(JTAIA, "CODIGO : "+x.getIdarea()+"\n"+"DESCRIPCIÓN : "+x.getNombreArea()+"\n"+"NÚM. AMBIENTES : "+x.getNumeroAmbientes()+"\n"+"ÁREA TRABAJAR : "+x.getAreaTrabajar()+" "+x.getFormato()+"\n"+"------------------------------------\n");
                    
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
                    JTC.msj(JTAIA,"ÁREA TOTAL : "+sumaAreaTotalMT2+" MT2"+"   ----------   "+sumaAreaTotalMT3+" MT3");
            }
    }
    
    
    if(CBconsultacontOrden.getSelectedItem().toString().equals("SERVICIOS"))
    {
        JTC.clear(JTAIA);
        JTC.cabecera(JTAIA);
        JTC.msj(JTAIA,"Consulta de Servicios.\n");    
        
        if(servicios.isEmpty()){JTC.msj(JTAIA,"Aún no escoge ningun servicio");}
        else
        {
            for( SGL_CL_Servicios x : servicios)
            {
                JTC.msj(JTAIA,"CODIGO : "+x.getIdserv()+"\n"+"NOMBRE DEL SERVICIO : "+x.getNomserv()+"\n"+"NOTA : "+x.getNotaserv()+"\n"+"------------------------------------\n");
            }
        }
    }
    
    if(CBconsultacontOrden.getSelectedItem().toString().equals("OPERARIOS"))
    {
        JTC.clear(JTAIA);
        JTC.cabecera(JTAIA);
        JTC.msj(JTAIA,"Consulta de Personal Operario.\n");    
            
        if(operarios.isEmpty()){JTC.msj(JTAIA,"Aún no escoge ningun operario");}
        else
        {
            for( SGL_CL_Operarios x : operarios)
            {
                JTC.msj(JTAIA, "CODIGO : "+x.getIdope()+"\n"+"NOMBRE : "+x.getNomope()+"\n"+"------------------------------------\n");
            }
        }
    }
    
    if(CBconsultacontOrden.getSelectedItem().toString().equals("PRODUCTOS"))
    {
        JTC.clear(JTAIA);
        JTC.cabecera(JTAIA);
        JTC.msj(JTAIA,"Consulta de Productos Usados.\n");    
            
        if(productos.isEmpty()){JTC.msj(JTAIA,"Aún no escoge ningun producto a usar");}
        else
        {
            for( SGL_CL_Productos x : productos)
            {
                JTC.msj(JTAIA, "CODIGO : "+x.getIdPro()+"\n"+"NOMBRE : "+x.getNomPro()+"\n"+"------------------------------------\n");
            }
        }
    }
    
    if(CBconsultacontOrden.getSelectedItem().toString().equals("IMPLEMENTOS"))
    {
        JTC.clear(JTAIA);
        JTC.cabecera(JTAIA);
        JTC.msj(JTAIA,"Consulta de Implementos Usados.\n");    
        
        if(implementos.isEmpty()){JTC.msj(JTAIA,"Aún no escoge ningun implemento a usar");}
        else
        {
            for( SGL_CL_Implementos x : implementos)
            {
                JTC.msj(JTAIA, "CODIGO : "+x.getIdmaq()+"\n"+"NOMBRE : "+x.getNommaq()+"\n"+"------------------------------------\n");
            }
        }
    }

    
    }//GEN-LAST:event_btnconsultarcontenidoOrdenActionPerformed

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
             evn.write(lblusuario.getText(), "Selecciono el tipo de inserción antiguo", "JIFGenerarOrdenServicio", "Radio Button 'Antigua' Presionado");
         }
          else 
            if(JCHAntigua.isSelected()==false)
            {
                evn.write(lblusuario.getText(), "Deselecciono el tipo de inserción antiguo", "JIFGenerarOrdenServicio", "Radio Button 'Antigua' Presionado");
                RDBNatural.setEnabled(false);
                RDBJuridica.setEnabled(false);
                cancelar();
                clearFRM();
                //BLOQUE 1:
                enadisa_bloque1_botones(false, false, false, true, false, false, false, true);
                //BLOQUE 2:
                enadisa_bloque2_botones(false, false, false, false);
                //BLOQUE 3:
                enadisa_bloque3_botones(false, false, false);
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
             evn.write(lblusuario.getText(), "Selecciono el tipo de inserción Ticket Trabajo", "JIFGenerarOrdenServicio", "Radio Button 'Ticket Trabajo' Presionado");
         }
         else 
            if(JCHTicketTrabajo.isSelected()==false)
            {
                evn.write(lblusuario.getText(), "Deselecciono el tipo de inserción Ticket Trabajo", "JIFGenerarOrdenServicio", "Radio Button 'Ticket Trabajo' Presionado");
                RDBNatural.setEnabled(false);
                RDBJuridica.setEnabled(false);
                cancelar();
                clearFRM();
                //BLOQUE 1:
                enadisa_bloque1_botones(false, false, false, true, false, false, false, true);
                //BLOQUE 2:
                enadisa_bloque2_botones(false, false, false, false);
                //BLOQUE 3:
                enadisa_bloque3_botones(false, false, false);
                //BLOQUE 5:
                enadisa_bloque5_botones(false);
                //BLOQUE 6:
                enadisa_bloque6_botones(false, false, false, false, false, false, false);
            }

    }//GEN-LAST:event_JCHTicketTrabajoActionPerformed

    private void txtcostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcostoKeyTyped

    txtcosto.addKeyListener(new KeyAdapter() 
    {
    @Override
    public void keyReleased(final KeyEvent e) 
    {

            if(txtcosto.getText().contains(",")){txtcosto.setText("0");txtcosto.setBackground(Color.RED);}
            else
            { 
               if(txtcosto.getText().equals(""))
               {
                   enadisa_bloque_descuento(false);
               }
               else
                {
                    enadisa_bloque_descuento(true);
                }
               String cadena = (txtcosto.getText());
               txtdescuentomanual.setText("0");
               txtdescuentocodigo.setText("-");
               txtdescuentocodigo.setBackground(ColorInicial);
               txtcostofinal.setText(cadena);
               repaint();
            }
        
    }
    });

    }//GEN-LAST:event_txtcostoKeyTyped

    private void txtdescuentomanualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescuentomanualKeyTyped

        
        txtdescuentomanual.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyReleased(final KeyEvent e) 
            {
                txtdescuentocodigo.setText("-");
                txtdescuentocodigo.setBackground(ColorInicial);
                if(txtdescuentomanual.getText().equals("")){txtdescuentomanual.setText("0");}
                if(Double.parseDouble(txtdescuentomanual.getText())>100){txtdescuentomanual.setText("99");}
                                     
                String cadena = (txtdescuentomanual.getText());
                txtdescuentomanual.setText(cadena);
                Double costo = Double.parseDouble(txtcosto.getText());
                Double desc = Double.parseDouble(txtdescuentomanual.getText());
                Double calcTotal = ((costo*desc)/100);
                Double total = costo-calcTotal;
                txtcostofinal.setText(""+String.format("%.2f", total));
                repaint();
            }
        });
           
        
        
    }//GEN-LAST:event_txtdescuentomanualKeyTyped

    private void btnAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarClienteActionPerformed
        
        try
        {
            JIFMantenerClientes clientes = new JIFMantenerClientes();
            JFrame jf=new JFrame();
            jf.setAlwaysOnTop(true);
            
            txtcodCliente.setText("");
            txtnombrecliente.setText("");
            JTAdetcliente.setText("");
            txtdireccion.setText("");
            
            if(JSMMantenerClientes.getActionCommand().equals("Abierto"))
                {
                    SYSAUDIOCON sysau = new SYSAUDIOCON();
                    sysau.E_ERROR();
                    JOptionPane.showMessageDialog(jf,"Ya esta abierto", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }
            else
            {                
                JSMMantenerClientes.setActionCommand("Abierto");
                JDEscritorio.add(clientes);
                Dimension desktopSize = JDEscritorio.getSize();
                Dimension FrameSize = clientes.getSize();
                clientes.setLocation((desktopSize.width - FrameSize.width)/8, (desktopSize.height- FrameSize.height)/2);
                evn.write(JMSesion.getText(), "Abrio el formulario 'Mantener Clientes'", "JFPrincipal", "Menu 'Mantener Clientes' Presionado");
                clientes.show();
            }
        }
        catch (Exception e)
        {
            
            lc.write("Error intentando abrir 'Mantenimiento Clientes'", "JFRPrincipal", e);
        }
        
    }//GEN-LAST:event_btnAgregarClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBconsultacontOrden;
    private javax.swing.JCheckBox JCHAntigua;
    private javax.swing.JCheckBox JCHTicketTrabajo;
    private javax.swing.JTextArea JTAAnuncio;
    private javax.swing.JTextArea JTAIA;
    public static javax.swing.JTextArea JTAdetcliente;
    private javax.swing.JRadioButton RDBJuridica;
    private javax.swing.JRadioButton RDBNatural;
    private javax.swing.ButtonGroup TipoOrden;
    private javax.swing.JButton btnAgregarAreas;
    private javax.swing.JButton btnAgregarCliente;
    private javax.swing.JButton btnAgregarImplementos;
    private javax.swing.JButton btnAgregarOperarios;
    private javax.swing.JButton btnAgregarProductos;
    private javax.swing.JButton btnAgregarServicios;
    private javax.swing.JButton btnBuscarcliente;
    private javax.swing.JButton btnPDF;
    private javax.swing.JButton btnQuitarDescuentoCodigo;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnconsultarAsociado;
    private javax.swing.JButton btnconsultarDescuentocodigo;
    private javax.swing.JButton btnconsultarVendedor;
    private javax.swing.JButton btnconsultarcontenidoOrden;
    private javax.swing.JButton btncopiar;
    private javax.swing.JButton btnfecha;
    private javax.swing.JButton btnhora;
    private javax.swing.JButton btnimprimir;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnquitarVendedor;
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
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lblnomcli;
    private javax.swing.JLabel lblnomcli1;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JLabel lblusuario1;
    private javax.swing.JLabel lblusuario2;
    private javax.swing.JLabel lblusuario3;
    private javax.swing.JLabel lblusuario4;
    private javax.swing.JLabel lblusuario5;
    private javax.swing.JLabel lblusuario6;
    private javax.swing.JLabel lblusuario7;
    private javax.swing.JLabel lblusuario8;
    public static javax.swing.JTextField txtcodCliente;
    public static javax.swing.JTextField txtcodvendCrystal;
    public static javax.swing.JTextField txtcosto;
    public static javax.swing.JTextField txtcostofinal;
    public static javax.swing.JTextField txtdescuentocodigo;
    public static javax.swing.JTextField txtdescuentomanual;
    public static javax.swing.JTextField txtdireccion;
    public static javax.swing.JTextField txtdocumentacion;
    public static javax.swing.JTextField txtfecha;
    public static javax.swing.JTextField txtgiro;
    public static javax.swing.JTextField txthora;
    public static javax.swing.JTextField txtidVendedor;
    public static javax.swing.JTextField txtidorden;
    public static javax.swing.JTextField txtnombrecliente;
    public static javax.swing.JTextField txtnombrevendedor;
    public static javax.swing.JTextField txtnumeroorden;
    // End of variables declaration//GEN-END:variables
}
