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

import ucla.si.modelo.Refrigerante;

@Repository
public class RefrigeranteDAO {

	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Refrigerante incluir(Refrigerante refrigerante) {
		em.persist(refrigerante);
		em.flush();
		return refrigerante;
	}
	
	@Transactional
	public Refrigerante editar(Refrigerante refrigerante){
		refrigerante.setFechaModificacion(new Date());
		em.merge(refrigerante);
		return refrigerante;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Refrigerante> listarRefrigerantes() {
		List<Refrigerante> refrigerantes = new ArrayList<Refrigerante>();
		try {
			Query query = em.createQuery("SELECT rf FROM Refrigerante rf order by id");
			refrigerantes = (List<Refrigerante>) query.getResultList();
			if(!refrigerantes.isEmpty()){
				for (Refrigerante refrigerante : refrigerantes) {
					Hibernate.initialize(refrigerante.getTipoRefrigerante());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return refrigerantes;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Refrigerante> buscarRefrigerantes(String descripcion) {
		List<Refrigerante> refrigerantes = new ArrayList<Refrigerante>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT rf FROM Refrigerante";
			hql += String.format(" rf where  upper(rf.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			refrigerantes = (List<Refrigerante>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return refrigerantes;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirRefrigerante(Refrigerante refrigerante){
		boolean guardado = false;
		try {
			if(incluir(refrigerante) != null){
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
	public boolean editarRefrigerante(Refrigerante refrigerante){
		boolean guardado = false;
		try {
			if(editar(refrigerante) != null){
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
