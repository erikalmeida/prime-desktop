package ucla.si.controlador.gc.accion;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Accion;
import ucla.si.servicio.ServicioAccion;

public class ControladorAccionEditar extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioAccion servicioAccion;
	
	@Wire
	private Textbox txtNombre;
	
	private Accion accion;

	@Override
	protected void inicializar() {
		accion = (Accion)getAtributo("accion");
		cargarDatos(accion);
	}
	
	public void cargarDatos(Accion accion){
		if(accion != null){
			txtNombre.setValue(accion.getNombre().trim());
		}
		else{
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/accion/frm-accion-catalogo.zul";
			clearDivApp(dir);
		}
	}
	
	@Listen("onClick =#btnAceptar")
	public void aceptar(){
		if(txtNombre.getValue().trim() == ""){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{
			accion.setNombre(txtNombre.getValue().trim().toUpperCase());
			if(servicioAccion.editarAccion(accion)){
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/accion/frm-accion-catalogo.zul";
				clearDivApp(dir);
			}
			else{
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

}
