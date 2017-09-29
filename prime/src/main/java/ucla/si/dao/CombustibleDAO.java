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

import ucla.si.modelo.Combustible;

@Repository
public class CombustibleDAO {

	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Combustible incluir(Combustible Combustible) {
		em.persist(Combustible);
		em.flush();
		return Combustible;
	}
	
	@Transactional
	public Combustible editar(Combustible Combustible){
		Combustible.setFechaModificacion(new Date());
		em.merge(Combustible);
		return Combustible;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Combustible> listarCombustibles() {
		List<Combustible> Combustibles = new ArrayList<Combustible>();
		try {
			Query query = em.createQuery("SELECT co FROM Combustible co order by id");
			Combustibles = (List<Combustible>) query.getResultList();
			if(!Combustibles.isEmpty()){
				for (Combustible Combustible : Combustibles) {
					Hibernate.initialize(Combustible.getTipoCombustible());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Combustibles;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Combustible> buscarCombustibles(String descripcion) {
		List<Combustible> combustibles = new ArrayList<Combustible>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT co FROM Combustible";
			hql += String.format(" co where  upper(co.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			combustibles = (List<Combustible>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return combustibles;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirCombustible(Combustible Combustible){
		boolean guardado = false;
		try {
			if(incluir(Combustible) != null){
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
	public boolean editarCombustible(Combustible Combustible){
		boolean guardado = false;
		try {
			if(editar(Combustible) != null){
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
