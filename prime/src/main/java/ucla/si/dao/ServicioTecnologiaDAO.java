


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


import ucla.si.modelo.ServiciosTecnologias;

@Repository

public class ServicioTecnologiaDAO {


	
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public ServiciosTecnologias incluir(ServiciosTecnologias serviciosTecnologias) {
		em.persist(serviciosTecnologias);
		em.flush();
		return serviciosTecnologias;
	}
	
	
	
	
	@Transactional
	public ServiciosTecnologias editar(ServiciosTecnologias serviciosTecnologias) {
		serviciosTecnologias.setFechaModificacion(new Date());
		em.merge(serviciosTecnologias);
		return serviciosTecnologias;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ServiciosTecnologias> listarServiciosTecnologias() {
		List<ServiciosTecnologias> serviciosTecnologiass= new ArrayList<ServiciosTecnologias>();
		try {
			Query query = em.createQuery("SELECT g FROM ServiciosTecnologias g order by id");
			serviciosTecnologiass = (List<ServiciosTecnologias>) query.getResultList();
			if(!serviciosTecnologiass.isEmpty()){
				for (ServiciosTecnologias serviciosTecnologia : serviciosTecnologiass) {
					Hibernate.initialize(serviciosTecnologia.getTecnologia());
					Hibernate.initialize(serviciosTecnologia.getServicio());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviciosTecnologiass;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirServiciosTecnologias(ServiciosTecnologias serviciosTecnologias ){
		boolean guardado = false;
		try {
			if(incluir(serviciosTecnologias) != null){
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
	public boolean editarServiciosTecnologias(ServiciosTecnologias serviciosTecnologias){
		boolean guardado = false;
		try {
			if(editar(serviciosTecnologias) != null){
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
