package ucla.si.controlador.gc.maestrico.ciudad;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Ciudad;
import ucla.si.modelo.Estado;
import ucla.si.servicio.ServicioCiudad;
import ucla.si.servicio.ServicioEstado;

public class ControladorCiudadIncluir extends ControladorInicio {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioEstado servicioEstado;

	@WireVariable
	private ServicioCiudad servicioCiudad;

	@Wire
	private Textbox txtDescripcion;

	@Wire
	private Combobox cmbEstado;

	@Override
	protected void inicializar() {
		ListModelList<Estado> modeloEstados = new ListModelList<Estado>(servicioEstado.listarEstados());
		if (modeloEstados.getSize() == 0) {
			Messagebox.show("Debe Incluir un Estado primero", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/estado/frm-estado-incluir.zul";
			clearDivApp(dir);
		} else {
			modeloEstados.setMultiple(false);
			cmbEstado.setModel(modeloEstados);
			cmbEstado.setMultiline(false);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtDescripcion.getValue().trim() == "" || cmbEstado.getSelectedIndex() == -1) {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			String descripcion = txtDescripcion.getValue().trim().toUpperCase();

			Ciudad ciudad = new Ciudad(descripcion, new Date(), null, "Activo");
			Estado estado = (Estado) cmbEstado.getSelectedItem().getValue();
			ciudad.setEstado(estado);
			if (servicioCiudad.incluirCiudad(ciudad)) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/ciudad/frm-ciudad-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/ciudad/frm-ciudad-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbCiudad")
	public void ciudad() {
		String dir = "gc/ciudad/frm-ciudad-catalogo.zul";
		clearDivApp(dir);
	}

}