package ucla.si.controlador.gc.tecnologia;




import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Tecnologia;
import ucla.si.servicio.ServicioTecnologia;

public class ControladorTecnologiaConsultar  extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioTecnologia servicioTecnologia;
	
	@Wire
	Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		Tecnologia tecnologia = (Tecnologia)getAtributo("tecnologia");
		cargarDatos(tecnologia);
	}
	
	public void cargarDatos(Tecnologia tecnologia){
		if(tecnologia != null){
			
			txtDescripcion.setValue(tecnologia.getDescripcion().trim());
			txtDescripcion.setDisabled(true);
			txtDescripcion.setReadonly(true);
		}
	}
	
	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/tecnologia/frm-tecnologia-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbTecnologia")
	public void tecnologia() {
		String dir = "gc/tecnologia/frm-tecnologia-catalogo.zul";
		clearDivApp(dir);
	}
}

