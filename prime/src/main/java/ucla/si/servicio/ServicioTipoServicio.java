package ucla.si.servicio;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.TipoServicioDAO;
import ucla.si.dao.TipoServicioDAO;
import ucla.si.modelo.TipoServicio;
import ucla.si.modelo.TipoServicio;



@Service("servicioTipoServicio")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioTipoServicio {
	
	@Autowired
	private TipoServicioDAO tipoServicioDAO;
	
	public boolean incluirTipoServicio(TipoServicio tipoServicio){
		boolean guardado = false;
		try {
			guardado = tipoServicioDAO.incluirTipoServicio(tipoServicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarTipoServicio(TipoServicio tipoServicio){
		boolean guardado = false;
		try {
			guardado = tipoServicioDAO.editarTipoServicio(tipoServicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<TipoServicio> listarTipoServicios(){
		List<TipoServicio> tipoServicios = new ArrayList<TipoServicio>();
		try {
			tipoServicios = tipoServicioDAO.listarTipoServicios();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoServicios;
	}
	
	public List<TipoServicio> buscarTipoServicios(String descripcion) {
		List<TipoServicio> tipoServicios = new ArrayList<TipoServicio>();
		try {
			tipoServicios = tipoServicioDAO.buscarTipoServicios(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoServicios;
	}

}
