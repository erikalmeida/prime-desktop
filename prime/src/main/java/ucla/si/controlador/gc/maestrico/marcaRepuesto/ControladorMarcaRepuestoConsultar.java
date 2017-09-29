package ucla.si.controlador.gc.maestrico.marcaRepuesto;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.MarcaRepuesto;
import ucla.si.servicio.ServicioMarcaRepuesto;

public class ControladorMarcaRepuestoConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioMarcaRepuesto servicioMarcaRepuesto;

	@Wire
	Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		MarcaRepuesto marcaRepuesto = (MarcaRepuesto) getAtributo("marcaRepuesto");
		cargarDatos(marcaRepuesto);
	}

	public void cargarDatos(MarcaRepuesto marcaRepuesto) {
		if (marcaRepuesto != null) {

			txtDescripcion.setValue(marcaRepuesto.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
			txtDescripcion.setReadonly(true);
		}
	}

	@Listen("onClick =#btnAceptar ; onClick =#btnCancelar")
	public void aceptar() {
		String dir = "gc/marcaRepuesto/frm-marcaRepuesto-catalogo.zul";
		clearDivApp(dir);
	}


	@Listen("onClick =#breadcrumbMarcaRepuesto")
	public void marcaRepuesto() {
		String dir = "gc/marcaRepuesto/frm-marcaRepuesto-catalogo.zul";
		clearDivApp(dir);
	}

}
