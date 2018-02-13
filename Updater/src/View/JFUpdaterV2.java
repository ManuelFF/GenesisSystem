/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import IC.UpdaterController;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
/**sss
 *
 * @author ANONIMO
 */
public class JFUpdaterV2 extends javax.swing.JFrame 
{

    //PUENTE
    UpdaterController P_update = new UpdaterController();
    
    public JFUpdaterV2() 
    {
        initComponents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setCursor(Cursor.WAIT_CURSOR);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setLocationRelativeTo(null);
        this.setTitle("Génesis Updater");
        this.setAlwaysOnTop(true);
        lbltitulo.setText("Actualizador de Génesis");
        buscador.start();
        comp.start();
    }
    
     int var = 0;
     Timer comp = new Timer (500, new ActionListener () 
     { 
        public void actionPerformed(ActionEvent e) 
        {
            if(estado == 0)
            {

              var = var+1;

              jpupdaterbar.setValue(var);
              lblcounter.setText(""+jpupdaterbar.getValue()+"%");
            }

            if(jpupdaterbar.getValue() == 100)
            {
               lblparrafo1.setText("La actualización se esta demorando más de lo esperado");
               lblparrafo2.setText("Espere unos segundos hasta completar la actualización");
               var =0;
            }
            
            if(estado==1)
            {
                var = 0;
                comp.stop();
            }
            
        } 
     }); 
    
    
    int estado = 0;

    Timer buscador = new Timer (400, new ActionListener () 
    { 
        public void actionPerformed(ActionEvent e) 
        {
            System.out.println("Buscando versiones");
            String ver_En_Base = P_update.Ver_SIS();
            String ver_actual = P_update.cargar_version_texto();

            if(ver_En_Base.equals(ver_actual))
            {
               estado = 1;
               
               lblparrafo1.setText("Actualización terminada!. En instantes se abrira el sistema");
               lblparrafo2.setText("automaticamente");
               
               var = var+(2*2);
               jpupdaterbar.setValue(var);
               lblcounter.setText(""+jpupdaterbar.getValue()+"%");
               
               if(jpupdaterbar.getValue()==100)
               {
                    P_update.abrir_archivo("GenesisSystem.jar");
                    System.out.println("Sistema Abierto");
                    buscador.stop();
                    System.exit(0);
               }
            }
            else
                {
                  estado=0;
                }
        } 
    }); 
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        lbltitulo = new javax.swing.JLabel();
        jpupdaterbar = new javax.swing.JProgressBar();
        lblparrafo1 = new javax.swing.JLabel();
        lblcounter = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbProgreso1 = new javax.swing.JLabel();
        lblparrafo2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 0, 0));
        setFocusTraversalPolicyProvider(true);
        setForeground(new java.awt.Color(204, 255, 204));

        lbltitulo.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lbltitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltitulo.setText("Title");

        lblparrafo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblparrafo1.setForeground(new java.awt.Color(0, 0, 255));
        lblparrafo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblparrafo1.setText("El sistema esta siendo actualizado. No cierre la ventana");

        lblcounter.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblcounter.setForeground(new java.awt.Color(255, 0, 0));
        lblcounter.setText("0%");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NIMG/logo updater.png"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(70, 70));
        jLabel1.setMinimumSize(new java.awt.Dimension(70, 70));

        lbProgreso1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lbProgreso1.setForeground(new java.awt.Color(255, 0, 0));
        lbProgreso1.setText("Progreso de la actualización:");

        lblparrafo2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblparrafo2.setForeground(new java.awt.Color(0, 153, 0));
        lblparrafo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblparrafo2.setText("Una vez finalizado el proceso de actualización el sistema se abrira automaticamente");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbltitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblparrafo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblparrafo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbProgreso1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblcounter, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpupdaterbar, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(232, 232, 232))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lbltitulo)
                .addGap(18, 18, 18)
                .addComponent(lblparrafo1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblparrafo2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpupdaterbar, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbProgreso1)
                    .addComponent(lblcounter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(JFUpdaterV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFUpdaterV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFUpdaterV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFUpdaterV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFUpdaterV2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JProgressBar jpupdaterbar;
    private javax.swing.JLabel lbProgreso1;
    private javax.swing.JLabel lblcounter;
    private javax.swing.JLabel lblparrafo1;
    private javax.swing.JLabel lblparrafo2;
    private javax.swing.JLabel lbltitulo;
    // End of variables declaration//GEN-END:variables
}
