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

import ucla.si.modelo.TipoRefrigerante;

@Repository
public class TipoRefrigeranteDAO {

	

	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public TipoRefrigerante incluir(TipoRefrigerante tipoRefrigerante) {
		em.persist(tipoRefrigerante);
		em.flush();
		return tipoRefrigerante;
	}
	
	@Transactional
	public TipoRefrigerante editar(TipoRefrigerante tipoRefrigerante) {
		tipoRefrigerante.setFechaModificacion(new Date());
		em.merge(tipoRefrigerante);
		return tipoRefrigerante;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoRefrigerante> listartiposTipoRefrigerantes() {
		List<TipoRefrigerante> tipoRefrigerantes = new ArrayList<TipoRefrigerante>();
		try {
			Query query = em.createQuery("SELECT tr FROM TipoRefrigerante tr order by id");
			tipoRefrigerantes = (List<TipoRefrigerante>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoRefrigerantes;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoRefrigerante> buscarTipoRefrigerantes(String descripcion) {
		List<TipoRefrigerante> tipoRefrigerantes = new ArrayList<TipoRefrigerante>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT tr FROM TipoRefrigerante";
			hql += String.format(" tr where  upper(tr.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			tipoRefrigerantes = (List<TipoRefrigerante>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoRefrigerantes;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirTipoRefrigerante(TipoRefrigerante tipoRefrigerante){
		boolean guardado = false;
		try {
			if(incluir(tipoRefrigerante) != null){
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
	public boolean editarTipoRefrigerante(TipoRefrigerante tipoRefrigerante){
		boolean guardado = false;
		try {
			if(editar(tipoRefrigerante) != null){
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
