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

import ucla.si.modelo.NotificacionUsuario;

@Repository
public class NotificacionUsuarioDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public NotificacionUsuario incluir(NotificacionUsuario notificacionUsuario) {
		em.persist(notificacionUsuario);
		em.flush();
		return notificacionUsuario;
	}
	
	@Transactional
	public NotificacionUsuario editar(NotificacionUsuario notificacionUsuario) {
		notificacionUsuario.setFechaModificacion(new Date());
		em.merge(notificacionUsuario);
		return notificacionUsuario;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<NotificacionUsuario> listarNotificacionUsuarios() {
		List<NotificacionUsuario> notificacionUsuario = new ArrayList<NotificacionUsuario>();
		try {
			Query query = em.createQuery("SELECT g FROM ServicioPresupuesto g order by id");
			notificacionUsuario = (List<NotificacionUsuario>) query.getResultList();
			if(!notificacionUsuario.isEmpty()){
				for (NotificacionUsuario notificacionUsuarios : notificacionUsuario) {
					Hibernate.initialize(notificacionUsuarios.getNotificacion());
					Hibernate.initialize(notificacionUsuarios.getUsuario());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notificacionUsuario;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirNotificacionUsuario (NotificacionUsuario notificacionUsuario){
		boolean guardado = false;
		try {
			if(incluir(notificacionUsuario) != null){
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
	public boolean editarNotificacionUsuario(NotificacionUsuario notificacionUsuario){
		boolean guardado = false;
		try {
			if(editar(notificacionUsuario) != null){
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
