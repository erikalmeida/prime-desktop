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

import ucla.si.modelo.TipoGarantia;

@Repository
public class TipoGarantiaDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public TipoGarantia incluir(TipoGarantia tipoGarantia) {
		em.persist(tipoGarantia);
		em.flush();
		return tipoGarantia;
	}
	
	@Transactional
	public TipoGarantia editar(TipoGarantia tipoGarantia) {
		tipoGarantia.setFechaModificacion(new Date());
		em.merge(tipoGarantia);
		return tipoGarantia;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoGarantia> listarTipoGarantias() {
		List<TipoGarantia> tipoGarantias = new ArrayList<TipoGarantia>();
		try {
			Query query = em.createQuery("SELECT g FROM TipoGarantia g order by id");
			tipoGarantias = (List<TipoGarantia>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoGarantias;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirTipoGarantia(TipoGarantia tipoGarantia){
		boolean guardado = false;
		try {
			if(incluir(tipoGarantia) != null){
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
	public boolean editarTipoGarantia(TipoGarantia tipoGarantia){
		boolean guardado = false;
		try {
			if(editar(tipoGarantia) != null){
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
