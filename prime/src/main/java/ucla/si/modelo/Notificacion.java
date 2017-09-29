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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Notificacion implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String descripcion;
	private Date fechacreacion;
	private String estatus;
	@ManyToOne
	private Promocion promocion;
	@ManyToOne
	private TipoNotificacion tiponotificacion;
	@ManyToOne
	private Eventualidad eventualidad;
	@OneToOne
	private Presupuesto presupuesto;
	@OneToMany
	private List<NotificacionUsuario> notificacionUsuarios = new ArrayList<NotificacionUsuario>();
	
	@OneToOne
	@JoinColumn(name = "idOrdenEntrega")
	private OrdenEntrega ordenEntrega;
	
	public Notificacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notificacion(String nombre, String descripcion, Date fechacreacion, String estatus) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechacreacion = fechacreacion;
		this.estatus = estatus;
	}

	@Id
	@SequenceGenerator(name = "pk_sequence_notificacion", sequenceName = "notificacion_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence_notificacion")
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

	@Column(nullable = true)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(nullable = false)
	public Date getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	@Column(nullable = false)
	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	@ManyToOne(targetEntity = Promocion.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idPromocion", nullable = true)
	public Promocion getPromocion() {
		return promocion;
	}

	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
	}

	@ManyToOne(targetEntity = TipoNotificacion.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idTipoNotificacion", nullable = true)
	public TipoNotificacion getTiponotificacion() {
		return tiponotificacion;
	}

	public void setTiponotificacion(TipoNotificacion tiponotificacion) {
		this.tiponotificacion = tiponotificacion;
	}

	@ManyToOne(targetEntity = Eventualidad.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idEventualidad", nullable = true)
	public Eventualidad getEventualidad() {
		return eventualidad;
	}

	public void setEventualidad(Eventualidad eventualidad) {
		this.eventualidad = eventualidad;
	}

	@OneToOne(targetEntity = Presupuesto.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idPresupuesto", nullable = true)
	public Presupuesto getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}

	@OneToMany(/* targetEntity=cioHerramienta.class, */ mappedBy = "notificacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<NotificacionUsuario> getNotificacionUsuario() {
		return notificacionUsuarios;
	}

	public void setNotificacionUsuario(List<NotificacionUsuario> notificacionUsuarios) {
		this.notificacionUsuarios = notificacionUsuarios;
	}

	@OneToOne(targetEntity = OrdenEntrega.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idOrdenEntrega", nullable = true)
	public OrdenEntrega getOrdenEntrega() {
		return ordenEntrega;
	}

	public void setOrdenEntrega(OrdenEntrega ordenEntrega) {
		this.ordenEntrega = ordenEntrega;
	}
	
	
	

}