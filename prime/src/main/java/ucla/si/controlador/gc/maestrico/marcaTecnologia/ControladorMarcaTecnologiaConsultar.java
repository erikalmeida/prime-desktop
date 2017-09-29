package ucla.si.controlador.gc.maestrico.marcaTecnologia;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.MarcaTecnologia;
import ucla.si.servicio.ServicioMarcaTecnologia;

public class ControladorMarcaTecnologiaConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioMarcaTecnologia servicioMarcaTecnologia;

	@Wire
	Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		MarcaTecnologia marcaTecnologia = (MarcaTecnologia) getAtributo("marcaTecnologia");
		cargarDatos(marcaTecnologia);
	}

	public void cargarDatos(MarcaTecnologia marcaTecnologia) {
		if (marcaTecnologia != null) {

			txtDescripcion.setValue(marcaTecnologia.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
			txtDescripcion.setReadonly(true);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/marcaTecnologia/frm-marcaTecnologia-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbMarcaTecnologia")
	public void marcaTecnologia() {
		String dir = "gc/marcaTecnologia/frm-marcaTecnologia-catalogo.zul";
		clearDivApp(dir);
	}

}
