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

import ucla.si.modelo.Etapa;
import ucla.si.modelo.Etapa;

@Repository
public class EtapaDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Etapa incluir(Etapa etapa) {
		em.persist(etapa);
		em.flush();
		return etapa;
	}
	
	@Transactional
	public Etapa editar(Etapa etapa) {
		etapa.setFechaModificacion(new Date());
		em.merge(etapa);
		return etapa;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Etapa> listarEtapas() {
		List<Etapa> etapas = new ArrayList<Etapa>();
		try {
			Query query = em.createQuery("SELECT e FROM Etapa e order by id");
			etapas = (List<Etapa>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return etapas;
	}
	
	
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirEtapa(Etapa etapa){
		boolean guardado = false;
		try {
			if(incluir(etapa) != null){
				guardado = true;
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			guardado = false;
		}
		return guardado;
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Etapa> listarEtapasXServicio(long idServicio) {
		List<Etapa> etapas = new ArrayList<Etapa>();
		try {
		//	Query query = em.createQuery("SELECT g FROM Etapa g where id=(select idEtapa h FROM ServiciosEtapas h where  idServicio="+idServicio+" order by id");
			Query query = em.createQuery("Select h from Etapa h Inner join h.serviciosEtapas s Inner join s.servicio s Where s.id = ?1");
		
			query.setParameter(1, idServicio).setMaxResults(query.getMaxResults());
			//usuario = query.getResultList().size() > 0 ? (Usuario) query.getResultList().get(0) : null;
			etapas = (List<Etapa>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return etapas;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Etapa> buscarEtapas(String descripcion) {
		List<Etapa> etapas = new ArrayList<Etapa>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT e FROM Etapa";
			hql += String.format(" e where  upper(e.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			etapas = (List<Etapa>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return etapas;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean editarEtapa(Etapa etapa){
		boolean guardado = false;
		try {
			if(editar(etapa) != null){
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
