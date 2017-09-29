package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.EspacioHerramientaDAO;
import ucla.si.modelo.EspacioHerramienta;

@Service("servicioEspacioHerramienta")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioEspacioHerramienta {
	
	@Autowired
	private EspacioHerramientaDAO espacioHerramientaDAO;
	
	public boolean incluirEspacioHerramienta(EspacioHerramienta espacioHerramienta){
		boolean guardado = false;
		try {
			guardado = espacioHerramientaDAO.incluirEspacioHerramienta(espacioHerramienta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarEspacioHerramienta(EspacioHerramienta espacioHerramienta){
		boolean guardado = false;
		try {
			guardado = espacioHerramientaDAO.editarServiciosHerramientas(espacioHerramienta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<EspacioHerramienta> listarEspacioHerramienta(){
		List<EspacioHerramienta> espacioHerramientas = new ArrayList<EspacioHerramienta>();
		try {
			espacioHerramientas = espacioHerramientaDAO.listarEspacioHerramienta();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return espacioHerramientas;
	}

}
