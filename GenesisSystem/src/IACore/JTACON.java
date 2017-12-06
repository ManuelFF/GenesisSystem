/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IACore;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;

/**
 *
 * @author USUARIO
 */
public class JTACON 
{
    //Limpia un JtextArea
    public void clear(JTextArea jta)
    {
        jta.setText("");
    }
    
    //Establece el tamaño de letra de un JtextArea
    public void font_size(JTextArea jta,int size)
    {
        Font font = new Font(jta.getFont().getFontName(), jta.getFont().getStyle(),size);
        jta.setFont(font);
    }
    
    //Establece la fuente que se usara en un JtextArea    
    public void font(JTextArea jta, String fontSytle, int size)
    {
        Font font = new Font(fontSytle,jta.getFont().getStyle(),size);
        jta.setFont(font);
    }
    
    //Establece el color de letra de un JtextArea
    public void Font_color(JTextArea jta, Color c)
    {
        jta.setForeground(c);
    }
    
    //Establece BOLD (NEGRITA) a la fuente en un JtextArea
    public void bold(JTextArea jta)
    {
        Font font = new Font(jta.getFont().getFontName(),Font.BOLD,jta.getFont().getSize());
        jta.setFont(font);
    }
    
    //Escribe dentro de un JtextArea
    public void msj(JTextArea jta,String msj)
    {
        jta.append(msj);
    }
    
    //Establece el color de fondo de un JtextArea
    public void Color(JTextArea jta,Color c)
    {
        jta.setBackground(c);
    }
    
    //Establece la cabecera inicial de un JtextArea
    public void cabecera(JTextArea jta)
    {
        clear(jta);
        msj(jta, "Génesis\n\n");
    }
    
}
