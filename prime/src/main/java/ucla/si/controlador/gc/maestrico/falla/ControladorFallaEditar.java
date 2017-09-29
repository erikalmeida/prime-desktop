package ucla.si.controlador.gc.maestrico.falla;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Falla;
import ucla.si.servicio.ServicioFalla;

public class ControladorFallaEditar extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioFalla servicioFalla;
	
	@Wire
	Textbox txtNombre, txtDescripcion;
	
	@Wire 
	Combobox cmbEstatus;
	
	private Falla falla;

	@Override
	protected void inicializar() {
		falla = (Falla)getAtributo("falla");
		cargarDatos(falla);
	}
	
	public void cargarDatos(Falla falla){
		if(falla != null){
			if(falla.getEstatus().equalsIgnoreCase("Activo"))
			{
			
			txtDescripcion.setValue(falla.getDescripcion().trim());
			}
			else
			{
				
				txtDescripcion.setValue(falla.getDescripcion().trim());
				cmbEstatus.setValue(falla.getEstatus());
			}
				
		}
		else{
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/falla/frm-falla-catalogo.zul";
			clearDivApp(dir);
		}
	}
	
	
	
	
	@Listen("onClick =#btnAceptar")
	public void aceptar(){
		if(txtDescripcion.getValue().trim() ==""){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{
			
			falla.setDescripcion(txtDescripcion.getValue().trim());
			if(servicioFalla.editarFalla(falla)){
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/falla/frm-falla-catalogo.zul";
				clearDivApp(dir);
			}
			else{
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}
	
	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/falla/frm-falla-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbFalla")
	public void falla() {
		String dir = "gc/falla/frm-falla-catalogo.zul";
		clearDivApp(dir);
	}

}
