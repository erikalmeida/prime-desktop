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

import ucla.si.modelo.Calificacion;

@Repository
public class CalificacionDAO {
	
	

	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Calificacion incluir(Calificacion calificacion) {
		em.persist(calificacion);
		em.flush();
		return calificacion;
	}
	
	@Transactional
	public Calificacion editar(Calificacion calificacion){
		//calificacion.setFechaModificacion(new Date());
		em.merge(calificacion);
		return calificacion;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Calificacion> listarCalificaciones() {
		List<Calificacion> calificaciones = new ArrayList<Calificacion>();
		try {
			Query query = em.createQuery("SELECT cl FROM Calificacion cl order by id");
			calificaciones = (List<Calificacion>) query.getResultList();
			if(!calificaciones.isEmpty()){
				for (Calificacion calificacion : calificaciones) {
					Hibernate.initialize(calificacion.getOrdenEntrega());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return calificaciones;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Calificacion> buscarCalificaciones(String descripcion) {
		List<Calificacion> calificaciones = new ArrayList<Calificacion>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT mt FROM Calificacion";
			hql += String.format(" mt where  upper(mt.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			calificaciones = (List<Calificacion>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return calificaciones;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirCalificacion(Calificacion calificacion){
		boolean guardado = false;
		try {
			if(incluir(calificacion) != null){
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
	public boolean editarCalificacion(Calificacion calificacion){
		boolean guardado = false;
		try {
			if(editar(calificacion) != null){
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
