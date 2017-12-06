/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker;

/**
 *
 * @author USUARIO
 */
public class SYSFRMCON 
{
    
    //TAGS
    /*
      los tags son marcas que se le asignan a un formulario para saber el estado
      en el que se encuentran acualmente programados.
    */
    
    //TAG DEV : Usado cuando un componente esta en actual desarrollo.
    public String DEV()
    {
        String dev ="[DEV]";
        return dev;
    }
    //TAG CANARY : Usado cuando un componente ha salido de desarrollo pero no ha sido
    //probado lo suficiente como para comprobar su plena funcionalidad.
    public String CANARY()
    {
        String ca ="[CANARY]";
        return ca;
    }
    //TAG ALFA : Usado para cuando un componente ha sido probado pero no lo suficiente
    //Sin embargo contiene la suficiente funcionalidad como para ser considerado usable
    public String ALFA()
    {
        String alfa ="[ALFA]";
        return alfa;
    }
    //TAG BETA : Usado para cuando un componente tiene la funcionalidad adecuada para
    //ser usado regularmente; Sin embargo, aun contiene errores no graves.
    public String BETA()
    {
        String beta ="[BETA]";
        return beta;
    }
    //TAG PRE_STABLE : Usado cuando un componente esta plenamente funcional pero sigue
    //siendo desarrollado por mejoramiento de caracteristicas o por falta de diseño.
    public String PRE_STABLE()
    {
        String preE ="[PRE-STABLE]";
        return preE;
    }
    //TAG STABLE : Usado cuando un componente ya esta terminado plenamente y puede
    //ser usado de manera productiva.
    public String STABLE()
    {
        String St ="[STABLE]";
        return St;
    }
    
    //TITLES
    /*
        los Titles son los titulos que cada formulario poseera y que seran
        netamente controlados por esta clase.
    */
    
    //Titulo Inicio Sesion
    public String T_login()
    {
        String t = STABLE()+" Iniciar Sesión en Génesis System";
        return t;
    }
    
    //Titulo Frame Principal
    public String T_principal()
    {
        String t = STABLE()+ "Génesis System";
        return t;
    }
    
    
    
}
