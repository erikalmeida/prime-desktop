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

import ucla.si.modelo.FallaPresupuesto;

@Repository
public class FallaPresupuestoDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public FallaPresupuesto incluir(FallaPresupuesto fallaPresupuesto) {
		em.persist(fallaPresupuesto);
		em.flush();
		return fallaPresupuesto;
	}
	
	@Transactional
	public FallaPresupuesto editar(FallaPresupuesto fallaPresupuesto) {
		fallaPresupuesto.setFechaModificacion(new Date());
		em.merge(fallaPresupuesto);
		return fallaPresupuesto;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<FallaPresupuesto> listarFallaPresupuesto() {
		List<FallaPresupuesto> fallaPresupuesto= new ArrayList<FallaPresupuesto>();
		try {
			Query query = em.createQuery("SELECT g FROM FallaPresupuesto g order by id");
			fallaPresupuesto = (List<FallaPresupuesto>) query.getResultList();
			if(!fallaPresupuesto.isEmpty()){
				for (FallaPresupuesto fallaPresupuestos : fallaPresupuesto) {
					Hibernate.initialize(fallaPresupuestos.getFalla());
					Hibernate.initialize(fallaPresupuestos.getPresupuesto());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fallaPresupuesto;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirFallaPresupuesto(FallaPresupuesto fallaPresupuesto){
		boolean guardado = false;
		try {
			if(incluir(fallaPresupuesto) != null){
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
	public boolean editarServiciosHerramientas(FallaPresupuesto fallaPresupuesto){
		boolean guardado = false;
		try {
			if(editar(fallaPresupuesto) != null){
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
