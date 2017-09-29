package ucla.si.controlador.gc.maestrico.ocupacion;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.A;
import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Ocupacion;
import ucla.si.servicio.ServicioOcupacion;

public class ControladorOcupacionIncluir extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	ServicioOcupacion servicioOcupacion;

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
			Ocupacion ocupacion = new Ocupacion(descripcion, "Activo", new Date(), null);
			if (servicioOcupacion.incluirOcupacion(ocupacion)) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "pc/ocupacion/frm-ocupacion-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "pc/ocupacion/frm-ocupacion-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbOcupacion")
	public void ocupacion() {
		String dir = "pc/ocupacion/frm-ocupacion-catalogo.zul";
		clearDivApp(dir);
	}

}
