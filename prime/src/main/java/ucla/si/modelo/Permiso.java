package ucla.si.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Permiso implements Serializable, Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private boolean acceso;
	private String estatus;
	private Date fechaCreaccion;
	private Date fechaModificacion;
	private Accion accion;
	private Funcion funcion;
	private Grupo grupo;
	
	public Permiso(){
		super();
	}
	
	public Permiso(boolean acceso, String estatus, Date fechaCreaccion, Date fechaModificacion) {
		super();
		this.acceso = acceso;
		this.estatus = estatus;
		this.fechaCreaccion = fechaCreaccion;
		this.fechaModificacion = fechaModificacion;
	}

	@Id
	@SequenceGenerator(name= "pk_sequence_permiso", sequenceName="permiso_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_permiso")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public boolean isAcceso() {
		return acceso;
	}

	public void setAcceso(boolean acceso) {
		this.acceso = acceso;
	}

	@Column(nullable = false)
	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	@Column(nullable = false)
	public Date getFechaCreaccion() {
		return fechaCreaccion;
	}

	public void setFechaCreaccion(Date fechaCreaccion) {
		this.fechaCreaccion = fechaCreaccion;
	}

	@Column(nullable = true)
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	@ManyToOne(targetEntity = Accion.class, fetch = FetchType.LAZY)
	@JoinColumn(name="idAccion", nullable = false)
	public Accion getAccion() {
		return accion;
	}

	public void setAccion(Accion accion) {
		this.accion = accion;
	}

	@ManyToOne(targetEntity = Funcion.class, fetch = FetchType.LAZY)
	@JoinColumn(name="idFuncion", nullable = false)
	public Funcion getFuncion() {
		return funcion;
	}

	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

	@ManyToOne(targetEntity = Grupo.class, fetch = FetchType.LAZY)
	@JoinColumn(name="idGrupo", nullable = false)
	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
}
