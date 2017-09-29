package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.TipoNotificacionDAO;
import ucla.si.modelo.TipoEventualidad;
import ucla.si.modelo.TipoNotificacion;

@Service("servicioTipoNotificacion")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioTipoNotificacion {
	
	@Autowired
	private TipoNotificacionDAO tipoNotificacionDAO;
	
	public boolean incluirTipoNotificacion(TipoNotificacion tipoNotificacion){
		boolean guardado = false;
		try {
			guardado = tipoNotificacionDAO.incluirTipoNotificacion(tipoNotificacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarTipoEventualidad(TipoNotificacion tipoNotificacion){
		boolean guardado = false;
		try {
			guardado = tipoNotificacionDAO.editarTipoNotificacion(tipoNotificacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<TipoNotificacion> listarTipoNotificacion(){
		List<TipoNotificacion> tipoNotificaciones = new ArrayList<TipoNotificacion>();
		try {
			tipoNotificaciones = tipoNotificacionDAO.listartipoNotificaciones();		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoNotificaciones;
	}

}