package ucla.si.controlador.gc.maestrico.profesion;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Profesion;
import ucla.si.servicio.ServicioProfesion;

public class ControladorProfesionIncluir extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	ServicioProfesion servicioProfesion;

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
			Profesion profesion = new Profesion(descripcion, "Activo", new Date(), null);
			if (servicioProfesion.incluirProfesion(profesion)) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "pc/profesion/frm-profesion-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "pc/profesion/frm-profesion-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbProfesion")
	public void profesion() {
		String dir = "pc/profesion/frm-profesion-catalogo.zul";
		clearDivApp(dir);
	}

}
