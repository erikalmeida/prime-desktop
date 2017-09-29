package ucla.si.controlador.gps.reclamo;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Cita;
import ucla.si.modelo.OrdenEntrega;
import ucla.si.modelo.OrdenServicio;
import ucla.si.modelo.Persona;
import ucla.si.modelo.Presupuesto;
import ucla.si.modelo.Prueba;
import ucla.si.modelo.Reclamo;
import ucla.si.modelo.TipoReclamo;
import ucla.si.modelo.Vehiculo;
import ucla.si.modelo.Usuario;
import ucla.si.servicio.ServicioGarantia;
import ucla.si.servicio.ServicioGarantia;
import ucla.si.servicio.ServicioOrdenEntrega;
import ucla.si.servicio.ServicioReclamo;
import ucla.si.servicio.ServicioTipoReclamo;

public class ControladorReclamoVerificarGarantia extends ControladorInicio {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioReclamo servicioReclamo;

	@WireVariable
	private ServicioTipoReclamo servicioTipoReclamo;
	
	@WireVariable
	private ServicioOrdenEntrega servicioOrdenEntrega;
	
	@WireVariable
	private ServicioGarantia servicioGarantia;

	@Wire
	private Textbox txtTipoReclamo;

	@Wire
	private Textbox txtFechaReclamo;

	@Wire
	private Textbox txtFechaOrdenEntrega;

	@Wire
	private Textbox txtDescOrdenEntrega;

	@Wire
	private Textbox txtFechaGarantia;

	@Wire
	private Textbox txtDescGarantia;

	@Wire
	private Textbox txtTiempoGarantia;

	@Wire
	private Textbox txtDescripcion;

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

	private Reclamo reclamo = null;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
		reclamo = (Reclamo) getAtributo("reclamo");
		cargarDatos(reclamo);

	}

	public void cargarDatos(Reclamo reclamo) {
		// Tipo de Reclamo
		TipoReclamo tipoReclamo = reclamo.getTipoReclamo();

		// Datos Orden de Entrega
		String fechaOrdenEntrega = reclamo.getOrdenEntrega().getFechaCreacion().toString();
		String descOrdenEntrega = reclamo.getOrdenEntrega().getDescripcion();
		txtFechaOrdenEntrega.setValue(fechaOrdenEntrega.trim());
		txtDescOrdenEntrega.setValue(descOrdenEntrega);

		// Datos Garantia
		String fechaGarantia = reclamo.getOrdenEntrega().getGarantia().getFechaCreacion().toString();
		String descGarantia = reclamo.getOrdenEntrega().getGarantia().getDescripcion();
		String tiempoVigencia = reclamo.getOrdenEntrega().getGarantia().getTiempoVigencia() + " Dias";
		txtFechaGarantia.setValue(fechaGarantia.trim());
		txtDescGarantia.setValue(descGarantia.trim());
		txtTiempoGarantia.setValue(tiempoVigencia.trim());
		
		Prueba prueba = reclamo.getOrdenEntrega().getPrueba();
		OrdenServicio ordenServicio = prueba.getOrdenServicio();
		Presupuesto presupuesto = ordenServicio.getPresupuesto();
		Cita cita = presupuesto.getCita();
		Vehiculo vehiculo = cita.getVehiculo();
		Usuario usuario = vehiculo.getUsuario();
		Persona persona = usuario.getPersona();
		
		

		// Cliente
		txtClienteFoto.setValue("Foto");
		txtClienteNombre.setValue(persona.getNombre().toString());
		txtClienteApellido.setValue(persona.getApellido().toString());
		txtClienteDireccion.setValue(persona.getDireccion().toString());
		txtClienteCorreo.setValue(usuario.getCorreo().toString());

		// Auto
		txtAutoPlaca.setValue(vehiculo.getPlaca().toString());
		txtAutoMarca.setValue(vehiculo.getMarca().getNombre().toString());
		txtAutoAnno.setValue(vehiculo.getAnno().toString());
		txtAutoModelo.setValue(vehiculo.getModelo().getNombre().toString());

		// Reclamo
		txtTipoReclamo.setValue(tipoReclamo.getDescripcion().trim());
		txtDescripcion.setValue(reclamo.getDescripcion().trim());
		txtFechaReclamo.setValue(reclamo.getFechaCreacion().toString().trim());

		// desabilitar
		txtTipoReclamo.setDisabled(true);
		txtDescripcion.setDisabled(true);
		txtFechaOrdenEntrega.setDisabled(true);
		txtDescOrdenEntrega.setDisabled(true);
		txtFechaReclamo.setDisabled(true);
		txtFechaGarantia.setDisabled(true);
		txtDescGarantia.setDisabled(true);
		txtTiempoGarantia.setDisabled(true);
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
		String dir = "gps/reclamo/frm-reclamo-reclamos.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#btnVolver")
	public void volver() {
		String dir = "gps/reclamo/frm-reclamo-reclamos.zul";
		clearDivApp(dir);
	}

	@Listen("onClick =#btnNoProcede")
	public void cancelar() {
		String dir = "gps/motivo/frm-motivo-incluir.zul";
		setAtributo("reclamo", reclamo);
		clearDivApp(dir);
	}
}
