package ucla.si.controlador.gc.tiposervicio;


import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoServicio;
import ucla.si.servicio.ServicioTipoServicio;

public class ControladorTipoServicioConsultar extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioTipoServicio servicioTipoServicio;
	
	@Wire
	Textbox txtNombre, txtDescripcion;

	@Override
	protected void inicializar() {
		TipoServicio tipoServicio = (TipoServicio)getAtributo("tipoServicio");
		cargarDatos(tipoServicio);
	}
	
	public void cargarDatos(TipoServicio tipoServicio){
		if(tipoServicio != null){
			
			txtDescripcion.setValue(tipoServicio.getDescripcion().trim());
		
			txtDescripcion.setReadonly(true);
		}
	}
	
	@Listen("onClick =#btnAceptar ; onClick =#btnCancelar")
	public void aceptar(){
		String dir = "gc/tipoServicio/frm-tipoServicio-catalogo.zul";
		clearDivApp(dir);
	}

}
