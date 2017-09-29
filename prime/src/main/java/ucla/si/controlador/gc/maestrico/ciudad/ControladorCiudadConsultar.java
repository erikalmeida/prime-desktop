package ucla.si.controlador.gc.maestrico.ciudad;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Ciudad;
import ucla.si.servicio.ServicioCiudad;

public class ControladorCiudadConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioCiudad servicioCiudad;

	@Wire
	private Combobox cmbEstado;

	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		Ciudad ciudad = (Ciudad) getAtributo("ciudad");
		cargarDatos(ciudad);
	}

	public void cargarDatos(Ciudad ciudad) {
		if (ciudad != null) {

			txtDescripcion.setValue(ciudad.getDescripcion().trim());
			cmbEstado.setValue(ciudad.getEstado().getDescripcion());
			cmbEstado.setDisabled(true);
			txtDescripcion.setDisabled(true);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		String dir = "gc/ciudad/frm-ciudad-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/ciudad/frm-ciudad-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbCiudad")
	public void ciudad() {
		String dir = "gc/ciudad/frm-ciudad-catalogo.zul";
		clearDivApp(dir);
	}

}
