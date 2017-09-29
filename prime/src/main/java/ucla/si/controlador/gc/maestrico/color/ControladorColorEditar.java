package ucla.si.controlador.gc.maestrico.color;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Color;
import ucla.si.servicio.ServicioColor;

public class ControladorColorEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioColor servicioColor;

	@Wire
	private Textbox txtDescripcion;

	private Color color;

	@Override
	protected void inicializar() {
		color = (Color) getAtributo("color");
		cargarDatos(color);
	}

	public void cargarDatos(Color color) {
		if (color != null) {
			txtDescripcion.setValue(color.getDescripcion().trim());
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/color/frm-color-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			color.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			if (servicioColor.editarColor(color)) {
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/color/frm-color-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/color/frm-color-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbColor")
	public void color() {
		String dir = "gc/color/frm-color-catalogo.zul";
		clearDivApp(dir);
	}

}
