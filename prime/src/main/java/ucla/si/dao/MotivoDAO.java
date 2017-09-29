package ucla.si.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ucla.si.modelo.Motivo;




@Repository
public class MotivoDAO  {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public Motivo incluir(Motivo motivo) {
		em.persist(motivo);
		em.flush();
		return motivo;
	}
	
	@Transactional
	public Motivo editar(Motivo motivo) {
		motivo.setFechaModificacion(new Date());
		em.merge(motivo);
		return motivo;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Motivo> listarMotivos() {
		List<Motivo> motivos = new ArrayList<Motivo>();
		try {
			Query query = em.createQuery("SELECT mo FROM Motivo mo order by id");
			motivos = (List<Motivo>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return motivos;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Motivo> buscarMotivos(String descripcion) {
		List<Motivo> motivos = new ArrayList<Motivo>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT mo FROM Motivo";
			hql += String.format(" mo where  upper(mo.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			motivos = (List<Motivo>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return motivos;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirMotivo(Motivo motivo){
		boolean guardado = false;
		try {
			if(incluir(motivo) != null){
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
	public boolean editarMotivo(Motivo motivo){
		boolean guardado = false;
		try {
			if(editar(motivo) != null){
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
