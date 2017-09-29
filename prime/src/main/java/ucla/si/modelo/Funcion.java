package ucla.si.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
public class Funcion implements Serializable, Cloneable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String clave;
	private String nombre;
	private String descripcion;
	private String icono;
	private String url;
	private Funcion funcionPadre;
	private Sistema sistema;
	private List<Permiso> permisos = new ArrayList<Permiso>();
	private List<Funcion> funcionHijos = new ArrayList<Funcion>();
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;

	public Funcion() {
		super();
	}

	public Funcion(String clave, String nombre, String descripcion, String icono, String url, String estatus, Date fechaCreacion,
			Date fechaModificacion) {
		super();
		this.clave = clave;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.icono = icono;
		this.url = url;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

	@Id
	@SequenceGenerator(name= "pk_sequence_funcion", sequenceName="funcion_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_funcion")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable = false, unique = true)
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Column(nullable = false)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(nullable = false)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Column(nullable = true)
	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}
	
	@Column(nullable = true)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}	

	@ManyToOne(targetEntity = Funcion.class, fetch = FetchType.LAZY)
	@JoinColumn(name="idFuncionPadre", nullable = true)
	public Funcion getFuncionPadre() {
		return funcionPadre;
	}

	public void setFuncionPadre(Funcion funcionPadre) {
		this.funcionPadre = funcionPadre;
	}

	@ManyToOne(targetEntity = Sistema.class, fetch = FetchType.LAZY)
	@JoinColumn(name="idSistema", nullable = false)
	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}
    
	@OneToMany(mappedBy="funcion", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<Permiso> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}

	@Transient
	public List<Funcion> getFuncionHijos() {
		return funcionHijos;
	}

	public void setFuncionHijos(List<Funcion> funcionHijos) {
		this.funcionHijos = funcionHijos;
	}

	@Column(nullable = false)
	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	@Column(nullable = true)
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Column(nullable = true)
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	

}
