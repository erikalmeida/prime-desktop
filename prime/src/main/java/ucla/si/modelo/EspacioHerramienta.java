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
public class EspacioHerramienta implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Espacio espacio;
	private Herramienta herramienta;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;

	public EspacioHerramienta() {
		super();
	}

	public EspacioHerramienta(String estatus, Date fechaCreacion, Date fechaModificacion) {
		super();
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

	@Id
	@SequenceGenerator(name = "pk_sequence_espacioherramienta", sequenceName = "espacioherramienta_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence_espacioherramienta")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFuncion(Herramienta herramienta) {
		this.herramienta = herramienta;
	}

	@ManyToOne(targetEntity = Espacio.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idEspacio", nullable = true)
	public Espacio getEspacio() {
		return espacio;
	}

	public void setEspacio(Espacio espacio) {
		this.espacio = espacio;
	}

	@ManyToOne(targetEntity = Herramienta.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idHerramienta", nullable = true)
	public Herramienta getHerramienta() {
		return herramienta;
	}

	public void setHerramienta(Herramienta herramienta) {
		this.herramienta = herramienta;
	}

	@Column(nullable = true)
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
