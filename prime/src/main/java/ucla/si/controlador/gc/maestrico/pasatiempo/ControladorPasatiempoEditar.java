package ucla.si.controlador.gc.maestrico.pasatiempo;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Pasatiempo;
import ucla.si.servicio.ServicioPasatiempo;

public class ControladorPasatiempoEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	ServicioPasatiempo servicioPasatiempo;

	@Wire
	private Textbox txtDescripcion;

	Pasatiempo pasatiempo;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
		pasatiempo = (Pasatiempo) getAtributo("pasatiempo");
		cargarDatos(pasatiempo);
	}

	public void cargarDatos(Pasatiempo pasatiempo) {
		if (pasatiempo != null) {
			txtDescripcion.setValue(pasatiempo.getDescripcion().trim());
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "pc/pasatiempo/frm-pasatiempo-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			pasatiempo.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			if (servicioPasatiempo.editarPasatiempo(pasatiempo)) {
				Messagebox.show("Edicion Exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "pc/pasatiempo/frm-pasatiempo-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al Guardar", "Error", Messagebox.OK, Messagebox.ERROR);
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
