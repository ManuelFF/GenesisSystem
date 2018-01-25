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
public class O_OrdenTrabajo 
{
    private String id_orden;
    private String numero_orden;
    private String id_cli;
    private String fecha;
    private String hora;
    private String direccion;
    private String giro_lugar;
    private String telefono;
    private String celular;
    private String desc_doc;
    private String id_per;
    private String costo;
    private String estado;

    //CONSTRUCTOR
    public O_OrdenTrabajo(String id_orden, String numero_orden, String id_cli, String fecha, String hora, String direccion, String giro_lugar, String telefono, String celular, String desc_doc, String id_per, String costo, String estado) 
    {
        this.id_orden = id_orden;
        this.numero_orden = numero_orden;
        this.id_cli = id_cli;
        this.fecha = fecha;
        this.hora = hora;
        this.direccion = direccion;
        this.giro_lugar = giro_lugar;
        this.telefono = telefono;
        this.celular = celular;
        this.desc_doc = desc_doc;
        this.id_per = id_per;
        this.costo = costo;
        this.estado = estado;
    }

    //GET AND SET
    public String getId_orden() 
    {
        return id_orden;
    }

    public void setId_orden(String id_orden) 
    {
        this.id_orden = id_orden;
    }

    public String getNumero_orden() 
    {
        return numero_orden;
    }

    public void setNumero_orden(String numero_orden) 
    {
        this.numero_orden = numero_orden;
    }

    public String getId_cli() 
    {
        return id_cli;
    }

    public void setId_cli(String id_cli) 
    {
        this.id_cli = id_cli;
    }

    public String getFecha() 
    {
        return fecha;
    }

    public void setFecha(String fecha) 
    {
        this.fecha = fecha;
    }

    public String getHora() 
    {
        return hora;
    }

    public void setHora(String hora) 
    {
        this.hora = hora;
    }

    public String getDireccion() 
    {
        return direccion;
    }

    public void setDireccion(String direccion) 
    {
        this.direccion = direccion;
    }

    public String getGiro_lugar() 
    {
        return giro_lugar;
    }

    public void setGiro_lugar(String giro_lugar) 
    {
        this.giro_lugar = giro_lugar;
    }

    public String getTelefono() 
    {
        return telefono;
    }

    public void setTelefono(String telefono) 
    {
        this.telefono = telefono;
    }

    public String getCelular() 
    {
        return celular;
    }

    public void setCelular(String celular) 
    {
        this.celular = celular;
    }

    public String getDesc_doc() 
    {
        return desc_doc;
    }

    public void setDesc_doc(String desc_doc) 
    {
        this.desc_doc = desc_doc;
    }

    public String getId_per() 
    {
        return id_per;
    }

    public void setId_per(String id_per) 
    {
        this.id_per = id_per;
    }

    public String getCosto() 
    {
        return costo;
    }

    public void setCosto(String costo) 
    {
        this.costo = costo;
    }

    public String getEstado() 
    {
        return estado;
    }

    public void setEstado(String estado) 
    {
        this.estado = estado;
    }
    
    
    
}
