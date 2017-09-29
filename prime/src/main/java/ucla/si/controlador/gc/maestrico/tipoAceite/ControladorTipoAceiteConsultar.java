package ucla.si.controlador.gc.maestrico.tipoAceite;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoAceite;
import ucla.si.servicio.ServicioTipoAceite;

public class ControladorTipoAceiteConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoAceite servicioTipoAceite;

	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		TipoAceite tipoAceite = (TipoAceite) getAtributo("tipoAceite");
		cargarDatos(tipoAceite);
	}

	public void cargarDatos(TipoAceite tipoAceite) {
		if (tipoAceite != null) {
			txtDescripcion.setValue(tipoAceite.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/tipoAceite/frm-tipoAceite-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbTipoAceite")
	public void tipoAceite() {
		String dir = "gc/tipoAceite/frm-tipoAceite-catalogo.zul";
		clearDivApp(dir);
	}

}
