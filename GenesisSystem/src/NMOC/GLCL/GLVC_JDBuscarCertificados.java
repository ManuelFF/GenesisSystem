/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.GLCL;

import ModuleWorker.DBCON;
import NCLPM.LOG;
import NMOC.MD_Generar.View.JIFGenerarCertificado;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class GLVC_JDBuscarCertificados
{
    
    private ResultSet rs;
    private PreparedStatement st;
    LOG lc = new LOG();
    
    public void iniciar(String ID_Cert)
    {
      CargarDetalle_General(ID_Cert);
      cargarServicios(ID_Cert);
    }

    public void CargarCertJ(DefaultTableModel modelo, JTable Jta)         
    {
        try{
            DBCON RCN = new DBCON();

            Statement smt=RCN.conector().createStatement();
            ResultSet rs= smt.executeQuery("SELECT * from V_BuscarCertJ");
            ResultSetMetaData md=rs.getMetaData();
            int columnas= md.getColumnCount();
            for (int i = 1; i <= columnas; i++)
             {
               modelo.addColumn(md.getColumnLabel(i));
             }
            while(rs.next())
            {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) {fila[i]=rs.getObject(i+1);}
                modelo.addRow(fila);
            }Jta.setModel(modelo);
        }catch(SQLException sqlex)
            {
                lc.write("Problema al Cargar Datos en el metodo 'CargarCertJ'", "GLVC_JDBuscarCertificados", sqlex);
            }
        try {
        } catch (Exception ex)
            {
                lc.write("Error no controlado en el metodo 'CargarCertJ'", "GLVC_JDBuscarCertificados", ex);
            }
    }
    
    public void CargarCertN(DefaultTableModel modelo, JTable Jta)         
    {
        try{
            DBCON RCN = new DBCON();

            Statement smt=RCN.conector().createStatement();
            ResultSet rs= smt.executeQuery("SELECT * from V_BuscarCertN");
            ResultSetMetaData md=rs.getMetaData();
            int columnas= md.getColumnCount();
            for (int i = 1; i <= columnas; i++)
             {
               modelo.addColumn(md.getColumnLabel(i));
             }
            while(rs.next())
            {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) {fila[i]=rs.getObject(i+1);}
                modelo.addRow(fila);
            }Jta.setModel(modelo);
        }catch(SQLException sqlex)
            {
                lc.write("Problema al Cargar Datos en el metodo 'CargarCertN'", "GLVC_JDBuscarCertificados", sqlex);
            }
        try {
        } catch (Exception ex)
            {
                lc.write("Error no controlado en el metodo 'CargarCertN'", "GLVC_JDBuscarCertificados", ex);
            }
    }
        
    //SERVICIOS
    public void cargarServicios(String ID_CERT)
    {
      try
      {  
       
        DBCON RCN = new DBCON();
        st=RCN.conector().prepareStatement("select DISTINCT ID_SERV from DET_CERTIFICADOV2 where ID_CERT = "+"'"+ID_CERT+"'");
        
        rs=st.executeQuery();
        
        while (rs.next())
        {
          String idserv= rs.getString("ID_SERV");
          
          if(idserv.equals("SERV-001")){JIFGenerarCertificado.jcdesinsectacion.setSelected(true);}
          if(idserv.equals("SERV-002")){JIFGenerarCertificado.jcdesratizacion.setSelected(true);}
          if(idserv.equals("SERV-003")){JIFGenerarCertificado.jcDesinfeccion.setSelected(true);}
          if(idserv.equals("SERV-004")){JIFGenerarCertificado.jclimpiezareservorios.setSelected(true);}
          if(idserv.equals("SERV-005")){JIFGenerarCertificado.jclimpiezaAmbientes.setSelected(true);}
          if(idserv.equals("SERV-006")){JIFGenerarCertificado.jclimpiezaTanques.setSelected(true);}
          
        }
        
      }catch (SQLException sqle) 
            {
               lc.write( "La base de datos no retorno conexion!","GLVC_JDBuscarCertificados -> metodo cargarServicios", sqle);
            }
        catch (Exception e)
            {
               lc.write( "ha ocurrido algun error no controlado","GLVC_JDBuscarCertificados -> metodo cargarServicios", e);
            }
    }
    

    //CARGAR DETALLES
    public void CargarDetalle_General(String ID_CERT)
    {
     try
      {  
        DBCON RCN = new DBCON();       
        
        st=RCN.conector().prepareStatement( "select ID_CERT,NUMERO_CERT,RAZ_SOCIAL,UBICACION,GIRO,AREA_TRATADA,FORMATO, TO_CHAR(FECH_SERV, 'DD/MM/YYYY') FECHA_SERV , TO_CHAR(FECH_VEN, 'DD/MM/YYYY') FECH_VEN ,TELF,COSTO,PERIODO,DETALLES,ID_CLI,NUMERO_ORDEN,CERT_ESCAN, ESTADO from CERTIFICADOV2 where ID_CERT = "+"'"+ID_CERT+"'");
        rs=st.executeQuery();
        
        while (rs.next())
        {
          
          JIFGenerarCertificado.txtidCert.setText(rs.getString("ID_CERT"));
          JIFGenerarCertificado.txtnumeroCert.setText(rs.getString("NUMERO_CERT"));
          JIFGenerarCertificado.cbestado.setSelectedItem(rs.getString("ESTADO"));
          JIFGenerarCertificado.txtrazonSocial.setText(rs.getString("RAZ_SOCIAL"));
          JIFGenerarCertificado.txtgiro.setText(rs.getString("GIRO"));
          JIFGenerarCertificado.txtareatotal.setText(rs.getString("AREA_TRATADA"));
          JIFGenerarCertificado.cbformato.setSelectedItem(rs.getString("FORMATO"));
          JIFGenerarCertificado.txtfechaServicio.setText(rs.getString("FECHA_SERV"));
          JIFGenerarCertificado.txtfechaVencimiento.setText(rs.getString("FECH_VEN"));
          JIFGenerarCertificado.txtidCli.setText(rs.getString("ID_CLI"));
          JIFGenerarCertificado.txtnumeroOrden.setText(rs.getString("NUMERO_ORDEN"));
          JIFGenerarCertificado.adjunt = ""+rs.getString("CERT_ESCAN");
          JIFGenerarCertificado.lblcertificadoAsignado.setText("Adjuntado con exito!");JIFGenerarCertificado.lblcertificadoAsignado.setForeground(Color.GREEN);
          JIFGenerarCertificado.txttelefono.setText(rs.getString("TELF"));
          JIFGenerarCertificado.txtcosto.setText(rs.getString("COSTO"));
          JIFGenerarCertificado.txtperiodo.setText(rs.getString("PERIODO"));
          JIFGenerarCertificado.JTADIRECCION.setText(rs.getString("UBICACION"));
          JIFGenerarCertificado.JTADET.setText(rs.getString("DETALLES"));

        }
        
      }catch (SQLException sqle) 
            {
               lc.write( "La base de datos no retorno conexion!","GLVC_JDBuscarCertificados -> metodo CargarDetalle_General", sqle);
            }
        catch (Exception e)
            {
               lc.write( "ha ocurrido algun error no controlado","GLVC_JDBuscarCertificados -> metodo CargarDetalle_General", e);
            } 
    }
    
    
    
}
