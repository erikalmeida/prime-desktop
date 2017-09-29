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

import ucla.si.modelo.Color;

@Repository
public class ColorDAO {
	
	
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Color incluir(Color color) {
		em.persist(color);
		em.flush();
		return color;
	}
	
	@Transactional
	public Color editar(Color color) {
		color.setfechaModificacion(new Date());
		em.merge(color);
		return color;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Color> listartiposColores() {
		List<Color> colores = new ArrayList<Color>();
		try {
			Query query = em.createQuery("SELECT g FROM Color g order by id");
			colores = (List<Color>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return colores;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Color> buscarColores(String descripcion) {
		List<Color> colores = new ArrayList<Color>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT co FROM Color";
			hql += String.format(" co where  upper(co.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			colores = (List<Color>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return colores;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirColor(Color color){
		boolean guardado = false;
		try {
			if(incluir(color) != null){
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
	public boolean editarColor(Color color){
		boolean guardado = false;
		try {
			if(editar(color) != null){
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
