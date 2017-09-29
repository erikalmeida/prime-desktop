package ucla.si.controlador.gc.maestrico.ocupacion;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Ocupacion;
import ucla.si.servicio.ServicioOcupacion;

public class ControladorOcupacionEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	ServicioOcupacion servicioOcupacion;

	@Wire
	private Textbox txtDescripcion;

	Ocupacion ocupacion;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
		ocupacion = (Ocupacion) getAtributo("ocupacion");
		cargarDatos(ocupacion);
	}

	public void cargarDatos(Ocupacion ocupacion) {
		if (ocupacion != null) {
			txtDescripcion.setValue(ocupacion.getDescripcion().trim());
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "pc/ocupacion/frm-ocupacion-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			ocupacion.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			if (servicioOcupacion.editarOcupacion(ocupacion)) {
				Messagebox.show("Edición Exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "pc/ocupacion/frm-ocupacion-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al Guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "pc/ocupacion/frm-ocupacion-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbOcupacion")
	public void ocupacion() {
		String dir = "pc/ocupacion/frm-ocupacion-catalogo.zul";
		clearDivApp(dir);
	}

}
