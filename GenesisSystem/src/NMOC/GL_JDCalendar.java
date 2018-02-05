/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC;

import ModuleWorker.IC.MWCON;
import ModuleWorker.SYSFRMCON;
import ModuleWorker.View.JFRPrincipal;
import NCLPM.EVENTS;
import NCLPM.LOG;
import NMOC.MD_Generar.View.JIFGenerarCertificado;
import NMOC.MD_Generar.View.JIFGenerarOrdenTrabajo;

/**
 *
 * @author USUARIO
 */
public class GL_JDCalendar extends javax.swing.JDialog
{

    /**
     * Creates new form GL_JDCalendar
     */
    
    LOG lc = new LOG();
    EVENTS evn = new EVENTS();
    SYSFRMCON sysfrm = new SYSFRMCON();
    MWCON mw = new MWCON();
    private String tipo;
    public String formulario = "";

    
    public GL_JDCalendar(java.awt.Frame parent, boolean modal,String capsule) 
    {
        super(parent, modal);
        initComponents();
        this.setTitle(sysfrm.T_Date());
        sysfrm.B_Date(this.getContentPane());
        this.setLocation(730, 400);
        H_T_DATE(capsule);
        tipo=capsule;
    }
   
    private void H_T_DATE(String tip)
    {
        if(tip.equals("D"))
        {
            //SEMI IA: este establecera las fechas a las fechas actuales cuando el tipo sea D
            String ArrayTem[] = mw.fecha_actual().split("/");
            int  i = Integer.parseInt(ArrayTem[1])-1;     
            CB1.setSelectedItem(ArrayTem[0]);
            CB2.setSelectedIndex(i);
            CB3.setSelectedItem(ArrayTem[2]);
            
            //NOMBRES A LOS LABELS
            lbl1.setText("Día");
            lbl2.setText("Mes");
            lbl3.setText("Año");
            
        }
        if(tip.equals("T"))
        {
            
            //NOMBRES DE LOS LABELS
            lbl1.setText("Hora");
            lbl2.setText("Minutos");
            lbl3.setText("AM/PM");
            //NUEVOS MODELOS DE CBOX 
            CB1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
            CB2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"00", "05", "10", "15", "20","25","30","35", "40","45", "50","55"}));
            CB3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"AM", "PM"}));
            //SEMI IA: este establecera las horas actuales cuando el tipo sea T
            String ArrayTemp[] = mw.hour_actual().split(":");
            CB1.setSelectedItem(ArrayTemp[0]);
            CB2.setSelectedIndex(6);

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

        jLabel4 = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        btnestablecer = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        CB3 = new javax.swing.JComboBox<>();
        CB2 = new javax.swing.JComboBox<>();
        CB1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("SELECTOR DE FECHA Y HORA");

        lbl1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl1.setText("Día");

        lbl2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl2.setText("Mes");

        lbl3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3.setText("Año");

        btnestablecer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Accept-icon24.png"))); // NOI18N
        btnestablecer.setText("Establecer");
        btnestablecer.setContentAreaFilled(false);
        btnestablecer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnestablecerActionPerformed(evt);
            }
        });

        btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Windows-Close-Program-icon.png"))); // NOI18N
        btncancelar.setText("Cancelar");
        btncancelar.setContentAreaFilled(false);
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        CB3.setBackground(new java.awt.Color(204, 204, 204));
        CB3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        CB3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040" }));

        CB2.setBackground(new java.awt.Color(204, 204, 204));
        CB2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        CB2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre" }));

        CB1.setBackground(new java.awt.Color(204, 204, 204));
        CB1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        CB1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CB1, 0, 77, Short.MAX_VALUE)
                                    .addComponent(lbl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CB2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CB3, 0, 77, Short.MAX_VALUE)
                                    .addComponent(lbl3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btncancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnestablecer)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl1)
                    .addComponent(lbl2)
                    .addComponent(lbl3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(CB3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CB2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CB1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnestablecer)
                    .addComponent(btncancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnestablecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnestablecerActionPerformed

    try 
    {
        if(tipo.equals("D"))
        {
            //OBTENCION DE DATOS
            String dia,mes,año;
            dia = CB1.getSelectedItem().toString();
            //MES CONDICIONAL
            if((CB2.getSelectedIndex()+1)<10)
            {
                mes = "0"+(CB2.getSelectedIndex()+1);
            }else{mes = ""+(CB2.getSelectedIndex()+1);}
            //MES FIN CONDICIONAL
            año = CB3.getSelectedItem().toString();
            
            //DESDE AQUI CONDICIONALES DE FORMULARIOS -- ESPECIFICACIONES
            if(formulario.equals("ORDEN_SERV"))
            {
                evn.write(JFRPrincipal.JMSesion.getText(), "Establecio la fecha "+dia+"/"+mes+"/"+año+"\n en el formulario 'JIFGenerarOrdenServicio'", "JDCalendar", "Botón 'Establecer' presionado");
                JIFGenerarOrdenTrabajo.txtfecha.setText(dia+"/"+mes+"/"+año);
            }
            
            if(formulario.equals("GEN_CERT_SERV"))
            {
                evn.write(JFRPrincipal.JMSesion.getText(), "Establecio la fecha "+dia+"/"+mes+"/"+año+"\n en el formulario 'JIFGenerarCertificado'", "JDCalendar", "Botón 'Establecer' presionado");
                JIFGenerarCertificado.txtfechaServicio.setText(dia+"/"+mes+"/"+año);
            }
            
            if(formulario.equals("GEN_CERT_VEN"))
            {
                evn.write(JFRPrincipal.JMSesion.getText(), "Establecio la fecha "+dia+"/"+mes+"/"+año+"\n en el formulario 'JIFGenerarCertificado'", "JDCalendar", "Botón 'Establecer' presionado");
                JIFGenerarCertificado.txtfechaVencimiento.setText(dia+"/"+mes+"/"+año);
            }
        }
        
        if(tipo.equals("T"))
        {
            //OBTENCION DE DATOS
            String hora,minuto,fort;
            hora = CB1.getSelectedItem().toString();
            minuto = CB2.getSelectedItem().toString();
            fort = CB3.getSelectedItem().toString();

            //DESDE AQUI CONDICIONALES DE FORMULARIOS -- ESPECIFICACIONES            
            if(formulario.equals("ORDEN_SERV"))
            {   
               evn.write(JFRPrincipal.JMSesion.getText(), "Establecio la hora "+hora+":"+minuto+" "+fort+"\n en el formulario 'JIFGenerarOrdenServicio'", "JDCalendar", "Botón 'Establecer' presionado");
               JIFGenerarOrdenTrabajo.txthora.setText(hora+":"+minuto+" "+fort);
            }
        }
        
        this.dispose();
    } catch (Exception e) 
        {
            lc.write("No se pudo establecer hora o la hora debido a un error no esperado", "JDCalendar", e);
        }
        
        
    }//GEN-LAST:event_btnestablecerActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed

    evn.write(JFRPrincipal.JMSesion.getText(), "Cancelo la seleccion de fechas y cerro el formulario", "GL_JDCalendar", "Botón 'cancelar' presionado");
    dispose();
        
    }//GEN-LAST:event_btncancelarActionPerformed

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
            java.util.logging.Logger.getLogger(GL_JDCalendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GL_JDCalendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GL_JDCalendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GL_JDCalendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GL_JDCalendar dialog = new GL_JDCalendar(new javax.swing.JFrame(), true,"");
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
    private javax.swing.JComboBox<String> CB1;
    private javax.swing.JComboBox<String> CB2;
    private javax.swing.JComboBox<String> CB3;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnestablecer;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    // End of variables declaration//GEN-END:variables
}
