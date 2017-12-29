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
public class NOB_producto 
{
    private String id_pro;

    //CONSTRUCTOR
    public NOB_producto(String id_pro) 
    {
        this.id_pro = id_pro;
    }
    
    //GET AND SET
    public String getId_pro() 
    {
        return id_pro;
    }

    public void setId_pro(String id_pro) 
    {
        this.id_pro = id_pro;
    }
    
}
