/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NISPM;

/**
 *
 * @author USUARIO
 */
public class SGL_CL_Productos 
{
    private String idPro;
    private String nomPro;

    //CONSTRUCTOR

    public SGL_CL_Productos(String idPro, String nomPro) 
    {
        this.idPro = idPro;
        this.nomPro = nomPro;
    }
    
    //GET AND SET

    public String getIdPro() 
    {
        return idPro;
    }

    public void setIdPro(String idPro) 
    {
        this.idPro = idPro;
    }

    public String getNomPro() 
    {
        return nomPro;
    }

    public void setNomPro(String nomPro) 
    {
        this.nomPro = nomPro;
    }
    
    
    
}
