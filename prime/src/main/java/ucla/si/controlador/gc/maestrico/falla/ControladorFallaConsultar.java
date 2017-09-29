package ucla.si.controlador.gc.maestrico.falla;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Falla;
import ucla.si.servicio.ServicioFalla;

public class ControladorFallaConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioFalla servicioFalla;

	@Wire
	Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		Falla falla = (Falla) getAtributo("falla");
		cargarDatos(falla);
	}

	public void cargarDatos(Falla falla) {
		if (falla != null) {

			txtDescripcion.setValue(falla.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
			txtDescripcion.setReadonly(true);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/falla/frm-falla-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbFalla")
	public void falla() {
		String dir = "gc/falla/frm-falla-catalogo.zul";
		clearDivApp(dir);
	}

}
