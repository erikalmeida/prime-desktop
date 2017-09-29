package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


import ucla.si.dao.CiudadDAO;
import ucla.si.modelo.Ciudad;


@Service("servicioCiudad")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)


public class ServicioCiudad {
	
	
	
	@Autowired
	private CiudadDAO ciudadDAO;
	
	public boolean incluirCiudad(Ciudad ciudad){
		boolean guardado = false;
		try {
			guardado = ciudadDAO.incluirCiudad(ciudad);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarCiudad(Ciudad ciudad){
		boolean guardado = false;
		try {
			guardado = ciudadDAO.editarCiudad(ciudad);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Ciudad> buscarCiudades(String descripcion) {
		List<Ciudad> ciudades = new ArrayList<Ciudad>();
		try {
			ciudades = ciudadDAO.buscarCiudades(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ciudades;
	}
	
	public List<Ciudad> listarCiudades(){
		List<Ciudad> ciudades = new ArrayList<Ciudad>();
		try {
			ciudades = ciudadDAO.listarCiudades();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ciudades;
	}
	
	
	
	
	
	

}