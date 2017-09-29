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
public class OrdenServicio implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String descripcion;
	private String estado;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Presupuesto presupuesto;
	private List<OrdenServicioUsuario> ordenServicioUsuario = new ArrayList<OrdenServicioUsuario>();
	private List<Prueba> prueba = new ArrayList<Prueba>();

	public OrdenServicio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdenServicio(String descripcion, String estado, String estatus, Date fechaCreacion,
			Date fechaModificacion) {
		super();
		this.descripcion = descripcion;
		this.estado = estado;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

	@Id
	@SequenceGenerator(name = "pk_sequence_ordenservicio", sequenceName = "ordenservicio_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence_ordenservicio")
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

	@OneToOne(targetEntity = Presupuesto.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idPresupuesto", nullable = false)
	public Presupuesto getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}

	@OneToMany(/* targetEntity=UsuarioHabilidad.class, */ mappedBy = "ordenServicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<OrdenServicioUsuario> getOrdenServicioUsuario() {
		return ordenServicioUsuario;
	}

	public void setOrdenServicioUsuario(List<OrdenServicioUsuario> ordenServicioUsuario) {
		this.ordenServicioUsuario = ordenServicioUsuario;
	}

	@Column(nullable = false)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@OneToMany(/* targetEntity=cioHerramienta.class, */ mappedBy = "ordenServicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Prueba> getPrueba() {
		return prueba;
	}

	public void setPrueba(List<Prueba> prueba) {
		this.prueba = prueba;
	}

}
