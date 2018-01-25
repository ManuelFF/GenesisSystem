/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.GLCL;

import ModuleWorker.DBCON;
import NCLPM.LOG;
import NISPM.SGL_CL_AreaTrabajo;
import NISPM.SGL_CL_Implementos;
import NISPM.SGL_CL_Operarios;
import NISPM.SGL_CL_Productos;
import NISPM.SGL_CL_Servicios;
import NISPM.SGL_CTRL_AreaTrabajo;
import NISPM.SGL_CTRL_Implementos;
import NISPM.SGL_CTRL_Operarios;
import NISPM.SGL_CTRL_Productos;
import NISPM.SGL_CTRL_Servicios;
import NMOC.MD_Generar.View.JIFGenerarOrdenServicio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class GLVC_JDBuscarOrdenes
{
    
    private ResultSet rs;
    private PreparedStatement st;
    LOG lc = new LOG();
    ArrayList<SGL_CL_AreaTrabajo> areas = SGL_CTRL_AreaTrabajo.getInstance().getSgl_area();
    ArrayList<SGL_CL_Servicios> servicios = SGL_CTRL_Servicios.getInstance().getsgl_servicio();
    ArrayList<SGL_CL_Operarios> operarios = SGL_CTRL_Operarios.getInstance().getsgl_operario();
    ArrayList<SGL_CL_Productos> productos = SGL_CTRL_Productos.getInstance().getsgl_producto();
    ArrayList<SGL_CL_Implementos> implementos = SGL_CTRL_Implementos.getInstance().getsgl_implementos();
    
    public void iniciar(String orden, String t)
    {
        cargarAreas(orden);
        cargarServicios(orden);
        cargarProductos(orden);
        cargarImplementos(orden);
        cargarOperativos(orden);
        
        if(t.equals("Juridica"))
        {
            CargarDetalle_JURIDICOS(orden);
        }
        
        if(t.equals("Natural"))
        {
            CargarDetalle_NATURAL(orden);
        }
        
    }

    public void CargarOrdenJ(DefaultTableModel modelo, JTable Jta)         
    {
        try{
            DBCON RCN = new DBCON();

            Statement smt=RCN.conector().createStatement();
            ResultSet rs= smt.executeQuery("SELECT * from V_BuscarOrdenJ");
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
                lc.write("Problema al Cargar Datos en el metodo 'CargarOrdenJ'", "GLVC_JDBuscarOrdenes", sqlex);
            }
        try {
        } catch (Exception ex)
            {
                lc.write("Error no controlado en el metodo 'CargarOrdenJ'", "GLVC_JDBuscarOrdenes", ex);
            }
    }
    
    public void CargarOrdenN(DefaultTableModel modelo, JTable Jta)         
    {
        try{
            DBCON RCN = new DBCON();

            Statement smt=RCN.conector().createStatement();
            ResultSet rs= smt.executeQuery("SELECT * from V_BuscarOrdenN");
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
                lc.write("Problema al Cargar Datos en el metodo 'CargarOrdenN'", "GLVC_JDBuscarOrdenes", sqlex);
            }
        try {
        } catch (Exception ex)
            {
                lc.write("Error no controlado en el metodo 'CargarOrdenN'", "GLVC_JDBuscarOrdenes", ex);
            }
    }
        
    //AREAS
    public void cargarAreas(String ID_ORD)
    {
      try
      {  
        DBCON RCN = new DBCON();
        SGL_CTRL_AreaTrabajo.controller.getSgl_area();
        st=RCN.conector().prepareStatement("select DISTINCT ID_AREA,nombre_area, num_ambientes, area_trabajar, FORMATO from det_orden_serv where ID_ORDEN = "+"'"+ID_ORD+"'");
        rs=st.executeQuery();
        
        while (rs.next())
        {
          String idarea = rs.getString("ID_AREA");
          String nombreArea = rs.getString("nombre_area");
          String numeroAmbientes = rs.getString("num_ambientes");
          String areaTrabajar = rs.getString("area_trabajar");
          String formato = rs.getString("FORMATO");
          
          SGL_CL_AreaTrabajo ar = new SGL_CL_AreaTrabajo(idarea, nombreArea, numeroAmbientes, areaTrabajar, formato);
          areas.add(ar);
        }
        
      }catch (SQLException sqle) 
            {
               lc.write( "La base de datos no retorno conexion!","GLVC_JDBuscarOrdenes -> Metodo cargar Areas", sqle);
            }
        catch (Exception e)
            {
               lc.write( "ha ocurrido algun error no controlado","GLVC_JDBuscarOrdenes -> Metodo cargar Areas", e);
            }
    }
    
    //SERVICIOS
    public void cargarServicios(String ID_ORD)
    {
      try
      {  
        DBCON RCN = new DBCON();
        st=RCN.conector().prepareStatement("select DISTINCT sr.ID_SERV ID_SERV,sr.NOMBRE_SERV servicio,ds.NOTA NOTA from det_orden_serv ds inner join servicios sr on ds.ID_SERV = sr.ID_SERV where ds.ID_ORDEN = "+"'"+ID_ORD+"'");
        
        rs=st.executeQuery();
        
        while (rs.next())
        {
          String idserv= rs.getString("ID_SERV");
          String nomserv= rs.getString("servicio");
          String notaserv= rs.getString("NOTA");
                    
          SGL_CL_Servicios ar = new SGL_CL_Servicios(idserv, nomserv, notaserv);
          servicios.add(ar);
        }
        
      }catch (SQLException sqle) 
            {
               lc.write( "La base de datos no retorno conexion!","GLVC_JDBuscarOrdenes -> metodo cargarServicios", sqle);
            }
        catch (Exception e)
            {
               lc.write( "ha ocurrido algun error no controlado","GLVC_JDBuscarOrdenes -> metodo cargarServicios", e);
            }
    }
    
    public void cargarProductos(String ID_ORD)
    {
     try
      {  
        DBCON RCN = new DBCON();
        st=RCN.conector().prepareStatement("select DISTINCT pr.ID_PRO ID_PRO, pr.NOMBRE NOMBRE from det_orden_serv ds inner join producto pr on ds.ID_PRO = pr.ID_PRO where ds.ID_ORDEN = "+"'"+ID_ORD+"'");
        
        rs=st.executeQuery();
        
        while (rs.next())
        {
          String idPro = rs.getString("ID_PRO");
          String nomPro = rs.getString("NOMBRE");
          
          SGL_CL_Productos ar = new SGL_CL_Productos(idPro, nomPro);
          productos.add(ar);
        }
        
      }catch (SQLException sqle) 
            {
               lc.write( "La base de datos no retorno conexion!","GLVC_JDBuscarOrdenes -> metodo cargarProductos", sqle);
            }
        catch (Exception e)
            {
               lc.write( "ha ocurrido algun error no controlado","GLVC_JDBuscarOrdenes -> metodo cargarProductos", e);
            }
    }
    
    public void cargarImplementos(String ID_ORD)
    {
     try
      {  
        DBCON RCN = new DBCON();
        st=RCN.conector().prepareStatement("select DISTINCT pr.ID_MAQU ID_MAQU, pr.NOMBRE NOMBRE from det_orden_serv ds inner join maquina pr on ds.ID_MAQU = pr.ID_MAQU where ds.ID_ORDEN = "+"'"+ID_ORD+"'");
        
        rs=st.executeQuery();
        
        while (rs.next())
        {
          String idmaq = rs.getString("ID_MAQU");
          String nomMaq = rs.getString("NOMBRE");
          
          SGL_CL_Implementos ar = new SGL_CL_Implementos(idmaq, nomMaq);
          implementos.add(ar);
        }
        
      }catch (SQLException sqle) 
            {
               lc.write( "La base de datos no retorno conexion!","GLVC_JDBuscarOrdenes -> metodo cargarImplementos", sqle);
            }
        catch (Exception e)
            {
               lc.write( "ha ocurrido algun error no controlado","GLVC_JDBuscarOrdenes -> metodo cargarImplementos", e);
            } 
    }
    
    public void cargarOperativos(String ID_ORD)
    {
     try
      {  
        DBCON RCN = new DBCON();
        st=RCN.conector().prepareStatement("select DISTINCT emp.NOM_PER||' '||emp.APE_PER NOMBRE, emp.ID_PER ID_PER, emp.DNI_PER DNI_PER from det_orden_serv ds inner join personal_emp emp on ds.ID_PER = emp.ID_PER where ds.ID_ORDEN = "+"'"+ID_ORD+"'");
        
        rs=st.executeQuery();
        
        while (rs.next())
        {
          String idope=rs.getString("ID_PER");
          String nomope=rs.getString("NOMBRE");
          String DNIOpe=rs.getString("DNI_PER");
            
          SGL_CL_Operarios ar = new SGL_CL_Operarios(idope, nomope, DNIOpe);
          operarios.add(ar);
        }
        
      }catch (SQLException sqle) 
            {
               lc.write( "La base de datos no retorno conexion!","GLVC_JDBuscarOrdenes -> metodo cargarOperativos", sqle);
            }
        catch (Exception e)
            {
               lc.write( "ha ocurrido algun error no controlado","GLVC_JDBuscarOrdenes -> metodo cargarOperativos", e);
            } 
    }
    
    public void CargarDetalle_JURIDICOS(String ID_ORD)
    {
     try
      {  
        DBCON RCN = new DBCON();       
        
        st=RCN.conector().prepareStatement( "select os.ID_ORDEN, os.NUMERO_ORDEN, os.fecha,os.hora,os.giro_lugar,os.telefono,os.celular,os.desc_doc,os.costo, emp.ID_PER ID_PER, emp.NOM_PER||' '||emp.APE_PER NOMBRE , os.ID_CLI ID_CLI, cj.RAZON_SOCIAL RAZON_SOCIAL,os.DIRECCION DIRECCION\n" +
                                            "from orden_serv os \n" +
                                            "inner join personal_emp emp on os.ID_PER = emp.ID_PER\n" +
                                            "inner join cliente_juridico cj on os.ID_CLI = cj.ID_CLI where os.ID_ORDEN = "+"'"+ID_ORD+"'");
        rs=st.executeQuery();
        
        while (rs.next())
        {

          JIFGenerarOrdenServicio.telefono = ""+rs.getString("TELEFONO");
          JIFGenerarOrdenServicio.celular = ""+rs.getString("CELULAR");
            
          JIFGenerarOrdenServicio.txtidorden.setText(rs.getString("ID_ORDEN"));
          JIFGenerarOrdenServicio.txtnumeroorden.setText(rs.getString("NUMERO_ORDEN"));
          JIFGenerarOrdenServicio.txtfecha.setText(rs.getString("fecha"));
          JIFGenerarOrdenServicio.txthora.setText(rs.getString("hora"));
          JIFGenerarOrdenServicio.txtgiro.setText(rs.getString("giro_lugar"));
          JIFGenerarOrdenServicio.txtdocumentacion.setText(rs.getString("desc_doc"));
          JIFGenerarOrdenServicio.txtcosto.setText(rs.getString("costo"));
          JIFGenerarOrdenServicio.txtcostofinal.setText(rs.getString("costo"));
          JIFGenerarOrdenServicio.txtidVendedor.setText(rs.getString("ID_PER"));
          JIFGenerarOrdenServicio.txtnombrevendedor.setText(rs.getString("NOMBRE"));
          JIFGenerarOrdenServicio.txtcodCliente.setText(rs.getString("ID_CLI"));
          
          JIFGenerarOrdenServicio.txtnombrecliente.setText(rs.getString("RAZON_SOCIAL"));
          JIFGenerarOrdenServicio.txtdireccion.setText(rs.getString("DIRECCION"));

        }
        
      }catch (SQLException sqle) 
            {
               lc.write( "La base de datos no retorno conexion!","GLVC_JDBuscarOrdenes -> metodo CargarDetalle", sqle);
            }
        catch (Exception e)
            {
               lc.write( "ha ocurrido algun error no controlado","GLVC_JDBuscarOrdenes -> metodo CargarDetalle", e);
            } 
    }
    
    public void CargarDetalle_NATURAL(String ID_ORD)
    {
     try
      {  
        DBCON RCN = new DBCON();       
        
        st=RCN.conector().prepareStatement( "select os.ID_ORDEN, os.NUMERO_ORDEN, os.fecha,os.hora,os.giro_lugar,os.telefono,os.celular,os.desc_doc,os.costo, emp.ID_PER, emp.NOM_PER||' '||emp.APE_PER NOMBRE , os.ID_CLI ID_CLI, cj.NOMBRES||' '||cj.APE_PAT||' '||cj.APE_MAT NOM_CLI ,os.DIRECCION DIRECCION\n" +
                                            "from orden_serv os \n" +
                                            "inner join personal_emp emp on os.ID_PER = emp.ID_PER\n" +
                                            "inner join cliente_natural cj on os.ID_CLI = cj.ID_CLI where os.ID_ORDEN = "+"'"+ID_ORD+"'");
        rs=st.executeQuery();
        
        while (rs.next())
        {

          JIFGenerarOrdenServicio.txtidorden.setText(rs.getString("ID_ORDEN"));
          JIFGenerarOrdenServicio.txtnumeroorden.setText(rs.getString("NUMERO_ORDEN"));
          JIFGenerarOrdenServicio.txtfecha.setText(rs.getString("fecha"));
          JIFGenerarOrdenServicio.txthora.setText(rs.getString("hora"));
          JIFGenerarOrdenServicio.txtgiro.setText(rs.getString("giro_lugar"));
          JIFGenerarOrdenServicio.txtdocumentacion.setText(rs.getString("desc_doc"));
          JIFGenerarOrdenServicio.txtcosto.setText(rs.getString("costo"));
          JIFGenerarOrdenServicio.txtcostofinal.setText(rs.getString("costo"));
          JIFGenerarOrdenServicio.txtidVendedor.setText(rs.getString("ID_PER"));
          JIFGenerarOrdenServicio.txtnombrevendedor.setText(rs.getString("NOMBRE"));
          JIFGenerarOrdenServicio.txtcodCliente.setText(rs.getString("ID_CLI"));
          
          JIFGenerarOrdenServicio.txtnombrecliente.setText(rs.getString("NOM_CLI"));
          JIFGenerarOrdenServicio.txtdireccion.setText(rs.getString("DIRECCION"));

        }
        
      }catch (SQLException sqle) 
            {
               lc.write( "La base de datos no retorno conexion!","GLVC_JDBuscarOrdenes -> metodo CargarDetalle", sqle);
            }
        catch (Exception e)
            {
               lc.write( "ha ocurrido algun error no controlado","GLVC_JDBuscarOrdenes -> metodo CargarDetalle", e);
            } 
    }
    
    
}
