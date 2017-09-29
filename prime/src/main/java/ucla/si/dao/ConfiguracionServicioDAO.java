package ucla.si.dao;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ucla.si.modelo.ConfiguracionServicio;

@Repository
public class ConfiguracionServicioDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public ConfiguracionServicio incluir(ConfiguracionServicio configuracionServicio) {
		em.persist(configuracionServicio);
		em.flush();
		return configuracionServicio;
	}
	
	@Transactional
	public ConfiguracionServicio editar(ConfiguracionServicio configuracionServicio) {
		em.merge(configuracionServicio);
		return configuracionServicio;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ConfiguracionServicio> listarConfiguracionServicio() {
		List<ConfiguracionServicio> configuracionServicio= new ArrayList<ConfiguracionServicio>();
		try {
			Query query = em.createQuery("SELECT g FROM ServiciosHerramientas g order by id");
			configuracionServicio = (List<ConfiguracionServicio>) query.getResultList();
			if(!configuracionServicio.isEmpty()){
				for (ConfiguracionServicio configuracionServicios : configuracionServicio) {
					Hibernate.initialize(configuracionServicios.getServicio());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return configuracionServicio;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirConfiguracionServicio(ConfiguracionServicio configuracionServicio){
		boolean guardado = false;
		try {
			if(incluir(configuracionServicio) != null){
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
	public boolean editarConfiguracionServicio(ConfiguracionServicio configuracionServicio){
		boolean guardado = false;
		try {
			if(editar(configuracionServicio) != null){
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
