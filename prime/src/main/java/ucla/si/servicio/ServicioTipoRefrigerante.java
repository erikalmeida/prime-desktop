package ucla.si.servicio;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.TipoRefrigeranteDAO;
import ucla.si.modelo.TipoRefrigerante;


@Service("servicioTipoRefrigerante")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)


public class ServicioTipoRefrigerante {

	
	@Autowired
	private TipoRefrigeranteDAO tipoRefrigeranteDAO;
	
	public boolean incluirTipoRefrigerante(TipoRefrigerante tipoRefrigerante){
		boolean guardado = false;
		try {
			guardado = tipoRefrigeranteDAO.incluirTipoRefrigerante(tipoRefrigerante);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarTipoRefrigerante(TipoRefrigerante tipoRefrigerante){
		boolean guardado = false;
		try {
			guardado = tipoRefrigeranteDAO.editarTipoRefrigerante(tipoRefrigerante);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<TipoRefrigerante> listarTipoRefrigerantes(){
		List<TipoRefrigerante> tipoRefrigerantes = new ArrayList<TipoRefrigerante>();
		try {
			tipoRefrigerantes = tipoRefrigeranteDAO.listartiposTipoRefrigerantes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoRefrigerantes;
	}
	
	public List<TipoRefrigerante> buscarTipoRefrigerantes(String descripcion) {
		List<TipoRefrigerante> tipoRefrigerantes = new ArrayList<TipoRefrigerante>();
		try {
			tipoRefrigerantes = tipoRefrigeranteDAO.buscarTipoRefrigerantes(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoRefrigerantes;
	}



}
