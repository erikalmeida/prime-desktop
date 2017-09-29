package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.EspacioTecnologiaDAO;
import ucla.si.modelo.EspacioTecnologia;

@Service("servicioEspacioTecnologia")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioEspacioTecnologia {
	
	@Autowired
	private EspacioTecnologiaDAO espacioTecnologiaDAO;
	
	public boolean incluirEspacioTecnologia(EspacioTecnologia espacioTecnologia){
		boolean guardado = false;
		try {
			guardado = espacioTecnologiaDAO.incluirEspacioTecnologia(espacioTecnologia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarEspacioTecnologia(EspacioTecnologia espacioTecnologia){
		boolean guardado = false;
		try {
			guardado = espacioTecnologiaDAO.editarServiciosTecnologias(espacioTecnologia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<EspacioTecnologia> listarEspacioTecnologia(){
		List<EspacioTecnologia> espacioTecnologias = new ArrayList<EspacioTecnologia>();
		try {
			espacioTecnologias = espacioTecnologiaDAO.listarEspacioTecnologia();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return espacioTecnologias;
	}

}
