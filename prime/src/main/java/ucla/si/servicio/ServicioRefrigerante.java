package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.RefrigeranteDAO;
import ucla.si.modelo.Refrigerante;


@Service("servicioRefrigerante")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioRefrigerante {

	@Autowired
	private RefrigeranteDAO refrigeranteDAO;
	
	public boolean incluirRefrigerante(Refrigerante refrigerante){
		boolean guardado = false;
		try {
			guardado = refrigeranteDAO.incluirRefrigerante(refrigerante);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarRefrigerante(Refrigerante refrigerante){
		boolean guardado = false;
		try {
			guardado = refrigeranteDAO.editarRefrigerante(refrigerante);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Refrigerante> buscarRefrigerantes(String descripcion) {
		List<Refrigerante> refrigerantes = new ArrayList<Refrigerante>();
		try {
			refrigerantes = refrigeranteDAO.buscarRefrigerantes(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return refrigerantes;
	}
	
	public List<Refrigerante> listarRefrigerantes(){
		List<Refrigerante> refrigerantes = new ArrayList<Refrigerante>();
		try {
			refrigerantes = refrigeranteDAO.listarRefrigerantes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return refrigerantes;
	}
	

}
