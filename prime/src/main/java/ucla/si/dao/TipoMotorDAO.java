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
import ucla.si.modelo.TipoMotor;;

@Repository
public class TipoMotorDAO {
	
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public TipoMotor incluir(TipoMotor tipoMotor) {
		em.persist(tipoMotor);
		em.flush();
		return tipoMotor;
	}
	
	
	@Transactional
	public TipoMotor editar(TipoMotor tipoMotor) {
		tipoMotor.setFechaModificacion(new Date());
		em.merge(tipoMotor);
		return tipoMotor;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoMotor> listartiposMotores() {
		List<TipoMotor> tipoMotores = new ArrayList<TipoMotor>();
		try {
			Query query = em.createQuery("SELECT g FROM TipoMotor g order by id");
			tipoMotores = (List<TipoMotor>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoMotores;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoMotor> buscarTipoMotores(String descripcion) {
		List<TipoMotor> tipoMotores = new ArrayList<TipoMotor>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT tp FROM TipoMotor";
			hql += String.format(" tp where  upper(tp.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			tipoMotores = (List<TipoMotor>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoMotores;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirTipoMotor(TipoMotor tipoMotor){
		boolean guardado = false;
		try {
			if(incluir(tipoMotor) != null){
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
	public boolean editarTipoMotor(TipoMotor tipoMotor){
		boolean guardado = false;
		try {
			if(editar(tipoMotor) != null){
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
