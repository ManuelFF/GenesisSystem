/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker.View;

import ModuleWorker.Core.NOB_Asistencia;
import ModuleWorker.IC.MWCON;
import ModuleWorker.IC.NANOCON_Asistencia;
import ModuleWorker.SYSFRMCON;
import NCLPM.EVENTS;
import NCLPM.LOG;
import NCLPM.RESULTS;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author USUARIO
 */
public class JDMarcarAsistencia extends javax.swing.JDialog 
{

    LOG lc = new LOG();
    EVENTS evn = new EVENTS();
    RESULTS rslt = new RESULTS();
    SYSFRMCON sysfrm = new SYSFRMCON();
    MWCON mw = new MWCON();
    NANOCON_Asistencia P_asist = new NANOCON_Asistencia();

    
    /**
     * Creates new form JDMarcarAsistencia
     * @param parent
     * @param modal
     */
    
    public JDMarcarAsistencia(java.awt.Frame parent, boolean modal,String nombre) 
    {
        super(parent, modal);
        initComponents();
        this.setAlwaysOnTop(true);
        this.setAutoRequestFocus(true);
        this.setLocationRelativeTo(null);
        this.setTitle(sysfrm.T_RegistrarAsistencia());
        sysfrm.B_RegistrarAsistencia(this.getContentPane());
        ID_PER = nombre;
    }

    String ID_PER;
    
    private String NuevoCodigo()
    {
        String codigo  = String.format("%03d", 1);
	int cod ; 
        NOB_Asistencia ultObjeto = null;
				
	if( P_asist.tamaño()==0 ) // Array vacío
		codigo = "ASIST-"+codigo;
	else {
		// La posición del último objeto se obtiene con tamaño()-1
		// Obtenemos el último objeto del ArrayList
		ultObjeto = P_asist.obtener( P_asist.tamaño()-1 );
		// Obtenemos los caracteres desde la posicion dos hasta el final ("003"); 
		codigo = ultObjeto.getId_asist().substring(6);
		//Agregamos una unidad al codigo extraido en el paso anterior
		cod = Integer.parseInt(codigo)+1;
		codigo = "ASIST-"+String.format("%03d",cod);
              }
	return codigo;
    }
    
    private int CT(int h, int m)
    {
       int HH=h*3600;
       int mm=m*60;
       int t=HH+mm;
             
       return t;
    }
    
    private String comprt(int CT1, int CT2)
    {
        if(CT1==CT2)
        {
           // System.out.println("hora EXACTA");
            return "NO";
            
        }else
            if(CT2<CT1)
            {
               // System.out.println("ENTRADA ANTES DE TIEMPO");
                return "NO";
                
            }else
                if(CT1<CT2)
                {
                    //System.out.println("HORA RETRASADA");

                    int result = CT1-CT2;

                    //System.out.println(Math.abs(result));
                    //System.out.println("Tiene un retraso de : "+(Math.abs(result)/60)+" Minutos "+"("+convert_D(Math.abs(result))+" Horas)");
                    return "Retraso de : "+(Math.abs(result)/60)+" Minutos "+"("+FMF(Math.abs(result)/60)+" Horas)";
                }
        return "NULL";
        
    }
        
    public String FMF(int minutos) 
    {
        String formato = "%02d:%02d";
        long horasReales = TimeUnit.MINUTES.toHours(minutos);
        long minutosReales = TimeUnit.MINUTES.toMinutes(minutos) - TimeUnit.HOURS.toMinutes(TimeUnit.MINUTES.toHours(minutos));
        return String.format(formato, horasReales, minutosReales);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbllogo = new javax.swing.JLabel();
        btnmarcar = new javax.swing.JButton();
        lbltext1 = new javax.swing.JLabel();
        lbltext2 = new javax.swing.JLabel();
        lbltext3 = new javax.swing.JLabel();
        lbltext4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        lbllogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/check IN_300.png"))); // NOI18N

        btnmarcar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnmarcar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/Accept-icon24.png"))); // NOI18N
        btnmarcar.setText("Registrar Entrada");
        btnmarcar.setContentAreaFilled(false);
        btnmarcar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmarcarActionPerformed(evt);
            }
        });

        lbltext1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbltext1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltext1.setText("No podrá");

        lbltext2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbltext2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltext2.setText("Usar el Sistema");

        lbltext3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbltext3.setText("Hasta que registre ");

        lbltext4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbltext4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltext4.setText("Su entrada.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lbllogo, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbltext2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbltext1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbltext3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbltext4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(btnmarcar)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lbllogo, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(lbltext1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbltext2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbltext3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbltext4)))
                .addGap(18, 18, 18)
                .addComponent(btnmarcar)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnmarcarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmarcarActionPerformed
       
        try 
        {
            evn.write(ID_PER, "Ha registrado entrada", "JDMarcarAsistencia", "botón 'Registrar Entrada' Presionado");
            String ArrayTemp[] = mw.hour_actual().split(":");
        
            //HORA DE ENTRADA
            //he:ee = he=heH:ee=heM
            
            int heH = Integer.parseInt(ArrayTemp[0]);
            int heM = Integer.parseInt(ArrayTemp[1]);
            
            String r = comprt(CT(8, 30), CT(heH,heM));
            
            System.out.println(r);
            
            P_asist.RegistrarEntrada(NuevoCodigo(), ID_PER, mw.fecha_actual_clasica());
            P_asist.UpdateEntrada(ID_PER, mw.fecha_actual_clasica(), r);

            this.dispose();
        
            
        } catch (Exception e) 
        {
            lc.write("Ha ocurrido un error al intentar registrar la entrada", "JDMarcarAsistencia", e);
        }
        
    }//GEN-LAST:event_btnmarcarActionPerformed

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
            java.util.logging.Logger.getLogger(JDMarcarAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDMarcarAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDMarcarAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDMarcarAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run() 
            {
                JDMarcarAsistencia dialog = new JDMarcarAsistencia(new javax.swing.JFrame(), true,"");
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
    private javax.swing.JButton btnmarcar;
    private javax.swing.JLabel lbllogo;
    private javax.swing.JLabel lbltext1;
    private javax.swing.JLabel lbltext2;
    private javax.swing.JLabel lbltext3;
    private javax.swing.JLabel lbltext4;
    // End of variables declaration//GEN-END:variables
}
