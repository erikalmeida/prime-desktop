
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
public class Cita implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String descripcion;
	private Date fecha;
	private String estado;
	@ManyToOne
	private Promocion promocion;
	private String nombreReferido;
	@ManyToOne
	private Eventualidad eventualidad;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String estatus;
	@ManyToOne
	private Motivo motivo;
	@ManyToOne
	private Vehiculo vehiculo;
	@ManyToOne
	private Servicio servicio;
	private List<Presupuesto> Presupuestos = new ArrayList<Presupuesto>();

	@ManyToOne
	private Espacio espacio;

	public Cita() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cita(String descripcion, String estado, Date fechaCreacion, Date fechaModificacion, String estatus) {
		this.descripcion = descripcion;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.estatus = estatus;
	}

	@Id
	@SequenceGenerator(name = "pk_sequence_cita", sequenceName = "cita_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence_cita")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = true)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(nullable = false)
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@ManyToOne(targetEntity = Eventualidad.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idEventualidad", nullable = true)
	public Eventualidad getEventualidad() {
		return eventualidad;
	}

	public void setEventualidad(Eventualidad eventualidad) {
		this.eventualidad = eventualidad;
	}

	@Column(nullable = false)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	@ManyToOne(targetEntity = Motivo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idMotivo", nullable = true)
	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	@ManyToOne(targetEntity = Vehiculo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idVehiculo", nullable = false)
	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	@Column(nullable = true)
	public String getNombreReferido() {
		return nombreReferido;
	}

	public void setNombreReferido(String nombreReferido) {
		this.nombreReferido = nombreReferido;
	}

	@ManyToOne(targetEntity = Servicio.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idServicio", nullable = true)
	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	@ManyToOne(targetEntity = Promocion.class, fetch = FetchType.LAZY)
	@JoinColumn(name="idPromocion", nullable = true)
	public Promocion getPromocion() {
		return promocion;
	}

	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
	}

	@ManyToOne(targetEntity = Espacio.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idEspacio", nullable = true)
	public Espacio getEspacio() {
		return espacio;
	}

	public void setEspacio(Espacio espacio) {
		this.espacio = espacio;
	}

	@OneToMany(/* targetEntity=cioHerramienta.class, */ mappedBy = "cita", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Presupuesto> getPresupuestos() {
		return Presupuestos;
	}

	public void setPresupuestos(List<Presupuesto> presupuestos) {
		Presupuestos = presupuestos;
	}
	
	

}