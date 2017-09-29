package ucla.si.controlador.gc.maestrico.tipoCombustible;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoCombustible;
import ucla.si.servicio.ServicioTipoCombustible;

public class ControladorTipoCombustibleEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoCombustible servicioTipoCombustible;

	@Wire
	private Textbox txtDescripcion;

	private TipoCombustible tipoCombustible;

	@Override
	protected void inicializar() {
		tipoCombustible = (TipoCombustible) getAtributo("tipoCombustible");
		cargarDatos(tipoCombustible);
	}

	public void cargarDatos(TipoCombustible tipoCombustible) {
		if (tipoCombustible != null) {
			txtDescripcion.setValue(tipoCombustible.getDescripcion().trim());
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/tipoCombustible/frm-tipoCombustible-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			tipoCombustible.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			if (servicioTipoCombustible.editarTipoCombustible(tipoCombustible)) {
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/tipoCombustible/frm-tipoCombustible-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/tipoCombustible/frm-tipoCombustible-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbTipoCombustible")
	public void tipoCombustible() {
		String dir = "gc/tipoCombustible/frm-tipoCombustible-catalogo.zul";
		clearDivApp(dir);
	}

}
