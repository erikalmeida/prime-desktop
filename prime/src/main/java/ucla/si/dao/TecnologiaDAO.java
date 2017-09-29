package ucla.si.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ucla.si.modelo.Tecnologia;
import ucla.si.modelo.Tecnologia;


@Repository
public class TecnologiaDAO {

	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Tecnologia incluir(Tecnologia tecnologia) {
		em.persist(tecnologia);
		em.flush();
		return tecnologia;
	}
	
	@Transactional
	public Tecnologia editar(Tecnologia tecnologia) {
		tecnologia.setFechaModificacion(new Date());
		em.merge(tecnologia);
		return tecnologia;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Tecnologia> listarTecnologias() {
		List<Tecnologia> tecnologias = new ArrayList<Tecnologia>();
		try {
			Query query = em.createQuery("SELECT tec FROM Tecnologia tec order by id");
			tecnologias = (List<Tecnologia>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tecnologias;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Tecnologia> buscarTecnologias(String descripcion) {
		List<Tecnologia> tecnologias = new ArrayList<Tecnologia>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT tec FROM Tecnologia";
			hql += String.format(" tec where  upper(tec.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			tecnologias = (List<Tecnologia>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tecnologias;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Tecnologia> listarTecnologiasXServicio(long idServicio) {
		List<Tecnologia> tecnologias = new ArrayList<Tecnologia>();
		try {
		//	Query query = em.createQuery("SELECT g FROM Tecnologia g where id=(select idTecnologia h FROM ServiciosTecnologias h where  idServicio="+idServicio+" order by id");
			Query query = em.createQuery("Select h from Tecnologia h Inner join h.serviciosTecnologias s Inner join s.servicio s Where s.id = ?1");
		
			query.setParameter(1, idServicio).setMaxResults(query.getMaxResults());
			//usuario = query.getResultList().size() > 0 ? (Usuario) query.getResultList().get(0) : null;
			tecnologias = (List<Tecnologia>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tecnologias;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirTecnologia(Tecnologia tecnologia){
		boolean guardado = false;
		try {
			if(incluir(tecnologia) != null){
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
	public boolean editarTecnologia(Tecnologia tecnologia){
		boolean guardado = false;
		try {
			if(editar(tecnologia) != null){
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
