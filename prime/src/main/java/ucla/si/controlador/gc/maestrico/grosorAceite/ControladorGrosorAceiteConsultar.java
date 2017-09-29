package ucla.si.controlador.gc.maestrico.grosorAceite;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.GrosorAceite;
import ucla.si.servicio.ServicioGrosorAceite;

public class ControladorGrosorAceiteConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioGrosorAceite servicioGrosorAceite;

	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		GrosorAceite grosorAceite = (GrosorAceite) getAtributo("grosorAceite");
		cargarDatos(grosorAceite);
	}

	public void cargarDatos(GrosorAceite grosorAceite) {
		if (grosorAceite != null) {
			txtDescripcion.setValue(grosorAceite.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
		}
	}

	@Listen("onClick =#btnAceptar ; onClick =#btnCancelar")
	public void aceptar() {
		String dir = "gc/grosorAceite/frm-grosorAceite-catalogo.zul";
		clearDivApp(dir);
	}


	@Listen("onClick =#breadcrumbGrosorAceite")
	public void grosorAceite() {
		String dir = "gc/grosorAceite/frm-grosorAceite-catalogo.zul";
		clearDivApp(dir);
	}

}
