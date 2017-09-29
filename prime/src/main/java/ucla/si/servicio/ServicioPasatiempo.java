package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.PasatiempoDAO;
import ucla.si.modelo.Pasatiempo;

@Service("servicioPasatiempo")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioPasatiempo {
	
	@Autowired
	private PasatiempoDAO pasatiempoDAO;
	
	public boolean incluirPasatiempo(Pasatiempo pasatiempo){
		boolean guardado = false;
		try {
			guardado = pasatiempoDAO.incluirPasatiempo(pasatiempo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarPasatiempo(Pasatiempo pasatiempo){
		boolean guardado = false;
		try {
			guardado = pasatiempoDAO.editarPasatiempo(pasatiempo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Pasatiempo> buscarPasatiempos(String descripcion) {
		List<Pasatiempo> pasatiempos = new ArrayList<Pasatiempo>();
		try {
			pasatiempos = pasatiempoDAO.buscarPasatiempos(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pasatiempos;
	}
	
	public List<Pasatiempo> listarPasatiempos(){
		List<Pasatiempo> pasatiempos = new ArrayList<Pasatiempo>();
		try {
			pasatiempos = pasatiempoDAO.listarPasatiempos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pasatiempos;
	}

}
