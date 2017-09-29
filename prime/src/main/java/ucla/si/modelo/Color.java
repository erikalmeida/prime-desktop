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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Color implements Serializable, Cloneable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String descripcion;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
	
	public Color(){
		super();
	}
	
    public Color(String descripcion, String estatus, Date fechaCreacion,
			Date fechaModificacion) {
		super();
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

    @Id
	@SequenceGenerator(name= "pk_sequence_color", sequenceName="color_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_color")
	public Long getId() {
    	return id;
	}
    
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable = false, unique = true)
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public Date getfechaModificacion() {
		return fechaModificacion;
	}
	
	public void setfechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	@OneToMany(/*targetEntity=Vehiculo.class,*/ mappedBy="color", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<Vehiculo> getVehiculo() {
		return vehiculos;
	}
	
	public void setVehiculo(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}	
	
}
