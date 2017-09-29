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

import ucla.si.modelo.Noticia;
import ucla.si.modelo.PreguntaFrecuente;

@Repository
public class PreguntaFrecuenteDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public PreguntaFrecuente incluir(PreguntaFrecuente preguntaFrecuente) {
		em.persist(preguntaFrecuente);
		em.flush();
		return preguntaFrecuente;
	}

	@Transactional
	public PreguntaFrecuente editar(PreguntaFrecuente preguntaFrecuente) {
		preguntaFrecuente.setFechaModificacion(new Date());
		em.merge(preguntaFrecuente);
		return preguntaFrecuente;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<PreguntaFrecuente> listarPreguntasFrecuentes() {
		List<PreguntaFrecuente> preguntasFrecuentes = new ArrayList<PreguntaFrecuente>();
		try {
			Query query = em.createQuery("SELECT g FROM PreguntaFrecuente g order by id");
			preguntasFrecuentes = (List<PreguntaFrecuente>) query.getResultList();

			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return preguntasFrecuentes;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<PreguntaFrecuente> buscarPreguntaFrecuente(String pregunta) {
		List<PreguntaFrecuente> preguntasFrecuentes = new ArrayList<PreguntaFrecuente>();
		try {

			String hql = "SELECT p FROM PreguntaFrecuente";
			hql += String.format(" p where upper(p.pregunta) like'%%%s%%'", pregunta.toUpperCase());
			Query query = em.createQuery(hql);
			preguntasFrecuentes = (List<PreguntaFrecuente>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return preguntasFrecuentes;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean incluirPreguntaFrecuente(PreguntaFrecuente preguntaFrecuente) {
		boolean guardado = false;
		try {
			if (incluir(preguntaFrecuente) != null) {
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
	public boolean editarPreguntaFrecuente(PreguntaFrecuente preguntaFrecuente) {
		boolean guardado = false;
		try {
			if (editar(preguntaFrecuente) != null) {
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
	public boolean eliminarPreguntaFrecuente(PreguntaFrecuente preguntaFrecuente) {
		boolean eliminado = false;
		try {
			String hql = "DELETE FROM PreguntaFrecuente where id="+preguntaFrecuente.getId();
			em.createQuery(hql).executeUpdate();
			eliminado = true;
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eliminado;

	}

}
