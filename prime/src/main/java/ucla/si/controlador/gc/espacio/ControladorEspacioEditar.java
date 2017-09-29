package ucla.si.controlador.gc.espacio;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Espacio;
import ucla.si.servicio.ServicioEspacio;

public class ControladorEspacioEditar extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioEspacio servicioEspacio;
	
	@Wire
	Textbox txtDescripcion;
	
	private Espacio espacio;

	@Override
	protected void inicializar() {
		espacio = (Espacio)getAtributo("espacio");
		cargarDatos(espacio);
	}
	
	public void cargarDatos(Espacio espacio){
		if(espacio != null){
			txtDescripcion.setValue(espacio.getDescripcion().trim());
		}
		else{
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/espacio/frm-espacio-catalogo.zul";
			clearDivApp(dir);
		}
	}
	
	@Listen("onClick =#btnAceptar")
	public void aceptar(){
		if(txtDescripcion.getValue().trim() ==""){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{
			espacio.setDescripcion(txtDescripcion.getValue().trim());
			if(servicioEspacio.editarEspacio(espacio)){
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/espacio/frm-espacio-catalogo.zul";
				clearDivApp(dir);
			}
			else{
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

}
