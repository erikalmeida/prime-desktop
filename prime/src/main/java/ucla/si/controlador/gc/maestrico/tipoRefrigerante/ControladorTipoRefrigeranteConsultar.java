package ucla.si.controlador.gc.maestrico.tipoRefrigerante;


import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoRefrigerante;
import ucla.si.servicio.ServicioTipoRefrigerante;

public class ControladorTipoRefrigeranteConsultar extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioTipoRefrigerante servicioTipoRefrigerante;
	
	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		TipoRefrigerante tipoRefrigerante = (TipoRefrigerante)getAtributo("tipoRefrigerante");
		cargarDatos(tipoRefrigerante);
	}
	
	public void cargarDatos(TipoRefrigerante tipoRefrigerante){
		if(tipoRefrigerante != null){
			txtDescripcion.setValue(tipoRefrigerante.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
		}
	}
	
	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/tipoRefrigerante/frm-tipoRefrigerante-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbTipoRefrigerante")
	public void tipoRefrigerante() {
		String dir = "gc/tipoRefrigerante/frm-tipoRefrigerante-catalogo.zul";
		clearDivApp(dir);
	}
}
