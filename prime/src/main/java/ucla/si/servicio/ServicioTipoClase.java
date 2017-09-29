package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.TipoClaseDAO;
import ucla.si.modelo.TipoClase;


@Service("servicioTipoClase")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioTipoClase {

	@Autowired
	private TipoClaseDAO tipoClaseDAO;
	
	public boolean incluirTipoClase(TipoClase tipoClase){
		boolean guardado = false;
		try {
			guardado = tipoClaseDAO.incluirTipoClase(tipoClase);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarTipoClase(TipoClase tipoClase){
		boolean guardado = false;
		try {
			guardado = tipoClaseDAO.editarTipoClase(tipoClase);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<TipoClase> listarTipoClases(){
		List<TipoClase> tipoClases = new ArrayList<TipoClase>();
		try {
			tipoClases = tipoClaseDAO.listartiposClases();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoClases;
	}
	
	public List<TipoClase> buscarTipoClases(String descripcion) {
		List<TipoClase> tipoClases = new ArrayList<TipoClase>();
		try {
			tipoClases = tipoClaseDAO.buscarTipoClases(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoClases;
	}
	
}
