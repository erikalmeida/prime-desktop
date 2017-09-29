package ucla.si.modelo;

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
public class PresupuestoTipoRepuesto {
	
	private Long id;
	@ManyToOne
	private Presupuesto presupuesto;
	@ManyToOne
	private TipoRepuesto tipoRepuesto;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	
	public PresupuestoTipoRepuesto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PresupuestoTipoRepuesto(String estatus, Date fechaCreacion, Date fechaModificacion) {
		super();
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

	@Id
	@SequenceGenerator(name = "pk_sequence_presupuestotiporepuesto", sequenceName = "presupuestotiporepuesto_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence_presupuestotiporepuesto")
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

	@ManyToOne(targetEntity = TipoRepuesto.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idTipoRepuesto", nullable = true)
	public TipoRepuesto getTipoRepuesto() {
		return tipoRepuesto;
	}

	public void setTipoRepuesto(TipoRepuesto tipoRepuesto) {
		this.tipoRepuesto = tipoRepuesto;
	}

	@Column(nullable = true)
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
