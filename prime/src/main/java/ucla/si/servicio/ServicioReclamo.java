package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.ReclamoDAO;
import ucla.si.modelo.Reclamo;

@Service("servicioReclamo")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioReclamo {
	
	@Autowired
	private ReclamoDAO reclamoDAO;
	
	public boolean incluirReclamo(Reclamo reclamo){
		boolean guardado = false;
		try {
			guardado = reclamoDAO.incluirReclamo(reclamo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarReclamo(Reclamo reclamo){
		boolean guardado = false;
		try {
			guardado = reclamoDAO.editarReclamo(reclamo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Reclamo> listarReclamos(){
		List<Reclamo> reclamos = new ArrayList<Reclamo>();
		try {
			reclamos = reclamoDAO.listarReclamos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reclamos;
	}

}  
