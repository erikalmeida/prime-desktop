package ucla.si.controlador.gps.reclamo;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Garantia;
import ucla.si.modelo.OrdenEntrega;
import ucla.si.modelo.Reclamo;
import ucla.si.modelo.TipoReclamo;
import ucla.si.servicio.ServicioGarantia;
import ucla.si.servicio.ServicioOrdenEntrega;
import ucla.si.servicio.ServicioReclamo;
import ucla.si.servicio.ServicioTipoGarantia;
import ucla.si.servicio.ServicioTipoReclamo;

public class ControladorReclamoIncluir extends ControladorInicio {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTipoReclamo servicioTipoReclamo;

	@WireVariable
	private ServicioOrdenEntrega servicioOrdenesEntrega;

	@WireVariable
	private ServicioGarantia servicioGarantia;

	@WireVariable
	private ServicioTipoGarantia servicioTipoGarantia;

	@WireVariable
	private ServicioReclamo servicioReclamo;

	@Wire
	private Textbox txtDescripcion;

	@Wire
	private Combobox cmbTipoReclamo;

	// Info de Garantia y Orden de entrega
	@Wire
	private Textbox txtFechaOrdenEntrega;

	@Wire
	private Textbox txtDescOrdenEntrega;

	@Wire
	private Textbox txtFechaGarantia;

	@Wire
	private Textbox txtDescGarantia;

	@Wire
	private Textbox txtTiempoGarantia;
	
	private OrdenEntrega ordenEntrega = null;

	@Override
	protected void inicializar() {
		ListModelList<TipoReclamo> modeloTipoReclamos = new ListModelList<TipoReclamo>(
				servicioTipoReclamo.listarTipoReclamos());
		modeloTipoReclamos.setMultiple(false);
		cmbTipoReclamo.setModel(modeloTipoReclamos);
		cmbTipoReclamo.setMultiline(false);

		ordenEntrega = (OrdenEntrega) getAtributo("ordenEntrega");
		cargarDatos(ordenEntrega);
	}

	public void cargarDatos(OrdenEntrega ordenEntrega) {
		// Datos Orden de Entrega
		String fechaOrdenEntrega = ordenEntrega.getFechaCreacion().toString();
		String descOrdenEntrega = ordenEntrega.getDescripcion().toString();
		txtFechaOrdenEntrega.setValue(fechaOrdenEntrega);
		txtDescOrdenEntrega.setValue(descOrdenEntrega);

		// Datos Garantia
		String fechaGarantia = ordenEntrega.getGarantia().getFechaCreacion().toString();
		String descGarantia = ordenEntrega.getGarantia().getDescripcion();
		String tiempoVigencia = ordenEntrega.getGarantia().getTiempoVigencia() + " Dias";
		txtFechaGarantia.setValue(fechaGarantia.trim());
		txtDescGarantia.setValue(descGarantia.trim());
		txtTiempoGarantia.setValue(tiempoVigencia.trim());

		// desabilitar
		txtFechaOrdenEntrega.setDisabled(true);
		txtDescOrdenEntrega.setDisabled(true);
		txtFechaGarantia.setDisabled(true);
		txtDescGarantia.setDisabled(true);
		txtTiempoGarantia.setDisabled(true);
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "" || cmbTipoReclamo.getSelectedIndex() == -1) {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			String descripcion = txtDescripcion.getValue().trim().toUpperCase();
			Reclamo reclamo = new Reclamo(descripcion, "Estado1", "Activo", new Date(), null);
			TipoReclamo tipoReclamo = (TipoReclamo) cmbTipoReclamo.getSelectedItem().getValue();
			reclamo.setTipoReclamo(tipoReclamo);
			reclamo.setOrdenEntrega(ordenEntrega);
			reclamo.setMotivo(null);
			if (servicioReclamo.incluirReclamo(reclamo)) {
				Messagebox.show("Inclusion exitosa", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gps/reclamo/frm-reclamo-ordenesEntrega.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gps/reclamo/frm-reclamo-ordenesEntrega.zul";
		clearDivApp(dir);
	}

}
