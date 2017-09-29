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

import ucla.si.modelo.Rectificacion;
import ucla.si.modelo.Rectificacion;

@Repository
public class RectificacionDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public Rectificacion incluir(Rectificacion rectificacion) {
		em.persist(rectificacion);
		em.flush();
		return rectificacion;
	}

	@Transactional
	public Rectificacion editar(Rectificacion rectificacion) {
		rectificacion.setFechaModificacion(new Date());
		em.merge(rectificacion);
		return rectificacion;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Rectificacion> listartiposRectificaciones() {
		List<Rectificacion> rectificaciones = new ArrayList<Rectificacion>();
		try {
			Query query = em.createQuery("SELECT re FROM Rectificacion re order by id");
			rectificaciones = (List<Rectificacion>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rectificaciones;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Rectificacion> buscarRectificaciones(String descripcion) {
		List<Rectificacion> rectificaciones = new ArrayList<Rectificacion>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT re FROM Rectificacion";
			hql += String.format(" re where  upper(re.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			rectificaciones = (List<Rectificacion>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rectificaciones;
	}
	

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean incluirRectificacion(Rectificacion rectificacion) {
		boolean guardado = false;
		try {
			if (incluir(rectificacion) != null) {
				guardado = true;
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			guardado = false;
		}
		return guardado;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean editarRectificacion(Rectificacion rectificacion) {
		boolean guardado = false;
		try {
			if (editar(rectificacion) != null) {
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
