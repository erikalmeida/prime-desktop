package ucla.si.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: TipoGarantia
 *
 */
@Entity
public class TipoGarantia implements Serializable, Cloneable{

	
	private static final long serialVersionUID = 1L;
	private Long id;
	private	String nombre;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	
	private List<Garantia> garantias = new ArrayList<Garantia>();

	public TipoGarantia() {
		super();
	}

	public TipoGarantia(String nombre, String estatus, Date fechaCreacion, Date fechaModificacion) {
		super();
		this.nombre = nombre;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}
	
	@Id
	@SequenceGenerator(name= "pk_sequence_tipoGarantia", sequenceName="tipoGarantia_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_tipoGarantia")
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
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
	@Column(nullable = false, unique = true)
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	/*
	 * 
	 */
	
	@OneToMany(/*targetEntity=Garantia.class,*/ mappedBy="tipoGarantia", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<Garantia> getGarantias() {
		return garantias;
	}

	public void setGarantias(List<Garantia> garantias) {
		this.garantias = garantias;
	}
	
}
