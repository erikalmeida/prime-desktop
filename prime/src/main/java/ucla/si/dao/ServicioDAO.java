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

import ucla.si.modelo.Etapa;
import ucla.si.modelo.Herramienta;
import ucla.si.modelo.Presupuesto;
import ucla.si.modelo.Servicio;

@Repository
public class ServicioDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Servicio incluir(Servicio servicio) {
		em.persist(servicio);
		em.flush();
		return servicio;
	}
	
	@Transactional
	public Servicio editar(Servicio servicio) {
		servicio.setFechaModificacion(new Date());
		em.merge(servicio);
		return servicio;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Servicio> listarServicios() {
		List<Servicio> servicios = new ArrayList<Servicio>();
		try {
			Query query = em.createQuery("SELECT g FROM Servicio g order by id");
			servicios = (List<Servicio>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicios;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Servicio> listarServiciosConfigurados() {
		List<Servicio> servicios = new ArrayList<Servicio>();
		try {
			Query query = em.createQuery("SELECT g FROM Servicio g where estado='Configurado'order by id");
			servicios = (List<Servicio>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicios;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Servicio> buscarServicios(String descripcion) {
		List<Servicio> servicios = new ArrayList<Servicio>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT g FROM Servicio";
			hql += String.format(" g where  upper(g.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			servicios = (List<Servicio>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicios;
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Servicio> listarServiciosXHerramientas(long herramientaId) {
		List<Servicio> servicios = new ArrayList<Servicio>();
		try {
			Query query = em.createQuery("SELECT g FROM Servicio g where id=select h FROM ServiciosHerramientas="+herramientaId+" order by id");
			servicios = (List<Servicio>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicios;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Servicio> listarServiciosXPresupuesto(long idPresupuesto) {
		List<Servicio> servicios = new ArrayList<Servicio>();
		try {//bien
		//	Query query = em.createQuery("SELECT g FROM Herramienta g where id=(select idHerramienta h FROM ServiciosHerramientas h where  idServicio="+idServicio+" order by id");
			Query query = em.createQuery("Select s from Servicio s Inner join s.presupuestoServicio p Inner join p.presupuesto p Where p.id = ?1");
		
			query.setParameter(1, idPresupuesto).setMaxResults(query.getMaxResults());
			//usuario = query.getResultList().size() > 0 ? (Usuario) query.getResultList().get(0) : null;
			servicios = (List<Servicio>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicios;
	}
	
	
	
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirServicio(Servicio servicio){
		boolean guardado = false;
		try {
			if(incluir(servicio) != null){
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
	public boolean editarServicio(Servicio servicio){
		boolean guardado = false;
		try {
			if(editar(servicio) != null){
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
	public Servicio getServicio(Long id) {
		Servicio servicio= new Servicio();
		try {
			Query query = em.createQuery("SELECT t FROM Servicio t where t.id=?1 ");
			query.setParameter(1, id);
			
			servicio =(Servicio) query.getSingleResult();
	
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicio;
	}

}
