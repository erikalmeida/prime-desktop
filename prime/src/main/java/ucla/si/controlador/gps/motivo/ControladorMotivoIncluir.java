package ucla.si.controlador.gps.motivo;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Motivo;
import ucla.si.modelo.Reclamo;
import ucla.si.servicio.ServicioMotivo;
import ucla.si.servicio.ServicioReclamo;

public class ControladorMotivoIncluir extends ControladorInicio{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioMotivo servicioMotivo;
	
	@WireVariable
	private ServicioReclamo servicioReclamo;
	
	@Wire
	private Textbox txtDescripcion;
	
	private Reclamo reclamo;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
		reclamo = (Reclamo) getAtributo("reclamo");
		
	}
	
	@Listen("onClick =#btnAceptar")
	public void aceptar(){
		
		if(txtDescripcion.getValue().trim() ==""){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{
			String descripcion = txtDescripcion.getValue().trim().toUpperCase();
			Motivo motivo = new Motivo(descripcion,"Activo",new Date(),null);
			if(servicioMotivo.incluirMotivo(motivo)){
				reclamo.setMotivo(motivo);
				servicioReclamo.editarReclamo(reclamo);
				Messagebox.show("Actualizacion del Reclamo Exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gps/reclamo/frm-reclamo-reclamos.zul";
				clearDivApp(dir);
			}
			else{
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
		
		
		String dir = "gps/reclamo/frm-reclamo-reclamos.zul";
		clearDivApp(dir);
	}

}
