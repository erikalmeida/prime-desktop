package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.ViajeDAO;
import ucla.si.modelo.Viaje;

@Service("servicioViaje")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioViaje {
	
	@Autowired
	private ViajeDAO viajeDAO;
	
	public boolean incluirViaje(Viaje viaje){
		boolean guardado = false;
		try {
			guardado = viajeDAO.incluirViaje(viaje);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}

	public boolean editarViaje(Viaje viaje){
		boolean guardado = false;
		try {
			guardado = viajeDAO.editarViaje(viaje);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Viaje> buscarViajes(String descripcion) {
		List<Viaje> viajes = new ArrayList<Viaje>();
		try {
			viajes = viajeDAO.buscarViajes(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return viajes;
	}
	
	public List<Viaje> listarViajes(){
		List<Viaje> deportes = new ArrayList<Viaje>();
		try {
			deportes = viajeDAO.listarViajes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deportes;
	}
}