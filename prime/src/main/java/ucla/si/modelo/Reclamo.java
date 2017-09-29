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
public class Reclamo implements Serializable, Cloneable{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private	String descripcion;
	private String estado;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	
	private TipoReclamo tipoReclamo;
	
	private Motivo motivo;
	
	private OrdenEntrega ordenEntrega;
	
	public Reclamo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reclamo(String descripcion, String estado, String estatus, Date fechaCreacion, Date fechaModificacion) {
		super();
		this.descripcion = descripcion;
		this.estado = estado;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}
	
	
	@Id
	@SequenceGenerator(name= "pk_sequence_reclamo", sequenceName="reclamo_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_reclamo")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable = false)
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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
	
	/*
	 * 
	 */
	
	@ManyToOne(targetEntity = TipoReclamo.class, fetch = FetchType.LAZY)
	@JoinColumn(name="idTipoReclamo", nullable = false)
	public TipoReclamo getTipoReclamo() {
		return tipoReclamo;
	}

	public void setTipoReclamo(TipoReclamo tipoReclamo) {
		this.tipoReclamo = tipoReclamo;
	}
	
	/*
	 * 
	 */
	
	@ManyToOne(targetEntity = Motivo.class, fetch = FetchType.LAZY)
	@JoinColumn(name="idMotivo", nullable = true)
	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}
	
	/*
	 * 
	 */
	
	@ManyToOne(targetEntity = OrdenEntrega.class, fetch = FetchType.LAZY)
	@JoinColumn(name="idOrdenEntrega", nullable = true)
	public OrdenEntrega getOrdenEntrega() {
		return ordenEntrega;
	}

	public void setOrdenEntrega(OrdenEntrega ordenEntrega) {
		this.ordenEntrega = ordenEntrega;
	}

}
