package ucla.si.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Conexion;
import ucla.si.dao.movil.ConexionDAO;

public class BaseDatosDAO extends ConexionDAO {

    private Runtime runTime;
    private Process p;
    private ProcessBuilder pb;
    private String driver = "org.postgresql.Driver";
    private String usuario = "postgres";
    private String clave = "postgres";
    private String ip = "127.0.0.1";
    private String puerto = "5432";
    private String database = "prime_prueba";


    public boolean generarBackup(String source, String parcial, boolean formatoSql) {
        try {            
            //Obetnemos la versi�n del Servidor
            String versionPostgres = versionPostgreSQL()[1].substring(0, 3);
            String tempCod = "";
            String url = "";

            String so = System.getProperty("os.name");
            System.out.println("SO: "+so);
            System.out.println("version Postgres "+versionPostgres);
            if( so.equals("Linux") ){
                //Ruta Linux
                url = "/usr/lib/postgresql/9.4/bin/pg_dump";
            }else

            if(so.equals("Windows 7")){
                //Ruta Windows, la cual debera modificarse segun sea el caso
                url = "C:\\Archivos de programa\\PostgreSQL\\"+versionPostgres+"\\bin\\pg_dump.exe";
                System.err.println("Windows :( -> " + url);
            }
            
            if(formatoSql)
                tempCod = url+", --verbose, --inserts, --column-inserts, -f, "+source+", "+database;
            else
                tempCod = url+",-i , -F, c, -b, -v, -f, "+source+", "+database;
            
            tempCod += parcial;
            System.out.println("dump "+tempCod);
            //atato = a un arreglo obtenido de la divisi�n de palabras de tempCod por cada coma (,) conseguida
            String[] atato = tempCod.split(",");
            System.out.println("atato "+atato.toString());
            runTime = Runtime.getRuntime();
            
            pb = new ProcessBuilder(atato);            

            //Se asignan valores a las variables de PostgreSQL
            pb.environment().put("PGHOST", ip);
            pb.environment().put("PGPORT", puerto);
            pb.environment().put("PGUSER", usuario);
            pb.environment().put("PGPASSWORD", clave);
            pb.redirectErrorStream(true);
            p = pb.start();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    public boolean realizarRestore(String source){
        try {
            //Obetnemos la versi�n del Servidor
            String versionPostgres = versionPostgreSQL()[1].substring(0, 3);
            String tempCod = "";
            String url = "";
            String so = System.getProperty("os.name");
            String rutaServidor = "";
            if( so.equals("Linux") ){
                rutaServidor = "/opt/PostgreSQL/"+versionPostgres+"/bin/pg_restore";

            }else

            if( so.equals("Windows 7")){
                rutaServidor = "C:\\Archivos de programa\\PostgreSQL\\"+versionPostgres+"\\bin\\pg_restore.exe";
                System.err.println("Windows :( -> " + url);
            }
            String[] atato = {rutaServidor, "-d", database, "-v", source};
            pb = new ProcessBuilder(atato);
                runTime = Runtime.getRuntime();
            //Se asignan valores a las variables de PostgreSQL
            pb.environment().put("PGHOST", ip);
            pb.environment().put("PGPORT", puerto);
            pb.environment().put("PGUSER", usuario);
            pb.environment().put("PGPASSWORD", clave);
            pb.redirectErrorStream(true);
            p = pb.start();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    
    }
    /**
    * Metodo Encargado consultar por orden alfabetico todas las tablas que se encuentren en nuestra base de datos
    * en el esquema <b>public</b>
    */
    public List<String> consultarTablas() {
        List<String> tablas = new ArrayList<String>();
        try {
        	 String tiraSQL = "SELECT  tablename FROM pg_tables WHERE schemaname ='public' ORDER BY tablename ASC";
        	 ResultSet rs = Conexion.consultar(tiraSQL);
        	 while(rs.next()){
        		 String tabla = rs.getString("tablename");
        		 tablas.add(tabla);
        	 }
        	 rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return tablas;
    }

    /**
    * Metodo Encargado obtener la versi�n de nuestro servidor PostgreSQL
    */
    private String[] versionPostgreSQL() {
        String tiraSQL = "SELECT  version();";
        try {
        	ResultSet rs = Conexion.consultar(tiraSQL);
            if (rs.next()) {
                return rs.getString(1).split(" ", 3);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
}

