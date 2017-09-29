package ucla.si.controlador.gs.servicio.ordenEntrega;

import java.util.Date;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Cita;
import ucla.si.modelo.Garantia;
import ucla.si.modelo.OrdenEntrega;
import ucla.si.modelo.OrdenServicio;
import ucla.si.modelo.Persona;
import ucla.si.modelo.Presupuesto;
import ucla.si.modelo.Prueba;
import ucla.si.modelo.TipoGarantia;
import ucla.si.servicio.ServicioCita;
import ucla.si.servicio.ServicioGarantia;
import ucla.si.servicio.ServicioOrdenEntrega;
import ucla.si.servicio.ServicioOrdenServicio;
import ucla.si.servicio.ServicioPresupuesto;
import ucla.si.servicio.ServicioTipoGarantia;

public class ControladorGenerarOrdenEntrega extends ControladorInicio {
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
	private Combobox cmbTipoGarantia;

	@Wire
	private Textbox txtDescripcion;

	@Wire
	private Textbox txtDescripcionGarantia;

	@Wire
	private Textbox txtDescripcionPrueba;

	@Wire
	private Textbox txtFechaPrueba;
	@Wire
	private Intbox intBoxTiempoVigencia;

	private OrdenServicio ordenServicio = null;

	@WireVariable
	private ServicioPresupuesto servicioPresupuesto;

	@WireVariable
	private ServicioOrdenServicio servicioOrdenServicio;

	@WireVariable
	private ServicioCita servicioCita;

	@WireVariable
	private ServicioOrdenEntrega servicioOrdenEntrega;

	@WireVariable
	private ServicioGarantia servicioGarantia;

	@WireVariable
	private ServicioTipoGarantia servicioTipoGarantia;

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub

		ListModelList<TipoGarantia> modeloTipoGarantias = new ListModelList<TipoGarantia>(
				servicioTipoGarantia.listarTipoGarantias());
		if (!modeloTipoGarantias.isEmpty()) {
			modeloTipoGarantias.setMultiple(false);
			cmbTipoGarantia.setModel(modeloTipoGarantias);
			cmbTipoGarantia.setMultiline(false);
		}

		ordenServicio = (OrdenServicio) getAtributo("ordenServicio");
		cargarDatos(ordenServicio);

	}

	public void cargarDatos(OrdenServicio ordenServicio) {
		// Presupuesto
		Long idPresupuesto = ordenServicio.getPresupuesto().getId();
		Presupuesto presupuesto = (Presupuesto) servicioPresupuesto.getPresupuesto(idPresupuesto);

		// Cita
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

		// Orden Servicio
		txtOrdenServicioFecha.setValue(ordenServicio.getFechaCreacion().toString());
		txtOrdenServicioEstado.setValue(ordenServicio.getEstado().toString());
		txtOrdenServicioDescripcion.setValue(ordenServicio.getDescripcion().toString());
		// Servicio
		txtServicio.setValue(cita.getServicio().getDescripcion().toString());

		// Prueba
		txtDescripcionPrueba.setValue(ordenServicio.getPrueba().get(0).getDescripcion().toString());
		txtFechaPrueba.setValue(ordenServicio.getPrueba().get(0).getFechaCreacion().toString());

		// desabilitar
		txtDescripcionPrueba.setDisabled(true);
		txtFechaPrueba.setDisabled(true);
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

		int tVigencia = intBoxTiempoVigencia.intValue();

		if (txtDescripcion.getValue().trim() == "" || cmbTipoGarantia.getSelectedIndex() == -1 || tVigencia <= 0) {
			Messagebox.show("Debe llenar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			// Garantia
			TipoGarantia tipoGarantia = (TipoGarantia) cmbTipoGarantia.getSelectedItem().getValue();
			String descripcionG = txtDescripcionGarantia.getValue().toString();
			int tiempoVigencia = intBoxTiempoVigencia.getValue();

			Garantia garantia = new Garantia(descripcionG, tiempoVigencia, "Aprobada", "Activo", new Date(), null);
			garantia.setTipoGarantia(tipoGarantia);
			// save garantia
			if (servicioGarantia.incluirGarantia(garantia)) {
				String descripcion = txtDescripcion.getValue().toUpperCase();
				Prueba prueba = ordenServicio.getPrueba().get(0);
				OrdenEntrega ordenEntrega = new OrdenEntrega(descripcion, "Activo", "Pendiente", new Date(), null);
				ordenEntrega.setGarantia(garantia);
				ordenEntrega.setPrueba(prueba);

				if (servicioOrdenEntrega.incluirOrdenEntrega(ordenEntrega)) {
					Messagebox.show("Inclusion exitosa", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
					String dir = "gs/ordenEntrega/frm-servicios-finalizados.zul";
					clearDivApp(dir);
				} else {
					Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}

	}

	@Listen("onClick =#btnVolver")
	public void volver() {
		String dir = "gs/ordenEntrega/frm-servicios-finalizados.zul";
		clearDivApp(dir);
	}

}
