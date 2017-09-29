package ucla.si.controlador.gc.maestrico.modelo;


import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Modelo;
import ucla.si.modelo.Modelo;
import ucla.si.servicio.ServicioModelo;
import ucla.si.servicio.ServicioModelo;

public class ControladorModeloConsultar extends ControladorInicio{

	/**
	 * 
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioModelo servicioModelo;
	
	@Wire
	private Combobox cmbMarca;
	
	@Wire
	private Textbox txtDescripcion;
	
	

	@Override
	protected void inicializar() {
		Modelo modelo = (Modelo)getAtributo("modelo");
		cargarDatos(modelo);
	}
	
	public void cargarDatos(Modelo modelo){
		if(modelo != null){
			cmbMarca.setValue(modelo.getMarca().getNombre());
			txtDescripcion.setValue(modelo.getNombre().trim());
			cmbMarca.setDisabled(true);
			txtDescripcion.setDisabled(true);
		}
	}
	
	@Listen("onClick =#btnAceptar ; onClick =#btnCancelar")
	public void aceptar(){
		String dir = "gc/modelo/frm-modelo-catalogo.zul";
		clearDivApp(dir);
	}

}
