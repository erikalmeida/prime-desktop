package ucla.si.controlador.gc.maestrico.viaje;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Viaje;
import ucla.si.servicio.ServicioViaje;

public class ControladorViajeIncluir extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	ServicioViaje servicioViaje;

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
			Viaje viaje = new Viaje(descripcion, "Activo", new Date(), null);
			if (servicioViaje.incluirViaje(viaje)) {
				Messagebox.show("Inclusion exitosa", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "pc/viaje/frm-viaje-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "pc/viaje/frm-viaje-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbViaje")
	public void viaje() {
		String dir = "pc/viaje/frm-viaje-catalogo.zul";
		clearDivApp(dir);
	}

}
