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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class OrdenEntrega implements Serializable, Cloneable{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private	String descripcion;
	private String estatus;
	private String estado;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Calificacion calificacion;
	
	@OneToOne
	@JoinColumn(name = "idGarantia")
	private Garantia garantia;
	@OneToOne
	@JoinColumn(name = "idPrueba")
	private Prueba prueba;
	
	private List<Reclamo> reclamos = new ArrayList<Reclamo>();
	@OneToOne
	private Notificacion notificacion;
	
	public OrdenEntrega() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdenEntrega(String descripcion, String estatus, String estado,
			Date fechaCreacion, Date fechaModificacion) {
		super();
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}



	@Id
	@SequenceGenerator(name= "pk_sequence_ordenEntrega", sequenceName="ordenEntrega_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_ordenEntrega")
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
	public String getDescripcion(){
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	
	@OneToMany(/*targetEntity=Reclamo.class,*/ mappedBy="ordenEntrega", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<Reclamo> getReclamos() {
		return reclamos;
	}

	public void setReclamos(List<Reclamo> reclamos) {
		this.reclamos = reclamos;
	}
	
	@OneToOne(targetEntity = Garantia.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idGarantia", nullable = false)
	public Garantia getGarantia() {
		return garantia;
	}

	public void setGarantia(Garantia garantia) {
		this.garantia = garantia;
	}

	@OneToOne(targetEntity = Prueba.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idPrueba", nullable = false)
	public Prueba getPrueba() {
		return prueba;
	}

	public void setPrueba(Prueba prueba) {
		this.prueba = prueba;
	}

	@OneToOne(mappedBy = "ordenEntrega", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Calificacion getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Calificacion calificacion) {
		this.calificacion = calificacion;
	}


	@Column(nullable = false)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@OneToOne(mappedBy = "ordenEntrega", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Notificacion getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(Notificacion notificacion) {
		this.notificacion = notificacion;
	}
	
	


	
}
