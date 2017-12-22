/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Mantenimientos.Core;

/**
 *
 * @author USUARIO
 */
public class NOB_cliente 
{
    private String id_cli;
    
    //CONSTRUCTOR
    public NOB_cliente(String id_cli) 
    {
        this.id_cli = id_cli;
    }
    
    //GET
    public String getId_cli() 
    {
        return id_cli;
    }

    //SET
    public void setId_cli(String id_cli) 
    {
        this.id_cli = id_cli;
    }
    
    
}
