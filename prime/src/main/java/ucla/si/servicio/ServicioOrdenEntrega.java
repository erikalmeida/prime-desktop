package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.OrdenEntregaDAO;
import ucla.si.modelo.OrdenEntrega;
import ucla.si.modelo.TipoReclamo;

@Service("servicioOrdenEntrega")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioOrdenEntrega {
	
	@Autowired
	private OrdenEntregaDAO ordenEntregaDAO;
	
	public boolean incluirOrdenEntrega(OrdenEntrega ordenEntrega){
		boolean guardado = false;
		try {
			guardado = ordenEntregaDAO.incluirOrdenEntrega(ordenEntrega);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarOrdenEntrega(OrdenEntrega ordenEntrega){
		boolean guardado = false;
		try {
			guardado = ordenEntregaDAO.editarOrdenEntrega(ordenEntrega);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<OrdenEntrega> listarOrdenesEntrega(){
		List<OrdenEntrega> ordenesEntrega = new ArrayList<OrdenEntrega>();
		try {
			ordenesEntrega = ordenEntregaDAO.listarOrdenEntregas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordenesEntrega;
	}
	
	
	public List<OrdenEntrega> listarOrdenesEntregaPendientes(){
		List<OrdenEntrega> ordenesEntrega = new ArrayList<OrdenEntrega>();
		try {
			ordenesEntrega = ordenEntregaDAO.listarOrdenEntregasPendientes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordenesEntrega;
	}
	
	
	public OrdenEntrega getOrdenEntrega(Long id){
		OrdenEntrega ordenEntrega = new OrdenEntrega();
		try {
			ordenEntrega = ordenEntregaDAO.getOrdenEntrega(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordenEntrega;
	}

}  
