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

import ucla.si.modelo.Garantia;

@Repository
public class GarantiaDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Garantia incluir(Garantia garantia) {
		em.persist(garantia);
		em.flush();
		return garantia;
	}
	
	@Transactional
	public Garantia editar(Garantia garantia) {
		garantia.setFechaModificacion(new Date());
		em.merge(garantia);
		return garantia;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Garantia> listarGarantias() {
		List<Garantia> garantias = new ArrayList<Garantia>();
		try {
			Query query = em.createQuery("SELECT g FROM Garantia g order by id");
			garantias = (List<Garantia>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return garantias;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirGarantia(Garantia garantia){
		boolean guardado = false;
		try {
			if(incluir(garantia) != null){
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
	public boolean editarGarantia(Garantia garantia){
		boolean guardado = false;
		try {
			if(editar(garantia) != null){
				guardado = true;
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			guardado = false;
		}
		return guardado;
	}
	
	@Transactional(readOnly = true)
	public Garantia getGarantia(Long id) {
		Garantia garantia = null;
		try {
			Query query = em.createQuery("SELECT t FROM Garantia t where t.id = ?1 ");
			query.setParameter(1, id).setMaxResults(1);
			garantia = query.getResultList().size() > 0 ? (Garantia) query.getResultList().get(0) : null;
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return garantia;
	}

}
