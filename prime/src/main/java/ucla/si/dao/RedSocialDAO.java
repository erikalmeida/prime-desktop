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
import ucla.si.modelo.RedSocial;

@Repository
public class RedSocialDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public RedSocial incluir(RedSocial redSocial) {
		em.persist(redSocial);
		em.flush();
		return redSocial;
	}

	@Transactional
	public RedSocial editar(RedSocial redSocial) {
		redSocial.setFechaModificacion(new Date());
		em.merge(redSocial);
		return redSocial;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RedSocial> listarRedesSociales() {
		List<RedSocial> redesSociales = new ArrayList<RedSocial>();
		try {
			Query query = em.createQuery("SELECT r FROM RedSocial r order by id");
			redesSociales = (List<RedSocial>) query.getResultList();

			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return redesSociales;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean incluirRedSocial(RedSocial redSocial) {
		boolean guardado = false;
		try {
			if (incluir(redSocial) != null) {
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
	public boolean editarRedSocial(RedSocial redSocial) {
		boolean guardado = false;
		try {
			if (editar(redSocial) != null) {
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
