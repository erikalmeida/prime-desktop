package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.OrdenServicioDAO;
import ucla.si.modelo.OrdenServicio;

@Service("servicioOrdenServicio")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioOrdenServicio {
	
	@Autowired
	private OrdenServicioDAO ordenServicioDAO;
	
	public boolean incluirOrdenServicio(OrdenServicio ordenServicio){
		boolean guardado = false;
		try {
			guardado = ordenServicioDAO.incluirOrdenServicio(ordenServicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarOrdenServicio(OrdenServicio ordenServicio){
		boolean guardado = false;
		try {
			guardado = ordenServicioDAO.editarOrdenServicio(ordenServicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<OrdenServicio> listarOrdenServicios(){
		List<OrdenServicio> ordenServicios = new ArrayList<OrdenServicio>();
		try {
			ordenServicios = ordenServicioDAO.listarOrdenServicios();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordenServicios;
	}
	
	public List<OrdenServicio> listarOrdenServiciosEjecutando(){
		List<OrdenServicio> ordenServicios = new ArrayList<OrdenServicio>();
		try {
			ordenServicios = ordenServicioDAO.listarOrdenServiciosEjecutando();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordenServicios;
	}
	
	public List<OrdenServicio> listarOrdenServiciosFinalizados(){
		List<OrdenServicio> ordenServicios = new ArrayList<OrdenServicio>();
		try {
			ordenServicios = ordenServicioDAO.listarOrdenServiciosFinalizado();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordenServicios;
	}

}
