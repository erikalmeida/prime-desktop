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
public class Motor implements Serializable, Cloneable {
	
	
	private Long id;
	private String descripcion;
	private String serial;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String estatus;
	private TipoMotor tipoMotor;
	
	
	public Motor() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Motor(String descripcion, String serial, Date fechaCreacion,
			Date fechaModificacion, String estatus) {
		super();
		this.descripcion = descripcion;
		this.serial = serial;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.estatus = estatus;
	}

	
	@Id
	@SequenceGenerator(name= "pk_sequence_motor", sequenceName="motor_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_motor")
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
	public String getSerial() {
		return serial;
	}


	public void setSerial(String serial) {
		this.serial = serial;
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

	@ManyToOne(targetEntity = TipoMotor.class, fetch = FetchType.EAGER)
	@JoinColumn(name="idTipoMotor", nullable = false)
	public TipoMotor getTipoMotor() {
		return tipoMotor;
	}


	public void setTipoMotor(TipoMotor tipoMotor) {
		this.tipoMotor = tipoMotor;
	}

    @Column(nullable= false)
	public String getEstatus() {
		return estatus;
	}


	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
	
	
	
	
	
	
	
	
	

}
