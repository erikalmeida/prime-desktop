package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.GrupoDAO;
import ucla.si.modelo.Grupo;

@Service("servicioGrupo")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioGrupo {

	@Autowired
	private GrupoDAO grupoDAO;

	public boolean incluirGrupo(Grupo grupo){
		boolean guardado = false;
		try {
			guardado = grupoDAO.incluirGrupo(grupo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}

	public boolean editarGrupo(Grupo grupo){
		boolean guardado = false;
		try {
			guardado = grupoDAO.editarGrupo(grupo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}

	public List<Grupo> listarGrupos(){
		List<Grupo> grupos = new ArrayList<Grupo>();
		try {
			grupos = grupoDAO.listarGrupos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grupos;
	}

	public List<Grupo> listarGruposSinAdministrador(){
		List<Grupo> grupos = new ArrayList<Grupo>();
		try {
			grupos = grupoDAO.listarGruposSinAdministrador();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grupos;
	}

	public Grupo buscarGrupo(String nombre){
		Grupo grupo = null;
		try {
			grupo = grupoDAO.buscarGrupo(nombre);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grupo;
	}

}
