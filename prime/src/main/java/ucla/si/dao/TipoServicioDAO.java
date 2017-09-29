package ucla.si.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ucla.si.modelo.TipoServicio;
import ucla.si.modelo.Servicio;


@Repository
public class TipoServicioDAO {

	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public TipoServicio incluir(TipoServicio tipoServicio) {
		em.persist(tipoServicio);
		em.flush();
		return tipoServicio;
	}
	
	@Transactional
	public TipoServicio editar(TipoServicio tipoServicio) {
		tipoServicio.setFechaModificacion(new Date());
		em.merge(tipoServicio);
		return tipoServicio;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoServicio> listarTipoServicios() {
		List<TipoServicio> tipoServicios = new ArrayList<TipoServicio>();
		try {
			Query query = em.createQuery("SELECT g FROM TipoServicio g order by id");
			tipoServicios = (List<TipoServicio>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoServicios;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoServicio> buscarTipoServicios(String descripcion) {
		List<TipoServicio> tipoServicios = new ArrayList<TipoServicio>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT g FROM TipoServicio";
			hql += String.format(" g where  upper(g.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			tipoServicios = (List<TipoServicio>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoServicios;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirTipoServicio(TipoServicio tipoServicio){
		boolean guardado = false;
		try {
			if(incluir(tipoServicio) != null){
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
	public boolean editarTipoServicio(TipoServicio tipoServicio){
		boolean guardado = false;
		try {
			if(editar(tipoServicio) != null){
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
