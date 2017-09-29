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

import ucla.si.modelo.Tarifa;

@Repository
public class TarifaDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Tarifa incluir(Tarifa tarifa) {
		em.persist(tarifa);
		em.flush();
		return tarifa;
	}
	
	@Transactional
	public Tarifa editar(Tarifa tarifa) {
		tarifa.setFechaModificacion(new Date());
		em.merge(tarifa);
		return tarifa;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Tarifa> listarTarifas() {
		List<Tarifa> tarifas = new ArrayList<Tarifa>();
		try {
			Query query = em.createQuery("SELECT e FROM Tarifa e order by id");
			tarifas = (List<Tarifa>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tarifas;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Tarifa> buscarTarifas(String descripcion) {
		List<Tarifa> tarifas = new ArrayList<Tarifa>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT e FROM Tarifa";
			hql += String.format(" e where  upper(e.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			tarifas = (List<Tarifa>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tarifas;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirTarifa(Tarifa tarifa){
		boolean guardado = false;
		try {
			if(incluir(tarifa) != null){
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
	public boolean editarTarifa(Tarifa tarifa){
		boolean guardado = false;
		try {
			if(editar(tarifa) != null){
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
