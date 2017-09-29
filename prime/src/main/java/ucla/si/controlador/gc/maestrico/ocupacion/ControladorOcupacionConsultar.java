package ucla.si.controlador.gc.maestrico.ocupacion;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Ocupacion;
import ucla.si.servicio.ServicioOcupacion;

public class ControladorOcupacionConsultar extends ControladorInicio {

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
		Ocupacion ocupacion = (Ocupacion) getAtributo("ocupacion");
		cargarDatos(ocupacion);
	}

	public void cargarDatos(Ocupacion ocupacion) {
		if (ocupacion != null) {
			txtDescripcion.setValue(ocupacion.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
			txtDescripcion.setReadonly(true);
		}
	}

	@Listen("onClick =#btnAceptar ; onClick =#btnCancelar")
	public void aceptar() {
		String dir = "pc/ocupacion/frm-ocupacion-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbOcupacion")
	public void ocupacion() {
		String dir = "pc/ocupacion/frm-ocupacion-catalogo.zul";
		clearDivApp(dir);
	}

}
