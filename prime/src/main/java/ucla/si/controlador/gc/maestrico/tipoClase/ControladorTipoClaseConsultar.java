package ucla.si.controlador.gc.maestrico.tipoClase;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoClase;
import ucla.si.servicio.ServicioTipoClase;

public class ControladorTipoClaseConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoClase servicioTipoClase;

	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		TipoClase tipoClase = (TipoClase) getAtributo("tipoClase");
		cargarDatos(tipoClase);
	}

	public void cargarDatos(TipoClase tipoClase) {
		if (tipoClase != null) {
			txtDescripcion.setValue(tipoClase.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/tipoClase/frm-tipoClase-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbTipoClase")
	public void tipoClase() {
		String dir = "gc/tipoClase/frm-tipoClase-catalogo.zul";
		clearDivApp(dir);
	}

}
