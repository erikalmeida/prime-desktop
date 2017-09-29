package ucla.si.servicio;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.ColorDAO;
import ucla.si.modelo.Color;


@Service("servicioColor")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)


public class ServicioColor {
	
	
	@Autowired
	private ColorDAO colorDAO;
	
	public boolean incluirColor(Color color){
		boolean guardado = false;
		try {
			guardado = colorDAO.incluirColor(color);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarColor(Color color){
		boolean guardado = false;
		try {
			guardado = colorDAO.editarColor(color);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Color> buscarColores(String descripcion) {
		List<Color> colores = new ArrayList<Color>();
		try {
			colores = colorDAO.buscarColores(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return colores;
	}
	
	public List<Color> listarColores(){
		List<Color> colores = new ArrayList<Color>();
		try {
			colores = colorDAO.listartiposColores();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return colores;
	}
	
	
	
	
	
	

}
