package ucla.si.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ucla.si.modelo.Persona;

@Repository
public class PersonaDAO {
	
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Persona incluir(Persona persona) {
		em.persist(persona);
		em.flush();
		return persona;
	}
	
	@Transactional
	public Persona editar(Persona persona){
		persona.setFechaModificacion(new Date());
		em.merge(persona);
		return persona;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Persona> listarPersonas() {
		List<Persona> personas = new ArrayList<Persona>();
		try {
			Query query = em.createQuery("SELECT g FROM Persona g order by id");
			personas = (List<Persona>) query.getResultList();
			if(!personas.isEmpty()){
				for (Persona persona : personas) {
					Hibernate.initialize(persona.getOcupacion());
					Hibernate.initialize(persona.getPasatiempo());
					Hibernate.initialize(persona.getProfesion());
					Hibernate.initialize(persona.getDeporte());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return personas;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirPersona(Persona persona){
		boolean guardado = false;
		try {
			if(incluir(persona) != null){
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
	public boolean editarPersona(Persona persona){
		boolean guardado = false;
		try {
			if(editar(persona) != null){
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
	public Persona getPersona(Long id) {
		Persona persona= new Persona();
		try {
			Query query = em.createQuery("SELECT t FROM Persona t where t.id=?1 ");
			query.setParameter(1, id);
			
			persona =(Persona) query.getSingleResult();
	
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return persona;
	}

}
