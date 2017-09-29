package ucla.si.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.FuncionDAO;
import ucla.si.modelo.Funcion;
import ucla.si.modelo.Grupo;


@Service("servicioFuncion")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioFuncion {

	@Autowired
	private FuncionDAO funcionDAO;

	public boolean incluirFuncion(Funcion funcion){
		boolean guardado = false;
		try {
			guardado = funcionDAO.incluirFuncion(funcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}

	public boolean editarFuncion(Funcion funcion){
		boolean guardado = false;
		try {
			guardado = funcionDAO.editarFuncion(funcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}

	public List<Funcion> listarFunciones(){
		List<Funcion> funciones = new ArrayList<Funcion>();
		try {
			funciones = funcionDAO.listarFunciones();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return funciones;
	}

	public List<Funcion> listarFuncionesHojas(){
		List<Funcion> funciones = new ArrayList<Funcion>();
		try {
			funciones = funcionDAO.listarFuncionesHojas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return funciones;
	}

	public Map<String,Long> contadorFunciones(){
		Map<String,Long> contadorFunciones = new HashMap<String, Long>();
		try {
			contadorFunciones = funcionDAO.contadorFunciones();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contadorFunciones;
	}

	public Map<String,Long> contadorFuncionesPorPermisos(Grupo grupo){
		Map<String,Long> contadorFuncionesPorPermisos = new HashMap<String, Long>();
		try {
			contadorFuncionesPorPermisos = funcionDAO.contadorFuncionesPorPermisos(grupo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contadorFuncionesPorPermisos;
	}

	public Funcion buscarFuncion(Long id){
		Funcion funcion = null;
		try {
			funcion = funcionDAO.buscarFuncion(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return funcion;
	}

	public boolean validarPadre(Long id){
		boolean funcion = false;
		try {
			funcion = funcionDAO.validarPadre(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return funcion;
	}

}
