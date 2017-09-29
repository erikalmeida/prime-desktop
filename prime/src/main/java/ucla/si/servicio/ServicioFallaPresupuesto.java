package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.FallaPresupuestoDAO;
import ucla.si.modelo.FallaPresupuesto;

@Service("servicioFallaPresupuesto")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioFallaPresupuesto {
	
	@Autowired
	private FallaPresupuestoDAO fallaPresupuestoDAO;
	
	public boolean incluirFallaPresupuesto(FallaPresupuesto fallaPresupuesto){
		boolean guardado = false;
		try {
			guardado = fallaPresupuestoDAO.incluirFallaPresupuesto(fallaPresupuesto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarFallaPresupuesto(FallaPresupuesto fallaPresupuesto){
		boolean guardado = false;
		try {
			guardado = fallaPresupuestoDAO.editarServiciosHerramientas(fallaPresupuesto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<FallaPresupuesto> listarFallaPresupuesto(){
		List<FallaPresupuesto> fallaPresupuestos = new ArrayList<FallaPresupuesto>();
		try {
			fallaPresupuestos = fallaPresupuestoDAO.listarFallaPresupuesto();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fallaPresupuestos;
	}

}
