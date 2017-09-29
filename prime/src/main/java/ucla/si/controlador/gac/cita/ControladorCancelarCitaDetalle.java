package ucla.si.controlador.gac.cita;

import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;







import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Cita;
import ucla.si.modelo.Falla;
import ucla.si.modelo.Persona;
import ucla.si.servicio.ServicioCita;
import ucla.si.servicio.ServicioCorreo;



public class ControladorCancelarCitaDetalle extends ControladorInicio {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioCita servicioCita;
	
	@WireVariable
	private ServicioCorreo servicioCorreo;
	
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
			
			txtPlaca.setValue(cita.getVehiculo().getPlaca());
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
	
	
	@Listen("onClick =#btnCancelar")
	public void cancelar(){
		
			cita.setEstado("Cancelada");
			cita.setFechaModificacion(new Date());
			if(servicioCita.editarCita(cita)){
				Persona persona = cita.getVehiculo().getUsuario().getPersona();
				String nombre = servicioCorreo.cortesia(persona.getSexo().trim())+" "+persona.getNombre().toUpperCase()+" "+persona.getApellido().toUpperCase();
				String mensaje = servicioCorreo.mensajeAtenderCita(nombre,"Cancelada",null);
				servicioCorreo.enviarCorreoHTML(persona.getUsuario().getCorreo(), "Notificación Cancelación de Cita SERVIALDANA'S", "","", mensaje);
				Messagebox.show("Cita Cancelada", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gac/frm-cancelar-cita.zul";
				clearDivApp(dir);
			}
			else{
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	
	
	
	
	
	@Listen("onClick =#btnVolver")
	public void volver(){
		
				String dir = "gac/frm-atender-cita.zul";
				clearDivApp(dir);
			
	}
	
	

}
