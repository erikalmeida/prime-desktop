package ucla.si.modelo.movil;

import java.io.Serializable;

import java.util.Date;

public class Sistema implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String correo;
	private String mapa;
	private String celular;	
	private String direccion;

	public Sistema() {
		super();
	}

	public Sistema(Long id, String correo, String mapa,
			String celular, String direccion) {
		super();
		this.id = id;
		this.correo = correo;
		this.mapa = mapa;
		this.celular = celular;
		this.direccion = direccion;
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

	public String getMapa() {
		return mapa;
	}

	public void setMapa(String mapa) {
		this.mapa = mapa;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
