package ucla.si.controlador.gc.maestrico.estado;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Estado;
import ucla.si.servicio.ServicioEstado;

public class ControladorEstadoConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioEstado servicioEstado;

	@Wire
	Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		Estado estado = (Estado) getAtributo("estado");
		cargarDatos(estado);
	}

	public void cargarDatos(Estado estado) {
		if (estado != null) {

			txtDescripcion.setValue(estado.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
			txtDescripcion.setReadonly(true);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		String dir = "gc/estado/frm-estado-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/estado/frm-estado-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbEstado")
	public void estado() {
		String dir = "gc/estado/frm-estado-catalogo.zul";
		clearDivApp(dir);
	}

}
