package ucla.si.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Persona implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;
	private String apellido;
	private String cedula;
	private String urlFoto;
	private String sexo;
	private String direccion;
	private String telefono;
	private Date fechaNacimiento;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	@ManyToOne
	private Deporte deporte;
	@ManyToOne
	private Ocupacion ocupacion;
	@ManyToOne
	private Pasatiempo pasatiempo;
	@ManyToOne
	private Profesion profesion;

	private Usuario usuario;

	public Persona() {
		super();
	}

	public Persona(String nombre, String apellido, String cedula, String urlFoto, String sexo, String direccion,
			String telefono, Date fechaNacimiento, String estatus, Date fechaCreacion, Date fechaModificacion) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.urlFoto = urlFoto;
		this.sexo = sexo;
		this.direccion = direccion;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

	@Id
	@SequenceGenerator(name = "pk_sequence_persona", sequenceName = "persona_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence_persona")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(nullable = false)
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Column(unique = true, nullable = false)
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	@Column(nullable = true)
	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	@Column(nullable = true)
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Column(nullable = true)
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Column(nullable = true)
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(nullable = true)
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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

	@OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@ManyToOne(targetEntity = Deporte.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idDeporte", nullable = true)
	public Deporte getDeporte() {
		return deporte;
	}

	public void setDeporte(Deporte deporte) {
		this.deporte = deporte;
	}

	@ManyToOne(targetEntity = Ocupacion.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idOcupacion", nullable = true)
	public Ocupacion getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(Ocupacion ocupacion) {
		this.ocupacion = ocupacion;
	}

	@ManyToOne(targetEntity = Pasatiempo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idPasatiempo", nullable = true)
	public Pasatiempo getPasatiempo() {
		return pasatiempo;
	}

	public void setPasatiempo(Pasatiempo pasatiempo) {
		this.pasatiempo = pasatiempo;
	}

	@ManyToOne(targetEntity = Profesion.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idProfesion", nullable = true)
	public Profesion getProfesion() {
		return profesion;
	}

	public void setProfesion(Profesion profesion) {
		this.profesion = profesion;
	}

}
