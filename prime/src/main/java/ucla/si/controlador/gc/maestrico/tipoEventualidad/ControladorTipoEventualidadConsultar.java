package ucla.si.controlador.gc.maestrico.tipoEventualidad;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoEventualidad;
import ucla.si.servicio.ServicioTipoEventualidad;

public class ControladorTipoEventualidadConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	ServicioTipoEventualidad servicioTipoEventualidad;

	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
		TipoEventualidad tipoEventualidad = (TipoEventualidad) getAtributo("tipoEventualidad");
		cargarDatos(tipoEventualidad);
	}

	public void cargarDatos(TipoEventualidad tipoEventualidad) {
		if (tipoEventualidad != null) {
			txtDescripcion.setValue(tipoEventualidad.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
			txtDescripcion.setReadonly(true);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "pc/tipoEventualidad/frm-tipoEventualidad-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbTipoEventualidad")
	public void tipoEventualidad() {
		String dir = "pc/tipoEventualidad/frm-tipoEventualidad-catalogo.zul";
		clearDivApp(dir);
	}

}
