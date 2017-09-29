package ucla.si.modelo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class EspacioOrdenServicio implements Serializable, Cloneable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	//private OrdenServicio ordenServicio;
	private Espacio espacio;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;

	public EspacioOrdenServicio() {
		super();
	}

	public EspacioOrdenServicio(String estatus, Date fechaCreacion, Date fechaModificacion) {
		super();
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

	@Id
	@SequenceGenerator(name= "pk_sequence_espacioOrdenServicio", sequenceName="espacioOrdenServicio_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_espacioOrdenServicio")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    /*
	@ManyToOne
	@JoinColumn(name="idOrdenServicio")
	public OrdenServicio getOrdenServicio() {
		return ordenServicio;
	}

	public void setOrdenServicio(OrdenServicio ordenServicio) {
		this.ordenServicio = rdenServicio;
	}*/

	@ManyToOne
	@JoinColumn(name="idEspacio")
	public Espacio getEspacio() {
		return espacio;
	}

	public void setEspacio(Espacio espacio) {
		this.espacio = espacio;
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
