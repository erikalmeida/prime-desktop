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

import ucla.si.modelo.Marca;

@Repository
public class MarcaDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public Marca incluir(Marca marca) {
		em.persist(marca);
		em.flush();
		return marca;
	}
	
	@Transactional
	public Marca editar(Marca marca) {
		marca.setFechaModificacion(new Date());
		em.merge(marca);
		return marca;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Marca> listartiposMarcas() {
		List<Marca> marcas = new ArrayList<Marca>();
		try {
			Query query = em.createQuery("SELECT g FROM Marca g order by id");
			marcas = (List<Marca>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marcas;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirMarca(Marca marca){
		boolean guardado = false;
		try {
			if(incluir(marca) != null){
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
	public boolean editarMarca(Marca marca){
		boolean guardado = false;
		try {
			if(editar(marca) != null){
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
