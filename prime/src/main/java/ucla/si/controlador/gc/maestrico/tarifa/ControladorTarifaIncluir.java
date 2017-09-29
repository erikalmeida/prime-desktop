package ucla.si.controlador.gc.maestrico.tarifa;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Tarifa;
import ucla.si.servicio.ServicioTarifa;

public class ControladorTarifaIncluir extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioTarifa servicioTarifa;

	@Wire
	Textbox txtDescripcion;

	@Wire
	Doublebox dbMonto;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			String descripcion = txtDescripcion.getValue().trim();
			Double monto = dbMonto.getValue();
			String estatus = "Activo";
			Tarifa tarifa = new Tarifa(descripcion, estatus, new Date(), null, monto);
			if (servicioTarifa.incluirTarifa(tarifa)) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/tarifa/frm-tarifa-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/tarifa/frm-tarifa-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbTarifa")
	public void tarifa() {
		String dir = "gc/tarifa/frm-tarifa-catalogo.zul";
		clearDivApp(dir);
	}
}
