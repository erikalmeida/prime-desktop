package ucla.si.servicio;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.CajaDAO;
import ucla.si.modelo.Caja;


@Service("servicioCaja")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioCaja {
	
	
	
	@Autowired
	private CajaDAO cajaDAO;
	
	public boolean incluirCaja(Caja caja){
		boolean guardado = false;
		try {
			guardado = cajaDAO.incluirCaja(caja);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarCaja(Caja caja){
		boolean guardado = false;
		try {
			guardado = cajaDAO.editarCaja(caja);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Caja> buscarCajas(String descripcion) {
		List<Caja> cajas = new ArrayList<Caja>();
		try {
			cajas = cajaDAO.buscarCajas(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cajas;
	}
	
	public List<Caja> listarCajas(){
		List<Caja> cajas = new ArrayList<Caja>();
		try {
			cajas = cajaDAO.listarCajas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cajas;
	}
	
	

}
