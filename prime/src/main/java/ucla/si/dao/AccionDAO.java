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

import ucla.si.modelo.Accion;

@Repository
public class AccionDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Accion incluir(Accion accion) {
		em.persist(accion);
		em.flush();
		return accion;
	}
	
	@Transactional
	public Accion editar(Accion accion) {
		accion.setFechaModificacion(new Date());
		em.merge(accion);
		return accion;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Accion> listarAcciones() {
		List<Accion> acciones = new ArrayList<Accion>();
		try {
			Query query = em.createQuery("SELECT a FROM Accion a order by a.id");
			acciones = (List<Accion>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acciones;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Accion> listarAccionesSinDesplegar() {
		List<Accion> acciones = new ArrayList<Accion>();
		try {
			Query query = em.createQuery("SELECT a FROM Accion a WHERE a.nombre NOT IN('DESPLEGAR') order by a.id");
			acciones = (List<Accion>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acciones;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirAccion(Accion accion){
		boolean guardado = false;
		try {
			if(incluir(accion) != null){
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
	public boolean editarAccion(Accion accion){
		boolean guardado = false;
		try {
			if(editar(accion) != null){
				guardado = true;
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			guardado = false;
		}
		return guardado;
	}
	
	@Transactional(readOnly = true)
	public Accion buscarAccion(String nombre) {
		Accion accion = null;
		try {
			Query query = em.createQuery("SELECT a FROM Accion a "
					+ " where a.nombre = ?1 ");
			query.setParameter(1, nombre).setMaxResults(1);
			accion = query.getResultList().size() > 0 ? (Accion) query.getResultList().get(0) : null;
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accion;
	}

}
