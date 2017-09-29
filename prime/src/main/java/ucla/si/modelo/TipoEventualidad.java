package ucla.si.modelo;

import java.io.Serializable;
import java.lang.Long;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
public class TipoEventualidad implements Serializable, Cloneable{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private	String descripcion;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	
	private List<Eventualidad> eventualidad = new ArrayList<Eventualidad>();

	public TipoEventualidad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoEventualidad(String descripcion, String estatus, Date fechaCreacion, Date fechaModificacion) {
		super();
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}
	
	@Id
	@SequenceGenerator(name= "pk_sequence_tipoEventualidad", sequenceName="tipoEventualidad_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_tipoEventualidad")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable = false)
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
	@Column(nullable = false, unique = true)
	public String getDescripcion(){
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Column(nullable = false)
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

	@OneToMany(/*targetEntity=CitaTipoEventualidad.class,*/ mappedBy="tipoeventualidad", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<Eventualidad> getEventualidad() {
		return eventualidad;
	}

	public void setEventualidad(List<Eventualidad> eventualidad) {
		this.eventualidad = eventualidad;
	}

	

	   
}
