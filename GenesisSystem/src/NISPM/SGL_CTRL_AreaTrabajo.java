/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NISPM;

import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class SGL_CTRL_AreaTrabajo
{
    private ArrayList<SGL_CL_AreaTrabajo> Sgl_area;
    public static SGL_CTRL_AreaTrabajo controller; //idea basica del patron singlaton
    
    private SGL_CTRL_AreaTrabajo()
    {
        Sgl_area = new ArrayList<SGL_CL_AreaTrabajo>();
    }
    
    public static SGL_CTRL_AreaTrabajo getInstance()
    {
        if(controller == null)
            controller = new SGL_CTRL_AreaTrabajo();
        return controller;
    }
    
    public ArrayList<SGL_CL_AreaTrabajo> getSgl_area()
    {
        return Sgl_area;
    }
    
    public void setSgl_area(ArrayList<SGL_CL_AreaTrabajo> sgl_area) 
    {
        this.Sgl_area = sgl_area;
    }
       
    
}
