package ucla.si.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ucla.si.modelo.Estado;

@Repository
public class EstadoDAO {

	private static SessionFactory factory;
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public Estado incluir(Estado estado) {
		em.persist(estado);
		em.flush();
		return estado;
	}

	@Transactional
	public Estado editar(Estado estado) {
		estado.setFechaModificacion(new Date());
		em.merge(estado);
		return estado;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Estado> listarEstados() {
		List<Estado> estados = new ArrayList<Estado>();
		try {
			Query query = em.createQuery("SELECT e FROM Estado e order by id");
			estados = (List<Estado>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estados;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Estado> buscarEstados(String descripcion) {
		List<Estado> estados = new ArrayList<Estado>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT e FROM Estado";
			hql += String.format(" e where  upper(e.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			estados = (List<Estado>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estados;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean incluirEstado(Estado estado) {
		boolean guardado = false;
		try {
			if (incluir(estado) != null) {
				guardado = true;
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			guardado = false;
		}
		return guardado;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean editarEstado(Estado estado) {
		boolean guardado = false;
		try {
			if (editar(estado) != null) {
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
