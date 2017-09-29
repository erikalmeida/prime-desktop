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
public class Espacio implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String descripcion;
	private boolean estado; // TRUE = OCUPADO & FALSE = DISPONIBLE

	private Agenda agenda;
	private String estatus;
	private Date fechaCreacion;
	private String identificacion;
	private Date fechaModificacion;
	private List<EspacioHerramienta> espacioHerramientas = new ArrayList<EspacioHerramienta>();
	private List<EspacioTecnologia> espacioTecnologias = new ArrayList<EspacioTecnologia>();
	private List<EspacioMecanico> espacioMecanicos = new ArrayList<EspacioMecanico>();
	
	private List<Cita> citas = new ArrayList<Cita>();

	public Espacio() {
		super();
	}

	public Espacio(String identificacion,String descripcion, boolean estado, String estatus, Date fechaCreacion, Date fechaModificacion) {
		super();
		this.identificacion=identificacion;
		this.descripcion = descripcion;
		this.estado = estado;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

	@Id
	@SequenceGenerator(name = "pk_sequence_espacio", sequenceName = "espacio_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence_espacio")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(nullable = false)
	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Column(nullable = false)
	public String getEstatus() {
		return estatus;
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

	@ManyToOne(targetEntity = Agenda.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idAgenda", nullable = true)
	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	@OneToMany(/* targetEntity=cioHerramienta.class, */ mappedBy = "espacio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Cita> getCitas() {
		return citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}
	
	@Column(nullable = true)
	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	
	@OneToMany(/* targetEntity=cioHerramienta.class, */ mappedBy = "espacio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<EspacioHerramienta> getEspacioHerramienta() {
		return espacioHerramientas;
	}

	public void setEspacioHerramienta(List<EspacioHerramienta> espacioHerramientas) {
		this.espacioHerramientas = espacioHerramientas;
	}
	
	@OneToMany(/* targetEntity=cioHerramienta.class, */ mappedBy = "espacio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<EspacioTecnologia> getEspacioTecnologia() {
		return espacioTecnologias;
	}

	public void setEspacioTecnologia(List<EspacioTecnologia> espacioTecnologias) {
		this.espacioTecnologias = espacioTecnologias;
	}
	
	@OneToMany(/* targetEntity=cioHerramienta.class, */ mappedBy = "espacio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<EspacioMecanico> getEspacioMecanico() {
		return espacioMecanicos;
	}

	public void setEspacioMecanico (List<EspacioMecanico> espacioMecanicos) {
		this.espacioMecanicos = espacioMecanicos;
	}

}
