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
public class Caja implements Serializable, Cloneable {
	
	
	private Long id;
	private String descripcion;
	
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String estatus;
	private TipoCaja tipoCaja;
	private List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
	
	
	public Caja() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Caja(String descripcion, Date fechaCreacion, Date fechaModificacion,
			String estatus) {
		super();
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.estatus = estatus;
	}


	@Id
	@SequenceGenerator(name= "pk_sequence_caja", sequenceName="caja_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_caja")
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

	@Column(nullable = false)
	public String getEstatus() {
		return estatus;
	}


	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

 
	@ManyToOne(targetEntity = TipoCaja.class, fetch = FetchType.EAGER)
	@JoinColumn(name="idTipoCaja", nullable = false)
	public TipoCaja getTipoCaja() {
		return tipoCaja;
	}


	public void setTipoCaja(TipoCaja tipoCaja) {
		this.tipoCaja = tipoCaja;
	}
	
	
	
	@OneToMany(/*targetEntity=Vehiculo.class,*/ mappedBy="caja", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	public List<Vehiculo> getVehiculo() {
		return vehiculos;
	}
	public void setVehiculo(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
	

}
