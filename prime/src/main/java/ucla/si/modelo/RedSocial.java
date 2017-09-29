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
public class RedSocial implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String url;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Sistema sistema;

	public RedSocial() {
		super();
	}

	public RedSocial(String nombre, String url, String estatus, Date fechaCreacion, Date fechaModificacion,
			Sistema sistema) {
		super();
		this.nombre = nombre;
		this.url = url;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.sistema = sistema;
	}

	@Id
	@SequenceGenerator(name = "pk_sequence_redSocial", sequenceName = "redSocial_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence_redSocial")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = true, unique = true)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(nullable = true)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	@ManyToOne(targetEntity = Sistema.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idSistema")
	public Sistema getIdSistema() {
		return sistema;
	}

	public void setIdSistema(Sistema idSistema) {
		this.sistema = idSistema;
	}

}
