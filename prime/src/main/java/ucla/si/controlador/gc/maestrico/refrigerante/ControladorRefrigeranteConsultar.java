package ucla.si.controlador.gc.maestrico.refrigerante;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Refrigerante;
import ucla.si.servicio.ServicioRefrigerante;

public class ControladorRefrigeranteConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioRefrigerante servicioRefrigerante;

	@Wire
	private Combobox cmbTipoRefrigerante;

	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		Refrigerante refrigerante = (Refrigerante) getAtributo("refrigerante");
		cargarDatos(refrigerante);
	}

	public void cargarDatos(Refrigerante refrigerante) {
		if (refrigerante != null) {
			cmbTipoRefrigerante.setValue(refrigerante.getTipoRefrigerante().getDescripcion());
			txtDescripcion.setValue(refrigerante.getDescripcion().trim());
			cmbTipoRefrigerante.setDisabled(true);
			txtDescripcion.setDisabled(true);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/refrigerante/frm-refrigerante-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbRefrigerante")
	public void refrigerante() {
		String dir = "gc/refrigerante/frm-refrigerante-catalogo.zul";
		clearDivApp(dir);
	}

}
