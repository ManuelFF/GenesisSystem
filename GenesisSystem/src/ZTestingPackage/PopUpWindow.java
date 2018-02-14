/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ZTestingPackage;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author USUARIO
 */
public class PopUpWindow 
{

    private static javax.swing.JLabel lblnoti;

    public static void main(final String[] args) 
    {
        final JFrame parent = new JFrame();
        
        parent.setUndecorated(true);

        
        lblnoti = new JLabel("<html><body><CENTER>Se cerrará en..<br>100</CENTER></body></html>");
        
        JLabel lblparrfo1 = new JLabel("El Sistema");
        JLabel lblparrfo2 = new JLabel("Cerrará en: ");
        
        lblnoti.setFont(new java.awt.Font("Tahoma", 1, 35)); // NOI18N
        
        lblparrfo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblparrfo2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        
        lblnoti.setForeground(new java.awt.Color(0, 0, 255));
        
        lblparrfo1.setForeground(new java.awt.Color(0, 0, 255));
        lblparrfo2.setForeground(new java.awt.Color(0, 0, 255));
        
        lblnoti.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        
        parent.setAlwaysOnTop(true);
        parent.setDefaultCloseOperation(0);
        parent.setSize(800, 600);
        
        
        
        parent.add(lblparrfo1);
        parent.add(lblparrfo2);
        parent.add(lblnoti);
        parent.pack();
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        
        int x = (int) rect.getMaxX() - parent.getWidth();
        int y = (int) rect.getMaxY() - parent.getHeight();
        
        int yx = (int) rect.getMaxY() - y ;
        
        System.out.println("X : "+x);
        System.out.println("Y : "+y);
        System.out.println("yx : "+yx);
        
        parent.setLocation(x, yx);
        parent.setVisible(true);
        
        
    }
}
