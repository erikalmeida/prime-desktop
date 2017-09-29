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

import ucla.si.modelo.Asunto;
import ucla.si.modelo.PreguntaFrecuente;

@Repository
public class AsuntoDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public Asunto incluir(Asunto asunto) {
		em.persist(asunto);
		em.flush();
		return asunto;
	}

	@Transactional
	public Asunto editar(Asunto asunto) {
		asunto.setFechaModificacion(new Date());
		em.merge(asunto);
		return asunto;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Asunto> listarAsuntos() {
		List<Asunto> asuntos = new ArrayList<Asunto>();
		try {
			Query query = em.createQuery("SELECT g FROM Asunto g order by id");
			asuntos = (List<Asunto>) query.getResultList();

			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return asuntos;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean incluirAsunto(Asunto asunto) {
		boolean guardado = false;
		try {
			if (incluir(asunto) != null) {
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
	public boolean editarAsunto(Asunto asunto) {
		boolean guardado = false;
		try {
			if (editar(asunto) != null) {
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
