package ucla.si.controlador.gc.maestrico.tipoRepuesto;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoRepuesto;
import ucla.si.servicio.ServicioTipoRepuesto;

public class ControladorTipoRepuestoIncluir extends ControladorInicio {

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
		// TODO Auto-generated method stub
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			String descripcion = txtDescripcion.getValue().trim().toUpperCase();
			TipoRepuesto tipoRepuesto = new TipoRepuesto(descripcion, "Activo", new Date(), null);
			if (servicioTipoRepuesto.incluirTipoRepuesto(tipoRepuesto)) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/tipoRepuesto/frm-tipoRepuesto-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
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