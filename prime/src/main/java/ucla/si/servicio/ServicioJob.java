package ucla.si.servicio;

import ucla.si.dao.JobDAO;
import ucla.si.modelo.Job;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service("servicioJob")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicioJob{

	@Autowired
	JobDAO jobDAO;
	
	public boolean incluirJob(Job job){
		boolean guardado = false;
		try {
			guardado = jobDAO.incluirJob(job);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public boolean editarJob(Job job){
		boolean guardado = false;
		try {
			guardado = jobDAO.editarJob(job);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}
	
	public List<Job> jobs(){
		List<Job> jobs = new ArrayList<Job>();
		try {
			jobs = jobDAO.jobs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobs;
	}
	
	public List<Job> jobsPendientes(){
		List<Job> jobsPendientes = new ArrayList<Job>();
		try {
			jobsPendientes = jobDAO.jobsPendientes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobsPendientes;
	}

}
