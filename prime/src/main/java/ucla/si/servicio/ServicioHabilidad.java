package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.HabilidadDAO;
import ucla.si.modelo.Habilidad;


@Service("servicioHabilidad")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioHabilidad {
	
	

	@Autowired
	private HabilidadDAO habilidadDAO;
	
	public boolean incluirHabilidad(Habilidad habilidad){
		boolean guardado = false;
		try {
			guardado = habilidadDAO.incluirHabilidad(habilidad);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarHabilidad(Habilidad habilidad){
		boolean guardado = false;
		try {
			guardado = habilidadDAO.editarHabilidad(habilidad);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Habilidad> buscarHabilidades(String descripcion) {
		List<Habilidad> habilidades = new ArrayList<Habilidad>();
		try {
			habilidades = habilidadDAO.buscarHabilidades(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return habilidades;
	}
	
	public List<Habilidad> listarHabilidades(){
		List<Habilidad> habilidades = new ArrayList<Habilidad>();
		try {
			habilidades = habilidadDAO.listartiposHabilidades();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return habilidades;
	}
	
	
	

}
