package ucla.si.controlador.gc.maestrico.estado;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Estado;
import ucla.si.servicio.ServicioEstado;

public class ControladorEstadoIncluir extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioEstado servicioEstado;

	@Wire
	Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			String descripcion = txtDescripcion.getValue().trim();
			String estatus = "Activo";
			Estado estado = new Estado(descripcion, new Date(), new Date(), estatus, new Date(), null);
			if (servicioEstado.incluirEstado(estado)) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/estado/frm-estado-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/estado/frm-estado-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbEstado")
	public void estado() {
		String dir = "gc/estado/frm-estado-catalogo.zul";
		clearDivApp(dir);
	}

}
