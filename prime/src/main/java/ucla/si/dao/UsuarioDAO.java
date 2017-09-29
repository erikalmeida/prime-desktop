package ucla.si.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.ArrayList;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ucla.si.modelo.Persona;
import ucla.si.modelo.Usuario;

@Repository
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public Usuario buscarUsuario(String correo, String contrasenna) {
		Usuario usuario = null;
		try {
			/*
			 * EntityManagerFactory entityManagerFactory =
			 * Persistence.createEntityManagerFactory("myapp"); em =
			 * entityManagerFactory.createEntityManager();
			 */

			System.out.println("em " + em);

			Query query = em
					.createQuery("SELECT u FROM Usuario u " + " where u.correo = ?1 " + " and u.contrasenna = ?2 ");
			query.setParameter(1, correo).setParameter(2, contrasenna).setMaxResults(1);
			usuario = query.getResultList().size() > 0 ? (Usuario) query.getResultList().get(0) : null;
			System.out.println("espacio de memoria usuario " + usuario);
			if (usuario != null) {
				Hibernate.initialize(usuario.getPersona());
				Hibernate.initialize(usuario.getGrupo());
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	// @Transactional(readOnly = true)
	// public List<Usuario> queryAll() {
	// Query query = em.createQuery("SELECT u FROM Usuario u");
	// List<Usuario> result = query.getResultList();
	// return result;
	// }

	@Transactional(readOnly = true)
	public Usuario buscarUsuarioCorreo(String correo) {
		Usuario usuario = null;
		try {
			/*
			 * EntityManagerFactory entityManagerFactory =
			 * Persistence.createEntityManagerFactory("myapp"); em =
			 * entityManagerFactory.createEntityManager();
			 */

			System.out.println("em " + em);

			Query query = em.createQuery("SELECT u FROM Usuario u " + " where u.correo = ?1 ");
			query.setParameter(1, correo).setMaxResults(1);
			usuario = query.getResultList().size() > 0 ? (Usuario) query.getResultList().get(0) : null;
			System.out.println("espacio de memoria usuario " + usuario);
			if (usuario != null) {
				Hibernate.initialize(usuario.getPersona());
				Hibernate.initialize(usuario.getGrupo());
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	@Transactional(readOnly = true)
	public Usuario buscarUsuarioMecanico(long id_grupo) {
		Usuario usuario = null;
		try {
			/*
			 * EntityManagerFactory entityManagerFactory =
			 * Persistence.createEntityManagerFactory("myapp"); em =
			 * entityManagerFactory.createEntityManager();
			 */

			System.out.println("em " + em);

			Query query = em.createQuery("SELECT u FROM Usuario u " + " where u.idgrupo = ?1 ");
			query.setParameter(1, id_grupo).setMaxResults(1);
			usuario = query.getResultList().size() > 0 ? (Usuario) query.getResultList().get(0) : null;
			System.out.println("espacio de memoria usuario " + usuario);
			if (usuario != null) {
				Hibernate.initialize(usuario.getPersona());
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Usuario> listarUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			Query query = em.createQuery("SELECT g FROM Usuario g order by id");
			usuarios = (List<Usuario>) query.getResultList();
			if (!usuarios.isEmpty()) {
				for (Usuario usuario : usuarios) {
					Hibernate.initialize(usuario.getPersona());
					Hibernate.initialize(usuario.getGrupo());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Usuario> listarUsuariosMecanicos() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			Query query = em.createQuery("select u from Usuario u " + "inner join u.grupo g "
					+ "where g.nombre = 'MECANICO' order by u.id ");
			usuarios = (List<Usuario>) query.getResultList();
			if (!usuarios.isEmpty()) {
				for (Usuario usuario : usuarios) {
					Hibernate.initialize(usuario.getPersona());
					Hibernate.initialize(usuario.getGrupo());
				}
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Usuario> listarUsuariosSinAdministrador() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		List<Usuario> usuariosSinGrupos = new ArrayList<Usuario>();
		try {
			Query query = em.createQuery("SELECT u FROM Usuario u " + " inner join u.grupo g "
					+ " where g.nombre not in('ADMINISTRADOR') order by u.id ");
			usuarios = (List<Usuario>) query.getResultList();
			if (!usuarios.isEmpty()) {
				for (Usuario usuario : usuarios) {
					Hibernate.initialize(usuario.getPersona());
					Hibernate.initialize(usuario.getGrupo());
				}
			}
			Query query2 = em.createQuery("SELECT u FROM Usuario u " + " where u.grupo.id is null order by u.id ");
			usuariosSinGrupos = (List<Usuario>) query2.getResultList();
			if (!usuariosSinGrupos.isEmpty()) {
				for (Usuario usuario : usuariosSinGrupos) {
					Hibernate.initialize(usuario.getPersona());
					Hibernate.initialize(usuario.getGrupo());
				}
				usuarios.addAll(usuariosSinGrupos);
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	@Transactional(readOnly = true)
	public Usuario get(Long id) {
		return em.find(Usuario.class, id);
	}

	@Transactional
	public Usuario save(Usuario usuario) {
		em.persist(usuario);
		em.flush();
		return usuario;
	}

	@Transactional
	public Persona save(Persona persona) {
		em.persist(persona);
		em.flush();
		return persona;
	}

	@Transactional
	public Usuario editar(Usuario usuario) {
		usuario.setFechaModificacion(new Date());
		em.merge(usuario);
		return usuario;
	}

	@Transactional
	public Persona editar(Persona persona) {
		em.merge(persona);
		return persona;
	}

	/* Registrar Cliente */
	/*
	 * esta anotacion permite que si ocurre un error la bd no tenga cambios
	 * (propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean registrarCliente(Persona persona, Usuario usuario) {
		boolean guardado = false;
		try {
			/*
			 * Guardamos primero el objeto que sera padre en este caso persona
			 */
			Persona p = save(persona);
			/* validamos si al guardar devuelve un objeto persona */
			if (p != null) {
				System.out.println("entro");
				/*
				 * Si se guarda correctamente le asignamos a usuario el objeto
				 * persona ya que el usuario depende de persona
				 */
				usuario.setPersona(p);
				Usuario u = save(usuario);
				/*
				 * Si usuario es diferente de null entonces el proceso guardo
				 * correctamente 2 objetos relacionados
				 */
				if (u != null) {
					guardado = true;
				} else {
					guardado = false;
				}
			}
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean editarCliente(Persona persona, Usuario usuario) {
		boolean guardado = false;
		try {
			/*
			 * Guardamos primero el objeto que sera padre en este caso persona
			 */
			Persona p = editar(persona);
			/* validamos si al guardar devuelve un objeto persona */
			if (p != null) {
				System.out.println("entro");
				/*
				 * Si se guarda correctamente le asignamos a usuario el objeto
				 * persona ya que el usuario depende de persona
				 */
				usuario.setPersona(p);
				Usuario u = editar(usuario);
				/*
				 * Si usuario es diferente de null entonces el proceso guardo
				 * correctamente 2 objetos relacionados
				 */
				if (u != null) {
					guardado = true;
				} else {
					guardado = false;
				}
			}
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guardado;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean editarUsuario(Usuario usuario) {
		boolean guardado = false;
		try {
			if (editar(usuario) != null) {
				guardado = true;
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			guardado = false;
		}
		return guardado;
	}

	@Transactional
	public void delete(Usuario usuario) {
		Usuario r = get(usuario.getId());
		if (r != null) {
			em.remove(r);
		}
	}

	@Transactional(readOnly = true)
	public Usuario getUsuario(Long id) {
		Usuario usuario = new Usuario();
		try {
			Query query = em.createQuery("SELECT t FROM Usuario t where t.id=?1 ");
			query.setParameter(1, id);

			usuario = (Usuario) query.getSingleResult();

			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

}
