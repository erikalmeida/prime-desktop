package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.EspacioMecanicoDAO;
import ucla.si.modelo.EspacioMecanico;

@Service("servicioEspacioMecanico")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioEspacioMecanico {
	
	@Autowired
	private EspacioMecanicoDAO espacioMecanicoDAO;
	
	public boolean incluirEspacioMecanico(EspacioMecanico espacioMecanico){
		boolean guardado = false;
		try {
			guardado = espacioMecanicoDAO.incluirEspacioMecanico(espacioMecanico);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarEspacioMecanico(EspacioMecanico espacioMecanico){
		boolean guardado = false;
		try {
			guardado = espacioMecanicoDAO.editarServiciosMecanicos(espacioMecanico);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<EspacioMecanico> listarEspacioMecanico(){
		List<EspacioMecanico> espacioMecanicos = new ArrayList<EspacioMecanico>();
		try {
			espacioMecanicos = espacioMecanicoDAO.listarEspacioMecanico();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return espacioMecanicos;
	}

}
