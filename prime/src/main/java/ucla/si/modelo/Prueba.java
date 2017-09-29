package ucla.si.modelo;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Prueba {
	
	private Long id;
	private String descripcion;
	private String estado;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String estatus;
	private OrdenServicio ordenServicio;
	private OrdenEntrega ordenEntrega;
	public Prueba() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Prueba(String descripcion, String tipoPrueba, String estado, Date fechaCreacion, Date fechaModificacion,
			String estatus) {
		super();
		this.descripcion = descripcion;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.estatus = estatus;
	}

	@Id
	@SequenceGenerator(name= "pk_sequence_prueba", sequenceName="prueba_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_prueba")
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

	@ManyToOne(targetEntity = OrdenServicio.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idOrdenServicio", nullable = true)
	public OrdenServicio getOrdenServicio() {
		return ordenServicio;
	}

	public void setOrdenServicio(OrdenServicio ordenServicio) {
		this.ordenServicio = ordenServicio;
	}

	@OneToOne(mappedBy = "prueba", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public OrdenEntrega getOrdenEntrega() {
		return ordenEntrega;
	}

	public void setOrdenEntrega(OrdenEntrega ordenEntrega) {
		this.ordenEntrega = ordenEntrega;
	}
	
	
	

}
