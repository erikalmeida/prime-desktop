package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


import ucla.si.dao.MotorDAO;
import ucla.si.modelo.Motor;


@Service("servicioMotor")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)


public class ServicioMotor {
	
	
	
	@Autowired
	private MotorDAO motorDAO;
	
	public boolean incluirMotor(Motor motor){
		boolean guardado = false;
		try {
			guardado = motorDAO.incluirMotor(motor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Motor> buscarMotores(String descripcion) {
		List<Motor> motores = new ArrayList<Motor>();
		try {
			motores = motorDAO.buscarMotores(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return motores;
	}
	
	public boolean editarMotor(Motor motor){
		boolean guardado = false;
		try {
			guardado = motorDAO.editarMotor(motor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Motor> listarMotores(){
		List<Motor> motores = new ArrayList<Motor>();
		try {
			motores = motorDAO.listarMotores();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return motores;
	}
	
	
	
	
	
	

}
