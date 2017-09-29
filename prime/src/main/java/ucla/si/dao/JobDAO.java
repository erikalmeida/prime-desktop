package ucla.si.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ucla.si.modelo.Job;

@Repository
public class JobDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public Job incluir(Job job) {
		em.persist(job);
		em.flush();
		return job;
	}
	
	@Transactional
	public Job editar(Job job) {
		job.setFechaModificacion(new Date());
		em.merge(job);
		return job;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Job> jobs() {
		List<Job> jobs = new ArrayList<Job>();
		try {
			Query query = em.createQuery("SELECT j FROM Job j order by j.id");
			jobs = (List<Job>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobs;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Job> jobsPendientes() {
		List<Job> jobsPendientes = new ArrayList<Job>();
		try {
			Query query = em.createQuery("SELECT j FROM Job j WHERE j.estado = 'Pendiente' order by j.id");
			jobsPendientes = (List<Job>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobsPendientes;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirJob(Job job){
		boolean guardado = false;
		try {
			if(incluir(job) != null){
				guardado = true;
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			guardado = false;
		}
		return guardado;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean editarJob(Job job){
		boolean guardado = false;
		try {
			if(editar(job) != null){
				guardado = true;
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			guardado = false;
		}
		return guardado;
	}
}
