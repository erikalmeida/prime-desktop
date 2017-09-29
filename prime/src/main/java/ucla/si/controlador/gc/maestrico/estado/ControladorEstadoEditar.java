package ucla.si.controlador.gc.maestrico.estado;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Estado;
import ucla.si.servicio.ServicioEstado;

public class ControladorEstadoEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioEstado servicioEstado;

	@Wire
	Textbox txtDescripcion;

	/*
	 * @Wire Combobox cmbEstatus;
	 */

	private Estado estado;

	@Override
	protected void inicializar() {
		estado = (Estado) getAtributo("estado");
		cargarDatos(estado);
	}

	public void cargarDatos(Estado estado) {
		if (estado != null) {
			if (estado.getEstatus().equalsIgnoreCase("Activo")) {
				txtDescripcion.setValue(estado.getDescripcion().trim());
			} else {
				txtDescripcion.setValue(estado.getDescripcion().trim());
				/* cmbEstatus.setValue(grupo.getEstatus()); */
			}

		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/estado/frm-estado-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {

			estado.setDescripcion(txtDescripcion.getValue().trim());
			if (servicioEstado.editarEstado(estado)) {
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/estado/frm-estado-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen(" onClick =#btnCancelar")
	public void Cancelar() {
		String dir = "gc/estado/frm-estado-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbEstado")
	public void estado() {
		String dir = "gc/estado/frm-estado-catalogo.zul";
		clearDivApp(dir);
	}

}
