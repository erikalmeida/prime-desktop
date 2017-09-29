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


import ucla.si.modelo.Profesion;

@Repository
public class ProfesionDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Profesion incluir(Profesion profesion){
		em.persist(profesion);
		em.flush();
		return profesion;
	}
	
	@Transactional
	public Profesion editar(Profesion profesion){
		profesion.setFechaModificacion(new Date());
		em.merge(profesion);
		return profesion;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Profesion> listarProfesiones(){
		List<Profesion> profesiones = new ArrayList<Profesion>();
		try {
			Query query = em.createQuery("SELECT g FROM Profesion g order by id");
			profesiones = (List<Profesion>) query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return profesiones;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Profesion> buscarProfesiones(String descripcion) {
		List<Profesion> profesiones = new ArrayList<Profesion>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT p FROM Profesion";
			hql += String.format(" p where  upper(p.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			profesiones = (List<Profesion>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profesiones;
	}

	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirProfesion(Profesion profesion){
		boolean guardado = false;
		try {
			if(incluir(profesion) != null){
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
	public boolean editarProfesion(Profesion profesion){
		boolean guardado = false;
		try {
			if(editar(profesion) != null){
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
