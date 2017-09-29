package ucla.si.controlador.gc.maestrico.anomalia;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Anomalia;
import ucla.si.servicio.ServicioAnomalia;

public class ControladorAnomaliaIncluir extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioAnomalia servicioAnomalia;

	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			String descripcion = txtDescripcion.getValue().trim().toUpperCase();
			Anomalia anomalia = new Anomalia(descripcion, "Activo", new Date(), null);
			if (servicioAnomalia.incluirAnomalia(anomalia)) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
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
