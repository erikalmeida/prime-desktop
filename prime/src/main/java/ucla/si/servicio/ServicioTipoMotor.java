package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


import ucla.si.dao.TipoMotorDAO;
import ucla.si.modelo.TipoMotor;



@Service("servicioTipoMotor")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioTipoMotor {
	
	
	@Autowired
	private TipoMotorDAO tipoMotorDAO;
	
	public boolean incluirTipoMotor(TipoMotor tipoMotor){
		boolean guardado = false;
		try {
			guardado = tipoMotorDAO.incluirTipoMotor(tipoMotor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarTipoMotor(TipoMotor tipoMotor){
		boolean guardado = false;
		try {
			guardado = tipoMotorDAO.editarTipoMotor(tipoMotor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<TipoMotor> buscarTipoMotores(String descripcion) {
		List<TipoMotor> tipoMotores = new ArrayList<TipoMotor>();
		try {
			tipoMotores = tipoMotorDAO.buscarTipoMotores(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoMotores;
	}
	
	public List<TipoMotor> listarTipoMotores(){
		List<TipoMotor> tipoMotores = new ArrayList<TipoMotor>();
		try {
			tipoMotores = tipoMotorDAO.listartiposMotores();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoMotores;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
