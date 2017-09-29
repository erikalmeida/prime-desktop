package ucla.si.controlador.gc.maestrico.tipoAceite;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoAceite;
import ucla.si.servicio.ServicioTipoAceite;

public class ControladorTipoAceiteEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoAceite servicioTipoAceite;

	@Wire
	private Textbox txtDescripcion;

	private TipoAceite tipoAceite;

	@Override
	protected void inicializar() {
		tipoAceite = (TipoAceite) getAtributo("tipoAceite");
		cargarDatos(tipoAceite);
	}

	public void cargarDatos(TipoAceite tipoAceite) {
		if (tipoAceite != null) {
			txtDescripcion.setValue(tipoAceite.getDescripcion().trim());
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/tipoAceite/frm-tipoAceite-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			tipoAceite.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			if (servicioTipoAceite.editarTipoAceite(tipoAceite)) {
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/tipoAceite/frm-tipoAceite-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/tipoAceite/frm-tipoAceite-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbTipoAceite")
	public void tipoAceite() {
		String dir = "gc/tipoAceite/frm-tipoAceite-catalogo.zul";
		clearDivApp(dir);
	}

}
