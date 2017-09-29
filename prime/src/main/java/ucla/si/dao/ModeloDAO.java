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

import ucla.si.modelo.Modelo;
import ucla.si.modelo.Modelo;

@Repository
public class ModeloDAO {
	
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Modelo incluir(Modelo modelo) {
		em.persist(modelo);
		em.flush();
		return modelo;
	}
	
	@Transactional
	public Modelo editar(Modelo modelo){
		modelo.setFechaModificacion(new Date());
		em.merge(modelo);
		return modelo;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Modelo> listarModelos() {
		List<Modelo> modeloes = new ArrayList<Modelo>();
		try {
			Query query = em.createQuery("SELECT g FROM Modelo g order by id");
			modeloes = (List<Modelo>) query.getResultList();
			if(!modeloes.isEmpty()){
				for (Modelo modelo : modeloes) {
					Hibernate.initialize(modelo.getMarca());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modeloes;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirModelo(Modelo modelo){
		boolean guardado = false;
		try {
			if(incluir(modelo) != null){
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
	public boolean editarModelo(Modelo modelo){
		boolean guardado = false;
		try {
			if(editar(modelo) != null){
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
