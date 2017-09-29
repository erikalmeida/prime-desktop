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
public class Calificacion implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String comentario;
	private Date fecha;
	private int calificacionServicio;
	private int calificacionInstalacion;
	private int calificacionAtencion;
	private String estatus;

	@OneToOne
	@JoinColumn(name = "idOrdenEntrega")
	private OrdenEntrega ordenEntrega;

	public Calificacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Calificacion(String comentario, Date fecha, int calificacionServicio, int calificacionInstalacion,
			int calificacionAtencion, String estatus) {
		super();
		this.comentario = comentario;
		this.fecha = fecha;
		this.calificacionServicio = calificacionServicio;
		this.calificacionInstalacion = calificacionInstalacion;
		this.calificacionAtencion = calificacionAtencion;
		this.estatus = estatus;
	}

	@Id
	@SequenceGenerator(name = "pk_sequence_calificacion", sequenceName = "calificacion_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence_calificacion")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = true)
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Column(nullable = true)
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(nullable = false)
	public int getCalificacionServicio() {
		return calificacionServicio;
	}

	public void setCalificacionServicio(int calificacionServicio) {
		this.calificacionServicio = calificacionServicio;
	}

	@Column(nullable = false)
	public int getCalificacionInstalacion() {
		return calificacionInstalacion;
	}

	public void setCalificacionInstalacion(int calificacionInstalacion) {
		this.calificacionInstalacion = calificacionInstalacion;
	}

	@Column(nullable = false)
	public int getCalificacionAtencion() {
		return calificacionAtencion;
	}

	public void setCalificacionAtencion(int calificacionAtencion) {
		this.calificacionAtencion = calificacionAtencion;
	}

	@Column(nullable = true)
	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	@OneToOne(targetEntity = OrdenEntrega.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idOrdenEntrega", nullable = false)
	public OrdenEntrega getOrdenEntrega() {
		return ordenEntrega;
	}

	public void setOrdenEntrega(OrdenEntrega ordenEntrega) {
		this.ordenEntrega = ordenEntrega;
	}

}
