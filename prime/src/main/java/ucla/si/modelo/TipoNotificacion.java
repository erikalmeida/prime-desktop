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
public class TipoNotificacion implements Serializable, Cloneable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private Date fechaCreacion;
	private String estatus;
	private Date fechaModificacion;
	private String icono;
	@OneToMany
	private List<Notificacion> notificaciones = new ArrayList<Notificacion>();
	
	public TipoNotificacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public TipoNotificacion(String nombre, Date fechaCreacion, String estatus, String icono) {
		super();
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.estatus = estatus;
		this.icono = icono;
	}



	@Id
	@SequenceGenerator(name= "pk_sequence_tiponotificacion", sequenceName="tiponotificacion_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_tiponotificacion")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(nullable = false)
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Column(nullable = false)
	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	@OneToMany(/*targetEntity=cioHerramienta.class,*/ mappedBy="tiponotificacion", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<Notificacion> getNotificacion() {
		return notificaciones;
	}

	public void setNotificacion(List<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}


	@Column(nullable = false)
	public String getIcono() {
		return icono;
	}



	public void setIcono(String icono) {
		this.icono = icono;
	}


	@Column(nullable = true)
	public Date getFechaModificacion() {
		return fechaModificacion;
	}



	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	
	

}