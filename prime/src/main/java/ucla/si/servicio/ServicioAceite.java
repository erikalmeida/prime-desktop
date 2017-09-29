package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.AceiteDAO;
import ucla.si.modelo.Aceite;


@Service("servicioAceite")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioAceite {
	
	
	
	@Autowired
	private AceiteDAO aceiteDAO;
	
	public boolean incluirAceite(Aceite aceite){
		boolean guardado = false;
		try {
			guardado = aceiteDAO.incluirAceite(aceite);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarAceite(Aceite aceite){
		boolean guardado = false;
		try {
			guardado = aceiteDAO.editarAceite(aceite);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Aceite> buscarAceites(String descripcion) {
		List<Aceite> aceites = new ArrayList<Aceite>();
		try {
			aceites = aceiteDAO.buscarAceites(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aceites;
	}
	
	public List<Aceite> listarAceites(){
		List<Aceite> aceites = new ArrayList<Aceite>();
		try {
			aceites = aceiteDAO.listarAceites();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aceites;
	}
	
	
	
	
	
	

}
