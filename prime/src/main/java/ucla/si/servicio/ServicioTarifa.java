package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.TarifaDAO;
import ucla.si.modelo.Tarifa;

@Service("servicioTarifa")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioTarifa {
	
	@Autowired
	private TarifaDAO tarifaDAO;
	
	public boolean incluirTarifa(Tarifa tarifa){
		boolean guardado = false;
		try {
			guardado = tarifaDAO.incluirTarifa(tarifa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarTarifa(Tarifa tarifa){
		boolean guardado = false;
		try {
			guardado = tarifaDAO.editarTarifa(tarifa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Tarifa> buscarTarifas(String descripcion) {
		List<Tarifa> tarifas = new ArrayList<Tarifa>();
		try {
			tarifas = tarifaDAO.buscarTarifas(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tarifas;
	}
	
	public List<Tarifa> listarTarifas(){
		List<Tarifa> tarifas = new ArrayList<Tarifa>();
		try {
			tarifas = tarifaDAO.listarTarifas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tarifas;
	}

}
