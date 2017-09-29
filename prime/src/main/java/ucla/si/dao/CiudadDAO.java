package ucla.si.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.Transaction;


import ucla.si.modelo.Ciudad;

@Repository
public class CiudadDAO {
	
	private static SessionFactory factory;
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Ciudad incluir(Ciudad estado) {
		em.persist(estado);
		em.flush();
		return estado;
	}
	
	@Transactional
	public Ciudad editar(Ciudad estado){
		estado.setFechaModificacion(new Date());
		em.merge(estado);
		return estado;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Ciudad> listarCiudades() {
		List<Ciudad> estados = new ArrayList<Ciudad>();
		try {
			Query query = em.createQuery("SELECT c FROM Ciudad c order by id");
			estados = (List<Ciudad>) query.getResultList();
			if(!estados.isEmpty()){
				for (Ciudad estado : estados) {
					Hibernate.initialize(estado.getEstado());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estados;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Ciudad> buscarCiudades(String descripcion) {
		List<Ciudad> ciudades = new ArrayList<Ciudad>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT c FROM Ciudad";
			hql += String.format(" c where  upper(c.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			ciudades = (List<Ciudad>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ciudades;
	}

	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirCiudad(Ciudad estado){
		boolean guardado = false;
		try {
			if(incluir(estado) != null){
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
	public boolean editarCiudad(Ciudad estado){
		boolean guardado = false;
		try {
			if(editar(estado) != null){
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
