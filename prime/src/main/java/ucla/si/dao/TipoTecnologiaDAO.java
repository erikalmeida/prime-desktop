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

import ucla.si.modelo.TipoTecnologia;

@Repository
public class TipoTecnologiaDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public TipoTecnologia incluir(TipoTecnologia tipoTecnologia) {
		em.persist(tipoTecnologia);
		em.flush();
		return tipoTecnologia;
	}
	
	@Transactional
	public TipoTecnologia editar(TipoTecnologia tipoTecnologia) {
		tipoTecnologia.setFechaModificacion(new Date());
		em.merge(tipoTecnologia);
		return tipoTecnologia;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoTecnologia> listarTipoTecnologias() {
		List<TipoTecnologia> tipoTecnologias = new ArrayList<TipoTecnologia>();
		try {
			Query query = em.createQuery("SELECT tt FROM TipoTecnologia tt order by id");
			tipoTecnologias = (List<TipoTecnologia>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoTecnologias;
	}
	

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoTecnologia> buscarTipoTecnologias(String descripcion) {
		List<TipoTecnologia> tipoTecnologias = new ArrayList<TipoTecnologia>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT tt FROM TipoTecnologia";
			hql += String.format(" tt where  upper(tt.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			tipoTecnologias = (List<TipoTecnologia>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoTecnologias;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirTipoTecnologia(TipoTecnologia tipoTecnologia){
		boolean guardado = false;
		try {
			if(incluir(tipoTecnologia) != null){
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
	public boolean editarTipoTecnologia(TipoTecnologia tipoTecnologia){
		boolean guardado = false;
		try {
			if(editar(tipoTecnologia) != null){
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

