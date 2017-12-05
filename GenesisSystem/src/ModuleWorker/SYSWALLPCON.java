/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.border.Border;


/**
 *
 * @author USUARIO
 */
public class SYSWALLPCON implements Border
{
    private final BufferedImage image;
    
    public SYSWALLPCON(BufferedImage image)
    {
       this.image=image;
    }
    
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
        int x0 = x+ (width-image.getWidth())/2;
        int y0 = y+ (height-image.getHeight())/2;
        g.drawImage(image, x0, y0, null);
    }
    
    @Override
    public Insets getBorderInsets(Component c)
    {
        return new Insets(0,0,0,0);
    }
    
    @Override
    public boolean isBorderOpaque()
    {
        return true;
    }
    
    public static void cargarImagen(javax.swing.JDesktopPane jDeskp,File fileImagen) 
    {
        try
        {
            BufferedImage imagen = ImageIO.read(fileImagen);
            jDeskp.setBorder(new SYSWALLPCON(imagen));
        } catch (IOException e) 
        {
            JOptionPane.showMessageDialog(null, "Error al cargar imagen de fondo, ¿Puso la imagen en la carpeta principal?",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
}
