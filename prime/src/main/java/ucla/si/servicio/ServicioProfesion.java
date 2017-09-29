package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.ProfesionDAO;

import ucla.si.modelo.Profesion;

@Service("servicioProfesion")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioProfesion {
	
	@Autowired
	private ProfesionDAO profesionDAO;
	
	public boolean incluirProfesion(Profesion profesion){
		boolean guardado = false;
		try {
			guardado = profesionDAO.incluirProfesion(profesion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarProfesion(Profesion profesion){
		boolean guardado = false;
		try {
			guardado = profesionDAO.editarProfesion(profesion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Profesion> buscarProfesiones(String descripcion) {
		List<Profesion> profesiones = new ArrayList<Profesion>();
		try {
			profesiones = profesionDAO.buscarProfesiones(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profesiones;
	}
	
	public List<Profesion> listarProfesiones(){
		List<Profesion> profesiones = new ArrayList<Profesion>();
		try {
			profesiones = profesionDAO.listarProfesiones();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profesiones;
	}


}
