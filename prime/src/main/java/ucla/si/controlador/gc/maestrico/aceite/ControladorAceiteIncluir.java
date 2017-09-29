package ucla.si.controlador.gc.maestrico.aceite;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Aceite;
import ucla.si.modelo.GrosorAceite;
import ucla.si.modelo.TipoAceite;
import ucla.si.servicio.ServicioAceite;
import ucla.si.servicio.ServicioGrosorAceite;
import ucla.si.servicio.ServicioTipoAceite;

public class ControladorAceiteIncluir extends ControladorInicio {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoAceite servicioTipoAceite;

	@WireVariable
	private ServicioAceite servicioAceite;

	@WireVariable
	private ServicioGrosorAceite servicioGrosorAceite;

	@Wire
	private Textbox txtDescripcion;

	@Wire
	private Combobox cmbTipoAceite;

	@Wire
	private Combobox cmbGrosorAceite;

	@Override
	protected void inicializar() {
		ListModelList<TipoAceite> modeloTipoAceites = new ListModelList<TipoAceite>(
				servicioTipoAceite.listarTipoAceites());
		ListModelList<GrosorAceite> modeloGrosorAceites = new ListModelList<GrosorAceite>(
				servicioGrosorAceite.listarGrosorAceites());

		if (modeloTipoAceites.getSize() == 0) {
			Messagebox.show("Debe Incluir un Tipo de Aceite primero", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/tipoAceite/frm-tipoAceite-incluir.zul";
			clearDivApp(dir);
		}

		else if (modeloGrosorAceites.getSize() == 0) {
			Messagebox.show("Debe Incluir un Grosor de Aceite primero", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/grosorAceite/frm-grosorAceite-incluir.zul";
			clearDivApp(dir);
		}

		else {
			modeloTipoAceites.setMultiple(false);
			modeloGrosorAceites.setMultiple(false);
			cmbTipoAceite.setModel(modeloTipoAceites);
			cmbGrosorAceite.setModel(modeloGrosorAceites);
			cmbTipoAceite.setMultiline(false);
			cmbGrosorAceite.setMultiline(false);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "" || cmbTipoAceite.getSelectedIndex() == -1
				|| cmbGrosorAceite.getSelectedIndex() == -1) {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			String descripcion = txtDescripcion.getValue().trim().toUpperCase();
			Aceite aceite = new Aceite(descripcion, "Activo", new Date(), null);
			TipoAceite tipoAceite = (TipoAceite) cmbTipoAceite.getSelectedItem().getValue();
			GrosorAceite grosorAceite = (GrosorAceite) cmbGrosorAceite.getSelectedItem().getValue();
			aceite.setTipoAceite(tipoAceite);
			aceite.setGrosorAceite(grosorAceite);
			if (servicioAceite.incluirAceite(aceite)) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/aceite/frm-aceite-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/aceite/frm-aceite-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbAceite")
	public void aceite() {
		String dir = "gc/aceite/frm-aceite-catalogo.zul";
		clearDivApp(dir);
	}

}
