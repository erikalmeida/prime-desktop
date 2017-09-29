package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.HorarioDAO;
import ucla.si.dao.HorarioDAO;
import ucla.si.modelo.Horario;
import ucla.si.modelo.Horario;


@Service("servicioHorario")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ServicioHorario {
	
	@Autowired
	private HorarioDAO horarioDAO;
	
	public boolean incluirHorario(Horario horario){
		boolean guardado = false;
		try {
			guardado = horarioDAO.incluirHorario(horario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarHorario(Horario horario){
		boolean guardado = false;
		try {
			guardado = horarioDAO.editarHorario(horario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Horario> buscarHorarios(String descripcion) {
		List<Horario> horarios = new ArrayList<Horario>();
		try {
			horarios = horarioDAO.buscarHorarios(descripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return horarios;
	}
	
	public List<Horario> listarHorarios(){
		List<Horario> horarios = new ArrayList<Horario>();
		try {
			horarios = horarioDAO.listarHorarios();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return horarios;
	}
	
	
	

}
