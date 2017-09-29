package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.ServicioEtapaDAO;
import ucla.si.modelo.ServiciosEtapas;

@Service("servicioServiciosEtapas")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioServiciosEtapas {
	
	@Autowired
	private ServicioEtapaDAO serviciosEtapasDAO;
	
	public boolean incluirServiciosEtapas(ServiciosEtapas serviciosEtapas){
		boolean guardado = false;
		try {
			guardado = serviciosEtapasDAO.incluirServiciosEtapas(serviciosEtapas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarServiciosEtapas(ServiciosEtapas serviciosEtapas){
		boolean guardado = false;
		try {
			guardado = serviciosEtapasDAO.editarServiciosEtapas(serviciosEtapas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<ServiciosEtapas> listarServiciosEtapass(){
		List<ServiciosEtapas> serviciosEtapass = new ArrayList<ServiciosEtapas>();
		try {
			serviciosEtapass = serviciosEtapasDAO.listarServiciosEtapas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviciosEtapass;
	}

}
