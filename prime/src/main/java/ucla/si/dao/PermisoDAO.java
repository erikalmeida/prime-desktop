package ucla.si.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ucla.si.modelo.Grupo;
import ucla.si.modelo.Permiso;

@Repository
public class PermisoDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public Permiso incluir(Permiso permiso) {
		em.persist(permiso);
		em.flush();
		return permiso;
	}

	@Transactional
	public Permiso editar(Permiso permiso) {
		permiso.setFechaModificacion(new Date());
		em.merge(permiso);
		return permiso;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Permiso> listarPermisos(Grupo grupo) {
		List<Permiso> permisos = new ArrayList<Permiso>();
		try {
			Query query = em.createQuery("SELECT p FROM Permiso p "
				+" inner join p.grupo g "
				+" Where g.nombre = ?1 "
				+" order by p.id");
			query.setParameter(1, grupo.getNombre());
			permisos = (List<Permiso>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return permisos;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Map<String,Permiso> arbolGrupo(Grupo grupo) {
		List<Permiso> permisos = new ArrayList<Permiso>();
		Map<String,Permiso> arbolGrupo = new HashMap<String, Permiso>();
		try {
			Query query = em.createQuery("SELECT p FROM Permiso p "
				+" inner join p.grupo g "
				+" inner join p.accion a "
				+" Where g.nombre = ?1 "
				+" and a.nombre not in('LISTAR') "
				+" order by p.id");
			query.setParameter(1, grupo.getNombre());
			permisos = (List<Permiso>) query.getResultList();
			for (Permiso permiso : permisos) {
				Hibernate.initialize(permiso.getFuncion());
				Hibernate.initialize(permiso.getAccion());
				String clave = permiso.getFuncion().getClave()+"-"+permiso.getAccion().getNombre().toUpperCase();
				System.out.println("clave permiso "+clave);
				if(!arbolGrupo.containsKey(clave)){
					arbolGrupo.put(clave, permiso);
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arbolGrupo;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Map<String,Permiso> menuGrupo(Grupo grupo) {
		List<Permiso> permisos = new ArrayList<Permiso>();
		Map<String,Permiso> arbolGrupo = new HashMap<String, Permiso>();
		try {
			Query query = em.createQuery("SELECT p FROM Permiso p "
				+" inner join p.grupo g "
				+" inner join p.accion a "
				+" Where g.nombre = ?1 "
				+" and a.nombre not in('LISTAR') "
				+" order by p.id");
			query.setParameter(1, grupo.getNombre());
			permisos = (List<Permiso>) query.getResultList();
			for (Permiso permiso : permisos) {
				Hibernate.initialize(permiso.getFuncion());
				Hibernate.initialize(permiso.getAccion());
				String clave = permiso.getFuncion().getClave();
				System.out.println("clave permiso "+clave);
				if(arbolGrupo.containsKey(clave)){
					Permiso permiso2 = arbolGrupo.get(clave);
					permiso = permiso.isAcceso() ? permiso : permiso2;
					arbolGrupo.put(clave, permiso);
				}
				else{
					arbolGrupo.put(clave, permiso);
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arbolGrupo;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirPermiso(Permiso permiso){
		boolean guardado = false;
		try {
			if(incluir(permiso) != null){
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
	public boolean editarPermiso(Permiso permiso){
		boolean guardado = false;
		try {
			if(editar(permiso) != null){
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
