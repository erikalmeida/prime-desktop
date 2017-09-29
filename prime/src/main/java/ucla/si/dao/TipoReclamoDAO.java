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

import ucla.si.modelo.TipoReclamo;

@Repository
public class TipoReclamoDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public TipoReclamo incluir(TipoReclamo tipoReclamo) {
		em.persist(tipoReclamo);
		em.flush();
		return tipoReclamo;
	}

	@Transactional
	public TipoReclamo editar(TipoReclamo tipoReclamo) {
		tipoReclamo.setFechaModificacion(new Date());
		em.merge(tipoReclamo);
		return tipoReclamo;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoReclamo> listarTipoReclamos() {
		List<TipoReclamo> tipoReclamos = new ArrayList<TipoReclamo>();
		try {
			Query query = em.createQuery("SELECT g FROM TipoReclamo g order by id");
			tipoReclamos = (List<TipoReclamo>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoReclamos;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoReclamo> buscarTipoReclamos(String descripcion) {
		List<TipoReclamo> tipoReclamos = new ArrayList<TipoReclamo>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT e FROM TipoReclamo";
			hql += String.format(" e where  upper(e.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			tipoReclamos = (List<TipoReclamo>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoReclamos;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean incluirTipoReclamo(TipoReclamo tipoReclamo) {
		boolean guardado = false;
		try {
			if (incluir(tipoReclamo) != null) {
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
	public boolean editarTipoReclamo(TipoReclamo tipoReclamo) {
		boolean guardado = false;
		try {
			if (editar(tipoReclamo) != null) {
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
	public TipoReclamo getTipoReclamo(Long id) {
		TipoReclamo tipoReclamo = null;
		try {
			Query query = em.createQuery("SELECT t FROM TipoReclamo t where t.id = ?1 ");
			query.setParameter(1, id).setMaxResults(1);
			tipoReclamo = query.getResultList().size() > 0 ? (TipoReclamo) query.getResultList().get(0) : null;
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoReclamo;
	}

}
