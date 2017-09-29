


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

@Repository

public class ServicioHerramientaDAO {


	
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public ServiciosHerramientas incluir(ServiciosHerramientas serviciosHerramientas) {
		em.persist(serviciosHerramientas);
		em.flush();
		return serviciosHerramientas;
	}
	
	
	
	
	@Transactional
	public ServiciosHerramientas editar(ServiciosHerramientas serviciosHerramientas) {
		serviciosHerramientas.setFechaModificacion(new Date());
		em.merge(serviciosHerramientas);
		return serviciosHerramientas;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ServiciosHerramientas> listarServiciosHerramientas() {
		List<ServiciosHerramientas> serviciosHerramientas= new ArrayList<ServiciosHerramientas>();
		try {
			Query query = em.createQuery("SELECT g FROM ServiciosHerramientas g order by id");
			serviciosHerramientas = (List<ServiciosHerramientas>) query.getResultList();
			if(!serviciosHerramientas.isEmpty()){
				for (ServiciosHerramientas serviciosHerramienta : serviciosHerramientas) {
					Hibernate.initialize(serviciosHerramienta.getHerramienta());
					Hibernate.initialize(serviciosHerramienta.getServicio());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviciosHerramientas;
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ServiciosHerramientas> listarServiciosHerramientasXServicio(long idServicio) {
		List<ServiciosHerramientas> serviciosHerramientas= new ArrayList<ServiciosHerramientas>();
		try {
			Query query = em.createQuery("Select s from Servicio s inner join s.presupuestoServicio ps ps.presupuesto p where p.id = ?1");
			serviciosHerramientas = (List<ServiciosHerramientas>) query.getResultList();
			if(!serviciosHerramientas.isEmpty()){
				for (ServiciosHerramientas serviciosHerramienta : serviciosHerramientas) {
					Hibernate.initialize(serviciosHerramienta.getHerramienta());
					Hibernate.initialize(serviciosHerramienta.getServicio());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviciosHerramientas;
	}
	
	
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirServiciosHerramientas(ServiciosHerramientas serviciosHerramientas){
		boolean guardado = false;
		try {
			if(incluir(serviciosHerramientas) != null){
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
	public boolean editarServiciosHerramientas(ServiciosHerramientas serviciosHerramientas){
		boolean guardado = false;
		try {
			if(editar(serviciosHerramientas) != null){
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
