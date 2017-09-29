package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.RectificacionDAO;
import ucla.si.modelo.Rectificacion;


@Service("servicioRectificacion")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioRectificacion {
	
	
	@Autowired
	private RectificacionDAO rectificacionDAO;
	
	public boolean incluirRectificacion(Rectificacion rectificacion){
		boolean guardado = false;
		try {
			guardado = rectificacionDAO.incluirRectificacion(rectificacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Rectificacion> buscarRectificaciones(String descripcion) {
		List<Rectificacion> rectificaciones = new ArrayList<Rectificacion>();
		try {
			rectificaciones = rectificacionDAO.buscarRectificaciones(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rectificaciones;
	}
	
	public boolean editarRectificacion(Rectificacion rectificacion){
		boolean guardado = false;
		try {
			guardado = rectificacionDAO.editarRectificacion(rectificacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Rectificacion> listarRectificaciones(){
		List<Rectificacion> rectificaciones = new ArrayList<Rectificacion>();
		try {
			rectificaciones = rectificacionDAO.listartiposRectificaciones();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rectificaciones;
	}
	
	

}
