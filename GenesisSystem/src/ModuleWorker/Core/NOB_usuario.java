/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker.Core;

/**
 *
 * @author USUARIO
 */
public class NOB_usuario 
{
    //Elemento
    private String id_user;

    //Constructor
    public NOB_usuario(String id_user)
    {
        this.id_user = id_user;
    }

    //GET
    public String getId_user() 
    {
        return id_user;
    }

    //SET
    public void setId_user(String id_user)
    {
        this.id_user = id_user;
    }
    
    
    
}
