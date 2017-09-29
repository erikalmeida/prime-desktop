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

import ucla.si.modelo.TipoClase;

@Repository
public class TipoClaseDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public TipoClase incluir(TipoClase tipoClase) {
		em.persist(tipoClase);
		em.flush();
		return tipoClase;
	}
	
	@Transactional
	public TipoClase editar(TipoClase tipoClase) {
		tipoClase.setFechaModificacion(new Date());
		em.merge(tipoClase);
		return tipoClase;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoClase> listartiposClases() {
		List<TipoClase> tipoClases = new ArrayList<TipoClase>();
		try {
			Query query = em.createQuery("SELECT g FROM TipoClase g order by id");
			tipoClases = (List<TipoClase>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoClases;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoClase> buscarTipoClases(String descripcion) {
		List<TipoClase> tipoClases = new ArrayList<TipoClase>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT tc FROM TipoClase";
			hql += String.format(" tc where  upper(tc.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			tipoClases = (List<TipoClase>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoClases;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirTipoClase(TipoClase tipoClase){
		boolean guardado = false;
		try {
			if(incluir(tipoClase) != null){
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
	public boolean editarTipoClase(TipoClase tipoClase){
		boolean guardado = false;
		try {
			if(editar(tipoClase) != null){
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
