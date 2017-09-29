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


import ucla.si.modelo.Viaje;

@Repository
public class ViajeDAO {
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Viaje incluir(Viaje viaje) {
		em.persist(viaje);
		em.flush();
		return viaje;
	}
	
	@Transactional
	public Viaje editar(Viaje viaje){
		viaje.setFechaModificacion(new Date());
		em.merge(viaje);
		return viaje;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Viaje> listarViajes() {
		List<Viaje> viajes = new ArrayList<Viaje>();
		
		try {
			Query query = em.createQuery("SELECT v FROM Viaje v order by id");
			viajes = (List<Viaje>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return viajes;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Viaje> buscarViajes(String descripcion) {
		List<Viaje> viajes = new ArrayList<Viaje>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT v FROM Viaje";
			hql += String.format(" v where  upper(v.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			viajes = (List<Viaje>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return viajes;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirViaje(Viaje viaje){
		boolean guardado = false;
		try {
			if(incluir(viaje) != null){
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
	public boolean editarViaje(Viaje viaje){
		boolean guardado = false;
		try {
			if(editar(viaje) != null){
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
