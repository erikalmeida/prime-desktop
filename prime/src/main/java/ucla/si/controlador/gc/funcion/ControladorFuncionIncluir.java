package ucla.si.controlador.gc.funcion;

import java.util.Date;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Funcion;
import ucla.si.modelo.Sistema;
import ucla.si.servicio.ServicioFuncion;

public class ControladorFuncionIncluir extends ControladorInicio {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioFuncion servicioFuncion;

	@Wire
	private Textbox txtClave, txtNombre, txtDescripcion, txtIcono, txtUrl;

	@Wire
	private Combobox cmbFuncionPadre;

	private Sistema sistema;

	@Override
	protected void inicializar() {
		ListModelList<Funcion> modeloFunciones = new ListModelList<Funcion>(servicioFuncion.listarFunciones());
		modeloFunciones.setMultiple(false);
		cmbFuncionPadre.setModel(modeloFunciones);
		cmbFuncionPadre.setMultiline(false);
		sistema = (Sistema) Sessions.getCurrent().getAttribute("sistema");
		if (sistema == null) {
			Messagebox.show("Error de sistema", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/funcion/frm-funcion-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtClave.getValue().trim() == "" || txtNombre.getValue().trim() == ""
				|| txtDescripcion.getValue().trim() == "" || txtUrl.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			String clave = txtClave.getValue().trim();
			String nombre = txtNombre.getValue().trim().toUpperCase();
			String descripcion = txtDescripcion.getValue().trim().toUpperCase();
			String icono = txtIcono.getValue().trim();
			String url = txtUrl.getValue().trim();
			Funcion funcionPadre = cmbFuncionPadre.getSelectedIndex() != -1
					? (Funcion) cmbFuncionPadre.getSelectedItem().getValue() : null;
			Funcion funcion = new Funcion(clave, nombre, descripcion, icono, url, "Activo", new Date(), null);
			funcion.setFuncionPadre(funcionPadre);
			funcion.setSistema(sistema);
			if (servicioFuncion.incluirFuncion(funcion)) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/funcion/frm-funcion-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/funcion/frm-funcion-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbFuncion")
	public void funcion() {
		String dir = "gc/funcion/frm-funcion-catalogo.zul";
		clearDivApp(dir);
	}

}
