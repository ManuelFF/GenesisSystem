/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBRPM;


import ModuleWorker.DBCON;
import NCLPM.LOG;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import java.math.BigDecimal;
import java.sql.Connection;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;


/**
 *
 * @author USUARIO
 */


public class RP_ORDENES 
{
     LOG lc = new LOG();
    private Connection con;
    
    public RP_ORDENES()
    {
        build();
    }
    
    private FontBuilder boldFont;
    
    private void Style_Builder()
    {
        boldFont = stl.fontArialBold().setFontSize(12);
    }
    
    private final String QRY_ORDENJ =   "select os.NUMERO_ORDEN, cls.RAZON_SOCIAL CLIENTE\n" +
                                        ",emp.NOM_PER||' '||emp.APE_PER VENDEDOR, os.GIRO_LUGAR,os.FECHA,os.COSTO\n" +
                                        "from orden_serv os\n" +
                                        "inner join personal_emp emp on os.ID_PER = emp.ID_PER\n" +
                                        "inner join cliente_juridico cls on os.ID_CLI = cls.ID_CLI\n" +
                                        "where os.id_cli = 'CLI-00000022' order by os.NUMERO_ORDEN";
    
    private final String QRY_ORDENN =   "select os.NUMERO_ORDEN, cls.NOMBRES||' '||cls.APE_PAT||' '||cls.APE_MAT CLIENTE\n" +
                                        ",emp.NOM_PER||' '||emp.APE_PER VENDEDOR, os.GIRO_LUGAR,os.FECHA,os.COSTO\n" +
                                        "from orden_serv os\n" +
                                        "inner join personal_emp emp on os.ID_PER = emp.ID_PER\n" +
                                        "inner join cliente_natural cls on os.ID_CLI = cls.ID_CLI\n" +
                                        "order by os.NUMERO_ORDEN";
    
    private void build()
    {
        DBCON RCN = new DBCON();
        con = RCN.conector();
        Style_Builder();
        
        //--NUMERO ORDEN, CLIENTE, VENDEDOR,GIRO LUGAR, FECHA , COSTO
        
        TextColumnBuilder<String> Numero_orden = col.column("Orden", "NUMERO_ORDEN", type.stringType());
        TextColumnBuilder<String> Cliente = col.column("Cliente", "CLIENTE", type.stringType());
        TextColumnBuilder<String> Vendedor = col.column("Vendedor", "VENDEDOR", type.stringType());
        TextColumnBuilder<String> Giro_lugar = col.column("Giro Lugar", "GIRO_LUGAR", type.stringType());
        TextColumnBuilder<String> Fecha = col.column("Fecha", "FECHA", type.stringType());
        TextColumnBuilder<BigDecimal> Costo = col.column("Costo", "COSTO", type.bigDecimalType());
      
      try 
      {
         JasperReportBuilder reports = DynamicReports.report();
         reports
         .setTemplate(Templates.reportTemplate)
         .columns(Numero_orden, Cliente, Vendedor,Giro_lugar,Fecha,Costo)
         .title(Templates.createTitleComponent("Reporte de ordenes de Servicio"))
         .summary
                (
                    cht.barChart()
                    .setTitle("Bar Chart")
                    .setTitleFont(boldFont)
                    .setCategory(Numero_orden)
                    .series
                           (
                              cht.serie(Costo)
                           )
                 )

         .pageFooter(Templates.footerComponent)
         .setDataSource(QRY_ORDENJ,con)
         .show();
         
      }catch (DRException e) 
        {
           e.printStackTrace();
        }
    }
       
          public static void main(String[] args) 
          {
              new RP_ORDENES();
          }
    
}
