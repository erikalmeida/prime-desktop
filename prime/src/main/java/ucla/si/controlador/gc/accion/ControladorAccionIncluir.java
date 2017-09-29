package ucla.si.controlador.gc.accion;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Accion;
import ucla.si.servicio.ServicioAccion;

public class ControladorAccionIncluir extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioAccion servicioAccion;
	
	@Wire
	private Textbox txtNombre;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
	}
	
	@Listen("onClick =#btnAceptar")
	public void aceptar(){
		if(txtNombre.getValue().trim() ==""){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{
			String nombre = txtNombre.getValue().trim().toUpperCase();					
			Accion accion = new Accion(nombre, "Activo", new Date(), null);
			if(servicioAccion.incluirAccion(accion)){
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/accion/frm-accion-catalogo.zul";
				clearDivApp(dir);
			}
			else{
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

}
