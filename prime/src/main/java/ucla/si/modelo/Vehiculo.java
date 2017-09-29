package ucla.si.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

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
public class Vehiculo implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String placa;
	private Integer anno;
	private Integer nroPuestos;
	private String serialCarroceria;
	private String serialMotor;
	private Float kilometraje;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Usuario usuario;
	private Color color;
	private Uso uso;
	private Modelo modelo;
	private Refrigerante refrigerante;
	private Clase clase;
	private List<Cita> citas = new ArrayList<Cita>();
	@OneToMany
	private List<VehiculoServicio> vehiculoServicios = new ArrayList<VehiculoServicio>();


	private Combustible combustible;
	private Caja caja;
	private Marca marca;

	private TipoAceite tipoAceite;
	private GrosorAceite grosorAceite;

	private List<VehiculoAccesorio> vehiculoAccesorio = new ArrayList<VehiculoAccesorio>();

	public Vehiculo() {
		super();
	}

	public Vehiculo(String placa, Integer anno, Integer nroPuestos, String serialCarroceria, String serialMotor,
			Float kilometraje, String estatus, Date fechaCreacion, Date fechaModificacion) {
		super();
		this.placa = placa;
		this.anno = anno;
		this.nroPuestos = nroPuestos;
		this.serialCarroceria = serialCarroceria;
		this.serialMotor = serialMotor;
		this.kilometraje = kilometraje;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

	@Id
	@SequenceGenerator(name = "pk_sequence_vehiculo", sequenceName = "vehiculo_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence_vehiculo")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(unique = true, nullable = false)
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	@Column(nullable = true)
	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	@Column(nullable = true)
	public Integer getNroPuestos() {
		return nroPuestos;
	}

	public void setNroPuestos(Integer nroPuestos) {
		this.nroPuestos = nroPuestos;
	}

	@Column(nullable = true)
	public String getSerialCarroceria() {
		return serialCarroceria;
	}

	public void setSerialCarroceria(String serialCarroceria) {
		this.serialCarroceria = serialCarroceria;
	}

	@Column(nullable = true)
	public String getSerialMotor() {
		return serialMotor;
	}

	public void setSerialMotor(String serialMotor) {
		this.serialMotor = serialMotor;
	}

	@Column(nullable = true)
	public Float getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(Float kilometraje) {
		this.kilometraje = kilometraje;
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

	@ManyToOne(targetEntity = Usuario.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idUsuario", nullable = false)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@ManyToOne(targetEntity = Color.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idColor", nullable=true)
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@ManyToOne(targetEntity = Uso.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idUso", nullable=true)
	public Uso getUso() {
		return uso;
	}

	public void setUso(Uso uso) {
		this.uso = uso;
	}

	@ManyToOne(targetEntity = Clase.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idClase", nullable = true)
	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	@OneToMany(/* targetEntity=Clase.class, */ mappedBy = "vehiculo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Cita> getCitas() {
		return citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	@ManyToOne(targetEntity = Marca.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idMarca", nullable = true)
	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	@ManyToOne(targetEntity = Caja.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idCaja", nullable = true)
	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	@ManyToOne(targetEntity = Combustible.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idCombustible", nullable = true)
	public Combustible getCombustible() {
		return combustible;
	}

	public void setCombustible(Combustible combustible) {
		this.combustible = combustible;
	}

	@OneToMany(/* targetEntity=Clase.class, */ mappedBy = "vehiculo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<VehiculoAccesorio> getVehiculoAccesorio() {
		return vehiculoAccesorio;
	}

	public void setVehiculoAccesorio(List<VehiculoAccesorio> vehiculoAccesorio) {
		this.vehiculoAccesorio = vehiculoAccesorio;
	}

	@ManyToOne(targetEntity = GrosorAceite.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idGrosorAceite", nullable = true)
	public GrosorAceite getGrosorAceite() {
		return grosorAceite;
	}

	public void setGrosorAceite(GrosorAceite grosorAceite) {
		this.grosorAceite = grosorAceite;
	}

	@ManyToOne(targetEntity = Modelo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idModelo", nullable = true)
	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	@ManyToOne(targetEntity = Refrigerante.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idRefrigerante", nullable = true)
	public Refrigerante getRefrigerante() {
		return refrigerante;
	}

	public void setRefrigerante(Refrigerante refrigerante) {
		this.refrigerante = refrigerante;
	}

	@ManyToOne(targetEntity = TipoAceite.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idTipoAceite", nullable = true)
	public TipoAceite getTipoAceite() {
		return tipoAceite;
	}

	public void setTipoAceite(TipoAceite tipoAceite) {
		this.tipoAceite = tipoAceite;
	}
	
	@OneToMany(/*targetEntity=ClienteDeporte.class,*/ mappedBy="vehiculo", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<VehiculoServicio> getVehiculoServicios() {
		return vehiculoServicios;
	}

	public void setVehiculoServicios(List<VehiculoServicio> vehiculoServicios) {
		this.vehiculoServicios = vehiculoServicios;
	}

}
