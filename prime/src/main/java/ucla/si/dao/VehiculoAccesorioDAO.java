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

import ucla.si.modelo.VehiculoAccesorio;

@Repository
public class VehiculoAccesorioDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public VehiculoAccesorio incluir(VehiculoAccesorio vehiculoAccesorio) {
		em.persist(vehiculoAccesorio);
		em.flush();
		return vehiculoAccesorio;
	}
	
	@Transactional
	public VehiculoAccesorio editar(VehiculoAccesorio vehiculoAccesorio){
		vehiculoAccesorio.setFechaModificacion(new Date());
		em.merge(vehiculoAccesorio);
		return vehiculoAccesorio;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<VehiculoAccesorio> listarVehiculoAccesorios() {
		List<VehiculoAccesorio> vehiculoAccesorios = new ArrayList<VehiculoAccesorio>();
		try {
			Query query = em.createQuery("SELECT g FROM VehiculoAccesorio g order by id");
			vehiculoAccesorios = (List<VehiculoAccesorio>) query.getResultList();
			if(!vehiculoAccesorios.isEmpty()){
				for (VehiculoAccesorio vehiculoAccesorio : vehiculoAccesorios) {
					Hibernate.initialize(vehiculoAccesorio.getAccesorio());
					Hibernate.initialize(vehiculoAccesorio.getVehiculo());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehiculoAccesorios;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirVehiculoAccesorio(VehiculoAccesorio vehiculoAccesorio){
		boolean guardado = false;
		try {
			if(incluir(vehiculoAccesorio) != null){
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
	public boolean editarVehiculoAccesorio(VehiculoAccesorio vehiculoAccesorio){
		boolean guardado = false;
		try {
			if(editar(vehiculoAccesorio) != null){
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
