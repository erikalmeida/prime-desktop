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

import ucla.si.modelo.TipoCaja;;

@Repository

public class TipoCajaDAO {
	
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public TipoCaja incluir(TipoCaja tipoCaja) {
		em.persist(tipoCaja);
		em.flush();
		return tipoCaja;
	}
	
	
	@Transactional
	public TipoCaja editar(TipoCaja tipoCaja) {
		tipoCaja.setFechaModificacion(new Date());
		em.merge(tipoCaja);
		return tipoCaja;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoCaja> listartiposCajas() {
		List<TipoCaja> tipoCajas = new ArrayList<TipoCaja>();
		try {
			Query query = em.createQuery("SELECT tca FROM TipoCaja tca order by id");
			tipoCajas = (List<TipoCaja>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoCajas;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoCaja> buscarTipoCajas(String descripcion) {
		List<TipoCaja> tipoCajas = new ArrayList<TipoCaja>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT tca FROM TipoCaja";
			hql += String.format(" tca where  upper(tca.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			tipoCajas = (List<TipoCaja>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoCajas;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirTipoCaja(TipoCaja tipoCaja){
		boolean guardado = false;
		try {
			if(incluir(tipoCaja) != null){
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
	public boolean editarTipoCaja(TipoCaja tipoCaja){
		boolean guardado = false;
		try {
			if(editar(tipoCaja) != null){
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
