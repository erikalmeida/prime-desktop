package ucla.si.modelo.movil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Presupuesto implements Serializable, Cloneable{
	
private static final long serialVersionUID = 1L;
	
	private Long idpresupuesto;
	private float monto_total;
	private Date fechacreacion;
	private String estatus;

	public Presupuesto(){
		super();
	}
	
	
	

	public Presupuesto(Long idpresupuesto, float monto_total, Date fechacreacion,
			String estatus) {
		super();
		this.idpresupuesto = idpresupuesto;
		this.monto_total = monto_total;
		this.fechacreacion = fechacreacion;
		this.estatus = estatus;
	}




	public Long getIdpresupuesto() {
		return idpresupuesto;
	}

	public void setIdpresupuesto(Long idpresupuesto) {
		this.idpresupuesto = idpresupuesto;
	}

	public float getMonto() {
		return monto_total;
	}

	public void setMonto(float monto_total) {
		this.monto_total = monto_total;
	}

	public Date getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
	
	

	
}
