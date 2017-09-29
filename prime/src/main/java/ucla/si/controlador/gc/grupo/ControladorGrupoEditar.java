package ucla.si.controlador.gc.grupo;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Grupo;
import ucla.si.servicio.ServicioGrupo;

public class ControladorGrupoEditar extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioGrupo servicioGrupo;

	@Wire
	Textbox txtNombre, txtDescripcion;

	private Grupo grupo;

	@Override
	protected void inicializar() {
		grupo = (Grupo) getAtributo("grupo");
		cargarDatos(grupo);
	}

	public void cargarDatos(Grupo grupo) {
		if (grupo != null) {
			txtNombre.setValue(grupo.getNombre().trim());
			txtDescripcion.setValue(grupo.getDescripcion().trim());
		} else {
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/grupo/frm-grupo-catalogo.zul";
			clearDivApp(dir);
		}
	}

	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		if (txtNombre.getValue().trim() == "" || txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			grupo.setNombre(txtNombre.getValue().trim().toUpperCase());
			grupo.setDescripcion(txtDescripcion.getValue().trim());
			if (servicioGrupo.editarGrupo(grupo)) {
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/grupo/frm-grupo-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/grupo/frm-grupo-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbGrupo")
	public void grupo() {
		String dir = "gc/grupo/frm-grupo-catalogo.zul";
		clearDivApp(dir);
	}

}
