package ucla.si.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ucla.si.modelo.Prueba;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PruebaDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Prueba incluir(Prueba prueba) {
		em.persist(prueba);
		em.flush();
		return prueba;
	}
	
	@Transactional
	public Prueba editar(Prueba prueba) {
		prueba.setFechaModificacion(new Date());
		em.merge(prueba);
		return prueba;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Prueba> listarPruebas() {
		List<Prueba> prueba= new ArrayList<Prueba>();
		try {
			Query query = em.createQuery("SELECT g FROM Prueba g order by id");
			prueba = (List<Prueba>) query.getResultList();
			if(!prueba.isEmpty()){
				for (Prueba pruebas : prueba) {
					Hibernate.initialize(pruebas.getOrdenServicio());
					
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prueba;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirPrueba(Prueba prueba){
		boolean guardado = false;
		try {
			if(incluir(prueba) != null){
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
	public boolean editarPrueba(Prueba prueba){
		boolean guardado = false;
		try {
			if(editar(prueba) != null){
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
