/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Generar.Core;

/**
 *
 * @author USUARIO
 */
public class NOB_Certificado 
{
    private String id_cert;

    //CONSTRUCTOR
    public NOB_Certificado(String id_cert) 
    {
        this.id_cert = id_cert;
    }
    
    //GET AND SET
    public String getId_cert() 
    {
        return id_cert;
    }

    public void setId_cert(String id_cert) 
    {
        this.id_cert = id_cert;
    }
    
    
}
