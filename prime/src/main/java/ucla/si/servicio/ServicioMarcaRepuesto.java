package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.MarcaRepuestoDAO;
import ucla.si.modelo.MarcaRepuesto;

@Service("servicioMarcaRepuesto")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioMarcaRepuesto {
	
	@Autowired
	private MarcaRepuestoDAO marcaRepuestoDAO;
	
	public boolean incluirMarcaRepuesto(MarcaRepuesto marcaRepuesto){
		boolean guardado = false;
		try {
			guardado = marcaRepuestoDAO.incluirMarcaRepuesto(marcaRepuesto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarMarcaRepuesto(MarcaRepuesto marcaRepuesto){
		boolean guardado = false;
		try {
			guardado = marcaRepuestoDAO.editarMarcaRepuesto(marcaRepuesto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<MarcaRepuesto> buscarMarcaRepuestos(String descripcion) {
		List<MarcaRepuesto> marcaRepuestos = new ArrayList<MarcaRepuesto>();
		try {
			marcaRepuestos = marcaRepuestoDAO.buscarMarcaRepuestos(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marcaRepuestos;
	}
	
	public List<MarcaRepuesto> listarMarcaRepuestos(){
		List<MarcaRepuesto> marcaRepuestos = new ArrayList<MarcaRepuesto>();
		try {
			marcaRepuestos = marcaRepuestoDAO.listarMarcaRepuestos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marcaRepuestos;
	}

}
