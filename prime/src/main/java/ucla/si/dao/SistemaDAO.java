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

import ucla.si.modelo.Sistema;

@Repository
public class SistemaDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Sistema incluir(Sistema sistema) {
		em.persist(sistema);
		em.flush();
		return sistema;
	}
	
	@Transactional
	public Sistema editar(Sistema sistema){
		sistema.setFechaModificacion(new Date());
		em.merge(sistema);
		return sistema;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Sistema buscar() {
		List<Sistema> sistemas = new ArrayList<Sistema>();
		Sistema sistema = null;
		try {
			Query query = em.createQuery("SELECT s FROM Sistema s order by id");
			sistemas = (List<Sistema>) query.getResultList();
			if(!sistemas.isEmpty()){
				sistema = sistemas.get(0);
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sistema;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirSistema(Sistema sistema){
		boolean guardado = false;
		try {
			if(incluir(sistema) != null){
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
	public boolean editarSistema(Sistema sistema){
		boolean guardado = false;
		try {
			if(editar(sistema) != null){
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
