package ucla.si.servicio;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.TecnologiaDAO;
import ucla.si.modelo.Herramienta;
import ucla.si.modelo.Tecnologia;





@Service("servicioTecnologia")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioTecnologia {
	
	@Autowired
	private TecnologiaDAO tecnologiaDAO;
	
	public boolean incluirTecnologia(Tecnologia tecnologia){
		boolean guardado = false;
		try {
			guardado = tecnologiaDAO.incluirTecnologia(tecnologia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarTecnologia(Tecnologia tecnologia){
		boolean guardado = false;
		try {
			guardado = tecnologiaDAO.editarTecnologia(tecnologia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Tecnologia> buscarTecnologias(String descripcion) {
		List<Tecnologia> tecnologias = new ArrayList<Tecnologia>();
		try {
			tecnologias = tecnologiaDAO.buscarTecnologias(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tecnologias;
	}
	
	public List<Tecnologia> listarTecnologias(){
		List<Tecnologia> tecnologias = new ArrayList<Tecnologia>();
		try {
			tecnologias = tecnologiaDAO.listarTecnologias();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tecnologias;
	}
	
	public List<Tecnologia> listarTecnologiasXServicio(long idServicio){
		List<Tecnologia> tecnologias = new ArrayList<Tecnologia>();
		try {
			tecnologias = tecnologiaDAO.listarTecnologiasXServicio(idServicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tecnologias;
	}

}
