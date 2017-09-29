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
public class UsuarioHabilidad implements Serializable, Cloneable {
	
	private Long id;
	private Usuario usuario;
	private Habilidad habilidad;
	
	public UsuarioHabilidad(){
		
		
	}
	
	
	public UsuarioHabilidad(Long id, Usuario usuario, Habilidad habilidad) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.habilidad = habilidad;
	}
	
	@Id
	@SequenceGenerator(name= "pk_sequence_usuarioHabilidad", sequenceName="usuarioHabilidad_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_usuarioHabilidad")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@ManyToOne
	@JoinColumn(name="habilidad_id")
	public Habilidad getHabilidad() {
		return habilidad;
	}
	public void setHabilidad(Habilidad habilidad) {
		this.habilidad = habilidad;
	}
	
	
	
	
	
		
	
	
	
	

}
