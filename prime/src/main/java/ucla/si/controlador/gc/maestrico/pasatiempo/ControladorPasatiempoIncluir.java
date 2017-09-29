package ucla.si.controlador.gc.maestrico.pasatiempo;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Pasatiempo;
import ucla.si.servicio.ServicioPasatiempo;

public class ControladorPasatiempoIncluir extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	ServicioPasatiempo servicioPasatiempo;

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
			Pasatiempo pasatiempo = new Pasatiempo(descripcion, "Activo", new Date(), null);
			if (servicioPasatiempo.incluirPasatiempo(pasatiempo)) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "pc/pasatiempo/frm-pasatiempo-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "pc/pasatiempo/frm-pasatiempo-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbPasatiempo")
	public void pasatiempo() {
		String dir = "pc/pasatiempo/frm-pasatiempo-catalogo.zul";
		clearDivApp(dir);
	}

}
