package ucla.si.controlador.gc.maestrico.habilidad;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Habilidad;
import ucla.si.servicio.ServicioHabilidad;

public class ControladorHabilidadConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioHabilidad servicioHabilidad;

	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		Habilidad habilidad = (Habilidad) getAtributo("habilidad");
		cargarDatos(habilidad);
	}

	public void cargarDatos(Habilidad habilidad) {
		if (habilidad != null) {
			txtDescripcion.setValue(habilidad.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/habilidad/frm-habilidad-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbHabilidad")
	public void habilidad() {
		String dir = "gc/habilidad/frm-habilidad-catalogo.zul";
		clearDivApp(dir);
	}
}
