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

import ucla.si.modelo.ServiciosHerramientas;
import ucla.si.modelo.VehiculoServicio;

@Repository
public class VehiculoServicioDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public VehiculoServicio incluir(VehiculoServicio vehiculoServicio) {
		em.persist(vehiculoServicio);
		em.flush();
		return vehiculoServicio;
	}
	
	@Transactional
	public VehiculoServicio editar(VehiculoServicio vehiculoServicio) {
		vehiculoServicio.setFechaModificacion(new Date());
		em.merge(vehiculoServicio);
		return vehiculoServicio;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<VehiculoServicio> listarVehiculoServicio() {
		List<VehiculoServicio> vehiculoServicio= new ArrayList<VehiculoServicio>();
		try {
			Query query = em.createQuery("SELECT g FROM VehiculoServicio g order by id");
			vehiculoServicio = (List<VehiculoServicio>) query.getResultList();
			if(!vehiculoServicio.isEmpty()){
				for (VehiculoServicio vehiculoServicios : vehiculoServicio) {
					Hibernate.initialize(vehiculoServicios.getServicio());
					Hibernate.initialize(vehiculoServicios.getVehiculo());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehiculoServicio;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirVehiculoServicio(VehiculoServicio vehiculoServicio){
		boolean guardado = false;
		try {
			if(incluir(vehiculoServicio) != null){
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
	public boolean editarVehiculoServicio(VehiculoServicio vehiculoServicio){
		boolean guardado = false;
		try {
			if(editar(vehiculoServicio) != null){
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
