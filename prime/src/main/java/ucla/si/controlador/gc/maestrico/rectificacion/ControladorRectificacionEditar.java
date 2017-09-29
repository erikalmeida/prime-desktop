package ucla.si.controlador.gc.maestrico.rectificacion;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Clase;
import ucla.si.modelo.Rectificacion;
import ucla.si.modelo.TipoClase;
import ucla.si.servicio.ServicioClase;
import ucla.si.servicio.ServicioRectificacion;
import ucla.si.servicio.ServicioTipoClase;

public class ControladorRectificacionEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioRectificacion servicioRectificacion;

	@Wire
	private Textbox txtDescripcion;

	private Rectificacion rectificacion;

	@Override
	protected void inicializar() {
		rectificacion = (Rectificacion) getAtributo("rectificacion");
		cargarDatos(rectificacion);
	}

	public void cargarDatos(Rectificacion rectificacion) {
		if (rectificacion != null) {
			txtDescripcion.setValue(rectificacion.getDescripcion().trim());
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/rectificacion/frm-rectificacion-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			rectificacion.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			if (servicioRectificacion.editarRectificacion(rectificacion)) {
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/rectificacion/frm-rectificacion-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/rectificacion/frm-rectificacion-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbRectificacion")
	public void rectificacion() {
		String dir = "gc/rectificacion/frm-rectificacion-catalogo.zul";
		clearDivApp(dir);
	}

}
