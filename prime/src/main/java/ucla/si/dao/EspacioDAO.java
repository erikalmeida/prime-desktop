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
import ucla.si.modelo.Espacio;

@Repository
public class EspacioDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Espacio incluir(Espacio espacio) {
		em.persist(espacio);
		em.flush();
		return espacio;
	}
	
	@Transactional
	public Espacio editar(Espacio espacio) {
		espacio.setFechaModificacion(new Date());
		em.merge(espacio);
		return espacio;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Espacio> listarEspacios() {
		List<Espacio> espacio= new ArrayList<Espacio>();
		try {
			Query query = em.createQuery("SELECT ep FROM Espacio ep order by id");
			espacio = (List<Espacio>) query.getResultList();
			if(!espacio.isEmpty()){
				for (Espacio espacios : espacio) {

					
					Hibernate.initialize(espacios.getAgenda());
					Hibernate.initialize(espacios.getCitas());
					
					
					
					
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return espacio;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Espacio> buscarEspacios(String descripcion) {
		List<Espacio> espacios = new ArrayList<Espacio>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT ep FROM Espacio";
			hql += String.format(" ep where  upper(ep.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			espacios = (List<Espacio>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return espacios;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Espacio> listarEspaciosConfigurados() {
		List<Espacio> espacio= new ArrayList<Espacio>();
		try {
			Query query = em.createQuery("SELECT g FROM Espacio g where descripcion='Configurado' order by id");
			espacio = (List<Espacio>) query.getResultList();
			if(!espacio.isEmpty()){
				for (Espacio espacios : espacio) {

					Hibernate.initialize(espacios.getAgenda());
					
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return espacio;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Espacio> listarEspaciosPorAsignar() {
		List<Espacio> espacio= new ArrayList<Espacio>();
		try {
			Query query = em.createQuery("SELECT g FROM Espacio g where descripcion='Por Asignar' order by id");
			espacio = (List<Espacio>) query.getResultList();
			if(!espacio.isEmpty()){
				for (Espacio espacios : espacio) {

					Hibernate.initialize(espacios.getAgenda());
					
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return espacio;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirEspacio(Espacio espacio){
		boolean guardado = false;
		try {
			if(incluir(espacio) != null){
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
	public boolean editarEspacio(Espacio espacio){
		boolean guardado = false;
		try {
			if(editar(espacio) != null){
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