package ucla.si.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import ucla.si.dao.OrdenServicioUsuarioDAO;
import ucla.si.modelo.OrdenServicioUsuario;


@Service("servicioOrdenServicioUsuario")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioOrdenServicioUsuario {
	
	@Autowired
	private OrdenServicioUsuarioDAO ordenServicioUsuarioDAO;

	
	public boolean incluir(OrdenServicioUsuario ordenServicioUsuario){
		boolean guardado = false;
		try {
			guardado = ordenServicioUsuarioDAO.incluirOrdenServicioUsuario(ordenServicioUsuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editar(OrdenServicioUsuario ordenServicioUsuario){
		boolean guardado = false;
		try {
			guardado = ordenServicioUsuarioDAO.editarServiciosHerramientas(ordenServicioUsuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<OrdenServicioUsuario> listarOrdenServicioUsuario(){
		List<OrdenServicioUsuario> ordenServicioUsuarios = new ArrayList<OrdenServicioUsuario>();
		try {
			ordenServicioUsuarios = ordenServicioUsuarioDAO.listarOrdenServicioUsuario();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordenServicioUsuarios;
	}

}
