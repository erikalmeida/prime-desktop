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

import ucla.si.modelo.Clase;

@Repository
public class ClaseDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Clase incluir(Clase clase) {
		em.persist(clase);
		em.flush();
		return clase;
	}
	
	@Transactional
	public Clase editar(Clase clase){
		clase.setFechaModificacion(new Date());
		em.merge(clase);
		return clase;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Clase> listarClases() {
		List<Clase> clases = new ArrayList<Clase>();
		try {
			Query query = em.createQuery("SELECT cl FROM Clase cl order by id");
			clases = (List<Clase>) query.getResultList();
			if(!clases.isEmpty()){
				for (Clase clase : clases) {
					Hibernate.initialize(clase.getTipoClase());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clases;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Clase> buscarClases(String descripcion) {
		List<Clase> clases = new ArrayList<Clase>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT cl FROM Clase";
			hql += String.format(" cl where  upper(cl.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			clases = (List<Clase>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clases;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirClase(Clase clase){
		boolean guardado = false;
		try {
			if(incluir(clase) != null){
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
	public boolean editarClase(Clase clase){
		boolean guardado = false;
		try {
			if(editar(clase) != null){
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
