package ucla.si.controlador.gc.maestrico.rectificacion;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Clase;
import ucla.si.modelo.Rectificacion;
import ucla.si.servicio.ServicioClase;
import ucla.si.servicio.ServicioRectificacion;

public class ControladorRectificacionConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioRectificacion servicioRectificacion;

	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		Rectificacion rectificacion = (Rectificacion) getAtributo("rectificacion");
		cargarDatos(rectificacion);
	}

	public void cargarDatos(Rectificacion rectificacion) {
		if (rectificacion != null) {
			txtDescripcion.setValue(rectificacion.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/rectificacion/frm-rectificacion-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbRectificacion")
	public void rectificacion() {
		String dir = "gc/rectificacion/frm-rectificacion-catalogo.zul";
		clearDivApp(dir);
	}
}
