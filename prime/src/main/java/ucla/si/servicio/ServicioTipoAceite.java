package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.TipoAceiteDAO;
import ucla.si.modelo.TipoAceite;


@Service("servicioTipoAceite")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioTipoAceite {

	
	@Autowired
	private TipoAceiteDAO tipoAceiteDAO;
	
	public boolean incluirTipoAceite(TipoAceite tipoAceite){
		boolean guardado = false;
		try {
			guardado = tipoAceiteDAO.incluirTipoAceite(tipoAceite);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarTipoAceite(TipoAceite tipoAceite){
		boolean guardado = false;
		try {
			guardado = tipoAceiteDAO.editarTipoAceite(tipoAceite);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<TipoAceite> buscarTipoAceites(String descripcion) {
		List<TipoAceite> tipoAceites = new ArrayList<TipoAceite>();
		try {
			tipoAceites = tipoAceiteDAO.buscarTipoAceites(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoAceites;
	}
	
	public List<TipoAceite> listarTipoAceites(){
		List<TipoAceite> tipoAceites = new ArrayList<TipoAceite>();
		try {
			tipoAceites = tipoAceiteDAO.listartiposAceites();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoAceites;
	}

}
