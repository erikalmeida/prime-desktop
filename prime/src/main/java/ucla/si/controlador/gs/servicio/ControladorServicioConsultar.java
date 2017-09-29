package ucla.si.controlador.gs.servicio;


import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Servicio;
import ucla.si.servicio.ServicioServicio;

public class ControladorServicioConsultar extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioServicio servicioServicio;
	
	@Wire
	Textbox txtEstado, txtDescripcion,txtTitulo,txtUrlImagen,txtEstatus;
	
	@Wire
	Doublebox dbTarifa;

	@Override
	protected void inicializar() {
		Servicio servicio = (Servicio)getAtributo("servicio");
		cargarDatos(servicio);
	}
	
	
	
	

	
	public void cargarDatos(Servicio servicio){
		if(servicio != null){
			txtDescripcion.setValue(servicio.getDescripcion().trim());
			txtEstado.setValue(servicio.getEstado().trim());
			txtTitulo.setValue(servicio.getTitulo().trim());
			txtUrlImagen.setValue(servicio.getUrlImagen().trim());
			txtEstatus.setValue(servicio.getEstatus().trim());
			dbTarifa.setValue(servicio.getTarifa());
			txtDescripcion.setReadonly(true);
		}
	}
	
	@Listen("onClick =#btnAceptar ; onClick =#btnCancelar")
	public void aceptar(){
		String dir = "gc/servicio/frm-servicio-catalogo.zul";
		clearDivApp(dir);
	}

}
