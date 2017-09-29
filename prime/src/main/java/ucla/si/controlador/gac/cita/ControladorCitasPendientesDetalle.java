package ucla.si.controlador.gac.cita;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Datebox;

import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Cita;

import ucla.si.modelo.Persona;
import ucla.si.servicio.ServicioCita;
import ucla.si.servicio.ServicioCorreo;

import ucla.si.modelo.Espacio;
import ucla.si.modelo.Falla;
import ucla.si.servicio.ServicioCita;
import ucla.si.servicio.ServicioEspacio;

public class ControladorCitasPendientesDetalle extends ControladorInicio {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private ServicioCita servicioCita;
	
	@WireVariable
	private ServicioCorreo servicioCorreo;

	@WireVariable
	private Espacio espacio;
	
	@WireVariable
	private ServicioEspacio servicioEspacio;;
	
	
	private Cita cita;
	
	
	@Wire
	Textbox  txtNombres, txtMarca,txtModelo,txtServicio,txtReferido, txtPlaca, txtPromocion;
	
	@Wire
	Datebox dtbFechaCita, txtFecha;

	@Override
	protected void inicializar() {
	// espacio=(Espacio) getAtributo("espacio");
	 cita = (Cita) getAtributo("cita");
	 //cita=servicioCita.buscarCitaPorIdEspacio(espacio.getId());
	 cargarDatos(cita);
		
		
	}
	
	public void cargarDatos(Cita cita) {
		if (cita != null) {
			
			System.out.println(cita);
			System.out.println("adhkhdskjdshaksdhkdshdskjhdaskj"+cita.getDescripcion());
			txtFecha.setValue(cita.getFechaCreacion());
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

			//txtPromocion.setValue(cita.getPromocion().getTitulo());
			//txtPromocion.setDisabled(true);
			//txtPromocion.setReadonly(true); 
			
			txtReferido.setValue(cita.getNombreReferido());
			txtReferido.setDisabled(true);
			txtReferido.setReadonly(true);
		}
	}
	
	
	@Listen("onClick =#btnAprobar")
	public void aceptar(){
		if(dtbFechaCita.getValue().toString() ==""){
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		else{
			cita.setEstado("Aprobada");
			cita.setFecha(dtbFechaCita.getValue());
			if(servicioCita.editarCita(cita)){
				Persona persona = cita.getVehiculo().getUsuario().getPersona();
				String nombre = servicioCorreo.cortesia(persona.getSexo().trim())+" "+persona.getNombre().toUpperCase()+" "+persona.getApellido().toUpperCase();
				String mensaje = servicioCorreo.mensajeAtenderCita(nombre,"Aprobada",cita.getFecha());
				servicioCorreo.enviarCorreoHTML(persona.getUsuario().getCorreo(), "Notificación Aprobación de Cita SERVIALDANA'S", "","", mensaje);
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
		
				String dir = "gac/frm-atender-cita.zul";
				clearDivApp(dir);
			
	}
	
	@Listen("onClick =#btnVolver2")
	public void volver2(){
		
				String dir = "gc/espacio/frm-asignar-espacio-catalogo.zul";
				clearDivApp(dir);
			
	}
	
	

}
