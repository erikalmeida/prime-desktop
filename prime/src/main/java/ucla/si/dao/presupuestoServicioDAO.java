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

import ucla.si.modelo.PresupuestoServicio;


@Repository
public class presupuestoServicioDAO {
	
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public PresupuestoServicio incluir(PresupuestoServicio presupuestoServicio) {
		em.persist(presupuestoServicio);
		em.flush();
		return presupuestoServicio;
	}
	
	@Transactional
	public PresupuestoServicio editar(PresupuestoServicio presupuestoServicio) {
		presupuestoServicio.setFechaModificacion(new Date());
		em.merge(presupuestoServicio);
		return presupuestoServicio;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<PresupuestoServicio> listarPresupuestoServicio() {
		List<PresupuestoServicio> presupuestoServicio= new ArrayList<PresupuestoServicio>();
		try {
			Query query = em.createQuery("SELECT g FROM PresupuestoServicio g order by id");
			presupuestoServicio = (List<PresupuestoServicio>) query.getResultList();
			if(!presupuestoServicio.isEmpty()){
				for (PresupuestoServicio presupuestoServicios : presupuestoServicio) {
					Hibernate.initialize(presupuestoServicios.getPresupuesto());
					Hibernate.initialize(presupuestoServicios.getServicio());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return presupuestoServicio;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirPresupuestoServicio(PresupuestoServicio presupuestoServicio){
		boolean guardado = false;
		try {
			if(incluir(presupuestoServicio) != null){
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
	public boolean editarPresupuestoServicio(PresupuestoServicio presupuestoServicio){
		boolean guardado = false;
		try {
			if(editar(presupuestoServicio) != null){
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
