package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.ClaseDAO;
import ucla.si.modelo.Clase;


@Service("servicioClase")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioClase {
	
	@Autowired
	private ClaseDAO claseDAO;
	
	public boolean incluirClase(Clase clase){
		boolean guardado = false;
		try {
			guardado = claseDAO.incluirClase(clase);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarClase(Clase clase){
		boolean guardado = false;
		try {
			guardado = claseDAO.editarClase(clase);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Clase> buscarClases(String descripcion) {
		List<Clase> clases = new ArrayList<Clase>();
		try {
			clases = claseDAO.buscarClases(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clases;
	}
	
	public List<Clase> listarClases(){
		List<Clase> clases = new ArrayList<Clase>();
		try {
			clases = claseDAO.listarClases();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clases;
	}
	

}
