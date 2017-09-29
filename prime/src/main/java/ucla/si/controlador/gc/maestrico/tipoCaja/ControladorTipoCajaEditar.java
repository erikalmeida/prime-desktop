package ucla.si.controlador.gc.maestrico.tipoCaja;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoCaja;
import ucla.si.servicio.ServicioTipoCaja;

public class ControladorTipoCajaEditar extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioTipoCaja servicioTipoCaja;
	
	@Wire
	private Textbox txtDescripcion;
	
	private TipoCaja tipoCaja;

	@Override
	protected void inicializar() {
		tipoCaja = (TipoCaja)getAtributo("tipoCaja");
		cargarDatos(tipoCaja);
	}
	
	public void cargarDatos(TipoCaja tipoCaja){
		if(tipoCaja != null){
			txtDescripcion.setValue(tipoCaja.getDescripcion().trim());
		}
		else{
			Messagebox.show("Error al cargar datos", "Error", Messagebox.OK, Messagebox.ERROR);
			String dir = "gc/tipoCaja/frm-tipoCaja-catalogo.zul";
			clearDivApp(dir);
		}
	}
	
	@Listen("onClick =#btnAceptar")
	public void aceptar(){
		if(txtDescripcion.getValue().trim() == ""){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{
			tipoCaja.setDescripcion(txtDescripcion.getValue().trim().toUpperCase());
			if(servicioTipoCaja.editarTipoCaja(tipoCaja)){
				Messagebox.show("Edición exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/tipoCaja/frm-tipoCaja-catalogo.zul";
				clearDivApp(dir);
			}
			else{
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

}
