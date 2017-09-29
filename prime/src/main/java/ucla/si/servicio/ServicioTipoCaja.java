package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.TipoCajaDAO;
import ucla.si.modelo.TipoCaja;


@Service("servicioTipoCaja")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioTipoCaja {
	
	@Autowired
	private TipoCajaDAO tipoCajaDAO;
	
	public boolean incluirTipoCaja(TipoCaja tipoCaja){
		boolean guardado = false;
		try {
			guardado = tipoCajaDAO.incluirTipoCaja(tipoCaja);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarTipoCaja(TipoCaja tipoCaja){
		boolean guardado = false;
		try {
			guardado = tipoCajaDAO.editarTipoCaja(tipoCaja);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<TipoCaja> listarTipoCajas(){
		List<TipoCaja> tipoCajas = new ArrayList<TipoCaja>();
		try {
			tipoCajas = tipoCajaDAO.listartiposCajas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoCajas;
	}
	
	public List<TipoCaja> buscarTipoCajas(String descripcion) {
		List<TipoCaja> tipoCajas = new ArrayList<TipoCaja>();
		try {
			tipoCajas = tipoCajaDAO.buscarTipoCajas(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoCajas;
	}
	
	
}
