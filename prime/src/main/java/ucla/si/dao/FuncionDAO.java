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

import ucla.si.modelo.Funcion;
import ucla.si.modelo.Grupo;

@Repository
public class FuncionDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public Funcion incluir(Funcion funcion) {
		em.persist(funcion);
		em.flush();
		return funcion;
	}

	@Transactional
	public Funcion editar(Funcion funcion){
		funcion.setFechaModificacion(new Date());
		em.merge(funcion);
		return funcion;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Funcion> listarFunciones() {
		List<Funcion> funciones = new ArrayList<Funcion>();
		try {
			Query query = em.createQuery("SELECT f FROM Funcion f order by f.id");
			funciones = (List<Funcion>) query.getResultList();
			if(!funciones.isEmpty()){
				for (Funcion funcion : funciones) {
					Hibernate.initialize(funcion.getSistema());
					Hibernate.initialize(funcion.getFuncionPadre());
					Funcion funcionRecursion = null;
					if(funcion.getFuncionPadre() != null){
						funcionRecursion = funcion.getFuncionPadre();
						Hibernate.initialize(funcionRecursion.getFuncionPadre());
						while (funcionRecursion.getFuncionPadre() != null) {
							Hibernate.initialize(funcionRecursion.getFuncionPadre());
							funcionRecursion = funcionRecursion.getFuncionPadre();
						}
					}
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return funciones;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Map<String,Long> contadorFunciones() {
		List<Funcion> funciones = new ArrayList<Funcion>();
		Map<String,Long> contadorFunciones = new HashMap<String, Long>();
		try {
			Query query = em.createQuery("SELECT f FROM Funcion f order by f.id");
			funciones = (List<Funcion>) query.getResultList();
			if(!funciones.isEmpty()){
				for (Funcion funcion : funciones) {
					Hibernate.initialize(funcion.getSistema());
					Hibernate.initialize(funcion.getFuncionPadre());
					Query query2 = em.createQuery("SELECT count(f) FROM Funcion f where f.funcionPadre.id =?1");
					query2.setParameter(1,funcion.getId());
					Long contador = query2.getResultList().size() > 0 ? (Long) query2.getResultList().get(0) : 0;
					contadorFunciones.put(funcion.getClave(),contador);
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contadorFunciones;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Map<String,Long> contadorFuncionesPorPermisos(Grupo grupo) {
		List<Funcion> funciones = new ArrayList<Funcion>();
		Map<String,Long> contadorFuncionesPorPermisos = new HashMap<String, Long>();
		try {
			Query query = em.createQuery("SELECT f "
				+" From Funcion f "
				+" inner join f.permisos p "
				+" inner join p.grupo g "
				+" Where g.nombre = ?1 "
				+" group by f.id");
			query.setParameter(1, grupo.getNombre());
			funciones = (List<Funcion>) query.getResultList();
			for (Funcion funcion : funciones) {
				Query query2 = em.createQuery("Select count(p) "
					+ " from Permiso p "
					+ " where p.funcion.id =?1 "
					+ " and p.grupo.id =?2 "
					+ " and p.acceso = true");
				query2.setParameter(1, funcion.getId());
				query2.setParameter(2, grupo.getId());
				Long contador = query2.getResultList().size() > 0 ? (Long)query2.getResultList().get(0) : 0;
				contadorFuncionesPorPermisos.put(funcion.getClave(), contador);
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contadorFuncionesPorPermisos;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Funcion> listarFuncionesHojas() {
		List<Funcion> funciones = new ArrayList<Funcion>();
		try {
			Query query = em.createQuery("SELECT f FROM Funcion f "
					+ " where 0 = (SELECT COUNT(fh) FROM Funcion fh where fh.funcionPadre.id = f.id) "
					+ " order by f.id");
			funciones = (List<Funcion>) query.getResultList();
			if(!funciones.isEmpty()){
				for (Funcion funcion : funciones) {
					Hibernate.initialize(funcion.getSistema());
					Hibernate.initialize(funcion.getFuncionPadre());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return funciones;
	}

	@Transactional(readOnly = true)
	public Funcion buscarFuncion(Long id) {
		Funcion funcion = null;
		try {
			Query query = em.createQuery("SELECT f FROM Funcion f "
					+ " WHERE f.id =?1 "
					+ " order by f.id");
			query.setParameter(1,id);
			funcion = (Funcion) query.getResultList().get(0);
			if(funcion != null){
				Hibernate.initialize(funcion.getSistema());
				Hibernate.initialize(funcion.getFuncionPadre());
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return funcion;
	}

	@Transactional(readOnly = true)
	public boolean validarPadre(Long id) {
		boolean funcion = false;
		try {
			Query query = em.createQuery("SELECT f FROM Funcion f "
					+ " WHERE f.id =?1 "
					+ " AND f.funcionPadre.id is not null ");
			query.setParameter(1,id);
			funcion = query.getResultList().size() == 0 ? false : true;
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return funcion;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirFuncion(Funcion funcion){
		boolean guardado = false;
		try {
			if(incluir(funcion) != null){
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
	public boolean editarFuncion(Funcion funcion){
		boolean guardado = false;
		try {
			if(editar(funcion) != null){
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
