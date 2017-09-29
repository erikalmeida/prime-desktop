package ucla.si.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ucla.si.modelo.EspacioMecanico;

@Repository
public class EspacioMecanicoDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public EspacioMecanico incluir(EspacioMecanico espacioMecanico) {
		em.persist(espacioMecanico);
		em.flush();
		return espacioMecanico;
	}
	
	@Transactional
	public EspacioMecanico editar(EspacioMecanico espacioMecanico) {
		espacioMecanico.setFechaModificacion(new Date());
		em.merge(espacioMecanico);
		return espacioMecanico;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<EspacioMecanico> listarEspacioMecanico() {
		List<EspacioMecanico> espacioMecanico= new ArrayList<EspacioMecanico>();
		try {
			Query query = em.createQuery("SELECT g FROM EspacioMecanico g order by id");
			espacioMecanico = (List<EspacioMecanico>) query.getResultList();
			if(!espacioMecanico.isEmpty()){
				for (EspacioMecanico espacioMecanicos : espacioMecanico) {
					Hibernate.initialize(espacioMecanicos.getMecanico());
					Hibernate.initialize(espacioMecanicos.getEspacio());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return espacioMecanico;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirEspacioMecanico(EspacioMecanico espacioMecanico){
		boolean guardado = false;
		try {
			if(incluir(espacioMecanico) != null){
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
	public boolean editarServiciosMecanicos(EspacioMecanico espacioMecanico){
		boolean guardado = false;
		try {
			if(editar(espacioMecanico) != null){
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
