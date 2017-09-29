package ucla.si.controlador.gc.movil.promocion;

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
import ucla.si.modelo.Promocion;
import ucla.si.servicio.ServicioPromocion;

public class ControladorConfiguracionPromocionMovil extends ControladorInicio {

	/**
	 * 
	 */

	@WireVariable
	private ServicioPromocion servicioPromocion;

	@Wire
	Listbox listPromocions;

	@Wire
	private Textbox txtBuscar;

	private static final long serialVersionUID = 1L;

	@Override
	protected void inicializar() {
		ListModelList<Promocion> promocions = new ListModelList<Promocion>(servicioPromocion.listarPromocions());
		Collections.reverse(promocions);
		promocions.setMultiple(false);
		listPromocions.setModel(promocions);
		listPromocions.setMultiple(false);
		listPromocions.setCheckmark(false);

	}

	@Listen("onClick =#promocion")
	public void promocionCatalogo() {
		String dir = "gc/movil/promocion/cat-promocion.zul";
		clearDivAppWeb(dir);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	@Listen("onAfterRender =#listPromocion")
	public void actualizarListbox() {
		if (listPromocions.getItemCount() > 0) {
			asignarEventos(listPromocions);
		}
	}

	@Listen("onClick =#mItemIncluir")
	public void mItemIncluir() {
        System.out.println("incluirraqui");
		String dir = "gc/movil/promocion/frm-promocion-incluir.zul";
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
		ListModelList<Promocion> promocions = new ListModelList<Promocion>(
				servicioPromocion.buscarPromociones(txtBuscar.getValue().trim().toString()));
		Collections.reverse(promocions);
		promocions.setMultiple(false);
		listPromocions.setModel(promocions);
		listPromocions.setMultiple(false);
		listPromocions.setCheckmark(false);
		// Clients.evalJavaScript("document.title = 'ServiAldanas'; ");
	}

	public void verificarAcciones(MouseEvent mouseEvent) {
		try {
			Button boton = (Button) mouseEvent.getTarget();
			Promocion promocion = (Promocion) (((Listitem) mouseEvent.getTarget().getParent().getParent()).getValue());

			if (boton.getTooltiptext().equals("Editar")) {
				setAtributo("promocion", promocion);
				String dir = "gc/movil/promocion/frm-editar-promocion.zul";
				clearDivAppWeb(dir);
			}
			if (boton.getTooltiptext().equals("Eliminar")) {
				setAtributo("promocion", promocion);
				Messagebox.show("¿Estas seguro de eliminar el Promocion?", "Confirmación",
						Messagebox.YES | Messagebox.CANCEL, Messagebox.QUESTION,
						new org.zkoss.zk.ui.event.EventListener() {
							public void onEvent(Event evt) throws InterruptedException {
								if (evt.getName().equals("onYes")) {
									//servicioPromocion.eliminarPromocion((Promocion) getAtributo("promocion"));
									inicializar();
									txtBuscar.setValue("");
									Messagebox.show("Promocion Eliminado con exito", "Información", Messagebox.OK,
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
