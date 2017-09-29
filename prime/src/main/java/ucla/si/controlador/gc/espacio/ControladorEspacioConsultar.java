package ucla.si.controlador.gc.espacio;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Espacio;
import ucla.si.servicio.ServicioEspacio;

public class ControladorEspacioConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioEspacio servicioEspacio;

	@Wire
	Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		Espacio espacio = (Espacio) getAtributo("espacio");
		cargarDatos(espacio);
	}

	public void cargarDatos(Espacio espacio) {
		if (espacio != null) {

			txtDescripcion.setValue(espacio.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
			txtDescripcion.setReadonly(true);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/espacio/frm-espacio-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbEspacio")
	public void espacio() {
		String dir = "gc/espacio/frm-espacio-catalogo.zul";
		clearDivApp(dir);
	}

}
