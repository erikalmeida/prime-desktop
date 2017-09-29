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

import ucla.si.modelo.OrdenServicio;
import ucla.si.modelo.Servicio;



@Repository
public class OrdenServicioDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public OrdenServicio incluir(OrdenServicio ordenServicio) {
		em.persist(ordenServicio);
		em.flush();
		return ordenServicio;
	}
	
	@Transactional
	public OrdenServicio editar(OrdenServicio ordenServicio) {
		ordenServicio.setFechaModificacion(new Date());
		em.merge(ordenServicio);
		return ordenServicio;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<OrdenServicio> listarOrdenServicios() {
		List<OrdenServicio> ordenServicio= new ArrayList<OrdenServicio>();
		try {
			Query query = em.createQuery("SELECT g FROM OrdenServicio g order by id");
			ordenServicio = (List<OrdenServicio>) query.getResultList();
			if(!ordenServicio.isEmpty()){
				for (OrdenServicio ordenServicios : ordenServicio) {
					Hibernate.initialize(ordenServicios.getPresupuesto());
					Hibernate.initialize(ordenServicios.getPrueba());
					
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordenServicio;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<OrdenServicio> listarOrdenServiciosEjecutando() {
		List<OrdenServicio> ordenServicio= new ArrayList<OrdenServicio>();
		try {
			Query query = em.createQuery("SELECT g FROM OrdenServicio g where estado = 'En proceso'");
			ordenServicio = (List<OrdenServicio>) query.getResultList();
			if(!ordenServicio.isEmpty()){
				for (OrdenServicio ordenServicios : ordenServicio) {
					Hibernate.initialize(ordenServicios.getPresupuesto());
					Hibernate.initialize(ordenServicios.getPrueba());
					
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordenServicio;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<OrdenServicio> listarOrdenServiciosFinalizado() {
		List<OrdenServicio> ordenServicio= new ArrayList<OrdenServicio>();
		try {
			Query query = em.createQuery("SELECT g FROM OrdenServicio g where estado = 'Finalizado'");
			ordenServicio = (List<OrdenServicio>) query.getResultList();
			if(!ordenServicio.isEmpty()){
				for (OrdenServicio ordenServicios : ordenServicio) {
					Hibernate.initialize(ordenServicios.getPresupuesto());
					Hibernate.initialize(ordenServicios.getPrueba());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordenServicio;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirOrdenServicio(OrdenServicio ordenServicio){
		boolean guardado = false;
		try {
			if(incluir(ordenServicio) != null){
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
	public boolean editarOrdenServicio(OrdenServicio ordenServicio){
		boolean guardado = false;
		try {
			if(editar(ordenServicio) != null){
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
