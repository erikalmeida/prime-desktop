package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.AnomaliaDAO;
import ucla.si.modelo.Anomalia;


@Service("servicioAnomalia")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)


public class ServicioAnomalia {
	
	
	@Autowired
	private AnomaliaDAO anomaliaDAO;
	
	public boolean incluirAnomalia(Anomalia anomalia){
		boolean guardado = false;
		try {
			guardado = anomaliaDAO.incluirAnomalia(anomalia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarAnomalia(Anomalia anomalia){
		boolean guardado = false;
		try {
			guardado = anomaliaDAO.editarAnomalia(anomalia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Anomalia> buscarAnomalias(String descripcion) {
		List<Anomalia> anomalias = new ArrayList<Anomalia>();
		try {
			anomalias = anomaliaDAO.buscarAnomalias(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anomalias;
	}
	
	public List<Anomalia> listarAnomalias(){
		List<Anomalia> anomalias = new ArrayList<Anomalia>();
		try {
			anomalias = anomaliaDAO.listartiposAnomalias();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anomalias;
	}
	
	
	

}
