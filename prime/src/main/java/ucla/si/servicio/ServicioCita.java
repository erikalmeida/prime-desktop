package ucla.si.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import ucla.si.dao.CitaDAO;
import ucla.si.modelo.Cita;
import ucla.si.modelo.Presupuesto;

@Service("servicioCita")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioCita {
	
	@Autowired 
	private CitaDAO citaDAO;
	
	public boolean incluirCita(Cita cita){
		boolean guardado = false;
		try {
			guardado = citaDAO.incluirCita(cita);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarCita(Cita cita){
		boolean guardado = false;
		try {
			guardado = citaDAO.editarCita(cita);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Cita> listarCitas(){
		List<Cita> citas = new ArrayList<Cita>();
		try {
			citas = citaDAO.listarCitas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return citas;
	}
	
	
	public List<Cita> listarCitasPendientes(){
		List<Cita> citas = new ArrayList<Cita>();
		try {
			citas = citaDAO.listarCitasPendientes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return citas;
	}
	
	public List<Cita> listarCitasAprobadas(){
		List<Cita> citas = new ArrayList<Cita>();
		try {
			citas = citaDAO.listarCitasAprobadas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return citas;
	}
	
	public List<Cita> listarCitasRecibidas(){
		List<Cita> citas = new ArrayList<Cita>();
		try {
			citas = citaDAO.listarCitasRecibidas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return citas;
	}
	
	
	public List<Cita> listarCancelarCitas(){
		List<Cita> citas = new ArrayList<Cita>();
		try {
			citas = citaDAO.listarCancelarCitas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return citas;
	}
	
	
	public List<Cita> listarRecepcion(){
		List<Cita> citas = new ArrayList<Cita>();
		try {
			citas = citaDAO.listarRecepcion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return citas;
	}
	
	public List<Cita> listarAprobadas(){
		List<Cita> citas = new ArrayList<Cita>();
		try {
			citas = citaDAO.listarCitasAprobadas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return citas;
	}
	
	public List<Cita> listarAsignadas(){
		List<Cita> citas = new ArrayList<Cita>();
		try {
			citas = citaDAO.listarCitasAsignadas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return citas;
	}
	
	
	
	
	
	public Cita buscarCitaPorIdEspacio(long idEspacio){
		Cita cita = new Cita();
		try {
			cita = citaDAO.buscarCitaPorIdEspacio(idEspacio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cita;
	}
	
	
	public Cita getCita(Long id){
		Cita cita = new Cita();
		try {
			cita = citaDAO.getCita(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cita;
	}
}
