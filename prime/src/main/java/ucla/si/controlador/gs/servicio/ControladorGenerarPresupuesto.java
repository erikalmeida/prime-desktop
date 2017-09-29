package ucla.si.controlador.gs.servicio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.dao.presupuestoServicioDAO;
import ucla.si.modelo.Falla;
import ucla.si.modelo.FallaPresupuesto;
import ucla.si.modelo.Presupuesto;
import ucla.si.modelo.PresupuestoServicio;
import ucla.si.modelo.PresupuestoTipoRepuesto;
import ucla.si.modelo.ServiciosTecnologias;
import ucla.si.modelo.Servicio;

import ucla.si.modelo.ServiciosEtapas;
import ucla.si.modelo.Cita;
import ucla.si.modelo.Etapa;
import ucla.si.modelo.Eventualidad;
import ucla.si.modelo.Falla;
import ucla.si.servicio.ServicioFalla;
import ucla.si.servicio.ServicioFallaPresupuesto;
import ucla.si.servicio.ServicioPresupuesto;
import ucla.si.servicio.ServicioPresupuestoServicio;
import ucla.si.servicio.ServicioPresupuestoTipoRepuesto;
import ucla.si.servicio.ServicioProfesion;
import ucla.si.servicio.ServicioServicio;
import ucla.si.servicio.ServicioServiciosEtapas;

import ucla.si.servicio.ServicioServiciosTecnologias;
import ucla.si.servicio.ServicioEtapa;
import ucla.si.servicio.ServicioFalla;
import ucla.si.servicio.ServicioTecnologia;
import ucla.si.servicio.ServicioTipoRepuesto;

import ucla.si.modelo.Tecnologia;
import ucla.si.modelo.TipoRepuesto;
import ucla.si.modelo.Notificacion;

public class ControladorGenerarPresupuesto extends ControladorInicio {

	/**
	 * 
	 */

	private Servicio servicio;// inventando

	// private ServiciosTecnologias serviciosTecnologias;
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServiciosTecnologias serviciosTecnologias;

	@WireVariable
	private ServiciosEtapas serviciosEtapas;

	@WireVariable
	private ServicioServicio servicioServicio;

	@WireVariable
	private FallaPresupuesto fallaPresupuesto;

	@WireVariable
	private ServicioPresupuesto servicioPresupuesto;

	@WireVariable
	private PresupuestoServicio presupuestoServicio;

	@WireVariable
	private ServicioFallaPresupuesto servicioFallaPresupuesto;

	@WireVariable
	private ServicioServiciosTecnologias servicioServiciosTecnologias;

	@WireVariable
	private ServicioServiciosEtapas servicioServiciosEtapas;

	@WireVariable
	private ServicioFalla servicioFalla;

	@WireVariable
	private ServicioPresupuestoServicio servicioPresupuestoServicio;

	@WireVariable
	private ServicioPresupuestoTipoRepuesto servicioPresupuestoTipoRepuesto;

	/*
	 * @WireVariable private ServiciosFallas serviciosFallas;
	 */

	@WireVariable
	private Cita cita;

	@WireVariable
	private Presupuesto presupuesto;

	@WireVariable
	private TipoRepuesto tipoRepuesto;

	@WireVariable
	private ServicioTipoRepuesto servicioTipoRepuesto;

	@WireVariable
	private PresupuestoTipoRepuesto presupuestoTipoRepuesto;

	@Wire
	Listbox listFallas, listServicios, listTipoRepuestos;

	@Override
	protected void inicializar() {

		cita = (Cita) getAtributo("cita");
		// System.out.println("id de cita: "+cita.getId());

		ListModelList<Falla> modeloFallas = new ListModelList<Falla>(servicioFalla.listarFallas());
		modeloFallas.setMultiple(true);
		listFallas.setModel(modeloFallas);
		listFallas.setMultiple(true);
		listFallas.setCheckmark(true);

		ListModelList<TipoRepuesto> modeloTipoRepuestos = new ListModelList<TipoRepuesto>(
				servicioTipoRepuesto.listarTipoRepuestos());
		modeloTipoRepuestos.setMultiple(true);
		listTipoRepuestos.setModel(modeloTipoRepuestos);
		listTipoRepuestos.setMultiple(true);
		listTipoRepuestos.setCheckmark(true);

		ListModelList<Servicio> modeloServicios = new ListModelList<Servicio>(servicioServicio.listarServicios());
		modeloServicios.setMultiple(true);
		listServicios.setModel(modeloServicios);
		listServicios.setMultiple(true);
		listServicios.setCheckmark(true);

	}

