package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.TipoCombustibleDAO;
import ucla.si.modelo.TipoCombustible;


@Service("servicioTipoCombustible")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)


public class ServicioTipoCombustible {
	
	@Autowired
	private TipoCombustibleDAO tipoCombustibleDAO;
	
	public boolean incluirTipoCombustible(TipoCombustible tipoCombustible){
		boolean guardado = false;
		try {
			guardado = tipoCombustibleDAO.incluirTipoCombustible(tipoCombustible);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarTipoCombustible(TipoCombustible tipoCombustible){
		boolean guardado = false;
		try {
			guardado = tipoCombustibleDAO.editarTipoCombustible(tipoCombustible);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<TipoCombustible> listarTipoCombustibles(){
		List<TipoCombustible> tipoCombustibles = new ArrayList<TipoCombustible>();
		try {
			tipoCombustibles = tipoCombustibleDAO.listartiposTipoCombustibles();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoCombustibles;
	}
	
	public List<TipoCombustible> buscarTipoCombustibles(String descripcion) {
		List<TipoCombustible> tipoCombustibles = new ArrayList<TipoCombustible>();
		try {
			tipoCombustibles = tipoCombustibleDAO.buscarTipoCombustibles(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoCombustibles;
	}


}
