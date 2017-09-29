package ucla.si.modelo;

import java.io.Serializable;
import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class PreguntaFrecuente implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String pregunta;
	private String respuesta;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;

	public PreguntaFrecuente(String pregunta, String respuesta, String estatus, Date fechaCreacion,
			Date fechaModificacion) {
		super();
		this.pregunta = pregunta;
		this.respuesta = respuesta;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

	public PreguntaFrecuente() {
		super();
	}

	@Id
	@SequenceGenerator(name = "pk_sequence_preguntafrecuente", sequenceName = "preguntafrecuente_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence_preguntafrecuente")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false,length=1000)
	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	@Column(nullable = false,length=1000)
	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
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
