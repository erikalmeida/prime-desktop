package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.ModeloDAO;
import ucla.si.modelo.Modelo;

@Service("servicioModelo")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioModelo {
	
	@Autowired
	private ModeloDAO modeloDAO;
	
	public boolean incluirModelo(Modelo modelo){
		boolean guardado = false;
		try {
			guardado = modeloDAO.incluirModelo(modelo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarModelo(Modelo modelo){
		boolean guardado = false;
		try {
			guardado = modeloDAO.editarModelo(modelo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Modelo> listarModelos(){
		List<Modelo> modelos = new ArrayList<Modelo>();
		try {
			modelos = modeloDAO.listarModelos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelos;
	}

}
