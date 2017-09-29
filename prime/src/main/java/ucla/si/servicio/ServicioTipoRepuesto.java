package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.TipoRepuestoDAO;
import ucla.si.modelo.TipoRepuesto;


@Service("servicioTipoRepuesto")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)


public class ServicioTipoRepuesto {
	
	
	@Autowired
	private TipoRepuestoDAO tipoRepuestoDAO;
	
	public boolean incluirTipoRepuesto(TipoRepuesto tipoRepuesto){
		boolean guardado = false;
		try {
			guardado = tipoRepuestoDAO.incluirTipoRepuesto(tipoRepuesto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarTipoRepuesto(TipoRepuesto tipoRepuesto){
		boolean guardado = false;
		try {
			guardado = tipoRepuestoDAO.editarTipoRepuesto(tipoRepuesto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<TipoRepuesto> listarTipoRepuestos(){
		List<TipoRepuesto> tipoRepuestos = new ArrayList<TipoRepuesto>();
		try {
			tipoRepuestos = tipoRepuestoDAO.listarTipoRepuestos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoRepuestos;
	}
	
   	public List<TipoRepuesto> listarRepuestoXPresupuesto(long idPresupuesto){
		List<TipoRepuesto> tipoRepuestos = new ArrayList<TipoRepuesto>();
		try {
			tipoRepuestos = tipoRepuestoDAO.listarRepuestoXPresupuesto(idPresupuesto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoRepuestos;
	}
	
	public List<TipoRepuesto> buscarTipoRepuestos(String descripcion) {
		List<TipoRepuesto> tipoRepuestos = new ArrayList<TipoRepuesto>();
		try {
			tipoRepuestos = tipoRepuestoDAO.buscarTipoRepuestos(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoRepuestos;
	}

}
