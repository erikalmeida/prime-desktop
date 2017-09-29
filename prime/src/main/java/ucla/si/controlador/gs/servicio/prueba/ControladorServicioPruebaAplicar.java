package ucla.si.controlador.gs.servicio.prueba;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Cita;
import ucla.si.modelo.OrdenServicio;
import ucla.si.modelo.Persona;
import ucla.si.modelo.Presupuesto;
import ucla.si.modelo.Prueba;
import ucla.si.servicio.ServicioCita;
import ucla.si.servicio.ServicioOrdenServicio;
import ucla.si.servicio.ServicioPresupuesto;
import ucla.si.servicio.ServicioPrueba;

public class ControladorServicioPruebaAplicar extends ControladorInicio {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	
	
	@Wire
	private Textbox txtClienteFoto;

	@Wire
	private Textbox txtClienteNombre;

	@Wire
	private Textbox txtClienteApellido;

	@Wire
	private Textbox txtClienteDireccion;

	@Wire
	private Textbox txtClienteCorreo;

	@Wire
	private Textbox txtAutoPlaca;

	@Wire
	private Textbox txtAutoMarca;

	@Wire
	private Textbox txtAutoAnno;

	@Wire
	private Textbox txtAutoModelo;
	
	@Wire
	private Textbox txtOrdenServicioDescripcion;
	
	@Wire
	private Textbox txtOrdenServicioFecha;
	
	@Wire
	private Textbox txtOrdenServicioEstado;
	
	@Wire
	private Textbox txtServicio;

	
	@Wire
	private Textbox txtDescripcion;

	
	private OrdenServicio ordenServicio = null;

	@WireVariable
	private ServicioPresupuesto servicioPresupuesto;
	
	@WireVariable
	private ServicioOrdenServicio servicioOrdenServicio;
	
	@WireVariable
	private ServicioCita servicioCita;
	
	@WireVariable
	private ServicioPrueba servicioPrueba;
	
	
	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
		ordenServicio = (OrdenServicio) getAtributo("ordenServicio");
		System.out.println(ordenServicio.getDescripcion());
		cargarDatos(ordenServicio);
		
	}
	
	public void cargarDatos(OrdenServicio ordenServicio) {
		// Presupuesto
		Long idPresupuesto = ordenServicio.getPresupuesto().getId();
		Presupuesto presupuesto = (Presupuesto) servicioPresupuesto.getPresupuesto(idPresupuesto);
		
		//Cita
		Long idCita = presupuesto.getCita().getId();
		Cita cita = (Cita) servicioCita.getCita(idCita);
		
		Persona persona = cita.getVehiculo().getUsuario().getPersona();
		
		// Cliente
		txtClienteFoto.setValue("Foto");
		txtClienteNombre.setValue(persona.getNombre().toString());
		txtClienteApellido.setValue(persona.getApellido().toString());
		txtClienteDireccion.setValue(persona.getDireccion().toString());
		txtClienteCorreo.setValue(cita.getVehiculo().getUsuario().getCorreo().toString());

		// Auto
		txtAutoPlaca.setValue(cita.getVehiculo().getPlaca().toString());
		txtAutoMarca.setValue(cita.getVehiculo().getMarca().getNombre().toString());
		txtAutoAnno.setValue(cita.getVehiculo().getAnno().toString());
		txtAutoModelo.setValue(cita.getVehiculo().getModelo().getNombre().toString());

		//Orden Servicio
		txtOrdenServicioFecha.setValue(ordenServicio.getFechaCreacion().toString());
		txtOrdenServicioEstado.setValue(ordenServicio.getEstado().toString());
		txtOrdenServicioDescripcion.setValue(ordenServicio.getDescripcion().toString());
		//Servicio
		txtServicio.setValue(cita.getServicio().getDescripcion().toString());
		
		
		// desabilitar
		txtOrdenServicioFecha.setDisabled(true);
		txtOrdenServicioEstado.setDisabled(true);
		txtOrdenServicioDescripcion.setDisabled(true);
		txtServicio.setDisabled(true);
		txtClienteFoto.setDisabled(true);
		txtClienteNombre.setDisabled(true);
		txtClienteApellido.setDisabled(true);
		txtClienteDireccion.setDisabled(true);
		txtClienteCorreo.setDisabled(true);
		txtAutoPlaca.setDisabled(true);
		txtAutoMarca.setDisabled(true);
		txtAutoAnno.setDisabled(true);
		txtAutoModelo.setDisabled(true);
		
	}
	
	@Listen("onClick =#btnAceptar")
	public void aceptar() {
		
		if (txtDescripcion.getValue().trim() == "") {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			// estado Finalizado
			String descripcion = txtDescripcion.getValue().toUpperCase();
			Prueba prueba = new Prueba(descripcion, "Tipo Prueba","Finalizado", new Date(), null, "Activo");
			prueba.setOrdenServicio(ordenServicio);
			if (servicioPrueba.incluirPrueba(prueba)) {
				ordenServicio.setEstado("Finalizado");
				servicioOrdenServicio.editarOrdenServicio(ordenServicio);
				Messagebox.show("Inclusion exitosa", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gs/prueba/frm-prueba-catalogo.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}
	
	@Listen("onClick =#btnVolver")
	public void volver() {
		String dir = "gs/prueba/frm-prueba-catalogo.zul";
		clearDivApp(dir);
	}

}
