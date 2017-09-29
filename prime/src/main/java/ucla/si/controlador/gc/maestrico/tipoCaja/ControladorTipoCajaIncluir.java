package ucla.si.controlador.gc.maestrico.tipoCaja;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.TipoCaja;
import ucla.si.servicio.ServicioTipoCaja;

public class ControladorTipoCajaIncluir extends ControladorInicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioTipoCaja servicioTipoCaja;
	
	@Wire
	private Textbox txtDescripcion;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
	}
	
	@Listen("onClick =#btnAceptar")
	public void aceptar(){
		if( txtDescripcion.getValue().trim() ==""){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{
			String descripcion = txtDescripcion.getValue().trim().toUpperCase();					
			TipoCaja tipoCaja = new TipoCaja(descripcion, "Activo", new Date(), null);
			if(servicioTipoCaja.incluirTipoCaja(tipoCaja)){
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gc/tipoCaja/frm-tipoCaja-catalogo.zul";
				clearDivApp(dir);
			}
			else{
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

}
