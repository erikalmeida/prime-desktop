package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.AccionDAO;
import ucla.si.modelo.Accion;


@Service("servicioAccion")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioAccion {

	@Autowired
	private AccionDAO accionDAO;
	
	public boolean incluirAccion(Accion accion){
		boolean guardado = false;
		try {
			guardado = accionDAO.incluirAccion(accion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarAccion(Accion accion){
		boolean guardado = false;
		try {
			guardado = accionDAO.editarAccion(accion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Accion> listarAcciones(){
		List<Accion> tipoClases = new ArrayList<Accion>();
		try {
			tipoClases = accionDAO.listarAcciones();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoClases;
	}
	
	public List<Accion> listarAccionesSinDesplegar(){
		List<Accion> tipoClases = new ArrayList<Accion>();
		try {
			tipoClases = accionDAO.listarAccionesSinDesplegar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoClases;
	}
	
	public Accion buscarAccion(String nombre) {
		Accion accion = null;
		try {
			accion = accionDAO.buscarAccion(nombre);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accion;
	}
	
}
