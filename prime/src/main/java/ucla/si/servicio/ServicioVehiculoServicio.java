package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.ServicioHerramientaDAO;
import ucla.si.dao.VehiculoServicioDAO;
import ucla.si.modelo.ServiciosHerramientas;
import ucla.si.modelo.VehiculoServicio;

@Service("servicioVehiculoServicio")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioVehiculoServicio {
	
	@Autowired
	private VehiculoServicioDAO vehiculoServicioDAO;
	
	public boolean incluirVehiculoServicio(VehiculoServicio vehiculoServicio){
		boolean guardado = false;
		try {
			guardado = vehiculoServicioDAO.incluirVehiculoServicio(vehiculoServicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarVehiculoServicio(VehiculoServicio vehiculoServicio){
		boolean guardado = false;
		try {
			guardado = vehiculoServicioDAO.editarVehiculoServicio(vehiculoServicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<VehiculoServicio> listarVehiculoServicios(){
		List<VehiculoServicio> vehiculoServicios = new ArrayList<VehiculoServicio>();
		try {
			vehiculoServicios = vehiculoServicioDAO.listarVehiculoServicio();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehiculoServicios;
	}
	
	

}
