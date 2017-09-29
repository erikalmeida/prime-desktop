package ucla.si.controlador.gc.maestrico.color;


import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Color;
import ucla.si.servicio.ServicioColor;

public class ControladorColorConsultar extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioColor servicioColor;
	
	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		Color color = (Color)getAtributo("color");
		cargarDatos(color);
	}
	
	public void cargarDatos(Color color){
		if(color != null){
			txtDescripcion.setValue(color.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
		}
	}
	
	@Listen("onClick =#btnAceptar ; onClick =#btnCancelar")
	public void aceptar(){
		String dir = "gc/color/frm-color-catalogo.zul";
		clearDivApp(dir);
	}
	
	@Listen("onClick =#breadcrumbColor")
	public void color() {
		String dir = "gc/color/frm-color-catalogo.zul";
		clearDivApp(dir);
	}

}
