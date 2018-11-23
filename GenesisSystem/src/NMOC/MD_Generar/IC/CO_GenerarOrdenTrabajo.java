/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Generar.IC;

import ModuleWorker.DBCON;
import NCLPM.LOG;
import NMOC.MD_Generar.Core.O_OrdenTrabajo;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class CO_GenerarOrdenTrabajo
{
    private ResultSet rs;
    private PreparedStatement st;
    LOG lc = new LOG();

    //Inicializador (CONSTRUCTOR)
    public CO_GenerarOrdenTrabajo()
    {
        L_ORDEN = new ArrayList();
        llenarDatos();
    }
   
    //inserta la forma basica de la orden de servicio
    public void Insertar_orden(String ID_ORDEN, String numero_orden, String id_cli,String fecha,String hora,String direccion,String giroLugar,String telefono,String celular,String desc_doc,String id_per,String costo,String estado )
    {
        try 
        {
            
            DBCON RCN = new DBCON();
            CallableStatement cst = RCN.conector().prepareCall("{call usp_insertar_orden(?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            cst.setString(1, ID_ORDEN);
            cst.setString(2, numero_orden);
            cst.setString(3, id_cli);
            cst.setString(4, fecha);
            cst.setString(5, hora);
            cst.setString(6, direccion);
            cst.setString(7, giroLugar);
            cst.setString(8, telefono);
            cst.setString(9, celular);
            cst.setString(10, desc_doc);
            cst.setString(11, id_per);
            cst.setString(12, costo);    
            cst.setString(13, estado);    

            cst.execute();
            cst.execute();

        } catch (SQLException ex)
            {
                System.out.println("Error: " + ex.getMessage());
            } finally {
            
                      }    
    }
    
    //Actualiza la forma basica de la orden de servicio
    public void Actualizar_orden(String ID_ORDEN, String numero_orden, String id_cli,String fecha,String hora,String direccion,String giroLugar,String telefono,String celular,String desc_doc,String id_per,String costo,String estado )
    {
        try 
        {
            
            DBCON RCN = new DBCON();
            CallableStatement cst = RCN.conector().prepareCall("{call usp_modificar_orden(?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            cst.setString(1, ID_ORDEN);
            cst.setString(2, numero_orden);
            cst.setString(3, id_cli);
            cst.setString(4, fecha);
            cst.setString(5, hora);
            cst.setString(6, direccion);
            cst.setString(7, giroLugar);
            cst.setString(8, telefono);
            cst.setString(9, celular);
            cst.setString(10, desc_doc);
            cst.setString(11, id_per);
            cst.setString(12, costo);    
            cst.setString(13, estado);    

            cst.execute();
            cst.execute();

        } catch (SQLException ex)
            {
                System.out.println("Error: " + ex.getMessage());
            } finally {
            
                      }    
    }

    
    
    //Insertar la forma detallada de la orden de servicio
    public void InsertarDETOrden(String ID_ORDEN, int det, String idpro,String idmaq, String idop,String idserv,String nota,String ID_AREA ,String nomArea, String areaTrab,String numabi, String areatotM2, String areatotM3, String formato)
    {

       try 
           {
            DBCON RCN = new DBCON();
            // Llamada al procedimiento almacenado
            CallableStatement cst = RCN.conector().prepareCall("{call usp_insertar_det_orden(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
           // do {

            // Parametro del procedimiento almacenado
            cst.setString(1, ID_ORDEN);
            cst.setInt(2, det);
            cst.setString(3, idpro);
            cst.setString(4, idmaq);
            cst.setString(5, idop);
            cst.setString(6, idserv);
            cst.setString(7, nota);
            cst.setString(8, ID_AREA);
            cst.setString(9, nomArea);
            cst.setString(10, areaTrab);
            cst.setString(11, numabi);
            cst.setString(12, areatotM2);
            cst.setString(13, areatotM3);
            cst.setString(14, formato);

            // Ejecuta el procedimiento almacenado
            cst.execute();
            cst.close();
        } 
         catch (SQLException ex)
          {
            System.out.println("Error: " + ex.getMessage());
          } finally 
            {

            }    
    }
    
    //Actualizar la forma detallada de la orden de servicio
    public void ActualizarDETOrden(String ID_ORDEN)
    {

       try 
           {
            DBCON RCN = new DBCON();
            // Llamada al procedimiento almacenado
            CallableStatement cst = RCN.conector().prepareCall("{call usp_modificar_det_orden(?)}");
           // do {

            // Parametro del procedimiento almacenado
            cst.setString(1, ID_ORDEN);
           
            // Ejecuta el procedimiento almacenado
            cst.execute();
            cst.close();
        } 
         catch (SQLException ex)
          {
            System.out.println("Error: " + ex.getMessage());
          } finally 
            {

            }    
    }
    
    
    
    
    //Busca porcentaje cuando recibe un codigo manejado por  crystalgen
    public int BuscarPorcentaje(String codigo)
    {
        try 
        {
            DBCON RCN = new DBCON();
            CallableStatement cst = RCN.conector().prepareCall("{?=call F_retornar_porcentaje(?)}");
            cst.setString(2, codigo);
            cst.registerOutParameter(1, Types.INTEGER);
            cst.execute();
            cst.execute();
            int resultado = cst.getInt(1);
            return resultado;

        } catch (SQLException ex)
            {
                System.out.println("Error: " + ex.getMessage());
            } finally {
                      }    
        return 0;
    } 

    //##########################################################################
    //ESPACIO ARRAYLIST Y OTROS METODOS QUE INTERACTUAN CON LA BASE DE DATOS

    public String R_ultimaOrden()
    {
        String cod;
        if(getL_ORDEN().isEmpty())
        {
            return "SIN ÚLTIMA ORDEN";
        }
        else
        {
            O_OrdenTrabajo ultObjeto = null;
            ultObjeto=obtener(tamaño()-1);
            cod=ultObjeto.getNumero_orden();
            return cod; 
        }
    }
    
    
    //ArrayList controlador
    private ArrayList<O_OrdenTrabajo> L_ORDEN;
    
    //GET
    public ArrayList<O_OrdenTrabajo> getL_ORDEN() 
    {
        return L_ORDEN;
    }
    
    //SET

    public void setL_ORDEN(ArrayList<O_OrdenTrabajo> L_ORDEN) 
    {
        this.L_ORDEN = L_ORDEN;
    }
    
    //ADD OBJECT
    public void A_Objeto(O_OrdenTrabajo p)
    {
        L_ORDEN.add(p);
    }
    
    //RETURN SIZE
    public int tamaño()
    {
        return L_ORDEN.size();
    }
    
    //Recibe pos y retorna obj
    public O_OrdenTrabajo obtener(int pos)
    {
        return L_ORDEN.get(pos);
    }
    
    //CLEAR
    public void Clear()
    {
        L_ORDEN.clear();
    }
    
    //LLENA ARRAYLIST
    public final void llenarDatos()
    {
       try
        {   
            DBCON RCN = new DBCON();
            L_ORDEN.clear();
            st=RCN.conector().prepareStatement("SELECT ID_ORDEN,NUMERO_ORDEN,ID_CLI,FECHA,HORA,DIRECCION,GIRO_LUGAR,TELEFONO,CELULAR,DESC_DOC,ID_PER,COSTO,ESTADO FROM ORDEN_SERV order by ID_ORDEN ");
            rs=st.executeQuery();

            while (rs.next())
            {            
                String ID_ORDEN = rs.getString("ID_ORDEN");
                String NUMERO_ORDEN = rs.getString("NUMERO_ORDEN");
                String ID_CLI = rs.getString("ID_CLI");
                String FECHA = rs.getString("FECHA");
                String HORA = rs.getString("HORA");
                String DIRECCION = rs.getString("DIRECCION");
                String GIRO_LUGAR = rs.getString("GIRO_LUGAR");
                String TELEFONO = rs.getString("TELEFONO");
                String CELULAR = rs.getString("CELULAR");
                String DESC_DOC = rs.getString("DESC_DOC");
                String ID_PER = rs.getString("ID_PER");
                String COSTO = rs.getString("COSTO");
                String ESTADO = rs.getString("ESTADO");
                
                O_OrdenTrabajo ord = new O_OrdenTrabajo(ID_ORDEN, NUMERO_ORDEN, ID_CLI, FECHA, HORA, DIRECCION, GIRO_LUGAR, TELEFONO, CELULAR, DESC_DOC, ID_PER, COSTO, ESTADO);
                A_Objeto(ord);
            }
            RCN.conector().close();

        }
        catch (SQLException sqle) 
            {
               lc.write( "La base de datos no retorno conexion!","CO_GenerarOrdenServicio", sqle);
            }
        catch (Exception e)
            {
               lc.write( "ha ocurrido algun error no controlado","CO_GenerarOrdenServicio", e);
            }
        
    }
    

    
}
