package ucla.si.controlador.gc.maestrico.tipoRepuesto;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoRepuesto;
import ucla.si.servicio.ServicioTipoRepuesto;

public class ControladorTipoRepuestoEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoRepuesto servicioTipoRepuesto;

	@Wire
	private Textbox txtDescripcion;

	private TipoRepuesto tipoRepuesto;

	@Override
	protected void inicializar() {
		tipoRepuesto = (TipoRepuesto) getAtributo("tipoRepuesto");
		cargarDatos(tipoRepuesto);
	}

	public void cargarDatos(TipoRepuesto tipoRepuesto) {
		if (tipoRepuesto != null) {
			txtDescripcion.setValue(tipoRepuesto.getDescripcion().trim());
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/tipoRepuesto/frm-tipoRepuesto-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			tipoRepuesto.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			if (servicioTipoRepuesto.editarTipoRepuesto(tipoRepuesto)) {
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
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