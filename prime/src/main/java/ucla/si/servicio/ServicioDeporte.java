package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.DeporteDAO;
import ucla.si.modelo.Deporte;


@Service("servicioDeporte")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioDeporte {
	
	@Autowired
	private DeporteDAO deporteDAO;
	
	public boolean incluirDeporte(Deporte deporte){
		boolean guardado = false;
		try {
			guardado = deporteDAO.incluirDeporte(deporte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}

	public List<Deporte> buscarDeportes(String descripcion) {
		List<Deporte> deportes = new ArrayList<Deporte>();
		try {
			deportes = deporteDAO.buscarDeportes(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deportes;
	}
	
	public boolean editarDeporte(Deporte deporte){
		boolean guardado = false;
		try {
			guardado = deporteDAO.editarDeporte(deporte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Deporte> listarDeportes(){
		List<Deporte> deportes = new ArrayList<Deporte>();
		try {
			deportes = deporteDAO.listarDeportes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deportes;
	}
}
