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

import ucla.si.modelo.Deporte;
import ucla.si.modelo.Eventualidad;



@Repository
public class EventualidadDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Eventualidad incluir(Eventualidad eventualidad) {
		em.persist(eventualidad);
		em.flush();
		return eventualidad;
	}
	
	@Transactional
	public Eventualidad editar(Eventualidad eventualidad){
		eventualidad.setFechaModificacion(new Date());
		em.merge(eventualidad);
		return eventualidad;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Deporte> listarDeportes() {
		List<Deporte> deportes = new ArrayList<Deporte>();
		
		try {
			Query query = em.createQuery("SELECT d FROM Deporte d order by id");
			deportes = (List<Deporte>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deportes;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Deporte> buscarDeportes(String descripcion) {
		List<Deporte> deportes = new ArrayList<Deporte>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT d FROM Deporte";
			hql += String.format(" d where  upper(d.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			deportes = (List<Deporte>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deportes;
	}
	


}