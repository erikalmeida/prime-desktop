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

import ucla.si.modelo.Habilidad;

@Repository

public class HabilidadDAO {
	
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Habilidad incluir(Habilidad habilidad) {
		em.persist(habilidad);
		em.flush();
		return habilidad;
	}
	
	@Transactional
	public Habilidad editar(Habilidad habilidad) {
		habilidad.setFechaModificacion(new Date());
		em.merge(habilidad);
		return habilidad;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Habilidad> listartiposHabilidades() {
		List<Habilidad> habilidades = new ArrayList<Habilidad>();
		try {
			Query query = em.createQuery("SELECT ha FROM Habilidad ha order by id");
			habilidades = (List<Habilidad>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return habilidades;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Habilidad> buscarHabilidades(String descripcion) {
		List<Habilidad> habilidades = new ArrayList<Habilidad>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT ha FROM Habilidad";
			hql += String.format(" ha where  upper(ha.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			habilidades = (List<Habilidad>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return habilidades;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirHabilidad(Habilidad habilidad){
		boolean guardado = false;
		try {
			if(incluir(habilidad) != null){
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
	public boolean editarHabilidad(Habilidad habilidad){
		boolean guardado = false;
		try {
			if(editar(habilidad) != null){
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
