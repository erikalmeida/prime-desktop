package ucla.si.controlador.gc.maestrico.tipoTecnologia;


import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoTecnologia;
import ucla.si.servicio.ServicioTipoTecnologia;

public class ControladorTipoTecnologiaConsultar extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioTipoTecnologia servicioTipoTecnologia;
	
	@Wire
	Textbox  txtDescripcion;

	@Override
	protected void inicializar() {
		TipoTecnologia tipoTecnologia = (TipoTecnologia)getAtributo("tipoTecnologia");
		cargarDatos(tipoTecnologia);
	}
	
	public void cargarDatos(TipoTecnologia tipoTecnologia){
		if(tipoTecnologia != null){
		
			txtDescripcion.setValue(tipoTecnologia.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
			txtDescripcion.setReadonly(true);
		}
	}
	
	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/tipoTecnologia/frm-tipoTecnologia-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbTipoTecnologia")
	public void tipoTecnologia() {
		String dir = "gc/tipoTecnologia/frm-tipoTecnologia-catalogo.zul";
		clearDivApp(dir);
	}

}

