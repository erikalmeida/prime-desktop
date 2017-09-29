package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.MarcaTecnologiaDAO;
import ucla.si.modelo.MarcaTecnologia;

@Service("servicioMarcaTecnologia")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioMarcaTecnologia {
	
	@Autowired
	private MarcaTecnologiaDAO marcaTecnologiaDAO;
	
	public boolean incluirMarcaTecnologia(MarcaTecnologia marcaTecnologia){
		boolean guardado = false;
		try {
			guardado = marcaTecnologiaDAO.incluirMarcaTecnologia(marcaTecnologia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarMarcaTecnologia(MarcaTecnologia marcaTecnologia){
		boolean guardado = false;
		try {
			guardado = marcaTecnologiaDAO.editarMarcaTecnologia(marcaTecnologia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<MarcaTecnologia> buscarMarcaTecnologias(String descripcion) {
		List<MarcaTecnologia> marcaTecnologias = new ArrayList<MarcaTecnologia>();
		try {
			marcaTecnologias = marcaTecnologiaDAO.buscarMarcaTecnologias(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marcaTecnologias;
	}
	
	public List<MarcaTecnologia> listarMarcaTecnologias(){
		List<MarcaTecnologia> marcaTecnologias = new ArrayList<MarcaTecnologia>();
		try {
			marcaTecnologias = marcaTecnologiaDAO.listarMarcaTecnologias();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marcaTecnologias;
	}

}
