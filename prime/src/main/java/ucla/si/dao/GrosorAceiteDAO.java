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

import ucla.si.modelo.GrosorAceite;
import ucla.si.modelo.GrosorAceite;

@Repository
public class GrosorAceiteDAO {
	
	

	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public GrosorAceite incluir(GrosorAceite grosorAceite) {
		em.persist(grosorAceite);
		em.flush();
		return grosorAceite;
	}
	
	@Transactional
	public GrosorAceite editar(GrosorAceite grosorAceite) {
		grosorAceite.setFechaModificacion(new Date());
		em.merge(grosorAceite);
		return grosorAceite;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<GrosorAceite> listartiposGrosorAceites() {
		List<GrosorAceite> grosorAceites = new ArrayList<GrosorAceite>();
		try {
			Query query = em.createQuery("SELECT g FROM GrosorAceite g order by id");
			grosorAceites = (List<GrosorAceite>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grosorAceites;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<GrosorAceite> buscarGrosorAceites(String descripcion) {
		List<GrosorAceite> grosorAceites = new ArrayList<GrosorAceite>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT ga FROM GrosorAceite";
			hql += String.format(" ga where  upper(ga.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			grosorAceites = (List<GrosorAceite>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grosorAceites;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirGrosorAceite(GrosorAceite grosorAceite){
		boolean guardado = false;
		try {
			if(incluir(grosorAceite) != null){
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
	public boolean editarGrosorAceite(GrosorAceite grosorAceite){
		boolean guardado = false;
		try {
			if(editar(grosorAceite) != null){
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
