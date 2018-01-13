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
public class SGL_CL_Implementos 
{
    private String idmaq;
    private String nommaq;

    //CONSTRUCTOR

    public SGL_CL_Implementos(String idmaq, String nommaq) 
    {
        this.idmaq = idmaq;
        this.nommaq = nommaq;
    }
    
    //GET AND SET

    public String getIdmaq() 
    {
        return idmaq;
    }

    public void setIdmaq(String idmaq) 
    {
        this.idmaq = idmaq;
    }

    public String getNommaq() 
    {
        return nommaq;
    }

    public void setNommaq(String nommaq) 
    {
        this.nommaq = nommaq;
    }
    
    
    
}
