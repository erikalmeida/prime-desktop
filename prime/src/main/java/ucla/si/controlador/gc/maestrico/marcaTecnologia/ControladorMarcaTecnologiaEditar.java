package ucla.si.controlador.gc.maestrico.marcaTecnologia;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.MarcaTecnologia;
import ucla.si.servicio.ServicioMarcaTecnologia;

public class ControladorMarcaTecnologiaEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioMarcaTecnologia servicioMarcaTecnologia;

	@Wire
	Textbox txtDescripcion;

	/*
	 * @Wire Combobox cmbEstatus;
	 */

	private MarcaTecnologia marcaTecnologia;

	@Override
	protected void inicializar() {
		marcaTecnologia = (MarcaTecnologia) getAtributo("marcaTecnologia");
		cargarDatos(marcaTecnologia);
	}

	public void cargarDatos(MarcaTecnologia marcaTecnologia) {
		if (marcaTecnologia != null) {
			if (marcaTecnologia.getEstatus().equalsIgnoreCase("Activo")) {
				txtDescripcion.setValue(marcaTecnologia.getDescripcion().trim());
			} else {
				txtDescripcion.setValue(marcaTecnologia.getDescripcion().trim());
				/* cmbEstatus.setValue(grupo.getEstatus()); */
			}

		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/marcaTecnologia/frm-marcaTecnologia-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {

			marcaTecnologia.setDescripcion(txtDescripcion.getValue().trim());
			if (servicioMarcaTecnologia.editarMarcaTecnologia(marcaTecnologia)) {
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/marcaTecnologia/frm-marcaTecnologia-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/marcaTecnologia/frm-marcaTecnologia-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbMarcaTecnologia")
	public void marcaTecnologia() {
		String dir = "gc/marcaTecnologia/frm-marcaTecnologia-catalogo.zul";
		clearDivApp(dir);
	}

}
