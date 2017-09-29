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

import ucla.si.modelo.Anomalia;

@Repository
public class AnomaliaDAO {
	
	

	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Anomalia incluir(Anomalia anomalia) {
		em.persist(anomalia);
		em.flush();
		return anomalia;
	}
	
	@Transactional
	public Anomalia editar(Anomalia anomalia) {
		anomalia.setFechaModificacion(new Date());
		em.merge(anomalia);
		return anomalia;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Anomalia> listartiposAnomalias() {
		List<Anomalia> anomalias = new ArrayList<Anomalia>();
		try {
			Query query = em.createQuery("SELECT g FROM Anomalia g order by id");
			anomalias = (List<Anomalia>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anomalias;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Anomalia> buscarAnomalias(String descripcion) {
		List<Anomalia> anomalias = new ArrayList<Anomalia>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT g FROM Anomalia";
			hql += String.format(" g where  upper(g.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			anomalias = (List<Anomalia>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anomalias;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirAnomalia(Anomalia anomalia){
		boolean guardado = false;
		try {
			if(incluir(anomalia) != null){
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
	public boolean editarAnomalia(Anomalia anomalia){
		boolean guardado = false;
		try {
			if(editar(anomalia) != null){
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
