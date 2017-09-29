package ucla.si.controlador.gc.maestrico.tipoRefrigerante;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoRefrigerante;
import ucla.si.servicio.ServicioTipoRefrigerante;

public class ControladorTipoRefrigeranteEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoRefrigerante servicioTipoRefrigerante;

	@Wire
	private Textbox txtDescripcion;

	private TipoRefrigerante tipoRefrigerante;

	@Override
	protected void inicializar() {
		tipoRefrigerante = (TipoRefrigerante) getAtributo("tipoRefrigerante");
		cargarDatos(tipoRefrigerante);
	}

	public void cargarDatos(TipoRefrigerante tipoRefrigerante) {
		if (tipoRefrigerante != null) {
			txtDescripcion.setValue(tipoRefrigerante.getDescripcion().trim());
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/tipoRefrigerante/frm-tipoRefrigerante-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			tipoRefrigerante.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			if (servicioTipoRefrigerante.editarTipoRefrigerante(tipoRefrigerante)) {
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/tipoRefrigerante/frm-tipoRefrigerante-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/tipoRefrigerante/frm-tipoRefrigerante-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbTipoRefrigerante")
	public void tipoRefrigerante() {
		String dir = "gc/tipoRefrigerante/frm-tipoRefrigerante-catalogo.zul";
		clearDivApp(dir);
	}

}
