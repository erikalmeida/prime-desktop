package ucla.si.modelo;

import java.util.Date;

public class ReporteServicio {
	private Long idServicio;
	private String descripcion;
	private Integer cantidad;
	private Date fechaModificacion;
	
	public ReporteServicio(Long idServicio, String descripcion, Integer cantidad, Date fechaModificacion) {
		super();
		this.idServicio = idServicio;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.fechaModificacion = fechaModificacion;
	}

	public Long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	
	
	

}
