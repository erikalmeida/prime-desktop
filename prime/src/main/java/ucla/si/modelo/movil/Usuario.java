package ucla.si.modelo.movil;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Usuario implements Serializable, Cloneable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String correo;
	private String contrasenna;
	private String pregunta;
	private String respuesta;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	

	public Usuario() {
		super();
	}
	
	public Usuario(String correo, String contrasenna, String pregunta, String respuesta, String estatus,
			Date fechaCreacion, Date fechaModificacion) {
		super();
		this.correo = correo;
		this.contrasenna = contrasenna;
		this.pregunta = pregunta;
		this.respuesta = respuesta;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenna() {
		return contrasenna;
	}

	public void setContrasenna(String contrasenna) {
		this.contrasenna = contrasenna;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}


}


