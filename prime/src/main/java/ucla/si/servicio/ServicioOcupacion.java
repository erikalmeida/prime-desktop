package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.OcupacionDAO;
import ucla.si.modelo.Ocupacion;


@Service("servicioOcupacion")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioOcupacion {

	@Autowired
	private OcupacionDAO ocupacionDAO;
	
	public boolean incluirOcupacion(Ocupacion ocupacion){
		boolean guardado = false;
		try {
			guardado = ocupacionDAO.incluirOcupacion(ocupacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Ocupacion> buscarOcupaciones(String descripcion) {
		List<Ocupacion> ocupaciones = new ArrayList<Ocupacion>();
		try {
			ocupaciones = ocupacionDAO.buscarOcupaciones(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ocupaciones;
	}
	
	public boolean editarOcupacion(Ocupacion ocupacion){
		boolean guardado = false;
		try {
			guardado = ocupacionDAO.editarOcupacion(ocupacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Ocupacion> listarOcupaciones(){
		List<Ocupacion> ocupaciones = new ArrayList<Ocupacion>();
		try {
			ocupaciones = ocupacionDAO.listarOcupaciones();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ocupaciones;
	}

}
