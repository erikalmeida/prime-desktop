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
public class Sistema implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String descripcion;
	private String icono;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private List<Funcion> funciones = new ArrayList<Funcion>();
	private String urlImagenCabezera;
	private Boolean urlImagenCabezeraVisible;
	private String timelineTwitter;
	private Boolean timelineTwitterVisible;
	private String correo;
	private String mapa;
	private String celular;
	private String telefono;
	private String mision;
	private String vision;
	private String filosofia;
	private String direccion;
	private List<RedSocial> redesSociales = new ArrayList<RedSocial>();

	public Sistema() {
		super();
	}

	public Sistema(String nombre, String descripcion, String icono, String estatus, Date fechaCreacion,
			Date fechaModificacion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.icono = icono;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

	@OneToMany(/* targetEntity=Vehiculo.class, */ mappedBy="idSistema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<RedSocial> getRedesSociales() {
		return redesSociales;
	}

	public void setRedesSociales(List<RedSocial> redesSociales) {
		this.redesSociales = redesSociales;
	}

	@Column(nullable = true)
	public String getUrlImagenCabezera() {
		return urlImagenCabezera;
	}

	public void setUrlImagenCabezera(String urlImagenCabezera) {
		this.urlImagenCabezera = urlImagenCabezera;
	}

	@Column(nullable = true,length=5000)
	public String getTimelineTwitter() {
		return timelineTwitter;
	}

	public void setTimelineTwitter(String timelineTwitter) {
		this.timelineTwitter = timelineTwitter;
	}
	
	@Column(nullable = true)
	public Boolean getUrlImagenCabezeraVisible() {
		return urlImagenCabezeraVisible;
	}

	public void setUrlImagenCabezeraVisible(Boolean urlImagenCabezeraVisible) {
		this.urlImagenCabezeraVisible = urlImagenCabezeraVisible;
	}
	@Column(nullable = true)
	public Boolean getTimelineTwitterVisible() {
		return timelineTwitterVisible;
	}

	public void setTimelineTwitterVisible(Boolean timelineTwitterVisible) {
		this.timelineTwitterVisible = timelineTwitterVisible;
	}
	
	
	@Column(nullable = true)
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(nullable = true)
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Column(nullable = true,length=5000)
	public String getMapa() {
		return mapa;
	}

	public void setMapa(String mapa) {
		this.mapa = mapa;
	}

	@Column(nullable = true)
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Column(nullable = true,length=5000)
	public String getMision() {
		return mision;
	}

	public void setMision(String mision) {
		this.mision = mision;
	}

	@Column(nullable = true,length=5000)
	public String getVision() {
		return vision;
	}

	public void setVision(String vision) {
		this.vision = vision;
	}

	@Column(nullable = true,length=5000)
	public String getFilosofia() {
		return filosofia;
	}

	public void setFilosofia(String filosofia) {
		this.filosofia = filosofia;
	}

	@Column(nullable = true,length=5000)
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Id
	@SequenceGenerator(name = "pk_sequence_sistema", sequenceName = "sistema_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence_sistema")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToMany(/* targetEntity=Funcion.class, */ mappedBy = "sistema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Funcion> getFunciones() {
		return funciones;
	}

	public void setFunciones(List<Funcion> funciones) {
		this.funciones = funciones;
	}

	@Column(unique = true, nullable = false)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(nullable = false)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(nullable = true)
	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
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

}
