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

import ucla.si.modelo.Agenda;



@Repository
public class AgendaDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Agenda incluir(Agenda agenda) {
		em.persist(agenda);
		em.flush();
		return agenda;
	}
	
	@Transactional
	public Agenda editar(Agenda agenda) {
		agenda.setFechaModificacion(new Date());
		em.merge(agenda);
		return agenda;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Agenda> listarAgendas() {
		List<Agenda> agendas = new ArrayList<Agenda>();
		try {
			Query query = em.createQuery("SELECT g FROM Agenda g order by id");
			agendas = (List<Agenda>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agendas;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirAgenda(Agenda agenda){
		boolean guardado = false;
		try {
			if(incluir(agenda) != null){
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
	public boolean editarAgenda(Agenda agenda){
		boolean guardado = false;
		try {
			if(editar(agenda) != null){
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
