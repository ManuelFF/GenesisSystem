/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBRPM;

import ModuleWorker.DBCON;
import NCLPM.LOG;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.constant.ImageType;
import net.sf.dynamicreports.report.builder.DynamicReports;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.field;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.exception.DRException;

/**
 *
 * @author USUARIO
 */
public class RP_ORDEN_SERV
{
 
    LOG lc = new LOG();
    private Connection con;
    
    //CLASE QUE ALOJARA A LAS ORDENES DE SERVICIO MODULARIZADAS
    
    //VARIABLES GLOBALES
    
    private static String ID_ORD = ""; //natural 342 -- juridico 194
    private static String tipo= "";   
    
    //ESTILOS
    private StyleBuilder borderedStyle;
    private StyleBuilder fontColor_GR;
    private StyleBuilder fontColor_CY;
    
    public static void speculation(String tip, String id)
    {
        ID_ORD = id;
        tipo = tip;
    }
    
    //FIN VARIABLES GLOBALES Y/O ESTILOs
    
    private void Style_Builder()
    {
        //DARK GREEN
        fontColor_GR = stl.style()
                            .bold();
        fontColor_GR.setForegroundColor(Color.GREEN.darker().darker().darker());
        
        //CYAN
        fontColor_CY = stl.style()
                            .bold();
        fontColor_CY.setForegroundColor(Color.CYAN.brighter().brighter());
                    
        //BORDERED STYLe
        borderedStyle = stl.style(stl.pen1Point());

    }
    
    //QUERYS
    //ESTABLECE LOS QUERYS QUE USARAN CADA SUB REPORTE
    private final String QRY_ORDENJ = "select os.ID_CLI, cj.RUC, cj.RAZON_SOCIAL, os.TELEFONO, os.CELULAR, cj.NOM_ENCARGADO, os.GIRO_LUGAR,\n" +
                        "os.DIRECCION,os.NUMERO_ORDEN,emp.NOM_PER||' '||emp.APE_PER NOMPER, os.COSTO,os.FECHA, os.HORA\n" +
                        "from orden_serv os\n" +
                        "inner join cliente_juridico cj on os.ID_CLI = cj.ID_CLI\n" +
                        "inner join personal_emp emp on os.ID_PER = emp.ID_PER\n" +
                        "where os.ID_ORDEN = "+"'"+ID_ORD+"'";
    
    private final String QRY_ORDENN = "select os.ID_CLI, cj.DNI, cj.NOMBRES||' '||cj.APE_PAT||' '||cj.APE_MAT NOMBRE, os.TELEFONO, os.CELULAR, os.GIRO_LUGAR,\n" +
                        "os.DIRECCION,os.NUMERO_ORDEN,emp.NOM_PER||' '||emp.APE_PER NOMPER, os.COSTO,os.FECHA, os.HORA\n" +
                        "from orden_serv os\n" +
                        "inner join cliente_natural cj on os.ID_CLI = cj.ID_CLI\n" +
                        "inner join personal_emp emp on os.ID_PER = emp.ID_PER\n" +
                        "where os.ID_ORDEN = "+"'"+ID_ORD+"'";
    
    private final String QRY_AREAS = "select DISTINCT ID_AREA,nombre_area, num_ambientes, area_trabajar ||' '|| FORMATO area_trabajar,"
                      +"'ÁREA TOTAL A TRABAJAR: '|| AREA_TOTAL_M2||' MT2 - '||AREA_TOTAL_M3||' MT3' area from det_orden_serv where ID_ORDEN = "+"'"+ID_ORD+"' order by ID_AREA";
    
    private final String QRY_SERVICIOS = "select DISTINCT ' '||sr.NOMBRE_SERV servicio,ds.NOTA\n" +
                           "from det_orden_serv ds\n" +
                           "inner join servicios sr on ds.ID_SERV = sr.ID_SERV\n" +
                           "where ds.ID_ORDEN = "+"'"+ID_ORD+"'";

    private final String QRY_PRODUCTOS = "select DISTINCT ' '||pr.NOMBRE producto\n" +
                           "from det_orden_serv ds\n" +
                           "inner join producto pr on ds.ID_PRO = pr.ID_PRO\n" +
                           "where ds.ID_ORDEN = "+"'"+ID_ORD+"'";
    
