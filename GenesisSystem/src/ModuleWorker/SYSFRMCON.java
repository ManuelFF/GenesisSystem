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
        String nivel = "0.5";
        String t = PRE_STABLE()+ " Mantener Usuario "+nivel;
        return t;
    }
    
    public void B_mantenerUsuario(Container p)
    {
      p.setBackground(Color.GRAY.brighter());
    }
    
    //JDES SELECCIONAR PERSONAL
    public String T_JDESseleccionarPersonal()
    {
        String nivel = "0.5";
        String t = ALFA()+ " Seleccionar Personal "+nivel;
        return t;
    }
    
    public void B_JDESseleccionarPersonal(Container p)
    {
      p.setBackground(Color.GRAY.brighter());
    }
       
    //PERMISOS
    public String T_Permisos()
    {
        String nivel = "0.5";
        String t = CANARY()+" Permisos de Usuarios "+nivel;
        return t;
    }
    
    public void B_permisos(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //CENTRO DE NOTIFICACIONES
    public String T_Centro_Notificaciones()
    {
        String nivel = "0.5";
        String t = DEV()+" Centro de Notificaciones "+nivel;
        return t;
    }
    
    public void B_Centro_Notificaciones(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //REGISTRAR ENTRADA
    public String T_RegistrarAsistencia()
    {
        String nivel = "0.5";
        String t = DEV()+" Registre su entrada "+nivel;
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
        String nivel = "0.5";
        String t = DEV()+" Selector de Fecha y Hora "+nivel;
        return t;
    }
    
    public void B_Date(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //BUSCAR VENDEDOR
    public String T_BuscarVendedor()
    {
        String nivel = "0.5";
        String t = DEV()+" Buscar Vendedor "+nivel;
        return t;
    }
    
    public void B_BuscarVendedor(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //BUSCAR CLIENTE
    public String T_BuscarCliente()
    {
        String nivel = "0.5";
        String t = DEV()+" Buscar Cliente "+nivel;
        return t;
    }
    
    public void B_BuscarCliente(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //BUSCAR ORDENES DE TRABAJO
    public String T_BuscarOrdenes()
    {
        String nivel = "0.5";
        String t = DEV()+" Buscar Ordenes de Trabajo "+nivel;
        return t;
    }
    
    public void B_BuscarOrdenes(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //BUSCAR CERTIFICADOS
    public String T_BuscarCertificados()
    {
        String nivel = "0.5";
        String t = DEV()+" Buscar Certificados "+nivel;
        return t;
    }
    
    public void B_BuscarCertificados(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    
    //#########################################################################
    //FORMULARIOS DEL PAQUETE NISPM
    
    //AREA TRABAJO
    public String T_GenerarArea()
    {
        String nivel = "0.5";
        String t = DEV()+" Generar Áreas de Trabajo "+nivel;
        return t;
    }
    
    public void B_GenerarArea(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //SERVICIOS
    public String T_Servicios()
    {
        String nivel = "0.5";
        String t = DEV()+" Importar Servicio "+nivel;
        return t;
    }
    
    public void B_Servicios(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //OPERARIOS
    public String T_Operarios()
    {
        String nivel = "0.5";
        String t = DEV()+" Importar Operarios "+nivel;
        return t;
    }
    
    public void B_Operarios(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //PRODUCTOS
    public String T_Productos()
    {
        String nivel = "0.5";
        String t = DEV()+" Importar Productos "+nivel;
        return t;
    }
    
    public void B_Productos(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //IMPLEMENTOS
    public String T_Implementos()
    {
        String nivel = "0.5";
        String t = DEV()+" Importar Implementos "+nivel;
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
        String nivel = "0.5";
        String t = BETA()+" Mantener Clientes "+nivel;
        return t;
    }
    
    public void B_MantenerClientes(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }

    //MANTENER PERSONAL
    public String T_MantenerPersonal()
    {
        String nivel = "0.5";
        String t = BETA()+" Mantener Personal "+nivel;
        return t;
    }
    
    public void B_MantenerPersonal(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //MANTENER PRODUCTO
    public String T_MantenerProducto()
    {
        String nivel = "0.5";
        String t = ALFA()+" Mantener Producto "+nivel;
        return t;
    }
    
    public void B_MantenerProducto(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
       
    //MANTENER IMPLEMENTO
    public String T_MantenerImplemento()
    {
        String nivel = "0.5";
        String t = ALFA()+" Mantener Implemento "+nivel;
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
        String nivel = "0.5";
        String t = DEV()+" Generar Cotización "+nivel;
        return t;
    }
    
    public void B_GenerarCotización(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //GENERAR ORDEN SERVICIO
    public String T_GenerarOrden()
    {
        String nivel = "0.5";
        String t = DEV()+" Generar Orden de Trabajo "+nivel;
        return t;
    }
    
    public void B_GenerarOrden(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //GENERAR CERTIFICADO
    public String T_Certificado()
    {
        String nivel = "0.5";
        String t = DEV()+" Generar Certificados de Servicios "+nivel;
        return t;
    }
    
    public void B_Certificado(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //#########################################################################
    //FORMULARIOS CONSULTAR
    
    //CONSULTAR ORDEN
    public String T_ConsultarORden()
    {
        String nivel = "0.5";
        String t = DEV()+" Consultar Orden de Trabajo "+nivel;
        return t;
    }
    
    public void B_ConsultarORden(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //CONSULTAR CERTIFICADO
    public String T_ConsultarCertificado()
    {
        String nivel = "0.5";
        String t = DEV()+" Consultar Certificado "+nivel;
        return t;
    }
    
    public void B_ConsultarCertificado(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
    //CONSULTAR CERTIFICADO
    public String T_ConsultarCertificadosVen()
    {
        String nivel = "0.5";
        String t = DEV()+" Consultar Certificados Vencidos "+nivel;
        return t;
    }
    
    public void B_ConsultarCertificadosVen(Container p)
    {
        p.setBackground(Color.GRAY.brighter());
    }
    
}
