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

@Entity
public class VehiculoAccesorio {
	
	
	private Long id;
//	private String descripcion;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Vehiculo vehiculo;
	private Accesorio accesorio;
	
	
	public VehiculoAccesorio() {
		super();
		// TODO Auto-generated constructor stub
	}


	public VehiculoAccesorio(String estatus,
			Date fechaCreacion, Date fechaModificacion) {
		super();
	//	this.descripcion = descripcion;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}


	@Id
	@SequenceGenerator(name= "pk_sequence_vehiculoAccesorio", sequenceName="vehiculoAccesorio_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_vehiculoAccesorio")
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
/*

	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
*/

	public String getEstatus() {
		return estatus;
	}


	public void setEstatus(String estatus) {
		this.estatus = estatus;
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

	

	@ManyToOne(targetEntity = Vehiculo.class, fetch = FetchType.LAZY)
	@JoinColumn(name="idVehiculo")
	public Vehiculo getVehiculo() {
		return vehiculo;
	}


	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}


	@ManyToOne(targetEntity = Accesorio.class, fetch = FetchType.LAZY)
	@JoinColumn(name="idAccesorio")
	public Accesorio getAccesorio() {
		return accesorio;
	}


	public void setAccesorio(Accesorio accesorio) {
		this.accesorio = accesorio;
	}
	
	
	
	
	

}
