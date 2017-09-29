package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.CalificacionDAO;
import ucla.si.modelo.Calificacion;


@Service("servicioCalificacion")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)


public class ServicioCalificacion {
	
	

	@Autowired
	private CalificacionDAO calificacionDAO;
	
	public boolean incluirCalificacion(Calificacion calificacion){
		boolean guardado = false;
		try {
			guardado = calificacionDAO.incluirCalificacion(calificacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarCalificacion(Calificacion calificacion){
		boolean guardado = false;
		try {
			guardado = calificacionDAO.editarCalificacion(calificacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Calificacion> buscarCalificaciones(String descripcion) {
		List<Calificacion> calificacions = new ArrayList<Calificacion>();
		try {
			calificacions = calificacionDAO.buscarCalificaciones(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return calificacions;
	}
	
	public List<Calificacion> listarCalificaciones(){
		List<Calificacion> calificacions = new ArrayList<Calificacion>();
		try {
			calificacions = calificacionDAO.listarCalificaciones();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return calificacions;
	}
	
	

}
