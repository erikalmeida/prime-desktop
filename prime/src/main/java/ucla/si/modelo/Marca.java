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
public class Marca implements Serializable, Cloneable {

	private Long id;
	private String nombre;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	@OneToMany
	private List<Modelo> modelos = new ArrayList<Modelo>();
	@OneToMany
	private List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

	public Marca() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Marca(String nombre, String estatus, Date fechaCreacion, Date fechaModificacion) {
		super();
		this.nombre = nombre;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

	@Id
	@SequenceGenerator(name= "pk_sequence_marca", sequenceName="marca_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_marca")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	@OneToMany(/*targetEntity = Modelo.class,*/ mappedBy = "marca", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Modelo> getModelo() {
		return modelos;
	}

	public void setModelo(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	@OneToMany(/*targetEntity = Modelo.class,*/ mappedBy = "marca", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Vehiculo> getVehiculo() {
		return vehiculos;
	}

	public void setVehiculo(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
	
	
	
	

	// TODO: REVISAR LAS RELACIONES Y ATRIBUTOS

	/*
	 * @OneToMany(targetEntity=Modelo.class, mappedBy="marca",
	 * cascade=CascadeType.ALL, fetch=FetchType.LAZY) public List<Modelo>
	 * getModelo() { return modelo; }
	 */

}
