package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.TipoTecnologiaDAO;
import ucla.si.modelo.TipoTecnologia;

@Service("servicioTipoTecnologia")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioTipoTecnologia {
	
	@Autowired
	private TipoTecnologiaDAO tipoTecnologiaDAO;
	
	public boolean incluirTipoTecnologia(TipoTecnologia tipoTecnologia){
		boolean guardado = false;
		try {
			guardado = tipoTecnologiaDAO.incluirTipoTecnologia(tipoTecnologia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarTipoTecnologia(TipoTecnologia tipoTecnologia){
		boolean guardado = false;
		try {
			guardado = tipoTecnologiaDAO.editarTipoTecnologia(tipoTecnologia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<TipoTecnologia> listarTipoTecnologias(){
		List<TipoTecnologia> tipoTecnologias = new ArrayList<TipoTecnologia>();
		try {
			tipoTecnologias = tipoTecnologiaDAO.listarTipoTecnologias();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoTecnologias;
	}
	
	public List<TipoTecnologia> buscarTipoTecnologias(String descripcion) {
		List<TipoTecnologia> tipoTecnologias = new ArrayList<TipoTecnologia>();
		try {
			tipoTecnologias = tipoTecnologiaDAO.buscarTipoTecnologias(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoTecnologias;
	}

}