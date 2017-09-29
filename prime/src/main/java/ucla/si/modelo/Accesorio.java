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
public class Accesorio implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String descripcion;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	
	private List<VehiculoAccesorio> vehiculoAccesorio = new ArrayList<VehiculoAccesorio>();
	
	
	public Accesorio() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Accesorio(String descripcion, String estatus, Date fechaCreacion,
			Date fechaModificacion) {
		super();
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}


	@Id
	@SequenceGenerator(name= "pk_sequence_accesorio", sequenceName="accesorio_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_accesorio")
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
	
	
	@OneToMany(/*targetEntity=Clase.class,*/ mappedBy="accesorio", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<VehiculoAccesorio> getVehiculoAccesorio() {
		return vehiculoAccesorio;
	}



	public void setVehiculoAccesorio(List<VehiculoAccesorio> vehiculoAccesorio) {
		this.vehiculoAccesorio = vehiculoAccesorio;
	}


	
	
	

}
