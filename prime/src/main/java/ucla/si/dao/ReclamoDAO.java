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

import ucla.si.modelo.Reclamo;

@Repository
public class ReclamoDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public Reclamo incluir(Reclamo reclamo) {
		em.persist(reclamo);
		em.flush();
		return reclamo;
	}

	@Transactional
	public Reclamo editar(Reclamo reclamo) {
		reclamo.setFechaModificacion(new Date());
		em.merge(reclamo);
		return reclamo;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Reclamo> listarReclamos() {
		List<Reclamo> reclamos = new ArrayList<Reclamo>();
		try {
			Query query = em.createQuery("SELECT g FROM Reclamo g order by id");
			reclamos = (List<Reclamo>) query.getResultList();
			if(!reclamos.isEmpty()){
				for (Reclamo reclamo : reclamos) {
					Hibernate.initialize(reclamo.getMotivo());
					Hibernate.initialize(reclamo.getOrdenEntrega());
					Hibernate.initialize(reclamo.getOrdenEntrega().getGarantia());
					Hibernate.initialize(reclamo.getOrdenEntrega().getPrueba());
					Hibernate.initialize(reclamo.getOrdenEntrega().getPrueba().getOrdenServicio());
					Hibernate.initialize(reclamo.getOrdenEntrega().getPrueba().getOrdenServicio().getPresupuesto());
					Hibernate.initialize(reclamo.getOrdenEntrega().getPrueba().getOrdenServicio().getPresupuesto().getCita());
					Hibernate.initialize(reclamo.getOrdenEntrega().getPrueba().getOrdenServicio().getPresupuesto().getCita().getVehiculo());
					Hibernate.initialize(reclamo.getOrdenEntrega().getPrueba().getOrdenServicio().getPresupuesto().getCita().getVehiculo().getMarca());
					Hibernate.initialize(reclamo.getOrdenEntrega().getPrueba().getOrdenServicio().getPresupuesto().getCita().getVehiculo().getModelo());
					Hibernate.initialize(reclamo.getOrdenEntrega().getPrueba().getOrdenServicio().getPresupuesto().getCita().getVehiculo().getUsuario());
					Hibernate.initialize(reclamo.getOrdenEntrega().getPrueba().getOrdenServicio().getPresupuesto().getCita().getVehiculo().getUsuario().getPersona());
					Hibernate.initialize(reclamo.getTipoReclamo());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reclamos;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean incluirReclamo(Reclamo reclamo) {
		boolean guardado = false;
		try {
			if (incluir(reclamo) != null) {
				guardado = true;
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			guardado = false;
		}
		return guardado;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean editarReclamo(Reclamo reclamo) {
		boolean guardado = false;
		try {
			if (editar(reclamo) != null) {
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
