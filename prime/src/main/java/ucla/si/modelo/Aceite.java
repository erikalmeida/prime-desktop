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
public class Aceite implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String descripcion;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private TipoAceite tipoAceite;
	private GrosorAceite grosorAceite;
	
	
	
	
	
	public Aceite() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Aceite(String descripcion, String estatus, Date fechaCreacion,
			Date fechaModificacion) {
		super();
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}



	@Id
	@SequenceGenerator(name= "pk_sequence_aceite", sequenceName="aceite_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_aceite")
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
	public String getEstatus() {
		return estatus;
	}




	public void setEstatus(String estatus) {
		this.estatus = estatus;
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


	@ManyToOne(targetEntity = TipoAceite.class, fetch = FetchType.EAGER)
	@JoinColumn(name="idTipoAceite", nullable = false)
	public TipoAceite getTipoAceite() {
		return tipoAceite;
	}


	public void setTipoAceite(TipoAceite tipoAceite) {
		this.tipoAceite = tipoAceite;
	}
	
	@ManyToOne(targetEntity = GrosorAceite.class, fetch = FetchType.EAGER)
	@JoinColumn(name="idGrosorAceite", nullable = false)
	public GrosorAceite getGrosorAceite() {
		return grosorAceite;
	}

	public void setGrosorAceite(GrosorAceite grosorAceite) {
		this.grosorAceite = grosorAceite;
	}
	
}
