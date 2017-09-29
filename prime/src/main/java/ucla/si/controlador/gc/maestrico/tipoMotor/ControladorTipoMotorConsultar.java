package ucla.si.controlador.gc.maestrico.tipoMotor;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoMotor;
import ucla.si.servicio.ServicioTipoMotor;

public class ControladorTipoMotorConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoMotor servicioTipoMotor;

	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		TipoMotor tipoMotor = (TipoMotor) getAtributo("tipoMotor");
		cargarDatos(tipoMotor);
	}

	public void cargarDatos(TipoMotor tipoMotor) {
		if (tipoMotor != null) {
			txtDescripcion.setValue(tipoMotor.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/tipoMotor/frm-tipoMotor-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbTipoMotor")
	public void tipoMotor() {
		String dir = "gc/tipoMotor/frm-tipoMotor-catalogo.zul";
		clearDivApp(dir);
	}

}
