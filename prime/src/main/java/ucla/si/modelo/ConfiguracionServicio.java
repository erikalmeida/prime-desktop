package ucla.si.modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class ConfiguracionServicio {
	
	private Long id;
	private Servicio servicio;
	private boolean visible;
	
	public ConfiguracionServicio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConfiguracionServicio(boolean visible) {
		super();
		this.visible = visible;
	}

	@Id
	@SequenceGenerator(name = "pk_sequence_configuracionservicio", sequenceName = "configuracionservicio_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence_configuracionservicio")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne(targetEntity = Servicio.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "idServicio", nullable = false)
	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	

}
