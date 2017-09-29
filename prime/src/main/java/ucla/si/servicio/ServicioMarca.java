package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.MarcaDAO;
import ucla.si.modelo.Marca;


@Service("servicioMarca")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioMarca {
	
	
	@Autowired
	private MarcaDAO marcaDAO;
	
	
	
	public boolean incluirMarca(Marca marca){
		boolean guardado = false;
		try {
			guardado = marcaDAO.incluirMarca(marca);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarMarca(Marca marca){
		boolean guardado = false;
		try {
			guardado = marcaDAO.editarMarca(marca);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Marca> listarMarcas(){
		List<Marca> marcas = new ArrayList<Marca>();
		try {
			marcas = marcaDAO.listartiposMarcas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marcas;
	}
	

}
