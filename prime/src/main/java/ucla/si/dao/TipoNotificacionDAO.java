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

import ucla.si.modelo.TipoNotificacion;

@Repository
public class TipoNotificacionDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public TipoNotificacion incluir(TipoNotificacion tipoNotificacion) {
		em.persist(tipoNotificacion);
		em.flush();
		return tipoNotificacion;
	}
	
	@Transactional
	public TipoNotificacion editar(TipoNotificacion tipoNotificacion){
		tipoNotificacion.setFechaModificacion(new Date());
		em.merge(tipoNotificacion);
		return tipoNotificacion;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoNotificacion> listartipoNotificaciones() {
		List<TipoNotificacion> tipoNotificacion = new ArrayList<TipoNotificacion>();
		try {
			Query query = em.createQuery("SELECT g FROM Tipo g order by id");
			tipoNotificacion = (List<TipoNotificacion>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoNotificacion;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirTipoNotificacion(TipoNotificacion tipoNotificacion){
		boolean guardado = false;
		try {
			if(incluir(tipoNotificacion) != null){
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
	public boolean editarTipoNotificacion(TipoNotificacion tipoNotificacion){
		boolean guardado = false;
		try {
			if(editar(tipoNotificacion) != null){
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