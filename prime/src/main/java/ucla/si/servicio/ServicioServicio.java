package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.ServicioDAO;
import ucla.si.modelo.Etapa;
import ucla.si.modelo.Herramienta;
import ucla.si.modelo.Servicio;

@Service("servicioServicio")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioServicio {

	@Autowired
	private ServicioDAO servicioDAO;

	public boolean incluirServicio(Servicio servicio) {
		boolean guardado = false;
		try {
			guardado = servicioDAO.incluirServicio(servicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}

	public boolean editarServicio(Servicio servicio) {
		boolean guardado = false;
		try {
			guardado = servicioDAO.editarServicio(servicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}

	public List<Servicio> buscarServicios(String descripcion) {
		List<Servicio> servicios = new ArrayList<Servicio>();
		try {
			servicios = servicioDAO.buscarServicios(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicios;
	}

	public List<Servicio> listarServicios() {
		List<Servicio> servicios = new ArrayList<Servicio>();
		try {
			servicios = servicioDAO.listarServicios();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicios;
	}
	
	public List<Servicio> listarServiciosConfigurados() {
		List<Servicio> servicios = new ArrayList<Servicio>();
		try {
			servicios = servicioDAO.listarServiciosConfigurados();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicios;
	}

	public List<Servicio> listarServiciosXHerramientas(long herramientaId) {
		List<Servicio> servicios = new ArrayList<Servicio>();
		try {
			servicios = servicioDAO.listarServiciosXHerramientas(herramientaId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicios;
	}

	public List<Servicio> listarServicioXPresupuesto(long idPresupuesto) {
		List<Servicio> servicios = new ArrayList<Servicio>();
		try {
			servicios = servicioDAO.listarServiciosXPresupuesto(idPresupuesto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicios;
	}

	public Servicio getServicio(Long id) {
		Servicio servicio = new Servicio();
		try {
			servicio = servicioDAO.getServicio(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicio;
	}

}
