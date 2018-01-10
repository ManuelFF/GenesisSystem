/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Generar.IC;

import ModuleWorker.DBCON;
import NCLPM.LOG;
import NMOC.MD_Generar.Core.O_OrdenServicio;
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
public class CO_GenerarOrdenServicio
{
    private ResultSet rs;
    private PreparedStatement st;
    LOG lc = new LOG();

    //Inicializador (CONSTRUCTOR)
    public CO_GenerarOrdenServicio()
    {
        L_ORDEN = new ArrayList();
        llenarDatos();
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
            O_OrdenServicio ultObjeto = null;
            ultObjeto=obtener(tamaño()-1);
            cod=ultObjeto.getNumero_orden();
            return cod; 
        }
    }
    
    
    //ArrayList controlador
    private ArrayList<O_OrdenServicio> L_ORDEN;
    
    //GET
    public ArrayList<O_OrdenServicio> getL_ORDEN() 
    {
        return L_ORDEN;
    }
    
    //SET

    public void setL_ORDEN(ArrayList<O_OrdenServicio> L_ORDEN) 
    {
        this.L_ORDEN = L_ORDEN;
    }
    
    //ADD OBJECT
    public void A_Objeto(O_OrdenServicio p)
    {
        L_ORDEN.add(p);
    }
    
    //RETURN SIZE
    public int tamaño()
    {
        return L_ORDEN.size();
    }
    
    //Recibe pos y retorna obj
    public O_OrdenServicio obtener(int pos)
    {
        return L_ORDEN.get(pos);
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
                
                O_OrdenServicio ord = new O_OrdenServicio(ID_ORDEN, NUMERO_ORDEN, ID_CLI, FECHA, HORA, DIRECCION, GIRO_LUGAR, TELEFONO, CELULAR, DESC_DOC, ID_PER, COSTO, ESTADO);
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
