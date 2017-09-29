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

import ucla.si.modelo.Falla;
import ucla.si.modelo.Falla;

@Repository
public class FallaDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Falla incluir(Falla falla) {
		em.persist(falla);
		em.flush();
		return falla;
	}
	
	@Transactional
	public Falla editar(Falla falla) {
		falla.setFechaModificacion(new Date());
		em.merge(falla);
		return falla;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Falla> listarFallas() {
		List<Falla> fallas = new ArrayList<Falla>();
		try {
			Query query = em.createQuery("SELECT g FROM Falla g order by id");
			fallas = (List<Falla>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fallas;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Falla> buscarFallas(String descripcion) {
		List<Falla> fallas = new ArrayList<Falla>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT g FROM Falla";
			hql += String.format(" g where  upper(g.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			fallas = (List<Falla>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fallas;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirFalla(Falla falla){
		boolean guardado = false;
		try {
			if(incluir(falla) != null){
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
	public boolean editarFalla(Falla falla){
		boolean guardado = false;
		try {
			if(editar(falla) != null){
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
	public List<Falla> listarFallasXPresupuesto(long idPresupuesto) {
		List<Falla> fallas = new ArrayList<Falla>();
		try {
			
		
	
			//Query query = em.createQuery("SELECT f FROM Falla f INNER JOIN f.fallaPresupuesto fp INNER JOIN fp.presupuesto p WHERE p.id =?1");
			Query query = em.createQuery("SELECT f from Falla f  Inner join f.fallaPresupuestos fp  where fp.presupuesto.id=?1");
			query.setParameter(1, idPresupuesto).setMaxResults(query.getMaxResults());
			//usuario = query.getResultList().size() > 0 ? (Usuario) query.getResultList().get(0) : null;
			fallas = (List<Falla>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fallas;
	}

}
