package ucla.si.controlador.gc.maestrico.tipoEventualidad;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoEventualidad;
import ucla.si.servicio.ServicioTipoEventualidad;

public class ControladorTipoEventualidadEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	ServicioTipoEventualidad servicioTipoEventualidad;

	@Wire
	private Textbox txtDescripcion;

	TipoEventualidad tipoEventualidad;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
		tipoEventualidad = (TipoEventualidad) getAtributo("tipoEventualidad");
		cargarDatos(tipoEventualidad);
	}

	public void cargarDatos(TipoEventualidad tipoEventualidad) {
		if (tipoEventualidad != null) {
			txtDescripcion.setValue(tipoEventualidad.getDescripcion().trim());
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "pc/tipoEventualidad/frm-tipoEventualidad-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			tipoEventualidad.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			if (servicioTipoEventualidad.editarTipoEventualidad(tipoEventualidad)) {
				Messagebox.show("Edicion Exitosa", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "pc/tipoEventualidad/frm-tipoEventualidad-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al Guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "pc/tipoEventualidad/frm-tipoEventualidad-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbTipoEventualidad")
	public void tipoEventualidad() {
		String dir = "pc/tipoEventualidad/frm-tipoEventualidad-catalogo.zul";
		clearDivApp(dir);
	}

}
