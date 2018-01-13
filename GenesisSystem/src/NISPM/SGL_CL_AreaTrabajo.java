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
public class SGL_CL_AreaTrabajo 
{
    private String idarea;
    private String nombreArea;
    private String numeroAmbientes;
    private String areaTrabajar;
    private String formato;

    //CONSTRUCTOR
    public SGL_CL_AreaTrabajo(String idarea, String nombreArea, String numeroAmbientes, String areaTrabajar, String formato)
    {
        this.idarea = idarea;
        this.nombreArea = nombreArea;
        this.numeroAmbientes = numeroAmbientes;
        this.areaTrabajar = areaTrabajar;
        this.formato = formato;
    }

    //GET AND SET
    public String getIdarea() 
    {
        return idarea;
    }

    public void setIdarea(String idarea) 
    {
        this.idarea = idarea;
    }

    public String getNombreArea() 
    {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea)
    {
        this.nombreArea = nombreArea;
    }

    public String getNumeroAmbientes() 
    {
        return numeroAmbientes;
    }

    public void setNumeroAmbientes(String numeroAmbientes) 
    {
        this.numeroAmbientes = numeroAmbientes;
    }

    public String getAreaTrabajar() 
    {
        return areaTrabajar;
    }

    public void setAreaTrabajar(String areaTrabajar)
    {
        this.areaTrabajar = areaTrabajar;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato)
    {
        this.formato = formato;
    }
    
    
    

    
}
