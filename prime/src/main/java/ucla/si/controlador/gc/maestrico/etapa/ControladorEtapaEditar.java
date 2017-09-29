package ucla.si.controlador.gc.maestrico.etapa;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Etapa;
import ucla.si.servicio.ServicioEtapa;

public class ControladorEtapaEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioEtapa servicioEtapa;

	@Wire
	Textbox txtDescripcion;

	/*
	 * @Wire Combobox cmbEstatus;
	 */

	private Etapa etapa;

	@Override
	protected void inicializar() {
		etapa = (Etapa) getAtributo("etapa");
		cargarDatos(etapa);
	}

	public void cargarDatos(Etapa etapa) {
		if (etapa != null) {
			if (etapa.getEstatus().equalsIgnoreCase("Activo")) {
				txtDescripcion.setValue(etapa.getDescripcion().trim());
			} else {
				txtDescripcion.setValue(etapa.getDescripcion().trim());
				/* cmbEstatus.setValue(grupo.getEstatus()); */
			}

		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/etapa/frm-etapa-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {

			etapa.setDescripcion(txtDescripcion.getValue().trim());
			if (servicioEtapa.editarEtapa(etapa)) {
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/etapa/frm-etapa-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/etapa/frm-etapa-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbEtapa")
	public void etapa() {
		String dir = "gc/etapa/frm-etapa-catalogo.zul";
		clearDivApp(dir);
	}

}
