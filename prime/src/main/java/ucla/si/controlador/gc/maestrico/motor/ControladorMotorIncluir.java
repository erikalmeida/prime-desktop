package ucla.si.controlador.gc.maestrico.motor;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Motor;
import ucla.si.modelo.TipoMotor;
import ucla.si.servicio.ServicioMotor;
import ucla.si.servicio.ServicioTipoMotor;

public class ControladorMotorIncluir extends ControladorInicio {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoMotor servicioTipoMotor;

	@WireVariable
	private ServicioMotor servicioMotor;

	@Wire
	private Textbox txtDescripcion;

	@Wire
	private Textbox txtSerial;

	@Wire
	private Combobox cmbTipoMotor;

	@Override
	protected void inicializar() {
		ListModelList<TipoMotor> modeloTipoMotores = new ListModelList<TipoMotor>(
				servicioTipoMotor.listarTipoMotores());
		if (modeloTipoMotores.getSize() == 0) {
			Messagebox.show("Debe Incluir un Tipo de Motor primero", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/tipoMotor/frm-tipoMotor-incluir.zul";
			clearDivApp(dir);
		} else {
			modeloTipoMotores.setMultiple(false);
			cmbTipoMotor.setModel(modeloTipoMotores);
			cmbTipoMotor.setMultiline(false);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "" || cmbTipoMotor.getSelectedIndex() == -1) {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			String descripcion = txtDescripcion.getValue().trim().toUpperCase();
			String serial = txtSerial.getValue().trim().toUpperCase();
			Motor motor = new Motor(descripcion, serial, new Date(), null, "Activo");
			TipoMotor tipoMotor = (TipoMotor) cmbTipoMotor.getSelectedItem().getValue();
			motor.setTipoMotor(tipoMotor);
			if (servicioMotor.incluirMotor(motor)) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/motor/frm-motor-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
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
