package ucla.si.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.SistemaDAO;
import ucla.si.modelo.Sistema;


@Service("servicioSistema")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioSistema{
	
	@Autowired
	private SistemaDAO sistemaDAO;
	
	public Sistema buscar(){
		Sistema sistema = null;
		try {
			sistema = sistemaDAO.buscar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sistema;
	}
	
	public boolean incluirSistema(Sistema sistema){
		boolean guardado = false;
		try {
			guardado = sistemaDAO.incluirSistema(sistema);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarSistema(Sistema sistema){
		boolean guardado = false;
		try {
			guardado = sistemaDAO.editarSistema(sistema);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
}
