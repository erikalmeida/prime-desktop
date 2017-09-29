package ucla.si.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ucla.si.modelo.Herramienta;
import ucla.si.modelo.Usuario;
import ucla.si.modelo.Herramienta;

@Repository
public class HerramientaDAO {


	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Herramienta incluir(Herramienta herramienta) {
		em.persist(herramienta);
		em.flush();
		return herramienta;
	}
	
	@Transactional
	public Herramienta editar(Herramienta herramienta) {
		herramienta.setFechaModificacion(new Date());
		em.merge(herramienta);
		return herramienta;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Herramienta> listarHerramientas() {
		List<Herramienta> herramientas = new ArrayList<Herramienta>();
		try {
			Query query = em.createQuery("SELECT g FROM Herramienta g order by id");
			herramientas = (List<Herramienta>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return herramientas;
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Herramienta> listarHerramientasXServicio(long idServicio) {
		List<Herramienta> herramientas = new ArrayList<Herramienta>();
		try {
		//	Query query = em.createQuery("SELECT g FROM Herramienta g where id=(select idHerramienta h FROM ServiciosHerramientas h where  idServicio="+idServicio+" order by id");
			Query query = em.createQuery("Select h from Herramienta h Inner join h.serviciosHerramientas s Inner join s.servicio s Where s.id = ?1");
		
			query.setParameter(1, idServicio).setMaxResults(query.getMaxResults());
			//usuario = query.getResultList().size() > 0 ? (Usuario) query.getResultList().get(0) : null;
			herramientas = (List<Herramienta>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return herramientas;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Herramienta> buscarHerramientas(String descripcion) {
		List<Herramienta> herramientas = new ArrayList<Herramienta>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT g FROM Herramienta";
			hql += String.format(" g where  upper(g.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			herramientas = (List<Herramienta>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return herramientas;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Herramienta> listarHerramientasXEspacio(long idEspacio) {
		List<Herramienta> herramientas = new ArrayList<Herramienta>();
		try {
		//	Query query = em.createQuery("SELECT g FROM Herramienta g where id=(select idHerramienta h FROM ServiciosHerramientas h where  idServicio="+idServicio+" order by id");
			Query query = em.createQuery("Select h from Herramienta h Inner join h.EspacioHerramienta s Inner join s.servicio s Where s.id = ?1");
		
			query.setParameter(1, idEspacio).setMaxResults(query.getMaxResults());
			//usuario = query.getResultList().size() > 0 ? (Usuario) query.getResultList().get(0) : null;
			herramientas = (List<Herramienta>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return herramientas;
	}


	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirHerramienta(Herramienta herramienta){
		boolean guardado = false;
		try {
			if(incluir(herramienta) != null){
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
	public boolean editarHerramienta(Herramienta herramienta){
		boolean guardado = false;
		try {
			if(editar(herramienta) != null){
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
