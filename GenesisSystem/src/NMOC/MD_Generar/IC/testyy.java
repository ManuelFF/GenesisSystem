/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NMOC.MD_Generar.IC;

/**
 *
 * @author Administrador
 */
public class testyy
{
    public static void main(String[] args)
    {
        System.out.println("ORden de Servicio Anterior - Ultimo IDS");
        System.out.println(UltimoID.UltimoID());
        System.out.println("Certificado Anterior - Ultimo IDS");
        System.out.println(UltimoID.UltimoID_CERT());
        //***
        System.out.println("ORden de Servicio Anterior - Ultimo Numero de Orden");
        System.out.println(UltimoID.UltimoNU());
        System.out.println("Certificado Anterior - Ultimo Ultimo Numero de Certificado");
        System.out.println(UltimoID.UltimoNU_CERT());
        
    }
}
