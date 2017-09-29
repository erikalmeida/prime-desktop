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

import ucla.si.modelo.OrdenEntrega;
import ucla.si.modelo.OrdenEntrega;

@Repository
public class OrdenEntregaDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public OrdenEntrega incluir(OrdenEntrega ordenEntrega) {
		em.persist(ordenEntrega);
		em.flush();
		return ordenEntrega;
	}

	@Transactional
	public OrdenEntrega editar(OrdenEntrega ordenEntrega) {
		ordenEntrega.setFechaModificacion(new Date());
		em.merge(ordenEntrega);
		return ordenEntrega;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<OrdenEntrega> listarOrdenEntregas() {
		List<OrdenEntrega> ordenesEntrega = new ArrayList<OrdenEntrega>();
		try {
			Query query = em.createQuery("SELECT g FROM OrdenEntrega g order by id");
			ordenesEntrega = (List<OrdenEntrega>) query.getResultList();
			if (!ordenesEntrega.isEmpty()) {
				for (OrdenEntrega ordenesEntregas : ordenesEntrega) {
					Hibernate.initialize(ordenesEntregas.getPrueba());
					Hibernate.initialize(ordenesEntregas.getGarantia());

				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordenesEntrega;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<OrdenEntrega> listarOrdenEntregasPendientes() {
		List<OrdenEntrega> ordenEntrega = new ArrayList<OrdenEntrega>();
		try {
			Query query = em.createQuery("SELECT g FROM OrdenEntrega g where estado='Pendiente' order by id");
			ordenEntrega = (List<OrdenEntrega>) query.getResultList();
			if (!ordenEntrega.isEmpty()) {
				for (OrdenEntrega ordenEntregas : ordenEntrega) {

					Hibernate.initialize(ordenEntregas.getPrueba());
					Hibernate.initialize(ordenEntregas.getPrueba().getOrdenServicio());
					Hibernate.initialize(ordenEntregas.getPrueba().getOrdenServicio().getPresupuesto());
					Hibernate.initialize(ordenEntregas.getPrueba().getOrdenServicio().getPresupuesto().getCita());
					Hibernate.initialize(
							ordenEntregas.getPrueba().getOrdenServicio().getPresupuesto().getCita().getServicio());
					Hibernate.initialize(
							ordenEntregas.getPrueba().getOrdenServicio().getPresupuesto().getCita().getPromocion());
					Hibernate.initialize(
							ordenEntregas.getPrueba().getOrdenServicio().getPresupuesto().getCita().getVehiculo());
					Hibernate.initialize(ordenEntregas.getPrueba().getOrdenServicio().getPresupuesto().getCita()
							.getVehiculo().getUsuario());
					Hibernate.initialize(ordenEntregas.getPrueba().getOrdenServicio().getPresupuesto().getCita()
							.getVehiculo().getUsuario().getPersona());
					Hibernate.initialize(ordenEntregas.getPrueba().getOrdenServicio().getPresupuesto().getCita()
							.getVehiculo().getMarca());
					Hibernate.initialize(ordenEntregas.getPrueba().getOrdenServicio().getPresupuesto().getCita()
							.getVehiculo().getModelo());
					Hibernate.initialize(ordenEntregas.getGarantia());

				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordenEntrega;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean incluirOrdenEntrega(OrdenEntrega ordenEntrega) {
		boolean guardado = false;
		try {
			if (incluir(ordenEntrega) != null) {
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
	public boolean editarOrdenEntrega(OrdenEntrega ordenEntrega) {
		boolean guardado = false;
		try {
			if (editar(ordenEntrega) != null) {
				guardado = true;
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			guardado = false;
		}
		return guardado;
	}

	@Transactional(readOnly = true)
	public OrdenEntrega getOrdenEntrega(Long id) {
		OrdenEntrega ordenEntrega = null;
		try {
			Query query = em.createQuery("SELECT t FROM OrdenEntrega t where t.id = ?1 ");
			query.setParameter(1, id).setMaxResults(1);
			ordenEntrega = query.getResultList().size() > 0 ? (OrdenEntrega) query.getResultList().get(0) : null;
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordenEntrega;
	}

}
