package ucla.si.controlador.gc.herramienta;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Herramienta;
import ucla.si.servicio.ServicioHerramienta;

public class ControladorHerramientaConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioHerramienta servicioHerramienta;

	@Wire
	Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		Herramienta herramienta = (Herramienta) getAtributo("herramienta");
		cargarDatos(herramienta);
	}

	public void cargarDatos(Herramienta herramienta) {
		if (herramienta != null) {

			txtDescripcion.setValue(herramienta.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
			txtDescripcion.setReadonly(true);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/herramienta/frm-herramienta-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbHerramienta")
	public void herramienta() {
		String dir = "gc/herramienta/frm-herramienta-catalogo.zul";
		clearDivApp(dir);
	}

}
