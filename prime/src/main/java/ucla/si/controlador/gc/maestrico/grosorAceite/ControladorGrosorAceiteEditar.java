package ucla.si.controlador.gc.maestrico.grosorAceite;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.GrosorAceite;
import ucla.si.servicio.ServicioGrosorAceite;

public class ControladorGrosorAceiteEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioGrosorAceite servicioGrosorAceite;

	@Wire
	private Textbox txtDescripcion;

	private GrosorAceite grosorAceite;

	@Override
	protected void inicializar() {
		grosorAceite = (GrosorAceite) getAtributo("grosorAceite");
		cargarDatos(grosorAceite);
	}

	public void cargarDatos(GrosorAceite grosorAceite) {
		if (grosorAceite != null) {
			txtDescripcion.setValue(grosorAceite.getDescripcion().trim());
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/grosorAceite/frm-grosorAceite-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			grosorAceite.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			if (servicioGrosorAceite.editarGrosorAceite(grosorAceite)) {
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/grosorAceite/frm-grosorAceite-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/grosorAceite/frm-grosorAceite-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbGrosorAceite")
	public void grosorAceite() {
		String dir = "gc/grosorAceite/frm-grosorAceite-catalogo.zul";
		clearDivApp(dir);
	}

}
