package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.NotificacionDAO;
import ucla.si.modelo.Notificacion;

@Service("servicioNotificacion")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioNotificacion {
	
	@Autowired 
	private NotificacionDAO notificacionDAO;
	
	public boolean incluirNotificacion(Notificacion notificacion){
		boolean guardado = false;
		try {
			guardado = notificacionDAO.incluirNotificacion(notificacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarNotificacion(Notificacion notificacion){
		boolean guardado = false;
		try {
			guardado = notificacionDAO.editarCita(notificacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Notificacion> listarNotificaciones(){
		List<Notificacion> notificaciones = new ArrayList<Notificacion>();
		try {
			notificaciones = notificacionDAO.listarNotificaciones();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notificaciones;
	}

}