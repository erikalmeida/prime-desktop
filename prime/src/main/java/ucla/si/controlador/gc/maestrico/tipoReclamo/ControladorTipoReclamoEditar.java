package ucla.si.controlador.gc.maestrico.tipoReclamo;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoReclamo;
import ucla.si.servicio.ServicioTipoReclamo;

public class ControladorTipoReclamoEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoReclamo servicioTipoReclamo;

	@Wire
	private Textbox txtDescripcion;

	private TipoReclamo tipoReclamo;

	@Override
	protected void inicializar() {
		tipoReclamo = (TipoReclamo) getAtributo("tipoReclamo");
		cargarDatos(tipoReclamo);
	}

	public void cargarDatos(TipoReclamo tipoReclamo) {
		if (tipoReclamo != null) {
			txtDescripcion.setValue(tipoReclamo.getDescripcion().trim());
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/tipoReclamo/frm-tipoReclamo-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			tipoReclamo.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			if (servicioTipoReclamo.editarTipoReclamo(tipoReclamo)) {
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/tipoReclamo/frm-tipoReclamo-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/tipoReclamo/frm-tipoReclamo-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbTipoReclamo")
	public void tipoReclamo() {
		String dir = "gc/tipoReclamo/frm-tipoReclamo-catalogo.zul";
		clearDivApp(dir);
	}

}
