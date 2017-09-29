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

public class RectificacionMotor {
	
	
	private Long id;
	private String descripcion;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String estatus;
	private Rectificacion rectificacion;
	private Motor motor;
	
	
	
	
	public RectificacionMotor() {
		super();
		// TODO Auto-generated constructor stub
	}




	public RectificacionMotor(String descripcion, Date fechaCreacion,
			Date fechaModificacion, String estatus) {
		super();
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.estatus = estatus;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getDescripcion() {
		return descripcion;
	}




	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}




	public Date getFechaCreacion() {
		return fechaCreacion;
	}




	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}




	public Date getFechaModificacion() {
		return fechaModificacion;
	}




	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}




	public String getEstatus() {
		return estatus;
	}




	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}



	@ManyToOne(targetEntity = Rectificacion.class, fetch = FetchType.LAZY)
	@JoinColumn(name="idRectificacion", nullable = false)
	public Rectificacion getRectificacion() {
		return rectificacion;
	}




	public void setRectificacion(Rectificacion rectificacion) {
		this.rectificacion = rectificacion;
	}



	@ManyToOne(targetEntity = Motor.class, fetch = FetchType.LAZY)
	@JoinColumn(name="idMotor", nullable = false)
	public Motor getMotor() {
		return motor;
	}




	public void setMotor(Motor motor) {
		this.motor = motor;
	}
	
	
	
	
	
	
	
	
	

}
