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

import ucla.si.modelo.Pasatiempo;

@Repository
public class PasatiempoDAO {
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Pasatiempo incluir(Pasatiempo pasatiempo){
		em.persist(pasatiempo);
		em.flush();
		return pasatiempo;
	}
	
	@Transactional
	public Pasatiempo editar(Pasatiempo pasatiempo){
		pasatiempo.setFechaModificacion(new Date());
		em.merge(pasatiempo);
		return pasatiempo;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Pasatiempo> listarPasatiempos(){
		List<Pasatiempo> pasatiempos = new ArrayList<Pasatiempo>();
		try {
			Query query = em.createQuery("SELECT g FROM Pasatiempo g order by id");
			pasatiempos = (List<Pasatiempo>) query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return pasatiempos;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Pasatiempo> buscarPasatiempos(String descripcion) {
		List<Pasatiempo> pasatiempos = new ArrayList<Pasatiempo>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT pa FROM Pasatiempo";
			hql += String.format(" pa where  upper(pa.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			pasatiempos = (List<Pasatiempo>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pasatiempos;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirPasatiempo(Pasatiempo pasatiempo){
		boolean guardado = false;
		try {
			if(incluir(pasatiempo) != null){
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
	public boolean editarPasatiempo(Pasatiempo pasatiempo){
		boolean guardado = false;
		try {
			if(editar(pasatiempo) != null){
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
