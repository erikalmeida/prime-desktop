package ucla.si.modelo;

import java.io.Serializable;
import java.lang.Long;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Garantia
 *
 */

@Entity
public class Garantia implements Serializable, Cloneable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String descripcion;
	private int tiempoVigencia;
	private String estado;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private TipoGarantia tipoGarantia;
	private OrdenEntrega ordenEntrega;
	
	public Garantia() {
		super();
	} 
	
	public Garantia(String descripcion, int tiempoVigencia, String estado, String estatus, Date fechaCreacion,
			Date fechaModificacion) {
		super();
		this.descripcion = descripcion;
		this.tiempoVigencia = tiempoVigencia;
		this.estado = estado;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}



	@Id
	@SequenceGenerator(name= "pk_sequence_garantia", sequenceName="garantia_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_garantia")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable = false, unique = true)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Column(nullable = false)
	public int getTiempoVigencia() {
		return tiempoVigencia;
	}

	public void setTiempoVigencia(int tiempoVigencia) {
		this.tiempoVigencia = tiempoVigencia;
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
	
	/*
	 * 
	 */

	@ManyToOne(targetEntity = TipoGarantia.class, fetch = FetchType.LAZY)
	@JoinColumn(name="idTipoGarantia", nullable = false)
	public TipoGarantia getTipoGarantia() {
		return tipoGarantia;
	}

	public void setTipoGarantia(TipoGarantia tipoGarantia) {
		this.tipoGarantia = tipoGarantia;
	}

	@OneToOne(mappedBy = "garantia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public OrdenEntrega getOrdenEntrega() {
		return ordenEntrega;
	}

	public void setOrdenEntrega(OrdenEntrega ordenEntrega) {
		this.ordenEntrega = ordenEntrega;
	}
	
	
	
}
