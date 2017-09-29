package ucla.si.controlador.gc.maestrico.tipoClase;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoClase;
import ucla.si.servicio.ServicioTipoClase;

public class ControladorTipoClaseEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoClase servicioTipoClase;

	@Wire
	private Textbox txtDescripcion;

	private TipoClase tipoClase;

	@Override
	protected void inicializar() {
		tipoClase = (TipoClase) getAtributo("tipoClase");
		cargarDatos(tipoClase);
	}

	public void cargarDatos(TipoClase tipoClase) {
		if (tipoClase != null) {
			txtDescripcion.setValue(tipoClase.getDescripcion().trim());
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/tipoClase/frm-tipoClase-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			tipoClase.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			if (servicioTipoClase.editarTipoClase(tipoClase)) {
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/tipoClase/frm-tipoClase-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/tipoClase/frm-tipoClase-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbTipoClase")
	public void tipoClase() {
		String dir = "gc/tipoClase/frm-tipoClase-catalogo.zul";
		clearDivApp(dir);
	}

}