    private final String QRY_IMPLEMENTOS = "select DISTINCT ' '||mq.NOMBRE implementos\n" +
                             "from det_orden_serv ds\n" +
                             "inner join maquina mq on ds.ID_MAQU = mq.ID_MAQU\n" +
                             "where ds.ID_ORDEN = "+"'"+ID_ORD+"'";
    
    private final String QRY_OPERATIVOS = "select DISTINCT ' '||emp.NOM_PER||' '||emp.APE_PER NOMBRE\n" +
                            "from det_orden_serv ds\n" +
                            "inner join personal_emp emp on ds.ID_PER = emp.ID_PER\n" +
                            "where ds.ID_ORDEN = "+"'"+ID_ORD+"'";
    
    //FIN QUERYS
    
    public void build(String operacion)
    {
      try
      {
        DBCON RCN = new DBCON();
        con = RCN.conector();
                
        Style_Builder();
        
        //SUB REPORTES
        SubreportBuilder subreportA = cmp.subreport(SubReporte_Areas());
        SubreportBuilder subreportS = cmp.subreport(SubReporte_Servicios());
        SubreportBuilder subreportP = cmp.subreport(SubReporte_Productos());
        SubreportBuilder subreportI = cmp.subreport(SubReporte_Implementos());
        SubreportBuilder subreportO = cmp.subreport(SubReporte_operarios());
        SubreportBuilder subreportF = cmp.subreport(SubReporte_footer());
        
        JasperReportBuilder reports = DynamicReports.report();
                
         //FUNCION SELECTIVA CONDICIONAL
        //ESTABLECIENDO EL TEMPLATE
                
        if(tipo.equals("Juridica"))
        {
           reports.setTemplateDesign("./ordenTrabajoJ.jrxml");
        }
            
        if(tipo.equals("Natural"))
        {
            reports.setTemplateDesign("./ordenTrabajoN.jrxml");
        }
        
        reports
        
        //FIN FUNCION SELECTIVA CONDICIONAL   
                
        //CONSTRUCTOR DE COLUMNAS (PARA LOS SUBREPORTES)
        .columnHeader
           (
               cmp.verticalList(cmp.horizontalGap(100), subreportA, cmp.horizontalGap(100)),
               cmp.text("\n"),
               cmp.verticalList(cmp.horizontalGap(100), subreportS, cmp.horizontalGap(100)),
               cmp.verticalList(cmp.horizontalGap(100), subreportP, cmp.horizontalGap(100)),
               cmp.verticalList(cmp.horizontalGap(100), subreportI, cmp.horizontalGap(100)),
               cmp.verticalList(cmp.horizontalGap(100), subreportO, cmp.horizontalGap(100)),
               cmp.verticalList(cmp.horizontalGap(100), subreportF, cmp.horizontalGap(100))
           )
        //CONFIGURACION DE LA PAGINA
        .setPageFormat(PageType.A4, PageOrientation.PORTRAIT);
        
        //FUNCION SELECTIVA CONDICIONAL
        if(tipo.equals("Juridica"))
        {
           reports.setDataSource(QRY_ORDENJ,con);//set datasource for ORDEN JURIDICAS
        }
            
        if(tipo.equals("Natural"))
        {
           reports.setDataSource(QRY_ORDENN,con);//set datasource for ORDEN NATURALES

        }
        //FIN FUNCION SELECTIVA CONDICIONAL        
        
        
        if(operacion.equals("SHOW"))
        {
            reports.show(false);//create and show repor
        }
        if(operacion.equals("PDF"))
        {
            reports.toPdf(new FileOutputStream("./OrdenServicio.pdf"));
        }
        if(operacion.equals("FAST_PRINT"))
        {
            reports.print(false);
        }
        if(operacion.equals("PRINT"))
        {
            reports.print();
        }
      
                
        //ESTABLECE CAMBIO DE FORMATOS
//        .toPptx(new FileOutputStream("./orden.pptx"))
//        .toImage(new FileOutputStream("./orden.jpg"), ImageType.JPG)
//        .toXlsx(new FileOutputStream("./orden.xlsx"))
//        .toPdf(new FileOutputStream("./orden.pdf"))
//        .toDocx(new FileOutputStream("./orden.docx"));
        
      }catch (DRException e) 
        {
           lc.write("Ha ocurrido algun error en el build principal de la generacion de orden de servicio", "RP_ORDEN_SERV", e);
           
        } catch (FileNotFoundException ex) 
            {
                lc.write("No se ha podido transformar la orden de servicio en otro formato", RP_ORDEN_SERV.class.getName(), ex);                
            }
      
    }
    
