package ucla.si.dao.movil;

import bean.Conexion;

public class ConexionDAO {
	
	public ConexionDAO() {
		super();	
		Conexion.establecerPropiedadesConexion("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/", "prime_prueba", "postgres", "postgres");
	}	
}