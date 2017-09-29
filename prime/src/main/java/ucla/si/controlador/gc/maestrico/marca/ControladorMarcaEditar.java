package ucla.si.controlador.gc.maestrico.marca;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Marca;
import ucla.si.servicio.ServicioMarca;

public class ControladorMarcaEditar extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioMarca servicioMarca;
	
	@Wire
	private Textbox txtDescripcion;
	
	private Marca marca;

	@Override
	protected void inicializar() {
		marca = (Marca)getAtributo("marca");
		cargarDatos(marca);
	}
	
	public void cargarDatos(Marca marca){
		if(marca != null){
			txtDescripcion.setValue(marca.getNombre().trim());
		}
		else{
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/marca/frm-marca-catalogo.zul";
			clearDivApp(dir);
		}
	}
	
	@Listen("onClick =#btnAceptar")
	public void aceptar(){
		if(txtDescripcion.getValue().trim() == ""){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{
			marca.setNombre(txtDescripcion.getValue().trim().toUpperCase());
			if(servicioMarca.editarMarca(marca)){
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/marca/frm-marca-catalogo.zul";
				clearDivApp(dir);
			}
			else{
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

}
