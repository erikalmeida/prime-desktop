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

import ucla.si.modelo.Cita;
import ucla.si.modelo.Presupuesto;
import ucla.si.modelo.Vehiculo;




@Repository
public class CitaDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Cita incluir(Cita cita) {
		em.persist(cita);
		em.flush();
		return cita;
	}
	
	@Transactional
	public Cita editar(Cita cita) {
		cita.setFechaModificacion(new Date());
		em.merge(cita);
		return cita;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Cita> listarCitas() {
		List<Cita> cita= new ArrayList<Cita>();
		try {
			Query query = em.createQuery("SELECT g FROM Cita g order by id");
			cita = (List<Cita>) query.getResultList();
			if(!cita.isEmpty()){
				for (Cita citas : cita) {

					Hibernate.initialize(citas.getEventualidad());
					Hibernate.initialize(citas.getMotivo());
					Hibernate.initialize(citas.getVehiculo());
					Hibernate.initialize(citas.getVehiculo().getUsuario().getPersona());
					Hibernate.initialize(citas.getPromocion());
					Hibernate.initialize(citas.getServicio());
					Hibernate.initialize(citas.getEspacio());
					Hibernate.initialize(citas.getVehiculo().getMarca());
					Hibernate.initialize(citas.getVehiculo().getMarca().getModelo());
					Hibernate.initialize(citas.getPresupuestos());
					
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cita;
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirCita(Cita cita){
		boolean guardado = false;
		try {
			if(incluir(cita) != null){
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
	public boolean editarCita(Cita cita){
		boolean guardado = false;
		try {
			if(editar(cita) != null){
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
	public Cita buscarCitaPorIdEspacio(long idEspacio) {
		Cita cita = new Cita();
		try {
			Query query = em.createQuery("SELECT g FROM Cita g where idEspacio="+idEspacio);
			cita = (Cita) query.getResultList().get(query.getResultList().size()-1);// ojo aqui es con el getSingleResult() q funciona :)
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cita;
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Cita> listarCitasPendientes() {
		List<Cita> cita= new ArrayList<Cita>();
		try {
			Query query = em.createQuery("SELECT g FROM Cita g where estado='Pendiente' order by id");
			cita = (List<Cita>) query.getResultList();
			if(!cita.isEmpty()){
				for (Cita citas : cita) {

					Hibernate.initialize(citas.getEventualidad());
					Hibernate.initialize(citas.getMotivo());
					Hibernate.initialize(citas.getVehiculo());
					Hibernate.initialize(citas.getVehiculo().getModelo());
					Hibernate.initialize(citas.getVehiculo().getUsuario().getPersona());
					Hibernate.initialize(citas.getPromocion());
					Hibernate.initialize(citas.getServicio());
					Hibernate.initialize(citas.getEspacio());
					Hibernate.initialize(citas.getVehiculo().getMarca());
					Hibernate.initialize(citas.getVehiculo().getMarca().getModelo());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cita;
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Cita> listarCitasRecibidas() {
		List<Cita> cita= new ArrayList<Cita>();
		try {
			Query query = em.createQuery("SELECT g FROM Cita g where estado='Recibida' order by id");
			cita = (List<Cita>) query.getResultList();
			if(!cita.isEmpty()){
				for (Cita citas : cita) {

					Hibernate.initialize(citas.getEventualidad());
					Hibernate.initialize(citas.getMotivo());
					Hibernate.initialize(citas.getVehiculo());
					Hibernate.initialize(citas.getVehiculo().getModelo());
					Hibernate.initialize(citas.getVehiculo().getUsuario().getPersona());
					Hibernate.initialize(citas.getPromocion());
					Hibernate.initialize(citas.getServicio());
					Hibernate.initialize(citas.getEspacio());
					Hibernate.initialize(citas.getVehiculo().getMarca());
					Hibernate.initialize(citas.getVehiculo().getMarca().getModelo());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cita;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Cita> listarCitasAsignadas() {
		List<Cita> cita= new ArrayList<Cita>();
		try {
			Query query = em.createQuery("SELECT g FROM Cita g where estado='Asignada' order by id");
			cita = (List<Cita>) query.getResultList();
			if(!cita.isEmpty()){
				for (Cita citas : cita) {

					Hibernate.initialize(citas.getEventualidad());
					Hibernate.initialize(citas.getMotivo());
					Hibernate.initialize(citas.getVehiculo());
					Hibernate.initialize(citas.getVehiculo().getModelo());
					Hibernate.initialize(citas.getVehiculo().getUsuario().getPersona());
					Hibernate.initialize(citas.getPromocion());
					Hibernate.initialize(citas.getServicio());
					Hibernate.initialize(citas.getEspacio());
					Hibernate.initialize(citas.getVehiculo().getMarca());
					Hibernate.initialize(citas.getVehiculo().getMarca().getModelo());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cita;
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Cita> listarCitasAprobadas() {
		List<Cita> cita= new ArrayList<Cita>();
		try {
			Query query = em.createQuery("SELECT g FROM Cita g where estado='Aprobada' order by id");
			cita = (List<Cita>) query.getResultList();
			if(!cita.isEmpty()){
				for (Cita citas : cita) {

					Hibernate.initialize(citas.getEventualidad());
					Hibernate.initialize(citas.getMotivo());
					Hibernate.initialize(citas.getVehiculo());
					Hibernate.initialize(citas.getVehiculo().getModelo());
					Hibernate.initialize(citas.getVehiculo().getUsuario().getPersona());
					Hibernate.initialize(citas.getPromocion());
					Hibernate.initialize(citas.getServicio());
					Hibernate.initialize(citas.getEspacio());
					Hibernate.initialize(citas.getVehiculo().getMarca());
					Hibernate.initialize(citas.getVehiculo().getMarca().getModelo());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cita;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Cita> listarCancelarCitas() {
		List<Cita> cita= new ArrayList<Cita>();
		try {
			Query query = em.createQuery("SELECT g FROM Cita g where estado='Pendiente' or estado='Aprobada' or estado='Asignada' order by id");
			cita = (List<Cita>) query.getResultList();
			if(!cita.isEmpty()){
				for (Cita citas : cita) {

					Hibernate.initialize(citas.getEventualidad());
					Hibernate.initialize(citas.getMotivo());
					Hibernate.initialize(citas.getVehiculo());
					Hibernate.initialize(citas.getVehiculo().getModelo());
					Hibernate.initialize(citas.getVehiculo().getUsuario().getPersona());
					Hibernate.initialize(citas.getPromocion());
					Hibernate.initialize(citas.getServicio());
					Hibernate.initialize(citas.getEspacio());
					Hibernate.initialize(citas.getVehiculo().getMarca());
					Hibernate.initialize(citas.getVehiculo().getMarca().getModelo());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cita;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Cita> listarRecepcion() {
		List<Cita> cita= new ArrayList<Cita>();
		try {
			Query query = em.createQuery("SELECT g FROM Cita g where estado='Asignada' order by id");
			cita = (List<Cita>) query.getResultList();
			if(!cita.isEmpty()){
				for (Cita citas : cita) {

					Hibernate.initialize(citas.getEventualidad());
					Hibernate.initialize(citas.getMotivo());
					Hibernate.initialize(citas.getVehiculo());
					Hibernate.initialize(citas.getVehiculo().getModelo());
					Hibernate.initialize(citas.getVehiculo().getUsuario().getPersona());
					Hibernate.initialize(citas.getPromocion());
					Hibernate.initialize(citas.getServicio());
					Hibernate.initialize(citas.getEspacio());
					Hibernate.initialize(citas.getVehiculo().getMarca());
					Hibernate.initialize(citas.getVehiculo().getMarca().getModelo());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cita;
	}
	
	
	@Transactional(readOnly = true)
	public Cita getCita(Long id) {
		Cita cita = null;
		try {
			Query query = em.createQuery("SELECT t FROM Cita t where t.id = ?1 ");
			query.setParameter(1, id);
			cita =(Cita) query.getSingleResult();
			
			if(cita != null){
					Hibernate.initialize(cita.getEspacio());
					Hibernate.initialize(cita.getEspacio());
					Hibernate.initialize(cita.getEventualidad());
					Hibernate.initialize(cita.getMotivo());
					Hibernate.initialize(cita.getPromocion());
					Hibernate.initialize(cita.getServicio());
					
					Hibernate.initialize(cita.getVehiculo());
					Hibernate.initialize(cita.getVehiculo().getMarca());
					Hibernate.initialize(cita.getVehiculo().getModelo());
					
					Hibernate.initialize(cita.getVehiculo().getUsuario());
					Hibernate.initialize(cita.getVehiculo().getUsuario().getPersona());

			}
			em.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cita;
	}
	

}
