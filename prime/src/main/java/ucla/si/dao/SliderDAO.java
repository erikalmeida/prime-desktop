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

import ucla.si.modelo.Slider;

@Repository
public class SliderDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public Slider incluir(Slider slider) {
		em.persist(slider);
		em.flush();
		return slider;
	}

	@Transactional
	public Slider editar(Slider slider) {
		slider.setFechaModificacion(new Date());
		em.merge(slider);
		return slider;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Slider> listarSliders() {
		List<Slider> sliders = new ArrayList<Slider>();
		try {
			Query query = em.createQuery("SELECT g FROM Slider g order by id");
			sliders = (List<Slider>) query.getResultList();

			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sliders;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Slider> buscarSlider(String nombre) {
		List<Slider> sliders = new ArrayList<Slider>();
		try {

			String hql = "SELECT s FROM Slider";
			hql += String.format(" s where  upper(s.nombre) like'%%%s%%'", nombre.toUpperCase());
			Query query = em.createQuery(hql);
			sliders = (List<Slider>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sliders;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean incluirSlider(Slider slider) {
		boolean guardado = false;
		try {
			if (incluir(slider) != null) {
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
	public boolean editarSlider(Slider slider) {
		boolean guardado = false;
		try {
			if (editar(slider) != null) {
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
	public boolean eliminarSlider(Slider slider) {
		boolean eliminado = false;
		try {
			String hql = "DELETE FROM Slider where id=" + slider.getId();
			em.createQuery(hql).executeUpdate();
			eliminado = true;
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eliminado;

	}

}
