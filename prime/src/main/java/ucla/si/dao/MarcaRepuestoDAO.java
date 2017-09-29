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

import ucla.si.modelo.MarcaRepuesto;

@Repository
public class MarcaRepuestoDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
	public MarcaRepuesto incluir(MarcaRepuesto marcaRepuesto) {
		em.persist(marcaRepuesto);
		em.flush();
		return marcaRepuesto;
	}
	
	@Transactional
	public MarcaRepuesto editar(MarcaRepuesto marcaRepuesto) {
		marcaRepuesto.setFechaModificacion(new Date());
		em.merge(marcaRepuesto);
		return marcaRepuesto;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<MarcaRepuesto> listarMarcaRepuestos() {
		List<MarcaRepuesto> marcaRepuestos = new ArrayList<MarcaRepuesto>();
		try {
			Query query = em.createQuery("SELECT g FROM MarcaRepuesto g order by id");
			marcaRepuestos = (List<MarcaRepuesto>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marcaRepuestos;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<MarcaRepuesto> buscarMarcaRepuestos(String descripcion) {
		List<MarcaRepuesto> marcaRepuestos = new ArrayList<MarcaRepuesto>();
		try {
			System.out.println("em " + em);
			System.out.println(descripcion);
			String hql = "SELECT mr FROM MarcaRepuesto";
			hql += String.format(" mr where  upper(mr.descripcion) like'%%%s%%'", descripcion.toUpperCase());
			Query query = em.createQuery(hql);
			marcaRepuestos = (List<MarcaRepuesto>) query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marcaRepuestos;
	}
	

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean incluirMarcaRepuesto(MarcaRepuesto marcaRepuesto){
		boolean guardado = false;
		try {
			if(incluir(marcaRepuesto) != null){
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
	public boolean editarMarcaRepuesto(MarcaRepuesto marcaRepuesto){
		boolean guardado = false;
		try {
			if(editar(marcaRepuesto) != null){
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
