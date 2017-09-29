
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
public class Modelo implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Marca marca;
	private List<Vehiculo> vehiculo = new ArrayList<Vehiculo>();

	public Modelo() {
		super();
	}

	public Modelo(String nombre, String estatus, Date fechaCreacion, Date fechaModificacion) {
		super();
		this.nombre = nombre;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

	@Id
	@SequenceGenerator(name = "pk_sequence_modelo", sequenceName = "modelo_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence_modelo")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getEstatus() {
		return estatus;
	}

	@Column(nullable = false, unique = true)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	@Column(nullable = true)
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

	@ManyToOne(targetEntity = Marca.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idMarca", nullable = true)
	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	@OneToMany(/* targetEntity = Modelo.class, */ mappedBy = "modelo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Vehiculo> getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(List<Vehiculo> vehiculo) {
		this.vehiculo = vehiculo;
	}

	// TODO: HAY QUE REALIZAR BIEN LAS RELACIONES Y REVISAR LOS ATRIBUTOS
	/*
	 * @OneToMany(/*targetEntity=Clase.class,
	 *//*
		 * mappedBy="vehiculo", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
		 * public List<Vehiculo> getVehiculo() { return vehiculos; }
		 */

}
