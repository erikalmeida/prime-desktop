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

import ucla.si.modelo.Grupo;

@Repository
public class GrupoDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public Grupo incluir(Grupo grupo) {
		em.persist(grupo);
		em.flush();
		return grupo;
	}

	@Transactional
	public Grupo editar(Grupo grupo) {
		grupo.setFechaModificacion(new Date());
		em.merge(grupo);
		return grupo;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Grupo> listarGrupos() {
		List<Grupo> grupos = new ArrayList<Grupo>();
		try {
			Query query = em.createQuery("SELECT g FROM Grupo g order by id");
			grupos = (List<Grupo>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grupos;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Grupo> listarGruposSinAdministrador() {
		List<Grupo> grupos = new ArrayList<Grupo>();
		try {
			Query query = em.createQuery("SELECT g FROM Grupo g WHERE g.nombre not in('ADMINISTRADOR') order by id");
			grupos = (List<Grupo>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grupos;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirGrupo(Grupo grupo){
		boolean guardado = false;
		try {
			if(incluir(grupo) != null){
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
	public boolean editarGrupo(Grupo grupo){
		boolean guardado = false;
		try {
			if(editar(grupo) != null){
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
	public Grupo buscarGrupo(String nombre) {
		Grupo grupo = null;
		try {
			Query query = em.createQuery("SELECT g FROM Grupo g "
					+ " where g.nombre = ?1 ");
			query.setParameter(1, nombre).setMaxResults(1);
			grupo = query.getResultList().size() > 0 ? (Grupo) query.getResultList().get(0) : null;
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grupo;
	}

}
