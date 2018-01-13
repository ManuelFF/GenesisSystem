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
public class SGL_CL_Operarios 
{
    private String idope;
    private String nomope;
    private String DNIOpe;

    //CONSTRUCTOR

    public SGL_CL_Operarios(String idope, String nomope, String notaope) 
    {
        this.idope = idope;
        this.nomope = nomope;
        this.DNIOpe = notaope;
    }
    
    //GET AND SET

    public String getIdope() 
    {
        return idope;
    }

    public void setIdope(String idope) 
    {
        this.idope = idope;
    }

    public String getNomope() 
    {
        return nomope;
    }

    public void setNomope(String nomope) 
    {
        this.nomope = nomope;
    }

    public String getDNIOpe() 
    {
        return DNIOpe;
    }

    public void setDNIOpe(String DNIOpe) 
    {
        this.DNIOpe = DNIOpe;
    }
    

    
}
