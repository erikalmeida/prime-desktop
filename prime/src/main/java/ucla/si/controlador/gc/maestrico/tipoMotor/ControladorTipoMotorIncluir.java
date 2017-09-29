package ucla.si.controlador.gc.maestrico.tipoMotor;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoMotor;
import ucla.si.servicio.ServicioTipoMotor;

public class ControladorTipoMotorIncluir extends ControladorInicio {

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
		// TODO Auto-generated method stub
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			String descripcion = txtDescripcion.getValue().trim().toUpperCase();
			TipoMotor tipoMotor = new TipoMotor(descripcion, "Activo", new Date(), null);
			if (servicioTipoMotor.incluirTipoMotor(tipoMotor)) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
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