    private JasperReportBuilder SubReporte_Areas() 
       {       
          JasperReportBuilder report = report();
          report
            .setTemplate(Templates.reportTemplate)

            .title(cmp.text("ÁREAS A TRABAJAR:").setStyle(fontColor_GR))

            .columns
            (
               col.column("DESCRIPCIÓN","nombre_area",type.stringType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER).setStyle(borderedStyle),
               col.column("NÚMERO DE AMBIENTES","num_ambientes",type.stringType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER).setStyle(borderedStyle),
               col.column("DIMENSIÓN","area_trabajar",type.stringType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER).setStyle(borderedStyle)
            )

            .columnFooter
            (
                cmp.text(field("area", type.stringType()).getField()).setStyle(fontColor_GR)
            )


            .setDataSource(QRY_AREAS,con);

          return report;
       }    
    
    private JasperReportBuilder SubReporte_Servicios() 
   {
      JasperReportBuilder report = report();
      
      report
        .setTemplate(Templates.reportTemplate)
        .title(cmp.text("1. SERVICIOS A REALIZAR.").setStyle(Templates.boldStyle))
       
        .addColumn
        (
           col.column("SERVICIO","servicio",type.stringType()).setStyle(borderedStyle),
           col.column("DESCRIPCIÓN","nota",type.stringType()).setStyle(borderedStyle)
        )

        .setDataSource
        (
            QRY_SERVICIOS,con
        );
      
      return report;
   }

    private JasperReportBuilder SubReporte_Productos() 
   {
     JasperReportBuilder report = report();
      
      report
        .setTemplate(Templates.reportTemplate)
        .title(cmp.text("2. PRODUCTOS QUÍMICOS O BIOLÓGICOS.").setStyle(Templates.boldStyle))
        
        .addColumn
        (
           
           col.column("PRODUCTOS","producto",type.stringType()).setStyle(borderedStyle)
            
        )

        .setDataSource
        (
            QRY_PRODUCTOS,con
        );
      
      return report;
   }

    private JasperReportBuilder SubReporte_Implementos() 
   {
     JasperReportBuilder report = report();
      
      report
        .setTemplate(Templates.reportTemplate)
        .title(cmp.text("3. MÁQUINAS E IMPLEMENTOS.").setStyle(Templates.boldStyle))
              
        .addColumn
        (
           col.column("MAQUINAS E IMPLEMENTOS","implementos",type.stringType()).setStyle(borderedStyle)
        )

        .setDataSource
        (
           QRY_IMPLEMENTOS,con
        );
      
      return report;
   }
    
    private JasperReportBuilder SubReporte_operarios() 
   {
     JasperReportBuilder report = report();

     report
        .setTemplate(Templates.reportTemplate)
        .title(cmp.text("4. PERSONAL OPERARIO.").setStyle(Templates.boldStyle))
              
        .addColumn
        (
           col.column("PERSONAL OPERATIVO","NOMBRE",type.stringType()).setStyle(borderedStyle)
        )

        .setDataSource
        (
            QRY_OPERATIVOS,con
        );
      
      return report;
   }
   
    private JasperReportBuilder SubReporte_footer() 
   {
     JasperReportBuilder report = report();
      
      report
        .setTemplate(Templates.reportTemplate)
        .title(cmp.text("\n5. ATENDIDO POR:___________________________________________________________________.").setStyle(Templates.boldStyle))
        .title(cmp.text("\n6. FIRMA DE CONFORMIDAD:__________________________________________________________.").setStyle(Templates.boldStyle))
        .title(cmp.text("Al firmar este documento el cliente especifica que esta conforme con los servicios brindados por la empresa."))
        .title(cmp.text("7. OBSERVACIONES:").setStyle(Templates.boldStyle));

      return report;
   }
    
    
}
