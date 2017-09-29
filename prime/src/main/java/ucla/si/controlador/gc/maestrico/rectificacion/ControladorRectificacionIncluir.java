package ucla.si.controlador.gc.maestrico.rectificacion;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Rectificacion;
import ucla.si.modelo.Rectificacion;
import ucla.si.servicio.ServicioRectificacion;
import ucla.si.servicio.ServicioRectificacion;

public class ControladorRectificacionIncluir extends ControladorInicio {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioRectificacion servicioRectificacion;

	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			String descripcion = txtDescripcion.getValue().trim().toUpperCase();
			Rectificacion rectificacion = new Rectificacion(descripcion, "Activo", new Date(), null);
			if (servicioRectificacion.incluirRectificacion(rectificacion)) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
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
