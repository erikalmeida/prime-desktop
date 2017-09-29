package ucla.si.modelo.movil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Notificacion implements Serializable, Cloneable{
	
private static final long serialVersionUID = 1L;
	
	private Long idnotificacion;
	private String nombre;
	private String descripcion;
	private Date fechacreacion;
	private Long idtiponotificacion;
	private Long idpromocion;
	private Long ideventualidad;
	private Long idordenentrega;
	private Long idpresupuesto;
	private String estatus;
	
	
	public Notificacion(Long idnotificacion, String nombre, String descripcion, Date fechacreacion,
			Long idtiponotificacion, Long idpromocion, Long ideventualidad, Long idpresupuesto,
			String estatus) {
		super();
		this.idnotificacion = idnotificacion;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechacreacion = fechacreacion;
		this.idtiponotificacion = idtiponotificacion;
		this.idpromocion = idpromocion;
		this.ideventualidad = ideventualidad;
		this.idpresupuesto = idpresupuesto;
		this.estatus = estatus;
	}


	public Long getIdnotificacion() {
		return idnotificacion;
	}


	public void setIdnotificacion(Long idnotificacion) {
		this.idnotificacion = idnotificacion;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Date getFechacreacion() {
		return fechacreacion;
	}


	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}


	public Long getIdtiponotificacion() {
		return idtiponotificacion;
	}


	public void setIdtiponotificacion(Long idtiponotificacion) {
		this.idtiponotificacion = idtiponotificacion;
	}


	public Long getIdpromocion() {
		return idpromocion;
	}


	public void setIdpromocion(Long idpromocion) {
		this.idpromocion = idpromocion;
	}


	public Long getIdeventualidad() {
		return ideventualidad;
	}


	public void setIdeventualidad(Long ideventualidad) {
		this.ideventualidad = ideventualidad;
	}


	public Long getIdordenentrega() {
		return idordenentrega;
	}


	public void setIdordenentrega(Long idordenentrega) {
		this.idordenentrega = idordenentrega;
	}


	public Long getIdpresupuesto() {
		return idpresupuesto;
	}


	public void setIdpresupuesto(Long idpresupuesto) {
		this.idpresupuesto = idpresupuesto;
	}


	public String getEstatus() {
		return estatus;
	}


	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
