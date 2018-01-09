/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Generar.IC;

import ModuleWorker.DBCON;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author USUARIO
 */
public class CO_GenerarOrdenServicio
{



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



    
}
