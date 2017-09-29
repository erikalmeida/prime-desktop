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

import ucla.si.modelo.EspacioTecnologia;

@Repository
public class EspacioTecnologiaDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public EspacioTecnologia incluir(EspacioTecnologia espacioTecnologia) {
		em.persist(espacioTecnologia);
		em.flush();
		return espacioTecnologia;
	}
	
	@Transactional
	public EspacioTecnologia editar(EspacioTecnologia espacioTecnologia) {
		espacioTecnologia.setFechaModificacion(new Date());
		em.merge(espacioTecnologia);
		return espacioTecnologia;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<EspacioTecnologia> listarEspacioTecnologia() {
		List<EspacioTecnologia> espacioTecnologia= new ArrayList<EspacioTecnologia>();
		try {
			Query query = em.createQuery("SELECT g FROM EspacioTecnologia g order by id");
			espacioTecnologia = (List<EspacioTecnologia>) query.getResultList();
			if(!espacioTecnologia.isEmpty()){
				for (EspacioTecnologia espacioTecnologias : espacioTecnologia) {
					Hibernate.initialize(espacioTecnologias.getTecnologia());
					Hibernate.initialize(espacioTecnologias.getEspacio());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return espacioTecnologia;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirEspacioTecnologia(EspacioTecnologia espacioTecnologia){
		boolean guardado = false;
		try {
			if(incluir(espacioTecnologia) != null){
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
	public boolean editarServiciosTecnologias(EspacioTecnologia espacioTecnologia){
		boolean guardado = false;
		try {
			if(editar(espacioTecnologia) != null){
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
