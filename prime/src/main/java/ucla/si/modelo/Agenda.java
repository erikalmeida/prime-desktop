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
public class Agenda implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String estatus;
	private List<Espacio> espacio = new ArrayList<Espacio>();

	public Agenda() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Agenda(Date fechaCreacion, Date fechaModificacion, String estatus) {
		super();
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.estatus = estatus;
	}

	@Id
	@SequenceGenerator(name = "pk_sequence_agenda", sequenceName = "agenda_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence_agenda")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@OneToMany(/* targetEntity=cioHerramienta.class, */ mappedBy = "agenda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Espacio> getEspacio() {
		return espacio;
	}

	public void setEspacio(List<Espacio> espacio) {
		this.espacio = espacio;
	}

}