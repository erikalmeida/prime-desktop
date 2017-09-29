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

import ucla.si.modelo.Cita;

@Entity
public class Eventualidad implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String descripcion;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String estatus;
	@OneToMany
	private List<Cita> citas = new ArrayList<Cita>();
	@ManyToOne
	private TipoEventualidad tipoeventualidad;
	@OneToMany
	private List<Notificacion> notificaciones = new ArrayList<Notificacion>();

	public Eventualidad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Eventualidad(String descripcion, Date fechaCreacion, Date fechaModificacion, String estatus) {
		super();
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.estatus = estatus;
	}

	@Id
	@SequenceGenerator(name = "pk_sequence_eventualidad", sequenceName = "eventualidad_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence_eventualidad")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getDescripcion() {
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

	@Column(nullable = false)
	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	@OneToMany(/* targetEntity=cioHerramienta.class, */ mappedBy = "eventualidad", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Cita> getCita() {
		return citas;
	}

	public void setCita(List<Cita> cita) {
		this.citas = cita;
	}

	@ManyToOne(targetEntity = TipoEventualidad.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "idTipoEventualidad", nullable = true)
	public TipoEventualidad getTipoeventualidad() {
		return tipoeventualidad;
	}

	public void setTipoeventualidad(TipoEventualidad tipoeventualidad) {
		this.tipoeventualidad = tipoeventualidad;
	}
	
	@OneToMany(/*targetEntity=cioHerramienta.class,*/ mappedBy="eventualidad", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<Notificacion> getNotificacion() {
		return notificaciones;
	}

	public void setNotificacion(List<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}

}
