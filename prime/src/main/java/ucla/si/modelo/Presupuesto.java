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
public class Presupuesto implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String descripcion;
	private String estado;
	private float monto_total;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String estatus;
	@ManyToOne
	private Cita cita;
	@OneToMany
	private List<FallaPresupuesto> fallaPresupuesto = new ArrayList<FallaPresupuesto>();
	@OneToMany
	private List<PresupuestoServicio> presupuestoServicio = new ArrayList<PresupuestoServicio>();
	@OneToMany
	private List<PresupuestoTipoRepuesto> presupuestoTipoRepuestos = new ArrayList<PresupuestoTipoRepuesto>();

	//@OneToOne
	OrdenServicio ordenservicio;

	//@OneToOne
	Notificacion notificacion;

	public Presupuesto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Presupuesto(String descripcion, String estado, float monto_total, Date fechaCreacion, Date fechaModificacion,
			String estatus) {
		super();
		this.descripcion = descripcion;
		this.estado = estado;
		this.monto_total = monto_total;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.estatus = estatus;
	}

	@Id
	@SequenceGenerator(name = "pk_sequence_presupuesto", sequenceName = "presupuesto_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence_presupuesto")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = true)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(nullable = true)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(nullable = true)
	public float getMonto_total() {
		return monto_total;
	}

	public void setMonto_total(float monto_total) {
		this.monto_total = monto_total;
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

	@OneToMany(/* targetEntity=cioHerramienta.class, */ mappedBy = "presupuesto", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<FallaPresupuesto> getFallaPresupuesto() {
		return fallaPresupuesto;
	}

	public void setFallaPresupuesto(List<FallaPresupuesto> fallaPresupuesto) {
		this.fallaPresupuesto = fallaPresupuesto;
	}

	

	@ManyToOne(targetEntity = Cita.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idCita", nullable = true)
	public Cita getCita() {
		return cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}

	@OneToOne(mappedBy = "presupuesto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public OrdenServicio getOrdenservicio() {
		return ordenservicio;
	}

	public void setOrdenservicio(OrdenServicio ordenservicio) {
		this.ordenservicio = ordenservicio;
	}

	@OneToMany(/* targetEntity=cioHerramienta.class, */ mappedBy = "presupuesto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<PresupuestoTipoRepuesto> getPresupuestoTipoRepuestos() {
		return presupuestoTipoRepuestos;
	}
	
	
	@OneToMany(/* targetEntity=cioHerramienta.class, */ mappedBy = "presupuesto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<PresupuestoServicio> getPresupuestoServicio() {
		return presupuestoServicio;
	}

	public void setPresupuestoServicio(List<PresupuestoServicio> presupuestoServicio) {
		this.presupuestoServicio = presupuestoServicio;
	}

	public void setPresupuestoTipoRepuestos(List<PresupuestoTipoRepuesto> presupuestoTipoRepuestos) {
		this.presupuestoTipoRepuestos = presupuestoTipoRepuestos;
	}

	@OneToOne(mappedBy = "presupuesto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Notificacion getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(Notificacion notificacion) {
		this.notificacion = notificacion;
	}

}
