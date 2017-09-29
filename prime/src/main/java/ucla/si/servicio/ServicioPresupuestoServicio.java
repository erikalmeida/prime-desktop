package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.presupuestoServicioDAO;
import ucla.si.modelo.PresupuestoServicio;

@Service("servicioPresupuestoServicio")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioPresupuestoServicio {
	
	@Autowired
	private presupuestoServicioDAO presupuestoServicioDAO;
	
	public boolean incluirPresupuestoServicio(PresupuestoServicio presupuestoServicio){
		boolean guardado = false;
		try {
			guardado = presupuestoServicioDAO.incluirPresupuestoServicio(presupuestoServicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarPresupuestoServicio(PresupuestoServicio presupuestoServicio){
		boolean guardado = false;
		try {
			guardado = presupuestoServicioDAO.editarPresupuestoServicio(presupuestoServicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<PresupuestoServicio> listarPresupuestoServicios(){
		List<PresupuestoServicio> presupuestoServicios = new ArrayList<PresupuestoServicio>();
		try {
			presupuestoServicios = presupuestoServicioDAO.listarPresupuestoServicio();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return presupuestoServicios;
	}
	

}
