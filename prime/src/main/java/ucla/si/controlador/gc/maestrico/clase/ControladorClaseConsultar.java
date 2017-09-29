package ucla.si.controlador.gc.maestrico.clase;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Clase;
import ucla.si.servicio.ServicioClase;

public class ControladorClaseConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioClase servicioClase;

	@Wire
	private Combobox cmbTipoClase;

	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		Clase clase = (Clase) getAtributo("clase");
		cargarDatos(clase);
	}

	public void cargarDatos(Clase clase) {
		if (clase != null) {
			cmbTipoClase.setValue(clase.getTipoClase().getDescripcion());
			txtDescripcion.setValue(clase.getDescripcion().trim());
			cmbTipoClase.setDisabled(true);
			txtDescripcion.setDisabled(true);
		}
	}

	@Listen("onClick =#btnAceptar ; onClick =#btnCancelar")
	public void aceptar() {
		String dir = "gc/clase/frm-clase-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbClase")
	public void clase() {
		String dir = "gc/clase/frm-clase-catalogo.zul";
		clearDivApp(dir);
	}

}
