package ucla.si.controlador.gc.maestrico.motivo;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Motivo;
import ucla.si.servicio.ServicioMotivo;

public class ControladorMotivoIncluir extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioMotivo servicioMotivo;

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
			Motivo motivo = new Motivo(descripcion, estatus, new Date(), null);
			if (servicioMotivo.incluirMotivo(motivo)) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/motivo/frm-motivo-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/motivo/frm-motivo-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbMotivo")
	public void motivo() {
		String dir = "gc/motivo/frm-motivo-catalogo.zul";
		clearDivApp(dir);
	}

}
