package ucla.si.controlador.gc.maestrico.habilidad;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Habilidad;
import ucla.si.servicio.ServicioHabilidad;

public class ControladorHabilidadIncluir extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioHabilidad servicioHabilidad;

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
			Habilidad habilidad = new Habilidad(descripcion, "Activo", new Date(), null);
			if (servicioHabilidad.incluirHabilidad(habilidad)) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
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
