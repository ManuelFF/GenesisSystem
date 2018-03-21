/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Ventas.Core;

/**
 *
 * @author USUARIO
 */
public class NOB_registroContacto
{
    private String ID_REG_CONT;
    
    //CONSTRUCTOR
    public NOB_registroContacto(String ID_REG_CONT) 
    {
        this.ID_REG_CONT = ID_REG_CONT;
    }

    public String getID_REG_CONT() 
    {
        return ID_REG_CONT;
    }

    public void setID_REG_CONT(String ID_REG_CONT) 
    {
        this.ID_REG_CONT = ID_REG_CONT;
    }
    
}
