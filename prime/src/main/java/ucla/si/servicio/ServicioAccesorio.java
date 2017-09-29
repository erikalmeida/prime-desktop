package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.AccesorioDAO;
import ucla.si.modelo.Accesorio;


@Service("servicioAccesorio")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioAccesorio {
	
	@Autowired
	private AccesorioDAO accesorioDAO;
	
	public boolean incluirAccesorio(Accesorio accesorio){
		boolean guardado = false;
		try {
			guardado = accesorioDAO.incluirAccesorio(accesorio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarAccesorio(Accesorio accesorio){
		boolean guardado = false;
		try {
			guardado = accesorioDAO.editarAccesorio(accesorio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Accesorio> buscarAccesorios(String descripcion) {
		List<Accesorio> accesorios = new ArrayList<Accesorio>();
		try {
			accesorios = accesorioDAO.buscarAccesorios(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accesorios;
	}
	
	public List<Accesorio> listarAccesorios(){
		List<Accesorio> accesorios = new ArrayList<Accesorio>();
		try {
			accesorios = accesorioDAO.listarAccesorios();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accesorios;
	}
	
	
	

}
