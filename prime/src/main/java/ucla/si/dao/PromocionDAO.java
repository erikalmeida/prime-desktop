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

import ucla.si.modelo.Promocion;
import ucla.si.modelo.ServiciosHerramientas;

@Repository
public class PromocionDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Promocion incluir(Promocion promocion) {
		em.persist(promocion);
		em.flush();
		return promocion;
	}
	
	@Transactional
	public Promocion editar(Promocion promocion) {
		promocion.setFechaModificacion(new Date());
		em.merge(promocion);
		return promocion;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Promocion> listarPromociones() {
		List<Promocion> promocion = new ArrayList<Promocion>();
		try {
			Query query = em.createQuery("SELECT g FROM Promocion g order by id");
			promocion = (List<Promocion>) query.getResultList();
			if(!promocion.isEmpty()){
				for (Promocion promociones : promocion) {
					Hibernate.initialize(promociones.getServicio());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return promocion;
	}
	

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Promocion> buscarPromociones(String descripcion) {
		List<Promocion> promociones = new ArrayList<Promocion>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT e FROM Promocion";
			hql += String.format(" e where  upper(e.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			promociones = (List<Promocion>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return promociones;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirPromocion(Promocion promocion){
		boolean guardado = false;
		try {
			if(incluir(promocion) != null){
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
	public boolean editarPromocion(Promocion promocion){
		boolean guardado = false;
		try {
			if(editar(promocion) != null){
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
	public Promocion getPromocion(Long id) {
		Promocion promocion= new Promocion();
		try {
			Query query = em.createQuery("SELECT t FROM Promocion t where t.id=?1 ");
			query.setParameter(1, id);
			
			promocion =(Promocion) query.getSingleResult();
	
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return promocion;
	}

}
