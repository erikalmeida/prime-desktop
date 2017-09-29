package ucla.si.controlador.gc.maestrico.deporte;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Deporte;
import ucla.si.servicio.ServicioDeporte;

public class ControladorDeporteIncluir extends ControladorInicio {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioDeporte servicioDeporte;

	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			String descripcion = txtDescripcion.getValue().trim().toUpperCase();
			Deporte deporte = new Deporte(descripcion, "Activo", new Date(), null);
			if (servicioDeporte.incluirDeporte(deporte)) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "pc/deporte/frm-deporte-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "pc/deporte/frm-deporte-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbDeporte")
	public void deporte() {
		String dir = "pc/deporte/frm-deporte-catalogo.zul";
		clearDivApp(dir);
	}

}
