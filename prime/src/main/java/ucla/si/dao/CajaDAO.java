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

import ucla.si.modelo.Caja;

@Repository
public class CajaDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Caja incluir(Caja caja) {
		em.persist(caja);
		em.flush();
		return caja;
	}
	
	@Transactional
	public Caja editar(Caja caja){
		caja.setFechaModificacion(new Date());
		em.merge(caja);
		return caja;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Caja> listarCajas() {
		List<Caja> cajas = new ArrayList<Caja>();
		try {
			Query query = em.createQuery("SELECT ca FROM Caja ca order by id");
			cajas = (List<Caja>) query.getResultList();
			if(!cajas.isEmpty()){
				for (Caja caja : cajas) {
					Hibernate.initialize(caja.getTipoCaja());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cajas;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Caja> buscarCajas(String descripcion) {
		List<Caja> cajas = new ArrayList<Caja>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT ca FROM Caja";
			hql += String.format(" ca where  upper(ca.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			cajas = (List<Caja>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cajas;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirCaja(Caja caja){
		boolean guardado = false;
		try {
			if(incluir(caja) != null){
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
	public boolean editarCaja(Caja caja){
		boolean guardado = false;
		try {
			if(editar(caja) != null){
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
