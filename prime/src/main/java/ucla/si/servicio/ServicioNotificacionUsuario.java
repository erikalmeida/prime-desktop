package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.NotificacionUsuarioDAO;
import ucla.si.modelo.NotificacionUsuario;

@Service("servicioNotificacionUsuario")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioNotificacionUsuario {
	
	@Autowired
	private NotificacionUsuarioDAO notificacionUsuarioDAO;
	
	public boolean incluirNotificacionUsuario(NotificacionUsuario notificacionUsuario){
		boolean guardado = false;
		try {
			guardado = notificacionUsuarioDAO.incluirNotificacionUsuario(notificacionUsuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarNotificacionUsuario(NotificacionUsuario notificacionUsuario){
		boolean guardado = false;
		try {
			guardado = notificacionUsuarioDAO.editarNotificacionUsuario(notificacionUsuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	
	public List<NotificacionUsuario> listarNotificacionUsuarios(){
		List<NotificacionUsuario> notificacionUsuarios = new ArrayList<NotificacionUsuario>();
		try {
			notificacionUsuarios = notificacionUsuarioDAO.listarNotificacionUsuarios();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notificacionUsuarios;
	}
	

}