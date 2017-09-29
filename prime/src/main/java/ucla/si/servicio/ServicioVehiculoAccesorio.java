package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.VehiculoAccesorioDAO;
import ucla.si.modelo.VehiculoAccesorio;


@Service("servicioVehiculoAccesorio")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioVehiculoAccesorio {
	
	@Autowired
	private VehiculoAccesorioDAO vehiculoAccesorioDAO;
	
	public boolean incluirVehiculoAccesorio(VehiculoAccesorio vehiculoAccesorio){
		boolean guardado = false;
		try {
			guardado = vehiculoAccesorioDAO.incluirVehiculoAccesorio(vehiculoAccesorio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarVehiculoAccesorio(VehiculoAccesorio vehiculoAccesorio){
		boolean guardado = false;
		try {
			guardado = vehiculoAccesorioDAO.editarVehiculoAccesorio(vehiculoAccesorio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<VehiculoAccesorio> listarVehiculoAccesorios(){
		List<VehiculoAccesorio> vehiculoAccesorios = new ArrayList<VehiculoAccesorio>();
		try {
			vehiculoAccesorios = vehiculoAccesorioDAO.listarVehiculoAccesorios();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehiculoAccesorios;
	}
	
	

}
