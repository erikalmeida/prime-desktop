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

import ucla.si.modelo.Persona;
import ucla.si.modelo.Presupuesto;
import ucla.si.modelo.Servicio;
import ucla.si.modelo.Usuario;
import ucla.si.modelo.Vehiculo;


import ucla.si.modelo.Servicio;

@Repository
public class PresupuestoDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Presupuesto incluir(Presupuesto presupuesto) {
		em.persist(presupuesto);
		em.flush();
		return presupuesto;
	}
	
	@Transactional
	public Presupuesto editar(Presupuesto presupuesto) {
		presupuesto.setFechaModificacion(new Date());
		em.merge(presupuesto);
		return presupuesto;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Presupuesto> listarPresupuestos() {
		List<Presupuesto> presupuesto= new ArrayList<Presupuesto>();
		try {
			Query query = em.createQuery("SELECT g FROM Presupuesto g where g.estado = 'En espera' order by id");
			presupuesto = (List<Presupuesto>) query.getResultList();
			if(!presupuesto.isEmpty()){
				for (Presupuesto presupuestos : presupuesto) {
					//Esto no lo coloco liscano
					Hibernate.initialize(presupuestos.getCita());
					
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return presupuesto;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Presupuesto> listarPresupuestosOrdenDeServicio() {
		//Presupuestos validados
		List<Presupuesto> presupuesto= new ArrayList<Presupuesto>();
		try {
			Query query = em.createQuery("SELECT g FROM Presupuesto g where g.estado = 'Validada' order by id");
			presupuesto = (List<Presupuesto>) query.getResultList();
			if(!presupuesto.isEmpty()){
				for (Presupuesto presupuestos : presupuesto) {
					//Esto no lo coloco liscano
					Hibernate.initialize(presupuestos.getCita());
					
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return presupuesto;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Presupuesto buscarPresupuesto(long id_cita) {
		Presupuesto presupuesto= new Presupuesto();
		try {
			Query query = em.createQuery("SELECT g FROM Presupuesto g where idcita="+id_cita);
			
			presupuesto =(Presupuesto) query.getResultList().get(0);
	
			/*if(presupuesto!=null){
				
					Hibernate.initialize(presupuesto.getCita());
					
				
			}*/
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return presupuesto;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String nombreUsuario(long id_presupuesto) {
		String nombre = "";
		try {
			Query query = em.createQuery("select UPPER(CONCAT(p.nombre,' ', p.apellido)) as nombre "
					+ "from Presupuesto pre inner join pre.cita c "
					+ "inner join c.vehiculo v "
					+ "inner join v.usuario u "
					+ "inner join u.persona p "
					+ "where pre.id = ?1");
			query.setParameter(1, id_presupuesto);
			nombre = query.getResultList().size() > 0 ? (String)query.getResultList().get(0) : "";
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nombre;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String nombreModelo(long id_presupuesto) {
		String nombre = "";
		try {
			Query query = em.createQuery("select m.nombre "
					+ "from Presupuesto pre inner join pre.cita c "
					+ "inner join c.vehiculo v "
					+ "inner join v.modelo m "
					+ "where pre.id = ?1").setParameter(1, id_presupuesto);
			
			nombre = query.getResultList().size() > 0 ? (String)query.getResultList().get(0) : "";
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nombre;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String nombreMarca(long id_presupuesto) {
		String nombre = "";
		try {
			Query query = em.createQuery("select ma.nombre "
					+ "from Presupuesto pre inner join pre.cita c "
					+ "inner join c.vehiculo v "
					+ "inner join v.modelo mo "
					+ "inner join mo.marca ma "
					+ "where pre.id = ?1").setParameter(1, id_presupuesto);
			
			nombre = query.getResultList().size() > 0 ? (String)query.getResultList().get(0) : "";
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nombre;
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirPresupuesto(Presupuesto presupuesto){
		boolean guardado = false;
		try {
			if(incluir(presupuesto) != null){
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
	public boolean editarServicio(Presupuesto presupuesto){
		boolean guardado = false;
		try {
			if(editar(presupuesto) != null){
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
	public Presupuesto getPresupuesto(Long id) {
		Presupuesto presupuesto= new Presupuesto();
		try {
			Query query = em.createQuery("SELECT t FROM Presupuesto t where t.id=?1 ");
			query.setParameter(1, id);
			presupuesto =(Presupuesto) query.getSingleResult();
	
			if(presupuesto!=null){
				
					Hibernate.initialize(presupuesto.getCita());
					
				
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return presupuesto;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Usuario nestedUsuario(Long presupuestoId) {
		Usuario u = null;
		try {
			Query query = em.createQuery("select u "
					+ "from Presupuesto pre inner join pre.cita c "
					+ "inner join c.vehiculo v "
					+ "inner join v.usuario u "
					+ "where pre.id = ?1");
			query.setParameter(1, presupuestoId);
			u = query.getResultList().size() > 0 ? (Usuario)query.getResultList().get(0) : null;
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Persona nestedPersona(Long presupuestoId) {
		Persona p = null;
		try {
			Query query = em.createQuery("select p "
					+ "from Presupuesto pre inner join pre.cita c "
					+ "inner join c.vehiculo v "
					+ "inner join v.usuario u "
					+ "inner join u.persona p "
					+ "where pre.id = ?1");
			query.setParameter(1, presupuestoId);
			p = query.getResultList().size() > 0 ? (Persona)query.getResultList().get(0) : null;
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Vehiculo nestedVehiculo(Long presupuestoId) {
		Vehiculo v = null;
		try {
			Query query = em.createQuery("select v "
					+ "from Presupuesto pre inner join pre.cita c "
					+ "inner join c.vehiculo v "
					+ "where pre.id = ?1");
			query.setParameter(1, presupuestoId);
			v = query.getResultList().size() > 0 ? (Vehiculo)query.getResultList().get(0) : null;
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Servicio> listarServiciosXPresupuesto(long idServicio) {
		List<Servicio> servicios = new ArrayList<Servicio>();
		try {
		
			Query query = em.createQuery("select s "
					+ "from Presupuesto pre inner join pre.presupuestoServicio sp "
					+ "inner join sp.servicio s "
					+ "where pre.id = ?1");		
			query.setParameter(1, idServicio);
			servicios = (List<Servicio>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("servicios");
		System.out.println(servicios.size());
		return servicios;
	}
	
	


}
