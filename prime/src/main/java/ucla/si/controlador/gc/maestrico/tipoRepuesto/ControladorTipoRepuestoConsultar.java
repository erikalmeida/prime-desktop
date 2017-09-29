package ucla.si.controlador.gc.maestrico.tipoRepuesto;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoRepuesto;
import ucla.si.servicio.ServicioTipoRepuesto;

public class ControladorTipoRepuestoConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoRepuesto servicioTipoRepuesto;

	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		TipoRepuesto tipoRepuesto = (TipoRepuesto) getAtributo("tipoRepuesto");
		cargarDatos(tipoRepuesto);
	}

	public void cargarDatos(TipoRepuesto tipoRepuesto) {
		if (tipoRepuesto != null) {
			txtDescripcion.setValue(tipoRepuesto.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/tipoRepuesto/frm-tipoRepuesto-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbTipoRepuesto")
	public void TipoRepuesto() {
		String dir = "gc/tipoRepuesto/frm-tipoRepuesto-catalogo.zul";
		clearDivApp(dir);
	}

}