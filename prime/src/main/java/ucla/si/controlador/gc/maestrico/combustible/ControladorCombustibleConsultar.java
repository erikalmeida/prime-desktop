package ucla.si.controlador.gc.maestrico.combustible;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Combustible;
import ucla.si.servicio.ServicioCombustible;

public class ControladorCombustibleConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioCombustible servicioCombustible;

	@Wire
	private Combobox cmbTipoCombustible;

	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		Combustible Combustible = (Combustible) getAtributo("Combustible");
		cargarDatos(Combustible);
	}

	public void cargarDatos(Combustible Combustible) {
		if (Combustible != null) {
			cmbTipoCombustible.setValue(Combustible.getTipoCombustible().getDescripcion());
			txtDescripcion.setValue(Combustible.getDescripcion().trim());
			cmbTipoCombustible.setDisabled(true);
			txtDescripcion.setDisabled(true);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/combustible/frm-combustible-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbCombustible")
	public void combustible() {
		String dir = "gc/combustible/frm-combustible-catalogo.zul";
		clearDivApp(dir);
	}
}
