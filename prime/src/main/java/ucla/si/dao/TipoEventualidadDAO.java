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

import ucla.si.modelo.TipoEventualidad;

@Repository
public class TipoEventualidadDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public TipoEventualidad incluir(TipoEventualidad tipoEventualidad) {
		em.persist(tipoEventualidad);
		em.flush();
		return tipoEventualidad;
	}
	
	@Transactional
	public TipoEventualidad editar(TipoEventualidad tipoEventualidad){
		tipoEventualidad.setFechaModificacion(new Date());
		em.merge(tipoEventualidad);
		return tipoEventualidad;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoEventualidad> listarTipoEventualidades() {
		List<TipoEventualidad> tipoEventualidades = new ArrayList<TipoEventualidad>();
		
		try {
			Query query = em.createQuery("SELECT t FROM TipoEventualidad t order by id");
			tipoEventualidades = (List<TipoEventualidad>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoEventualidades;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirTipoEventualidad(TipoEventualidad tipoEventualidad){
		boolean guardado = false;
		try {
			if(incluir(tipoEventualidad) != null){
				guardado = true;
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			guardado = false;
		}
		return guardado;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoEventualidad> buscarTipoEventualidades(String descripcion) {
		List<TipoEventualidad> tipoEventualidades = new ArrayList<TipoEventualidad>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT t FROM TipoEventualidad";
			hql += String.format(" t where  upper(t.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			tipoEventualidades = (List<TipoEventualidad>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoEventualidades;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean editarTipoEventualidad(TipoEventualidad tipoEventualidad){
		boolean guardado = false;
		try {
			if(editar(tipoEventualidad) != null){
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