	@Listen("onClick =#btnAnnadirFalla")
	public void annadirFallas() {

		List<Listitem> listItemFallas = new ArrayList<Listitem>(listFallas.getItems());
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
		boolean test = false;
		presupuesto = new Presupuesto();
		presupuesto.setFechaCreacion(new Date());
		presupuesto.setEstatus("Activo");
		presupuesto.setCita(cita);
		presupuesto.setEstado("En espera");
		System.out.println("---------------antes---------aqui" + presupuesto.getDescripcion());
		servicioPresupuesto.incluirPresupuesto(presupuesto);
		System.out.println("---------------despues--------");
		// setAtributo("presupuesto", presupuesto);

		if (!listFallas.getItems().isEmpty()) {
			for (Listitem listitem : listItemFallas) {
				if (listitem.isSelected()) {
					fallaPresupuesto = new FallaPresupuesto();
					Falla falla = (Falla) listitem.getValue();

					if (falla != null) {

						fallaPresupuesto.setFalla(falla);
						fallaPresupuesto.setPresupuesto(presupuesto);
						fallaPresupuesto.setEstatus("Activo");

						fallaPresupuesto.setFechaCreacion(new Date());
						servicioFallaPresupuesto.incluirFallaPresupuesto(fallaPresupuesto);
						System.out.println("y Entonces: aqui deberia ir un presupuesto: " + presupuesto.getId());
						// servicioServiciosFallas.incluirServiciosFallas(serviciosFallas);
						// servicioFallaPresupuesto.incluirFallaPresupuesto(fallaPresupuesto);
						test = true;
						// System.out.println("sdsadsadsads:"+servicio.getFalla().getId());

					}
				}
			}

			if (test == true) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gs/servicio/frm-realizar-presupuesto.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnPresupuesto")
	public void generarPresupuesto() {
		/*
		Notificacion notificacion = new Notificacion();
		notificacion.setDescripcion("Monto a cancelar");
		notificacion.setEstatus("Activo");
		notificacion.setNombre("Presupuesto");
		notificacion.setFechacreacion(new Date());
		notificacion.setPresupuesto(this.presupuesto);
		Eventualidad eventualidad = new Eventualidad();
		eventualidad.setEstatus("Activo");
		eventualidad.setFechaCreacion(new Date());
		eventualidad.setDescripcion("Notificación");
		eventualidad.setId(presupuesto.getId());
		notificacion.setEventualidad(eventualidad);*/
		String dir = "gs/presupuesto/frm-presupuesto.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onClick =#btnAnnadirRepuesto")
	public void annadirRepuesto() {

		List<Listitem> listItemTipoRepuestos = new ArrayList<Listitem>(listTipoRepuestos.getItems());
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
		boolean test = false;
		// presupuesto =new Presupuesto();

		if (!listTipoRepuestos.getItems().isEmpty()) {

			for (Listitem listitem : listItemTipoRepuestos) {
				if (listitem.isSelected()) {
					presupuestoTipoRepuesto = new PresupuestoTipoRepuesto();
					TipoRepuesto tipoRepuesto = (TipoRepuesto) listitem.getValue();

					if (tipoRepuesto != null) {

						presupuesto = servicioPresupuesto.buscarPresupuesto(cita.getId());
						presupuestoTipoRepuesto.setTipoRepuesto(tipoRepuesto);

						presupuestoTipoRepuesto.setPresupuesto(presupuesto);
						presupuestoTipoRepuesto.setEstatus("Activo");

						presupuestoTipoRepuesto.setFechaCreacion(new Date());
						// presupuestoTipoRepuesto.setPresupuesto(presupuesto);
						// montoTotal+=servicio.getTarifa();

						servicioPresupuesto.editarPresupuesto(presupuesto);

						servicioPresupuestoTipoRepuesto.editarPresupuestoTipoRepuesto(presupuestoTipoRepuesto);
						System.out.println("y Entonces: aqui deberia ir un presupuesto: " + presupuesto.getId());
						// servicioServiciosFallas.incluirServiciosFallas(serviciosFallas);
						// servicioFallaPresupuesto.incluirFallaPresupuesto(fallaPresupuesto);
						test = true;

					}
				}
			}

			if (test == true) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gs/servicio/frm-realizar-presupuesto.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#btnAnnadirServicio")
	public void annadirServicio() {

		List<Listitem> listItemServicios = new ArrayList<Listitem>(listServicios.getItems());
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
		boolean test = false;
		// presupuesto =new Presupuesto();

		/*
		 * presupuesto =new Presupuesto(); presupuesto.setFechaCreacion(new
		 * Date()); presupuesto.setEstatus("Activo"); presupuesto.setCita(cita);
		 * presupuesto.setEstado("En espera");
		 * 
		 * servicioPresupuesto.incluirPresupuesto(presupuesto);
		 */

		// setAtributo("presupuesto", presupuesto);
		if (!listServicios.getItems().isEmpty()) {
			float montoTotal = 0;
			for (Listitem listitem : listItemServicios) {
				if (listitem.isSelected()) {
					presupuestoServicio = new PresupuestoServicio();
					Servicio servicio = (Servicio) listitem.getValue();

					if (servicio != null) {

						presupuestoServicio.setServicio(servicio);
						montoTotal += servicio.getTarifa();
						presupuestoServicio.setPresupuesto(presupuesto);
						presupuestoServicio.setEstatus("Activo");
						presupuesto = servicioPresupuesto.buscarPresupuesto(cita.getId());
						presupuestoServicio.setFechaCreacion(new Date());
						presupuestoServicio.setPresupuesto(presupuesto);
						// montoTotal+=servicio.getTarifa();

						servicioPresupuesto.editarPresupuesto(presupuesto);

						servicioPresupuestoServicio.editarPresupuestoServicio(presupuestoServicio);
						System.out.println("y Entonces: aqui deberia ir un presupuesto: " + presupuesto.getId());
						// servicioServiciosFallas.incluirServiciosFallas(serviciosFallas);
						// servicioFallaPresupuesto.incluirFallaPresupuesto(fallaPresupuesto);
						test = true;
						// System.out.println("sdsadsadsads:"+servicio.getFalla().getId());

					}
				}
			}
			presupuesto.setMonto_total(montoTotal);
			servicioPresupuesto.editarPresupuesto(presupuesto);
			System.out.println("Monto Total: " + montoTotal);

			if (test == true) {
				Messagebox.show("Inclusión exitosa", "Información", Messagebox.OK, Messagebox.INFORMATION);
				String dir = "gs/servicio/frm-realizar-presupuesto.zul";
				clearDivApp(dir);
			} else {
				Messagebox.show("Error al guardar", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gs/servicio/frm-servicioFalla.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

}
