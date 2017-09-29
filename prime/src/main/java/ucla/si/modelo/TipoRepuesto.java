
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;


@Entity
public class TipoRepuesto implements Serializable, Cloneable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String descripcion;
	private String estatus;
	private Date fechaCreacion;
	private Date fechaModificacion;
	@OneToMany
	private List<PresupuestoTipoRepuesto> presupuestoTipoRepuestos = new ArrayList<PresupuestoTipoRepuesto>();

	public TipoRepuesto() {
		super();
	}

	public TipoRepuesto(String descripcion, String estatus, Date fechaCreacion, Date fechaModificacion) {
		super();
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

	@Id
	@SequenceGenerator(name= "pk_sequence_tipoRepuesto", sequenceName="tipoRepuesto_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_tipoRepuesto")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	@Column(nullable = false)
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

	@OneToMany( mappedBy = "tipoRepuesto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<PresupuestoTipoRepuesto> getPresupuestoTipoRepuestos() {
		return presupuestoTipoRepuestos;
	}

	public void setPresupuestoTipoRepuestos(List<PresupuestoTipoRepuesto> presupuestoTipoRepuestos) {
		this.presupuestoTipoRepuestos = presupuestoTipoRepuestos;
	}
	
	

}

