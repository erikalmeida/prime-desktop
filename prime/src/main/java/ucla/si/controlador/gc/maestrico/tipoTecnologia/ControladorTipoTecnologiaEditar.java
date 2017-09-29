package ucla.si.controlador.gc.maestrico.tipoTecnologia;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoTecnologia;
import ucla.si.servicio.ServicioTipoTecnologia;

public class ControladorTipoTecnologiaEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoTecnologia servicioTipoTecnologia;

	@Wire
	Textbox txtDescripcion;

	private TipoTecnologia tipoTecnologia;

	@Override
	protected void inicializar() {
		tipoTecnologia = (TipoTecnologia) getAtributo("tipoTecnologia");
		cargarDatos(tipoTecnologia);
	}

	public void cargarDatos(TipoTecnologia tipoTecnologia) {
		if (tipoTecnologia != null) {
			if (tipoTecnologia.getEstatus().equalsIgnoreCase("Activo")) {
				txtDescripcion.setValue(tipoTecnologia.getDescripcion().trim());
			} else {
				txtDescripcion.setValue(tipoTecnologia.getDescripcion().trim());

			}

		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/tipoTecnologia/frm-tipoTecnologia-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {

			tipoTecnologia.setDescripcion(txtDescripcion.getValue().trim());
			if (servicioTipoTecnologia.editarTipoTecnologia(tipoTecnologia)) {
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/tipoTecnologia/frm-tipoTecnologia-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/tipoTecnologia/frm-tipoTecnologia-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbTipoTecnologia")
	public void tipoTecnologia() {
		String dir = "gc/tipoTecnologia/frm-tipoTecnologia-catalogo.zul";
		clearDivApp(dir);
	}

}
