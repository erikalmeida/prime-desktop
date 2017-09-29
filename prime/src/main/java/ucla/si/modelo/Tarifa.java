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
	public class Tarifa implements Serializable, Cloneable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Long id;		
		private String descripcion;
		private String estatus;
		private Date fechaCreacion;
		private Date fechaModificacion;
		private Double monto;

		public Tarifa() {
			super();
		}

		public Tarifa(String descripcion, String estatus, Date fechaCreacion,
				Date fechaModificacion, Double monto) {
			super();

			this.descripcion = descripcion;
			this.estatus = estatus;
			this.fechaCreacion = fechaCreacion;
			this.fechaModificacion = fechaModificacion;
			this.monto = monto;
			
		}

		public Double getMonto() {
			return monto;
		}

		public void setMonto(Double monto) {
			this.monto = monto;
		}

		@Id
		@SequenceGenerator(name= "pk_sequence_tarifa", sequenceName="tarifa_id_seq", allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.AUTO, generator="pk_sequence_tarifa")
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
		

	}