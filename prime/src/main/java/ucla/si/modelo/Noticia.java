package ucla.si.modelo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.zkoss.image.Image;
import org.zkoss.image.Images;
import org.zkoss.zk.ui.Executions;

import ucla.si.utils.*;


@Entity
public class Noticia implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String titulo;
	private String descripcion;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String urlImagen;

	public Noticia() {
		super();
	}

	public Noticia(String titulo, String descripcion, String estatus, Date fechaCreacion, Date fechaModificacion,
			String urlImagen) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.urlImagen = urlImagen;
	}

	@Id
	@SequenceGenerator(name = "pk_sequence_noticia", sequenceName = "noticia_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence_noticia")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(unique = true, nullable = false)
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Column(nullable = false,length=1000)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	@Column(nullable = true)
	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
	
	public String transformarHtml() {

		return this.descripcion.replaceAll("\\<.*?>", "");

	}




}
