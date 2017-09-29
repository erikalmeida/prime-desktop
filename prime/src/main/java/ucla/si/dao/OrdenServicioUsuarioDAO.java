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
import ucla.si.modelo.OrdenServicioUsuario;


@Repository
public class OrdenServicioUsuarioDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public OrdenServicioUsuario incluir(OrdenServicioUsuario ordenServicioUsuario) {
		em.persist(ordenServicioUsuario);
		em.flush();
		return ordenServicioUsuario;
	}
	
	@Transactional
	public OrdenServicioUsuario editar(OrdenServicioUsuario ordenServicioUsuario) {
		ordenServicioUsuario.setFechaModificacion(new Date());
		em.merge(ordenServicioUsuario);
		return ordenServicioUsuario;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<OrdenServicioUsuario> listarOrdenServicioUsuario() {
		List<OrdenServicioUsuario> ordenServicioUsuario = new ArrayList<OrdenServicioUsuario>();
		try {
			Query query = em.createQuery("SELECT g FROM ServiciosHerramientas g order by id");
			ordenServicioUsuario = (List<OrdenServicioUsuario>) query.getResultList();
			if(!ordenServicioUsuario.isEmpty()){
				for (OrdenServicioUsuario ordenServicioUsuarios : ordenServicioUsuario) {
					Hibernate.initialize(ordenServicioUsuarios.getOrdenServicio());
					Hibernate.initialize(ordenServicioUsuarios.getUsuario());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordenServicioUsuario;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirOrdenServicioUsuario(OrdenServicioUsuario ordenServicioUsuario){
		boolean guardado = false;
		try {
			if(incluir(ordenServicioUsuario) != null){
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
	public boolean editarServiciosHerramientas(OrdenServicioUsuario ordenServicioUsuario){
		boolean guardado = false;
		try {
			if(editar(ordenServicioUsuario) != null){
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
