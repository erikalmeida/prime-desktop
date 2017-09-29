package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.TipoEventualidadDAO;
import ucla.si.modelo.TipoEventualidad;

@Service("servicioTipoEventualidad")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioTipoEventualidad {
	
	@Autowired
	private TipoEventualidadDAO tipoEventualidadDAO;
	
	public boolean incluirTipoEventualidad(TipoEventualidad tipoEventualidad){
		boolean guardado = false;
		try {
			guardado = tipoEventualidadDAO.incluirTipoEventualidad(tipoEventualidad);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}

	public boolean editarTipoEventualidad(TipoEventualidad tipoEventualidad){
		boolean guardado = false;
		try {
			guardado = tipoEventualidadDAO.editarTipoEventualidad(tipoEventualidad);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<TipoEventualidad> listarTipoEventualidades(){
		List<TipoEventualidad> tipoEventualidades = new ArrayList<TipoEventualidad>();
		try {
			tipoEventualidades = tipoEventualidadDAO.listarTipoEventualidades();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoEventualidades;
	}
	
	public List<TipoEventualidad> buscarTipoEventualidades(String descripcion) {
		List<TipoEventualidad> tipoEventualidades = new ArrayList<TipoEventualidad>();
		try {
			tipoEventualidades = tipoEventualidadDAO.buscarTipoEventualidades(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoEventualidades;
	}
}
