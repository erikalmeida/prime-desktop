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

import ucla.si.modelo.PresupuestoTipoRepuesto;

@Repository
public class PresupuestoTipoRepuestoDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public PresupuestoTipoRepuesto incluir(PresupuestoTipoRepuesto presupuestoTipoRepuesto) {
		em.persist(presupuestoTipoRepuesto);
		em.flush();
		return presupuestoTipoRepuesto;
	}
	
	@Transactional
	public PresupuestoTipoRepuesto editar(PresupuestoTipoRepuesto presupuestoTipoRepuesto) {
		presupuestoTipoRepuesto.setFechaModificacion(new Date());
		em.merge(presupuestoTipoRepuesto);
		return presupuestoTipoRepuesto;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<PresupuestoTipoRepuesto> listarPresupuestoTipoRepuestos() {
		List<PresupuestoTipoRepuesto> presupuestoTipoRepuesto= new ArrayList<PresupuestoTipoRepuesto>();
		try {
			Query query = em.createQuery("SELECT g FROM PresupuestoTipoRepuesto g order by id");
			presupuestoTipoRepuesto = (List<PresupuestoTipoRepuesto>) query.getResultList();
			if(!presupuestoTipoRepuesto.isEmpty()){
				for (PresupuestoTipoRepuesto presupuestoTipoRepuestos : presupuestoTipoRepuesto) {
					Hibernate.initialize(presupuestoTipoRepuestos.getPresupuesto());
					Hibernate.initialize(presupuestoTipoRepuestos.getTipoRepuesto());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return presupuestoTipoRepuesto;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirPresupuestoTipoRepuesto(PresupuestoTipoRepuesto presupuestoTipoRepuesto){
		boolean guardado = false;
		try {
			if(incluir(presupuestoTipoRepuesto) != null){
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
	public boolean editarPresupuestoTipoRepuesto(PresupuestoTipoRepuesto presupuestoTipoRepuesto){
		boolean guardado = false;
		try {
			if(editar(presupuestoTipoRepuesto) != null){
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
