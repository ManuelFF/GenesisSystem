package NMOC.MD_Generar.IC;


import ModuleWorker.DBCON;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UltimoID
{
    private static ResultSet rs;
    private static PreparedStatement st;

    //ORDEN DE SERVICIO
    public static int UltimoID()
    {
        int last=0;

        try{
            DBCON RCN = new DBCON();

            st=RCN.conector().prepareStatement("select sorted from V_ultimoCode_cot");
            rs=st.executeQuery();

            while (rs.next())
            {
                last = rs.getInt("sorted");
            }
            System.out.println(last);

        }catch(SQLException sqlex)
        {
            //lc.write("Problema al intentar hacer el SentMarcarAsistencia", "Sent_marcarAsistencia", sqlex);
        }
        try
        {
        } catch (Exception ex)
        {
            //lc.write("Error no controlado en el metodo Sent_MarcaR_asistencia", "Sent_marcarAsistencia", ex);
        }

        return (last+1);
    }
    
    //CERTIFICADO  ID 
  public static int UltimoID_CERT()
    {
        int last=0;

        try{
            DBCON RCN = new DBCON();

            st=RCN.conector().prepareStatement("select sorted from V_ULTIMOCODE_CERT");
            rs=st.executeQuery();

            while (rs.next())
            {
                last = rs.getInt("sorted");
            }
            System.out.println(last);

        }catch(SQLException sqlex)
        {
            //lc.write("Problema al intentar hacer el SentMarcarAsistencia", "Sent_marcarAsistencia", sqlex);
        }
        try
        {
        } catch (Exception ex)
        {
            //lc.write("Error no controlado en el metodo Sent_MarcaR_asistencia", "Sent_marcarAsistencia", ex);
        }
        return (last+1);
    }

  //Ultimo numero de orden Orden de servicio
    public static int UltimoNU()
    {
        int last=0;

        try{
            DBCON RCN = new DBCON();

            st=RCN.conector().prepareStatement("select sorted from V_ULTIMOCODE_DETCOT");
            rs=st.executeQuery();

            while (rs.next())
            {
                last = rs.getInt("sorted");
            }
            System.out.println(last);

        }catch(SQLException sqlex)
        {
            //lc.write("Problema al intentar hacer el SentMarcarAsistencia", "Sent_marcarAsistencia", sqlex);
        }
        try
        {
        } catch (Exception ex)
        {
            //lc.write("Error no controlado en el metodo Sent_MarcaR_asistencia", "Sent_marcarAsistencia", ex);
        }

        return (last+1);
    }
    
    //Ultimo numero de certificado CertificadosV2
    public static int UltimoNU_CERT()
    {
        int last=0;

        try{
            DBCON RCN = new DBCON();

            st=RCN.conector().prepareStatement("select sorted from V_ULTIMOCODE_DETCERT");
            rs=st.executeQuery();

            while (rs.next())
            {
                last = rs.getInt("sorted");
            }
            System.out.println(last);

        }catch(SQLException sqlex)
        {
            //lc.write("Problema al intentar hacer el SentMarcarAsistencia", "Sent_marcarAsistencia", sqlex);
        }
        try
        {
        } catch (Exception ex)
        {
            //lc.write("Error no controlado en el metodo Sent_MarcaR_asistencia", "Sent_marcarAsistencia", ex);
        }

        return (last+1);
    }
    
}
