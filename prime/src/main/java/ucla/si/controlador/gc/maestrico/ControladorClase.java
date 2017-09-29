package ucla.si.controlador.gc.maestrico;

import java.util.Date;
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

public class ControladorClase extends ControladorInicio{
	
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
		
	@Override
	protected void inicializar() {
		ListModelList<TipoClase> modeloTipoClases = new ListModelList<TipoClase>(servicioTipoClase.listarTipoClases());
		modeloTipoClases.setMultiple(false);
		cmbTipoClase.setModel(modeloTipoClases);
		cmbTipoClase.setMultiline(false);
		System.out.print("Antes: "+cmbTipoClase.getSelectedIndex());
	}
	
/*	
	public void cargarTipoClases(List<TipoClase> tipoClase){
		//cmbTipoClase.removeAllItems();
		cmbTipoClase.appendItem("Seleccione");
		for (int i = 0; i < tipoClase.size(); i++){
			cmbTipoClase.appendItem(tipoClase.get(i).getDescripcion());
		}
	}
	*/
	
	/*
	public void onCreate$rol(CreateEvent event)
    {	
		List<TipoClase> descripcion = tipodao.listartiposClases();
		descripciones = new ListModelList<TipoClase>(descripcion);
		rol.setModel(descripciones);
    }
	
	
	*/
	
 
	
	@Listen("onClick =#btnRegistrar")
	public void registrar(){
		if(txtDescripcion.getValue().trim() =="" || cmbTipoClase.getSelectedIndex() == 0){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{
			String descripcion = txtDescripcion.getValue().trim().toUpperCase();
			Clase clase = new Clase(descripcion,"Activo",new Date(), null);
			TipoClase tipoClase = (TipoClase)cmbTipoClase.getSelectedItem().getValue();
			clase.setTipoClase(tipoClase);
			if(servicioClase.incluirClase(clase)){
				Messagebox.show("Registro exitoso", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/clase/frm-clase.zul";
				clearDivApp(dir);
			}
			else{
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

}
