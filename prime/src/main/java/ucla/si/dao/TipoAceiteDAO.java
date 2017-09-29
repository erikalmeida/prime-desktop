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

import ucla.si.modelo.TipoAceite;

@Repository
public class TipoAceiteDAO {
	

	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public TipoAceite incluir(TipoAceite tipoAceite) {
		em.persist(tipoAceite);
		em.flush();
		return tipoAceite;
	}
	
	@Transactional
	public TipoAceite editar(TipoAceite tipoAceite) {
		tipoAceite.setFechaModificacion(new Date());
		em.merge(tipoAceite);
		return tipoAceite;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoAceite> listartiposAceites() {
		List<TipoAceite> tipoAceites = new ArrayList<TipoAceite>();
		try {
			Query query = em.createQuery("SELECT g FROM TipoAceite g order by id");
			tipoAceites = (List<TipoAceite>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoAceites;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoAceite> buscarTipoAceites(String descripcion) {
		List<TipoAceite> tipoAceites = new ArrayList<TipoAceite>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT ta FROM TipoAceite";
			hql += String.format(" ta where  upper(ta.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			tipoAceites = (List<TipoAceite>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoAceites;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirTipoAceite(TipoAceite tipoAceite){
		boolean guardado = false;
		try {
			if(incluir(tipoAceite) != null){
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
	public boolean editarTipoAceite(TipoAceite tipoAceite){
		boolean guardado = false;
		try {
			if(editar(tipoAceite) != null){
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
