package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.TipoReclamoDAO;
import ucla.si.modelo.TipoReclamo;


@Service("servicioTipoReclamo")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioTipoReclamo {
	

	@Autowired
	private TipoReclamoDAO tipoReclamoDAO;
	
	public boolean incluirTipoReclamo(TipoReclamo tipoReclamo){
		boolean guardado = false;
		try {
			guardado = tipoReclamoDAO.incluirTipoReclamo(tipoReclamo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarTipoReclamo(TipoReclamo tipoReclamo){
		boolean guardado = false;
		try {
			guardado = tipoReclamoDAO.editarTipoReclamo(tipoReclamo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<TipoReclamo> listarTipoReclamos(){
		List<TipoReclamo> tipoReclamos = new ArrayList<TipoReclamo>();
		try {
			tipoReclamos = tipoReclamoDAO.listarTipoReclamos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoReclamos;
	}
	
	
	public TipoReclamo getTipoReclamo(Long id){
		TipoReclamo tipoReclamo = new TipoReclamo();
		try {
			tipoReclamo = tipoReclamoDAO.getTipoReclamo(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoReclamo;
	}
	
	public List<TipoReclamo> buscarTipoReclamos(String descripcion) {
		List<TipoReclamo> tipoReclamos = new ArrayList<TipoReclamo>();
		try {
			tipoReclamos = tipoReclamoDAO.buscarTipoReclamos(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoReclamos;
	}

}
