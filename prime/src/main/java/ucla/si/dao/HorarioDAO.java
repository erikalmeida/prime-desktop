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

import ucla.si.modelo.Horario;
import ucla.si.modelo.Horario;

@Repository
public class HorarioDAO {
	

	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Horario incluir(Horario horario) {
		em.persist(horario);
		em.flush();
		return horario;
	}
	
	@Transactional
	public Horario editar(Horario horario) {
		horario.setFechaModificacion(new Date());
		em.merge(horario);
		return horario;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Horario> listarHorarios() {
		List<Horario> horarios = new ArrayList<Horario>();
		try {
			Query query = em.createQuery("SELECT ho FROM Horario ho order by id");
			horarios = (List<Horario>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return horarios;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirHorario(Horario horario){
		boolean guardado = false;
		try {
			if(incluir(horario) != null){
				guardado = true;
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			guardado = false;
		}
		return guardado;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Horario> buscarHorarios(String descripcion) {
		List<Horario> horarios = new ArrayList<Horario>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT ho FROM Horario";
			hql += String.format(" ho where  upper(ho.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			horarios = (List<Horario>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return horarios;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean editarHorario(Horario horario){
		boolean guardado = false;
		try {
			if(editar(horario) != null){
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
