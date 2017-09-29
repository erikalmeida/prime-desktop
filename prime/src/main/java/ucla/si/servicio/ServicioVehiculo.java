package ucla.si.servicio;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.VehiculoDAO;
import ucla.si.modelo.Herramienta;
import ucla.si.modelo.Tecnologia;
import ucla.si.modelo.Vehiculo;





@Service("servicioVehiculo")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioVehiculo {
	
	@Autowired
	private VehiculoDAO vehiculoDAO;
	
	public boolean incluirVehiculo(Vehiculo vehiculo){
		boolean guardado = false;
		try {
			guardado = vehiculoDAO.incluirVehiculo(vehiculo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarVehiculo(Vehiculo vehiculo){
		boolean guardado = false;
		try {
			guardado = vehiculoDAO.editarVehiculo(vehiculo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	
	
	public List<Vehiculo> listarVehiculos(){
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		try {
			vehiculos = vehiculoDAO.listarVehiculos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehiculos;
	}
	
	public List<Vehiculo> listarVehiculosXServicio(long idServicio){
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		try {
			vehiculos = vehiculoDAO.listarVehiculosXServicio(idServicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehiculos;
	}
	
	

}
