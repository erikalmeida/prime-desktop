package ucla.si.controlador.gc.maestrico.anomalia;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Anomalia;
import ucla.si.servicio.ServicioAnomalia;

public class ControladorAnomaliaEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioAnomalia servicioAnomalia;

	@Wire
	private Textbox txtDescripcion;

	private Anomalia anomalia;

	@Override
	protected void inicializar() {
		anomalia = (Anomalia) getAtributo("anomalia");
		cargarDatos(anomalia);
	}

	public void cargarDatos(Anomalia anomalia) {
		if (anomalia != null) {
			txtDescripcion.setValue(anomalia.getDescripcion().trim());
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/anomalia/frm-anomalia-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			anomalia.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			if (servicioAnomalia.editarAnomalia(anomalia)) {
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/anomalia/frm-anomalia-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/anomalia/frm-anomalia-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbAnomalia")
	public void anomalia() {
		String dir = "gc/anomalia/frm-anomalia-catalogo.zul";
		clearDivApp(dir);
	}

}
