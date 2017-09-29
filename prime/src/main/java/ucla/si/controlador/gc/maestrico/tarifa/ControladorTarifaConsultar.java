package ucla.si.controlador.gc.maestrico.tarifa;


import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Tarifa;
import ucla.si.servicio.ServicioTarifa;

public class ControladorTarifaConsultar extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioTarifa servicioTarifa;
	
	@Wire
	Textbox  txtDescripcion;
	
	@Wire
	Doublebox dbMonto;

	@Override
	protected void inicializar() {
		Tarifa tarifa = (Tarifa)getAtributo("tarifa");
		cargarDatos(tarifa);
	}
	
	public void cargarDatos(Tarifa tarifa){
		if(tarifa != null){
		
			txtDescripcion.setValue(tarifa.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
			txtDescripcion.setReadonly(true);
			Double monto = dbMonto.getValue();
			
		}
	}
	
	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/tarifa/frm-tarifa-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbTarifa")
	public void tarifa() {
		String dir = "gc/tarifa/frm-tarifa-catalogo.zul";
		clearDivApp(dir);
	}
}

