package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.PresupuestoTipoRepuestoDAO;
import ucla.si.modelo.PresupuestoTipoRepuesto;

@Service("servicioPresupuestoTipoRepuesto")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioPresupuestoTipoRepuesto {
	
	@Autowired
	private PresupuestoTipoRepuestoDAO presupuestoTipoRepuestoDAO;
	
	public boolean incluir(PresupuestoTipoRepuesto presupuestoTipoRepuesto){
		boolean guardado = false;
		try {
			guardado = presupuestoTipoRepuestoDAO.incluirPresupuestoTipoRepuesto(presupuestoTipoRepuesto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarPresupuestoTipoRepuesto(PresupuestoTipoRepuesto presupuestoTipoRepuesto){
		boolean guardado = false;
		try {
			guardado = presupuestoTipoRepuestoDAO.editarPresupuestoTipoRepuesto(presupuestoTipoRepuesto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<PresupuestoTipoRepuesto> listarPresupuestoTipoRepuestos(){
		List<PresupuestoTipoRepuesto> presupuestoTipoRepuestos = new ArrayList<PresupuestoTipoRepuesto>();
		try {
			presupuestoTipoRepuestos = presupuestoTipoRepuestoDAO.listarPresupuestoTipoRepuestos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return presupuestoTipoRepuestos;
	}
	

}
