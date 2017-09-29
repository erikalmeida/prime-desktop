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

import ucla.si.modelo.Accesorio;
import ucla.si.modelo.Accesorio;

@Repository
public class AccesorioDAO {
	
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Accesorio incluir(Accesorio accesorio) {
		em.persist(accesorio);
		em.flush();
		return accesorio;
	}
	
	
	@Transactional
	public Accesorio editar(Accesorio accesorio) {
		accesorio.setFechaModificacion(new Date());
		em.merge(accesorio);
		return accesorio;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Accesorio> listarAccesorios() {
		List<Accesorio> accesorios = new ArrayList<Accesorio>();
		try {
			Query query = em.createQuery("SELECT acc FROM Accesorio acc order by id");
			accesorios = (List<Accesorio>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accesorios;
	}
	

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Accesorio> buscarAccesorios(String descripcion) {
		List<Accesorio> accesorios = new ArrayList<Accesorio>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT acc FROM Accesorio";
			hql += String.format(" acc where  upper(acc.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			accesorios = (List<Accesorio>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accesorios;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirAccesorio(Accesorio accesorio){
		boolean guardado = false;
		try {
			if(incluir(accesorio) != null){
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
	public boolean editarAccesorio(Accesorio accesorio){
		boolean guardado = false;
		try {
			if(editar(accesorio) != null){
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
