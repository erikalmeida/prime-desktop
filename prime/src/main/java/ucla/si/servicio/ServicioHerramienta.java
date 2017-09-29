package ucla.si.servicio;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.HerramientaDAO;
import ucla.si.dao.HerramientaDAO;
import ucla.si.modelo.Herramienta;
import ucla.si.modelo.Herramienta;




@Service("servicioHerramienta")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioHerramienta {
	
	@Autowired
	private HerramientaDAO herramientaDAO;
	
	public boolean incluirHerramienta(Herramienta herramienta){
		boolean guardado = false;
		try {
			guardado = herramientaDAO.incluirHerramienta(herramienta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarHerramienta(Herramienta herramienta){
		boolean guardado = false;
		try {
			guardado = herramientaDAO.editarHerramienta(herramienta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Herramienta> buscarHerramientas(String descripcion) {
		List<Herramienta> herramientas = new ArrayList<Herramienta>();
		try {
			herramientas = herramientaDAO.buscarHerramientas(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return herramientas;
	}
	
	public List<Herramienta> listarHerramientas(){
		List<Herramienta> herramientas = new ArrayList<Herramienta>();
		try {
			herramientas = herramientaDAO.listarHerramientas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return herramientas;
	}
	
	public List<Herramienta> listarHerramientasXServicio(long idServicio){
		List<Herramienta> herramientas = new ArrayList<Herramienta>();
		try {
			herramientas = herramientaDAO.listarHerramientasXServicio(idServicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return herramientas;
	}
	
	
	
	public List<Herramienta> listarHerramientasXEspacio(long idEspacio){
		List<Herramienta> herramientas = new ArrayList<Herramienta>();
		try {
			herramientas = herramientaDAO.listarHerramientasXEspacio(idEspacio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return herramientas;
	}

}
