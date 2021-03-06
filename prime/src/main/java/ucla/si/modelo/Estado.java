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
import javax.persistence.SequenceGenerator;


	@Entity
	public class Estado implements Serializable, Cloneable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Long id;		
		private String descripcion;
		private Date tiempoEstimadoEjecucion;
		private Date tiempoMaximoEjecucion;
		private String estatus;
		private Date fechaCreacion;
		private Date fechaModificacion;

		public Estado() {
			super();
		}

		public Estado(String descripcion,Date tiempoestimadoEjecucion, Date tiempoMaximoEjecucion, String estatus, Date fechaCreacion,
				Date fechaModificacion) {
			super();

			this.descripcion = descripcion;
			this.tiempoEstimadoEjecucion = tiempoEstimadoEjecucion;
			this.tiempoMaximoEjecucion = tiempoMaximoEjecucion;
			this.estatus = estatus;
			this.fechaCreacion = fechaCreacion;
			this.fechaModificacion = fechaModificacion;
		}

		@Id
		@SequenceGenerator(name= "pk_sequence_estado", sequenceName="estado_id_seq", allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_estado")
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
		
		@Column(nullable = true)
		public Date getTiempoEstimadoEjecucion() {
			return tiempoEstimadoEjecucion;
		}

		public void setTiempoEstimadoEjecucion(Date tiempoEstimadoEjecucion) {
			this.tiempoEstimadoEjecucion = tiempoEstimadoEjecucion;
		}
		
		@Column(nullable = true)
		public Date getTiempoMaximoEjecucion() {
			return tiempoMaximoEjecucion;
		}

		public void setTiempoMaximoEjecucion(Date tiempoMaximoEjecucion) {
			this.tiempoMaximoEjecucion = tiempoMaximoEjecucion;
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
		

	}