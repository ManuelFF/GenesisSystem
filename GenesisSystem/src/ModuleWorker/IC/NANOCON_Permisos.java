/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker.IC;

import ModuleWorker.DBCON;
import NCLPM.LOG;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class NANOCON_Permisos 
{

    LOG lc = new LOG(); //puente apuntando a la clase LOG
    
    
    public void CargarUsuariosPermisos(DefaultTableModel modelo, JTable Jta)         
    {
        try{
            DBCON RCN = new DBCON();
            
            Statement smt=RCN.conector().createStatement();
            ResultSet rs= smt.executeQuery("SELECT * from V_Seleccion_Usuario_Permisos");
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
                lc.write("Problema al Cargar Datos en el metodo 'CargarSeleccionPersonal'", "MICROCON_MantenerUsuario", sqlex);
            }
        try {
        } catch (Exception ex)
            {
                lc.write("Error no controlado en el metodo 'CargarSeleccionPersonal'", "MICROCON_MantenerUsuario", ex);
            }
    }
    
    
    //COMPROBAR PERMISO EN USUARIO(SI EXISTE O NO )
    public String Comprueba_Permiso_Usuario(String idusr, String PERMISO)
    {
        try 
        {
            DBCON db = new DBCON();
                                    
            CallableStatement cst = db.conector().prepareCall("{?=call F_CompruebaPermisosUsuario(?,?)}");
            cst.setString(2, idusr);
            cst.setString(3, PERMISO);
            cst.registerOutParameter(1, Types.VARCHAR);
            cst.execute();
            String resultado = cst.getString(1);
            
            return resultado;
        } catch (SQLException sqle) 
        {
            lc.write( "La base de datos retorno error en la conexion!","Comprueba_Permiso_Usuario", sqle);
            return "ERROR";
        } 
    }
    
    //UPDATEA PERMISOS
    public void UpdateaPermisos(String idUser,String prm, String estado)
    {
        try {
                 DBCON RCN = new DBCON();

                // Llamada al procedimiento almacenado
                CallableStatement cst = RCN.conector().prepareCall("{call usp_updatear_permisos(?,?,?)}");
                // Parametro del procedimiento almacenado
                cst.setString(1, idUser);
                cst.setString(2, prm);
                cst.setString(3, estado);
                // Ejecuta el procedimiento almacenado
                cst.execute();
                cst.close();

            } catch (SQLException ex) 
                {
                     lc.write("Problema al intentar insertar un usuario en el metodo 'InsertarUsuario'", "MICROCON_MantenerUsuario linea 42", ex);             
                } 
    }
    
}
