package ucla.si.controlador.gc.maestrico.profesion;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Profesion;
import ucla.si.servicio.ServicioProfesion;

public class ControladorProfesionEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	ServicioProfesion servicioProfesion;

	@Wire
	private Textbox txtDescripcion;

	Profesion profesion;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
		profesion = (Profesion) getAtributo("profesion");
		cargarDatos(profesion);
	}

	public void cargarDatos(Profesion profesion) {
		if (profesion != null) {
			txtDescripcion.setValue(profesion.getDescripcion().trim());
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "pc/deporte/frm-deporte-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			profesion.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			if (servicioProfesion.editarProfesion(profesion)) {
				Messagebox.show("Edición Exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "pc/profesion/frm-profesion-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al Guardar", "Error", Messagebox.OK, Messagebox.ERROR);
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
