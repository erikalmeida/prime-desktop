package ucla.si.controlador.gc.maestrico.marcaRepuesto;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.MarcaRepuesto;
import ucla.si.servicio.ServicioMarcaRepuesto;

public class ControladorMarcaRepuestoEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioMarcaRepuesto servicioMarcaRepuesto;

	@Wire
	Textbox txtDescripcion;

	@Wire
	Combobox cmbEstatus;

	private MarcaRepuesto marcaRepuesto;

	@Override
	protected void inicializar() {
		marcaRepuesto = (MarcaRepuesto) getAtributo("marcaRepuesto");
		cargarDatos(marcaRepuesto);
	}

	public void cargarDatos(MarcaRepuesto marcaRepuesto) {
		if (marcaRepuesto != null) {
			if (marcaRepuesto.getEstatus().equalsIgnoreCase("Activo")) {

				txtDescripcion.setValue(marcaRepuesto.getDescripcion().trim());
			} else {

				txtDescripcion.setValue(marcaRepuesto.getDescripcion().trim());
				cmbEstatus.setValue(marcaRepuesto.getEstatus());
			}

		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/marcaRepuesto/frm-marcaRepuesto-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {

			marcaRepuesto.setDescripcion(txtDescripcion.getValue().trim());
			if (servicioMarcaRepuesto.editarMarcaRepuesto(marcaRepuesto)) {
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/marcaRepuesto/frm-marcaRepuesto-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}



	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/marcaRepuesto/frm-marcaRepuesto-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbMarcaRepuesto")
	public void marcaRepuesto() {
		String dir = "gc/marcaRepuesto/frm-marcaRepuesto-catalogo.zul";
		clearDivApp(dir);
	}
}
