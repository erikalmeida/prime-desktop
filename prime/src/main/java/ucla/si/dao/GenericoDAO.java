package ucla.si.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GenericoDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Object incluir(Object object) {
		em.persist(object);
		em.flush();
		return object;
	}
	
	@Transactional
	public Object editar(Object object){
		em.merge(object);
		return object;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirObject(Object object){
		boolean guardado = false;
		try {
			if(incluir(object) != null){
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
	public boolean editarObject(Object object){
		boolean guardado = false;
		try {
			if(editar(object) != null){
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
