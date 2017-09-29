package ucla.si.controlador.gc.maestrico.motor;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Motor;
import ucla.si.servicio.ServicioMotor;

public class ControladorMotorConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioMotor servicioMotor;

	@Wire
	private Combobox cmbTipoMotor;

	@Wire
	private Textbox txtDescripcion;

	@Wire
	private Textbox txtSerial;

	@Override
	protected void inicializar() {
		Motor motor = (Motor) getAtributo("motor");
		cargarDatos(motor);
	}

	public void cargarDatos(Motor motor) {
		if (motor != null) {
			cmbTipoMotor.setValue(motor.getTipoMotor().getDescripcion());
			txtDescripcion.setValue(motor.getDescripcion().trim());
			txtSerial.setValue(motor.getSerial().trim());
			txtSerial.setDisabled(true);
			cmbTipoMotor.setDisabled(true);
			txtDescripcion.setDisabled(true);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		String dir = "gc/motor/frm-motor-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/motor/frm-motor-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbMotor")
	public void motor() {
		String dir = "gc/motor/frm-motor-catalogo.zul";
		clearDivApp(dir);
	}

}
