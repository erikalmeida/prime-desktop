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
public class Garaje implements Serializable, Cloneable {
	
	private Long garaje_id;
	private String descripcion;
	private Usuario usuario;
	
	public Garaje(){
		
		
	}

	public Garaje(Long garaje_id, String descripcion, Usuario usuario) {
		super();
		this.garaje_id = garaje_id;
		this.descripcion = descripcion;
		this.usuario = usuario;
	}

	@Id
	@GeneratedValue
	public Long getGaraje_id() {
		return garaje_id;
	}

	public void setGaraje_id(Long garaje_id) {
		this.garaje_id = garaje_id;
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
	
	
	
	
	
	
	

}
