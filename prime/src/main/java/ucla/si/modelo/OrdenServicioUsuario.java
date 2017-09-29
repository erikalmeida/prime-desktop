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
public class OrdenServicioUsuario implements Serializable, Cloneable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private OrdenServicio ordenServicio;
	private Usuario usuario;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	
	public OrdenServicioUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdenServicioUsuario(String estatus, Date fechaCreacion, Date fechaModificacion) {
		super();
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

	@Id
	@SequenceGenerator(name = "pk_sequence_ordenserviciousuario", sequenceName = "ordenserviciousuario_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence_ordenserviciousuario")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(targetEntity = OrdenServicio.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idOrdenServicio", nullable = true)
	public OrdenServicio getOrdenServicio() {
		return ordenServicio;
	}

	public void setOrdenServicio(OrdenServicio ordenServicio) {
		this.ordenServicio = ordenServicio;
	}

	@ManyToOne(targetEntity = Usuario.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idUsuario", nullable = true)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
