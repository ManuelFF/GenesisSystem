package ModuleWorker.coreModels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author ManuelS
 */
public class DbConfModel {

    private String db_serverName;
    private String db_serverPort;
    private String db_userName;
    private String db_password;
    private String db_sid;

    public DbConfModel() {
    }

    public String getDb_serverName() {
        return db_serverName;
    }

    public String getDb_serverPort() {
        return db_serverPort;
    }

    public String getDb_userName() {
        return db_userName;
    }

    public String getDb_password() {
        return db_password;
    }

    public String getDb_sid() {
        return db_sid;
    }

    
    
    public static DbConfModel getDbConfModel() {
        ObjectMapper objectMapper = new ObjectMapper();
        DbConfModel dbConfModel = null;
        String rRequest = read("./dbConf.json");
        //objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        try {
            dbConfModel = (DbConfModel) objectMapper.readValue(rRequest.trim().replace("\\", "").replace("\"{", "{").replace("}\"", "}"), DbConfModel.class);
        } catch (Exception var4) {
            dbConfModel = new DbConfModel();
        }

        return dbConfModel;
    }

    private static String read(String ruta) {
        FileReader fr = null;
        BufferedReader br = null;
        //Cadena de texto donde se guardara el contenido del archivo
        String contenido = "";
        try {
            //ruta puede ser de tipo String o tipo File
            fr = new FileReader(ruta);
            br = new BufferedReader(fr);

            String linea;
            //Obtenemos el contenido del archivo linea por linea
            while ((linea = br.readLine()) != null) {
                contenido += linea + "\n";
            }

        } catch (Exception e) {
        } //finally se utiliza para que si todo ocurre correctamente o si ocurre 
        //algun error se cierre el archivo que anteriormente abrimos
        finally {
            try {
                br.close();
            } catch (Exception ex) {
            }
        }
        return contenido;
    }

}
