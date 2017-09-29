package ucla.si.controlador.gc.tecnologia;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Tecnologia;
import ucla.si.servicio.ServicioTecnologia;

public class ControladorTecnologiaEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTecnologia servicioTecnologia;

	@Wire
	Textbox txtDescripcion;

	@Wire
	Combobox cmbEstatus;

	private Tecnologia tecnologia;

	@Override
	protected void inicializar() {
		tecnologia = (Tecnologia) getAtributo("tecnologia");
		cargarDatos(tecnologia);
	}

	public void cargarDatos(Tecnologia tecnologia) {
		if (tecnologia != null) {
			if (tecnologia.getEstatus().equalsIgnoreCase("Activo")) {

				txtDescripcion.setValue(tecnologia.getDescripcion().trim());
			} else {

				txtDescripcion.setValue(tecnologia.getDescripcion().trim());
				cmbEstatus.setValue(tecnologia.getEstatus());
			}

		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/tecnologia/frm-tecnologia-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {

			tecnologia.setDescripcion(txtDescripcion.getValue().trim());
			if (servicioTecnologia.editarTecnologia(tecnologia)) {
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/tecnologia/frm-tecnologia-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/tecnologia/frm-tecnologia-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbTecnologia")
	public void tecnologia() {
		String dir = "gc/tecnologia/frm-tecnologia-catalogo.zul";
		clearDivApp(dir);
	}
}
