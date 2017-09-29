package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.PruebaDAO;
import ucla.si.modelo.Prueba;

@Service("servicioPrueba")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioPrueba {
	
	@Autowired
	private PruebaDAO pruebaDAO;
	
	public boolean incluirPrueba(Prueba prueba){
		boolean guardado = false;
		try {
			guardado = pruebaDAO.incluirPrueba(prueba);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarPrueba(Prueba prueba){
		boolean guardado = false;
		try {
			guardado = pruebaDAO.editarPrueba(prueba);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Prueba> listarPruebas(){
		List<Prueba> pruebas = new ArrayList<Prueba>();
		try {
			pruebas = pruebaDAO.listarPruebas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pruebas;
	}

}
