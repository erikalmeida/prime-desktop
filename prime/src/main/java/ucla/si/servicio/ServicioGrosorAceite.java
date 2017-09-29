package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.GrosorAceiteDAO;
import ucla.si.dao.GrosorAceiteDAO;
import ucla.si.modelo.GrosorAceite;
import ucla.si.modelo.GrosorAceite;


@Service("servicioGrosorAceite")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)


public class ServicioGrosorAceite {
	
	
	@Autowired
	private GrosorAceiteDAO grosorAceiteDAO;
	
	public boolean incluirGrosorAceite(GrosorAceite grosorAceite){
		boolean guardado = false;
		try {
			guardado = grosorAceiteDAO.incluirGrosorAceite(grosorAceite);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarGrosorAceite(GrosorAceite grosorAceite){
		boolean guardado = false;
		try {
			guardado = grosorAceiteDAO.editarGrosorAceite(grosorAceite);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<GrosorAceite> buscarGrosorAceites(String descripcion) {
		List<GrosorAceite> grosorAceites = new ArrayList<GrosorAceite>();
		try {
			grosorAceites = grosorAceiteDAO.buscarGrosorAceites(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grosorAceites;
	}
	
	public List<GrosorAceite> listarGrosorAceites(){
		List<GrosorAceite> grosorAceites = new ArrayList<GrosorAceite>();
		try {
			grosorAceites = grosorAceiteDAO.listartiposGrosorAceites();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grosorAceites;
	}
	

}
