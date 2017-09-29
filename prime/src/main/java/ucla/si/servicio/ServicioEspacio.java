package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.EspacioDAO;
import ucla.si.modelo.Espacio;

@Service("servicioEspacio")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioEspacio {
	
	@Autowired
	private EspacioDAO espacioDAO;
	
	public boolean incluirEspacio(Espacio espacio){
		boolean guardado = false;
		try {
			guardado = espacioDAO.incluirEspacio(espacio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarEspacio(Espacio espacio){
		boolean guardado = false;
		try {
			guardado = espacioDAO.editarEspacio(espacio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Espacio> buscarEspacios(String descripcion) {
		List<Espacio> espacios = new ArrayList<Espacio>();
		try {
			espacios = espacioDAO.buscarEspacios(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return espacios;
	}
	
	public List<Espacio> listarEspacios(){
		List<Espacio> espacios = new ArrayList<Espacio>();
		try {
			espacios = espacioDAO.listarEspacios();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return espacios;
	}
	
	public List<Espacio> listarEspaciosConfigurados(){
		List<Espacio> espacios = new ArrayList<Espacio>();
		try {
			espacios = espacioDAO.listarEspaciosConfigurados();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return espacios;
	}
	
	public List<Espacio> listarEspaciosPorAsignar(){
		List<Espacio> espacios = new ArrayList<Espacio>();
		try {
			espacios = espacioDAO.listarEspaciosPorAsignar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return espacios;
	}

}
