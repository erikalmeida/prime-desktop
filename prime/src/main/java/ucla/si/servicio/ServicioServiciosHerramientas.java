package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.ServicioHerramientaDAO;
import ucla.si.modelo.ServiciosHerramientas;

@Service("servicioServiciosHerramientas")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioServiciosHerramientas {
	
	@Autowired
	private ServicioHerramientaDAO serviciosHerramientasDAO;
	
	public boolean incluirServiciosHerramientas(ServiciosHerramientas serviciosHerramientas){
		boolean guardado = false;
		try {
			guardado = serviciosHerramientasDAO.incluirServiciosHerramientas(serviciosHerramientas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarServiciosHerramientas(ServiciosHerramientas serviciosHerramientas){
		boolean guardado = false;
		try {
			guardado = serviciosHerramientasDAO.editarServiciosHerramientas(serviciosHerramientas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<ServiciosHerramientas> listarServiciosHerramientass(){
		List<ServiciosHerramientas> serviciosHerramientass = new ArrayList<ServiciosHerramientas>();
		try {
			serviciosHerramientass = serviciosHerramientasDAO.listarServiciosHerramientas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviciosHerramientass;
	}
	
	public List<ServiciosHerramientas> listarServiciosHerramientasXServicio(long idServicio){
		List<ServiciosHerramientas> serviciosHerramientasXServicio = new ArrayList<ServiciosHerramientas>();
		try {
			serviciosHerramientasXServicio = serviciosHerramientasDAO.listarServiciosHerramientasXServicio(idServicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviciosHerramientasXServicio;
	}

}
