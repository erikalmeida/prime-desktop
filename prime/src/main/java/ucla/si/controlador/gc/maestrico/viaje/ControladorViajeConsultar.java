package ucla.si.controlador.gc.maestrico.viaje;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Viaje;
import ucla.si.servicio.ServicioViaje;

public class ControladorViajeConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	ServicioViaje servicioOcupacion;

	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
		Viaje viaje = (Viaje) getAtributo("viaje");
		cargarDatos(viaje);
	}

	public void cargarDatos(Viaje viaje) {
		if (viaje != null) {
			txtDescripcion.setValue(viaje.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
			txtDescripcion.setReadonly(true);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		String dir = "pc/viaje/frm-viaje-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "pc/viaje/frm-viaje-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbViaje")
	public void viaje() {
		String dir = "pc/viaje/frm-viaje-catalogo.zul";
		clearDivApp(dir);
	}

}
