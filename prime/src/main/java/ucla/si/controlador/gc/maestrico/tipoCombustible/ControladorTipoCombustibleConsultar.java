package ucla.si.controlador.gc.maestrico.tipoCombustible;


import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoCombustible;
import ucla.si.servicio.ServicioTipoCombustible;

public class ControladorTipoCombustibleConsultar extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioTipoCombustible servicioTipoCombustible;
	
	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		TipoCombustible tipoCombustible = (TipoCombustible)getAtributo("tipoCombustible");
		cargarDatos(tipoCombustible);
	}
	
	public void cargarDatos(TipoCombustible tipoCombustible){
		if(tipoCombustible != null){
			txtDescripcion.setValue(tipoCombustible.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
		}
	}
	
	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/tipoCombustible/frm-tipoCombustible-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbTipoCombustible")
	public void tipoCombustible() {
		String dir = "gc/tipoCombustible/frm-tipoCombustible-catalogo.zul";
		clearDivApp(dir);
	}

}
