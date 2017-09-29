package ucla.si.controlador.gc.maestrico.anomalia;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Anomalia;
import ucla.si.servicio.ServicioAnomalia;

public class ControladorAnomaliaConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioAnomalia servicioAnomalia;

	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		Anomalia anomalia = (Anomalia) getAtributo("anomalia");
		cargarDatos(anomalia);
	}

	public void cargarDatos(Anomalia anomalia) {
		if (anomalia != null) {
			txtDescripcion.setValue(anomalia.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/anomalia/frm-anomalia-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbAnomalia")
	public void anomalia() {
		String dir = "gc/anomalia/frm-anomalia-catalogo.zul";
		clearDivApp(dir);
	}

}
