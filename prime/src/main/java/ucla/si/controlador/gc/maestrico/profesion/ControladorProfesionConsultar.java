package ucla.si.controlador.gc.maestrico.profesion;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Profesion;
import ucla.si.servicio.ServicioProfesion;

public class ControladorProfesionConsultar extends ControladorInicio {

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
		Profesion profesion = (Profesion) getAtributo("profesion");
		cargarDatos(profesion);
	}

	public void cargarDatos(Profesion profesion) {
		if (profesion != null) {
			txtDescripcion.setValue(profesion.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
			txtDescripcion.setReadonly(true);
		}
	}

	@Listen("onClick =#btnAceptar ; onClick =#btnCancelar")
	public void aceptar() {
		String dir = "pc/profesion/frm-profesion-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbProfesion")
	public void profesion() {
		String dir = "pc/profesion/frm-profesion-catalogo.zul";
		clearDivApp(dir);
	}

}
