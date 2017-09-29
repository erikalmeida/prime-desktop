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


import ucla.si.modelo.Motor;

@Repository
public class MotorDAO {
	
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Motor incluir(Motor motor) {
		em.persist(motor);
		em.flush();
		return motor;
	}
	
	@Transactional
	public Motor editar(Motor motor){
		motor.setFechaModificacion(new Date());
		em.merge(motor);
		return motor;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Motor> listarMotores() {
		List<Motor> motores = new ArrayList<Motor>();
		try {
			Query query = em.createQuery("SELECT mt FROM Motor mt order by id");
			motores = (List<Motor>) query.getResultList();
			if(!motores.isEmpty()){
				for (Motor motor : motores) {
					Hibernate.initialize(motor.getTipoMotor());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return motores;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Motor> buscarMotores(String descripcion) {
		List<Motor> motores = new ArrayList<Motor>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT mt FROM Motor";
			hql += String.format(" mt where  upper(mt.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			motores = (List<Motor>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return motores;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirMotor(Motor motor){
		boolean guardado = false;
		try {
			if(incluir(motor) != null){
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
	public boolean editarMotor(Motor motor){
		boolean guardado = false;
		try {
			if(editar(motor) != null){
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
