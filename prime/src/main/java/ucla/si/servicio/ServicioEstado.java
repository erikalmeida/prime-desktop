package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.EstadoDAO;
import ucla.si.modelo.Estado;

@Service("servicioEstado")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioEstado {

	@Autowired
	private EstadoDAO estadoDAO;

	public boolean incluirEstado(Estado estado) {
		boolean guardado = false;
		try {
			guardado = estadoDAO.incluirEstado(estado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}

	public List<Estado> buscarEstados(String descripcion) {
		List<Estado> estados = new ArrayList<Estado>();
		try {
			estados = estadoDAO.buscarEstados(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estados;
	}

	public boolean editarEstado(Estado estado) {
		boolean guardado = false;
		try {
			guardado = estadoDAO.editarEstado(estado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}

	public List<Estado> listarEstados() {
		List<Estado> estados = new ArrayList<Estado>();
		try {
			estados = estadoDAO.listarEstados();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estados;
	}

}