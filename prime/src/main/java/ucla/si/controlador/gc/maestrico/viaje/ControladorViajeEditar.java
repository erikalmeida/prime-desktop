package ucla.si.controlador.gc.maestrico.viaje;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Viaje;
import ucla.si.servicio.ServicioViaje;

public class ControladorViajeEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	ServicioViaje servicioViaje;

	@Wire
	private Textbox txtDescripcion;

	Viaje viaje;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
		viaje = (Viaje) getAtributo("viaje");
		cargarDatos(viaje);
	}

	public void cargarDatos(Viaje viaje) {
		if (viaje != null) {
			txtDescripcion.setValue(viaje.getDescripcion().trim());
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "pc/viaje/frm-viaje-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			viaje.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			if (servicioViaje.editarViaje(viaje)) {
				Messagebox.show("Edición Exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "pc/viaje/frm-viaje-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al Guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "pc/viaje/frm-viaje-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbViaje")
	public void viaje() {
		String dir = "pc/viaje/frm-viaje-catalogo.zul";
		clearDivApp(dir);
	}

}
