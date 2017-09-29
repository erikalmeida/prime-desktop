package ucla.si.controlador.gs.servicio;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Cita;
import ucla.si.modelo.Falla;
import ucla.si.modelo.Herramienta;
import ucla.si.modelo.Presupuesto;
import ucla.si.modelo.Servicio;
import ucla.si.modelo.TipoRepuesto;
import ucla.si.servicio.ServicioFalla;
import ucla.si.servicio.ServicioPresupuesto;
import ucla.si.servicio.ServicioServicio;
import ucla.si.servicio.ServicioTipoRepuesto;

public class ControladorPresupuesto extends ControladorInicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@WireVariable
	private ServicioServicio servicioServicio;

	@WireVariable
	private ServicioFalla servicioFalla;

	@WireVariable
	private ServicioTipoRepuesto servicioTipoRepuesto;

	@WireVariable
	private TipoRepuesto tipoRepuesto;

	@WireVariable
	private Falla falla;

	@WireVariable
	private Presupuesto presupuesto;

	@WireVariable
	private Cita cita;

	@WireVariable
	private ServicioPresupuesto servicioPresupuesto;

	@Wire
	Label montoTotal;

	@Wire
	Listbox listServicios, listFallas, listTipoRepuestos;

	@Override
	protected void inicializar() {

		/*cita = (Cita) getAtributo("cita");
		presupuesto = new Presupuesto();
		System.out.println("3 controladores: " + cita.getId());
		presupuesto = servicioPresupuesto.buscarPresupuesto(cita.getId());
		System.out.println("id de presupuesto generado: " + presupuesto.getId());*/
		
		presupuesto = (Presupuesto) getAtributo("presupuesto");
		
		montoTotal.setValue(Float.toString(presupuesto.getMonto_total()));
		System.out.println("id de presupuesto: " + presupuesto.getId());
		System.out.println("por fin" + presupuesto.getId());

		ListModelList<Servicio> modeloServicios = new ListModelList<Servicio>(
				servicioServicio.listarServicioXPresupuesto(presupuesto.getId()));

		servicioServicio.listarServicioXPresupuesto(presupuesto.getId());
		modeloServicios.setMultiple(true);
		listServicios.setModel(modeloServicios);
		listServicios.setMultiple(true);
		listServicios.setCheckmark(true);

		ListModelList<Falla> modeloFallas = new ListModelList<Falla>(
				servicioFalla.listarFallaXPresupuesto(presupuesto.getId()));
		servicioFalla.listarFallaXPresupuesto(presupuesto.getId());
		modeloFallas.setMultiple(true);
		listFallas.setModel(modeloFallas);
		listFallas.setMultiple(true);
		listFallas.setCheckmark(true);

		ListModelList<TipoRepuesto> modeloTipoRepuestos = new ListModelList<TipoRepuesto>(
				servicioTipoRepuesto.listarRepuestoXPresupuesto(presupuesto.getId()));
		modeloTipoRepuestos.setMultiple(true);
		listTipoRepuestos.setModel(modeloTipoRepuestos);
		listTipoRepuestos.setMultiple(true);
		listTipoRepuestos.setCheckmark(true);

	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
		String dir = "gs/servicio/frm-servicio-incluir.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listServicios")
	public void actualizarListbox() {
		if (listServicios.getItemCount() > 0) {
			asignarEventos(listServicios);
		}
	}

	@Listen("onClick =#btnConfigurar")
	public void configurarServicio() {
		String dir = "gs/servicio/frm-configurar-servicio-falla.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#btnValidar")
	public void validarPresupuesto() {
		presupuesto.setEstado("Validada");
		servicioPresupuesto.editarPresupuesto(presupuesto);
		Messagebox.show("¡Presupuesto validado exitosamente!", "Información", Messagebox.OK, null);
		String dir = "index.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}
	
	@Listen("onClick =#btnRechazar")
	public void rechazarPresupuesto() {
		presupuesto.setEstado("Rechazada");
		servicioPresupuesto.editarPresupuesto(presupuesto);
		String dir = "index.zul";
		clearDivApp(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	// btnConfigurar
	private void asignarEventos(Component padre) {
		String click = "onClick";
		for (Component hijo : padre.getChildren()) {
			if (hijo instanceof Button) {
				hijo.addEventListener(click, new EventListener<MouseEvent>() {
					public void onEvent(MouseEvent mouseEvent) throws Exception {
						verificarAcciones(mouseEvent);
					}
				});
			}
			asignarEventos(hijo);
		}
	}

	public void verificarAcciones(MouseEvent mouseEvent) {
		try {
			Button boton = (Button) mouseEvent.getTarget();
			Servicio servicio = (Servicio) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("servicio", servicio);
				String dir = "gs/servicio/frm-servicio-editar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Consultar")) {
				setAtributo("servicio", servicio);
				String dir = "gs/servicio/frm-servicio-consultar.zul";
				clearDivApp(dir);
			} else if (boton.getTooltiptext().equals("Eliminar")) {
				Messagebox.show("¡En Construcción!", "Información", Messagebox.OK, Messagebox.EXCLAMATION);
			} else {

			}
		} catch (org.springframework.transaction.TransactionTimedOutException e) {
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información", Messagebox.OK, Messagebox.ERROR);
		} catch (org.hibernate.TransactionException e) {
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información", Messagebox.OK, Messagebox.ERROR);
		}
	}

}
