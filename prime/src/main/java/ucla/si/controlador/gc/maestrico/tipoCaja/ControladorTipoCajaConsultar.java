package ucla.si.controlador.gc.maestrico.tipoCaja;


import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoCaja;
import ucla.si.servicio.ServicioTipoCaja;

public class ControladorTipoCajaConsultar extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioTipoCaja servicioTipoCaja;
	
	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		TipoCaja tipoCaja = (TipoCaja)getAtributo("tipoCaja");
		cargarDatos(tipoCaja);
	}
	
	public void cargarDatos(TipoCaja tipoCaja){
		if(tipoCaja != null){
			txtDescripcion.setValue(tipoCaja.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
		}
	}
	
	@Listen("onClick =#btnAceptar ; onClick =#btnCancelar")
	public void aceptar(){
		String dir = "gc/tipoCaja/frm-tipoCaja-catalogo.zul";
		clearDivApp(dir);
	}

}
