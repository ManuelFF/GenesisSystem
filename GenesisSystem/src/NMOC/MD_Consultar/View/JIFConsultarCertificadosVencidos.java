/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Consultar.View;

import ModuleWorker.IC.MWCON;
import ModuleWorker.SYSFRMCON;
import ModuleWorker.View.JFRPrincipal;
import NCLPM.EVENTS;
import NCLPM.LOG;
import NCLPM.RESULTS;
import NMOC.MD_Consultar.IC.NANOCON_ConsultarCertificados;
import NMOC.MD_Consultar.IC.PaintTable;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class JIFConsultarCertificadosVencidos extends javax.swing.JInternalFrame
{

    /**
     * Creates new form JIFConsultarCertificadosVencidos
     */
    
    NANOCON_ConsultarCertificados P_CC = new NANOCON_ConsultarCertificados();
    LOG lc = new LOG();
    EVENTS evn = new EVENTS();
    RESULTS rslt = new RESULTS();
    SYSFRMCON sysfrm = new SYSFRMCON();
    MWCON mw = new MWCON();
    JFrame form;
    Color colorinicial;
    
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
    
    public JIFConsultarCertificadosVencidos()
    {
        initComponents();
        this.setTitle(sysfrm.T_ConsultarCertificadosVen());
        sysfrm.B_ConsultarCertificadosVen(this.getContentPane());
        lblusuario.setText(JFRPrincipal.JMSesion.getText());
        lbldate.setText(mw.fecha_actual());
        
        //ESTABLECIENDO MODELO DE JTABLE
        JTConsultar.setModel(modelo);
        
        JTConsultar.getTableHeader().setReorderingAllowed(false);
        
        //CREANDO EL ROW FILTER SORTER
        trsFiltro = new TableRowSorter(JTConsultar.getModel());
        
        //Estableciendo el ROW FILTER SORTER
        JTConsultar.setRowSorter(trsFiltro);
        
        iniciar();
        inteligencia_artificial();
        pintarTabla();
    }
    
    //INTELIGENCIA ARTIFICIAL
    private void inteligencia_artificial()
    {               
        for(int i=0;i<modelo.getRowCount();i++)
        {
            String fecha2 = modelo.getValueAt(i, 6).toString();
            System.out.println(compararFechasConDate(fecha2, mw.fecha_actual()));
            
            if(compararFechasConDate(fecha2, mw.fecha_actual()).equals("MENOR"))
            {
                modelo.setValueAt("VENCIDO", i, 10);
                System.out.println(modelo.getValueAt(i, 0));
            }
            
            if(compararFechasConDate(fecha2, mw.fecha_actual()).equals("MAYOR"))
            {
                modelo.setValueAt("VIGENTE", i, 10);
                System.out.println(modelo.getValueAt(i, 0));

            }
            
            if(compararFechasConDate(fecha2, mw.fecha_actual()).equals("IGUAL"))
            {
                modelo.setValueAt("POR VENCER", i, 10);
                System.out.println(modelo.getValueAt(i, 0));
            }
        }
    }
    
    private void pintarTabla()
    {
        //se crea instancia a clase FormatoTable y se indica columna patron
        PaintTable ft = new PaintTable(10);
        JTConsultar.setDefaultRenderer (Object.class, ft );
    }
    
    private void iniciar()
    {
        PseudoIA();
        inteligencia_artificial();
        pintarTabla();
    }
    
    
    
    
    private String compararFechasConDate(String fecha1, String fechaActual) 
    {  
        System.out.println("Parametro String Fecha 1 = "+fecha1+"\n" +
    "Parametro String fechaActual = "+fechaActual+"\n");  
        String resultado="";
        try 
        {
         /**Obtenemos las fechas enviadas en el formato a comparar*/
         SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy"); 
         Date fechaDate1 = formateador.parse(fecha1);
         Date fechaDate2 = formateador.parse(fechaActual);

         System.out.println("Parametro Date Fecha 1 = "+fechaDate1+"\n" +
     "Parametro Date fechaActual = "+fechaDate2+"\n");

          if ( fechaDate1.before(fechaDate2) )
          {
          resultado= "MENOR";
          }else{
           if ( fechaDate2.before(fechaDate1) )
           {
            resultado= "MAYOR";
           }else
               {
                resultado= "IGUAL";
               } 
          }
        } catch (ParseException e) 
        {
         lc.write("Se produjo un error al intentar comprar las fechas", "JIFConsultarCertificadosVencidos -> compararFechasConDate", e);
        }  
        return resultado;
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

    //Razón Social, Giro, ID,Número Certificado
    private void filtro() 
    {
          int columnaABuscar = 0;
          
          if(cbfiltro.getSelectedItem().toString().equals("Razón Social"))
          {
              columnaABuscar = 2;
          }
          if(cbfiltro.getSelectedItem().toString().equals("Giro"))
          {
              columnaABuscar = 3;
          }
          if(cbfiltro.getSelectedItem().toString().equals("Número Certificado"))
          {
              columnaABuscar = 1;
          }
          if (cbfiltro.getSelectedItem().toString().equals("ID")) 
          {
              columnaABuscar = 0;
          }
          trsFiltro.setRowFilter(RowFilter.regexFilter(txtfiltro.getText(), columnaABuscar));
    }   
    
    private void clearTable()
    {
       mw.clear_table(modelo, JTConsultar);
    }
    
    private int obtenerUltimoDiaMes (String year, String month) 
    {
        int anio = Integer.parseInt(year);
        int mes = Integer.parseInt(month);
        
        Calendar calendario=Calendar.getInstance();
        calendario.set(anio, mes-1, 1);
        return calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    private String retornaMes(String monthNumberS)
    {
        int monthNumber = Integer.parseInt(monthNumberS);
        String monthString = null;
        
        switch (monthNumber)
        {
            case 1:  monthString = "Enero";
                     break;
            case 2:  monthString = "Febrero";
                     break;
            case 3:  monthString = "Marzo";
                     break;
            case 4:  monthString = "Abril";
                     break;
            case 5:  monthString = "Mayo";
                     break;
            case 6:  monthString = "Junio";
                     break;
            case 7:  monthString = "Julio";
                     break;
            case 8:  monthString = "Agosto";
                     break;
            case 9:  monthString = "Setiembre";
                     break;
            case 10: monthString = "Octubre";
                     break;
            case 11: monthString = "Noviembre";
                     break;
            case 12: monthString = "Diciembre";
                     break;
            default: monthString = "Mes invalido";
                     break;
        } 
        return monthString;
    }
        
    private void PseudoIA()
    {
        //SELECTOR DE FECHAS
        String arrayFech[] = mw.fecha_actual().split("/");

        //BUFF OP
        String day = ""+obtenerUltimoDiaMes(arrayFech[2], arrayFech[1]);
        
        //FIN BUFFER DE OP
        String fech1 = "01"+"/"+arrayFech[1]+"/"+arrayFech[2];
        String fech2 = day+"/"+arrayFech[1]+"/"+arrayFech[2];
        
        P_CC.CargarCertificadoVencidos(modelo, JTConsultar, fech1, fech2);
        
        cbdia.setSelectedItem(arrayFech[0]);
        cbmes.setSelectedItem(retornaMes(arrayFech[1]));
        cbaño.setSelectedItem(arrayFech[2]);
        
        inteligencia_artificial();
        pintarTabla();
    }
    
    private void cambiarVista(String day, String month , String year)
    {
                
        clearTable();

        String fech1 = "01"+"/"+month+"/"+year;
        String fech2 = day+"/"+month+"/"+year;
                        
        P_CC.CargarCertificadoVencidos(modelo, JTConsultar, fech1, fech2);
        inteligencia_artificial();
        pintarTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        JTConsultar = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        lblusuario = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbldate = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbdia = new javax.swing.JComboBox<>();
        cbmes = new javax.swing.JComboBox<>();
        cbaño = new javax.swing.JComboBox<>();
        btncambiarFecha = new javax.swing.JButton();
        btnFiltrarDia = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbfiltro = new javax.swing.JComboBox<>();
        txtfiltro = new javax.swing.JTextField();
        btnMostrarTodos = new javax.swing.JButton();
        btnsalir2 = new javax.swing.JButton();
        btnverdetalles = new javax.swing.JButton();

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
        jScrollPane1.setViewportView(JTConsultar);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 102));
        jLabel4.setText("Consultar Certificados Vencidos ");

        lblusuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblusuario.setText("Manuel Fernando Saavedra Benites");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("usuario:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Fecha Sistema:");

        lbldate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbldate.setText("18/12/2017");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Mostrando de :");

        cbdia.setBackground(new java.awt.Color(204, 204, 204));
        cbdia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        cbmes.setBackground(new java.awt.Color(204, 204, 204));
        cbmes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre" }));

        cbaño.setBackground(new java.awt.Color(204, 204, 204));
        cbaño.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040" }));

        btncambiarFecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/exchange.png"))); // NOI18N
        btncambiarFecha.setText("Cambiar");
        btncambiarFecha.setContentAreaFilled(false);
        btncambiarFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncambiarFechaActionPerformed(evt);
            }
        });

        btnFiltrarDia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Time.png"))); // NOI18N
        btnFiltrarDia.setText("Filtrar día");
        btnFiltrarDia.setContentAreaFilled(false);
        btnFiltrarDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarDiaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 255, 0));
        jLabel7.setText("Vigente");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 51));
        jLabel8.setText("Por Vencer");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("Vencido");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Buscar:");

        cbfiltro.setBackground(new java.awt.Color(204, 204, 204));
        cbfiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Número Certificado", "Razón Social", "Giro", "ID" }));

        txtfiltro.setBackground(new java.awt.Color(204, 204, 255));
        txtfiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfiltroKeyTyped(evt);
            }
        });

        btnMostrarTodos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/information-icon.png"))); // NOI18N
        btnMostrarTodos.setText("Mostrar Todos");
        btnMostrarTodos.setContentAreaFilled(false);
        btnMostrarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTodosActionPerformed(evt);
            }
        });

        btnsalir2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/logout-icon24.png"))); // NOI18N
        btnsalir2.setText("Salir");
        btnsalir2.setContentAreaFilled(false);
        btnsalir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalir2ActionPerformed(evt);
            }
        });

        btnverdetalles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/OutOFDate_ConsultaCert24.png"))); // NOI18N
        btnverdetalles.setText("Ver detalles");
        btnverdetalles.setContentAreaFilled(false);
        btnverdetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnverdetallesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbdia, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbmes, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbaño, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btncambiarFecha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnFiltrarDia))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtfiltro)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMostrarTodos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnverdetalles)
                        .addGap(10, 10, 10)
                        .addComponent(btnsalir2)
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(jLabel7))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addComponent(jLabel9)))
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblusuario)
                        .addComponent(jLabel4)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(lbldate))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbmes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btncambiarFecha)
                            .addComponent(cbdia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(btnFiltrarDia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cbfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMostrarTodos)
                            .addComponent(btnsalir2)
                            .addComponent(btnverdetalles)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btncambiarFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncambiarFechaActionPerformed

   try 
    {
        String mes = ""+(cbmes.getSelectedIndex());
        String year = cbaño.getSelectedItem().toString();
        String day = ""+obtenerUltimoDiaMes(year, mes);
        cambiarVista(day,mes,year);        
        
        cbdia.setSelectedIndex(1);
        cbmes.setSelectedItem(mes);
        cbaño.setSelectedItem(year);
        
    } catch (Exception e) 
      {
          lc.write("Ha ocurrido algun error al intentar cambiar la vista de certificados vencidos", "JIFConsultarCertificadosVencidos", e);
      }
        
    }//GEN-LAST:event_btncambiarFechaActionPerformed

    private void btnFiltrarDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarDiaActionPerformed
    
    try 
    {
        String day = cbdia.getSelectedItem().toString();
        String year = cbaño.getSelectedItem().toString();
        int month = cbmes.getSelectedIndex();
        
        clearTable();

        String fech1 = day+"/"+month+"/"+year;
        String fech2 = day+"/"+month+"/"+year;
        
        cbdia.setSelectedItem(day);
                
        P_CC.CargarCertificadoVencidos(modelo, JTConsultar, fech1, fech2);
        
        inteligencia_artificial();
        pintarTabla();
        
    } catch (Exception e) 
      {
          lc.write("Ha ocurrido algun error al intentar filtrar el día", "JIFConsultarCertificadosVencidos", e);
      }
        
    }//GEN-LAST:event_btnFiltrarDiaActionPerformed

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

    private void btnMostrarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTodosActionPerformed

  try 
    {
        //SELECTOR DE FECHAS
        clearTable();
        String arrayFech[] = mw.fecha_actual().split("/");

        //BUFF OP
        String day = ""+obtenerUltimoDiaMes(arrayFech[2]+1, "12");
        
        //FIN BUFFER DE OP
        String fech1 = "01"+"/"+arrayFech[1]+"/"+2010;
        String fech2 = day+"/"+12+"/"+(arrayFech[2]);
        
        P_CC.CargarCertificadoVencidos(modelo, JTConsultar, fech1, fech2);
        
        cbdia.setSelectedItem(day);
        cbmes.setSelectedItem("Diciembre");
        cbaño.setSelectedItem(arrayFech[2]);
        
        inteligencia_artificial();
        pintarTabla();
        
    } catch (Exception e) 
      {
          lc.write("Ha ocurrido algun error al intentar mostrar todos los certificados vencidos", "JIFConsultarCertificadosVencidos", e);
      }
        
    }//GEN-LAST:event_btnMostrarTodosActionPerformed

    private void btnsalir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalir2ActionPerformed

    try
    {
        evn.write(lblusuario.getText(), "Ha salido del formulario 'Consultar Certificados Vencidos'", "JIFConsultarCertificadosVencidos", "Presiono Botón 'Salir'");
        JFRPrincipal.JSMConsultarCertificadoVen.setActionCommand("CERRADO");
        this.dispose();
    } catch (Exception e)
    {
        lc.write("No se ha podido cerrar el formulario 'Consultar Certificados Vencidos' debido a un error inesperado", "JIFConsultarCertificadosVencidos", e);
    }


    }//GEN-LAST:event_btnsalir2ActionPerformed

    private void btnverdetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnverdetallesActionPerformed
    
    try
    {
//        if(lblseleccion1.getText().equals("SELECCION")){JOptionPane.showMessageDialog(this, "Seleccione primero un certificado");}
//      else
//        {
//            int filasel = Jtordenes.getSelectedRow();
//            String ID = ""+modelo.getValueAt(filasel, 0);
//            String numero_cert = ""+modelo.getValueAt(filasel, 10);
//            String Razon_Social = ""+modelo.getValueAt(filasel, 1);
//            String ubicacion = ""+modelo.getValueAt(filasel, 2);
//            String giro = ""+modelo.getValueAt(filasel, 3);
//            String Area_tratada = ""+modelo.getValueAt(filasel, 4);
//            String fecha_serv = ""+modelo.getValueAt(filasel, 5);
//            String fecha_ven = ""+modelo.getValueAt(filasel, 6);
//            String telefono = ""+modelo.getValueAt(filasel, 7);
//            String costo = ""+modelo.getValueAt(filasel, 8);
//            String periodo = ""+modelo.getValueAt(filasel, 9);
//            String estado = ""+modelo.getValueAt(filasel, 12);
//            String detalles = ""+modelo.getValueAt(filasel, 14);
//            String nota = ""+modelo.getValueAt(filasel, 15);
//
//            if(detalles.equals("")|| detalles.toUpperCase().equals("NULL")){detalles = " ";}
//            if(nota.equals("")|| nota.toUpperCase().equals("NULL")){nota = " ";}
//
//
//
//            String ServiciosRealizados = ""+modelo.getValueAt(filasel, 13);
//
//            String ArrayTemp[] = ServiciosRealizados.split("/");
//
//            String op1,op2,op3,op4,op5,op6;
//
//            op1 = ArrayTemp[0];
//            op2 = ArrayTemp[1];
//            op3 = ArrayTemp[2];
//            op4 = ArrayTemp[3];
//            op5 = ArrayTemp[4];
//            op6 = ArrayTemp[5];
//
//            if(op1.equals("-")){op1="";}if(op2.equals("-")){op2="";}if(op3.equals("-")){op3="";}if(op4.equals("-")){op4="";}if(op5.equals("-")){op5="";}if(op6.equals("-")){op6="";}
//
//
//
//            JDDetalleCertificados grande = new JDDetalleCertificados(form,true);
//            grande.setTitle("VER DETALLE CERTIFICADO DE SERVICIO N°"+numero_cert+" - "+moduleWorker.nombreSistema()+" V"+moduleWorker.Version());
//
//            grande.LOG_FontSize(14);
//            grande.LOG_borrarTexto();
//            grande.LOG_mensaje("CRYSTAL MANAGEMENT SYSTEM.");
//            grande.LOG_mensaje("\nDETALLE DEL CERTIFICADO N° "+numero_cert);
//            grande.LOG_mensaje("\n\nASISTENTE IA:\n");
//
//            grande.LOG_mensaje("\nCertificado N° : "+numero_cert);
//            grande.LOG_mensaje("\nRazón Social : "+Razon_Social);
//            grande.LOG_mensaje("\nGiro : "+giro);
//            grande.LOG_mensaje("\nUbicación : "+ubicacion);
//            grande.LOG_mensaje("\nTelefono : "+telefono);
//            grande.LOG_mensaje("\nPeriodo : "+periodo);
//            grande.LOG_mensaje("\nCosto : "+costo);
//            grande.LOG_mensaje("\nFecha de Servicio : "+fecha_serv);
//            grande.LOG_mensaje("\nFecha de Vencimiento : "+fecha_ven);
//            grande.LOG_mensaje("\nEstado : "+estado);
//
//            grande.LOG_mensaje("\n\nÁrea Tratada : "+Area_tratada);
//            grande.LOG_mensaje("\nServicios Realizados :\n");
//
//            //PRE CONDICIONAL
//            if(op1.equals("")){}
//            else{grande.LOG_mensaje("\n"+op1.toUpperCase());}
//            if(op2.equals("")){}
//            else{grande.LOG_mensaje("\n"+op2.toUpperCase());}
//            if(op3.equals("")){}
//            else{grande.LOG_mensaje("\n"+op3.toUpperCase());}
//            if(op4.equals("")){}
//            else{grande.LOG_mensaje("\n"+op4.toUpperCase());}
//            if(op5.equals("")){}
//            else{grande.LOG_mensaje("\n"+op5.toUpperCase());}
//            if(op6.equals("")){}
//            else{grande.LOG_mensaje("\n"+op6.toUpperCase());}
//
//            grande.LOG_mensaje("\n\nDetalles : "+detalles+"\n");
//            grande.LOG_mensaje("\n\nNotas : "+nota+"\n");
//
//            grande.setVisible(true);

//        }
    } catch (Exception e) {JOptionPane.showMessageDialog(this, "Ha ocurrido un Error imprevisto, ¿Selecciono un Certificado?\nCodigo de Error : "+e); }
        
    }//GEN-LAST:event_btnverdetallesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTConsultar;
    private javax.swing.JButton btnFiltrarDia;
    private javax.swing.JButton btnMostrarTodos;
    private javax.swing.JButton btncambiarFecha;
    private javax.swing.JButton btnsalir2;
    private javax.swing.JButton btnverdetalles;
    private javax.swing.JComboBox<String> cbaño;
    private javax.swing.JComboBox<String> cbdia;
    private javax.swing.JComboBox<String> cbfiltro;
    private javax.swing.JComboBox<String> cbmes;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JTextField txtfiltro;
    // End of variables declaration//GEN-END:variables
}
