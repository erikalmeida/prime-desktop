package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.EtapaDAO;
import ucla.si.modelo.Etapa;
import ucla.si.modelo.Etapa;

@Service("servicioEtapa")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioEtapa {
	
	@Autowired
	private EtapaDAO etapaDAO;
	
	public boolean incluirEtapa(Etapa etapa){
		boolean guardado = false;
		try {
			guardado = etapaDAO.incluirEtapa(etapa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarEtapa(Etapa etapa){
		boolean guardado = false;
		try {
			guardado = etapaDAO.editarEtapa(etapa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Etapa> buscarEtapas(String descripcion) {
		List<Etapa> etapas = new ArrayList<Etapa>();
		try {
			etapas = etapaDAO.buscarEtapas(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return etapas;
	}
	
	public List<Etapa> listarEtapas(){
		List<Etapa> etapas = new ArrayList<Etapa>();
		try {
			etapas = etapaDAO.listarEtapas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return etapas;
	}
	
	public List<Etapa> listarEtapasXServicio(long idServicio){
		List<Etapa> etapas = new ArrayList<Etapa>();
		try {
			etapas = etapaDAO.listarEtapasXServicio(idServicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return etapas;
	}

}
