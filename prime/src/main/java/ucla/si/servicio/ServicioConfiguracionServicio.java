package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ucla.si.dao.ConfiguracionServicioDAO;
import ucla.si.modelo.ConfiguracionServicio;

public class ServicioConfiguracionServicio {
	
	@Autowired
	private ConfiguracionServicioDAO configuracionServicioDAO;
	
	public boolean incluirConfiguracionServicio(ConfiguracionServicio configuracionServicio){
		boolean guardado = false;
		try {
			guardado = configuracionServicioDAO.incluirConfiguracionServicio(configuracionServicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarConfiguracionServicio(ConfiguracionServicio configuracionServicio){
		boolean guardado = false;
		try {
			guardado = configuracionServicioDAO.editarConfiguracionServicio(configuracionServicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<ConfiguracionServicio> listarConfiguracionServicio(){
		List<ConfiguracionServicio> configuracionServicio = new ArrayList<ConfiguracionServicio>();
		try {
			configuracionServicio = configuracionServicioDAO.listarConfiguracionServicio();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return configuracionServicio;
	}

}
