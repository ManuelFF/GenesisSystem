/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleWorker;

import java.awt.Color;
import java.awt.Container;

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

//##############################################################################################################    
    //TITLES
    /*
        los Titles son los titulos que cada formulario poseera y que seran
        netamente controlados por esta clase.
    */
    
    //Titulo Inicio Sesion
    public String T_login()
    {
        String t = STABLE()+" Iniciar Sesión en Génesis.";
        return t;
    }
    
    public void B_login(Container p)
    {
      p.setBackground(Color.WHITE);
    }
    
    //*****CONDICION ESPECIAL*****//
   
    public void B_JMSesion(Container p)
    {
      p.setBackground(Color.BLUE.brighter());
      p.setForeground(Color.CYAN.darker().darker());
    }
    
    //*****FIN CONDICION ESPECIAL******//
    
    //Titulo Frame Principal
    public String T_principal()
    {
        String t = DEV()+ " Génesis System";
        return t;
    }
    
    //MANTENER USUARIO
    public String T_mantenerUsuario()
    {
        String t = PRE_STABLE()+ " Mantener Usuario";
        return t;
    }
    
    public void B_mantenerUsuario(Container p)
    {
      p.setBackground(Color.GRAY.brighter());
    }
    
    //JDES SELECCIONAR PERSONAL
    public String T_JDESseleccionarPersonal()
    {
        String t = ALFA()+ " Seleccionar Personal";
        return t;
    }
    
    public void B_JDESseleccionarPersonal(Container p)
    {
      p.setBackground(Color.GRAY.brighter());
    }
       
    //PERMISOS
    public String T_Permisos()
    {
        String t = CANARY()+" Permisos de Usuarios";
        return t;
    }
    
    public void B_permisos(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //CENTRO DE NOTIFICACIONES
    public String T_Centro_Notificaciones()
    {
        String t = DEV()+" Centro de Notificaciones";
        return t;
    }
    
    public void B_Centro_Notificaciones(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //CENTRO DE NOTIFICACIONES
    public String T_RegistrarAsistencia()
    {
        String t = DEV()+" Registre su entrada";
        return t;
    }
    
    public void B_RegistrarAsistencia(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //#########################################################################
    //FORMULARIOS GLOBALES
    //CALENDAR
    public String T_Date()
    {
        String t = DEV()+" Selector de Fecha y Hora";
        return t;
    }
    
    public void B_Date(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //BUSCAR VENDEDOR
    public String T_BuscarVendedor()
    {
        String t = DEV()+" Buscar Vendedor";
        return t;
    }
    
    public void B_BuscarVendedor(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //BUSCAR CLIENTE
    public String T_BuscarCliente()
    {
        String t = DEV()+" Buscar Cliente";
        return t;
    }
    
    public void B_BuscarCliente(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //BUSCAR ORDENES DE TRABAJO
    public String T_BuscarOrdenes()
    {
        String t = DEV()+" Buscar Ordenes de Trabajo";
        return t;
    }
    
    public void B_BuscarOrdenes(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //#########################################################################
    //FORMULARIOS DEL PAQUETE NISPM
    
    //AREA TRABAJO
    public String T_GenerarArea()
    {
        String t = DEV()+" Generar Áreas de Trabajo";
        return t;
    }
    
    public void B_GenerarArea(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //SERVICIOS
    public String T_Servicios()
    {
        String t = DEV()+" Importar Servicio";
        return t;
    }
    
    public void B_Servicios(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //OPERARIOS
    public String T_Operarios()
    {
        String t = DEV()+" Importar Operarios";
        return t;
    }
    
    public void B_Operarios(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //PRODUCTOS
    public String T_Productos()
    {
        String t = DEV()+" Importar Productos";
        return t;
    }
    
    public void B_Productos(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //IMPLEMENTOS
    public String T_Implementos()
    {
        String t = DEV()+" Importar Implementos";
        return t;
    }
    
    public void B_Implementos(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    
    //#########################################################################
    //FORMULARIOS DE MANTENIMIENTO
    
    //MANTENER CLIENTES
    public String T_MantenerClientes()
    {
        String t = BETA()+" Mantener Clientes";
        return t;
    }
    
    public void B_MantenerClientes(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }

    //MANTENER PERSONAL
    public String T_MantenerPersonal()
    {
        String t = BETA()+" Mantener Personal";
        return t;
    }
    
    public void B_MantenerPersonal(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //MANTENER PRODUCTO
    public String T_MantenerProducto()
    {
        String t = ALFA()+" Mantener Producto";
        return t;
    }
    
    public void B_MantenerProducto(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
       
    //MANTENER IMPLEMENTO
    public String T_MantenerImplemento()
    {
        String t = ALFA()+" Mantener Implemento";
        return t;
    }
    
    public void B_MantenerImplemento(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }            
    
    //#########################################################################
    //FORMULARIOS GENERAR
    
    //GENERAR COTIZACIÓN
    public String T_GenerarCotización()
    {
        String t = DEV()+" Generar Cotización";
        return t;
    }
    
    public void B_GenerarCotización(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //GENERAR ORDEN SERVICIO
    public String T_GenerarOrden()
    {
        String t = DEV()+" Generar Orden Servicio";
        return t;
    }
    
    public void B_GenerarOrden(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //#########################################################################
    //FORMULARIOS CONSULTAR
    
    //CONSULTAR ORDEN
    public String T_ConsultarORden()
    {
        String t = DEV()+" Generar Cotización";
        return t;
    }
    
    public void B_ConsultarORden(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    
}
