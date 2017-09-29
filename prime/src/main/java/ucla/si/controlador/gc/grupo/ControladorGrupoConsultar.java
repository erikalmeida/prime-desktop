package ucla.si.controlador.gc.grupo;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Grupo;
import ucla.si.servicio.ServicioGrupo;

public class ControladorGrupoConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioGrupo servicioGrupo;

	@Wire
	Textbox txtNombre, txtDescripcion;

	@Override
	protected void inicializar() {
		Grupo grupo = (Grupo) getAtributo("grupo");
		cargarDatos(grupo);
	}

	public void cargarDatos(Grupo grupo) {
		if (grupo != null) {
			txtNombre.setValue(grupo.getNombre().trim());
			txtDescripcion.setValue(grupo.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
			txtNombre.setDisabled(true);
			txtNombre.setReadonly(true);
			txtDescripcion.setReadonly(true);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/grupo/frm-grupo-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbGrupo")
	public void grupo() {
		String dir = "gc/grupo/frm-grupo-catalogo.zul";
		clearDivApp(dir);
	}

}
