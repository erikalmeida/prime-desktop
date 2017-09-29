package ucla.si.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ucla.si.modelo.Notificacion;

@Repository
public class NotificacionDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Notificacion incluir(Notificacion notificacion) {
		em.persist(notificacion);
		em.flush();
		return notificacion;
	}
	
	@Transactional
	public Notificacion editar(Notificacion notificacion) {
		
		em.merge(notificacion);
		return notificacion;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Notificacion> listarNotificaciones() {
		List<Notificacion> notificacion = new ArrayList<Notificacion>();
		try {
			Query query = em.createQuery("SELECT g FROM Notificacion g order by id");
			notificacion = (List<Notificacion>) query.getResultList();
			if(!notificacion.isEmpty()){
				for (Notificacion notificaciones : notificacion) {

					Hibernate.initialize(notificaciones.getPromocion());
					Hibernate.initialize(notificaciones.getTiponotificacion());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notificacion;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirNotificacion(Notificacion notificacion){
		boolean guardado = false;
		try {
			if(incluir(notificacion) != null){
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
	public boolean editarCita(Notificacion notificacion){
		boolean guardado = false;
		try {
			if(editar(notificacion) != null){
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