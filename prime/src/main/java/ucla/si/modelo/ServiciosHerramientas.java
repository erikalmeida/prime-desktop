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
public class ServiciosHerramientas implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Servicio servicio;
	private Herramienta herramienta;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;

	public ServiciosHerramientas() {
		super();
	}

	public ServiciosHerramientas(String estatus, Date fechaCreacion, Date fechaModificacion) {
		super();
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

	@Id
	@SequenceGenerator(name = "pk_sequence_servicioherramienta", sequenceName = "servicioherramienta_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence_servicioherramienta")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFuncion(Herramienta herramienta) {
		this.herramienta = herramienta;
	}

	@ManyToOne(targetEntity = Servicio.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idServicio", nullable = true)
	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	@ManyToOne(targetEntity = Herramienta.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idHerramienta", nullable = true)
	public Herramienta getHerramienta() {
		return herramienta;
	}

	public void setHerramienta(Herramienta herramienta) {
		this.herramienta = herramienta;
	}

	@Column(nullable = false)
	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
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

}
