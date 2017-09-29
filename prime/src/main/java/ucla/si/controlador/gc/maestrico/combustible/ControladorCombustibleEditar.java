package ucla.si.controlador.gc.maestrico.combustible;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Combustible;
import ucla.si.modelo.TipoCombustible;
import ucla.si.servicio.ServicioCombustible;
import ucla.si.servicio.ServicioTipoCombustible;

public class ControladorCombustibleEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoCombustible servicioTipoCombustible;

	@WireVariable
	private ServicioCombustible servicioCombustible;

	@Wire
	private Textbox txtDescripcion;

	@Wire
	private Combobox cmbTipoCombustible;

	private Combustible Combustible;

	@Override
	protected void inicializar() {
		ListModelList<TipoCombustible> modeloTipoCombustibles = new ListModelList<TipoCombustible>(
				servicioTipoCombustible.listarTipoCombustibles());
		if (modeloTipoCombustibles.getSize() == 0) {
			Messagebox.show("Debe Incluir un Tipo de Combustible primero", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/tipoCombustible/frm-tipoCombustible-incluir.zul";
			clearDivApp(dir);
		} else {
			modeloTipoCombustibles.setMultiple(false);
			cmbTipoCombustible.setModel(modeloTipoCombustibles);
			cmbTipoCombustible.setMultiline(false);
			Combustible = (Combustible) getAtributo("Combustible");
			cargarDatos(Combustible);
		}
	}

	public void cargarDatos(Combustible Combustible) {
		if (Combustible != null) {
			txtDescripcion.setValue(Combustible.getDescripcion().trim());
			cmbTipoCombustible.setValue(Combustible.getTipoCombustible().getDescripcion());
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/combustible/frm-combustible-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "" || cmbTipoCombustible.getSelectedIndex() == -1) {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			Combustible.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			Combustible.setTipoCombustible((TipoCombustible) cmbTipoCombustible.getSelectedItem().getValue());
			if (servicioCombustible.editarCombustible(Combustible)) {
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/combustible/frm-combustible-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/combustible/frm-combustible-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbCombustible")
	public void combustible() {
		String dir = "gc/combustible/frm-combustible-catalogo.zul";
		clearDivApp(dir);
	}
}