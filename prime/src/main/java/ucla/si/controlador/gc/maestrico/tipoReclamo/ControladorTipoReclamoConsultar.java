package ucla.si.controlador.gc.maestrico.tipoReclamo;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoReclamo;
import ucla.si.servicio.ServicioTipoReclamo;

public class ControladorTipoReclamoConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoReclamo servicioTipoReclamo;

	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		TipoReclamo tipoReclamo = (TipoReclamo) getAtributo("tipoReclamo");
		cargarDatos(tipoReclamo);
	}

	public void cargarDatos(TipoReclamo tipoReclamo) {
		if (tipoReclamo != null) {
			txtDescripcion.setValue(tipoReclamo.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/tipoReclamo/frm-tipoReclamo-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbTipoReclamo")
	public void tipoReclamo() {
		String dir = "gc/tipoReclamo/frm-tipoReclamo-catalogo.zul";
		clearDivApp(dir);
	}

}
