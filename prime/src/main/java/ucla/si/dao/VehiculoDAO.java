package ucla.si.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ucla.si.modelo.Servicio;
import ucla.si.modelo.Vehiculo;
import ucla.si.modelo.Vehiculo;

@Repository
public class VehiculoDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Vehiculo incluir(Vehiculo vehiculo) {
		em.persist(vehiculo);
		em.flush();
		return vehiculo;
	}
	
	@Transactional
	public Vehiculo editar(Vehiculo vehiculo){
		vehiculo.setFechaModificacion(new Date());
		em.merge(vehiculo);
		return vehiculo;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Vehiculo> listarVehiculos() {
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		try {
			Query query = em.createQuery("SELECT g FROM Vehiculo g order by id");
			vehiculos = (List<Vehiculo>) query.getResultList();
			if(!vehiculos.isEmpty()){
				for (Vehiculo vehiculo : vehiculos) {
					Hibernate.initialize(vehiculo.getUsuario());
					Hibernate.initialize(vehiculo.getUsuario().getPersona());
					Hibernate.initialize(vehiculo.getColor());
					Hibernate.initialize(vehiculo.getUso());
					Hibernate.initialize(vehiculo.getMarca());
					Hibernate.initialize(vehiculo.getModelo());
					Hibernate.initialize(vehiculo.getRefrigerante());
					Hibernate.initialize(vehiculo.getClase());
					Hibernate.initialize(vehiculo.getTipoAceite());
					Hibernate.initialize(vehiculo.getGrosorAceite());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehiculos;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirVehiculo(Vehiculo vehiculo){
		boolean guardado = false;
		try {
			if(incluir(vehiculo) != null){
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
	public boolean editarVehiculo(Vehiculo vehiculo){
		boolean guardado = false;
		try {
			if(editar(vehiculo) != null){
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
	public Vehiculo getVehiculo(Long id) {
		Vehiculo vehiculo= new Vehiculo();
		try {
			Query query = em.createQuery("SELECT t FROM Vehiculo t where t.id=?1 ");
			query.setParameter(1, id);
			vehiculo =(Vehiculo) query.getSingleResult();
			
			if(vehiculo != null){
				Hibernate.initialize(vehiculo.getCaja());
				Hibernate.initialize(vehiculo.getClase());
				Hibernate.initialize(vehiculo.getColor());
				Hibernate.initialize(vehiculo.getCombustible());
				Hibernate.initialize(vehiculo.getGrosorAceite());
				Hibernate.initialize(vehiculo.getMarca());
				Hibernate.initialize(vehiculo.getModelo());
				Hibernate.initialize(vehiculo.getRefrigerante());
				Hibernate.initialize(vehiculo.getTipoAceite());
				Hibernate.initialize(vehiculo.getUso());
				Hibernate.initialize(vehiculo.getUsuario());
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehiculo;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Vehiculo> listarVehiculosXServicio(long idServicio) {
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		try {
		//	Query query = em.createQuery("SELECT g FROM Vehiculo g where id=(select idVehiculo h FROM ServiciosVehiculos h where  idServicio="+idServicio+" order by id");
			Query query = em.createQuery("Select h from Vehiculo h Inner join h.vehiculoServicios s Inner join s.servicio s Where s.id = ?1");
		
			query.setParameter(1, idServicio).setMaxResults(query.getMaxResults());
			//usuario = query.getResultList().size() > 0 ? (Usuario) query.getResultList().get(0) : null;
			vehiculos = (List<Vehiculo>) query.getResultList();
			
			if(vehiculos != null){
				for (Vehiculo vehiculo2 : vehiculos) {
					
				
				Hibernate.initialize(vehiculo2.getCaja());
				Hibernate.initialize(vehiculo2.getClase());
				Hibernate.initialize(vehiculo2.getColor());
				Hibernate.initialize(vehiculo2.getCombustible());
				Hibernate.initialize(vehiculo2.getGrosorAceite());
				Hibernate.initialize(vehiculo2.getMarca());
				Hibernate.initialize(vehiculo2.getModelo());
				Hibernate.initialize(vehiculo2.getRefrigerante());
				Hibernate.initialize(vehiculo2.getTipoAceite());
				Hibernate.initialize(vehiculo2.getUso());
				Hibernate.initialize(vehiculo2.getUsuario());
			}
			
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehiculos;
	}

}
