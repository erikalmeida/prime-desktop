package ucla.si.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
public class Ciudad implements Serializable, Cloneable {
	
	
	private Long id;
	private String descripcion;
	
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String estatus;
	private Estado estado;
	
	
	public Ciudad() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Ciudad(String descripcion, Date fechaCreacion,
			Date fechaModificacion, String estatus) {
		super();
		this.descripcion = descripcion;
		
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.estatus = estatus;
	}

	
	@Id
	@SequenceGenerator(name= "pk_sequence_ciudad", sequenceName="ciudad_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_ciudad")
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

	@ManyToOne(targetEntity = Estado.class, fetch = FetchType.EAGER)
	@JoinColumn(name="idEstado", nullable = false)
	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}

    @Column(nullable= false)
	public String getEstatus() {
		return estatus;
	}


	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
	
	
	
	
	
	
	
	
	

}