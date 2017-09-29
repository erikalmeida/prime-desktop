package ucla.si.controlador.gc.maestrico.horario;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Horario;
import ucla.si.modelo.Horario;
import ucla.si.servicio.ServicioHorario;
import ucla.si.servicio.ServicioHorario;

public class ControladorHorarioEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioHorario servicioHorario;

	@Wire
	private Textbox txtDescripcion;

	private Horario horario;

	@Override
	protected void inicializar() {
		horario = (Horario) getAtributo("horario");
		cargarDatos(horario);
	}

	public void cargarDatos(Horario horario) {
		if (horario != null) {
			txtDescripcion.setValue(horario.getDescripcion().trim());
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/horario/frm-horario-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			horario.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			if (servicioHorario.editarHorario(horario)) {
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/horario/frm-horario-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/horario/frm-horario-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbHorario")
	public void horario() {
		String dir = "gc/horario/frm-horario-catalogo.zul";
		clearDivApp(dir);
	}

}
