package ucla.si.controlador.gc.maestrico.motivo;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Motivo;
import ucla.si.servicio.ServicioMotivo;

public class ControladorMotivoConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioMotivo servicioMotivo;

	@Wire
	Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		Motivo motivo = (Motivo) getAtributo("motivo");
		cargarDatos(motivo);
	}

	public void cargarDatos(Motivo motivo) {
		if (motivo != null) {

			txtDescripcion.setValue(motivo.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
			txtDescripcion.setReadonly(true);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		String dir = "gc/motivo/frm-motivo-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/motivo/frm-motivo-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbMotivo")
	public void motivo() {
		String dir = "gc/motivo/frm-motivo-catalogo.zul";
		clearDivApp(dir);
	}

}
