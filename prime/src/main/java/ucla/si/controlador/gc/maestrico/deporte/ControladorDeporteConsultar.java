package ucla.si.controlador.gc.maestrico.deporte;


import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Deporte;
import ucla.si.servicio.ServicioDeporte;

public class ControladorDeporteConsultar extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioDeporte servicioDeporte;
	
	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		Deporte deporte = (Deporte)getAtributo("deporte");
		cargarDatos(deporte);
	}
	
	public void cargarDatos(Deporte deporte){
		if(deporte != null){
			txtDescripcion.setValue(deporte.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
			txtDescripcion.setReadonly(true);
		}
	}
	
	@Listen("onClick =#btnAceptar")
	public void aceptar(){
		String dir = "pc/deporte/frm-deporte-catalogo.zul";
		clearDivApp(dir);
	}
	


	@Listen("onClick =#breadcrumbDeporte")
	public void deporte() {
		String dir = "pc/deporte/frm-deporte-catalogo.zul";
		clearDivApp(dir);
	}

}
