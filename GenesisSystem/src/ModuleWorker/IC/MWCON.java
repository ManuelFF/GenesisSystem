/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker.IC;

import NCLPM.LOG;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class MWCON 
{
    //puentes
    LOG lc = new LOG(); //puente apuntando a la clase LOG
    
    //Metodo que abre un archivo en la raiz con el nombre *arhivo
    public void abrir_archivo(String archivo)
    {
        try 
        {
            File ObjectoFile = new File(archivo);
            Desktop.getDesktop().open(ObjectoFile);
        } catch (IOException ioex) 
            {
                lc.write("Error al intentar abrir el archivo "+archivo, "MWCON", ioex);
            }
    }
    
    //Metodo que sera usado para abrir el actualizado de GenesisSystem 
    //el cual comprobara versiones para bajar y actualizar el sistema
    public void abrir_actualizador()
    {
        abrir_archivo("Update.jar");
    }
    
   //Funcion que limpia un Jtable quitando los elemtos.
    public void clear_table(DefaultTableModel modelo, JTable jt)
    {
        for(int i=0;i<jt.getRowCount();i++)
        {
            modelo.removeRow(i);
            i-=1;
        }
        modelo.setRowCount(0);
        modelo.setColumnCount(0);
        //jt.setModel(modelo);
    }
    
    //Metodo que devuelve la fecha actual con el formato dd/MM/yyyy
    public String fecha_actual()
    {
        Date date = new Date();
        
        DateFormat año_current = new SimpleDateFormat("yyyy");
        DateFormat dia_current = new SimpleDateFormat("dd");
        DateFormat mes_current = new SimpleDateFormat("MM");
        
        String compl = ""+dia_current.format(date)+"/"+""+mes_current.format(date)+"/"+""+año_current.format(date);
        return compl;
    }
    
    public String hour_actual()
    {
        Calendar calendario = new GregorianCalendar();
        int hora, minutos, segundos;
        String horafor="", frt= "";

        hora =calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);
        if(calendario.get(Calendar.AM_PM)==0) {frt=frt+" AM";}
        else {frt=frt+" PM";}
        if(hora<10)
        {
            horafor = "0"+hora;
        }else{horafor = ""+hora;}
        
        
        return horafor + ":" + minutos + ":" + segundos+frt;
    }
    
    //Metodo que devuelve el boton que esta siendo presionado dentro de un 
    //ButtonGroup
    public String getSelectedButtonText(ButtonGroup buttonGroup) 
    {
      for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) 
      {
          AbstractButton button = buttons.nextElement();
            if (button.isSelected()) 
            {
                return button.getText();
            }
      }
        return null;
    }
            
   
    
    //MARCAR ENTRADA
    //MARCAR SALIDA
    //MARCAR SALIDA_ESTADO
    //ELIMINAR ASISTENCIA
    //INSERTAR ASISTENCIA
    //OBTENER ESTADO ASISTENCIA
    //OBTENER ESTADO SALIDA
    //OBTENER APELLIDO
    //OBTENER TIPO DE USUARIO
    
    //---SACADOS DE CONECTORDeBASE---
    //CARGAR ASISTENCIA PERSONALIZADA
    //CARGAR PERSONAL
    //CARGAR CATEGORIA
    
    
}
