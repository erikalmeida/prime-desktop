package ucla.si.controlador.gc.herramienta;







import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Herramienta;
import ucla.si.modelo.Herramienta;
import ucla.si.servicio.ServicioHerramienta;

public class ControladorHerramientaEditar  extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioHerramienta servicioHerramienta;
	
	@Wire
	Textbox  txtDescripcion;
	
	@Wire 
	Combobox cmbEstatus;
	
	private Herramienta herramienta;

	@Override
	protected void inicializar() {
		herramienta = (Herramienta)getAtributo("herramienta");
		cargarDatos(herramienta);
	}
	
	public void cargarDatos(Herramienta herramienta){
		if(herramienta != null){
			if(herramienta.getEstatus().equalsIgnoreCase("Activo"))
			{
			
			txtDescripcion.setValue(herramienta.getDescripcion().trim());
			}
			else
			{
				
				txtDescripcion.setValue(herramienta.getDescripcion().trim());
				cmbEstatus.setValue(herramienta.getEstatus());
			}
				
		}
		else{
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/herramienta/frm-herramienta-catalogo.zul";
			clearDivApp(dir);
		}
	}
	
	
	
	
	@Listen("onClick =#btnAceptar")
	public void aceptar(){
		if( txtDescripcion.getValue().trim() ==""){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{
			
			herramienta.setDescripcion(txtDescripcion.getValue().trim());
			if(servicioHerramienta.editarHerramienta(herramienta)){
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/herramienta/frm-herramienta-catalogo.zul";
				clearDivApp(dir);
			}
			else{
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}
	
	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/herramienta/frm-herramienta-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbHerramienta")
	public void herramienta() {
		String dir = "gc/herramienta/frm-herramienta-catalogo.zul";
		clearDivApp(dir);
	}

}
