package ucla.si.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ucla.si.modelo.Aceite;

@Repository
public class AceiteDAO {
	
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Aceite incluir(Aceite aceite) {
		em.persist(aceite);
		em.flush();
		return aceite;
	}
	
	@Transactional
	public Aceite editar(Aceite aceite){
		aceite.setFechaModificacion(new Date());
		em.merge(aceite);
		return aceite;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Aceite> listarAceites() {
		List<Aceite> aceites = new ArrayList<Aceite>();
		try {
			Query query = em.createQuery("SELECT a FROM Aceite a order by id");
			aceites = (List<Aceite>) query.getResultList();
			if(!aceites.isEmpty()){
				for (Aceite aceite : aceites) {
					Hibernate.initialize(aceite.getTipoAceite());
					Hibernate.initialize(aceite.getGrosorAceite());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aceites;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Aceite> buscarAceites(String descripcion) {
		List<Aceite> aceites = new ArrayList<Aceite>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT a FROM Aceite";
			hql += String.format(" a where  upper(a.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			aceites = (List<Aceite>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aceites;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirAceite(Aceite aceite){
		boolean guardado = false;
		try {
			if(incluir(aceite) != null){
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
	public boolean editarAceite(Aceite aceite){
		boolean guardado = false;
		try {
			if(editar(aceite) != null){
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
