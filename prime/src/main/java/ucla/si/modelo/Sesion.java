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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;


@Entity
public class Sesion implements Serializable, Cloneable {
	
	private Long seseion_id;
	private String descripcion;
	private Date ultima_sesion;
	private Usuario usuario;
	
	public Sesion(){
	}

	
    
	public Sesion(Long seseion_id, String descripcion, Date ultima_sesion) {
		super();
		this.seseion_id = seseion_id;
		this.descripcion = descripcion;
		this.ultima_sesion = ultima_sesion;
	}



	@Id
	@GeneratedValue
	public Long getSeseion_id() {
		return seseion_id;
	}

	public void setSeseion_id(Long seseion_id) {
		this.seseion_id = seseion_id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@ManyToOne
	@JoinColumn(name="usuario_id")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public Date getUltima_sesion() {
		return ultima_sesion;
	}



	public void setUltima_sesion(Date ultima_sesion) {
		this.ultima_sesion = ultima_sesion;
	}
	
	
	

}
