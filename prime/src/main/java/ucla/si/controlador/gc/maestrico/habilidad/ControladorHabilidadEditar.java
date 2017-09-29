package ucla.si.controlador.gc.maestrico.habilidad;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Habilidad;
import ucla.si.servicio.ServicioHabilidad;

public class ControladorHabilidadEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioHabilidad servicioHabilidad;

	@Wire
	private Textbox txtDescripcion;

	private Habilidad habilidad;

	@Override
	protected void inicializar() {
		habilidad = (Habilidad) getAtributo("habilidad");
		cargarDatos(habilidad);
	}

	public void cargarDatos(Habilidad habilidad) {
		if (habilidad != null) {
			txtDescripcion.setValue(habilidad.getDescripcion().trim());
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/habilidad/frm-habilidad-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			habilidad.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			if (servicioHabilidad.editarHabilidad(habilidad)) {
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/habilidad/frm-habilidad-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/habilidad/frm-habilidad-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbHabilidad")
	public void habilidad() {
		String dir = "gc/habilidad/frm-habilidad-catalogo.zul";
		clearDivApp(dir);
	}

}
