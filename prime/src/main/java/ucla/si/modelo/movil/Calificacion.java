package ucla.si.modelo.movil;

import java.io.Serializable;
import java.util.Date;

public class Calificacion implements Serializable, Cloneable{
	
private static final long serialVersionUID = 1L;
	
	private Long id;
	private String comentario;
	private Date fecha;
	private int calificacionServicio;
	private int calificacionInstalacion;
	private int calificacionAtencion;
	private int idordenentrega;
	private String estatus;
	
	public Calificacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Calificacion(Long id, String comentario, Date fecha,
			int calificacionServicio, int calificacionInstalacion,
			int calificacionAtencion, int idordenentrega) {
		super();
		this.id = id;
		this.comentario = comentario;
		this.fecha = fecha;
		this.calificacionServicio = calificacionServicio;
		this.calificacionInstalacion = calificacionInstalacion;
		this.calificacionAtencion = calificacionAtencion;
		this.idordenentrega = idordenentrega;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getCalificacionServicio() {
		return calificacionServicio;
	}

	public void setCalificacionServicio(int calificacionServicio) {
		this.calificacionServicio = calificacionServicio;
	}

	public int getCalificacionInstalacion() {
		return calificacionInstalacion;
	}

	public void setCalificacionInstalacion(int calificacionInstalacion) {
		this.calificacionInstalacion = calificacionInstalacion;
	}

	public int getCalificacionAtencion() {
		return calificacionAtencion;
	}

	public void setCalificacionAtencion(int calificacionAtencion) {
		this.calificacionAtencion = calificacionAtencion;
	}

	public int getIdOrdenentrega() {
		return idordenentrega;
	}

	public void setIdOrdenentrega(int idordenentrega) {
		this.idordenentrega = idordenentrega;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
	
	
	
	
	
	
	

}
