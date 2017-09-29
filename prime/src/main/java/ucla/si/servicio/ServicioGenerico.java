package ucla.si.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.GenericoDAO;


@Service("servicioGenerico")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioGenerico {
	
	@Autowired
	protected GenericoDAO genericoDAO;
	
	protected boolean incluirObject(Object object){
		boolean guardado = false;
		try {
			guardado = genericoDAO.incluirObject(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	protected boolean editarObject(Object object){
		boolean guardado = false;
		try {
			guardado = genericoDAO.editarObject(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}	

}
