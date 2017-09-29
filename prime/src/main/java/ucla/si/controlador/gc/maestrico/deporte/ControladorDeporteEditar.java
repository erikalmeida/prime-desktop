package ucla.si.controlador.gc.maestrico.deporte;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Deporte;
import ucla.si.servicio.ServicioDeporte;

public class ControladorDeporteEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioDeporte servicioDeporte;

	@Wire
	private Textbox txtDescripcion;

	Deporte deporte;

	@Override
	protected void inicializar() {
		deporte = (Deporte) getAtributo("deporte");
		cargarDatos(deporte);

	}

	public void cargarDatos(Deporte deporte) {
		if (deporte != null) {
			txtDescripcion.setValue(deporte.getDescripcion().trim());
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "pc/deporte/frm-deporte-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			deporte.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			if (servicioDeporte.editarDeporte(deporte)) {
				Messagebox.show("Edición Exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "pc/deporte/frm-deporte-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al Guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "pc/deporte/frm-deporte-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbDeporte")
	public void deporte() {
		String dir = "pc/deporte/frm-deporte-catalogo.zul";
		clearDivApp(dir);
	}

}
