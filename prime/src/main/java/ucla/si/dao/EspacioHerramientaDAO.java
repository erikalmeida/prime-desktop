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

import ucla.si.modelo.EspacioHerramienta;

@Repository
public class EspacioHerramientaDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public EspacioHerramienta incluir(EspacioHerramienta espacioHerramienta) {
		em.persist(espacioHerramienta);
		em.flush();
		return espacioHerramienta;
	}
	
	@Transactional
	public EspacioHerramienta editar(EspacioHerramienta espacioHerramienta) {
		espacioHerramienta.setFechaModificacion(new Date());
		em.merge(espacioHerramienta);
		return espacioHerramienta;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<EspacioHerramienta> listarEspacioHerramienta() {
		List<EspacioHerramienta> espacioHerramienta= new ArrayList<EspacioHerramienta>();
		try {
			Query query = em.createQuery("SELECT g FROM EspacioHerramienta g order by id");
			espacioHerramienta = (List<EspacioHerramienta>) query.getResultList();
			if(!espacioHerramienta.isEmpty()){
				for (EspacioHerramienta espacioHerramientas : espacioHerramienta) {
					Hibernate.initialize(espacioHerramientas.getHerramienta());
					Hibernate.initialize(espacioHerramientas.getEspacio());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return espacioHerramienta;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirEspacioHerramienta(EspacioHerramienta espacioHerramienta){
		boolean guardado = false;
		try {
			if(incluir(espacioHerramienta) != null){
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
	public boolean editarServiciosHerramientas(EspacioHerramienta espacioHerramienta){
		boolean guardado = false;
		try {
			if(editar(espacioHerramienta) != null){
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
