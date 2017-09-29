package ucla.si.controlador.gc.funcion;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Clase;
import ucla.si.modelo.Funcion;
import ucla.si.modelo.Sistema;
import ucla.si.servicio.ServicioClase;
import ucla.si.servicio.ServicioFuncion;

public class ControladorFuncionConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioFuncion servicioFuncion;

	@Wire
	private Textbox txtClave, txtNombre, txtDescripcion, txtIcono, txtUrl;

	@Wire
	private Combobox cmbFuncionPadre;

	private Sistema sistema;

	@Override
	protected void inicializar() {
		Funcion funcion = (Funcion) getAtributo("funcion");
		cargarDatos(funcion);
	}

	public void cargarDatos(Funcion funcion) {
		if (funcion != null) {
			txtClave.setValue(funcion.getClave());
			txtNombre.setValue(funcion.getNombre());
			txtDescripcion.setValue(funcion.getDescripcion());
			txtIcono.setValue(funcion.getIcono());
			txtUrl.setValue(funcion.getUrl());;
			cmbFuncionPadre.setValue(funcion.getFuncionPadre() != null ? funcion.getFuncionPadre().getClave() : "");			
			txtClave.setDisabled(true);
			txtNombre.setDisabled(true);
			txtDescripcion.setDisabled(true);
			txtIcono.setDisabled(true);
			txtUrl.setDisabled(true);
			cmbFuncionPadre.setDisabled(true);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/funcion/frm-funcion-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbFuncion")
	public void funcion() {
		String dir = "gc/funcion/frm-funcion-catalogo.zul";
		clearDivApp(dir);
	}

}
