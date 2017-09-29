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
public class Servicio implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String titulo;
	private String descripcion;
	private double tarifa;
	private String urlImagen;
	private String estatus;
	private String estado;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private List<Cita> citas = new ArrayList<Cita>();
	@OneToMany
	private List<Promocion> promociones = new ArrayList<Promocion>();
	private List<ServiciosHerramientas> servicioHerramientas = new ArrayList<ServiciosHerramientas>();
	@OneToMany
	private List<VehiculoServicio> vehiculoServicios = new ArrayList<VehiculoServicio>();
	private List<ServiciosTecnologias> servicioTecnologias = new ArrayList<ServiciosTecnologias>();
	private List<ServiciosEtapas> servicioEtapas = new ArrayList<ServiciosEtapas>();
	private List<PresupuestoServicio> presupuestoServicio = new ArrayList<PresupuestoServicio>();
	private ConfiguracionServicio configuracionServicio;

	public void setConfiguracionServicio(ConfiguracionServicio configuracionServicio) {
		this.configuracionServicio = configuracionServicio;
	}

	public Servicio() {
		super();
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	@Id
	@SequenceGenerator(name = "pk_sequence_servicio", sequenceName = "servicio_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence_servicio")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getTarifa() {
		return tarifa;
	}

	public void setTarifa(double tarifa) {
		this.tarifa = tarifa;
	}

	@Column(nullable = true)
	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(nullable = false)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Servicio(String titulo, String descripcion, double tarifa, String urlImagen, String estatus, String estado,
			Date fechaCreacion, Date fechaModificacion) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.tarifa = tarifa;
		this.urlImagen = urlImagen;
		this.estatus = estatus;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
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

	@OneToMany(/* targetEntity=cioHerramienta.class, */ mappedBy = "servicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<ServiciosHerramientas> getServicioHerramientas() {
		return servicioHerramientas;
	}

	public void setServicioHerramientas(List<ServiciosHerramientas> servicioHerramientas) {
		this.servicioHerramientas = servicioHerramientas;
	}

	@OneToMany(/* targetEntity=cioHerramienta.class, */ mappedBy = "servicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<ServiciosTecnologias> getServicioTecnologias() {
		return servicioTecnologias;
	}

	public void setServicioTecnologias(List<ServiciosTecnologias> servicioTecnologias) {
		this.servicioTecnologias = servicioTecnologias;
	}

	@OneToMany(/* targetEntity=cioHerramienta.class, */ mappedBy = "servicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<ServiciosEtapas> getServicioEtapas() {
		return servicioEtapas;
	}

	public void setServicioEtapas(List<ServiciosEtapas> servicioEtapas) {
		this.servicioEtapas = servicioEtapas;
	}

	@OneToMany(/* targetEntity=cioHerramienta.class, */ mappedBy = "servicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Cita> getCitas() {
		return citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	@OneToMany(/* targetEntity=cioHerramienta.class, */ mappedBy = "servicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<PresupuestoServicio> getPresupuestoServicio() {
		return presupuestoServicio;
	}

	public void setPresupuestoServicio(List<PresupuestoServicio> presupuestoServicio) {
		this.presupuestoServicio = presupuestoServicio;
	}

	@OneToMany(/* targetEntity=cioHerramienta.class, */ mappedBy = "servicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Promocion> getPromociones() {
		return promociones;
	}

	public void setPromociones(List<Promocion> promociones) {
		this.promociones = promociones;
	}

	@OneToOne(mappedBy = "servicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public ConfiguracionServicio getConfiguracionServicio() {
		return configuracionServicio;
	}
	
	@OneToMany(/* targetEntity=cioHerramienta.class, */ mappedBy = "servicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<VehiculoServicio> getVehiculoServicio() {
		return vehiculoServicios;
	}

	public void setVehiculoServicio(List<VehiculoServicio> vehiculoServicios) {
		this.vehiculoServicios = vehiculoServicios;
	}

}