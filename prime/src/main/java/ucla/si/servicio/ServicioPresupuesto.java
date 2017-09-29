package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucla.si.dao.EspacioDAO;
import ucla.si.dao.PresupuestoDAO;
import ucla.si.modelo.Espacio;
import ucla.si.modelo.Persona;
import ucla.si.modelo.Presupuesto;
import ucla.si.modelo.Servicio;
import ucla.si.modelo.Usuario;
import ucla.si.modelo.Vehiculo;

@Service("servicioPresupuesto")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioPresupuesto {
	
	@Autowired
	private PresupuestoDAO presupuestoDAO;
	
	public boolean incluirPresupuesto(Presupuesto presupuesto){
		boolean guardado = false;
		try {
			guardado = presupuestoDAO.incluirPresupuesto(presupuesto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarPresupuesto(Presupuesto presupuesto){
		boolean guardado = false;
		try {
			guardado = presupuestoDAO.editarServicio(presupuesto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}

	public List<Presupuesto> listarPresupuestos(){
		List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
		try {
			presupuestos = presupuestoDAO.listarPresupuestos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return presupuestos;
	}
	
	public List<Presupuesto> listarPresupuestosOrdenDeServicio(){
		List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
		try {
			presupuestos = presupuestoDAO.listarPresupuestosOrdenDeServicio();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return presupuestos;
	}
	
	public Presupuesto buscarPresupuesto(long id_cita){
		Presupuesto presupuesto =new Presupuesto();
		try {
			presupuesto = presupuestoDAO.buscarPresupuesto(id_cita);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return presupuesto;
	}
	
	public Presupuesto getPresupuesto(Long id){
		Presupuesto presupuesto = new Presupuesto();
		try {
			presupuesto = presupuestoDAO.getPresupuesto(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return presupuesto;
	}

	public String nombreUsuario(Long presupuestoId) {
			String nombre ="";
			try {
				nombre = presupuestoDAO.nombreUsuario(presupuestoId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return nombre;
	}
	
	public String nombreMarca(Long presupuestoId) {
		String nombre ="";
		try {
			nombre = presupuestoDAO.nombreMarca(presupuestoId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nombre;
	}
	
	public String nombreModelo(Long presupuestoId) {
		String nombre ="";
		try {
			nombre = presupuestoDAO.nombreModelo(presupuestoId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nombre;
	}
	
	public List<Servicio> listarServiciosXPresupuesto(Long presupuestoId) {
		return presupuestoDAO.listarServiciosXPresupuesto(presupuestoId);
	}

	public Usuario nestedUsuario(Long presupuestoId) {
		return presupuestoDAO.nestedUsuario(presupuestoId);
	}
	public Persona nestedPersona(Long presupuestoId) {
		return presupuestoDAO.nestedPersona(presupuestoId);
	}
	
	public Vehiculo nestedVehiculo(Long presupuestoId) {
		return presupuestoDAO.nestedVehiculo(presupuestoId);
	}

}
