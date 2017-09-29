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

import ucla.si.modelo.Noticia;
import ucla.si.modelo.Slider;

@Repository
public class NoticiaDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public Noticia incluir(Noticia noticia) {
		em.persist(noticia);
		em.flush();
		return noticia;
	}

	@Transactional
	public Noticia editar(Noticia noticia) {
		noticia.setFechaModificacion(new Date());
		em.merge(noticia);
		return noticia;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Noticia> listarNoticias() {
		List<Noticia> noticias = new ArrayList<Noticia>();
		try {
			Query query = em.createQuery("SELECT g FROM Noticia g order by id");
			noticias = (List<Noticia>) query.getResultList();

			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noticias;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean incluirNoticia(Noticia noticia) {
		boolean guardado = false;
		try {
			if (incluir(noticia) != null) {
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
	public boolean editarNoticia(Noticia noticia) {
		boolean guardado = false;
		try {
			if (editar(noticia) != null) {
				guardado = true;
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			guardado = false;
		}
		return guardado;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Noticia> buscarNoticia(String titulo) {
		List<Noticia> noticias = new ArrayList<Noticia>();
		try {

			String hql = "SELECT n FROM Noticia";
			hql += String.format(" n where upper(n.titulo) like'%%%s%%'", titulo.toUpperCase());
			Query query = em.createQuery(hql);
			noticias = (List<Noticia>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noticias;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean eliminarNoticia(Noticia noticia) {
		boolean eliminado = false;
		try {
			String hql = "DELETE FROM Noticia where id="+noticia.getId();
			em.createQuery(hql).executeUpdate();
			eliminado = true;
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eliminado;

	}

}
