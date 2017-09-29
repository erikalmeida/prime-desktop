package ucla.si.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ucla.si.modelo.Ocupacion;

@Repository
public class OcupacionDAO {
	
	private static SessionFactory factory;
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Ocupacion incluir(Ocupacion ocupacion){
		em.persist(ocupacion);
		em.flush();
		return ocupacion;
	}
	
	@Transactional
	public Ocupacion editar(Ocupacion ocupacion){
		ocupacion.setFechaModificacion(new Date());
		em.merge(ocupacion);
		return ocupacion;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Ocupacion> listarOcupaciones(){
		List<Ocupacion> ocupaciones = new ArrayList<Ocupacion>();
		try {
			Query query = em.createQuery("SELECT o FROM Ocupacion o order by id");
			ocupaciones = (List<Ocupacion>) query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ocupaciones;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Ocupacion> buscarOcupaciones(String descripcion) {
		List<Ocupacion> ocupaciones = new ArrayList<Ocupacion>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT o FROM Ocupacion";
			hql += String.format(" o where  upper(o.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			ocupaciones = (List<Ocupacion>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ocupaciones;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirOcupacion(Ocupacion ocupacion){
		boolean guardado = false;
		try {
			if(incluir(ocupacion) != null){
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
	public boolean editarOcupacion(Ocupacion ocupacion){
		boolean guardado = false;
		try {
			if(editar(ocupacion) != null){
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
