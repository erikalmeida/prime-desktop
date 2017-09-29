package ucla.si.controlador.gc.herramienta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Herramienta;
import ucla.si.dao.HerramientaDAO;
import ucla.si.servicio.ServicioHerramienta;

public class ControladorHerramientaIncluir extends ControladorInicio {
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioHerramienta servicioHerramienta;

	@Wire
	Textbox txt_descripcion;

	public ControladorHerramientaIncluir() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub

	}

	@Listen("onClick =#btnGuardar")
	public void registrarHerramienta() {
		if (txt_descripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			String descripcion = txt_descripcion.getValue().trim();
			String estatus = "Activo";
			Herramienta herramienta = new Herramienta(descripcion, estatus, new Date(), null);

			if (servicioHerramienta.incluirHerramienta(herramienta)) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/herramienta/frm-herramienta-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/herramienta/frm-herramienta-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbHerramienta")
	public void herramienta() {
		String dir = "gc/herramienta/frm-herramienta-catalogo.zul";
		clearDivApp(dir);
	}

}
