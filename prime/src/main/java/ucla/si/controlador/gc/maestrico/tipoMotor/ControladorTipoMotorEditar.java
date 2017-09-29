package ucla.si.controlador.gc.maestrico.tipoMotor;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoMotor;
import ucla.si.servicio.ServicioTipoMotor;

public class ControladorTipoMotorEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoMotor servicioTipoMotor;

	@Wire
	private Textbox txtDescripcion;

	private TipoMotor tipoMotor;

	@Override
	protected void inicializar() {
		tipoMotor = (TipoMotor) getAtributo("tipoMotor");
		cargarDatos(tipoMotor);
	}

	public void cargarDatos(TipoMotor tipoMotor) {
		if (tipoMotor != null) {
			txtDescripcion.setValue(tipoMotor.getDescripcion().trim());
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/tipoMotor/frm-tipoMotor-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			tipoMotor.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			if (servicioTipoMotor.editarTipoMotor(tipoMotor)) {
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/tipoMotor/frm-tipoMotor-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
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
