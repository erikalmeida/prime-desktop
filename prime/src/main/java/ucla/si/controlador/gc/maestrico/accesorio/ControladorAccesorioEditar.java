package ucla.si.controlador.gc.maestrico.accesorio;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Accesorio;
import ucla.si.servicio.ServicioAccesorio;

public class ControladorAccesorioEditar extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioAccesorio servicioAccesorio;
	
	@Wire
	private Textbox txtDescripcion;
	
	private Accesorio accesorio;

	@Override
	protected void inicializar() {
		accesorio = (Accesorio)getAtributo("accesorio");
		cargarDatos(accesorio);
	}
	
	public void cargarDatos(Accesorio accesorio){
		if(accesorio != null){
			txtDescripcion.setValue(accesorio.getDescripcion().trim());
		}
		else{
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/accesorio/frm-accesorio-catalogo.zul";
			clearDivApp(dir);
		}
	}
	
	@Listen("onClick =#btnAceptar")
	public void aceptar(){
		if(txtDescripcion.getValue().trim() == ""){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{
			accesorio.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			if(servicioAccesorio.editarAccesorio(accesorio)){
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/accesorio/frm-accesorio-catalogo.zul";
				clearDivApp(dir);
			}
			else{
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

}
