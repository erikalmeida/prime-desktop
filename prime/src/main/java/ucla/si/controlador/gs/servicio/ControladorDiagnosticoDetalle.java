package ucla.si.controlador.gs.servicio;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Cita;
import ucla.si.servicio.ServicioCita;

public class ControladorDiagnosticoDetalle extends ControladorInicio {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioCita servicioCita;
	
	
	private Cita cita;
	
	
	@Wire
	Textbox  txtNombres, txtMarca,txtModelo,txtServicio,txtReferido, txtPlaca;
	
	@Wire
	Datebox dtbFechaCita, txtFecha;

	@Override
	protected void inicializar() {
		
	 cita=(Cita) getAtributo("cita");
	 cargarDatos(cita);
		
		
	}
	
	public void cargarDatos(Cita cita) {
		if (cita != null) {

			txtFecha.setValue(cita.getFechaCreacion());;
			txtFecha.setDisabled(true);
			txtFecha.setReadonly(true); 
			
			txtNombres.setValue(cita.getVehiculo().getUsuario().getPersona().getNombre() + " " + cita.getVehiculo().getUsuario().getPersona().getApellido() );
			txtNombres.setDisabled(true);
			txtNombres.setReadonly(true);
			
			System.out.println(cita.getVehiculo().getPlaca());
			
			txtPlaca.setValue(cita.getVehiculo().getPlaca().toString());
			txtPlaca.setDisabled(true);
			txtPlaca.setReadonly(true); 
			
			txtMarca.setValue(cita.getVehiculo().getMarca().getNombre());
			txtMarca.setDisabled(true);
			txtMarca.setReadonly(true); 
			
			txtModelo.setValue(cita.getVehiculo().getModelo().getNombre());
			txtModelo.setDisabled(true);
			txtModelo.setReadonly(true); 
			
			txtServicio.setValue(cita.getServicio().getTitulo());
			txtServicio.setDisabled(true);
			txtServicio.setReadonly(true); 
			
			txtReferido.setValue(cita.getNombreReferido());
			txtReferido.setDisabled(true);
			txtReferido.setReadonly(true);
		}
	}
	
	
	@Listen("onClick =#btnAceptar")
	public void aceptar(){
		if(dtbFechaCita.getValue().toString() ==""){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{
			cita.setEstado("Aprobada");
			cita.setFecha(dtbFechaCita.getValue());
			if(servicioCita.editarCita(cita)){
				Messagebox.show("Cita Aprobada", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gac/frm-atender-cita.zul";
				clearDivApp(dir);
			}
			else{
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}
	
	
	
	
	@Listen("onClick =#btnVolver")
	public void volver(){
		
				String dir = "gs/servicio/frm-diagnostico-catalogo.zul";
				clearDivApp(dir);
			
	}
	
	
	

}
