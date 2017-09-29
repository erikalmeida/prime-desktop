


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


import ucla.si.modelo.ServiciosEtapas;

@Repository

public class ServicioEtapaDAO {


	
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public ServiciosEtapas incluir(ServiciosEtapas serviciosEtapas) {
		em.persist(serviciosEtapas);
		em.flush();
		return serviciosEtapas;
	}
	
	
	
	
	@Transactional
	public ServiciosEtapas editar(ServiciosEtapas serviciosEtapas) {
		serviciosEtapas.setFechaModificacion(new Date());
		em.merge(serviciosEtapas);
		return serviciosEtapas;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ServiciosEtapas> listarServiciosEtapas() {
		List<ServiciosEtapas> serviciosEtapass= new ArrayList<ServiciosEtapas>();
		try {
			Query query = em.createQuery("SELECT g FROM ServiciosEtapas g order by id");
			serviciosEtapass = (List<ServiciosEtapas>) query.getResultList();
			if(!serviciosEtapass.isEmpty()){
				for (ServiciosEtapas serviciosEtapa : serviciosEtapass) {
					Hibernate.initialize(serviciosEtapa.getEtapa());
					Hibernate.initialize(serviciosEtapa.getServicio());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviciosEtapass;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirServiciosEtapas(ServiciosEtapas serviciosEtapas ){
		boolean guardado = false;
		try {
			if(incluir(serviciosEtapas) != null){
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
	public boolean editarServiciosEtapas(ServiciosEtapas serviciosEtapas){
		boolean guardado = false;
		try {
			if(editar(serviciosEtapas) != null){
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
