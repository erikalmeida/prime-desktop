package ucla.si.controlador.gc.maestrico.clase;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Clase;
import ucla.si.modelo.TipoClase;
import ucla.si.servicio.ServicioClase;
import ucla.si.servicio.ServicioTipoClase;

public class ControladorClaseEditar extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioTipoClase servicioTipoClase;
	
	@WireVariable
	private ServicioClase servicioClase;
		
	@Wire
	private Textbox txtDescripcion;
	
	@Wire
	private Combobox cmbTipoClase;
	
	private Clase clase;

	@Override
	protected void inicializar() {
		ListModelList<TipoClase> modeloTipoClases = new ListModelList<TipoClase>(servicioTipoClase.listarTipoClases());
		if(modeloTipoClases.getSize() == 0){
			Messagebox.show("Debe Incluir un Tipo de Clase primero", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/tipoClase/frm-tipoClase-incluir.zul";
			clearDivApp(dir);
		}
		else{
			modeloTipoClases.setMultiple(false);
			cmbTipoClase.setModel(modeloTipoClases);
			cmbTipoClase.setMultiline(false);
			clase = (Clase)getAtributo("clase");
			cargarDatos(clase);
		}
	}
	
	public void cargarDatos(Clase clase){
		if(clase != null){
			txtDescripcion.setValue(clase.getDescripcion().trim());
			cmbTipoClase.setValue(clase.getTipoClase().getDescripcion());
		}
		else{
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/clase/frm-clase-catalogo.zul";
			clearDivApp(dir);
		}
	}
	
	@Listen("onClick =#btnAceptar")
	public void aceptar(){
		if(txtDescripcion.getValue().trim() == "" || cmbTipoClase.getSelectedIndex() == -1){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{
			clase.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			clase.setTipoClase((TipoClase) cmbTipoClase.getSelectedItem().getValue());
			if(servicioClase.editarClase(clase)){
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/clase/frm-clase-catalogo.zul";
				clearDivApp(dir);
			}
			else{
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}
	@Listen("onClick =#btnCancelar")
	public void cancelar() {
		String dir = "gc/clase/frm-clase-catalogo.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#breadcrumbClase")
	public void clase() {
		String dir = "gc/clase/frm-clase-catalogo.zul";
		clearDivApp(dir);
	}

}
