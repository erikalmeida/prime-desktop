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

import ucla.si.modelo.MarcaTecnologia;

@Repository
public class MarcaTecnologiaDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public MarcaTecnologia incluir(MarcaTecnologia marcaTecnologia) {
		em.persist(marcaTecnologia);
		em.flush();
		return marcaTecnologia;
	}
	
	@Transactional
	public MarcaTecnologia editar(MarcaTecnologia marcaTecnologia) {
		marcaTecnologia.setFechaModificacion(new Date());
		em.merge(marcaTecnologia);
		return marcaTecnologia;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<MarcaTecnologia> listarMarcaTecnologias() {
		List<MarcaTecnologia> marcaTecnologias = new ArrayList<MarcaTecnologia>();
		try {
			Query query = em.createQuery("SELECT mt FROM MarcaTecnologia mt order by id");
			marcaTecnologias = (List<MarcaTecnologia>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marcaTecnologias;
	}
	

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<MarcaTecnologia> buscarMarcaTecnologias(String descripcion) {
		List<MarcaTecnologia> marcaTecnologias = new ArrayList<MarcaTecnologia>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT mt FROM MarcaTecnologia";
			hql += String.format(" mt where  upper(mt.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			marcaTecnologias = (List<MarcaTecnologia>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marcaTecnologias;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirMarcaTecnologia(MarcaTecnologia marcaTecnologia){
		boolean guardado = false;
		try {
			if(incluir(marcaTecnologia) != null){
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
	public boolean editarMarcaTecnologia(MarcaTecnologia marcaTecnologia){
		boolean guardado = false;
		try {
			if(editar(marcaTecnologia) != null){
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
