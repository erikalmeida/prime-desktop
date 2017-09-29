package ucla.si.controlador.gc.maestrico.combustible;

import java.util.Date;

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

public class ControladorCombustibleIncluir extends ControladorInicio {
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
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "" || cmbTipoCombustible.getSelectedIndex() == -1) {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			String descripcion = txtDescripcion.getValue().trim().toUpperCase();
			Combustible Combustible = new Combustible(descripcion, "Activo", new Date(), null);
			TipoCombustible tipoCombustible = (TipoCombustible) cmbTipoCombustible.getSelectedItem().getValue();
			Combustible.setTipoCombustible(tipoCombustible);
			if (servicioCombustible.incluirCombustible(Combustible)) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
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
