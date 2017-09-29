package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


import ucla.si.dao.ServicioTecnologiaDAO;
import ucla.si.modelo.ServiciosTecnologias;

@Service("servicioServiciosTecnologias")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioServiciosTecnologias {
	
	@Autowired
	private ServicioTecnologiaDAO serviciosTecnologiasDAO;
	
	public boolean incluirServiciosTecnologias(ServiciosTecnologias serviciosTecnologias){
		boolean guardado = false;
		try {
			guardado = serviciosTecnologiasDAO.incluirServiciosTecnologias(serviciosTecnologias);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarServiciosTecnologias(ServiciosTecnologias serviciosTecnologias){
		boolean guardado = false;
		try {
			guardado = serviciosTecnologiasDAO.editarServiciosTecnologias(serviciosTecnologias);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<ServiciosTecnologias> listarServiciosTecnologiass(){
		List<ServiciosTecnologias> serviciosTecnologiass = new ArrayList<ServiciosTecnologias>();
		try {
			serviciosTecnologiass = serviciosTecnologiasDAO.listarServiciosTecnologias();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviciosTecnologiass;
	}

}
