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
public class SGL_CL_Servicios 
{
    private String idserv;
    private String nomserv;
    private String notaserv;

    //CONSTRUCTOR

    public SGL_CL_Servicios(String idserv, String nomserv, String notaserv) 
    {
        this.idserv = idserv;
        this.nomserv = nomserv;
        this.notaserv = notaserv;
    }
   
    //GET AND SET

    public String getIdserv() 
    {
        return idserv;
    }

    public void setIdserv(String idserv) 
    {
        this.idserv = idserv;
    }

    public String getNomserv() 
    {
        return nomserv;
    }

    public void setNomserv(String nomserv) 
    {
        this.nomserv = nomserv;
    }

    public String getNotaserv() 
    {
        return notaserv;
    }

    public void setNotaserv(String notaserv) 
    {
        this.notaserv = notaserv;
    }
    
    
    

    
}
