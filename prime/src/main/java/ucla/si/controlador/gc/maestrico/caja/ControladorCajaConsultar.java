package ucla.si.controlador.gc.maestrico.caja;


import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Caja;
import ucla.si.servicio.ServicioCaja;

public class ControladorCajaConsultar extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioCaja servicioCaja;
	
	@Wire
	private Combobox cmbTipoCaja;
	
	@Wire
	private Textbox txtDescripcion;
	
	

	@Override
	protected void inicializar() {
		Caja caja = (Caja)getAtributo("caja");
		cargarDatos(caja);
	}
	
	public void cargarDatos(Caja caja){
		if(caja != null){
			cmbTipoCaja.setValue(caja.getTipoCaja().getDescripcion());
			txtDescripcion.setValue(caja.getDescripcion().trim());
			cmbTipoCaja.setDisabled(true);
			txtDescripcion.setDisabled(true);
		}
	}
	
	@Listen("onClick =#btnAceptar ; onClick =#btnCancelar")
	public void aceptar(){
		String dir = "gc/caja/frm-caja-catalogo.zul";
		clearDivApp(dir);
	}

}
