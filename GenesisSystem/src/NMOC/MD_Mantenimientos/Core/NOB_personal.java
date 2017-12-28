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
public class NOB_personal 
{
    private String id_personal;

    //CONSTRUCTOR
    public NOB_personal(String id_personal) 
    {
        this.id_personal = id_personal;
    }
    
    //GET AND SET

    public String getId_personal() 
    {
        return id_personal;
    }

    public void setId_personal(String id_personal) 
    {
        this.id_personal = id_personal;
    }
    
    
}
