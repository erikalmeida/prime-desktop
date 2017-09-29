package ucla.si.controlador.gc.maestrico.accesorio;


import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Accesorio;
import ucla.si.servicio.ServicioAccesorio;

public class ControladorAccesorioConsultar extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioAccesorio servicioAccesorio;
	
	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		Accesorio accesorio = (Accesorio)getAtributo("accesorio");
		cargarDatos(accesorio);
	}
	
	public void cargarDatos(Accesorio accesorio){
		if(accesorio != null){
			txtDescripcion.setValue(accesorio.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
		}
	}
	
	@Listen("onClick =#btnAceptar ; onClick =#btnCancelar")
	public void aceptar(){
		String dir = "gc/accesorio/frm-accesorio-catalogo.zul";
		clearDivApp(dir);
	}

}
