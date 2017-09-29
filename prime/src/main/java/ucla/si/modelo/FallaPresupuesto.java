package ucla.si.modelo;

import java.io.Serializable;
import java.util.Date;

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
public class FallaPresupuesto implements Serializable, Cloneable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Presupuesto presupuesto;
	private Falla falla;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	
	public FallaPresupuesto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FallaPresupuesto(String estatus, Date fechaCreacion, Date fechaModificacion) {
		super();
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

	@Id
	@SequenceGenerator(name= "pk_sequence_fallapresupuesto", sequenceName="fallapresupuesto_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_fallapresupuesto")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(targetEntity = Presupuesto.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idPresupuesto", nullable = true)
	public Presupuesto getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}

	@ManyToOne(targetEntity = Falla.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "idFalla", nullable = true)
	public Falla getFalla() {
		return falla;
	}

	public void setFalla(Falla falla) {
		this.falla = falla;
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
	
	
	
	

}
