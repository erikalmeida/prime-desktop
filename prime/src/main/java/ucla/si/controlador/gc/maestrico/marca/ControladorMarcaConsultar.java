package ucla.si.controlador.gc.maestrico.marca;


import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Marca;
import ucla.si.servicio.ServicioMarca;

public class ControladorMarcaConsultar extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioMarca servicioMarca;
	
	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		Marca marca = (Marca)getAtributo("marca");
		cargarDatos(marca);
	}
	
	public void cargarDatos(Marca marca){
		if(marca != null){
			txtDescripcion.setValue(marca.getNombre().trim());
			txtDescripcion.setDisabled(true);
		}
	}
	
	@Listen("onClick =#btnAceptar ; onClick =#btnCancelar")
	public void aceptar(){
		String dir = "gc/marca/frm-marca-catalogo.zul";
		clearDivApp(dir);
	}

}
