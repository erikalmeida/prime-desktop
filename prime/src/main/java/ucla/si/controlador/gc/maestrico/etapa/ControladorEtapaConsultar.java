package ucla.si.controlador.gc.maestrico.etapa;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Etapa;
import ucla.si.servicio.ServicioEtapa;

public class ControladorEtapaConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioEtapa servicioEtapa;

	@Wire
	Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		Etapa etapa = (Etapa) getAtributo("etapa");
		cargarDatos(etapa);
	}

	public void cargarDatos(Etapa etapa) {
		if (etapa != null) {

			txtDescripcion.setValue(etapa.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
			txtDescripcion.setReadonly(true);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/etapa/frm-etapa-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbEtapa")
	public void etapa() {
		String dir = "gc/etapa/frm-etapa-catalogo.zul";
		clearDivApp(dir);
	}

}
