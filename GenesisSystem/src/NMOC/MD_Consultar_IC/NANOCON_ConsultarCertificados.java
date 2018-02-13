/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Consultar_IC;

import ModuleWorker.DBCON;
import NCLPM.LOG;
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
public class NANOCON_ConsultarCertificados 
{
    LOG lc = new LOG();
    private ResultSet rs;
    private PreparedStatement st;
    
    public void ConsultarCertificado(DefaultTableModel modelo, JTable Jta,String ID_CLI)         
    {
        try{
            DBCON RCN = new DBCON();

            Statement smt=RCN.conector().createStatement();
            ResultSet rs= smt.executeQuery
                                (
                                  "select crtV2.ID_CERT CODIGO, crtV2.NUMERO_CERT CERTIFICADO, crtV2.RAZ_SOCIAL, crtV2.GIRO, TO_CHAR(FECH_SERV, 'DD/MM/YYYY') FECHA_SERV, TO_CHAR(FECH_VEN, 'DD/MM/YYYY') FECH_VEN,crtV2.costo, crtV2.NUMERO_ORDEN ORDEN from CERTIFICADOV2 crtV2  where crtV2.ID_CLI = "+"'"+ID_CLI+"' order by crtV2.ID_CERT"
                                );

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
                lc.write("Problema al Cargar Datos en el metodo 'ConsultarCertificado'", "MICROCON_ConsultaCertificados", sqlex);
            }
        try {
        } catch (Exception ex)
            {
                lc.write("Error no controlado en el metodo 'ConsultarCertificado'", "MICROCON_ConsultaCertificados", ex);
            }
    }
    
    public void CargarCertificadoVencidos(DefaultTableModel modelo, JTable Jta,String fech_ven1,String fech_ven2)         
    {
        try{
            DBCON RCN = new DBCON();

            Statement smt=RCN.conector().createStatement();
            ResultSet rs= smt.executeQuery
                                (
                                  "select cvrt2.ID_CERT CÓDIGO, cvrt2.NUMERO_CERT,cvrt2.RAZ_SOCIAL RAZON, cvrt2.GIRO, "
                                          + "cvrt2.AREA_TRATADA||' '||cvrt2.FORMATO AREA, TO_CHAR(cvrt2.FECH_SERV, 'DD/MM/YYYY') FECH_SERV, "
                                          + " TO_CHAR(cvrt2.FECH_VEN, 'DD/MM/YYYY') FECH_VEN ,cvrt2.TELF,cvrt2.COSTO, cvrt2.UBICACION DIRECCION,cvrt2.ESTADO from CERTIFICADOV2 cvrt2 "
                                          + "where fech_ven between "+"'"+fech_ven1+"' and "+"'"+fech_ven2+"' order by cvrt2.NUMERO_CERT"
                                );

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
                lc.write("Problema al Cargar Datos en el metodo 'ConsultarCertificado'", "MICROCON_ConsultaCertificados", sqlex);
            }
        try {
        } catch (Exception ex)
            {
                lc.write("Error no controlado en el metodo 'ConsultarCertificado'", "MICROCON_ConsultaCertificados", ex);
            }
    }
    
    public String img(String ID_CLI)         
    {
        try{
            DBCON RCN = new DBCON();

            st=RCN.conector().prepareStatement("select crtV2.CERT_ESCAN CERT_ESCAN from CERTIFICADOV2 crtV2 where crtV2.ID_CERT = "+"'"+ID_CLI+"' order by crtV2.ID_CERT");
            rs=st.executeQuery();
            
            String escan = null;
            
            while (rs.next())
            {            
              escan = rs.getString("CERT_ESCAN");
            }
            System.out.println(escan);
            return escan;
            
        }catch(SQLException sqlex)
            {
                lc.write("Problema al Cargar Datos en el metodo 'img'", "MICROCON_ConsultaCertificados", sqlex);
            }
        try 
        {
        } catch (Exception ex)
            {
                lc.write("Error no controlado en el metodo 'img'", "MICROCON_ConsultaCertificados", ex);
            }
        return null;
    }
    
    
    public void ModificarCertificado_ESTADO(String ID_CERT, String ESTADO)         
    {
        try{
            DBCON RCN = new DBCON();

            st=RCN.conector().prepareStatement("update CERTIFICADOV2 set estado = "+"'"+ESTADO+" where ID_CERT = "+"'"+ID_CERT+"'");
            rs=st.executeQuery();
      
        }catch(SQLException sqlex)
            {
                lc.write("Problema al intentar modificar estado Datos en el metodo 'ModificarCertificado_ESTADO'", "MICROCON_ConsultaCertificados", sqlex);
            }
        try 
        {
        } catch (Exception ex)
            {
                lc.write("Error no controlado en el metodo 'ModificarCertificado_ESTADO'", "MICROCON_ConsultaCertificados", ex);
            }
        
    }
    
}
