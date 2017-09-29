package ucla.si.controlador.gc.maestrico.refrigerante;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Refrigerante;
import ucla.si.modelo.TipoRefrigerante;
import ucla.si.servicio.ServicioRefrigerante;
import ucla.si.servicio.ServicioTipoRefrigerante;

public class ControladorRefrigeranteIncluir extends ControladorInicio {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoRefrigerante servicioTipoRefrigerante;

	@WireVariable
	private ServicioRefrigerante servicioRefrigerante;

	@Wire
	private Textbox txtDescripcion;

	@Wire
	private Combobox cmbTipoRefrigerante;

	@Override
	protected void inicializar() {
		ListModelList<TipoRefrigerante> modeloTipoRefrigerantes = new ListModelList<TipoRefrigerante>(
				servicioTipoRefrigerante.listarTipoRefrigerantes());
		if (modeloTipoRefrigerantes.getSize() == 0) {
			Messagebox.show("Debe Incluir un Tipo de Refrigerante primero", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/tipoRefrigerante/frm-tipoRefrigerante-incluir.zul";
			clearDivApp(dir);
		} else {
			modeloTipoRefrigerantes.setMultiple(false);
			cmbTipoRefrigerante.setModel(modeloTipoRefrigerantes);
			cmbTipoRefrigerante.setMultiline(false);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "" || cmbTipoRefrigerante.getSelectedIndex() == -1) {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			String descripcion = txtDescripcion.getValue().trim().toUpperCase();
			Refrigerante refrigerante = new Refrigerante(descripcion, "Activo", new Date(), null);
			TipoRefrigerante tipoRefrigerante = (TipoRefrigerante) cmbTipoRefrigerante.getSelectedItem().getValue();
			refrigerante.setTipoRefrigerante(tipoRefrigerante);
			if (servicioRefrigerante.incluirRefrigerante(refrigerante)) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/refrigerante/frm-refrigerante-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/refrigerante/frm-refrigerante-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbRefrigerante")
	public void refrigerante() {
		String dir = "gc/refrigerante/frm-refrigerante-catalogo.zul";
		clearDivApp(dir);
	}

}
