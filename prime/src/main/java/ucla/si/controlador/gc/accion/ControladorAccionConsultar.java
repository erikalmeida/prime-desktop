package ucla.si.controlador.gc.accion;


import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Accion;
import ucla.si.servicio.ServicioAccion;

public class ControladorAccionConsultar extends ControladorInicio{

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
		Accion accion = (Accion)getAtributo("accion");
		cargarDatos(accion);
	}
	
	public void cargarDatos(Accion accion){
		if(accion != null){
			txtNombre.setValue(accion.getNombre().trim());
			txtNombre.setReadonly(true);
		}
	}
	
	@Listen("onClick =#btnAceptar ; onClick =#btnCancelar")
	public void aceptar(){
		String dir = "gc/accion/frm-accion-catalogo.zul";
		clearDivApp(dir);
	}

}
