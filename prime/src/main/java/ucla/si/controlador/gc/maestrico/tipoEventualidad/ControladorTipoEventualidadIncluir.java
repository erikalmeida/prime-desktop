package ucla.si.controlador.gc.maestrico.tipoEventualidad;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoEventualidad;
import ucla.si.servicio.ServicioTipoEventualidad;

public class ControladorTipoEventualidadIncluir extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	ServicioTipoEventualidad servicioTipoEventualidad;

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
			TipoEventualidad tipoEventualidad = new TipoEventualidad(descripcion, "Activo", new Date(), null);
			if (servicioTipoEventualidad.incluirTipoEventualidad(tipoEventualidad)) {
				Messagebox.show("Inclusión exitosa", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "pc/tipoEventualidad/frm-tipoEventualidad-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "pc/tipoEventualidad/frm-tipoEventualidad-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbTipoEventualidad")
	public void tipoEventualidad() {
		String dir = "pc/tipoEventualidad/frm-tipoEventualidad-catalogo.zul";
		clearDivApp(dir);
	}

}
