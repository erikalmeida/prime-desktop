package ucla.si.servicio;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.CombustibleDAO;
import ucla.si.modelo.Combustible;


@Service("servicioCombustible")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioCombustible {

	

	@Autowired
	private CombustibleDAO CombustibleDAO;
	
	public boolean incluirCombustible(Combustible Combustible){
		boolean guardado = false;
		try {
			guardado = CombustibleDAO.incluirCombustible(Combustible);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarCombustible(Combustible Combustible){
		boolean guardado = false;
		try {
			guardado = CombustibleDAO.editarCombustible(Combustible);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Combustible> buscarCombustibles(String descripcion) {
		List<Combustible> combustibles = new ArrayList<Combustible>();
		try {
			combustibles = CombustibleDAO.buscarCombustibles(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return combustibles;
	}
	
	public List<Combustible> listarCombustibles(){
		List<Combustible> Combustibles = new ArrayList<Combustible>();
		try {
			Combustibles = CombustibleDAO.listarCombustibles();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Combustibles;
	}

}
