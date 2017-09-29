package ucla.si.controlador.gc.maestrico.pasatiempo;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Pasatiempo;
import ucla.si.servicio.ServicioPasatiempo;

public class ControladorPasatiempoConsultar extends ControladorInicio {

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
		Pasatiempo pasatiempo = (Pasatiempo) getAtributo("pasatiempo");
		cargarDatos(pasatiempo);
	}

	public void cargarDatos(Pasatiempo pasatiempo) {
		if (pasatiempo != null) {
			txtDescripcion.setValue(pasatiempo.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
			txtDescripcion.setReadonly(true);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		String dir = "pc/pasatiempo/frm-pasatiempo-catalogo.zul";
		clearDivApp(dir);
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
