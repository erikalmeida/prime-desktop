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

import ucla.si.modelo.Servicio;
import ucla.si.modelo.TipoRepuesto;

@Repository
public class TipoRepuestoDAO {
	
	
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public TipoRepuesto incluir(TipoRepuesto TipoRepuesto) {
		em.persist(TipoRepuesto);
		em.flush();
		return TipoRepuesto;
	}
	
	@Transactional
	public TipoRepuesto editar(TipoRepuesto TipoRepuesto) {
		TipoRepuesto.setFechaModificacion(new Date());
		em.merge(TipoRepuesto);
		return TipoRepuesto;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoRepuesto> listarTipoRepuestos() {
		List<TipoRepuesto> tipoRepuestos = new ArrayList<TipoRepuesto>();
		try {
			Query query = em.createQuery("SELECT trp FROM TipoRepuesto trp order by id");
			tipoRepuestos = (List<TipoRepuesto>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoRepuestos;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoRepuesto> buscarTipoRepuestos(String descripcion) {
		List<TipoRepuesto> tipoRepuestos = new ArrayList<TipoRepuesto>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT trp FROM TipoRepuesto";
			hql += String.format(" trp where  upper(trp.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			tipoRepuestos = (List<TipoRepuesto>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoRepuestos;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoRepuesto> listarRepuestoXPresupuesto(long idPresupuesto) {
		List<TipoRepuesto> tipoRepuestos = new ArrayList<TipoRepuesto>();
		try {//bien
		//	Query query = em.createQuery("SELECT g FROM Herramienta g where id=(select idHerramienta h FROM ServiciosHerramientas h where  idServicio="+idServicio+" order by id");
			Query query = em.createQuery("SELECT tp from TipoRepuesto tp  Inner join tp.presupuestoTipoRepuestos ptr  where ptr.presupuesto.id=?1");
		
			query.setParameter(1, idPresupuesto).setMaxResults(query.getMaxResults());
			//usuario = query.getResultList().size() > 0 ? (Usuario) query.getResultList().get(0) : null;
			tipoRepuestos = (List<TipoRepuesto>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoRepuestos;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirTipoRepuesto(TipoRepuesto TipoRepuesto){
		boolean guardado = false;
		try {
			if(incluir(TipoRepuesto) != null){
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
	public boolean editarTipoRepuesto(TipoRepuesto TipoRepuesto){
		boolean guardado = false;
		try {
			if(editar(TipoRepuesto) != null){
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
