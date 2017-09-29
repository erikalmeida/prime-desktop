package ucla.si.controlador.gc.maestrico.horario;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Horario;
import ucla.si.modelo.Horario;
import ucla.si.servicio.ServicioHorario;
import ucla.si.servicio.ServicioHorario;

public class ControladorHorarioConsultar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioHorario servicioHorario;

	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		Horario horario = (Horario) getAtributo("horario");
		cargarDatos(horario);
	}

	public void cargarDatos(Horario horario) {
		if (horario != null) {
			txtDescripcion.setValue(horario.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/horario/frm-horario-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbHorario")
	public void horario() {
		String dir = "gc/horario/frm-horario-catalogo.zul";
		clearDivApp(dir);
	}

}
