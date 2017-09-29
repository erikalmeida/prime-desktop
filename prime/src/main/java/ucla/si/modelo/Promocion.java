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

@Entity
public class Promocion implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String titulo;
	private String descripcion;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Date fechaVigenciaInicio;
	private Date fechaVigenciaFin;
	private String imagen;
	private double descuento;
	private String estado;
	@ManyToOne
	@JoinColumn(name="idServicio")
	private Servicio servicio;

	@OneToMany
	private List<Notificacion> notificaciones = new ArrayList<Notificacion>();
	@OneToMany
	private List<Cita> citas = new ArrayList<Cita>();

	public Promocion() {
		super();
	}

	public Promocion(String titulo, String descripcion, String estatus, Date fechaVigenciaInicio, Date fechaVigenciaFin,
			String imagen, double descuento, String estado, Servicio servicio) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.fechaCreacion = new Date();
		this.fechaModificacion = null;
		this.fechaVigenciaInicio = fechaVigenciaInicio;
		this.fechaVigenciaFin = fechaVigenciaFin;
		this.imagen = imagen;
		this.descuento = descuento;
		this.estado = estado;
		this.servicio = servicio;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Id
	@SequenceGenerator(name = "pk_sequence_promocion", sequenceName = "promocion_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence_promocion")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(unique = true, nullable = false)
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Column(nullable = false)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	@OneToMany(/* targetEntity=Clase.class, */ mappedBy = "promocion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Cita> getCitas() {
		return citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	@Column(nullable = true)
	public Date getFechaVigenciaInicio() {
		return fechaVigenciaInicio;
	}

	public void setFechaVigenciaInicio(Date fechaVigenciaInicio) {
		this.fechaVigenciaInicio = fechaVigenciaInicio;
	}

	@Column(nullable = true)
	public Date getFechaVigenciaFin() {
		return fechaVigenciaFin;
	}

	public void setFechaVigenciaFin(Date fechaVigenciaFin) {
		this.fechaVigenciaFin = fechaVigenciaFin;
	}

	@ManyToOne(targetEntity = Servicio.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idServicio", nullable = false)
	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	@OneToMany(/* targetEntity=cioHerramienta.class, */ mappedBy = "promocion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Notificacion> getNotificacion() {
		return notificaciones;
	}

	public void setNotificacion(List<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}
}
