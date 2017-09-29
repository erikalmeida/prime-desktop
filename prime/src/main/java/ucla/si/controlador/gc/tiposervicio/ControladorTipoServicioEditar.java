package ucla.si.controlador.gc.tiposervicio;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoServicio;
import ucla.si.servicio.ServicioTipoServicio;

public class ControladorTipoServicioEditar extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioTipoServicio servicioTipoServicio;
	
	@Wire
	Textbox  txtDescripcion;
	
	@Wire 
	Combobox cmbEstatus;
	
	private TipoServicio tipoServicio;

	@Override
	protected void inicializar() {
		tipoServicio = (TipoServicio)getAtributo("tipoServicio");
		cargarDatos(tipoServicio);
	}
	
	public void cargarDatos(TipoServicio tipoServicio){
		if(tipoServicio != null){
			if(tipoServicio.getEstatus().equalsIgnoreCase("Activo"))
			{
			
			txtDescripcion.setValue(tipoServicio.getDescripcion().trim());
			}
			else
			{
				
				txtDescripcion.setValue(tipoServicio.getDescripcion().trim());
				cmbEstatus.setValue(tipoServicio.getEstatus());
			}
				
		}
		else{
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/tipoServicio/frm-tipoServicio-catalogo.zul";
			clearDivApp(dir);
		}
	}
	
	
	
	
	@Listen("onClick =#btnAceptar")
	public void aceptar(){
		if( txtDescripcion.getValue().trim() ==""){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{
			
			tipoServicio.setDescripcion(txtDescripcion.getValue().trim());
			if(servicioTipoServicio.editarTipoServicio(tipoServicio)){
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/tipoServicio/frm-tipoServicio-catalogo.zul";
				clearDivApp(dir);
			}
			else{
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}
	
	@Listen(" onClick =#btnCancelar")
	public void Cancelar(){
		String dir = "gc/tipoServicio/frm-tipoServicio-catalogo.zul";
		clearDivApp(dir);
	}

}
