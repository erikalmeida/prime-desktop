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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;


@Entity
public class Grupo implements Serializable, Cloneable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String descripcion;
	private List<Permiso> permisos = new ArrayList<Permiso>();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;

	public Grupo() {
		super();
	}

	public Grupo(String nombre, String descripcion, String estatus, Date fechaCreacion, Date fechaModificacion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

	@Id
	@SequenceGenerator(name= "pk_sequence_grupo", sequenceName="grupo_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_grupo")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(unique = true, nullable = false)
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

	@OneToMany(mappedBy="grupo", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<Permiso> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}

	@OneToMany(mappedBy="grupo", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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
