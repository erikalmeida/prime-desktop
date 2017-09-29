package ucla.si.controlador.gc.maestrico.etapa;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Etapa;
import ucla.si.servicio.ServicioEtapa;

public class ControladorEtapaIncluir extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioEtapa servicioEtapa;

	@Wire
	Textbox txtDescripcion;

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
			String estatus = "Activo";
			Etapa etapa = new Etapa(descripcion, new Date(), new Date(), estatus, new Date(), null);
			if (servicioEtapa.incluirEtapa(etapa)) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
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
