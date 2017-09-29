package ucla.si.controlador.gc.espacio;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Espacio;
import ucla.si.servicio.ServicioEspacio;

public class ControladorEspacioIncluir extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioEspacio servicioEspacio;

	@Wire
	private Textbox txtDescripcion,txtIdentificacion;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {

			String descripcion = txtDescripcion.getValue().trim();
			String identificacion = txtIdentificacion.getValue().trim();
			String estatus = "Activo";
			Espacio espacio = new Espacio(identificacion,descripcion, false, estatus, new Date(), null);
			if (servicioEspacio.incluirEspacio(espacio)) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/espacio/frm-espacio-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/espacio/frm-espacio-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbEspacio")
	public void espacio() {
		String dir = "gc/espacio/frm-espacio-catalogo.zul";
		clearDivApp(dir);
	}

}
