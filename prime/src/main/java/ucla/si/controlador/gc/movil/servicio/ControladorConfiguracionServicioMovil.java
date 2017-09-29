package ucla.si.controlador.gc.movil.servicio;

import java.util.Collections;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ucla.si.controlador.app.ControladorInicio;
import ucla.si.modelo.Servicio;
import ucla.si.modelo.Servicio;
import ucla.si.servicio.ServicioServicio;
import ucla.si.servicio.ServicioServicio;

public class ControladorConfiguracionServicioMovil extends ControladorInicio {

	/**
	 * 
	 */

	@WireVariable
	private ServicioServicio servicioServicio;

	@Wire
	Listbox listServicios;

	@Wire
	private Textbox txtBuscar;

	private static final long serialVersionUID = 1L;

	@Override
	protected void inicializar() {
		ListModelList<Servicio> servicios = new ListModelList<Servicio>(servicioServicio.listarServicios());
		Collections.reverse(servicios);
		servicios.setMultiple(false);
		listServicios.setModel(servicios);
		listServicios.setMultiple(false);
		listServicios.setCheckmark(false);
	}

	@Listen("onClick =#servicio")
	public void servicioCatalogo() {
		String dir = "gc/movil/servicio/cat-servicio.zul";
		clearDivAppWeb(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listServicio")
	public void actualizarListbox() {
		if (listServicios.getItemCount() > 0) {
			asignarEventos(listServicios);
		}
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
        System.out.println("incluirraqui");
		String dir = "gc/movil/servicio/frm-servicio-incluir.zul";
		clearDivAppWeb(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

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

	@Listen("onClick =#mItemBuscar")
	public void mItemBuscar() {
		ListModelList<Servicio> servicios = new ListModelList<Servicio>(
				servicioServicio.buscarServicios(txtBuscar.getValue().trim().toString()));
		Collections.reverse(servicios);
		servicios.setMultiple(false);
		listServicios.setModel(servicios);
		listServicios.setMultiple(false);
		listServicios.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	public void verificarAcciones(MouseEvent mouseEvent) {
		try {
			Button boton = (Button) mouseEvent.getTarget();
			Servicio servicio = (Servicio) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("servicio", servicio);
				String dir = "gc/movil/servicio/frm-editar-servicio.zul";
				clearDivAppWeb(dir);
			}
			if (boton.getTooltiptext().equals("Eliminar")) {
				setAtributo("servicio", servicio);
				Messagebox.show("¿Estas seguro de eliminar el Servicio?", "Confirmación",
						Messagebox.YES | Messagebox.CANCEL, Messagebox.QUESTION,
						new org.zkoss.zk.ui.event.EventListener() {
							public void onEvent(Event evt) throws InterruptedException {
								if (evt.getName().equals("onYes")) {
									//servicioServicio.eliminarServicio((Servicio) getAtributo("servicio"));
									inicializar();
									txtBuscar.setValue("");
									Messagebox.show("Servicio Eliminado con exito", "Información", Messagebox.OK,
											Messagebox.INFORMATION);

								}
							}
						});

			}

		} catch (org.springframework.transaction.TransactionTimedOutException e) {
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información", Messagebox.OK, Messagebox.ERROR);
		} catch (org.hibernate.TransactionException e) {
			Messagebox.show("¡Tiempo Expirado para la transacción!", "Información", Messagebox.OK, Messagebox.ERROR);
		}
	}

}
