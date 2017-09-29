package ucla.si.dao.movil;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Conexion;
import ucla.si.modelo.movil.Presupuesto;
import ucla.si.modelo.movil.Calificacion;
import ucla.si.modelo.movil.Log;
import ucla.si.modelo.movil.Notificacion;
import ucla.si.modelo.movil.Sistema;
import ucla.si.modelo.movil.Usuario;

public class PeticionesDAO extends ConexionDAO {

	public PeticionesDAO() {
		// System.out.println("La lista de Profesores se creo:
		// "+insertarProfesores());
	}

	public Log consultarLog(Integer id) {
		Log log = null;
		String tiraSQL = "Select * from log where id ='" + id + "' ";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			if (resultSet.next()) {
				String message = resultSet.getString("message");
				Date date = resultSet.getDate("date");
				log = new Log(id, message, date);
			}
			resultSet.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return log;
	}

	public List<Log> consultarLogs() {
		List<Log> logs = new ArrayList<Log>();
		String tiraSQL = "Select * from log ";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String message = resultSet.getString("message");
				Date date = resultSet.getDate("date");
				Log log = new Log(id, message, date);
				logs.add(log);
			}
			resultSet.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return logs;
	}

	public List<Notificacion> consultarNotificaciones(String idusuario) {
		List<Notificacion> notificaciones = new ArrayList<Notificacion>();
		Integer idusu = Integer.parseInt(idusuario);
		String tiraSQL = "Select notificacion.id, notificacion.nombre, notificacion.descripcion, notificacion.fechacreacion, notificacion.idtiponotificacion, notificacion.idpromocion, notificacion.idpresupuesto, notificacion.ideventualidad, notificacion.estatus FROM notificacion INNER JOIN notificacionusuario ON notificacion.id = notificacionusuario.idnotificacion AND notificacionusuario.idusuario = '"
				+ idusu + "'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String nombre = resultSet.getString("nombre");
				String descripcion = resultSet.getString("descripcion");
				Date fechacreacion = resultSet.getDate("fechacreacion");
				Long idtiponotificacion = resultSet.getLong("idtiponotificacion");
				Long idpromocion = resultSet.getLong("idpromocion");
				Long idpresupuesto = resultSet.getLong("idpresupuesto");
				// Long idordenentrega = resultSet.getLong("idordenentrega");
				Long ideventualidad = resultSet.getLong("ideventualidad");
				String estatus = resultSet.getString("estatus");
				Notificacion notificacion = new Notificacion(id, nombre, descripcion, fechacreacion, idtiponotificacion,
						idpromocion, idpresupuesto, ideventualidad, estatus);
				notificaciones.add(notificacion);
			}
			resultSet.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return notificaciones;
	}

	public Sistema buscarSistema() {
		Sistema sistema = null;
		String tiraSQL = "Select * from sistema limit 1";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			if (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String correo = resultSet.getString("correo");
				String mapa = resultSet.getString("mapa");
				String celular = resultSet.getString("celular");
				String direccion = resultSet.getString("direccion");
				sistema = new Sistema(id, correo, mapa, celular, direccion);
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sistema;
	}

	
	
	
	public Presupuesto buscarPresupuesto(String idp) {
		
		int i = Integer.parseInt(idp);
		Presupuesto presupuesto = null;
		String tiraSQL = "Select * from presupuesto where id= '"+idp+"'  ";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			if (resultSet.next()) {
				Long idpresupuesto = resultSet.getLong("id");
				//String descripcion = resultSet.getString("descripcion");
				float monto_total = resultSet.getFloat("monto_total");
				Date fechaCreacion = resultSet.getDate("fechaCreacion");
				String estatus = resultSet.getString("estatus");
				presupuesto = new Presupuesto(idpresupuesto, monto_total, fechaCreacion,estatus);
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return presupuesto;
	}
	/*
	 * public Usuario buscarUsuario(String correo){ Usuario usuario = null;
	 * String tiraSQL = "Select * from usuario where correo = '"+correo+"' ";
	 * ResultSet resultSet = Conexion.consultar(tiraSQL); try {
	 * if(resultSet.next()){ Long id = resultSet.getLong("id"); String username
	 * = resultSet.getString("correo"); String contrasenna =
	 * resultSet.getString("contrasenna"); usuario = new Usuario();
	 * usuario.setId(id); usuario.setContrasenna(contrasenna);
	 * usuario.setCorreo(username); } resultSet.close(); } catch (Exception e) {
	 * e.printStackTrace(); } return usuario; }
	 */

	public Usuario buscarUsuario(String correo) {
		Usuario usuario = null;
		String tiraSQL = "Select * from usuario where correo = '" + correo + "' ";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			if (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String contrasenna = resultSet.getString("contrasenna");
				usuario = new Usuario();
				usuario.setId(id);
				usuario.setContrasenna(contrasenna);
				usuario.setCorreo(correo);
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	
	
	
	
	
	
	public void actualizarPresupuesto(String id, String estado){

		
		   Integer idpresupuesto = Integer.parseInt(id);
			String tiraSQL = "UPDATE presupuesto set estado = '"+estado+"' WHERE id= "+idpresupuesto;
			Conexion.ejecutar(tiraSQL);		
	
}
	
	
	public void insertarCalificacion(String idordenentrega, String comentario, String calificacionServicio, String calificacionInstalacion, 
			 String calificacionAtencion){
		
		Integer idorden = Integer.parseInt(idordenentrega);
		Integer calservicio = Integer.parseInt(calificacionServicio);
		Integer calinstalacion = Integer.parseInt(calificacionInstalacion);
		Integer calatencion = Integer.parseInt(calificacionAtencion);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String date2 = dateFormat.format(date);


		String tiraSQL ="INSERT INTO calificacion "
				+"(id,comentario,fecha,calificacionServicio,calificacionInstalacion,calificacionAtencion,estatus,idordenentrega) "
				+"VALUES(nextval('calificacion_id_seq'),'"+comentario+"','"+date2+"',"+calservicio+","+calinstalacion+","
				+calatencion+",'Activa',"+idorden+")";

		
		Conexion.ejecutar(tiraSQL);

}

	// public Profesor consultarProfesor(String cedula){
	// Profesor profesor = null;
	// String tiraSQL ="Select * from profesor where cedula ='"+cedula+"' ";
	// ResultSet resultSet = Conexion.consultar(tiraSQL);
	// try {
	// if(resultSet.next()){
	// String nombre = resultSet.getString("nombre");
	// String apellido = resultSet.getString("apellido");
	// String telefono = resultSet.getString("telefono");
	// String estatus = resultSet.getString("estatus");
	// profesor = new Profesor(cedula,nombre,apellido,telefono,estatus);
	// }
	// resultSet.close();
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return profesor;
	// }

	// public ArrayList<Profesor> consultarProfesores(){
	// ArrayList<Profesor> profesores = new ArrayList<Profesor>();
	// String tiraSQL ="Select * from profesor ";
	// ResultSet resultSet = Conexion.consultar(tiraSQL);
	// try {
	// while(resultSet.next()){
	// String cedula = resultSet.getString("cedula");
	// String nombre = resultSet.getString("nombre");
	// String apellido = resultSet.getString("apellido");
	// String telefono = resultSet.getString("telefono");
	// String estatus = resultSet.getString("estatus");
	// Profesor profesor = new
	// Profesor(/*cedula,nombre,apellido,telefono,estatus*/);
	// profesores.add(profesor);
	// }
	// resultSet.close();
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return profesores;
	// }

	public boolean insertarProfesores() {
		boolean estado = false;
		// ArrayList<Profesor> profesores = new ArrayList<Profesor>();
		// profesores = consultarProfesores();
		// if(profesores.isEmpty()){
		// Profesor profesor1 = new
		// Profesor("1","jose","ramos","04160000000","Activo");
		// profesores.add(profesor1);
		// Profesor profesor2 = new
		// Profesor("2","asbel","maestre","04160000001","Activo");
		// profesores.add(profesor2);
		// Profesor profesor3 = new
		// Profesor("3","sandra","Gutierrez","04160000002","Activo");
		// profesores.add(profesor3);
		// Profesor profesor4 = new
		// Profesor("4","maria","Dorante","04160000003","Activo");
		// profesores.add(profesor4);

		// for(int i = 0; i < profesores.size(); i++){
		// String tiraSQL = "INSERT INTO profesor "+
		// "(cedula,nombre,apellido,telefono,estatus)"+
		// "VALUES('"+profesores.get(i).getCedula()+"','"+profesores.get(i).getNombre()+
		// "','"+profesores.get(i).getApellido()+"','"+profesores.get(i).getTelefono()+
		// "','"+profesores.get(i).getEstatus()+"')";
		// Conexion.ejecutar(tiraSQL);
		// }
		// estado = true;
		// }
		return estado;
	}

}