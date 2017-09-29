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

import ucla.si.modelo.TipoCombustible;

@Repository
public class TipoCombustibleDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public TipoCombustible incluir(TipoCombustible tipoCombustible) {
		em.persist(tipoCombustible);
		em.flush();
		return tipoCombustible;
	}
	
	@Transactional
	public TipoCombustible editar(TipoCombustible tipoCombustible) {
		tipoCombustible.setFechaModificacion(new Date());
		em.merge(tipoCombustible);
		return tipoCombustible;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoCombustible> listartiposTipoCombustibles() {
		List<TipoCombustible> tipoCombustibles = new ArrayList<TipoCombustible>();
		try {
			Query query = em.createQuery("SELECT tco FROM TipoCombustible tco order by id");
			tipoCombustibles = (List<TipoCombustible>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoCombustibles;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoCombustible> buscarTipoCombustibles(String descripcion) {
		List<TipoCombustible> tipoCombustibles = new ArrayList<TipoCombustible>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT tco FROM TipoCombustible";
			hql += String.format(" tco where  upper(tco.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			tipoCombustibles = (List<TipoCombustible>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoCombustibles;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirTipoCombustible(TipoCombustible tipoCombustible){
		boolean guardado = false;
		try {
			if(incluir(tipoCombustible) != null){
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
	public boolean editarTipoCombustible(TipoCombustible tipoCombustible){
		boolean guardado = false;
		try {
			if(editar(tipoCombustible) != null){
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
