package ucla.si.controlador.gc.maestrico.motivo;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Motivo;
import ucla.si.servicio.ServicioMotivo;

public class ControladorMotivoEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioMotivo servicioMotivo;

	@Wire
	Textbox txtDescripcion;

	private Motivo motivo;

	@Override
	protected void inicializar() {
		motivo = (Motivo) getAtributo("motivo");
		cargarDatos(motivo);
	}

	public void cargarDatos(Motivo motivo) {
		if (motivo != null) {
			if (motivo.getEstatus().equalsIgnoreCase("Activo")) {
				txtDescripcion.setValue(motivo.getDescripcion().trim());
			} else {
				txtDescripcion.setValue(motivo.getDescripcion().trim());
			}

		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/motivo/frm-motivo-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			motivo.setDescripcion(txtDescripcion.getValue().trim());
			if (servicioMotivo.editarMotivo(motivo)) {
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/motivo/frm-motivo-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen(" onClick =#btnCancelar")
	public void Cancelar() {
		String dir = "gc/motivo/frm-motivo-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbMotivo")
	public void motivo() {
		String dir = "gc/motivo/frm-motivo-catalogo.zul";
		clearDivApp(dir);
	}

}
