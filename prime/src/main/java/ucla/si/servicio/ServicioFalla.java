package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.FallaDAO;
import ucla.si.modelo.Falla;
import ucla.si.modelo.Herramienta;
import ucla.si.modelo.Falla;

@Service("servicioFalla")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioFalla {
	
	@Autowired
	private FallaDAO fallaDAO;
	
	public boolean incluirFalla(Falla falla){
		boolean guardado = false;
		try {
			guardado = fallaDAO.incluirFalla(falla);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarFalla(Falla falla){
		boolean guardado = false;
		try {
			guardado = fallaDAO.editarFalla(falla);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Falla> buscarFallas(String descripcion) {
		List<Falla> fallas = new ArrayList<Falla>();
		try {
			fallas = fallaDAO.buscarFallas(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fallas;
	}
	
	public List<Falla> listarFallas(){
		List<Falla> fallas = new ArrayList<Falla>();
		try {
			fallas = fallaDAO.listarFallas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fallas;
	}
	
	public List<Falla> listarFallaXPresupuesto(long idPresupuesto){
		List<Falla> fallas = new ArrayList<Falla>();
		try {
			fallas = fallaDAO.listarFallasXPresupuesto(idPresupuesto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fallas;
	}

}
