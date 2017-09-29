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
public class ServiciosTecnologias implements Serializable, Cloneable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Servicio servicio;
	private Tecnologia tecnologia;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	

	public ServiciosTecnologias() {
		super();
	}

	public ServiciosTecnologias(String estatus, Date fechaCreacion, Date fechaModificacion) {
		super();
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

	@Id
	@SequenceGenerator(name= "pk_sequence_serviciotecnologia", sequenceName="serviciotecnologia_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_serviciotecnologia")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	


	public void setFuncion(Tecnologia tecnologia) {
		this.tecnologia = tecnologia;
	}

	@ManyToOne(targetEntity = Servicio.class, fetch = FetchType.LAZY)
	@JoinColumn(name="idServicio", nullable = true)
	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
	@ManyToOne(targetEntity = Tecnologia.class, fetch = FetchType.LAZY)
	@JoinColumn(name="idTecnologia", nullable = true)
	public Tecnologia getTecnologia() {
		return tecnologia;
	}
	
	public void setTecnologia(Tecnologia tecnologia) {
		this.tecnologia = tecnologia;
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
