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



@Repository
public class DeporteDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Deporte incluir(Deporte deporte) {
		em.persist(deporte);
		em.flush();
		return deporte;
	}
	
	@Transactional
	public Deporte editar(Deporte deporte){
		deporte.setFechaModificacion(new Date());
		em.merge(deporte);
		return deporte;
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
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirDeporte(Deporte deporte){
		boolean guardado = false;
		try {
			if(incluir(deporte) != null){
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
	public boolean editarDeporte(Deporte deporte){
		boolean guardado = false;
		try {
			if(editar(deporte) != null){
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