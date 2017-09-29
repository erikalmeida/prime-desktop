package ucla.si.controlador.gc.maestrico.aceite;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Aceite;
import ucla.si.servicio.ServicioAceite;

public class ControladorAceiteConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioAceite servicioAceite;

	@Wire
	private Combobox cmbTipoAceite;

	@Wire
	private Combobox cmbGrosorAceite;

	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		Aceite aceite = (Aceite) getAtributo("aceite");
		cargarDatos(aceite);
	}

	public void cargarDatos(Aceite aceite) {
		if (aceite != null) {
			cmbTipoAceite.setValue(aceite.getTipoAceite().getDescripcion());
			cmbGrosorAceite.setValue(aceite.getGrosorAceite().getDescripcion());
			txtDescripcion.setValue(aceite.getDescripcion().trim());
			cmbTipoAceite.setDisabled(true);
			cmbGrosorAceite.setDisabled(true);
			txtDescripcion.setDisabled(true);
		}
	}

	@Listen("onClick =#btnAceptar ; onClick =#btnCancelar")
	public void aceptar() {
		String dir = "gc/aceite/frm-aceite-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbAceite")
	public void aceite() {
		String dir = "gc/aceite/frm-aceite-catalogo.zul";
		clearDivApp(dir);
	}

}
