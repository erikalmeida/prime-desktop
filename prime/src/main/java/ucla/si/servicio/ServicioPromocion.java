package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.PromocionDAO;
import ucla.si.modelo.Promocion;

@Service("servicioPromocion")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioPromocion {
	
	@Autowired
	private PromocionDAO promocionDAO;
	
	public boolean incluirPromocion(Promocion promocion){
		boolean guardado = false;
		try {
			guardado = promocionDAO.incluirPromocion(promocion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarPromocion(Promocion promocion){
		boolean guardado = false;
		try {
			guardado = promocionDAO.editarPromocion(promocion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Promocion> buscarPromociones(String descripcion) {
		List<Promocion> promociones = new ArrayList<Promocion>();
		try {
			promociones = promocionDAO.buscarPromociones(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return promociones;
	}
	
	public List<Promocion> listarPromocions(){
		List<Promocion> promocions = new ArrayList<Promocion>();
		try {
			promocions = promocionDAO.listarPromociones();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return promocions;
	}
	
	public Promocion getPromocion(Long id){
		Promocion promocion = new Promocion();
		try {
			promocion = promocionDAO.getPromocion(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return promocion;
	}

}
